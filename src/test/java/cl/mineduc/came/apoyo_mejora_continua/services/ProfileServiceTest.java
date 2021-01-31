package cl.mineduc.came.apoyo_mejora_continua.services;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;

import cl.mineduc.came.apoyo_mejora_continua.UtilsProfile;
import cl.mineduc.came.apoyo_mejora_continua.dto.profile.ModuloDataDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.profile.ProfileAccesoDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.profile.ProfileDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.profile.ProfileDataDTO;
import cl.mineduc.came.apoyo_mejora_continua.helpers.UserHelper;
import cl.mineduc.came.apoyo_mejora_continua.modelo.Modulo;
import cl.mineduc.came.apoyo_mejora_continua.modelo.Perfil;
import cl.mineduc.came.apoyo_mejora_continua.modelo.UsuarioRegistrado;
import cl.mineduc.came.apoyo_mejora_continua.repo.ModuloRepo;
import cl.mineduc.came.apoyo_mejora_continua.repo.PerfilMenuRepo;
import cl.mineduc.came.apoyo_mejora_continua.repo.PerfilRepo;

@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(MockitoJUnitRunner.Silent.class)
@PrepareForTest({UserHelper.class})
public class ProfileServiceTest {
	
	@Spy
	private ModelMapper modelMapper;

	@Mock
	private PerfilRepo perfilRepo;

	@Mock
	private ModuloRepo moduloRepo;

	@Mock
	private PerfilMenuRepo perfilMenuRepo;

	@InjectMocks
	private ProfileService profileService;
	
	List<Perfil> perfiles = new ArrayList<Perfil>();
	
	List<Modulo> modulos = new ArrayList<Modulo>();
	
	@Before
	public void setUp() {
		perfiles.add(UtilsProfile.getPerfilMock());
		modulos.add(UtilsProfile.getModuloMock());
		UsuarioRegistrado mockedCurrentUser= UtilsProfile.getCurrentUser();	
		PowerMockito.mockStatic(UserHelper.class);	
		PowerMockito.when(UserHelper.getUsuarioRegistrado()).thenReturn(mockedCurrentUser);
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void test_obtenerPerfiles_happyPath() {
		when(perfilRepo.obtenerPerfiles()).thenReturn(perfiles);
		List<ProfileDataDTO> perfilesDTO = profileService.obtenerPerfiles();
		assertNotNull(perfilesDTO);
	}

	@Test
	public void test_obtenerModulos_happyPath() {
		when(moduloRepo.obtenerModulos()).thenReturn(modulos);
		List<ModuloDataDTO> modulosDto = profileService.obtenerModulos();
		assertNotNull(modulosDto);
	}
	
	@Test
	public void test_getProfile_happyPath() {
		when(perfilRepo.obtenerPerfil(1l)).thenReturn(UtilsProfile.getPerfilMock());
		ProfileDTO profileDto = profileService.getProfile(1l);
		assertNotNull(profileDto);
	}
	
	@Test
	public void test_getProfileWithEndDateNull_happyPath() {
		Perfil perfil = UtilsProfile.getPerfilMock();
		perfil.getPerfilMenu().get(0).setEndDate(null);
		when(perfilRepo.obtenerPerfil(1l)).thenReturn(perfil);
		ProfileDTO profileDto = profileService.getProfile(1l);
		assertNotNull(profileDto);
	}
	
	public void test_setProfileStatus_happyPath() {
		when(perfilRepo.obtenerPerfil(1l)).thenReturn(UtilsProfile.getPerfilMock());
		doNothing().when(perfilRepo).actualizarPerfil(UtilsProfile.getPerfilMock());
		assertNotNull(UtilsProfile.getModuloMock());
	}

	@Test
	public void test_insertPerfil_happyPath() {
		ProfileDTO perfil = convertToDto(UtilsProfile.getPerfilMock());
		List<ProfileAccesoDTO> acs = new ArrayList<ProfileAccesoDTO>();
		acs.add(UtilsProfile.accesoMock());
		perfil.setAccesos(acs);
		perfil.setIdPerfil("");
		doNothing().when(perfilRepo).insertarPerfil(UtilsProfile.getPerfilMock());
		doNothing().when(perfilMenuRepo).insertarPerfilMenu(UtilsProfile.getPerfilMenuMock());
		profileService.insertPerfil(perfil);
	}
	
	@Test
	public void test_updatePerfil_happyPath() {
		ProfileDTO perfil = convertToDto(UtilsProfile.getPerfilMock());
		List<ProfileAccesoDTO> acs = new ArrayList<ProfileAccesoDTO>();
		acs.add(UtilsProfile.accesoMock());
		perfil.setAccesos(acs);
		when(perfilRepo.obtenerPerfil(Long.parseLong(perfil.getIdPerfil()))).thenReturn(UtilsProfile.getPerfilMock());
		doNothing().when(perfilRepo).insertarPerfil(UtilsProfile.getPerfilMock());
		doNothing().when(perfilMenuRepo).insertarPerfilMenu(UtilsProfile.getPerfilMenuMock());
		profileService.insertPerfil(perfil);
	}
	
	private ProfileDTO convertToDto(Perfil perfil) {
		ProfileDTO profile = modelMapper.map(perfil, ProfileDTO.class);
		return profile;
	}

}
