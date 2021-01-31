package cl.mineduc.came.apoyo_mejora_continua.services;

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
import cl.mineduc.came.apoyo_mejora_continua.dto.period.PeriodoDTO;
import cl.mineduc.came.apoyo_mejora_continua.helpers.UserHelper;
import cl.mineduc.came.apoyo_mejora_continua.helpers.Util;
import cl.mineduc.came.apoyo_mejora_continua.modelo.UsuarioRegistrado;
import cl.mineduc.came.apoyo_mejora_continua.repo.PeriodoAsignacionSupervisoresRepo;
import cl.mineduc.came.apoyo_mejora_continua.repo.PeriodoConformacionRedesRepo;
import cl.mineduc.came.apoyo_mejora_continua.repo.PeriodoDocumentosProvincialesRepo;
import cl.mineduc.came.apoyo_mejora_continua.repo.PeriodoDocumentosRegionalesRepo;
// import cl.mineduc.came.apoyo_mejora_continua.repo.PeriodoOrganizacionPlanificacionProvincialRepo;
import cl.mineduc.came.apoyo_mejora_continua.repo.PeriodoPlanificacionImplementacionApoyoRepo;
import cl.mineduc.came.apoyo_mejora_continua.repo.PeriodoRepo;

@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(MockitoJUnitRunner.Silent.class)
@PrepareForTest({UserHelper.class, Util.class, String.class})
public class PeriodServiceTest {
	
	@Spy
	private ModelMapper modelMapper;
	
	@Mock
	private PeriodoRepo periodoRepo;

	@Mock
	private PeriodoDocumentosProvincialesRepo provincialesRepo;

	@Mock
	private PeriodoDocumentosRegionalesRepo regionalesRepo;

//	@Mock
//	private RedRepo redRepo;
//
//	@Mock
//	private RedEstablecimientoRepo establecimientoRepo;

	// @Mock
	// private PeriodoOrganizacionPlanificacionProvincialRepo provincialRepo;

	@Mock
	private PeriodoPlanificacionImplementacionApoyoRepo apoyoRepo;

	@Mock
	private PeriodoAsignacionSupervisoresRepo supervisorRepo;

	@Mock
	private PeriodoConformacionRedesRepo conformacionRedesRepo;
	
	@InjectMocks
	private PeriodService periodoService;
	
	PeriodoDTO periodo = new PeriodoDTO();
	
	@Before
	public void setUp() {
		periodo = UtilsPeriodoDTO.getPeriodoMock();
		UsuarioRegistrado mockedCurrentUser= UtilsProfile.getCurrentUser();	
		PowerMockito.mockStatic(UserHelper.class);	
		PowerMockito.mockStatic(Util.class);
		PowerMockito.mockStatic(String.class);
		PowerMockito.when(UserHelper.getUsuarioRegistrado()).thenReturn(mockedCurrentUser);
		MockitoAnnotations.initMocks(this);
	}

	// @Test
	// public void test_setPeriod_happyPath() {
	// 	periodoService.setPeriod(periodo);
	// }

}
