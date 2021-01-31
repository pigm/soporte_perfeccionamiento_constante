package cl.mineduc.came.apoyo_mejora_continua.repo;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cl.mineduc.came.apoyo_mejora_continua.mappers.AsignacionSupervisorMapper;
import cl.mineduc.came.apoyo_mejora_continua.modelo.AsignacionSupervisor;
import cl.mineduc.framework2.exceptions.MineducException;

@Repository
public class AsignacionSupervisorRepo {
	
	private static Logger logger = LogManager.getLogger(AsignacionSupervisorRepo.class);
	
	@Autowired
	private AsignacionSupervisorMapper asignacionMapper;
	
	public List<AsignacionSupervisor> getAll() {
		try {
			return asignacionMapper.getAll();
		} catch (DataAccessException e) {
            logger.error("Error al obtener lista de AsignacionSupervisor", e);
            throw new MineducException("Error al obtener lista de AsignacionSupervisor", e);
		}
    }
	
	/**
	 * Inserta asignacion supervisor
	 * @param asi
	 * @return
	 */
	@Transactional
	public int insert(AsignacionSupervisor asi) {
		try {
			return asignacionMapper.insert(asi);
		} catch (DataAccessException e) {
            logger.error("Error al insertar Asignacion Supervisor", e);
            throw new MineducException("Error al insertar Asignacion Supervisor", e);
		}
	}
	
	/**
	 * Elimina Asignacion Supervisor por id
	 * @param idAsignacionSupervisor
	 * @return
	 */
	@Transactional
	public int deleteAssignmentSupervisorById(Long idAsignacionSupervisor) {
		try {
			return asignacionMapper.deleteById(idAsignacionSupervisor);
		} catch (DataAccessException e) {
            logger.error("Error al eliminar Asignacion Supervisor por id: " + idAsignacionSupervisor, e);
            throw new MineducException("Error al eliminar Asignacion Supervisor por id: " + idAsignacionSupervisor, e);
		}
	}
	
	/**
	 * Obtiene asignacion de supervisor por id
	 * @param id
	 * @return
	 */
	public AsignacionSupervisor getAssignmentSupervisorById(Long id) {
		try {
			return asignacionMapper.getById(id);
		} catch (DataAccessException e) {
            logger.error("Error al obtener Asignacion Supervisor por id: " + id, e);
            throw new MineducException("Error al obtener Asignacion Supervisor por id: " + id, e);
		}
	}
	
	/**
	 * Actualiza asignacion de supervisor
	 * @param asignacion
	 * @return
	 */
	@Transactional
	public int updateAssignmentSupervisor(AsignacionSupervisor asignacion) {
		try {
			return asignacionMapper.updateById(asignacion);
		} catch (DataAccessException e) {
            logger.error("Error al actualizar Asignacion Supervisor", e);
            throw new MineducException("Error al actualizar Asignacion Supervisor", e);
		}
	}

}
