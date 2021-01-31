package cl.mineduc.came.apoyo_mejora_continua.repo;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import cl.mineduc.came.apoyo_mejora_continua.mappers.FocoMapper;
import cl.mineduc.came.apoyo_mejora_continua.modelo.Foco;
import cl.mineduc.framework2.exceptions.MineducException;

@Repository
public class FocoRepo {

	private static Logger logger = LogManager.getLogger(FocoRepo.class);

	@Autowired
	private FocoMapper focoMapper;

	public void insertFoco(Foco foco) {
		try {
			logger.debug("Insertando Foco");
			focoMapper.insert(foco);
		} catch (DataAccessException e) {
			throw new MineducException("Error al insertar foco", e);
		}
	}
	
	public void deleteById(Long id) {
		try {
			logger.debug("Elimina Foco " + id);
			focoMapper.deleteById(id);
		} catch (DataAccessException e) {
			throw new MineducException("Error al elimina foco: " + id, e);
		}
	}
	
	public List<Foco> getAllFocos() {
		try {
			logger.debug("Obtiene todos los Foco");
			return focoMapper.getAll();
		} catch (DataAccessException e) {
			throw new MineducException("Error al obtener todos los focos", e);
		}
	}
	
	public Foco getFocoById(Long id) {
		try {
			logger.debug("Obtiene Foco por id: " + id);
			return focoMapper.getFocoById(id);
		} catch (DataAccessException e) {
			throw new MineducException("Error al obtener foco: " + id, e);
		}
	}
	
	public void updateFocoById(Foco foco) {
		try {
			logger.debug("Actualiza Foco por id: " + foco.getIdFoco());
			focoMapper.updateById(foco);
		} catch (DataAccessException e) {
			throw new MineducException("Error al obtener foco: " + foco.getIdFoco(), e);
		}
	}

}
