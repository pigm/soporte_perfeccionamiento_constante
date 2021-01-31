package cl.mineduc.came.apoyo_mejora_continua.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.naming.NamingEnumeration;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchResult;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.mineduc.came.apoyo_mejora_continua.dto.integration.region.RegionResponseDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.shared.SelectorDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.users.ProfileSelectorDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.users.UserDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.users.UserDataDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.users.UserEnvDTO;
import cl.mineduc.came.apoyo_mejora_continua.helpers.ActiveDirectory;
import cl.mineduc.came.apoyo_mejora_continua.helpers.RutUtil;
import cl.mineduc.came.apoyo_mejora_continua.helpers.StringHelper;
import cl.mineduc.came.apoyo_mejora_continua.helpers.Util;
import cl.mineduc.came.apoyo_mejora_continua.integration.RegionIntegration;
import cl.mineduc.came.apoyo_mejora_continua.modelo.Perfil;
import cl.mineduc.came.apoyo_mejora_continua.modelo.Periodo;
import cl.mineduc.came.apoyo_mejora_continua.modelo.Supervisor;
import cl.mineduc.came.apoyo_mejora_continua.modelo.UsuarioPerfil;
import cl.mineduc.came.apoyo_mejora_continua.modelo.UsuarioRegistrado;
import cl.mineduc.came.apoyo_mejora_continua.repo.PerfilRepo;
import cl.mineduc.came.apoyo_mejora_continua.repo.PeriodoRepo;
import cl.mineduc.came.apoyo_mejora_continua.repo.SupervisorRepo;
import cl.mineduc.came.apoyo_mejora_continua.repo.UsuarioPerfilRepo;
import cl.mineduc.came.apoyo_mejora_continua.repo.UsuarioRepo;
import cl.mineduc.framework2.exceptions.MineducException;

@Service
public class UsuarioService {

	private static Logger logger = Logger.getLogger(UsuarioService.class);

	private static String authMail = "certificado_digital";
	private static String authCredential = "Qkq9i4XD";
	private static String domainController = "minduccentral.mineduc.cl:389";
	private static String domain = "mineduca";

	private static final String PERFIL_SUPERVISOR = "2416264829774857258";

	@Autowired
	private UsuarioRepo usuarioRepo;

	@Autowired
	private PerfilRepo perfilRepo;

	@Autowired
	private UsuarioPerfilRepo upRepo;

	@Autowired
	private SupervisorRepo supervisorRepo;

	@Autowired
	RegionIntegration regionIntegration;

	@Autowired
	private PeriodoRepo periodRepo;

	// #region Private Methods
	private UsuarioRegistrado convertFromDto(UserDTO user) {
		UsuarioRegistrado ur = new UsuarioRegistrado();
		ur.setIdUsuario(StringHelper.convertToNumber(user.getIdUsuario()));
		ur.setIdRegion(StringHelper.convertToNumber(user.getIdRegion()));
		ur.setIdDeprov(StringHelper.convertToNumber(user.getIdDeprov()));
		ur.setUsuarioDominio(user.getUserName());
		ur.setHabilitado(user.isHabilitado());
		ur.setEmail(user.getEmail());
		ur.setNombre(user.getNombre());
		ur.setMaterno(user.getApellidoMaterno());
		ur.setPaterno(user.getApellidoPaterno());
		ur.setRut(Integer.toString(RutUtil.splitRutFromDv(user.getRut())));
		ur.setDv(Character.toString(RutUtil.splitDVFromRut(user.getRut())));
		return ur;
	}

	private void insertSupervisor(UserDTO user) {
		if (user.getIdPerfil().equals(PERFIL_SUPERVISOR)) {
			Supervisor supervisor = new Supervisor();
			supervisor.setIdUsuario(Long.parseLong(user.getIdUsuario()));
			supervisor.setIdDeprov(Long.parseLong(user.getIdDeprov()));
			supervisor.setStartDate(new Date());
			supervisorRepo.insert(supervisor);
		}
	}

	private void insertUsuarioPerfil(UserDTO user) {
		UsuarioPerfil up = new UsuarioPerfil();
		up.setIdPerfil(Long.parseLong(user.getIdPerfil()));
		up.setIdUsuario(Long.parseLong(user.getIdUsuario()));
		up.setStartDate(new Date());
		upRepo.insertProfileMenu(up);
	}

	private SelectorDTO getDeprov(int region, int deprov) {
		return getDeprovByRegion(region).stream().filter(d -> d.getValue().equals(String.valueOf(deprov))).findFirst().get();
	}

	private UsuarioPerfil getLastProfile(UsuarioRegistrado usuarioPaso) {
		UsuarioPerfil pu = usuarioPaso.getPerfilesUsuarios().stream().filter(p -> p.getEndDate() == null).iterator()
				.next();
		return pu;
	}

	private void endSupervisor(long idUsuario) {
		Optional<Supervisor> supervisor = supervisorRepo.getAll().stream().filter(s -> s.getIdUsuario().equals(idUsuario) && s.getEndDate() == null).findFirst();
		if(supervisor.isPresent()){
			Supervisor supPaso = supervisor.get();
			supPaso.setEndDate(new Date());
			supervisorRepo.updateSupervisor(supPaso);
		}		
	}

	private void updateUsuarioPerfil(UsuarioPerfil up, UserDTO uDto) {
		if (!up.getIdPerfil().equals(Long.parseLong(uDto.getIdPerfil()))) {
			up.setEndDate(new Date());
			upRepo.updateProfileMenu(up);
			insertUsuarioPerfil(uDto);
		}
	}
	// endRegion

	public UsuarioRegistrado buscarUsuarioRegistrado(String username) {
		return usuarioRepo.findByUsername(username);
	}

	public void guardarUsuario(UsuarioRegistrado user) {
		usuarioRepo.insertUser(user);
	}

	public void actualizarUsuario(UsuarioRegistrado user) {
		usuarioRepo.insertUser(user);
	}

	public UserDTO buscaUsuarioMineduc(String userName) {
		ActiveDirectory activeDirectory = new ActiveDirectory(authMail, authCredential, domainController, domain);
		UserDTO ur = new UserDTO();
		try {
			String strTemp = "";
			NamingEnumeration<SearchResult> results = activeDirectory.searchUser(userName, "username", "mineduc.cl");
			if (results.hasMore()) {

				final SearchResult rs = results.next();
				Attributes attrs = rs.getAttributes();

				strTemp = attrs.get("samaccountname").toString();
				ur.setUserName(strTemp.substring(strTemp.indexOf(':') + 1).trim());

				strTemp = attrs.get("employeeid").toString();
				String run = strTemp.substring(strTemp.indexOf(':') + 1).trim();
				ur.setRut(run);

				strTemp = attrs.get("givenname").toString();
				ur.setNombre(strTemp.substring(strTemp.indexOf(':') + 1).trim());

				strTemp = attrs.get("sn").toString();
				String apellidos = strTemp.substring(strTemp.indexOf(':') + 1).trim();
				ur.setApellidoPaterno(apellidos.split(" ")[0]);
				ur.setApellidoMaterno(apellidos.split(" ")[1]);

				if (attrs.get("mail") != null) {
					strTemp = attrs.get("mail").toString();
					ur.setEmail(strTemp.substring(strTemp.indexOf(':') + 1).trim());
				} else {
					ur.setEmail("");
				}

				UsuarioRegistrado userr = buscarUsuarioRegistrado(userName);
				if (userr != null) {
					ur.setIdUsuario(Long.toString(userr.getIdUsuario()));
					ur.setIdRegion(userr.getIdRegion().toString());
					ur.setIdDeprov(userr.getIdDeprov().toString());

					if (!userr.getPerfilesUsuarios().isEmpty()) {
						UsuarioPerfil pu = getLastProfile(userr);
						if (pu != null && pu.getIdPerfil() != null) {
							ur.setIdPerfil(pu.getIdPerfil().toString());
						}
					}
				}
			}
		} catch (Exception ex) {
			if (logger.isDebugEnabled())
				logger.debug("Error al encontrar usuarios");
			activeDirectory.closeLdapConnection();
			throw new MineducException("Error al buscar usuario", ex);
		}
		activeDirectory.closeLdapConnection();
		return ur;
	}

	public List<ProfileSelectorDTO> getProfiles() {
		List<ProfileSelectorDTO> result = new ArrayList<ProfileSelectorDTO>();
		List<Perfil> profilesPaso = this.perfilRepo.obtenerPerfiles().stream().filter(p -> p.getHabilitado())
				.collect(Collectors.toList());

		profilesPaso.forEach(p -> {
			ProfileSelectorDTO selPaso = new ProfileSelectorDTO();
			selPaso.setValue(p.getIdPerfil().toString());
			selPaso.setDisplayText(p.getNombre());
			selPaso.setNivel(p.getPerfilNivel().getNombre());

			result.add(selPaso);
		});
		return result;
	}

	public List<SelectorDTO> getRegiones() {
		List<SelectorDTO> result = new ArrayList<SelectorDTO>();
		List<RegionResponseDTO> regionesPaso = regionIntegration.getRegiones();

		regionesPaso.forEach(p -> result.add(SelectorDTO.of(p.getIdRegion().toString(), p.getNombreRegion())));

		return result;
	}

	public void insertUsuario(UserDTO user) {
		try {
			UsuarioRegistrado userR = convertFromDto(user);
			if (user.getIdUsuario() == null || user.getIdUsuario().isEmpty()) {
				usuarioRepo.insertUser(userR);
				user.setIdUsuario(Long.toString(userR.getIdUsuario()));
				insertUsuarioPerfil(user);
				insertSupervisor(user);
			} else {
				UsuarioRegistrado usuario = usuarioRepo.findById(Long.parseLong(user.getIdUsuario()));
				UsuarioPerfil up = getLastProfile(usuario);
				/* si el perfil es distinto */
				/*
				 * si usuario es supervisor y antes no lo era, se debe finalizar el perfil
				 * anterior e insertar un nuevo registro.
				 */
				/*
				 * si usuario no es supervisor pero antes lo era, se finaliza el perfil anterior
				 * y se debe finalizar el registro supervisor
				 */
				/* Si el perfil no es distinto, no se hace nada. */
				updateUsuarioPerfil(up, user);
				
				if (!up.getIdPerfil().equals(Long.parseLong(user.getIdPerfil())) && user.getIdPerfil().equals(PERFIL_SUPERVISOR)) {
					insertSupervisor(user);
				}

				if (!up.getIdPerfil().equals(Long.parseLong(user.getIdPerfil())) && up.getIdPerfil().equals(Long.parseLong((PERFIL_SUPERVISOR)))) {
					endSupervisor(up.getIdUsuario());
				}

				if(up.getIdPerfil().equals(Long.parseLong(user.getIdPerfil())) && 
				   user.getIdPerfil().equals(PERFIL_SUPERVISOR)
				   ) {
					Supervisor sup = this.supervisorRepo.getSupervisorByIdUsuario(up.getIdUsuario());
					if(!sup.getIdDeprov().equals(Long.parseLong(user.getIdDeprov()))) {
						endSupervisor(up.getIdUsuario());
						insertSupervisor(user);
					}
				}
				
				usuarioRepo.updateUser(userR);
			}
		} catch (Exception e) {
			System.out.println("El error es: ");
			System.out.println(e.getMessage());
			System.out.println(e);
			throw new MineducException("Error insertar usuario", e);
		}
	}

	public void setUserStatus(long idUser, boolean status) {
		UsuarioRegistrado user = usuarioRepo.findById(idUser);
		user.setHabilitado(status);
		usuarioRepo.updateUser(user);
	}

	public UserDTO getUsuarioById(String idUsuario) {
		UsuarioRegistrado usuarioPaso = this.usuarioRepo.findById(Long.parseLong(idUsuario));
		UserDTO result = new UserDTO();
		result.setIdUsuario(Long.toString(usuarioPaso.getIdUsuario()));
		result.setUserName(usuarioPaso.getUsuarioDominio());
		result.setApellidoPaterno(usuarioPaso.getPaterno());
		result.setApellidoMaterno(usuarioPaso.getMaterno());
		result.setNombre(usuarioPaso.getNombre());
		result.setRut(usuarioPaso.getRut() + "-" + usuarioPaso.getDv());


		result.setIdRegion(usuarioPaso.getIdRegion() != null ? usuarioPaso.getIdRegion().toString() : "");
		result.setIdDeprov(usuarioPaso.getIdDeprov() != null ?  usuarioPaso.getIdDeprov().toString() : "") ;
		
		result.setEmail(usuarioPaso.getEmail());
		result.setHabilitado(usuarioPaso.isHabilitado());

		if (!usuarioPaso.getPerfilesUsuarios().isEmpty()) {
			UsuarioPerfil pu = getLastProfile(usuarioPaso);
			if (pu != null && pu.getIdPerfil() != null) {
				result.setIdPerfil(pu.getIdPerfil().toString());
			}
		}

		return result;
	}

	public List<UserDataDTO> getAllUsers() {
		List<UserDataDTO> usersDto = new ArrayList<UserDataDTO>();

		List<Perfil> profilesPaso = this.perfilRepo.obtenerPerfiles().stream().filter(p -> p.getHabilitado()).collect(Collectors.toList());

		List<UsuarioRegistrado> users = this.usuarioRepo.findAll();
		users.forEach(u -> {
			UserDataDTO udto = new UserDataDTO();
			udto.setIdUsuario(u.getIdUsuario().toString());
			udto.setUsername(u.getUsuarioDominio());
			udto.setApellidoPaterno(u.getPaterno());
			udto.setApellidoMaterno(u.getMaterno());
			udto.setNombre(u.getNombre());
			udto.setRut(RutUtil.formatearRut(u.getRut() + u.getDv()));
			if(u.getIdRegion() != null){
				udto.setRegion(getRegionByIdRegion(u.getIdRegion().intValue()).getDisplayText());
			}
			
			if(u.getIdRegion() != null && u.getIdDeprov() != null){
				udto.setDeprov(getDeprov(u.getIdRegion().intValue(), u.getIdDeprov().intValue()).getDisplayText());
			}
			
			// Obtengo el nomre del perfil
			if (!u.getPerfilesUsuarios().isEmpty()) {
				UsuarioPerfil pu = u.getPerfilesUsuarios().stream().filter(p -> p.getEndDate() == null).iterator()
						.next();
				if (pu != null) {
					logger.debug("CONSULTANDO USUARIO PERFIL" + pu.toString());
				}

				if (pu != null && pu.getIdPerfil() != null) {
					Perfil ppaso = profilesPaso.stream().filter(p -> p.getIdPerfil().equals(pu.getIdPerfil()))
							.iterator().next();
					udto.setPerfil(ppaso.getNombre());
				}
			}
			udto.setLastConnection(Util.formatDate(new Date()));
			udto.setHabilitar(u.isHabilitado());
			usersDto.add(udto);
		});
		return usersDto;
	}

	public List<SelectorDTO> getDeprovByRegion(int value) {
		List<SelectorDTO> result = new ArrayList<SelectorDTO>();
		RegionResponseDTO regionesPaso = regionIntegration.getRegionByNumber((long)value);
		regionesPaso.getDeprovs()
				.forEach(p -> result.add(SelectorDTO.of(p.getIdDeprov().toString(), p.getNombreDeprov())));
		return result;
	}

	public SelectorDTO getRegionByIdRegion(int value) {

		RegionResponseDTO regionesPaso = regionIntegration.getRegionByNumber((long)value);

		return SelectorDTO.of(regionesPaso.getIdRegion().toString(), regionesPaso.getNombreRegion());
	}
	
	public String getNameUserById(Long id) {
		UsuarioRegistrado usuarioPaso = this.usuarioRepo.findById(id);
		return usuarioPaso.getNombre().concat(" " + usuarioPaso.getPaterno().concat(" " + usuarioPaso.getMaterno()));
	}
	
	public List<UsuarioRegistrado> getUsersByProfile(Long id) {
		return id != null ? usuarioRepo.findByIdProfile(id) : null;
	}

	public UserEnvDTO getUserEnv(Long userId){
		UserEnvDTO result = null;
		UsuarioRegistrado usuarioPaso = this.usuarioRepo.findById(userId);
		if(usuarioPaso != null){
			result = new UserEnvDTO();
			result.setIdUsuario(usuarioPaso.getIdUsuario().toString());
			result.setUserName(usuarioPaso.getUsuarioDominio());
			result.setNombreCompleto(usuarioPaso.getNombre() + " " + usuarioPaso.getPaterno() + " " + usuarioPaso.getMaterno());
			result.setIdRegion(usuarioPaso.getIdRegion() != null ? usuarioPaso.getIdRegion().toString() : "");
			result.setIdDeprov(usuarioPaso.getIdDeprov() != null ?  usuarioPaso.getIdDeprov().toString() : "");
			result.setDeprov(usuarioPaso.getIdDeprov() != null ? getDeprovByRegion(usuarioPaso.getIdRegion().intValue()).stream().filter(d -> d.getValue().equals(String.valueOf(usuarioPaso.getIdDeprov()))).findFirst().get().getDisplayText() : "");
			if (!usuarioPaso.getPerfilesUsuarios().isEmpty()) {
				UsuarioPerfil pu = getLastProfile(usuarioPaso);
				if (pu != null && pu.getIdPerfil() != null) {
					Perfil perfilPaso = this.perfilRepo.obtenerPerfil(pu.getIdPerfil());
					result.setIdPerfil(perfilPaso.getIdPerfil().toString());
					result.setPerfil(perfilPaso.getNombre());
					result.setIdPerfilNivel(perfilPaso.getPerfilNivel().getIdPerfilNivel().toString());
					result.setNombrePerfilNivel(perfilPaso.getPerfilNivel().getNombre());					
				}
			}

			int year = Calendar.getInstance().get(Calendar.YEAR);
			Periodo period = periodRepo.getPeriodByYear(year);
			if(period != null){
				result.setCurrentPeriod(period.getAnio());
			}
		}

		return result;
	}
}
