package cl.mineduc.came.apoyo_mejora_continua.mappers.it;

import static org.junit.Assert.assertNotNull;
import static org.testng.Assert.assertEquals;

import java.time.LocalDateTime;
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
import cl.mineduc.came.apoyo_mejora_continua.mappers.AccionesMejorasMapper;
import cl.mineduc.came.apoyo_mejora_continua.modelo.AccionesMejoras;

@Test
@TestPropertySource("classpath:application.properties")
@SpringBootTest(classes = { ApoyoMejoraContinuaDataSourcesConfiguration.class, ApoyoMejoraContinuaConfiguration.class,
		ApoyoMejoraContinuaSecurityConfiguration.class })
public class ITAccionesMejorasMapperTest extends AbstractTransactionalTestNGSpringContextTests {
	
	@Autowired
	private AccionesMejorasMapper accionesMapper;

	@Test
	public void test_insert_happyPath() {
		AccionesMejoras acciones = new AccionesMejoras();
		acciones.setDescripcion("Descripcion");
		acciones.setIdUsuarioModificacion(2414154165161821185l);
		acciones.setUltimaModificacion(LocalDateTime.now());
		int valor = accionesMapper.insert(acciones);
		assertEquals(valor, 1);
	}
	
	@Test
	@Rollback
	public void test_getAll_happyPath() {
		List<AccionesMejoras> acciones = accionesMapper.getAll();
		assertNotNull(acciones);
	}
	
	@Test
	@Rollback
	public void test_getIdAccionesMejoras_happyPath() {
		AccionesMejoras acc = accionesMapper.getById(2442705550803207178l);
		assertNotNull(acc);
	}
	
	@Test
	@Rollback
	public void test_getDeleteById_happyPath() {
		int acc = accionesMapper.deleteById(2442703912306738184l);
		assertEquals(acc, 1);
	}
	
	@Test
	@Rollback
	public void test_update_happyPath() {
		AccionesMejoras acciones = new AccionesMejoras();
		acciones.setIdAccionesMejoras(2442703912306738184l);
		acciones.setDescripcion("Descripcion");
		acciones.setIdUsuarioModificacion(2414154165161821185l);
		acciones.setUltimaModificacion(LocalDateTime.now());
		acciones.setIdFoco(2442696688758948867l);
		int valor = accionesMapper.updateById(acciones);
		assertEquals(valor, 1);
	}


}
