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

import cl.mineduc.came.apoyo_mejora_continua.mappers.RedMapper;
import cl.mineduc.came.apoyo_mejora_continua.modelo.Red;

@RunWith(MockitoJUnitRunner.class)
public class RedRepoTest {
	
	@Mock
	private RedMapper redMapper;
	
	@InjectMocks
	private RedRepo redRepo;
	
	Red red;
	
	List<Red> redes = new ArrayList<Red>();
	
	@Before
	public void setUp() {
		red = mock(Red.class);
		redes.add(red);
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void test_insertNetwork_happyPath() {
		when(redMapper.insert(red)).thenReturn(1);
		redRepo.insertNetwork(red);
	}
	
	@Test
	public void test_deleteNetwork_happyPath() {
		when(redMapper.deleteById(1l)).thenReturn(1);
		redRepo.deleteNetwork(1l);
	}
	
	@Test
	public void test_getNetworkById_happyPath() {
		when(redMapper.getById(1l)).thenReturn(red);
		Red r = redRepo.getNetworkById(1l);
		assertNotNull(r);
	}
	
	@Test
	public void test_getAllNetworks_happyPath() {
		when(redMapper.getAll()).thenReturn(redes);
		List<Red> rs = redRepo.getAllNetworks();
		assertNotNull(rs);
	}
	
	@Test
	public void test_updateNetwork_happyPath() {
		when(redMapper.updateById(red)).thenReturn(1);
		redRepo.updateNetwork(red);
	}

}
