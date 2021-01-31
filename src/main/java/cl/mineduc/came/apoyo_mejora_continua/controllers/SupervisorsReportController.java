package cl.mineduc.came.apoyo_mejora_continua.controllers;

import java.util.List;

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

import cl.mineduc.came.apoyo_mejora_continua.dto.report.advisory.AdvisoryRequestDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.report.advisory.AdvisoryResultDirectaDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.report.supervisors.SupervisorsRequestDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.report.supervisors.SupervisorsResultDTO;
import cl.mineduc.came.apoyo_mejora_continua.services.AdvisoryReportService;
import cl.mineduc.came.apoyo_mejora_continua.services.SupervisorsReportService;

@Controller      
@RequestMapping("supervisorsreport")
public class SupervisorsReportController {

	private static Logger logger = LogManager.getLogger(SupervisorsReportController.class);
	
	@Autowired
	SupervisorsReportService supervisorsReportService;
	
	@GetMapping(value = "/getSupervisores", produces = "application/json")
    public ResponseEntity<Object> getSupervisores(SupervisorsRequestDTO request) {
        List<SupervisorsResultDTO> asesoriaDirecta = supervisorsReportService.getSupervisores(request);
        return new ResponseEntity<Object>(asesoriaDirecta, HttpStatus.OK);
    }
	
	@GetMapping(value = "index")
	public ModelAndView index(@RequestParam(required = false) Integer error){
		logger.debug("supervisors report  view request... ");
		ModelAndView mv = new ModelAndView();
	
		mv.addObject("error",error);
		return mv;
	}
}
