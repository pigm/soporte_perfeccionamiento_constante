package cl.mineduc.came.apoyo_mejora_continua.Excel;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import cl.mineduc.came.apoyo_mejora_continua.dto.documents.DocumentsListDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.documents.DocumentsViewDTO;

public class DocumentosExcel {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<DocumentsListDTO> records;

    public DocumentosExcel(List<DocumentsListDTO> records) {
        this.records = records;
        workbook = new XSSFWorkbook();
    }

    private void writeHeaderLine() {
        sheet = workbook.createSheet("Documentos");

        Row row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);

        createCell(row, 0, "Región", style);
        createCell(row, 1, "Tipo", style);
        createCell(row, 2, "Deprov", style);        
        createCell(row, 3, "Estado", style);
        createCell(row, 4, "Documentos", style);
    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        } else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style); 
    }

    private void writeDataLines() {
        int rowCount = 1;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);

        for (DocumentsListDTO est : records) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;

            createCell(row, columnCount++, est.getRegion(), style);
            createCell(row, columnCount++, est.getTipo(), style);
            createCell(row, columnCount++, est.getDeprov(), style);
            createCell(row, columnCount++, est.getEstado(), style);
            createCell(row, columnCount++,  String.join(",",est.getDocumentos().stream().map(DocumentsViewDTO::getName).collect(Collectors.toList())), style);
        }
    }

    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();
    }
}
