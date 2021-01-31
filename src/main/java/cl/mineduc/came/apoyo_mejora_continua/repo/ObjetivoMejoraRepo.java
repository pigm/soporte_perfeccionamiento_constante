package cl.mineduc.came.apoyo_mejora_continua.repo;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;


import cl.mineduc.came.apoyo_mejora_continua.mappers.ObjetivoMejoraMapper;
import cl.mineduc.came.apoyo_mejora_continua.modelo.ObjetivoMejora;
import cl.mineduc.framework2.exceptions.MineducException;

@Repository
public class ObjetivoMejoraRepo {
	
	private static Logger logger = LogManager.getLogger(ObjetivoMejoraRepo.class);
	
	@Autowired
	private ObjetivoMejoraMapper objetivoMejoraMapper;
	
	
	/**
	 * Inserta ObjetivoMejora
	 * 
	 * @param ObjetivoMejoraDirecta
	 */
	public void insert(ObjetivoMejora objetivoMejora) {
		try {
			logger.debug("Insert ObjetivoMejora");
			objetivoMejoraMapper.insert(objetivoMejora);
		} catch (DataAccessException e) {
			throw new MineducException("Error al insertar ObjetivoMejoraDirecta", e);
		}
	}
	
	/**
	 * Elimina ObjetivoMejora por id
	 * 
	 * @param id
	 */
	public void delete(Long id) {
		try {
			logger.debug("Delete ObjetivoMejora by id: " + id);
			objetivoMejoraMapper.deleteById(id);
		} catch (DataAccessException e) {
			throw new MineducException("Error al eliminar ObjetivoMejoraDirecta", e);
		}
	}
	
	/**
	 * Obtiene ObjetivoMejora por id
	 * 
	 * @param id
	 * @return
	 */
	public ObjetivoMejora getById(Long id) {
		try {
			logger.debug("Get ObjetivoMejora by id: " + id);
			return objetivoMejoraMapper.getById(id);
		} catch (DataAccessException e) {
			throw new MineducException("Error al obtener ObjetivoMejoraDirecta", e);
		}
	}
	
	/**
	 * Obtiene todas las ObjetivoMejora
	 * 
	 * @param id
	 * @return
	 */
	public List<ObjetivoMejora> getAll() {
		try {
			logger.debug("Get all Sessions");
			return objetivoMejoraMapper.getAll();
		} catch (DataAccessException e) {
			throw new MineducException("Error al obtener todas las ObjetivoMejoraDirectaes", e);
		}
	}
	
	public List<ObjetivoMejora> getByIdSesion(Long idSesion) {
		try {
			logger.debug("Get all Networks");
			return objetivoMejoraMapper.getByIdSesion(idSesion);
		} catch (DataAccessException e) {
			throw new MineducException("Error al obtener todas las ObjetivoMejoraDirectaes", e);
		}
	}

	/**
	 * Actualiza ObjetivoMejora
	 * 
	 * @param ObjetivoMejora
	 */
	public void update(ObjetivoMejora objetivoMejora) {
		try {
			logger.debug("Update ObjetivoMejora by id: " + objetivoMejora.getIdObjetivoMejora());
			objetivoMejoraMapper.updateById(objetivoMejora);
		} catch (DataAccessException e) {
			throw new MineducException("Error al actualizar ObjetivoMejoraDirecta", e);
		}
	}
}
