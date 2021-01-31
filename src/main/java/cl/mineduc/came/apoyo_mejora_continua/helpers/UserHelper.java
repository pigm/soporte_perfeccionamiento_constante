package cl.mineduc.came.apoyo_mejora_continua.helpers;

import org.springframework.security.core.context.SecurityContextHolder;

import cl.mineduc.came.apoyo_mejora_continua.modelo.UsuarioRegistrado;

public class UserHelper {
	
	public static UsuarioRegistrado getUsuarioRegistrado() {
		UsuarioRegistrado persona = null;
		if(!(SecurityContextHolder.getContext().getAuthentication().getDetails()==null)){
			persona = (UsuarioRegistrado) SecurityContextHolder.getContext().getAuthentication().getDetails();
		}else{
			if(!(SecurityContextHolder.getContext().getAuthentication().getPrincipal()==null)){
				persona = (UsuarioRegistrado) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			}
		}
		return persona;
	}

}
