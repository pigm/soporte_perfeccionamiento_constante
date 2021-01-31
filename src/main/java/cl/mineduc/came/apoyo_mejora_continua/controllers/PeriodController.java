package cl.mineduc.came.apoyo_mejora_continua.controllers;

import java.util.ArrayList;
import java.util.Calendar;
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

import cl.mineduc.came.apoyo_mejora_continua.dto.period.DeprovDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.period.PeriodoDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.period.PeriodoDocumentosRegionalesDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.period.SpecialCaseDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.shared.SelectorDTO;
import cl.mineduc.came.apoyo_mejora_continua.services.PeriodService;
import cl.mineduc.came.apoyo_mejora_continua.services.UsuarioService;

@Controller
@RequestMapping("period")
public class PeriodController {
    private static Logger logger = LogManager.getLogger(PeriodController.class);
    
    @Autowired
	private PeriodService periodService;
	
	@Autowired
	private UsuarioService usuarioService;

	//#region REST

	/**
	 *
	 * @return profile
	 */
	@GetMapping(value = "/existPeriod", produces = "application/json")
	public ResponseEntity<Object> existPeriod(int period) {
		
		Boolean exist = periodService.existsPeriod(period);
		
		return new ResponseEntity<Object>(exist, HttpStatus.OK);
	}

	@GetMapping(value = "/getPeriod", produces = "application/json")
	public ResponseEntity<Object> getPeriod(int period) {
		
		PeriodoDTO record = periodService.getPeriodByYear(period);
		
		// List<PeriodoDocumentosRegionalesDTO> espPaso = new ArrayList<PeriodoDocumentosRegionalesDTO>();
		// PeriodoDocumentosRegionalesDTO obj = new PeriodoDocumentosRegionalesDTO();
		// obj.setCasoEspecial(true);
		// obj.setFechaFin(new Date());
		// obj.setFechaInicio(new Date());
		// obj.setFechaRegistro(new Date());
		// obj.setRegionalDocument("Region");
		
		// espPaso.add(obj);
		// record.setDocumentosRegionalesEspeciales(espPaso);

		return new ResponseEntity<Object>(record, HttpStatus.OK);
	}

	@PostMapping(value = "/setPeriod", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object> setProfile(@Valid @RequestBody PeriodoDTO record, BindingResult result) {		
		if(result.hasErrors()){			
			return  new ResponseEntity<Object>(result.getAllErrors(), HttpStatus.BAD_REQUEST);	
		}
		
		periodService.setPeriod(record);
		
		return  new ResponseEntity<Object>(true, HttpStatus.OK);
	}

	@PostMapping(value = "/setSpecialCase", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object> setSpecialCase(@Valid @RequestBody SpecialCaseDTO record, BindingResult result) {		
		if(result.hasErrors()){			
			return  new ResponseEntity<Object>(result.getAllErrors(), HttpStatus.BAD_REQUEST);	
		}
		
		 periodService.setSpecialCase(record);
		
		return  new ResponseEntity<Object>(record, HttpStatus.OK);
	}

	@GetMapping(value = "/getProvincias", produces = "application/json")
	public ResponseEntity<Object> getProvincias(Integer idRegion, Integer period, Integer moduleId) {
		List<DeprovDTO> provincias = periodService.getDeprovSpecialCases(period, moduleId, idRegion);
		return new ResponseEntity<Object>(provincias, HttpStatus.OK);
	}
	//#endregion


    @GetMapping(value = "index")
	public ModelAndView index(@RequestParam(required = false) Integer error){
		logger.debug("period view request... ");
		ModelAndView mv = new ModelAndView();
		String[] years = { "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027" };
		List<SelectorDTO> regiones = usuarioService.getRegiones();
		
		Integer year = Calendar.getInstance().get(Calendar.YEAR);

		mv.addObject("regiones", regiones);
		mv.addObject("currentYear", year);
		mv.addObject("years", years);
		mv.addObject("error",error);
		return mv;
	}
}
