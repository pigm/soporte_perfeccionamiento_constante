package cl.mineduc.came.apoyo_mejora_continua.repo;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import cl.mineduc.came.apoyo_mejora_continua.mappers.PerfilEncuestaMapper;
import cl.mineduc.came.apoyo_mejora_continua.modelo.PerfilEncuesta;
import cl.mineduc.framework2.exceptions.MineducException;

@Repository
public class PerfilEncuestaRepo {
	
	private static Logger logger = LogManager.getLogger(PerfilEncuestaRepo.class);
	
	@Autowired
	private PerfilEncuestaMapper perfilEncuestaMapper;
	
	public void insert(PerfilEncuesta record) {
		try {
			logger.debug("Insertando PerfilEncuesta");
			perfilEncuestaMapper.insert(record);
		} catch (DataAccessException e) {
			throw new MineducException("Error al insertar PerfilEncuesta", e);
		}
	}
	
	public void deleteById(Long id) {
		try {
			logger.debug("Elimina PerfilEncuesta " + id);
			perfilEncuestaMapper.deleteById(id);
		} catch (DataAccessException e) {
			throw new MineducException("Error al elimina  PerfilEncuesta: " + id, e);
		}
	}
	
	public List<PerfilEncuesta> getAll() {
		try {
			logger.debug("Obtiene todos los PerfilEncuesta Claves");
			return perfilEncuestaMapper.getAll();
		} catch (DataAccessException e) {
			throw new MineducException("Obtiene todos los PerfilEncuesta Claves", e);
		}
	}
	
	public PerfilEncuesta getById(Long id) {
		try {
			logger.debug("Obtiene PerfilEncuesta por id: " + id);
			return perfilEncuestaMapper.getById(id);
		} catch (DataAccessException e) {
			throw new MineducException("Obtiene PerfilEncuesta por id: " + id, e);
		}
	}
		
	public void updateById(PerfilEncuesta perfilEncuesta) {
		try {
			logger.debug("Actualiza PerfilEncuesta por id: " + perfilEncuesta.getIdPerfilEncuesta());
			perfilEncuestaMapper.updateById(perfilEncuesta);
		} catch (DataAccessException e) {
			throw new MineducException("Actualiza PerfilEncuesta por id: " + perfilEncuesta.getIdPerfilEncuesta(), e);
		}
	}

	public List<PerfilEncuesta> getByIdEncuesta(Long idEncuesta) {
		try {
			logger.debug("Obtiene todos los PerfilEncuesta Claves");
			return perfilEncuestaMapper.getByIdEncuesta(idEncuesta);
		} catch (DataAccessException e) {
			throw new MineducException("Obtiene todos los PerfilEncuesta Claves", e);
		}
	}

}
