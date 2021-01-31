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
import cl.mineduc.came.apoyo_mejora_continua.modelo.api.Establecimientos;

@Test
@TestPropertySource("classpath:application.properties")
@SpringBootTest(classes = { ApoyoMejoraContinuaDataSourcesConfiguration.class, ApoyoMejoraContinuaConfiguration.class,
		ApoyoMejoraContinuaSecurityConfiguration.class })
public class EstablecimientosServiceTest extends AbstractTestNGSpringContextTests {
	
	@Autowired
	private EstablecimientosIntegration it;
	
	@Test
	public void test_getAll_happyPath() {
		List<Establecimientos> establecimientos = it.getEstablecimientos(1,1,1,1,1,"","",1);
		assertNotNull(establecimientos);
	}
	
	@Test
	public void test_getEstablishment_happyPath() {
		Establecimientos establecimientos = it.getEstablecimientoByRbd(9);
		assertNotNull(establecimientos);
	}

	

}
