package cl.mineduc.came.apoyo_mejora_continua.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cl.mineduc.came.apoyo_mejora_continua.dto.profile.MenuDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.users.UserEnvDTO;
import cl.mineduc.came.apoyo_mejora_continua.helpers.UserHelper;
import cl.mineduc.came.apoyo_mejora_continua.modelo.auth.Usuario;
import cl.mineduc.came.apoyo_mejora_continua.services.ProfileService;
import cl.mineduc.came.apoyo_mejora_continua.services.UsuarioService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/")
public class MainController {
	private static Logger logger = LogManager.getLogger(MainController.class);

	@Autowired
	private ProfileService profileService;

	@Autowired
	private UsuarioService usuarioService; 

	//#region
	@GetMapping(value = "home/getMenu", produces = "application/json")
	public ResponseEntity<Object> getMenu() {
		logger.debug("main controller getMenu... " + UserHelper.getUsuarioRegistrado().getIdUsuario().toString());
		List<MenuDTO> menu = profileService.getMenuAccesoUsuario(UserHelper.getUsuarioRegistrado().getIdUsuario().toString());
		return new ResponseEntity<Object>(menu, HttpStatus.OK);
	}

	@GetMapping(value = "home/getUserEnv", produces = "application/json")
	public ResponseEntity<Object> getUserEnv() {
		logger.debug("main controller getUserEnv... " + UserHelper.getUsuarioRegistrado().getIdUsuario());
		UserEnvDTO  usuarioEnv = usuarioService.getUserEnv(UserHelper.getUsuarioRegistrado().getIdUsuario());
		return new ResponseEntity<Object>(usuarioEnv, HttpStatus.OK);
	}
	//#endregion


	//@RequestMapping(value = "index", method = {RequestMethod.GET,RequestMethod.POST})
	@GetMapping(value = "index")
	public ModelAndView index(@RequestParam(required = false) Integer error){
		ModelAndView mv = new ModelAndView();
		return mv;
	}
	
	// @RequestMapping(value = "home/index", method = {RequestMethod.GET,RequestMethod.POST})
	@GetMapping(value = "home/index")
	public ModelAndView home(@RequestParam(required = false) Integer error){
		ModelAndView mv = new ModelAndView();

		// if(this.getCurrentUser() != null){
		// 	String login = getCurrentUser().getLogin();
		// 	mv.addObject("userId", login);
		// }

		mv.addObject("error", error);
		return mv;
	}	
}