package cl.mineduc.came.apoyo_mejora_continua.mappers.it;

import static org.junit.Assert.assertNotNull;
import static org.testng.Assert.assertEquals;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.Test;

import cl.mineduc.came.apoyo_mejora_continua.ApoyoMejoraContinuaConfiguration;
import cl.mineduc.came.apoyo_mejora_continua.ApoyoMejoraContinuaDataSourcesConfiguration;
import cl.mineduc.came.apoyo_mejora_continua.ApoyoMejoraContinuaSecurityConfiguration;
import cl.mineduc.came.apoyo_mejora_continua.mappers.MovimientosClavesMapper;
import cl.mineduc.came.apoyo_mejora_continua.modelo.MovimientosClaves;

@Test
@TestPropertySource("classpath:application.properties")
@SpringBootTest(classes = { ApoyoMejoraContinuaDataSourcesConfiguration.class, ApoyoMejoraContinuaConfiguration.class,
		ApoyoMejoraContinuaSecurityConfiguration.class })
public class ITMovimientosClavesMapperTest extends AbstractTransactionalTestNGSpringContextTests {

	@Autowired
	private MovimientosClavesMapper movimientosMapper;
	
	@Test
	public void testDeleteById() {
		int valor = movimientosMapper.deleteById(2442717530012255249l);
		assertEquals(valor, 1);
	}

	@Test
	public void testInsert() {
		MovimientosClaves mov = new MovimientosClaves();
		mov.setIdAccionesMejoras(2442705550803207178l);
		mov.setDescripcion("acciones");
		mov.setNombre("prueba");
		mov.setUltimaModificacion(LocalDateTime.now());
		mov.setIdUsuarioModificacion(2414154165161821185l);
		int a = movimientosMapper.insert(mov);
		assertEquals(a, 1);
	}

	@Test
	public void testGetById() {
		MovimientosClaves mov = movimientosMapper.getById(2442717530012255249l);
		assertNotNull(mov);
	}

	@Test
	public void testGetAll() {
		List<MovimientosClaves> movs = movimientosMapper.getAll();
		assertNotNull(movs);
	}

	@Test
	public void testUpdateById() {
		MovimientosClaves mov = new MovimientosClaves();
		mov.setIdMovimientosClaves(2442717530012255249l);
		mov.setIdAccionesMejoras(2442705550803207178l);
		mov.setDescripcion("actualizado");
		mov.setNombre("prueba");
		mov.setUltimaModificacion(LocalDateTime.now());
		mov.setIdUsuarioModificacion(2414154165161821185l);
		int a = movimientosMapper.updateById(mov);
		assertEquals(a, 1);
	}

}
