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

import cl.mineduc.came.apoyo_mejora_continua.mappers.PeriodoMapper;
import cl.mineduc.came.apoyo_mejora_continua.modelo.Periodo;

@RunWith(MockitoJUnitRunner.class)
public class PeriodoRepoTest {
	
	@Mock
	private PeriodoMapper periodoMapper;
	
	@InjectMocks
	private PeriodoRepo periodoRepo;
	
	Periodo periodo;
	
	List<Periodo> periodos = new ArrayList<Periodo>();
	
	@Before
	public void setUp() {
		periodo = mock(Periodo.class);
		periodos.add(periodo);		
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void test_insertPeriod_happyPath() {
		when(periodoMapper.insert(periodo)).thenReturn(1);
		periodoRepo.insertPeriod(periodo);
	}
	
	@Test
	public void test_deletePeriodByid_happyPath() {
		when(periodoMapper.deleteById(1l)).thenReturn(1);
		periodoRepo.deletePeriodByid(1l);
	}
	
	@Test
	public void test_getAllPeriods_happyPath() {
		when(periodoMapper.getAll()).thenReturn(periodos);
		List<Periodo> p = periodoRepo.getAllPeriods();
		assertNotNull(p);
	}
	
	@Test
	public void test_getPeriodById_happyPath() {
		when(periodoMapper.getPeriodoById(1l)).thenReturn(periodo);
		Periodo per = periodoRepo.getPeriodById(1l);
		assertNotNull(per);
	}

}
