package cl.mineduc.came.apoyo_mejora_continua.repo;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import cl.mineduc.came.apoyo_mejora_continua.mappers.PeriodoDocumentosProvincialesMapper;
import cl.mineduc.came.apoyo_mejora_continua.modelo.PeriodoDocumentosProvinciales;
import cl.mineduc.framework2.exceptions.MineducException;

@Repository
public class PeriodoDocumentosProvincialesRepo {
	
	private static Logger logger = LogManager.getLogger(PeriodoDocumentosProvincialesRepo.class);

	@Autowired
	private PeriodoDocumentosProvincialesMapper documentMapper;
	
	
	/**
	 * Inserta documento provincial
	 * 
	 * @param documents
	 */
	public void insertDocumentsProvincials(PeriodoDocumentosProvinciales documents) {
		try {
			logger.debug("Insert Document Provincials");
			documentMapper.insert(documents);
		} catch (DataAccessException e) {
			throw new MineducException("Error al insertar documento provincial", e);
		}
	}
	
	/**
	 * Elimina documento provincial por id
	 * 
	 * @param id
	 */
	public void deleteDocumentsProvincialsById(Long id) {
		try {
			logger.debug("Delete Document Provincials by id: " + id);
			documentMapper.deleteById(id);
		} catch (DataAccessException e) {
			throw new MineducException("Error al eliminar documento provincial", e);
		}
	}
	
	/**
	 * Obtiene documento provincial por id
	 * 
	 * @param id
	 * @return
	 */
	public PeriodoDocumentosProvinciales getDocumentsProvincialsById(Long id) {
		try {
			logger.debug("Get Document Provincials by id: " + id);
			return documentMapper.getById(id);
		} catch (DataAccessException e) {
			throw new MineducException("Error al obtener documento provincial", e);
		}
	}
	
	/**
	 * Obtiene todos los documentos provinciales
	 * 
	 * @param id
	 * @return
	 */
	public List<PeriodoDocumentosProvinciales> getAllDocumentsProvincials() {
		try {
			logger.debug("Get all Document Provincials");
			return documentMapper.getAll();
		} catch (DataAccessException e) {
			throw new MineducException("Error al obtener todos los documentos provinciales", e);
		}
	}
	
	/**
	 * Actualiza documento provincial
	 * 
	 * @param documents
	 */
	public void updateDocumentsProvincials(PeriodoDocumentosProvinciales documents) {
		try {
			logger.debug("Update Document Provincials by id: " + documents.getIdPeriodoDocumentosProvinciales());
			documentMapper.updateById(documents);
		} catch (DataAccessException e) {
			throw new MineducException("Error al actualizar documento provincial", e);
		}
	}
}
