package cl.mineduc.came.apoyo_mejora_continua;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cl.mineduc.came.apoyo_mejora_continua.dto.profile.ProfileAccesoDTO;
import cl.mineduc.came.apoyo_mejora_continua.modelo.Modulo;
import cl.mineduc.came.apoyo_mejora_continua.modelo.Perfil;
import cl.mineduc.came.apoyo_mejora_continua.modelo.PerfilMenu;
import cl.mineduc.came.apoyo_mejora_continua.modelo.PerfilNivel;
import cl.mineduc.came.apoyo_mejora_continua.modelo.SubModulo;
import cl.mineduc.came.apoyo_mejora_continua.modelo.UsuarioRegistrado;
import cl.mineduc.came.apoyo_mejora_continua.modelo.auth.Usuario;


public class UtilsProfile {
	
	public static final long DEFAULT_LONG = 1L;
	public static final long DEFAULT_LONG_NEW_ELEMENT = 0L;
	
	public static Perfil getPerfilMock() {
		Perfil perfil = new Perfil();
		perfil.setNombre("a");
		perfil.setDescripcion("descripcion");
		perfil.setHabilitado(true);
		perfil.setIdEstado(DEFAULT_LONG);
		perfil.setIdPerfil(DEFAULT_LONG_NEW_ELEMENT);
		perfil.setIdPerfilNivel(DEFAULT_LONG_NEW_ELEMENT);
		List<PerfilMenu> perfilMenu = new ArrayList<PerfilMenu>();
		perfilMenu.add(getPerfilMenuMock());
		perfil.setPerfilMenu(perfilMenu);
		perfil.setPerfilNivel(getPerfilNivelMock());
		perfil.setUltimaModificacion(new Date());
		perfil.setIdUsuarioModificacion(DEFAULT_LONG_NEW_ELEMENT);
		return perfil;
	}

	public static PerfilNivel getPerfilNivelMock() {
		PerfilNivel pn = new PerfilNivel();
		pn.setIdPerfilNivel(DEFAULT_LONG);
		pn.setNombre("Perfil Nivel");
		return pn;
	}

	public static PerfilMenu getPerfilMenuMock() {
		PerfilMenu pm = new PerfilMenu();
		pm.setEndDate(new Date());
		pm.setIdPerfil(DEFAULT_LONG);
		pm.setIdPerfilMenu(DEFAULT_LONG_NEW_ELEMENT);
		pm.setIdPerfilMenuAcceso(DEFAULT_LONG);
		pm.setIdSubModulo(DEFAULT_LONG_NEW_ELEMENT);
		pm.setStartDate(new Date());
		pm.setStatus(true);
		return pm;
	}
	
	public static Modulo getModuloMock() {
		Modulo m = new Modulo();
		m.setIdEstado(DEFAULT_LONG);
		m.setIdModulo(DEFAULT_LONG_NEW_ELEMENT);
		m.setNombre("Nombre");
		m.setStatus(true);
		List<SubModulo> subModulo = new ArrayList<SubModulo>(); 
		m.setSubModulo(subModulo);
		return m;
	}
	
	public static UsuarioRegistrado getCurrentUser() {
		final UsuarioRegistrado p = new UsuarioRegistrado();
		p.setRut("1-9");
		p.setNombre("Marty");
		p.setEmail("user@domain.cl");
		p.setIdUsuario(DEFAULT_LONG);
		return p;
	}

	public static Usuario getUsuarioMock() {
		final Usuario u = new Usuario();
		return u;
	}
	
	public static ProfileAccesoDTO accesoMock() {
		ProfileAccesoDTO a = new ProfileAccesoDTO();
		a.setId("1");
		a.setReferred("2");
		return a;
	}

}
