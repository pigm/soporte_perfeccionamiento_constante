package cl.mineduc.came.apoyo_mejora_continua.repo;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import cl.mineduc.came.apoyo_mejora_continua.mappers.BitacoraMapper;
import cl.mineduc.came.apoyo_mejora_continua.modelo.Bitacora;
import cl.mineduc.framework2.exceptions.MineducException;

@Repository
public class BitacoraRepo {
	
	private static Logger logger = LogManager.getLogger(BitacoraRepo.class);
	
	@Autowired
	private BitacoraMapper bitacoraMapper;
	
	public void insert(Bitacora mov) {
		try {
			logger.debug("Insertando Bitacora");
			bitacoraMapper.insert(mov);
		} catch (DataAccessException e) {
			throw new MineducException("Error al insertar Bitacora", e);
		}catch (Exception e) {
			throw new MineducException("Error general al insertar Bitacora", e);
		}
		logger.debug("Registro en Bitacora Insertado");
	}
	
	public void deleteById(Long id) {
		try {
			logger.debug("Elimina Bitacora " + id);
			bitacoraMapper.deleteById(id);
		} catch (DataAccessException e) {
			throw new MineducException("Error al elimina  Bitacora: " + id, e);
		}
	}
	
	public List<Bitacora> getAll() {
		try {
			logger.debug("Obtiene todos los Bitacora Claves");
			return bitacoraMapper.getAll();
		} catch (DataAccessException e) {
			throw new MineducException("Obtiene todos los Bitacora Claves", e);
		}
	}
	
	public Bitacora getById(Long id) {
		try {
			logger.debug("Obtiene Bitacora por id: " + id);
			return bitacoraMapper.getById(id);
		} catch (DataAccessException e) {
			throw new MineducException("Obtiene Bitacora por id: " + id, e);
		}
	}
		
	public void updateById(Bitacora bitacora) {
		try {
			logger.debug("Actualiza Bitacora por id: " + bitacora.getIdBitacora());
			bitacoraMapper.updateById(bitacora);
		} catch (DataAccessException e) {
			throw new MineducException("Actualiza Bitacora por id: " + bitacora.getIdBitacora(), e);
		}
	}

}
