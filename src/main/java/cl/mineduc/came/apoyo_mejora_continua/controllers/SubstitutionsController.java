package cl.mineduc.came.apoyo_mejora_continua.controllers;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cl.mineduc.came.apoyo_mejora_continua.dto.supervisor_suplencia.SupervisorDataDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.supervisor_suplencia.SupervisorSuplenciaDTO;
import cl.mineduc.came.apoyo_mejora_continua.helpers.StringHelper;
import cl.mineduc.came.apoyo_mejora_continua.services.SupervisorSuplenciaService;

@Controller
@RequestMapping("substitutions")
public class SubstitutionsController {
    private static Logger logger = LogManager.getLogger(SubstitutionsController.class);

	@Autowired
	private SupervisorSuplenciaService supervisorSuplenciaService;

	//#region REST

	/**
	 *
	 * @return 
	 */
	@GetMapping(value = "/getUserNamesSuper", produces = "application/json")
	public ResponseEntity<Object> getUserNamesSuper() {		
		List<String> usernames = supervisorSuplenciaService.getSupUserNames();		
		return new ResponseEntity<Object>(usernames, HttpStatus.OK);
	}

	/**
	 *
	 * @return 
	 */
	@GetMapping(value = "/getSuperByUserName", produces = "application/json")
	public ResponseEntity<Object> getSuperByUserName(String userName) {		
		SupervisorDataDTO supData = supervisorSuplenciaService.getSupervisorData(userName);	
		return new ResponseEntity<Object>(supData, HttpStatus.OK);
	}

	@GetMapping(value = "/isValidRange", produces = "application/json")
	public ResponseEntity<Object> isValidRange(String idSupervisorAusente, String startDate, String endDate) {
		Boolean isValid = supervisorSuplenciaService.isValidRange(idSupervisorAusente, StringHelper.convertToDate(startDate), StringHelper.convertToDate(endDate));
		return new ResponseEntity<Object>(isValid, HttpStatus.OK);
	}

	@PostMapping(value = "/setSupervisorSuplencia", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object> setProfile(@Valid @RequestBody SupervisorSuplenciaDTO record, BindingResult result) {
		
		if(result.hasErrors()){			
			return  new ResponseEntity<Object>(result.getAllErrors(), HttpStatus.BAD_REQUEST);	
		}

		if(record.getStartDate().after(record.getEndDate())){
			return  new ResponseEntity<Object>("la fecha de inicio no puede ser mayor a la fecha de termino.", HttpStatus.BAD_REQUEST);	
		}

		if(record.getIdUsuarioSupervisorAusente().equals(record.getIdUsuarioSupervisorSuplente())){
			return  new ResponseEntity<Object>("El supervidor suplente no puede ser igual al supervisor ausente.", HttpStatus.BAD_REQUEST);	
		}

		supervisorSuplenciaService.insertSupervisorSuplencia(record);
		
		return  new ResponseEntity<Object>(record, HttpStatus.OK);
	}
	//#endregion

    @GetMapping(value = "index")
	public ModelAndView index(@RequestParam(required = false) Integer error){
		logger.debug("max assing view request... ");
		ModelAndView mv = new ModelAndView();
		
		List<SupervisorSuplenciaDTO> records = supervisorSuplenciaService.get();

		mv.addObject("suplencias",records);
		mv.addObject("error",error);
		return mv;
	}   
}
