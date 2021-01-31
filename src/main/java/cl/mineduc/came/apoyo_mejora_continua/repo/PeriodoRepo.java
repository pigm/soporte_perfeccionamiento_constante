package cl.mineduc.came.apoyo_mejora_continua.repo;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import cl.mineduc.came.apoyo_mejora_continua.mappers.PeriodoMapper;
import cl.mineduc.came.apoyo_mejora_continua.modelo.Periodo;
import cl.mineduc.framework2.exceptions.MineducException;

@Repository
public class PeriodoRepo {
	
	private static Logger logger = LogManager.getLogger(PeriodoRepo.class);
	
	@Autowired
	private PeriodoMapper periodoMapper;
	
	/**
	 * Inserta periodo
	 * @param periodo
	 */
	public void insertPeriod(Periodo periodo) {
		try {
			logger.debug("Insert Periodo");
			periodoMapper.insert(periodo);
		} catch (DataAccessException e) {
			throw new MineducException("Error al insertar periodo", e);
		}
	}
	
	/**
	 * Eliminar periodo por id
	 * @param id
	 */
	public void deletePeriodByid(Long id) {
		try {
			logger.debug("Delete Periodo by id: " + id);
			periodoMapper.deleteById(id);
		} catch (DataAccessException e) {
			throw new MineducException("Error al eliminar periodo", e);
		}
	}
	
	/**
	 * Obtener todos los periodos
	 * @return
	 */
	public List<Periodo> getAllPeriods() {
		try {
			logger.debug("Get all periods ");
			return periodoMapper.getAll();
		} catch (DataAccessException e) {
			throw new MineducException("Error al obtener periodos", e);
		}
	}
	
	/**
	 * Obtener periodo por id
	 * @param id
	 * @return
	 */
	public Periodo getPeriodById(Long id) {
		try {
			logger.debug("Get periods by id " + id);
			return periodoMapper.getPeriodoById(id);
		} catch (DataAccessException e) {
			throw new MineducException("Error al obtener periodo", e);
		}
	}

	/**
	 * Obtiene periodo según año
	 * @param period
	 * @return
	 */
	public Periodo getPeriodByYear(int period) {
		try {
			logger.debug("Get periods by year " + period);
			return periodoMapper.getPeriodoByYear(period);
		} catch (DataAccessException e) {
			throw new MineducException("Error al obtener periodo por año", e);
		}
	}
}
