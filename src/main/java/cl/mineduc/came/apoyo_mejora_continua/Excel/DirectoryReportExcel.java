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

import cl.mineduc.came.apoyo_mejora_continua.dto.report.directory.DirectoryListDTO;

public class DirectoryReportExcel {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<DirectoryListDTO> records;
     
    public DirectoryReportExcel(List<DirectoryListDTO> records) {
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
         
        createCell(row, 0, "Nombre establecimiento", style);      
        createCell(row, 1, "RBD", style);       
        createCell(row, 2, "Ruralidad", style);    
        createCell(row, 3, "Categorización", style);
        createCell(row, 4, "Clasificación SEP", style);
        createCell(row, 5, "Dependencia", style);
        createCell(row, 6, "Estado", style);
        createCell(row, 7, "Matriculas total", style);
        createCell(row, 8, "Tipo de apoyo", style);
        createCell(row, 9, "Supervisor", style);         
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
                 
        for (DirectoryListDTO est : records) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
             
            createCell(row, columnCount++, est.getNombre(), style);
            createCell(row, columnCount++, est.getRbd(), style);
            createCell(row, columnCount++, est.getRuralidad(), style);
            createCell(row, columnCount++, est.getCategorizacion(), style);
            createCell(row, columnCount++, est.getClasificacionSEP(), style);
            createCell(row, columnCount++, est.getDependencia(), style);
            createCell(row, columnCount++, est.getEstado(), style);
            createCell(row, columnCount++, est.getMatriculasTotal(), style);
            createCell(row, columnCount++, est.getTipoDeApoyo(), style);
            createCell(row, columnCount++, est.getSupervisor(), style);
//            if(rowCount == 100) {
//            	break;
//            }
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
