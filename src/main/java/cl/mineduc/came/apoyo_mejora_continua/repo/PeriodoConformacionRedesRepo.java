package cl.mineduc.came.apoyo_mejora_continua.repo;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import cl.mineduc.came.apoyo_mejora_continua.mappers.PeriodoConformacionRedesMapper;
import cl.mineduc.came.apoyo_mejora_continua.modelo.PeriodoConformacionRedes;
import cl.mineduc.framework2.exceptions.MineducException;

@Repository
public class PeriodoConformacionRedesRepo {
	
	private static Logger logger = LogManager.getLogger(PeriodoConformacionRedesRepo.class);

	@Autowired
	private PeriodoConformacionRedesMapper conformacionMapper;
	
	
	/**
	 * Inserta Conformacion redes
	 * 
	 * @param conformacion
	 */
	public void insertNetworkConformation(PeriodoConformacionRedes conformacion) {
		try {
			logger.debug("Insert network conformation");
			conformacionMapper.insert(conformacion);
		} catch (DataAccessException e) {
			throw new MineducException("Error al insertar conformacion de redes", e);
		}
	}
	
	/**
	 * Elimina Conformacion de redes por id
	 * 
	 * @param id
	 */
	public void deleteNetworkConformationById(Long id) {
		try {
			logger.debug("Delete network conformation by id: " + id);
			conformacionMapper.deleteById(id);
		} catch (DataAccessException e) {
			throw new MineducException("Error al eliminar conformacion de redes", e);
		}
	}
	
	/**
	 * Obtiene conformacion de redes por id
	 * 
	 * @param id
	 * @return
	 */
	public PeriodoConformacionRedes getNetworkConformationById(Long id) {
		try {
			logger.debug("Get network conformation by id: " + id);
			return conformacionMapper.getById(id);
		} catch (DataAccessException e) {
			throw new MineducException("Error al obtener conformacion de redes", e);
		}
	}
	
	/**
	 * Obtiene todas las conformaciones de redes
	 * @return
	 */
	public List<PeriodoConformacionRedes> getAllNetworkConformation() {
		try {
			logger.debug("Get all network conformation");
			return conformacionMapper.getAll();
		} catch (DataAccessException e) {
			throw new MineducException("Error al obtener todas las conformaciones de redes", e);
		}
	}
	
	/**
	 * Actualiza conformacion de redes
	 * 
	 * @param conformacion
	 */
	public void updateNetworkConformation(PeriodoConformacionRedes conformacion) {
		try {
			logger.debug("Update network conformation by id: " + conformacion.getIdPeriodoConformacionRedes());
			conformacionMapper.updateById(conformacion);
		} catch (DataAccessException e) {
			throw new MineducException("Error al actualizar conformacion redes", e);
		}
	}
}
