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

import cl.mineduc.came.apoyo_mejora_continua.dto.shared.SelectorDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.users.ProfileSelectorDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.users.UserDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.users.UserStatusDTO;
import cl.mineduc.came.apoyo_mejora_continua.services.UsuarioService;

@Controller
@RequestMapping("users")
public class UsersController {
	private static Logger logger = LogManager.getLogger(UsersController.class);

	@Autowired
	private UsuarioService usuarioService;

	// #region REST
	/**
	 *
	 * @return userldap
	 */
	@GetMapping(value = "/findUserLdap", produces = "application/json")
	public ResponseEntity<Object> findUserLdap(String userName) {
		UserDTO userLdap = usuarioService.buscaUsuarioMineduc(userName);
		return new ResponseEntity<Object>(userLdap, HttpStatus.OK);
	}

	@GetMapping(value = "/getProvincias", produces = "application/json")
	public ResponseEntity<Object> getProvincias(String idRegion) {

		List<SelectorDTO> provincias = usuarioService.getDeprovByRegion(Integer.parseInt(idRegion));
		return new ResponseEntity<Object>(provincias, HttpStatus.OK);
	}

	@PostMapping(value = "/setUser", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object> setProfile(@Valid @RequestBody UserDTO user, BindingResult result) {

		if (result.hasErrors()) {
			return new ResponseEntity<Object>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
		}

		usuarioService.insertUsuario(user);
		
		return  new ResponseEntity<Object>(user, HttpStatus.OK);
	}

	@GetMapping(value = "/getUsers", produces = "application/json")
	public ResponseEntity<Object> getUsers() {

		return new ResponseEntity<Object>(usuarioService.getAllUsers(), HttpStatus.OK);
	}

	@GetMapping(value = "/getUsuario", produces = "application/json")
	public ResponseEntity<Object> getUsuario(String idUsuario) {

		UserDTO usuarioPaso = usuarioService.getUsuarioById(idUsuario);
		return new ResponseEntity<Object>(usuarioPaso, HttpStatus.OK);
	}

	@PostMapping(value = "/setUserStatus", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object> setUserStatus(@RequestBody UserStatusDTO userStatus, BindingResult result) {
		
		usuarioService.setUserStatus(Long.parseLong(userStatus.getIdUser()), userStatus.isStatus());
		
		return  new ResponseEntity<Object>(userStatus, HttpStatus.OK);
	}
	// #endregion

	@GetMapping(value = "index")
	public ModelAndView index(@RequestParam(required = false) Integer error) {
		logger.debug("users view request... ");
		ModelAndView mv = new ModelAndView();
		List<ProfileSelectorDTO> profiles = usuarioService.getProfiles();
		
		List<SelectorDTO> regiones = usuarioService.getRegiones();


		mv.addObject("profiles", profiles);
		mv.addObject("regiones", regiones);
		mv.addObject("error",error);
		return mv;
	}
}
