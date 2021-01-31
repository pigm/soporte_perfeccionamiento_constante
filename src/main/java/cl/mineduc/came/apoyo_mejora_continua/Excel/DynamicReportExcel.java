package cl.mineduc.came.apoyo_mejora_continua.Excel;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import cl.mineduc.came.apoyo_mejora_continua.dto.dynamicreport.DynamicListDTO;

public class DynamicReportExcel {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<DynamicListDTO> records;

    public DynamicReportExcel(List<DynamicListDTO> records) {
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

        createCell(row, 0, "RBD", style);
        createCell(row, 1, "Establecimiento", style);
        createCell(row, 2, "Estado", style);
        createCell(row, 3, "Región", style);
        createCell(row, 4, "Deprov", style);
        createCell(row, 5, "Comuna", style);
        createCell(row, 6, "Dependencia", style);
        createCell(row, 7, "Categorización", style);
        createCell(row, 8, "Periodo", style);
        createCell(row, 9, "Tipo de apoyo", style);
        createCell(row, 10, "Tipo de red", style);
        createCell(row, 11, "Supervisor directa", style);
        createCell(row, 12, "Supervisor red", style);
        createCell(row, 13, "Nombre de la red", style);
        createCell(row, 14, "Sesiones programadas directa", style);
        createCell(row, 15, "Sesiones programadas red", style);
        createCell(row, 16, "Sesiones realizadas directa", style);
        createCell(row, 17, "Sesiones realizadas red", style);        
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

        for (DynamicListDTO obj : records) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;

            createCell(row, columnCount++, obj.getRbd(), style);
            createCell(row, columnCount++, obj.getEstablecimiento(), style);
            createCell(row, columnCount++, obj.getEstado(), style);
            createCell(row, columnCount++, obj.getRegion(), style);
            createCell(row, columnCount++, obj.getDeprov(), style);
            createCell(row, columnCount++, obj.getComuna(), style);
            createCell(row, columnCount++, obj.getDependencia(), style);
            createCell(row, columnCount++, obj.getCategorizacion(), style);
            createCell(row, columnCount++, obj.getPeriodo(), style);
            createCell(row, columnCount++, obj.getTipoApoyo(), style);
            createCell(row, columnCount++, obj.getTipoRed(), style);
            createCell(row, columnCount++, obj.getSupervisorDirecta(), style);
            createCell(row, columnCount++, StringUtils.join(obj.getSupervisorRed(), ";"), style);
            createCell(row, columnCount++, obj.getNombreRed(), style);
            createCell(row, columnCount++, obj.getSesionesProgramadasDirecta(), style);
            createCell(row, columnCount++, obj.getSesionesProgramadasRed(), style);
            createCell(row, columnCount++, obj.getSesionesRealizadasDirecta(), style);
            createCell(row, columnCount++, obj.getSesionesRealizadasRed(), style);
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
