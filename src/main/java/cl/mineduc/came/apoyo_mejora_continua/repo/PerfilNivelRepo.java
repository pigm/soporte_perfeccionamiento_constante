package cl.mineduc.came.apoyo_mejora_continua.repo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import cl.mineduc.came.apoyo_mejora_continua.mappers.PerfilNivelMapper;
import cl.mineduc.came.apoyo_mejora_continua.modelo.PerfilNivel;
import cl.mineduc.framework2.exceptions.MineducException;

@Repository
public class PerfilNivelRepo {
	
	private static Logger logger = LogManager.getLogger(PerfilNivelRepo.class);
	
	@Autowired
	private PerfilNivelMapper perfilNivelMapper;
	
	/**
	 * 
	 * @param perfilNivel
	 */
	public void insertarPerfilNivel(PerfilNivel perfilNivel) {
		try {
			logger.debug("Insert nivel");
			perfilNivelMapper.insert(perfilNivel);
		} catch (DataAccessException e) {
			throw new MineducException("Error al insertar perfil", e);
		}
	}

}
