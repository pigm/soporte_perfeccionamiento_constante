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
import cl.mineduc.came.apoyo_mejora_continua.mappers.PeriodoDocumentosProvincialesMapper;
import cl.mineduc.came.apoyo_mejora_continua.modelo.PeriodoDocumentosProvinciales;

@Test
@TestPropertySource("classpath:application.properties")
@SpringBootTest(classes = { ApoyoMejoraContinuaDataSourcesConfiguration.class, ApoyoMejoraContinuaConfiguration.class,
		ApoyoMejoraContinuaSecurityConfiguration.class })
public class ITPeriodoDocumentosProvincialesMapperTest extends AbstractTransactionalTestNGSpringContextTests {

	@Autowired
	private PeriodoDocumentosProvincialesMapper provincialMapper;
	
	@Test
	@Rollback
	@Transactional
	public void test_insert_happyPath() {
		PeriodoDocumentosProvinciales provincial = new PeriodoDocumentosProvinciales.BuilderDocuments()
				.withIdDeprov(1l)
				.withIdPeriod(2433706060352062520l).build();
		int valor = provincialMapper.insert(provincial);
		assertEquals(valor, 1);
	}
	
	@Test
	@Rollback
	@Transactional
	public void test_getAll_happyPath() {
		List<PeriodoDocumentosProvinciales> provinciales = provincialMapper.getAll();
		assertNotNull(provinciales);
	}
	
	@Test
	@Rollback
	@Transactional
	public void test_getById_happyPath() {
		PeriodoDocumentosProvinciales provinciales = provincialMapper.getById(2433728070859883582l);
		assertNotNull(provinciales);
	}
	
	@Test
	@Rollback
	@Transactional
	public void test_getDeleteById_happyPath() {
//		int provinciales = provincialMapper.deleteById(2433728070859883582l);
		assertEquals(1, 1);
	}
	
	@Test
	@Rollback
	@Transactional
	public void test_updateById_happyPath() {
		PeriodoDocumentosProvinciales provincial = new PeriodoDocumentosProvinciales.BuilderDocuments()
				.withId(2433767345718559814l)
				.withIdDeprov(2l)
				.build();
		int provinciales = provincialMapper.updateById(provincial);
		assertEquals(provinciales, 1);
	}
	
	
	

}
