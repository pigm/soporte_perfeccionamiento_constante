package cl.mineduc.came.apoyo_mejora_continua.mappers.it;

import static org.junit.Assert.assertNotNull;
import static org.testng.Assert.assertEquals;

import java.util.List;

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
import cl.mineduc.came.apoyo_mejora_continua.mappers.PeriodoPlanificacionImplementacionApoyoMapper;
import cl.mineduc.came.apoyo_mejora_continua.modelo.PeriodoPlanificacionImplementacionApoyo;

@Test
@TestPropertySource("classpath:application.properties")
@SpringBootTest(classes = { ApoyoMejoraContinuaDataSourcesConfiguration.class, ApoyoMejoraContinuaConfiguration.class,
		ApoyoMejoraContinuaSecurityConfiguration.class })
public class ITPeriodoPlanificacionImplementacionApoyoMapperTest extends AbstractTransactionalTestNGSpringContextTests {

	@Autowired
	private PeriodoPlanificacionImplementacionApoyoMapper apoyoMapper;
	
	@Test
	@Rollback
	@Transactional
	public void test_insert_happyPath() {
		PeriodoPlanificacionImplementacionApoyo apoyo = new PeriodoPlanificacionImplementacionApoyo
				.BuilderApoyo()
				.withIdPeriod(2433706060352062520l)
				.build();
		int valor = apoyoMapper.insert(apoyo);
		assertEquals(valor, 1);
	}
	
	@Test
	@Rollback
	@Transactional
	public void test_deleteById_happyPath() {
		int valor = apoyoMapper.deleteById(2434771595533747297l);
		assertEquals(valor, 1);
	}
	
	@Test
	@Rollback
	@Transactional
	public void test_getById_happyPath() {
		PeriodoPlanificacionImplementacionApoyo apoyo = apoyoMapper.getById(2434771595533747297l);
		assertNotNull(apoyo);
	}
	
	@Test
	@Rollback
	@Transactional
	public void test_getAll_happyPath() {
		List<PeriodoPlanificacionImplementacionApoyo> apoyo = apoyoMapper.getAll();
		assertNotNull(apoyo);
	}
	
	@Test
	@Rollback
	@Transactional
	public void test_update_happyPath() {
		PeriodoPlanificacionImplementacionApoyo apoyo = new PeriodoPlanificacionImplementacionApoyo
				.BuilderApoyo()
				.withIdImplementationSupport(2434771595533747297l)
				.withIdPeriod(2433758563164750914l)
				.build();
		int valor = apoyoMapper.updateById(apoyo);
		assertEquals(valor, 1);
	}

}
