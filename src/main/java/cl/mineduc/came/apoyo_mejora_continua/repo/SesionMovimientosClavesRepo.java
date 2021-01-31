package cl.mineduc.came.apoyo_mejora_continua.repo;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;


import cl.mineduc.came.apoyo_mejora_continua.mappers.SesionMovimientosClavesMapper;
import cl.mineduc.came.apoyo_mejora_continua.modelo.SesionMovimientosClaves;
import cl.mineduc.framework2.exceptions.MineducException;

@Repository
public class SesionMovimientosClavesRepo {
	
	private static Logger logger = LogManager.getLogger(SesionMovimientosClavesRepo.class);
	
	@Autowired
	private SesionMovimientosClavesMapper sesionMovimientosClavesMapper;
	
	
	/**
	 * Inserta SesionMovimientosClaves
	 * 
	 * @param SesionMovimientosClavesDirecta
	 */
	public void insert(SesionMovimientosClaves sesionMovimientosClaves) {
		try {
			logger.debug("Insert SesionMovimientosClaves");
			sesionMovimientosClavesMapper.insert(sesionMovimientosClaves);
		} catch (DataAccessException e) {
			throw new MineducException("Error al insertar SesionMovimientosClavesDirecta", e);
		}
	}
	
	/**
	 * Elimina SesionMovimientosClaves por id
	 * 
	 * @param id
	 */
	public void delete(Long id) {
		try {
			logger.debug("Delete SesionMovimientosClaves by id: " + id);
			sesionMovimientosClavesMapper.deleteById(id);
		} catch (DataAccessException e) {
			throw new MineducException("Error al eliminar SesionMovimientosClavesDirecta", e);
		}
	}
	
	/**
	 * Obtiene SesionMovimientosClaves por id
	 * 
	 * @param id
	 * @return
	 */
	public SesionMovimientosClaves getById(Long id) {
		try {
			logger.debug("Get SesionMovimientosClaves by id: " + id);
			return sesionMovimientosClavesMapper.getById(id);
		} catch (DataAccessException e) {
			throw new MineducException("Error al obtener SesionMovimientosClavesDirecta", e);
		}
	}
	
	/**
	 * Obtiene todas las SesionMovimientosClaves
	 * 
	 * @param id
	 * @return
	 */
	public List<SesionMovimientosClaves> getAll() {
		try {
			logger.debug("Get all Sessions");
			return sesionMovimientosClavesMapper.getAll();
		} catch (DataAccessException e) {
			throw new MineducException("Error al obtener todas las SesionMovimientosClavesDirectaes", e);
		}
	}
	
	public List<SesionMovimientosClaves> getByIdSesionFoco(Long idSesionFoco) {
		try {
			logger.debug("Get all Networks");
			return sesionMovimientosClavesMapper.getByIdSesionFoco(idSesionFoco);
		} catch (DataAccessException e) {
			throw new MineducException("Error al obtener todas las SesionMovimientosClavesDirectaes", e);
		}
	}

	/**
	 * Actualiza SesionMovimientosClaves
	 * 
	 * @param SesionMovimientosClaves
	 */
	public void update(SesionMovimientosClaves sesionMovimientosClaves) {
		try {
			logger.debug("Update SesionMovimientosClaves by id: " + sesionMovimientosClaves.getIdSesionMovimientosClaves());
			sesionMovimientosClavesMapper.updateById(sesionMovimientosClaves);
		} catch (DataAccessException e) {
			throw new MineducException("Error al actualizar SesionMovimientosClavesDirecta", e);
		}
	}
}
