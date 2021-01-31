package cl.mineduc.came.apoyo_mejora_continua.repo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import cl.mineduc.came.apoyo_mejora_continua.mappers.SubModuloMapper;
import cl.mineduc.came.apoyo_mejora_continua.modelo.SubModulo;
import cl.mineduc.framework2.exceptions.MineducException;

@Repository("subModuloRepo")
public class SubModuloRepo {
	
	private static Logger logger = LogManager.getLogger(SubModuloRepo.class);
	
	@Autowired
	private SubModuloMapper subModuloMapper;
	
	/**
	 * 
	 * @param subModulo
	 */
	public void insertarSubModulo(SubModulo subModulo) {
		try {
			logger.debug("Insertando sub modulo");
			subModuloMapper.insert(subModulo);
		} catch (DataAccessException e) {
			throw new MineducException("Error al insertar submodulo", e);
		}
	}

	public SubModulo getSubModuloByUrl(String url) {
		try {
			logger.debug("Obteniendo sub modulo");
			return subModuloMapper.getSubModuloByUrl(url);
		} catch (DataAccessException e) {
			throw new MineducException("Error al obtener submodulo", e);
		}
	}
	

	
}
