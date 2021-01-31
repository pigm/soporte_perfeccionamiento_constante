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
import cl.mineduc.came.apoyo_mejora_continua.mappers.PeriodoDocumentosRegionalesMapper;
import cl.mineduc.came.apoyo_mejora_continua.modelo.PeriodoDocumentosRegionales;

@Test
@TestPropertySource("classpath:application.properties")
@SpringBootTest(classes = { ApoyoMejoraContinuaDataSourcesConfiguration.class, ApoyoMejoraContinuaConfiguration.class,
		ApoyoMejoraContinuaSecurityConfiguration.class })
public class ITPeriodoDocumentosRegionalesMapperTest extends AbstractTransactionalTestNGSpringContextTests{

	@Autowired
	private PeriodoDocumentosRegionalesMapper regionalesMapper;
	
	@Test
	@Rollback
	public void test_insert_happyPath() {
		PeriodoDocumentosRegionales regional = new PeriodoDocumentosRegionales.BuilderRegionals()
				.withIdRegion(2l)
				.withIdPeriod(2433712711394133052l)
				.build();
		int valor = regionalesMapper.insert(regional);
		assertEquals(valor, 1);
	}
	
	@Test
	@Rollback
	public void test_getPeriodsById_happyPath() {
		PeriodoDocumentosRegionales valor = regionalesMapper.getById(2434732495460107343l);
		assertNotNull(valor);
	}
	
	@Test
	@Rollback
	public void test_deleteById_happyPath() {
		int valor = regionalesMapper.deleteById(2434732495460107343l);
		assertEquals(valor, 1);
	}
	
	@Test
	@Rollback
	public void test_getAll_happyPath() {
		List<PeriodoDocumentosRegionales> valor = regionalesMapper.getAll();
		assertNotNull(valor);
	}
	
	@Test
	@Rollback
	public void test_updateById_happyPath() {
		PeriodoDocumentosRegionales regional = new PeriodoDocumentosRegionales.BuilderRegionals()
				.withIdPeriodDocumentsRegionals(2434732495460107343l)
				.build();
		int valor = regionalesMapper.updateById(regional);
		assertEquals(valor, 1);
	}
	
	

}
