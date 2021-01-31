package cl.mineduc.came.apoyo_mejora_continua.integration;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import cl.mineduc.came.apoyo_mejora_continua.ApoyoMejoraContinuaConfiguration;
import cl.mineduc.came.apoyo_mejora_continua.ApoyoMejoraContinuaDataSourcesConfiguration;
import cl.mineduc.came.apoyo_mejora_continua.ApoyoMejoraContinuaSecurityConfiguration;
import cl.mineduc.came.apoyo_mejora_continua.modelo.api.sostenedores.Sostenedores;

@Test
@TestPropertySource("classpath:application.properties")
@SpringBootTest(classes = { ApoyoMejoraContinuaDataSourcesConfiguration.class, ApoyoMejoraContinuaConfiguration.class,
		ApoyoMejoraContinuaSecurityConfiguration.class })
public class SostenedoresServiceTest extends AbstractTestNGSpringContextTests {

	@Autowired
	private SostenedoresIntegration it;
	
	@Test
	public void test_getAll_happyPath() {
		List<Sostenedores> sostenedores = it.getSostenedores(1);
		assertNotNull(sostenedores);
	}
	
	public void test_getSostenedorByRut_happyPath() {
		Sostenedores sostenedor = it.getSostenedorByRut("70938800");
		assertNotNull(sostenedor);
	}
}
