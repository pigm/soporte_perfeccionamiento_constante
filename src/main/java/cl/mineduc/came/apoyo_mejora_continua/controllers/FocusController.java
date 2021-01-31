package cl.mineduc.came.apoyo_mejora_continua.controllers;

import java.util.Calendar;
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

import cl.mineduc.came.apoyo_mejora_continua.dto.foco.AccionesMejorasDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.foco.FocoDTO;
import cl.mineduc.came.apoyo_mejora_continua.services.FocoService;

@Controller
@RequestMapping("focus")
public class FocusController {
    private static Logger logger = LogManager.getLogger(FocusController.class);

	@Autowired
	private FocoService focoService;

	@GetMapping(value = "/getFocusById", produces = "application/json")
	public ResponseEntity<Object> getFocusById(String focoId) {		
		FocoDTO record = focoService.getFocusById(Long.parseLong(focoId));
		return new ResponseEntity<Object>(record, HttpStatus.OK);
	}

	@PostMapping(value = "/setFocus", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object> setFocus(@Valid @RequestBody FocoDTO record, BindingResult result) {		
		if(result.hasErrors()){			
			return  new ResponseEntity<Object>(result.getAllErrors(), HttpStatus.BAD_REQUEST);	
		}		
		focoService.setFocus(record);		
		return  new ResponseEntity<Object>(record, HttpStatus.OK);
	}

	@PostMapping(value = "/removeAccion", produces = "application/json")
	public ResponseEntity<Object> removeAccion(@RequestParam(name = "activityId") String activityId) {					
		focoService.removeAction(activityId);		
		return  new ResponseEntity<Object>(true, HttpStatus.OK);
	}

	@PostMapping(value = "/removeFoco", produces = "application/json")
	public ResponseEntity<Object> removeFoco(@RequestParam(name = "focoId") String focoId) {					
		focoService.removeFoco(focoId);		
		return  new ResponseEntity<Object>(true, HttpStatus.OK);
	}

    @GetMapping(value = "index")
	public ModelAndView index(@RequestParam(required = false) Integer year,
							  @RequestParam(required = false) Integer error){
		logger.debug("max assing view request... ");
		ModelAndView mv = new ModelAndView();
		String[] years = { "2020", "2021", "2022", "2023", "2024", "2025", "2026" };
								
		year = year != null ? year : Calendar.getInstance().get(Calendar.YEAR);
		List<FocoDTO> focos = focoService.getFocus(year);
		
		mv.addObject("currentYear", year);
		mv.addObject("years", years);
		mv.addObject("focos",focos);
		mv.addObject("error",error);
		return mv;
	}   
}