package cl.mineduc.came.apoyo_mejora_continua.repo;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import cl.mineduc.came.apoyo_mejora_continua.mappers.RedSupervisorMapper;
import cl.mineduc.came.apoyo_mejora_continua.modelo.RedSupervisor;
import cl.mineduc.framework2.exceptions.MineducException;

@Repository
public class RedSupervisorRepo {
	private static Logger logger = LogManager.getLogger(RedSupervisorRepo.class);
	
	@Autowired
	private RedSupervisorMapper supervisorMapper;
	
	
	/**
	 * Inserta red supervisor
	 * 
	 * @param establecimiento
	 */
	public void insert(RedSupervisor supervisor) {
		try {
			logger.debug("Insert Network Supervisor");
			supervisorMapper.insert(supervisor);
		} catch (DataAccessException e) {
			throw new MineducException("Error al insertar red supervisor", e);
		}
	}
	
	/**
	 * Elimina red supervisor por id
	 * 
	 * @param id
	 */
	public void deleteById(Long id) {
		try {
			logger.debug("Delete Network Supervisor by id: " + id);
			supervisorMapper.deleteById(id);
		} catch (DataAccessException e) {
			throw new MineducException("Error al eliminar red supervisor", e);
		}
	}
	
	/**
	 * Obtiene red supervisor por id
	 * 
	 * @param id
	 * @return
	 */
	public RedSupervisor getById(Long id) {
		try {
			logger.debug("Get Network Supervisor by id: " + id);
			return supervisorMapper.getById(id);
		} catch (DataAccessException e) {
			throw new MineducException("Error al obtener red supervisor", e);
		}
	}
	
	/**
	 * Obtiene todas las redes supervisores
	 * 
	 * @return
	 */
	public List<RedSupervisor> getAll() {
		try {
			logger.debug("Get all Networks supervisor");
			return supervisorMapper.getAll();
		} catch (DataAccessException e) {
			throw new MineducException("Error al obtener todas las redes supervisor", e);
		}
	}
	
	/**
	 * Actualiza red supervisor
	 * 
	 * @param supervisor
	 * 
	 */
	public void updateById(RedSupervisor supervisor) {
		try {
			logger.debug("Update Network Supervisor by id: " + supervisor.getIdRed());
			supervisorMapper.updateById(supervisor);
		} catch (DataAccessException e) {
			throw new MineducException("Error al actualizar red supervisor", e);
		}
	}

	public List<RedSupervisor> getByIdRed(Long idRed) {
		try {
			logger.debug("Get all Red Supervisor by Id red");
			return supervisorMapper.getByIdRed(idRed);
		} catch (DataAccessException e) {
			throw new MineducException("Error al obtener todas las redes supervisores", e);
		}
	}

}
