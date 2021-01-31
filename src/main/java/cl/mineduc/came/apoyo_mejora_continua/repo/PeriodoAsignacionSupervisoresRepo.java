package cl.mineduc.came.apoyo_mejora_continua.repo;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import cl.mineduc.came.apoyo_mejora_continua.mappers.PeriodoAsignacionSupervisorMapper;
import cl.mineduc.came.apoyo_mejora_continua.modelo.PeriodoAsignacionSupervisores;
import cl.mineduc.framework2.exceptions.MineducException;

@Repository
public class PeriodoAsignacionSupervisoresRepo {
	
	private static Logger logger = LogManager.getLogger(PeriodoAsignacionSupervisoresRepo.class);

	@Autowired
	private PeriodoAsignacionSupervisorMapper supervisoresMapper;
	
	
	/**
	 * Inserta Asignacion supervisor
	 * 
	 * @param asignaciones
	 */
	public void insertAssignmentSupervisor(PeriodoAsignacionSupervisores asignaciones) {
		try {
			logger.debug("Insert assignment supervisor");
			supervisoresMapper.insert(asignaciones);
		} catch (DataAccessException e) {
			throw new MineducException("Error al insertar asignacion supervisor", e);
		}
	}
	
	/**
	 * Elimina Asignacion supervisor por id
	 * 
	 * @param id
	 */
	public void deleteAssignmentSupervisorById(Long id) {
		try {
			logger.debug("Delete assignment supervisor by id: " + id);
			supervisoresMapper.deleteById(id);
		} catch (DataAccessException e) {
			throw new MineducException("Error al eliminar Asignacion supervisor", e);
		}
	}
	
	/**
	 * Obtiene asignacion supervisor por id
	 * 
	 * @param id
	 * @return
	 */
	public PeriodoAsignacionSupervisores getAssignmentSupervisorById(Long id) {
		try {
			logger.debug("Get assignment supervisor by id: " + id);
			return supervisoresMapper.getById(id);
		} catch (DataAccessException e) {
			throw new MineducException("Error al obtener asignacion supervisor", e);
		}
	}
	
	/**
	 * Obtiene todas las asignaciones supervisores
	 * @return
	 */
	public List<PeriodoAsignacionSupervisores> getAllAssignmentSupervisors() {
		try {
			logger.debug("Get all assignment supervisor");
			return supervisoresMapper.getAll();
		} catch (DataAccessException e) {
			throw new MineducException("Error al obtener todos los asignacion supervisor", e);
		}
	}
	
	/**
	 * Actualiza documento provincial
	 * 
	 * @param documents
	 */
	public void updateDocumentsProvincials(PeriodoAsignacionSupervisores asignaciones) {
		try {
			logger.debug("Update Document Provincials by id: " + asignaciones.getIdPeriodoAsignacionSupervisores());
			supervisoresMapper.updateById(asignaciones);
		} catch (DataAccessException e) {
			throw new MineducException("Error al actualizar documento provincial", e);
		}
	}
}
