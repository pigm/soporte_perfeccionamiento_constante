package cl.mineduc.came.apoyo_mejora_continua.repo;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import cl.mineduc.came.apoyo_mejora_continua.mappers.EncuestaMapper;
import cl.mineduc.came.apoyo_mejora_continua.modelo.Encuesta;
import cl.mineduc.framework2.exceptions.MineducException;

@Repository
public class EncuestaRepo {
	
	private static Logger logger = LogManager.getLogger(EncuestaRepo.class);
	
	@Autowired
	private EncuestaMapper encuestaMapper;
	
	public void insert(Encuesta record) {
		try {
			logger.debug("Insertando Encuesta");
			encuestaMapper.insert(record);
		} catch (DataAccessException e) {
			throw new MineducException("Error al insertar Encuesta", e);
		}
	}
	
	public void deleteById(Long id) {
		try {
			logger.debug("Elimina Encuesta " + id);
			encuestaMapper.deleteById(id);
		} catch (DataAccessException e) {
			throw new MineducException("Error al elimina  Encuesta: " + id, e);
		}
	}
	
	public List<Encuesta> getAll() {
		try {
			logger.debug("Obtiene todos los Encuesta Claves");
			return encuestaMapper.getAll();
		} catch (DataAccessException e) {
			throw new MineducException("Obtiene todos los Encuesta Claves", e);
		}
	}
	
	public Encuesta getById(Long id) {
		try {
			logger.debug("Obtiene Encuesta por id: " + id);
			return encuestaMapper.getById(id);
		} catch (DataAccessException e) {
			throw new MineducException("Obtiene Encuesta por id: " + id, e);
		}
	}
		
	public void updateById(Encuesta encuesta) {
		try {
			logger.debug("Actualiza Encuesta por id: " + encuesta.getIdEncuesta());
			encuestaMapper.updateById(encuesta);
		} catch (DataAccessException e) {
			throw new MineducException("Actualiza Encuesta por id: " + encuesta.getIdEncuesta(), e);
		}
	}

	public List<Encuesta> getByIdPeriodo(Long idPeriodo) {
		try {
			logger.debug("Obtiene todos los Encuesta");
			return encuestaMapper.getByIdPeriodo(idPeriodo);
		} catch (DataAccessException e) {
			throw new MineducException("Obtiene todos los Encuesta Claves", e);
		}
	}

}
