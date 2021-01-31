package cl.mineduc.came.apoyo_mejora_continua.Excel;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import cl.mineduc.came.apoyo_mejora_continua.dto.planning.PlanningListDTO;

public class PlanningExcel {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<PlanningListDTO> records;
    
    public PlanningExcel(List<PlanningListDTO> records) {
        this.records = records;
        workbook = new XSSFWorkbook();
    }
    
    private void writeHeaderLine() {
        sheet = workbook.createSheet("Redes");

        Row row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);
        
        createCell(row, 0, "Región", style);
        createCell(row, 1, "Deprov", style);
        createCell(row, 2, "Comuna", style);
        createCell(row, 3, "Supervisores", style);
        createCell(row, 4, "Tipo", style);
        createCell(row, 5, "Nombre Red", style);
        createCell(row, 6, "N Sesiones", style);
        createCell(row, 7, "Establecimientos", style);
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
        
        for (PlanningListDTO obj : records) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;

            createCell(row, columnCount++, obj.getRegion(), style);
            createCell(row, columnCount++, obj.getDeprov(), style);
            createCell(row, columnCount++, obj.getComuna(), style);
            createCell(row, columnCount++, obj.getSupervisores() == null || obj.getSupervisores().isEmpty() ? "" : String.join(",", obj.getSupervisores()), style);            
            createCell(row, columnCount++, obj.getTipo(), style);
            createCell(row, columnCount++, obj.getNombreRed(), style);
            createCell(row, columnCount++, obj.getSessiones(), style);
            createCell(row, columnCount++, obj.getEstablecimientos(), style);
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
