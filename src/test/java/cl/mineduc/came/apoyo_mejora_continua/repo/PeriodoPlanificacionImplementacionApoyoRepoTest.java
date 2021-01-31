package cl.mineduc.came.apoyo_mejora_continua.repo;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import cl.mineduc.came.apoyo_mejora_continua.mappers.PeriodoPlanificacionImplementacionApoyoMapper;
import cl.mineduc.came.apoyo_mejora_continua.modelo.PeriodoPlanificacionImplementacionApoyo;

@RunWith(MockitoJUnitRunner.class)
public class PeriodoPlanificacionImplementacionApoyoRepoTest {
	
	@Mock
	private PeriodoPlanificacionImplementacionApoyoMapper apoyoMapper;
	
	@InjectMocks
	private PeriodoPlanificacionImplementacionApoyoRepo apoyoRepo;
	
	PeriodoPlanificacionImplementacionApoyo apoyo;
	
	List<PeriodoPlanificacionImplementacionApoyo> apoyos = new ArrayList<PeriodoPlanificacionImplementacionApoyo>();
	
	@Before
	public void setUp() {
		apoyo = mock(PeriodoPlanificacionImplementacionApoyo.class);
		apoyos.add(apoyo);
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void test_insertProvincialPlanification_happyPath() {
		when(apoyoMapper.insert(apoyo)).thenReturn(1);
		apoyoRepo.insertProvincialPlanification(apoyo);
	}
	
	@Test
	public void test_deleteSupportImplementationById_happyPath() {
		when(apoyoMapper.deleteById(1l)).thenReturn(1);
		apoyoRepo.deleteSupportImplementationById(1l);
	}
	
	@Test
	public void test_getSupportImplementationById_happyPath() {
		when(apoyoMapper.getById(1l)).thenReturn(apoyo);
		PeriodoPlanificacionImplementacionApoyo apo = apoyoRepo.getSupportImplementationById(1l);
		assertNotNull(apo);
	}
	
	@Test
	public void test_getAllSupportImplementation_happyPath() {
		when(apoyoMapper.getAll()).thenReturn(apoyos);
		List<PeriodoPlanificacionImplementacionApoyo> apo = apoyoRepo.getAllSupportImplementation();
		assertNotNull(apo);
	}
	
	@Test
	public void test_updateSupportPlanification_happyPath() {
		when(apoyoMapper.updateById(apoyo)).thenReturn(1);
		apoyoRepo.updateSupportPlanification(apoyo);
		
	}

}
