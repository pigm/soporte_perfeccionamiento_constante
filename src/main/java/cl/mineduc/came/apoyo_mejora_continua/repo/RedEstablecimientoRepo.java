package cl.mineduc.came.apoyo_mejora_continua.repo;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import cl.mineduc.came.apoyo_mejora_continua.mappers.RedEstablecimientoMapper;
import cl.mineduc.came.apoyo_mejora_continua.modelo.RedEstablecimiento;
import cl.mineduc.framework2.exceptions.MineducException;

@Repository
public class RedEstablecimientoRepo {
	private static Logger logger = LogManager.getLogger(RedEstablecimientoRepo.class);
	
	@Autowired
	private RedEstablecimientoMapper establecimientoMapper;
	
	
	/**
	 * Inserta red establecimiento
	 * 
	 * @param establecimiento
	 */
	public void insert(RedEstablecimiento establecimiento) {
		try {
			logger.debug("Insert Network Establishment");
			establecimientoMapper.insert(establecimiento);
		} catch (DataAccessException e) {
			throw new MineducException("Error al insertar red establecimiento", e);
		}
	}
	
	/**
	 * Elimina red establecimiento por id
	 * 
	 * @param id
	 */
	public void deleteById(Long id) {
		try {
			logger.debug("Delete Network Establishment by id: " + id);
			establecimientoMapper.deleteById(id);
		} catch (DataAccessException e) {
			throw new MineducException("Error al eliminar red establecimiento", e);
		}
	}
	
	/**
	 * Obtiene red establecimiento por id
	 * 
	 * @param id
	 * @return
	 */
	public RedEstablecimiento getById(Long id) {
		try {
			logger.debug("Get Network Establishment by id: " + id);
			return establecimientoMapper.getById(id);
		} catch (DataAccessException e) {
			throw new MineducException("Error al obtener red establecimiento", e);
		}
	}
	
	/**
	 * Obtiene todas las redes establecimientos
	 * 
	 * @return
	 */
	public List<RedEstablecimiento> getAll() {
		try {
			logger.debug("Get all Networks Establishment");
			return establecimientoMapper.getAll();
		} catch (DataAccessException e) {
			throw new MineducException("Error al obtener todas las redes establecimientos", e);
		}
	}
	
	/**
	 * Actualiza red establecimiento
	 * 
	 * @param establecimiento
	 * 
	 */
	public void updateById(RedEstablecimiento establecimiento) {
		try {
			logger.debug("Update Network Establishment by id: " + establecimiento.getIdRed());
			establecimientoMapper.updateById(establecimiento);
		} catch (DataAccessException e) {
			throw new MineducException("Error al actualizar red establecimiento", e);
		}
	}

	public List<RedEstablecimiento> getByIdRed(Long idRed) {
		try {
			logger.debug("Get all Red Establecimientos by Id red");
			return establecimientoMapper.getByIdRed(idRed);
		} catch (DataAccessException e) {
			throw new MineducException("Error al obtener todas las redes establecimientos", e);
		}
	}

}
