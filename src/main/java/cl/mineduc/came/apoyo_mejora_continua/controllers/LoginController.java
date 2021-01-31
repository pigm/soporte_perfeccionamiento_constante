package cl.mineduc.came.apoyo_mejora_continua.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cl.mineduc.came.apoyo_mejora_continua.modelo.UsuarioRegistrado;
import cl.mineduc.came.apoyo_mejora_continua.services.RecaptchaService;
import cl.mineduc.came.apoyo_mejora_continua.services.UsuarioService;

@Controller
@RequestMapping("login")
public class LoginController {
	private static Logger logger = LogManager.getLogger(LoginController.class);

	@Autowired
	private Environment env;

	@Autowired
	@Qualifier("recaptchaService")
	private RecaptchaService recaptchaService;

	@Autowired
	private UsuarioService userService;

	// #region REST
	@GetMapping(value = "/getStatus", produces = "application/json")
	public ResponseEntity<Object> getStatus(String username) {
		Boolean result = false;
		UsuarioRegistrado user = userService.buscarUsuarioRegistrado(username);
		if(user != null){
			result = user.isHabilitado();	
		}
		return new ResponseEntity<Object>(result, HttpStatus.OK);
	}
	// #end Region

	@RequestMapping(value = "login", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView login(@RequestParam(required = false) Integer error) {
		logger.debug("login view request... ");
		ModelAndView mv = new ModelAndView();
		mv.addObject("recaptchaSiteKey", env.getProperty("cl.mineduc.came.apoyo_mejora_continua.recaptcha.site.key"));
		mv.addObject("error", error);
		return mv;
	}

	@RequestMapping(value = "navegador", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView navegador() {
		ModelAndView mv = new ModelAndView();
		return mv;
	}

	@PostMapping(value = "validarCaptcha")
	public ResponseEntity<?> validarCaptcha(@RequestParam(defaultValue = "") String captcha, HttpServletResponse resp,
			HttpServletRequest request) throws IOException {

		logger.debug("Ingreso a validar en Login, valida Captcha :");
		String recaptchaResponse = captcha;
		String ipAddress = request.getRemoteAddr();
		boolean validCaptcha = recaptchaService.recaptchaValidation(recaptchaResponse, ipAddress);

		if (!validCaptcha) {			
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(null);
		}
	}

}