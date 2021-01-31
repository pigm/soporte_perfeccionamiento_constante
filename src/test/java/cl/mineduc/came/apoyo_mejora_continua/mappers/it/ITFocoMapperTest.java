package cl.mineduc.came.apoyo_mejora_continua.mappers.it;

import static org.junit.Assert.assertNotNull;
import static org.testng.Assert.assertEquals;

import java.time.LocalDateTime;
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
import cl.mineduc.came.apoyo_mejora_continua.mappers.FocoMapper;
import cl.mineduc.came.apoyo_mejora_continua.modelo.Foco;

@Test
@TestPropertySource("classpath:application.properties")
@SpringBootTest(classes = { ApoyoMejoraContinuaDataSourcesConfiguration.class, ApoyoMejoraContinuaConfiguration.class,
		ApoyoMejoraContinuaSecurityConfiguration.class })
public class ITFocoMapperTest extends AbstractTransactionalTestNGSpringContextTests {

	@Autowired
	private FocoMapper focoMapper;
	
	@Test
	public void test_insert_happyPath() {
		Foco foco = new Foco();
		foco.setDescripcion("Descripcion");
		foco.setIdUsuarioModificacion(2414154165161821185l);
		foco.setUltimaModificacion(LocalDateTime.now());
		int valor = focoMapper.insert(foco);
		assertEquals(valor, 1);
	}
	
	@Test
	@Rollback
	public void test_getAll_happyPath() {
		List<Foco> focos = focoMapper.getAll();
		assertNotNull(focos);
	}
	
	@Test
	@Rollback
	public void test_getIdFoco_happyPath() {
		Foco foco = focoMapper.getFocoById(2442696688758948867l);
		assertNotNull(foco);
	}
	
	@Ignore
	@Test
	@Rollback
	public void test_getDeleteById_happyPath() {
		int foco = focoMapper.deleteById(2442696688758948867l);
		assertEquals(foco, 1);
	}
	
	@Test
	@Rollback
	public void test_update_happyPath() {
		Foco foco = new Foco();
		foco.setIdFoco(2442696688758948867l);
		foco.setDescripcion("Descripcion");
		foco.setIdUsuarioModificacion(2414154165161821185l);
		foco.setUltimaModificacion(LocalDateTime.now());
		int valor = focoMapper.updateById(foco);
		assertEquals(valor, 1);
	}

}
