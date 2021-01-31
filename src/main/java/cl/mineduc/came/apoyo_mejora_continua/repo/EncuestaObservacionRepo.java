package cl.mineduc.came.apoyo_mejora_continua.repo;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import cl.mineduc.came.apoyo_mejora_continua.mappers.EncuestaObservacionMapper;
import cl.mineduc.came.apoyo_mejora_continua.modelo.EncuestaObservacion;
import cl.mineduc.framework2.exceptions.MineducException;

@Repository
public class EncuestaObservacionRepo {
	
	private static Logger logger = LogManager.getLogger(EncuestaObservacionRepo.class);
	
	@Autowired
	private EncuestaObservacionMapper encuestaObservacionMapper;
	
	public void insert(EncuestaObservacion record) {
		try {
			logger.debug("Insertando EncuestaObservacion");
			encuestaObservacionMapper.insert(record);
		} catch (DataAccessException e) {
			throw new MineducException("Error al insertar EncuestaObservacion", e);
		}
	}
	
	public void deleteById(Long id) {
		try {
			logger.debug("Elimina EncuestaObservacion " + id);
			encuestaObservacionMapper.deleteById(id);
		} catch (DataAccessException e) {
			throw new MineducException("Error al elimina  EncuestaObservacion: " + id, e);
		}
	}
	
	public List<EncuestaObservacion> getAll() {
		try {
			logger.debug("Obtiene todos los EncuestaObservacion Claves");
			return encuestaObservacionMapper.getAll();
		} catch (DataAccessException e) {
			throw new MineducException("Obtiene todos los EncuestaObservacion Claves", e);
		}
	}
	
	public EncuestaObservacion getById(Long id) {
		try {
			logger.debug("Obtiene EncuestaObservacion por id: " + id);
			return encuestaObservacionMapper.getById(id);
		} catch (DataAccessException e) {
			throw new MineducException("Obtiene EncuestaObservacion por id: " + id, e);
		}
	}
		
	public void updateById(EncuestaObservacion encuestaObservacion) {
		try {
			logger.debug("Actualiza EncuestaObservacion por id: " + encuestaObservacion.getIdEncuestaObservacion());
			encuestaObservacionMapper.updateById(encuestaObservacion);
		} catch (DataAccessException e) {
			throw new MineducException("Actualiza EncuestaObservacion por id: " + encuestaObservacion.getIdEncuestaObservacion(), e);
		}
	}

	public List<EncuestaObservacion> getByIdEncuesta(Long idEncuesta) {
		try {
			logger.debug("Obtiene todos los EncuestaObservacion Claves");
			return encuestaObservacionMapper.getByIdEncuesta(idEncuesta);
		} catch (DataAccessException e) {
			throw new MineducException("Obtiene todos los EncuestaObservacion Claves", e);
		}
	}

}
