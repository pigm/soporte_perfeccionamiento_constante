package cl.mineduc.came.apoyo_mejora_continua.mappers.it;

import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.List;

import org.junit.Ignore;
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
import cl.mineduc.came.apoyo_mejora_continua.dto.users.UserDTO;
import cl.mineduc.came.apoyo_mejora_continua.mappers.EstadoMapper;
import cl.mineduc.came.apoyo_mejora_continua.mappers.UsuarioPerfilMapper;
import cl.mineduc.came.apoyo_mejora_continua.mappers.UsuarioRegistradoMapper;
import cl.mineduc.came.apoyo_mejora_continua.modelo.Estado;
import cl.mineduc.came.apoyo_mejora_continua.modelo.UsuarioPerfil;
import cl.mineduc.came.apoyo_mejora_continua.modelo.UsuarioRegistrado;
import cl.mineduc.came.apoyo_mejora_continua.services.UsuarioService;

@Ignore
@Test
@TestPropertySource("classpath:application.properties")
@SpringBootTest(classes = { ApoyoMejoraContinuaDataSourcesConfiguration.class, ApoyoMejoraContinuaConfiguration.class,
		ApoyoMejoraContinuaSecurityConfiguration.class })
public class ITUsuarioMapperTest extends AbstractTransactionalTestNGSpringContextTests {

	@Autowired
	private UsuarioRegistradoMapper usuarioMapper;

	@Autowired
	private EstadoMapper estadoMapper;

	@Autowired
	private UsuarioPerfilMapper usuarioPerfilMapper;

	@Autowired
	private UsuarioService usuarioService;

	@Ignore
	@Test
	@Rollback
	@Transactional
	public void test_getAll_happyPath() {
		List<UsuarioRegistrado> usuarios = usuarioMapper.getAll();
		usuarios.forEach(u -> {
			System.out.println(u.getEstado().getNombre());
		});
		assertNotNull(usuarios);
	}

	@Ignore
	@Test
	@Rollback
	@Transactional
	public void test_insertUsuario_happyPath() {

		Estado estado = new Estado();
		estado.setNombre("Nombre estado");
		estadoMapper.insert(estado);

		UsuarioRegistrado usuario = new UsuarioRegistrado();
		usuario.setIdEstado(estado.getIdEstado());
		usuario.setIdRegion((long) 1);
		usuario.setIdDeprov((long) 11);
		usuario.setUsuarioDominio("lalo.arias");
		usuario.setRut("19191919");
		usuario.setNombre("Lalo");
		usuario.setPaterno("Arias");
		usuario.setMaterno("Fuentes");
		usuario.setEmail("lalo.arias@mineduc.cl");
		usuario.setDv("2");
		usuarioMapper.insert(usuario);

		UsuarioPerfil userPerfil = new UsuarioPerfil();
		userPerfil.setIdUsuario(usuario.getIdUsuario());
		userPerfil.setStartDate(new Date());
		userPerfil.setEndDate(new Date());
		usuarioPerfilMapper.insert(userPerfil);
	}

	@Test
	@Rollback
	@Transactional
	public void test_findUserLdap_happyPath() {
		UserDTO usuarios = usuarioService.buscaUsuarioMineduc("loreto.arias");

		assertNotNull(usuarios);
	}
	
	@Test
	public void test_getUsersByProfile_happyPath() {
		List<UsuarioRegistrado> users = usuarioMapper.getUsersByProfile(2416264829774857258l);
		assertNotNull(users);
	}

}
