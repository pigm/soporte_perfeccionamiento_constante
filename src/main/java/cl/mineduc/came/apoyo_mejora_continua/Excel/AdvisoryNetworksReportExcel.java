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
import cl.mineduc.came.apoyo_mejora_continua.dto.report.advisory.AdvisoryResultRedDTO;

public class AdvisoryNetworksReportExcel {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<AdvisoryResultRedDTO> records;
     
    public AdvisoryNetworksReportExcel(List<AdvisoryResultRedDTO> records) {
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
         
        createCell(row, 0, "Region ", style);      
        createCell(row, 1, "Deprov ", style);       
        createCell(row, 2, "Nombre red ", style);    
        createCell(row, 3, "Tipo Red ", style);
        createCell(row, 4, "Supervisores ", style);
        createCell(row, 5, "Encargado red ", style);
        createCell(row, 6, "Cargo encargado ", style);
        createCell(row, 7, "Email encargado ", style);
        createCell(row, 8, "Número sesión(es) ", style);
        createCell(row, 9, "Tipo planificación ", style);
        createCell(row, 10, "Fecha planificada ", style);
        createCell(row, 11, "Fecha realizada", style);
        createCell(row, 12, "Estado ", style);
        createCell(row, 13, "Establecimientos red ", style);
        createCell(row, 14, "Establecimientos presentes reunión ", style);
        createCell(row, 15, "Cargo participantes ", style);
        createCell(row, 16, "% asistencia ", style);
        createCell(row, 17, "Foco trabajo 1 ", style);
        createCell(row, 18, "Foco trabajo 2 ", style);
        createCell(row, 19, "Objetivo anual 1 ", style);
        createCell(row, 20, "Objetivo anual 2 ", style);
        createCell(row, 21, "Estado foco 1 ", style);
        createCell(row, 22, "Estado foco 2 ", style);
        createCell(row, 23, "Acciones mejora foco 1 ", style);
        createCell(row, 24, "Acciones mejora foco 2 ", style);
        createCell(row, 25, "Estado acciones mejora ", style);
        createCell(row, 26, "Responsables ", style);
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
                 
        for (AdvisoryResultRedDTO est : records) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
             
            createCell(row, columnCount++, est.getRegion(), style);
            createCell(row, columnCount++, est.getDeprov(), style);
            createCell(row, columnCount++, est.getNombreRed(), style);
            createCell(row, columnCount++, est.getTipoRed(), style);
            createCell(row, columnCount++, est.getSupervisores(), style);
            createCell(row, columnCount++, est.getEncargadoRed(), style);
            createCell(row, columnCount++, est.getCargoEncargado(), style);
            createCell(row, columnCount++, est.getEmailEncargado(), style);
            createCell(row, columnCount++, est.getNumeroSesion(), style);
            createCell(row, columnCount++, est.getTipoPlanificacion(), style);
            createCell(row, columnCount++, est.getFechaPlanificacion(), style);
            createCell(row, columnCount++, est.getFechaRealizada(), style);
            createCell(row, columnCount++, est.getEstado(), style);
            createCell(row, columnCount++, est.getEstablecimientosEnRed(), style);
            createCell(row, columnCount++, est.getEstablecimientosPresentesReunion(), style);
            createCell(row, columnCount++, est.getCargoParticipantes(), style);
            createCell(row, columnCount++, est.getPorcentajeAsistencia(), style);
            createCell(row, columnCount++, est.getFocoTrabajoUno(), style);
            createCell(row, columnCount++, est.getFocoTrabajoDos(), style);
            createCell(row, columnCount++, est.getObjetivoAnualUno(), style);
            createCell(row, columnCount++, est.getObjetivoAnualDos(), style);
            createCell(row, columnCount++, est.getEstadoFocoUno(), style);
            createCell(row, columnCount++, est.getEstadoFocoDos(), style);
            createCell(row, columnCount++, est.getAccionesMejoraFocoUno(), style);
            createCell(row, columnCount++, est.getAccionesMejoraFocoDos(), style);
            createCell(row, columnCount++, est.getEstadoAccionesMejora(), style);
            createCell(row, columnCount++, est.getResponsables(), style);
            
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
