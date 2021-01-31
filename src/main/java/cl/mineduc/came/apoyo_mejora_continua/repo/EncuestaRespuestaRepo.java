package cl.mineduc.came.apoyo_mejora_continua.repo;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import cl.mineduc.came.apoyo_mejora_continua.mappers.EncuestaRespuestaMapper;
import cl.mineduc.came.apoyo_mejora_continua.modelo.EncuestaRespuesta;
import cl.mineduc.framework2.exceptions.MineducException;

@Repository
public class EncuestaRespuestaRepo {
	
	private static Logger logger = LogManager.getLogger(EncuestaRespuestaRepo.class);
	
	@Autowired
	private EncuestaRespuestaMapper encuestaRespuestaMapper;
	
	public void insert(EncuestaRespuesta record) {
		try {
			logger.debug("Insertando EncuestaRespuesta");
			encuestaRespuestaMapper.insert(record);
		} catch (DataAccessException e) {
			throw new MineducException("Error al insertar EncuestaRespuesta", e);
		}
	}
	
	public void deleteById(Long id) {
		try {
			logger.debug("Elimina EncuestaRespuesta " + id);
			encuestaRespuestaMapper.deleteById(id);
		} catch (DataAccessException e) {
			throw new MineducException("Error al elimina  EncuestaRespuesta: " + id, e);
		}
	}
	
	public List<EncuestaRespuesta> getAll() {
		try {
			logger.debug("Obtiene todos los EncuestaRespuesta Claves");
			return encuestaRespuestaMapper.getAll();
		} catch (DataAccessException e) {
			throw new MineducException("Obtiene todos los EncuestaRespuesta Claves", e);
		}
	}
	
	public EncuestaRespuesta getById(Long id) {
		try {
			logger.debug("Obtiene EncuestaRespuesta por id: " + id);
			return encuestaRespuestaMapper.getById(id);
		} catch (DataAccessException e) {
			throw new MineducException("Obtiene EncuestaRespuesta por id: " + id, e);
		}
	}
		
	public void updateById(EncuestaRespuesta encuestaRespuesta) {
		try {
			logger.debug("Actualiza EncuestaRespuesta por id: " + encuestaRespuesta.getIdEncuestaRespuesta());
			encuestaRespuestaMapper.updateById(encuestaRespuesta);
		} catch (DataAccessException e) {
			throw new MineducException("Actualiza EncuestaRespuesta por id: " + encuestaRespuesta.getIdEncuestaRespuesta(), e);
		}
	}

	public List<EncuestaRespuesta> getByIdPregunta(Long idPregunta) {
		try {
			logger.debug("Obtiene todos los EncuestaRespuesta Claves");
			return encuestaRespuestaMapper.getByIdPregunta(idPregunta);
		} catch (DataAccessException e) {
			throw new MineducException("Obtiene todos los EncuestaRespuesta Claves", e);
		}
	}

}
