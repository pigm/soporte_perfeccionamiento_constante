package cl.mineduc.came.apoyo_mejora_continua.repo;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import cl.mineduc.came.apoyo_mejora_continua.mappers.EncuestaPreguntaMapper;
import cl.mineduc.came.apoyo_mejora_continua.modelo.EncuestaPregunta;
import cl.mineduc.framework2.exceptions.MineducException;

@Repository
public class EncuestaPreguntaRepo {
	
	private static Logger logger = LogManager.getLogger(EncuestaPreguntaRepo.class);
	
	@Autowired
	private EncuestaPreguntaMapper encuestaPreguntaMapper;
	
	public void insert(EncuestaPregunta record) {
		try {
			logger.debug("Insertando EncuestaPregunta");
			encuestaPreguntaMapper.insert(record);
		} catch (DataAccessException e) {
			throw new MineducException("Error al insertar EncuestaPregunta", e);
		}
	}
	
	public void deleteById(Long id) {
		try {
			logger.debug("Elimina EncuestaPregunta " + id);
			encuestaPreguntaMapper.deleteById(id);
		} catch (DataAccessException e) {
			throw new MineducException("Error al elimina  EncuestaPregunta: " + id, e);
		}
	}
	
	public List<EncuestaPregunta> getAll() {
		try {
			logger.debug("Obtiene todos los EncuestaPregunta Claves");
			return encuestaPreguntaMapper.getAll();
		} catch (DataAccessException e) {
			throw new MineducException("Obtiene todos los EncuestaPregunta Claves", e);
		}
	}
	
	public EncuestaPregunta getById(Long id) {
		try {
			logger.debug("Obtiene EncuestaPregunta por id: " + id);
			return encuestaPreguntaMapper.getById(id);
		} catch (DataAccessException e) {
			throw new MineducException("Obtiene EncuestaPregunta por id: " + id, e);
		}
	}
		
	public void updateById(EncuestaPregunta encuestaPregunta) {
		try {
			logger.debug("Actualiza EncuestaPregunta por id: " + encuestaPregunta.getIdEncuestaPregunta());
			encuestaPreguntaMapper.updateById(encuestaPregunta);
		} catch (DataAccessException e) {
			throw new MineducException("Actualiza EncuestaPregunta por id: " + encuestaPregunta.getIdEncuestaPregunta(), e);
		}
	}

	public List<EncuestaPregunta> getByIdEncuesta(Long idEncuesta) {
		try {
			logger.debug("Obtiene todos los EncuestaPregunta");
			return encuestaPreguntaMapper.getByIdEncuesta(idEncuesta);
		} catch (DataAccessException e) {
			throw new MineducException("Obtiene todos los EncuestaPregunta Claves", e);
		}
	}

}
