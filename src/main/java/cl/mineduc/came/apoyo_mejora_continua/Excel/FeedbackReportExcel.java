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

import cl.mineduc.came.apoyo_mejora_continua.dto.feedbackReport.FeedbackListDTO;

public class FeedbackReportExcel {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<FeedbackListDTO> records;

    public FeedbackReportExcel(List<FeedbackListDTO> records) {
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
        createCell(row, 2, "Semestre", style);
        createCell(row, 3, "Nombre jefe técnico", style);
        createCell(row, 4, "Nombre supervisor", style);
        createCell(row, 5, "Frecuencia", style);
        createCell(row, 6, "Fecha planificada", style);
        createCell(row, 7, "Fecha realizada", style);
        createCell(row, 8, "Hora retroalimentación", style);
        createCell(row, 9, "Tipo retroalimentación", style);
        createCell(row, 10, "Fecha acompañamiento activoactivo", style);
        createCell(row, 11, "Foco abordado", style);
        createCell(row, 12, "Aspectos reforzar", style);
        createCell(row, 13, "Acciones/estrategias acompañamiento", style);
        createCell(row, 14, "Responsable", style);
        createCell(row, 15, "Plazos", style);
        createCell(row, 16, "Comentarios", style);
        createCell(row, 17, "Fecha próxima reunión", style);
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

        for (FeedbackListDTO obj : records) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;

            createCell(row, columnCount++, obj.getRegion(), style);
            createCell(row, columnCount++, obj.getDeprov(), style);
            createCell(row, columnCount++, obj.getSemestre(), style);
            createCell(row, columnCount++, obj.getJefeTecnico(), style);
            createCell(row, columnCount++, obj.getSupervisor(), style);
            createCell(row, columnCount++, obj.getFrecuencia(), style);
            createCell(row, columnCount++, obj.getFechaPlanificada(), style);
            createCell(row, columnCount++, obj.getFechaRealizada(), style);
            createCell(row, columnCount++, obj.getHoraRealizada(), style);
            createCell(row, columnCount++, obj.getTipoRetroalimentacion(), style);
            createCell(row, columnCount++, obj.getFechaAcompaniamiento(), style);
            createCell(row, columnCount++, obj.getFocoAbordado(), style);
            createCell(row, columnCount++, obj.getAspectosReforzar(), style);
            createCell(row, columnCount++, obj.getAcciones(), style);
            createCell(row, columnCount++, obj.getResponsable(), style);
            createCell(row, columnCount++, obj.getPlazos(), style);
            createCell(row, columnCount++, obj.getComentarios(), style);
            createCell(row, columnCount++, obj.getFechaProxima(), style);            
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
