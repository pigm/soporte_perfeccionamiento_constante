package cl.mineduc.came.apoyo_mejora_continua.repo;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import cl.mineduc.came.apoyo_mejora_continua.mappers.PerfilMenuMapper;
import cl.mineduc.came.apoyo_mejora_continua.modelo.PerfilMenu;
import cl.mineduc.framework2.exceptions.MineducException;

@Repository("perfilMenuRepo")
public class PerfilMenuRepo {
	
	private static Logger logger = LogManager.getLogger(PerfilMenuRepo.class);
	
	@Autowired
	private PerfilMenuMapper perfilMenuMapper;
	
	/**
	 * 
	 * @param perfilMenu
	 */
	public void insertarPerfilMenu(PerfilMenu perfilMenu) {
		try {
			logger.debug("Insertando perfil menu");
			perfilMenuMapper.insert(perfilMenu);
		} catch (DataAccessException e) {
			throw new MineducException("Error al insertar perfil menu", e);
		}
	}

	public void updatePerfilMenu(PerfilMenu perfilMenu) {
		try {
			logger.debug("Update perfil menu");
			perfilMenuMapper.updateById(perfilMenu);
		} catch (DataAccessException e) {
			throw new MineducException("Error al actualizar perfil menu", e);
		}
	}
}
