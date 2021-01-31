package cl.mineduc.came.apoyo_mejora_continua.repo;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;


import cl.mineduc.came.apoyo_mejora_continua.mappers.SesionFocoMapper;
import cl.mineduc.came.apoyo_mejora_continua.modelo.SesionFoco;
import cl.mineduc.framework2.exceptions.MineducException;

@Repository
public class SesionFocoRepo {
	
	private static Logger logger = LogManager.getLogger(SesionFocoRepo.class);
	
	@Autowired
	private SesionFocoMapper sesionFocoMapper;
	
	
	/**
	 * Inserta SesionFoco
	 * 
	 * @param SesionFocoDirecta
	 */
	public void insert(SesionFoco sesionFoco) {
		try {
			logger.debug("Insert SesionFoco");
			sesionFocoMapper.insert(sesionFoco);
		} catch (DataAccessException e) {
			throw new MineducException("Error al insertar SesionFocoDirecta", e);
		}
	}
	
	/**
	 * Elimina SesionFoco por id
	 * 
	 * @param id
	 */
	public void delete(Long id) {
		try {
			logger.debug("Delete SesionFoco by id: " + id);
			sesionFocoMapper.deleteById(id);
		} catch (DataAccessException e) {
			throw new MineducException("Error al eliminar SesionFocoDirecta", e);
		}
	}
	
	/**
	 * Obtiene SesionFoco por id
	 * 
	 * @param id
	 * @return
	 */
	public SesionFoco getById(Long id) {
		try {
			logger.debug("Get SesionFoco by id: " + id);
			return sesionFocoMapper.getById(id);
		} catch (DataAccessException e) {
			throw new MineducException("Error al obtener SesionFocoDirecta", e);
		}
	}
	
	/**
	 * Obtiene todas las SesionFoco
	 * 
	 * @param id
	 * @return
	 */
	public List<SesionFoco> getAll() {
		try {
			logger.debug("Get all Sessions");
			return sesionFocoMapper.getAll();
		} catch (DataAccessException e) {
			throw new MineducException("Error al obtener todas las SesionFocoDirectaes", e);
		}
	}
	
	public List<SesionFoco> getByIdSesion(Long idSesion) {
		try {
			logger.debug("Get all Networks");
			return sesionFocoMapper.getByIdSesion(idSesion);
		} catch (DataAccessException e) {
			throw new MineducException("Error al obtener todas las SesionFocoDirectaes", e);
		}
	}

	/**
	 * Actualiza SesionFoco
	 * 
	 * @param SesionFoco
	 */
	public void update(SesionFoco sesionFoco) {
		try {
			logger.debug("Update SesionFoco by id: " + sesionFoco.getIdSesionFoco());
			sesionFocoMapper.updateById(sesionFoco);
		} catch (DataAccessException e) {
			throw new MineducException("Error al actualizar SesionFocoDirecta", e);
		}
	}
}
