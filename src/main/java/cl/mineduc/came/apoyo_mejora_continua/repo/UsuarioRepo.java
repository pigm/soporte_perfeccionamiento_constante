package cl.mineduc.came.apoyo_mejora_continua.repo;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import cl.mineduc.came.apoyo_mejora_continua.mappers.UsuarioRegistradoMapper;
import cl.mineduc.came.apoyo_mejora_continua.modelo.UsuarioRegistrado;
import cl.mineduc.framework2.exceptions.MineducException;

@Repository("usuarioRepo")
public class UsuarioRepo {

	private static Logger logger = LogManager.getLogger(UsuarioRepo.class);

	@Autowired
	private UsuarioRegistradoMapper usuarioMapper;

	/**
	 * 
	 * @return UsuarioRegistrado
	 */
	public List<UsuarioRegistrado> findAll() {
		try {
			logger.debug("Consulta usuarios");
			return usuarioMapper.getAll();
		} catch (DataAccessException e) {
			throw new MineducException("Error al obtener todos UsuarioRegistrado", e);
		}
	}
	
	/**
	 * @param id
	 * @return UsuarioRegistrado
	 */
	public UsuarioRegistrado findById(Long id) {
		try {
			logger.debug("Consulta usuarios por id " + id);
			return usuarioMapper.getUsuarioById(id);
		} catch (DataAccessException e) {
			throw new MineducException("Error al consultar UsuarioRegistrado por id", e);
		}
	}
	
	/**
	 * @param username
	 * @return UsuarioRegistrado
	 */
	public UsuarioRegistrado findByUsername(String username) {
		try {
			logger.debug("Consulta usuarios por username " + username);
			return usuarioMapper.getUsuarioByUsername(username);
		} catch (DataAccessException e) {
			throw new MineducException("Error al consultar UsuarioRegistrado", e);
		}
	}
	
	/**
	 * @param usuario
	 */
	public void insertUser(UsuarioRegistrado usuario) {
		try {
			usuarioMapper.insert(usuario);
		} catch (DataAccessException e) {
			throw new MineducException("Error al insertar UsuarioRegistrado", e);
		}
	}
	
	/**
	 * @param usuario
	 */
	public void updateUser(UsuarioRegistrado usuario) {
		try {
			usuarioMapper.update(usuario);
		} catch (DataAccessException e) {
			throw new MineducException("Error al insertar UsuarioRegistrado", e);
		}
	}
	
	/**
	 * Consulta usuarios segun perfil
	 * @param idProfile
	 * @return
	 */
	public List<UsuarioRegistrado> findByIdProfile(Long idProfile) {
		try {
			logger.debug("Consulta usuarios segun perfil {}", idProfile);
			return usuarioMapper.getUsersByProfile(idProfile);
		} catch (DataAccessException e) {
			throw new MineducException("Error al consultar UsuarioRegistrado" + idProfile, e);
		}
	}

}
