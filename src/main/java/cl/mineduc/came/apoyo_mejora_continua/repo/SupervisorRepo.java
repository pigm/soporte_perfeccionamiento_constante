package cl.mineduc.came.apoyo_mejora_continua.repo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import cl.mineduc.came.apoyo_mejora_continua.mappers.SupervisorMapper;
import cl.mineduc.came.apoyo_mejora_continua.modelo.Supervisor;
import cl.mineduc.framework2.exceptions.MineducException;

@Repository
public class SupervisorRepo {
	
	@Autowired
	private SupervisorMapper supervisorMapper;
	
	/**
	 * 
	 * @param supervisor
	 */
	public void insert(Supervisor supervisor) {
		try {
			supervisorMapper.insert(supervisor);
		} catch (DataAccessException e) {
			throw new MineducException("Error al insertar Supervisor", e);
		}
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public Supervisor getSupervisorById(Long id) {
		try {
			return supervisorMapper.getById(id);
		} catch (DataAccessException e) {
			throw new MineducException("Error al consultar Supervisor", e);
		}
	}
	
	public void updateSupervisor(Supervisor supervisor) {
		try {
			supervisorMapper.updateById(supervisor);
		} catch (DataAccessException e) {
			throw new MineducException("Error al actualizar Supervisor", e);
		}
	}
	
	/**
	 * 
	 * @param idUsuario
	 * @return
	 */
	public Supervisor getSupervisorByIdUsuario(Long idUsuario) {
		try {
			return supervisorMapper.getByIdUsuario(idUsuario);
		} catch (DataAccessException e) {
			throw new MineducException("Error al consultar Supervisor por id usuario", e);
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public List<Supervisor> getAll() {
		try {
			return supervisorMapper.getAll();
		} catch (DataAccessException e) {
			throw new MineducException("Error al obtener los supervisores", e);
		}
	}
}
