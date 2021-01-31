package cl.mineduc.came.apoyo_mejora_continua.repo;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import cl.mineduc.came.apoyo_mejora_continua.mappers.PeriodoPlanificacionImplementacionApoyoMapper;
import cl.mineduc.came.apoyo_mejora_continua.modelo.PeriodoPlanificacionImplementacionApoyo;
import cl.mineduc.framework2.exceptions.MineducException;

@Repository
public class PeriodoPlanificacionImplementacionApoyoRepo {
	
	private static Logger logger = 
			LogManager.getLogger(PeriodoPlanificacionImplementacionApoyoRepo.class);
	
	@Autowired
	private PeriodoPlanificacionImplementacionApoyoMapper apoyoMapper;
	
	/**
	 * Inserta periodo implementacion apoyo
	 * 
	 * @param apoyo
	 */
	public void insertProvincialPlanification(PeriodoPlanificacionImplementacionApoyo apoyo) {
		try {
			logger.debug("Insert periods support");
			apoyoMapper.insert(apoyo);
		} catch (DataAccessException e) {
			throw new MineducException("Error al insertar periodo apoyo", e);
		}
	}
	
	/**
	 * Elimina periodo implementacion apoyo por id
	 * 
	 * @param id
	 */
	public void deleteSupportImplementationById(Long id) {
		try {
			logger.debug("delete periods support by id: " + id);
			apoyoMapper.deleteById(id);
		} catch (DataAccessException e) {
			throw new MineducException("Error al eliminar periodo de apoyo", e);
		}
	}
	
	/**
	 * Obtiene periodo implementacion apoyo por id
	 * 
	 * @param id
	 * @return
	 */
	public PeriodoPlanificacionImplementacionApoyo getSupportImplementationById(Long id) {
		try {
			logger.debug("get periods provincial by id: " + id);
			return apoyoMapper.getById(id);
		} catch (DataAccessException e) {
			throw new MineducException("Error al obtener periodo provincial", e);
		}
	}
	
	/**
	 * Obtiene todos los periodos implementacion apoyo
	 * 
	 * @return
	 */
	public List<PeriodoPlanificacionImplementacionApoyo> getAllSupportImplementation() {
		try {
			logger.debug("get all periods support");
			return apoyoMapper.getAll();
		} catch (DataAccessException e) {
			throw new MineducException("Error al obtener periodos apoyos", e);
		}
	}
	
	/**
	 * Actualiza periodo implementacion apoyo
	 * 
	 * @param apoyo
	 */
	public void updateSupportPlanification(PeriodoPlanificacionImplementacionApoyo apoyo) {
		try {
			logger.debug("Update periods support by id: " + apoyo.getIdPeriodoPlanificacionImplementacionApoyo());
			apoyoMapper.updateById(apoyo);
		} catch (DataAccessException e) {
			throw new MineducException("Error al actualizar periodo apoyo", e);
		}
	}

}
