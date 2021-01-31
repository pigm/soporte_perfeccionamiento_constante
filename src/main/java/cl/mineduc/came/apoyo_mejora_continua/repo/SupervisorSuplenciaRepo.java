package cl.mineduc.came.apoyo_mejora_continua.repo;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import cl.mineduc.came.apoyo_mejora_continua.mappers.SupervisorSuplenciaMapper;
import cl.mineduc.came.apoyo_mejora_continua.modelo.SupervisorSuplencia;
import cl.mineduc.framework2.exceptions.MineducException;

@Repository
public class SupervisorSuplenciaRepo {
    private static Logger logger = LogManager.getLogger(SupervisorSuplenciaRepo.class);

    @Autowired
    private SupervisorSuplenciaMapper supervisorSuplenciaMapper;


    public void insert(SupervisorSuplencia record) {
		try {
			logger.debug("Insertando SupervisorSuplencia");
			supervisorSuplenciaMapper.insert(record);
		} catch (DataAccessException e) {
			throw new MineducException("Error al insertar SupervisorSuplencia", e);
		}
	}
	
	public void deleteById(Long id) {
		try {
			logger.debug("Eliminar SupervisorSuplencia por id" + id);
			supervisorSuplenciaMapper.deleteById(id);
		} catch (DataAccessException e) {
			throw new MineducException("Error al eliminar SupervisorSuplencia", e);
		}
	}

    public List<SupervisorSuplencia> get() {
		try {
			return supervisorSuplenciaMapper.getAll();
		} catch (DataAccessException e) {
            logger.error("Error al obtener SupervisorSuplencia", e);
            throw new MineducException("Error al obtener SupervisorSuplencia", e);
		}
    }

    public SupervisorSuplencia getById(Long id) {
		try {
			logger.debug("Obtener SupervisorSuplencia por id" + id);
			return supervisorSuplenciaMapper.getById(id);
		} catch (DataAccessException e) {
			throw new MineducException("Error al obtener el SupervisorSuplencia", e);
		}
	}

    public int update(SupervisorSuplencia record) {
		try {
			logger.debug("actualizar SupervisorSuplencia: " + record.toString());
			return supervisorSuplenciaMapper.updateById(record);
		} catch (DataAccessException e) {
			throw new MineducException("Error al actualizar el SupervisorSuplencia", e);
		}
	}

	public List<SupervisorSuplencia> getByIdPeriodo(Long idPeriodo) {
		try {
			return supervisorSuplenciaMapper.getByIdPeriodo(idPeriodo);
		} catch (DataAccessException e) {
			logger.error("Error al obtener SupervisorSuplencia", e);
			throw new MineducException("Error al obtener SupervisorSuplencia", e);
		}
	}


}
