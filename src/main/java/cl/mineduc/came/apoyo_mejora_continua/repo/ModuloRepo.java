package cl.mineduc.came.apoyo_mejora_continua.repo;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import cl.mineduc.came.apoyo_mejora_continua.mappers.ModuloMapper;
import cl.mineduc.came.apoyo_mejora_continua.modelo.Modulo;
import cl.mineduc.framework2.exceptions.MineducException;

@Repository
public class ModuloRepo {
	
	private static Logger logger = LogManager.getLogger(ModuloRepo.class);
	
	@Autowired
	private ModuloMapper moduloMapper;
	
	/**
	 * 
	 * @param modulo
	 */
	public void insertarModulo(Modulo modulo) {
		try {
			logger.debug("Insertando modulo");
			moduloMapper.insert(modulo);
		} catch (DataAccessException e) {
			throw new MineducException("Error al insertar modulo", e);
		}
	}

	/**
	 * 
	 * @param modulo
	 */
	public List<Modulo> obtenerModulos() {
		try {
			logger.debug("Consulta todos los modulos");
			return moduloMapper.getAll();
		} catch (DataAccessException e) {
			throw new MineducException("Error al consultar modulos", e);
		}
	}

	public Modulo getById(Long idModulo){
		try {
			logger.debug("Consulta modulo by Id");
			return moduloMapper.getById(idModulo);
		} catch (DataAccessException e) {
			throw new MineducException("Error al consultar modulo by Id", e);
		}
	}

}
