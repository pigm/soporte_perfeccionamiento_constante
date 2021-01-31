package cl.mineduc.came.apoyo_mejora_continua.repo;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;


import cl.mineduc.came.apoyo_mejora_continua.mappers.RetroalimentacionMapper;
import cl.mineduc.came.apoyo_mejora_continua.modelo.Retroalimentacion;
import cl.mineduc.framework2.exceptions.MineducException;

@Repository
public class RetroalimentacionRepo {
	
	private static Logger logger = LogManager.getLogger(RetroalimentacionRepo.class);
	
	@Autowired
	private RetroalimentacionMapper retroalimentacionMapper;
	
	
	/**
	 * Inserta Retroalimentacion
	 * 
	 * @param RetroalimentacionDirecta
	 */
	public void insert(Retroalimentacion retroalimentacion) {
		try {
			logger.debug("Insert Retroalimentacion");
			retroalimentacionMapper.insert(retroalimentacion);
		} catch (DataAccessException e) {
			throw new MineducException("Error al insertar RetroalimentacionDirecta", e);
		}
	}
	
	/**
	 * Elimina Retroalimentacion por id
	 * 
	 * @param id
	 */
	public void delete(Long id) {
		try {
			logger.debug("Delete Retroalimentacion by id: " + id);
			retroalimentacionMapper.deleteById(id);
		} catch (DataAccessException e) {
			throw new MineducException("Error al eliminar RetroalimentacionDirecta", e);
		}
	}
	
	/**
	 * Obtiene Retroalimentacion por id
	 * 
	 * @param id
	 * @return
	 */
	public Retroalimentacion getById(Long id) {
		try {
			logger.debug("Get Retroalimentacion by id: " + id);
			return retroalimentacionMapper.getById(id);
		} catch (DataAccessException e) {
			throw new MineducException("Error al obtener RetroalimentacionDirecta", e);
		}
	}
	
	/**
	 * Obtiene todas las Retroalimentacion
	 * 
	 * @param id
	 * @return
	 */
	public List<Retroalimentacion> getAll() {
		try {
			logger.debug("Get all Retroalimentacion");
			return retroalimentacionMapper.getAll();
		} catch (DataAccessException e) {
			throw new MineducException("Error al obtener todas las RetroalimentacionDirectaes", e);
		}
	}
	
	public List<Retroalimentacion> getByIdPeriodo(Long idPeriodo) {
		try {
			logger.debug("Get all Retroalimentacion");
			return retroalimentacionMapper.getByIdPeriodo(idPeriodo);
		} catch (DataAccessException e) {
			throw new MineducException("Error al obtener todas las RetroalimentacionDirectaes", e);
		}
	}

	/**
	 * Actualiza Retroalimentacion
	 * 
	 * @param Retroalimentacion
	 */
	public void update(Retroalimentacion retroalimentacion) {
		try {
			logger.debug("Update Retroalimentacion by id: " + retroalimentacion.getIdRetroalimentacion());
			retroalimentacionMapper.updateById(retroalimentacion);
		} catch (DataAccessException e) {
			throw new MineducException("Error al actualizar RetroalimentacionDirecta", e);
		}
	}
}
