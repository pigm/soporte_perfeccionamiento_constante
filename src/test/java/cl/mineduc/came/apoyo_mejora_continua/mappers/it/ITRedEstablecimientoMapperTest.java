package cl.mineduc.came.apoyo_mejora_continua.mappers.it;

import static org.junit.Assert.assertNotNull;
import static org.testng.Assert.assertEquals;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.Test;

import cl.mineduc.came.apoyo_mejora_continua.ApoyoMejoraContinuaConfiguration;
import cl.mineduc.came.apoyo_mejora_continua.ApoyoMejoraContinuaDataSourcesConfiguration;
import cl.mineduc.came.apoyo_mejora_continua.ApoyoMejoraContinuaSecurityConfiguration;
import cl.mineduc.came.apoyo_mejora_continua.mappers.RedEstablecimientoMapper;
import cl.mineduc.came.apoyo_mejora_continua.modelo.RedEstablecimiento;

@Test
@TestPropertySource("classpath:application.properties")
@SpringBootTest(classes = { ApoyoMejoraContinuaDataSourcesConfiguration.class, ApoyoMejoraContinuaConfiguration.class,
		ApoyoMejoraContinuaSecurityConfiguration.class })
public class ITRedEstablecimientoMapperTest extends AbstractTransactionalTestNGSpringContextTests {
	
	@Autowired
	private RedEstablecimientoMapper establecimientoMapper;

	@Test
	@Rollback
	public void test_insert_happyPath() {
		RedEstablecimiento red = new RedEstablecimiento();
				// .withIdNetwork(2434740420924671062l)
				// .withIdPeriod(2433706060352062520l)
				// .build();
		int valor = establecimientoMapper.insert(red);
		assertEquals(valor, 1);
	}
	
	@Test
	@Rollback
	public void test_delete_happyPath() {
		int valor = establecimientoMapper.deleteById(2434750851034645596l);
		assertEquals(valor, 1);
	}
	
	@Test
	@Rollback
	public void test_getById_happyPath() {
		RedEstablecimiento valor = establecimientoMapper.getById(2434750851034645596l);
		assertNotNull(valor);
	}
	
	@Test
	@Rollback
	public void test_getAll_happyPath() {
		List<RedEstablecimiento> valor = establecimientoMapper.getAll();
		assertNotNull(valor);
	}
	
	@Test
	@Rollback
	public void test_updateById_happyPath() {
		RedEstablecimiento red = new RedEstablecimiento();
				// .withIdNetworkStablishment(2434750851034645596l)
				// .withIdNetwork(2434739873635107925l)
				// .build();
		red.setIdRed(2434739873635107925l);

		Integer valor = establecimientoMapper.updateById(red);
		assertNotNull(valor);
	}

}
