package cl.mineduc.came.apoyo_mejora_continua.controllers;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cl.mineduc.came.apoyo_mejora_continua.Excel.DirectoryReportExcel;
import cl.mineduc.came.apoyo_mejora_continua.Excel.EstablecimientosExcel;
import cl.mineduc.came.apoyo_mejora_continua.dto.establishment.EstablecimientosDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.establishment.EstablecimientosRequestDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.report.directory.DirectoryListDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.report.directory.DirectoryRequestDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.report.directory.DirectoryRequestExcelDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.shared.SelectorDTO;
import cl.mineduc.came.apoyo_mejora_continua.services.DirectoryReportService;

@Controller
@RequestMapping("directoryreport")
public class DirectoryReportController {
    private static Logger logger = LogManager.getLogger(DirectoryReportController.class);

	@Autowired
	DirectoryReportService reportDirectoryService;
	
	@GetMapping(value = "/getProvincias", produces = "application/json")
	public ResponseEntity<Object> getProvincias(String idRegion) {

		List<SelectorDTO> provincias = reportDirectoryService.getDeprovByRegion(Integer.parseInt(idRegion));
		return new ResponseEntity<Object>(provincias, HttpStatus.OK);
	}
	
	@GetMapping(value = "/getComunas", produces = "application/json")
    public ResponseEntity<Object> getComunas(String idDeprov) {
        List<SelectorDTO> comunas = reportDirectoryService.getComunasByDeprov(Integer.parseInt(idDeprov));
        return new ResponseEntity<Object>(comunas, HttpStatus.OK);
    }
	
	@GetMapping(value = "/getDirectoryReport", produces = "application/json")
    public ResponseEntity<Object> getDirectoryReport(DirectoryRequestDTO request) {
        List<DirectoryListDTO> comunas = reportDirectoryService.getDirectoryReport(request);
        return new ResponseEntity<Object>(comunas, HttpStatus.OK);
    }
	
	@GetMapping(value = "/export/excel")
    public void exportToExcel(HttpServletResponse response, DirectoryRequestDTO filter) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
         
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Reporte_Directorio__" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<DirectoryListDTO> records = reportDirectoryService.getDirectoryReport(filter);
        DirectoryReportExcel excelExporter = new DirectoryReportExcel(records);
        excelExporter.export(response);    
    }  

	@GetMapping(value = "index")
	public ModelAndView index(@RequestParam(required = false) Integer error){
		logger.debug("report directory view request... ");
		ModelAndView mv = new ModelAndView();
		List<SelectorDTO> regiones = reportDirectoryService.getRegiones();

		mv.addObject("regiones", regiones);
		mv.addObject("error",error);
		return mv;
	}
}