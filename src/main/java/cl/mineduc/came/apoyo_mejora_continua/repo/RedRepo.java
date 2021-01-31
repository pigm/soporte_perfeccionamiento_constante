package cl.mineduc.came.apoyo_mejora_continua.repo;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import cl.mineduc.came.apoyo_mejora_continua.mappers.RedMapper;
import cl.mineduc.came.apoyo_mejora_continua.modelo.Red;
import cl.mineduc.framework2.exceptions.MineducException;

@Repository
public class RedRepo {
	
	private static Logger logger = LogManager.getLogger(RedRepo.class);
	
	@Autowired
	private RedMapper redMapper;
	
	
	/**
	 * Inserta red
	 * 
	 * @param red
	 */
	public void insertNetwork(Red red) {
		try {
			logger.debug("Insert Network");
			redMapper.insert(red);
		} catch (DataAccessException e) {
			throw new MineducException("Error al insertar red", e);
		}
	}
	
	/**
	 * Elimina red por id
	 * 
	 * @param id
	 */
	public void deleteNetwork(Long id) {
		try {
			logger.debug("Delete Network by id: " + id);
			redMapper.deleteById(id);
		} catch (DataAccessException e) {
			throw new MineducException("Error al eliminar red", e);
		}
	}
	
	/**
	 * Obtiene red por id
	 * 
	 * @param id
	 * @return
	 */
	public Red getNetworkById(Long id) {
		try {
			logger.debug("Get Network by id: " + id);
			return redMapper.getById(id);
		} catch (DataAccessException e) {
			throw new MineducException("Error al obtener red", e);
		}
	}
	
	/**
	 * Obtiene todas las redes
	 * 
	 * @param id
	 * @return
	 */
	public List<Red> getAllNetworks() {
		try {
			logger.debug("Get all Networks");
			return redMapper.getAll();
		} catch (DataAccessException e) {
			throw new MineducException("Error al obtener todas las redes", e);
		}
	}
	
	public List<Red> getByIdPeriodo(Long idPeriodo) {
		try {
			logger.debug("Get all Networks");
			return redMapper.getByIdPeriodo(idPeriodo);
		} catch (DataAccessException e) {
			throw new MineducException("Error al obtener todas las redes", e);
		}
	}

	/**
	 * Actualiza red
	 * 
	 * @param red
	 */
	public void updateNetwork(Red red) {
		try {
			logger.debug("Update Networks by id: " + red.getIdRed());
			redMapper.updateById(red);
		} catch (DataAccessException e) {
			throw new MineducException("Error al actualizar red", e);
		}
	}
}
