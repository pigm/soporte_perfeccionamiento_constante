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

import cl.mineduc.came.apoyo_mejora_continua.dto.report.advisory.AdvisoryResultDirectaDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.report.directory.DirectoryListDTO;

public class AdvisoryReportExcel {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<AdvisoryResultDirectaDTO> records;
     
    public AdvisoryReportExcel(List<AdvisoryResultDirectaDTO> records) {
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
         
        createCell(row, 0, "Region", style);      
        createCell(row, 1, "Deprov", style);       
        createCell(row, 2, "Rbd", style);    
        createCell(row, 3, "Establecimiento", style);
        createCell(row, 4, "Nombre supervisor", style);
        createCell(row, 5, "Número sesión", style);
        createCell(row, 6, "Tipo planificación", style);
        createCell(row, 7, "Fecha planificada", style);
        createCell(row, 8, "Fecha realizada", style);
        createCell(row, 9, "Estado", style);
        createCell(row, 10, "Foco", style);
        createCell(row, 11, "Estado foco", style);
        createCell(row, 12, "Acciones mejoras desarrollo", style);
        createCell(row, 13, "Movimiento clave comprometido", style);
        createCell(row, 14, "Movimiento clave desarrollado", style);
        createCell(row, 15, "Plazo", style);
        createCell(row, 16, "Responsable", style);
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
                 
        for (AdvisoryResultDirectaDTO est : records) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
             
            createCell(row, columnCount++, est.getRegion(), style);
            createCell(row, columnCount++, est.getDeprov(), style);
            createCell(row, columnCount++, est.getRbd(), style);
            createCell(row, columnCount++, est.getEstablecimiento(), style);
            createCell(row, columnCount++, est.getNombreSupervisor(), style);
            createCell(row, columnCount++, est.getNumeroSesion(), style);
            createCell(row, columnCount++, est.getTipoPlanificacion(), style);
            createCell(row, columnCount++, est.getFechaPlanificada(), style);
            createCell(row, columnCount++, est.getFechaRealizada(), style);
            createCell(row, columnCount++, est.getEstado(), style);
            createCell(row, columnCount++, est.getEstadoFoco(), style);
            createCell(row, columnCount++, est.getAcionesMejorasDesarrollo(), style);
            createCell(row, columnCount++, est.getMovimientoClaveComprometido(), style);
            createCell(row, columnCount++, est.getMovimientoClaveDesarrollado(), style);
            createCell(row, columnCount++, est.getPlazo(), style);
            createCell(row, columnCount++, est.getResponsable(), style);
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
