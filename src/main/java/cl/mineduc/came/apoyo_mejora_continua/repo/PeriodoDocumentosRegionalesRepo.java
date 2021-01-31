package cl.mineduc.came.apoyo_mejora_continua.repo;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import cl.mineduc.came.apoyo_mejora_continua.mappers.PeriodoDocumentosRegionalesMapper;
import cl.mineduc.came.apoyo_mejora_continua.modelo.PeriodoDocumentosRegionales;
import cl.mineduc.framework2.exceptions.MineducException;

@Repository
public class PeriodoDocumentosRegionalesRepo {
	
	private static Logger logger = LogManager.getLogger(PeriodoDocumentosRegionalesRepo.class);

	@Autowired
	private PeriodoDocumentosRegionalesMapper regionalMapper;
	
	
	/**
	 * Inserta documento regional
	 * 
	 * @param regional
	 */
	public void insertDocumentsregional(PeriodoDocumentosRegionales regional) {
		try {
			logger.debug("Insert Document Regional");
			regionalMapper.insert(regional);
		} catch (DataAccessException e) {
			throw new MineducException("Error al insertar documento regional", e);
		}
	}
	
	/**
	 * Elimina documento regional por id
	 * 
	 * @param id
	 */
	public void deleteDocumentsRegionalById(Long id) {
		try {
			logger.debug("Delete Document Regional by id: " + id);
			regionalMapper.deleteById(id);
		} catch (DataAccessException e) {
			throw new MineducException("Error al eliminar documento regional", e);
		}
	}
	
	/**
	 * Obtiene documento regional por id
	 * 
	 * @param id
	 * @return
	 */
	public PeriodoDocumentosRegionales getDocumentsRegionalById(Long id) {
		try {
			logger.debug("Get Document Regional by id: " + id);
			return regionalMapper.getById(id);
		} catch (DataAccessException e) {
			throw new MineducException("Error al obtener documento regional", e);
		}
	}
	
	/**
	 * Obtiene todos los documentos Regionales
	 * 
	 * @param id
	 * @return
	 */
	public List<PeriodoDocumentosRegionales> getAllDocumentsRegionals() {
		try {
			logger.debug("Get all Document Regionals");
			return regionalMapper.getAll();
		} catch (DataAccessException e) {
			throw new MineducException("Error al obtener todos los documentos Regionals", e);
		}
	}
	
	/**
	 * Actualiza documento regional
	 * 
	 * @param regional
	 */
	public void updateDocumentsRegional(PeriodoDocumentosRegionales regional) {
		try {
			logger.debug("Update Document Regional by id: " + regional.getIdPeriodoDocumentosRegionales());
			regionalMapper.updateById(regional);
		} catch (DataAccessException e) {
			throw new MineducException("Error al actualizar documento regional", e);
		}
	}

}
