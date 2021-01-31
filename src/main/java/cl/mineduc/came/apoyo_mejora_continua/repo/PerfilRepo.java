package cl.mineduc.came.apoyo_mejora_continua.repo;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import cl.mineduc.came.apoyo_mejora_continua.mappers.PerfilMapper;
import cl.mineduc.came.apoyo_mejora_continua.modelo.Perfil;
import cl.mineduc.framework2.exceptions.MineducException;

@Repository("perfilRepo")
public class PerfilRepo {
	
	private static Logger logger = LogManager.getLogger(PerfilRepo.class);
	
	@Autowired
	private PerfilMapper perfilMapper;
	
	/**
	 * 
	 * @param perfil
	 */
	public void insertarPerfil(Perfil perfil) {
		try {
			logger.debug("Insertando perfil: " + perfil.getNombre());
			perfilMapper.insert(perfil);
		} catch (DataAccessException e) {
			throw new MineducException("Error al insertar perfil", e);
		}
	}
	
	public void actualizarPerfil(Perfil perfil) {
		try {
			logger.debug("Actualizando perfil: " + perfil.getNombre());
			perfilMapper.updateById(perfil);
		} catch (DataAccessException e) {
			throw new MineducException("Error al insertar perfil", e);
		}
	}

	public List<Perfil> obtenerPerfiles() {
		try {
			logger.debug("Consulta todos los perfiles");
			return perfilMapper.getAll();
		} catch (DataAccessException e) {
			throw new MineducException("Error al consultar perfiles", e);
		}
	}

	public Perfil obtenerPerfil(long idPerfil){
		try {
			logger.debug("Consulta perfil por id");
			return perfilMapper.getById(idPerfil);
		} catch (DataAccessException e){
			throw new MineducException("Error al obtener perfil", e);
		}
	}

}
