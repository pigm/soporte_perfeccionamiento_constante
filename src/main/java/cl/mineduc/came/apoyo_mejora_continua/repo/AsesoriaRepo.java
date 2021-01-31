package cl.mineduc.came.apoyo_mejora_continua.repo;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;


import cl.mineduc.came.apoyo_mejora_continua.mappers.AsesoriaMapper;
import cl.mineduc.came.apoyo_mejora_continua.modelo.Asesoria;
import cl.mineduc.framework2.exceptions.MineducException;

@Repository
public class AsesoriaRepo {
	
	private static Logger logger = LogManager.getLogger(AsesoriaRepo.class);
	
	@Autowired
	private AsesoriaMapper asesoriaMapper;
	
	
	/**
	 * Inserta Asesoria
	 * 
	 * @param AsesoriaDirecta
	 */
	public void insert(Asesoria asesoria) {
		try {
			logger.debug("Insert Asesoria");
			asesoriaMapper.insert(asesoria);
		} catch (DataAccessException e) {
			throw new MineducException("Error al insertar AsesoriaDirecta", e);
		}
	}
	
	/**
	 * Elimina Asesoria por id
	 * 
	 * @param id
	 */
	public void delete(Long id) {
		try {
			logger.debug("Delete Asesoria by id: " + id);
			asesoriaMapper.deleteById(id);
		} catch (DataAccessException e) {
			throw new MineducException("Error al eliminar AsesoriaDirecta", e);
		}
	}
	
	/**
	 * Obtiene Asesoria por id
	 * 
	 * @param id
	 * @return
	 */
	public Asesoria getById(Long id) {
		try {
			logger.debug("Get Asesoria by id: " + id);
			return asesoriaMapper.getById(id);
		} catch (DataAccessException e) {
			throw new MineducException("Error al obtener AsesoriaDirecta", e);
		}
	}
	
	/**
	 * Obtiene todas las Asesoria
	 * 
	 * @param id
	 * @return
	 */
	public List<Asesoria> getAll() {
		try {
			logger.debug("Get all Networks");
			return asesoriaMapper.getAll();
		} catch (DataAccessException e) {
			throw new MineducException("Error al obtener todas las AsesoriaDirectaes", e);
		}
	}
	
	public List<Asesoria> getByIdPeriodo(Long idPeriodo) {
		try {
			logger.debug("Get all Networks");
			return asesoriaMapper.getByIdPeriodo(idPeriodo);
		} catch (DataAccessException e) {
			throw new MineducException("Error al obtener todas las AsesoriaDirectaes", e);
		}
	}

	/**
	 * Actualiza Asesoria
	 * 
	 * @param Asesoria
	 */
	public void update(Asesoria asesoria) {
		try {
			logger.debug("Update Networks by id: " + asesoria.getIdAsesoria());
			asesoriaMapper.updateById(asesoria);
		} catch (DataAccessException e) {
			throw new MineducException("Error al actualizar AsesoriaDirecta", e);
		}
	}
}
