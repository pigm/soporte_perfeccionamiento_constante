package cl.mineduc.came.apoyo_mejora_continua.repo;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import cl.mineduc.came.apoyo_mejora_continua.mappers.SesionFocoAccionMejoraMapper;
import cl.mineduc.came.apoyo_mejora_continua.modelo.SesionFocoAccionMejora;
import cl.mineduc.framework2.exceptions.MineducException;

@Repository
public class SesionFocoAccionMejoraRepo {

	private static Logger logger = LogManager.getLogger(SesionFocoAccionMejoraRepo.class);

	@Autowired
	private SesionFocoAccionMejoraMapper sesionFocoAccionMejoraMapper;

	/**
	 * Inserta SesionFocoAccionMejora
	 * 
	 * @param SesionFocoAccionMejoraDirecta
	 */
	public void insert(SesionFocoAccionMejora sesionFocoAccionMejora) {
		try {
			logger.debug("Insert SesionFocoAccionMejora");
			sesionFocoAccionMejoraMapper.insert(sesionFocoAccionMejora);
		} catch (DataAccessException e) {
			throw new MineducException("Error al insertar SesionFocoAccionMejoraDirecta", e);
		}
	}

	/**
	 * Elimina SesionFocoAccionMejora por id
	 * 
	 * @param id
	 */
	public void delete(Long id) {
		try {
			logger.debug("Delete SesionFocoAccionMejora by id: " + id);
			sesionFocoAccionMejoraMapper.deleteById(id);
		} catch (DataAccessException e) {
			throw new MineducException("Error al eliminar SesionFocoAccionMejoraDirecta", e);
		}
	}

	/**
	 * Obtiene SesionFocoAccionMejora por id
	 * 
	 * @param id
	 * @return
	 */
	public SesionFocoAccionMejora getById(Long id) {
		try {
			logger.debug("Get SesionFocoAccionMejora by id: " + id);
			return sesionFocoAccionMejoraMapper.getById(id);
		} catch (DataAccessException e) {
			throw new MineducException("Error al obtener SesionFocoAccionMejoraDirecta", e);
		}
	}

	/**
	 * Obtiene todas las SesionFocoAccionMejora
	 * 
	 * @param id
	 * @return
	 */
	public List<SesionFocoAccionMejora> getAll() {
		try {
			logger.debug("Get all Sessions");
			return sesionFocoAccionMejoraMapper.getAll();
		} catch (DataAccessException e) {
			throw new MineducException("Error al obtener todas las SesionFocoAccionMejoraDirectaes", e);
		}
	}

	public List<SesionFocoAccionMejora> getByIdSesionFoco(Long idSesionFocoAccionMejora) {
		try {
			logger.debug("Get all Networks");
			return sesionFocoAccionMejoraMapper.getByIdSesionFoco(idSesionFocoAccionMejora);
		} catch (DataAccessException e) {
			throw new MineducException("Error al obtener todas las SesionFocoAccionMejoraDirectaes", e);
		}
	}

	/**
	 * Actualiza SesionFocoAccionMejora
	 * 
	 * @param SesionFocoAccionMejora
	 */
	public void update(SesionFocoAccionMejora sesionFocoAccionMejora) {
		try {
			logger.debug(
					"Update SesionFocoAccionMejora by id: " + sesionFocoAccionMejora.getIdSesionFocoAccionMejora());
			sesionFocoAccionMejoraMapper.updateById(sesionFocoAccionMejora);
		} catch (DataAccessException e) {
			throw new MineducException("Error al actualizar SesionFocoAccionMejoraDirecta", e);
		}
	}
}
