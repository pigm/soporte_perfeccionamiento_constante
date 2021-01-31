package cl.mineduc.came.apoyo_mejora_continua.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.mineduc.came.apoyo_mejora_continua.dto.profile.MenuDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.profile.ModuloDataDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.profile.ProfileAccesoDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.profile.ProfileDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.profile.ProfileDataDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.profile.SubMenuDTO;
import cl.mineduc.came.apoyo_mejora_continua.enums.EstadoEnum;
import cl.mineduc.came.apoyo_mejora_continua.helpers.UserHelper;
import cl.mineduc.came.apoyo_mejora_continua.modelo.Modulo;
import cl.mineduc.came.apoyo_mejora_continua.modelo.Perfil;
import cl.mineduc.came.apoyo_mejora_continua.modelo.PerfilMenu;
import cl.mineduc.came.apoyo_mejora_continua.modelo.UsuarioPerfil;
import cl.mineduc.came.apoyo_mejora_continua.modelo.UsuarioRegistrado;
import cl.mineduc.came.apoyo_mejora_continua.repo.ModuloRepo;
import cl.mineduc.came.apoyo_mejora_continua.repo.PerfilMenuRepo;
import cl.mineduc.came.apoyo_mejora_continua.repo.PerfilRepo;
import cl.mineduc.came.apoyo_mejora_continua.repo.UsuarioRepo;

import org.apache.log4j.Logger;

@Service
public class ProfileService {
	private static Logger logger = Logger.getLogger(ProfileService.class);

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private PerfilRepo perfilRepo;

	@Autowired
	private ModuloRepo moduloRepo;

	@Autowired
	private PerfilMenuRepo perfilMenuRepo;

	@Autowired
	private UsuarioRepo usuarioRepo;

	// #region Private Methods
	private ProfileDataDTO convertToDto(Perfil perfil) {
		ProfileDataDTO profile = modelMapper.map(perfil, ProfileDataDTO.class);
		return profile;
	}

	private ModuloDataDTO convertToDto(Modulo modulo) {
		ModuloDataDTO module = modelMapper.map(modulo, ModuloDataDTO.class);
		return module;
	}
	// #endregion

	// #region Public Methods
	/**
	 * 
	 * @return
	 */
	public List<ProfileDataDTO> obtenerPerfiles() {
		List<Perfil> perfiles = perfilRepo.obtenerPerfiles();
		return perfiles.stream().map(this::convertToDto).collect(Collectors.toList());
	}

	public List<ModuloDataDTO> obtenerModulos() {
		List<Modulo> modulos = moduloRepo.obtenerModulos();
		return modulos.stream().map(this::convertToDto).collect(Collectors.toList());
	}

	public ProfileDTO getProfile(long idPerfil) {
		// Obtengo el perfil segun el id
		Perfil profilePaso = perfilRepo.obtenerPerfil(idPerfil);
		ProfileDTO perfilResult = new ProfileDTO();
		// seteo las propiedades del resultado
		perfilResult.setIdPerfil(profilePaso.getIdPerfil().toString());
		perfilResult.setNombre(profilePaso.getNombre());
		perfilResult.setDescripcion(profilePaso.getDescripcion());
		perfilResult.setIdNivel(profilePaso.getIdPerfilNivel().toString());
		perfilResult.setHabilitado(profilePaso.getHabilitado());
		// Recorro el menu y genero la lista de accesos
		List<ProfileAccesoDTO> accesosPaso = new ArrayList<ProfileAccesoDTO>();
		profilePaso.getPerfilMenu().forEach(pm -> {
			if (pm.getEndDate() == null) {
				ProfileAccesoDTO sel = new ProfileAccesoDTO();
				sel.setId(pm.getIdSubModulo().toString());
				sel.setReferred(pm.getIdPerfilMenuAcceso().toString());
				accesosPaso.add(sel);
			}
		});

		perfilResult.setAccesos(accesosPaso);

		return perfilResult;
	}

	public void setProfileStatus(long idPerfil, boolean status) {
		Perfil profilePaso = perfilRepo.obtenerPerfil(idPerfil);
		profilePaso.setHabilitado(status);
		// Actualizo el perfil actual
		perfilRepo.actualizarPerfil(profilePaso);
	}

	public void insertPerfil(ProfileDTO perfil) {

		Perfil profileRegistra = perfil.getIdPerfil().equals("") ? new Perfil()
				: perfilRepo.obtenerPerfil(Long.parseLong(perfil.getIdPerfil()));
		profileRegistra.setIdEstado(EstadoEnum.Habilitado.getValue());
		profileRegistra.setIdPerfilNivel(Long.parseLong(perfil.getIdNivel()));
		profileRegistra.setNombre(perfil.getNombre());
		profileRegistra.setDescripcion(perfil.getDescripcion());
		profileRegistra.setHabilitado(perfil.isHabilitado());
		profileRegistra.setUltimaModificacion(new Date());
		// necesito saber como se obtiene el usuario que esta ejecutando la accion.

		profileRegistra.setIdUsuarioModificacion(UserHelper.getUsuarioRegistrado().getIdUsuario());

		// Verifico si existe el perfil.
		if (perfil.getIdPerfil().equals("")) {
			// Registro un nuevo perfil
			profileRegistra.setHabilitado(true);
			perfilRepo.insertarPerfil(profileRegistra);
			perfil.getAccesos().forEach(a -> {
				PerfilMenu pm = new PerfilMenu();
				pm.setIdPerfil(profileRegistra.getIdPerfil());
				pm.setIdSubModulo(Long.parseLong(a.getId()));
				pm.setIdPerfilMenuAcceso(Long.parseLong(a.getReferred()));
				pm.setStartDate(new Date());
				pm.setStatus(true);

				// aqui aregar el insert del repo
				perfilMenuRepo.insertarPerfilMenu(pm);
			});
		} else {
			// Actualizo el perfil actual
			perfilRepo.actualizarPerfil(profileRegistra);
			// debo obtener los perfiles enu del perfil menu y hacer el cruce
			profileRegistra.getPerfilMenu().forEach(pm -> {
				boolean idExists = perfil.getAccesos().stream()
						.anyMatch(t -> Long.parseLong(t.getId()) == pm.getIdSubModulo().longValue()
								&& Long.parseLong(t.getReferred()) == pm.getIdPerfilMenuAcceso().longValue()
								&& pm.getEndDate() == null);
				if (!idExists) {
					// Como no existe en la lista enviada, debe ser marcado en la fecha de termino
					// la fecha actual y actualizar
					pm.setEndDate(new Date());
					// Actuliza el permiso.
					perfilMenuRepo.updatePerfilMenu(pm);
				}
			});

			perfil.getAccesos().forEach(pm -> {
				boolean idExists = profileRegistra.getPerfilMenu().stream()
						.anyMatch(t -> t.getIdSubModulo() == Long.parseLong(pm.getId())
								&& t.getIdPerfilMenuAcceso() == Long.parseLong(pm.getReferred())
								&& t.getEndDate() == null);

				if (!idExists) {
					// Como no existe en la lista enviada, debe ser marcado en la fecha de termino
					// la fecha actual y actualizar
					PerfilMenu pmn = new PerfilMenu();
					pmn.setIdPerfil(profileRegistra.getIdPerfil());
					pmn.setIdSubModulo(Long.parseLong(pm.getId()));
					pmn.setIdPerfilMenuAcceso(Long.parseLong(pm.getReferred()));
					pmn.setStartDate(new Date());
					pmn.setStatus(true);

					// aqui aregar el insert del repo
					perfilMenuRepo.insertarPerfilMenu(pmn);
				}
			});
		}

		// verifico cada uno de los permmisos

	}
	// #endregion

	public List<MenuDTO> getMenuAccesoUsuario(String userId) {
		// primero obtengo el perfirl del usuario
		UsuarioRegistrado usuarioPaso = this.usuarioRepo.findById(Long.parseLong(userId));
		// con el perfil
		UsuarioPerfil usuarioPerfil = usuarioPaso.getPerfilesUsuarios().stream().filter(p -> p.getEndDate() == null)
				.iterator().next();
		// obtengo los accesos del perfil
		Perfil perfilPaso = this.perfilRepo.obtenerPerfil(usuarioPerfil.getIdPerfil());
		// Obtengo los Modulos
		List<Modulo> modulos = moduloRepo.obtenerModulos();
		// recorro el menu
		List<MenuDTO> response = new ArrayList<MenuDTO>();
		// verifco los permisos
		modulos.forEach(m -> {
			MenuDTO menu = new MenuDTO();
			menu.setName(m.getNombre());
			menu.setIdModule(m.getIdModulo().toString());

			List<SubMenuDTO> subMenu = new ArrayList<SubMenuDTO>();

			m.getSubModulo().forEach(sm -> {
				SubMenuDTO subPaso = new SubMenuDTO(m.getIdModulo().toString(), sm.getIdSubModulo().toString(), "2415742140157002753", sm.getNombre(), sm.getUrl());
				perfilPaso.getPerfilMenu().forEach(lp -> {
					logger.trace(lp.getIdSubModulo());
					if (lp.getIdSubModulo().equals(sm.getIdSubModulo())) {
						subPaso.setIdMenuAcceso(lp.getIdPerfilMenuAcceso().toString());
					}
				});
				subMenu.add(subPaso);
			});

			menu.setAccess(false);
			subMenu.stream().forEach(t ->{
				if( !(t.getIdMenuAcceso().equals("2415742140157002753"))){
					menu.setAccess(true);
					t.setReadOnly(t.getIdMenuAcceso().equals("2415742140157002754"));
				}
			});

			//menu.setAccess(subMenu.stream().anyMatch(t -> t.getIdSubModule().equals(menu.getIdModule()) && !t.getIdMenuAcceso().equals("2415742140157002753l")));
			menu.setSubMenu(subMenu);

			response.add(menu);
		});

		return response;
	}
}
