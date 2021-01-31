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

import cl.mineduc.came.apoyo_mejora_continua.dto.survey.SurveyAnswerExcelDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.survey.SurveyExcelDTO;

public class SurveyExcel {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private SurveyExcelDTO records;    

    public SurveyExcel(SurveyExcelDTO records) {
        this.records = records;
        workbook = new XSSFWorkbook();
    }

    private void writeHeaderLine(List<String> header) {
        sheet = workbook.createSheet("Encuesta");

        Row row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);

        header.forEach(x ->{
            createCell(row, header.indexOf(x), x, style);    
        });
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

    private void writeDataLines(List<SurveyAnswerExcelDTO> items) {
        int rowCount = 1;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
        
        for (SurveyAnswerExcelDTO obj : items) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;

            createCell(row, columnCount++, obj.getNombre(), style);
            createCell(row, columnCount++, obj.getPerfiles(), style);
            createCell(row, columnCount++, obj.getFechaInicio(), style);            
            createCell(row, columnCount++, obj.getFechaFin(), style);
            createCell(row, columnCount++, obj.getUsuario(), style);
            for (Integer val : obj.getRespuestas()) {
                createCell(row, columnCount++, val, style);
            }
            createCell(row, columnCount++, obj.getComentario(), style);
        }
    }

    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine(this.records.getHeader());
        writeDataLines(this.records.getItems());

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();

    }

}
