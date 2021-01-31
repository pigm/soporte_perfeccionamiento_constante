package cl.mineduc.came.apoyo_mejora_continua.controllers;
import org.springframework.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cl.mineduc.came.apoyo_mejora_continua.dto.asignacion_maxima.AsignacionMaximaDTO;
import cl.mineduc.came.apoyo_mejora_continua.services.AsignacionMaximaService;

@Controller
@RequestMapping("assign")
public class AssignController {
    private static Logger logger = LogManager.getLogger(AssignController.class);

	@Autowired
	private AsignacionMaximaService asignacionMaximaService;

	
	//#region	
	@PostMapping(value = "/setMaxAssign", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object> setMaxAssign(@RequestBody AsignacionMaximaDTO record, BindingResult result) {
		
		asignacionMaximaService.updateAsignacionMaxima(record);	
		
		return  new ResponseEntity<Object>(record, HttpStatus.OK);
	}
	//#endregion

    @GetMapping(value = "index")
	public ModelAndView index(@RequestParam(required = false) Integer error){
		logger.debug("max assing view request... ");
		ModelAndView mv = new ModelAndView();
		
		AsignacionMaximaDTO assing = asignacionMaximaService.get();

		mv.addObject("assing", assing);
		mv.addObject("error", error);
		return mv;
	}    
}
