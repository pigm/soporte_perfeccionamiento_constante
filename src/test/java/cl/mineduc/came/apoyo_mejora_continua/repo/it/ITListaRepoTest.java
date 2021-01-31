package cl.mineduc.came.apoyo_mejora_continua.repo.it;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Ignore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.Test;

import cl.mineduc.came.apoyo_mejora_continua.ApoyoMejoraContinuaConfiguration;
import cl.mineduc.came.apoyo_mejora_continua.ApoyoMejoraContinuaDataSourcesConfiguration;
import cl.mineduc.came.apoyo_mejora_continua.ApoyoMejoraContinuaSecurityConfiguration;
import cl.mineduc.came.apoyo_mejora_continua.modelo.Lista;
import cl.mineduc.came.apoyo_mejora_continua.repo.ListaRepo;

@Ignore
@Test
@TestPropertySource("classpath:application.properties")
@SpringBootTest(classes = {ApoyoMejoraContinuaDataSourcesConfiguration.class,
				ApoyoMejoraContinuaConfiguration.class, ApoyoMejoraContinuaSecurityConfiguration.class })
public class ITListaRepoTest extends AbstractTransactionalTestNGSpringContextTests{
	
	@Autowired
	private ListaRepo listaRepo;
	
	@Test
	@Rollback
	@Transactional
	public void test_insertLista_happyPath() {
		listaRepo.insertLista(new Lista());
	}
	
	@Test
	@Rollback
	@Transactional
	public void test_getLists_happyPath() {
		List<Lista> listas = listaRepo.getLists();
		assertNotNull(listas);
	}
	
	@Test
	@Rollback
	@Transactional
	public void test_getListById_happyPath() {
		Lista lista = listaRepo.getListById(2419282499197731845l);
		assertNotNull(lista);
	}
	
}
