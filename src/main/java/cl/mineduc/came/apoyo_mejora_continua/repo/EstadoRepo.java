package cl.mineduc.came.apoyo_mejora_continua.repo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import cl.mineduc.came.apoyo_mejora_continua.mappers.EstadoMapper;
import cl.mineduc.came.apoyo_mejora_continua.modelo.Estado;
import cl.mineduc.framework2.exceptions.MineducException;

@Repository
public class EstadoRepo {
	
	private static Logger logger = LogManager.getLogger(EstadoRepo.class);
	
	@Autowired
	private EstadoMapper estadoMapper;
	
	/**
	 * 
	 * @param estado
	 */
	public void insertarEstado(Estado estado) {
		try {
			logger.debug("Insertando estado");
			estadoMapper.insert(estado);
		} catch (DataAccessException e) {
			throw new MineducException("Error al insertar estado", e);
		}
	}

}
