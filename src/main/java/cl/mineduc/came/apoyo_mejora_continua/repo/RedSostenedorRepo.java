package cl.mineduc.came.apoyo_mejora_continua.repo;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import cl.mineduc.came.apoyo_mejora_continua.mappers.RedSostenedorMapper;
import cl.mineduc.came.apoyo_mejora_continua.modelo.RedSostenedor;
import cl.mineduc.framework2.exceptions.MineducException;

@Repository
public class RedSostenedorRepo {
	private static Logger logger = LogManager.getLogger(RedSostenedorRepo.class);
	
	@Autowired
	private RedSostenedorMapper sostenedorMapper;
	
	
	/**
	 * Inserta red Sostenedor
	 * 
	 * @param Sostenedor
	 */
	public void insert(RedSostenedor sostenedor) {
		try {
			logger.debug("Insert RedSostenedor");
			sostenedorMapper.insert(sostenedor);
		} catch (DataAccessException e) {
			throw new MineducException("Error al insertar red Sostenedor", e);
		}
	}
	
	/**
	 * Elimina red Sostenedor por id
	 * 
	 * @param id
	 */
	public void deleteById(Long id) {
		try {
			logger.debug("Delete RedSostenedor by id: " + id);
			sostenedorMapper.deleteById(id);
		} catch (DataAccessException e) {
			throw new MineducException("Error al eliminar red Sostenedor", e);
		}
	}
	
	/**
	 * Obtiene red Sostenedor por id
	 * 
	 * @param id
	 * @return
	 */
	public RedSostenedor getById(Long id) {
		try {
			logger.debug("Get RedSostenedor by id: " + id);
			return sostenedorMapper.getById(id);
		} catch (DataAccessException e) {
			throw new MineducException("Error al obtener red Sostenedor", e);
		}
	}
	
	/**
	 * Obtiene todas las redes Sostenedors
	 * 
	 * @return
	 */
	public List<RedSostenedor> getAll() {
		try {
			logger.debug("Get all RedSostenedor");
			return sostenedorMapper.getAll();
		} catch (DataAccessException e) {
			throw new MineducException("Error al obtener todas las redes Sostenedors", e);
		}
	}
	
	/**
	 * Actualiza red Sostenedor
	 * 
	 * @param Sostenedor
	 * 
	 */
	public void updateById(RedSostenedor sostenedor) {
		try {
			logger.debug("Update RedSostenedor by id: " + sostenedor.getIdRedSostenedor());
			sostenedorMapper.updateById(sostenedor);
		} catch (DataAccessException e) {
			throw new MineducException("Error al actualizar red Sostenedor", e);
		}
	}

	public List<RedSostenedor> getByIdRed(Long idRed) {
		try {
			logger.debug("Get all Red Establecimientos by Id red");
			return sostenedorMapper.getByIdRed(idRed);
		} catch (DataAccessException e) {
			throw new MineducException("Error al obtener todas las redes establecimientos", e);
		}
	}

}
