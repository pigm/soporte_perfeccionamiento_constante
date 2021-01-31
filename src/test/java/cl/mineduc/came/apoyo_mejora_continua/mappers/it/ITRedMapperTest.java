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
import cl.mineduc.came.apoyo_mejora_continua.mappers.RedMapper;
import cl.mineduc.came.apoyo_mejora_continua.modelo.Red;

@Test
@TestPropertySource("classpath:application.properties")
@SpringBootTest(classes = { ApoyoMejoraContinuaDataSourcesConfiguration.class, ApoyoMejoraContinuaConfiguration.class,
		ApoyoMejoraContinuaSecurityConfiguration.class })
public class ITRedMapperTest extends AbstractTransactionalTestNGSpringContextTests {

	@Autowired
	private RedMapper redMapper;

	@Test
	@Rollback
	public void test_insert_happyPath() {
		Red red = new Red();
				// .withIdPeriod(2433706060352062520l)
				// .withIdUser(2430318860893684787l)
				// .withIdDeprov(2l)
				// .build();
		int valor = redMapper.insert(red);
		assertEquals(valor, 1);
	}
	
	@Test
	@Rollback
	public void test_delete_happyPath() {
		int valor = redMapper.deleteById(2434740420924671062l);
		assertEquals(valor, 1);
	}
	
	@Test
	@Rollback
	public void test_getById_happyPath() {
		Red valor = redMapper.getById(2434740420924671062l);
		assertNotNull(valor);
	}
	
	@Test
	@Rollback
	public void test_getAll_happyPath() {
		List<Red> valor = redMapper.getAll();
		assertNotNull(valor);
	}
	
	@Test
	@Rollback
	public void test_updateById_happyPath() {
		Red valor = new Red();
		int val = redMapper.updateById(valor);
		assertEquals(val, 1);
	}

}
