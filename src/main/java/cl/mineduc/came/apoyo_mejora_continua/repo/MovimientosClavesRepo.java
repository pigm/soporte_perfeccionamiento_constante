package cl.mineduc.came.apoyo_mejora_continua.repo;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import cl.mineduc.came.apoyo_mejora_continua.mappers.MovimientosClavesMapper;
import cl.mineduc.came.apoyo_mejora_continua.modelo.MovimientosClaves;
import cl.mineduc.framework2.exceptions.MineducException;

@Repository
public class MovimientosClavesRepo {
	
	private static Logger logger = LogManager.getLogger(MovimientosClavesRepo.class);
	
	@Autowired
	private MovimientosClavesMapper movimientosMapper;
	
	public void insertKeyMov(MovimientosClaves mov) {
		try {
			logger.debug("Insertando Movimiento Clave");
			movimientosMapper.insert(mov);
		} catch (DataAccessException e) {
			throw new MineducException("Error al insertar Movimiento Clave", e);
		}
	}
	
	public void deleteById(Long id) {
		try {
			logger.debug("Elimina Movimiento Clave " + id);
			movimientosMapper.deleteById(id);
		} catch (DataAccessException e) {
			throw new MineducException("Error al elimina  Movimiento Clave: " + id, e);
		}
	}
	
	public List<MovimientosClaves> getAllFocos() {
		try {
			logger.debug("Obtiene todos los Movimientos Claves");
			return movimientosMapper.getAll();
		} catch (DataAccessException e) {
			throw new MineducException("Obtiene todos los Movimientos Claves", e);
		}
	}
	
	public MovimientosClaves getKeyMovById(Long id) {
		try {
			logger.debug("Obtiene Movimiento Clave por id: " + id);
			return movimientosMapper.getById(id);
		} catch (DataAccessException e) {
			throw new MineducException("Obtiene Movimiento Clave por id: " + id, e);
		}
	}
	
	public MovimientosClaves getByName(String name) {
		try {
			logger.debug("Obtiene Movimiento Clave por name: " + name);
			return movimientosMapper.getByName(name);
		} catch (DataAccessException e) {
			throw new MineducException("Obtiene Movimiento Clave por name: " + name, e);
		}
	}

	public void updateKeyMovById(MovimientosClaves mov) {
		try {
			logger.debug("Actualiza Movimientos claves por id: " + mov.getIdMovimientosClaves());
			movimientosMapper.updateById(mov);
		} catch (DataAccessException e) {
			throw new MineducException("Actualiza Movimientos claves por id: " + mov.getIdMovimientosClaves(), e);
		}
	}

}
