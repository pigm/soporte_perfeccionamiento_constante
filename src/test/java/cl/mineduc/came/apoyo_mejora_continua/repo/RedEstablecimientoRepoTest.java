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

import cl.mineduc.came.apoyo_mejora_continua.mappers.RedEstablecimientoMapper;
import cl.mineduc.came.apoyo_mejora_continua.modelo.Red;
import cl.mineduc.came.apoyo_mejora_continua.modelo.RedEstablecimiento;

@RunWith(MockitoJUnitRunner.class)
public class RedEstablecimientoRepoTest {
	
	@Mock
	private RedEstablecimientoMapper redEstablecimientoMapper;
	
	@InjectMocks
	private RedEstablecimientoRepo redEstablecimientoRepo;
	
	RedEstablecimiento red;
	
	List<RedEstablecimiento> redes = new ArrayList<RedEstablecimiento>();
	
	@Before
	public void setUp() {
		red = mock(RedEstablecimiento.class);
		redes.add(red);
		
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void test_insertNetworkEstablishment_happyPath() {
		when(redEstablecimientoMapper.insert(red)).thenReturn(1);
		redEstablecimientoRepo.insert(red);
		assertNotNull(red);
	}
	
	@Test
	public void test_deleteNetworkEstablishment_happyPath() {
		when(redEstablecimientoMapper.deleteById(1l)).thenReturn(1);
		redEstablecimientoRepo.deleteById(1l);
		assertNotNull(red);
	}
	
	@Test
	public void test_getNetworkEstablishmentById_happyPath() {
		when(redEstablecimientoMapper.getById(1l)).thenReturn(red);
		RedEstablecimiento establecimiento = redEstablecimientoRepo.getById(1l);
		assertNotNull(establecimiento);
	}
	
	@Test
	public void test_getAllNetworks_happyPath() {
		when(redEstablecimientoMapper.getAll()).thenReturn(redes);
		List<RedEstablecimiento> establecimiento = redEstablecimientoRepo.getAll();
		assertNotNull(establecimiento);
	}
	
	@Test
	public void test_updateNetworkEstablishment_happyPath() {
		when(redEstablecimientoMapper.updateById(red)).thenReturn(1);
		redEstablecimientoRepo.updateById(red);
		assertNotNull(red);
	}

}
