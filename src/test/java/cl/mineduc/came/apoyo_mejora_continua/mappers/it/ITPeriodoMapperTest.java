package cl.mineduc.came.apoyo_mejora_continua.mappers.it;

import static org.junit.Assert.assertNotNull;
import static org.testng.Assert.assertEquals;

import java.util.List;

import org.junit.Ignore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.Test;

import cl.mineduc.came.apoyo_mejora_continua.ApoyoMejoraContinuaConfiguration;
import cl.mineduc.came.apoyo_mejora_continua.ApoyoMejoraContinuaDataSourcesConfiguration;
import cl.mineduc.came.apoyo_mejora_continua.ApoyoMejoraContinuaSecurityConfiguration;
import cl.mineduc.came.apoyo_mejora_continua.mappers.PeriodoMapper;
import cl.mineduc.came.apoyo_mejora_continua.modelo.Periodo;

@Test
@TestPropertySource("classpath:application.properties")
@SpringBootTest(classes = { ApoyoMejoraContinuaDataSourcesConfiguration.class, ApoyoMejoraContinuaConfiguration.class,
		ApoyoMejoraContinuaSecurityConfiguration.class })
public class ITPeriodoMapperTest extends AbstractTransactionalTestNGSpringContextTests {
	
	@Autowired
	private PeriodoMapper periodoMapper;
	
	@Ignore
	@Test
	@Rollback
	public void test_insert_happyPath() {
		Periodo periodo = new Periodo.Builder().build();
		int valor = periodoMapper.insert(periodo);
		assertEquals(valor, 1);
	}
	
	@Test
	@Rollback
	public void test_getAll_happyPath() {
		List<Periodo> peridos = periodoMapper.getAll();
		assertNotNull(peridos);
	}
	
	@Test
	@Rollback
	public void test_getIdPeriodo_happyPath() {
		Periodo perido = periodoMapper.getPeriodoById(2433707983717270587l);
		assertNotNull(perido);
	}
	
	@Test
	@Rollback
	public void test_getDeleteById_happyPath() {
		int perido = periodoMapper.deleteById(2433707983717270587l);
		assertEquals(perido, 1);
	}
	

}
