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

import cl.mineduc.came.apoyo_mejora_continua.mappers.PeriodoDocumentosRegionalesMapper;
import cl.mineduc.came.apoyo_mejora_continua.modelo.PeriodoDocumentosRegionales;

@RunWith(MockitoJUnitRunner.class)
public class PeriodoDocumentosRegionalesRepoTest {
	
	@Mock
	private PeriodoDocumentosRegionalesMapper regionalMapper;
	
	@InjectMocks
	private PeriodoDocumentosRegionalesRepo regionalRepo;
	
	PeriodoDocumentosRegionales regional;
	
	List<PeriodoDocumentosRegionales> regionals = new ArrayList<PeriodoDocumentosRegionales>();
	
	@Before
	public void setUp() {
		regional = mock(PeriodoDocumentosRegionales.class);
		regionals.add(regional);
		
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void test_insertDocumentsregional_happyPath() {
		when(regionalMapper.insert(regional)).thenReturn(1);
		regionalRepo.insertDocumentsregional(regional);
	}
	
	@Test
	public void test_deleteDocumentsRegionalById_happyPath() {
		when(regionalMapper.deleteById(1l)).thenReturn(1);
		regionalRepo.deleteDocumentsRegionalById(1l);
	}
	
	@Test
	public void test_getDocumentsRegionalById_happyPath() {
		when(regionalMapper.getById(1l)).thenReturn(regional);
		PeriodoDocumentosRegionales reg = regionalRepo.getDocumentsRegionalById(1l);
		assertNotNull(reg);
	}
	
	@Test
	public void test_getAllDocumentsRegionals_happyPath() {
		when(regionalMapper.getAll()).thenReturn(regionals);
		List<PeriodoDocumentosRegionales> regs = regionalRepo.getAllDocumentsRegionals();
		assertNotNull(regs);
	}
	
	@Test
	public void test_updateDocumentsRegional_happyPath() {
		when(regionalMapper.updateById(regional)).thenReturn(1);
		regionalRepo.updateDocumentsRegional(regional);
	}

}
