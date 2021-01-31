package cl.mineduc.came.apoyo_mejora_continua.authentication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cl.mineduc.came.apoyo_mejora_continua.helpers.LdapHelper;
import cl.mineduc.came.apoyo_mejora_continua.modelo.UsuarioRegistrado;
import cl.mineduc.came.apoyo_mejora_continua.services.UsuarioService;
import cl.mineduc.framework2.security.spring.DBUserService;

public class DBUserServices implements DBUserService {
	private static final Logger log = LoggerFactory.getLogger(DBUserServices.class);
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private LdapHelper ldap;

	@Override
	public String[] getAuthorities(String userName) {
		String[] roles = {"ADMIN"};
		return roles;
	}

	@Override
	public Object getUserDetails(String userName) {
		log.debug("Consultando por username: " + userName);
		UsuarioRegistrado user = usuarioService.buscarUsuarioRegistrado(userName.toLowerCase());
		return user;
	}

	@Override
	public boolean loginUser(String userName, String password) {
		String psw = password.isEmpty() ? "a" : password; 
		if (!ldap.validaUsuarioLDAP(userName, psw)) {
			return false;
		}
		UsuarioRegistrado user = usuarioService.buscarUsuarioRegistrado(userName.toLowerCase());

		if (user == null) {
			return false;
		}
		return true;

		
	}

}
