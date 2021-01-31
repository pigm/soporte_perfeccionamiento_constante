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

import cl.mineduc.came.apoyo_mejora_continua.Excel.AdvisoryNetworksReportExcel;
import cl.mineduc.came.apoyo_mejora_continua.Excel.AdvisoryReportExcel;
import cl.mineduc.came.apoyo_mejora_continua.dto.report.advisory.AdvisoryRequestDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.report.advisory.AdvisoryResultDirectaDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.report.advisory.AdvisoryResultRedDTO;
import cl.mineduc.came.apoyo_mejora_continua.services.AdvisoryNetworksReportService;

@Controller
@RequestMapping("advisorynetworksreport")
public class AdvisoryNetworksReportController {
	private static Logger logger = LogManager.getLogger(AdvisoryNetworksReportController.class);
	
	@Autowired
	AdvisoryNetworksReportService reportAdvisoryNetworksService;
	
	@GetMapping(value = "/getRed", produces = "application/json")
    public ResponseEntity<Object> getRed(AdvisoryRequestDTO request) {
        List<AdvisoryResultRedDTO> asesoriaRed = reportAdvisoryNetworksService.getRed(request);
        return new ResponseEntity<Object>(asesoriaRed, HttpStatus.OK);
    }
	
	@GetMapping(value = "/export/excel")
    public void exportToExcel(HttpServletResponse response, AdvisoryRequestDTO filter) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
         
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Reporte_Directorio__" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<AdvisoryResultRedDTO> records = reportAdvisoryNetworksService.getRed(filter);
        AdvisoryNetworksReportExcel excelExporter = new AdvisoryNetworksReportExcel(records);
        excelExporter.export(response);    
    } 
	
	@GetMapping(value = "index")
	public ModelAndView index(@RequestParam(required = false) Integer error){
		logger.debug("report advisory networks view request... ");
		ModelAndView mv = new ModelAndView();
	
		mv.addObject("error",error);
		return mv;
	}
}
