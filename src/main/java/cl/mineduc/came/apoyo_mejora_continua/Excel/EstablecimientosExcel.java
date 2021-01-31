package cl.mineduc.came.apoyo_mejora_continua.Excel;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import cl.mineduc.came.apoyo_mejora_continua.dto.establishment.EstablecimientosDTO;

public class EstablecimientosExcel {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<EstablecimientosDTO> records;
     
    public EstablecimientosExcel(List<EstablecimientosDTO> records) {
        this.records = records;
        workbook = new XSSFWorkbook();
    }
 
 
    private void writeHeaderLine() {
        sheet = workbook.createSheet("Users");
         
        Row row = sheet.createRow(0);
         
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);
         
        createCell(row, 0, "RBD", style);      
        createCell(row, 1, "Nombre establecimiento", style);       
        createCell(row, 2, "Región", style);    
        createCell(row, 3, "Deprov", style);
        createCell(row, 4, "comuna", style);
        createCell(row, 5, "Ruralidad", style);
        createCell(row, 6, "Categorización", style);
        createCell(row, 7, "Clasificación SEP", style);
        createCell(row, 8, "Dependencia", style);
        createCell(row, 9, "Estado", style);         
    }
     
    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        }else {
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
                 
        for (EstablecimientosDTO est : records) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
             
            createCell(row, columnCount++, est.getRbd(), style);
            createCell(row, columnCount++, est.getNombre(), style);
            createCell(row, columnCount++, est.getRegion(), style);
            createCell(row, columnCount++, est.getDeprov(), style);
            createCell(row, columnCount++, est.getComuna(), style);
            createCell(row, columnCount++, est.getRuralidad(), style);
            createCell(row, columnCount++, est.getCategorizacion(), style);
            createCell(row, columnCount++, est.getClasificacionSEP(), style);
            createCell(row, columnCount++, est.getDependencia(), style);
            createCell(row, columnCount++, est.getEstado(), style);
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
