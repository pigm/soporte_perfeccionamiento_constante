package cl.mineduc.came.apoyo_mejora_continua.repo.it;

import static org.junit.Assert.assertNotNull;
import static org.testng.Assert.assertEquals;

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
import cl.mineduc.came.apoyo_mejora_continua.mappers.ElementoListaMapper;
import cl.mineduc.came.apoyo_mejora_continua.modelo.ElementoLista;

@Ignore
@Test
@TestPropertySource("classpath:application.properties")
@SpringBootTest(classes = { ApoyoMejoraContinuaDataSourcesConfiguration.class, ApoyoMejoraContinuaConfiguration.class,
		ApoyoMejoraContinuaSecurityConfiguration.class })
public class ITElementoListaRepoTest extends AbstractTransactionalTestNGSpringContextTests {

	@Autowired
	private ElementoListaMapper elementoListaMapper;

	@Test
	@Rollback
	@Transactional
	public void test_insert_happyPath() {
		ElementoLista elemento = new ElementoLista();
		elemento.setIdLista(2419282499197731845l);
		elementoListaMapper.insert(elemento);
	}

	@Test
	@Rollback
	@Transactional
	public void test_getByIdLista_happyPath() {
		List<ElementoLista> elements = elementoListaMapper.getByIdLista(2419282499197731845l);
		assertNotNull(elements);
	}

	@Test
	@Rollback
	@Transactional
	public void test_deleteById_happyPath() {
		int valor = elementoListaMapper.deleteByPrimaryKey(2419287042140668936l);

		assertEquals(valor, 1);
	}

	@Test
	@Rollback
	@Transactional
	public void test_getById_happyPath() {
		ElementoLista elements = elementoListaMapper.getById(2419298501969052682l);
		assertNotNull(elements);
	}

	@Test
	@Rollback
	@Transactional
	public void test_getUpdateById_happyPath() {
		ElementoLista elemento = new ElementoLista();
		elemento.setIdLista(2419289939624592393l);
		elemento.setIdElementoLista(2419299961276466188l);
		int valor = elementoListaMapper.updateById(elemento);
		assertEquals(valor, 1);
	}

	@Test
	@Rollback
	@Transactional
	public void test_getElements_happyPath() {
		List<ElementoLista> elements = elementoListaMapper.getAll();
		assertNotNull(elements);
	}

}
