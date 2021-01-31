package cl.mineduc.came.apoyo_mejora_continua.controllers;

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

import cl.mineduc.came.apoyo_mejora_continua.dto.profile.ModuloDataDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.profile.ProfileDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.profile.ProfileDataDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.profile.ProfileStatusDTO;
import cl.mineduc.came.apoyo_mejora_continua.services.ProfileService;

@Controller
@RequestMapping("profile")
public class ProfileController {
	private static Logger logger = LogManager.getLogger(ProfileController.class);

	@Autowired
	private ProfileService profileService;

	// #region Metodos REST
	/**
	 *
	 * @return profiles
	 */
	@GetMapping(value = "/getProfiles", produces = "application/json")
	public ResponseEntity<Object> getProfiles() {

		List<ProfileDataDTO> profiles = profileService.obtenerPerfiles();
		return new ResponseEntity<Object>(profiles, HttpStatus.OK);
	}

	/**
	 *
	 * @return profile
	 */
	@GetMapping(value = "/getProfile", produces = "application/json")
	public ResponseEntity<Object> getProfile(String idProfile) {
		long id = Long.parseLong(idProfile);
		ProfileDTO profile = profileService.getProfile(id);
		return new ResponseEntity<Object>(profile, HttpStatus.OK);
	}

	/**
	 *
	 * @return modulos
	 */
	@GetMapping(value = "/getModulos", produces = "application/json")
	public ResponseEntity<Object> getModulos() {

		List<ModuloDataDTO> modulos = profileService.obtenerModulos();
		return new ResponseEntity<Object>(modulos, HttpStatus.OK);
	}

	@PostMapping(value = "/setProfile", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object> setProfile(@Valid @RequestBody ProfileDTO profile, BindingResult result) {
		/* 	TODO: CGG.
			Aqui debo crear la logica de validacion de los campos y responder segun segun resultado
			Si todo sale bien, le envio el objeto al servicio para que sea registrado. */
		if(result.hasErrors()){			
			return  new ResponseEntity<Object>(result.getAllErrors(), HttpStatus.BAD_REQUEST);	
		}

		profileService.insertPerfil(profile);
		
		return  new ResponseEntity<Object>(profile, HttpStatus.OK);
	}

	@PostMapping(value = "/setProfileStatus", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object> setProfileStatus(@RequestBody ProfileStatusDTO profileStatus, BindingResult result) {
		
		profileService.setProfileStatus(Long.parseLong(profileStatus.getIdProfile()), profileStatus.isStatus());
		
		return  new ResponseEntity<Object>(profileStatus, HttpStatus.OK);
	}

	//#endregion

	

	@GetMapping(value = "profile")
	public ModelAndView profile(@RequestParam(required = false) Integer error){
		logger.debug("profile view request... ");
		ModelAndView mv = new ModelAndView();

		mv.addObject("modulos", profileService.obtenerModulos());
		mv.addObject("error",error);
		return mv;
	}

}
