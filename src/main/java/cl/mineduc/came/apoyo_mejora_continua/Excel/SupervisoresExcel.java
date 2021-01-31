package cl.mineduc.came.apoyo_mejora_continua.Excel;

import java.io.IOException;
import java.util.ArrayList;
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

import cl.mineduc.came.apoyo_mejora_continua.dto.assignment.AssignmentListDTO;

public class SupervisoresExcel {

    /**
     *
     */
    private static final String SIN_ASIGNAR = "Sin Asignar";
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<AssignmentListDTO> records;

    public SupervisoresExcel(List<AssignmentListDTO> records) {
        workbook = new XSSFWorkbook();
        this.records = records;
    }

    private void writeHeaderLine() {
        sheet = workbook.createSheet("Supervisores");

        Row row = sheet.createRow(0);
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);

        createCell(row, 0, "Supervisor", style);
        createCell(row, 1, "RBD", style);
        createCell(row, 2, "Región", style);
        createCell(row, 3, "Deprov", style);
        createCell(row, 4, "Comuna", style);
        createCell(row, 5, "Tipo", style);
        createCell(row, 6, "Tipo apoyo", style);
        createCell(row, 7, "Categoría", style);
        createCell(row, 8, "Nombre red", style);

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

        for (AssignmentListDTO sup : records) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
            if (null != sup.getSupervisors()) {
                StringUtils.join(sup.getSupervisors(), ";");
                createCell(row, columnCount++, StringUtils.join(sup.getSupervisors(), ";"), style);
                createCell(row, columnCount++, sup.getRbd(), style);
                createCell(row, columnCount++, sup.getRegion(), style);
                createCell(row, columnCount++, sup.getDeprov(), style);
                createCell(row, columnCount++, sup.getComuna(), style);
                createCell(row, columnCount++, sup.getTipo(), style);
                createCell(row, columnCount++, sup.getTipoRed(), style);
                createCell(row, columnCount++, sup.getCategoria(), style);
                createCell(row, columnCount++, sup.getNombreRed(), style);
            } else {
                createCell(row, columnCount++, SIN_ASIGNAR, style);
                createCell(row, columnCount++, sup.getRbd(), style);
                createCell(row, columnCount++, sup.getRegion(), style);
                createCell(row, columnCount++, sup.getDeprov(), style);
                createCell(row, columnCount++, sup.getComuna(), style);
                createCell(row, columnCount++, sup.getTipo(), style);
                createCell(row, columnCount++, sup.getTipoRed(), style);
                createCell(row, columnCount++, sup.getCategoria(), style);
                createCell(row, columnCount++, sup.getNombreRed(), style);
            }

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
