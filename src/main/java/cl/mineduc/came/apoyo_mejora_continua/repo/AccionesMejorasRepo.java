package cl.mineduc.came.apoyo_mejora_continua.repo;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import cl.mineduc.came.apoyo_mejora_continua.mappers.AccionesMejorasMapper;
import cl.mineduc.came.apoyo_mejora_continua.modelo.AccionesMejoras;
import cl.mineduc.framework2.exceptions.MineducException;

@Repository
public class AccionesMejorasRepo {
	
	private static Logger logger = LogManager.getLogger(AccionesMejorasRepo.class);
	
	@Autowired
	private AccionesMejorasMapper accionesMapper;
	
	public void insertAccionMejora(AccionesMejoras acciones) {
		try {
			logger.debug("Insertando Accion de mejora");
			accionesMapper.insert(acciones);
		} catch (DataAccessException e) {
			throw new MineducException("Error al insertar Accion de mejora", e);
		}
	}
	
	public void deleteAccionMejoraById(Long id) {
		try {
			logger.debug("Elimina Accion de mejora: " + id);
			accionesMapper.deleteById(id);
		} catch (DataAccessException e) {
			throw new MineducException("Elimina Accion de mejora: " + id, e);
		}
	}
	
	public AccionesMejoras getAccionMejoraById(Long id) {
		try {
			logger.debug("Obtiene Accion de mejora: " + id);
			return accionesMapper.getById(id);
		} catch (DataAccessException e) {
			throw new MineducException("Obtiene Accion de mejora: " + id, e);
		}
	}

	public AccionesMejoras getByName(String name) {
		try {
			logger.debug("Obtiene Accion de mejora: " + name);
			return accionesMapper.getByName(name);
		} catch (DataAccessException e) {
			throw new MineducException("Obtiene Accion de mejora: " + name, e);
		}
	}
	
	public List<AccionesMejoras> getAllAccionMejora() {
		try {
			logger.debug("Obtiene todas las Acciones de mejoras");
			return accionesMapper.getAll();
		} catch (DataAccessException e) {
			throw new MineducException("Obtiene todas las Acciones de mejoras", e);
		}
	}
	
	public void updateAccionMejora(AccionesMejoras acciones) {
		try {
			logger.debug("Actualiza Accion de mejora: " + acciones.getIdAccionesMejoras());
			accionesMapper.updateById(acciones);
		} catch (DataAccessException e) {
			throw new MineducException("Actualiza Accion de mejora: " + acciones.getIdAccionesMejoras(), e);
		}
	}

}
