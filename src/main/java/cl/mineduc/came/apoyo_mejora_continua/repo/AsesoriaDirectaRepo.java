package cl.mineduc.came.apoyo_mejora_continua.repo;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import cl.mineduc.came.apoyo_mejora_continua.mappers.AsesoriaDirectaMapper;
import cl.mineduc.came.apoyo_mejora_continua.modelo.AsesoriaDirecta;
import cl.mineduc.framework2.exceptions.MineducException;

@Repository
public class AsesoriaDirectaRepo {
	
	private static Logger logger = LogManager.getLogger(AsesoriaDirectaRepo.class);
	
	@Autowired
	private AsesoriaDirectaMapper asesoriaDirectaMapper;
	
	
	/**
	 * Inserta AsesoriaDirecta
	 * 
	 * @param AsesoriaDirecta
	 */
	public void insert(AsesoriaDirecta asesoriaDirecta) {
		try {
			logger.debug("Insert Network");
			asesoriaDirectaMapper.insert(asesoriaDirecta);
		} catch (DataAccessException e) {
			throw new MineducException("Error al insertar AsesoriaDirecta", e);
		}
	}
	
	/**
	 * Elimina AsesoriaDirecta por id
	 * 
	 * @param id
	 */
	public void delete(Long id) {
		try {
			logger.debug("Delete Network by id: " + id);
			asesoriaDirectaMapper.deleteById(id);
		} catch (DataAccessException e) {
			throw new MineducException("Error al eliminar AsesoriaDirecta", e);
		}
	}
	
	/**
	 * Obtiene AsesoriaDirecta por id
	 * 
	 * @param id
	 * @return
	 */
	public AsesoriaDirecta getById(Long id) {
		try {
			logger.debug("Get Network by id: " + id);
			return asesoriaDirectaMapper.getById(id);
		} catch (DataAccessException e) {
			throw new MineducException("Error al obtener AsesoriaDirecta", e);
		}
	}
	
	/**
	 * Obtiene todas las AsesoriaDirectaes
	 * 
	 * @param id
	 * @return
	 */
	public List<AsesoriaDirecta> getAll() {
		try {
			logger.debug("Get all Networks");
			return asesoriaDirectaMapper.getAll();
		} catch (DataAccessException e) {
			throw new MineducException("Error al obtener todas las AsesoriaDirectaes", e);
		}
	}
	
	public List<AsesoriaDirecta> getByIdPeriodo(Long idPeriodo) {
		try {
			logger.debug("Get all Networks");
			return asesoriaDirectaMapper.getByIdPeriodo(idPeriodo);
		} catch (DataAccessException e) {
			throw new MineducException("Error al obtener todas las AsesoriaDirectaes", e);
		}
	}

	/**
	 * Actualiza AsesoriaDirecta
	 * 
	 * @param AsesoriaDirecta
	 */
	public void update(AsesoriaDirecta asesoriaDirecta) {
		try {
			logger.debug("Update Networks by id: " + asesoriaDirecta.getIdAsesoriaDirecta());
			asesoriaDirectaMapper.updateById(asesoriaDirecta);
		} catch (DataAccessException e) {
			throw new MineducException("Error al actualizar AsesoriaDirecta", e);
		}
	}

	public AsesoriaDirecta getByIdAsignacionSupervisor(Long idAsignacionSupervisor) {
		try {
			logger.debug("Get Assign by idAsignacionSupervisor: " + idAsignacionSupervisor);
			return asesoriaDirectaMapper.getByIdAsignacionSupervisor(idAsignacionSupervisor);
		} catch (DataAccessException e) {
			throw new MineducException("Error al obtener AsesoriaDirecta", e);
		}
	}
}
