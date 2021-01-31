package cl.mineduc.came.apoyo_mejora_continua.repo;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import cl.mineduc.came.apoyo_mejora_continua.mappers.DocumentosProvincialesMapper;
import cl.mineduc.came.apoyo_mejora_continua.modelo.DocumentosProvinciales;
import cl.mineduc.framework2.exceptions.MineducException;

@Repository
public class DocumentosProvincialesRepo {

	private static Logger logger = LogManager.getLogger(DocumentosProvincialesRepo.class);

	@Autowired
	private DocumentosProvincialesMapper documentosProvincialesMapper;

	public void insertDocumentosProvinciales(DocumentosProvinciales documentosProvinciales) {
		try {
			logger.debug("Insertando DocumentosProvinciales");
			documentosProvincialesMapper.insert(documentosProvinciales);
		} catch (DataAccessException e) {
			throw new MineducException("Error al insertar DocumentosProvinciales", e);
		}
	}

	public List<DocumentosProvinciales> get() {
		try {
			return documentosProvincialesMapper.getAll();
		} catch (DataAccessException e) {
			throw new MineducException("Error al obtener DocumentosProvincialess", e);
		}
	}

	public List<DocumentosProvinciales> getByPeriod(Long idPeriodo) {
		try {
			return documentosProvincialesMapper.getByPeriod(idPeriodo);
		} catch (DataAccessException e) {
			throw new MineducException("Error al obtener DocumentosProvincialess", e);
		}
	}

	public DocumentosProvinciales getById(Long id) {
		try {
			return documentosProvincialesMapper.getById(id);
		} catch (DataAccessException e) {
			throw new MineducException("Error al obtener DocumentosProvinciales por id", e);
		}
	}

	public void setHasDownloaded(DocumentosProvinciales record){
		try {
			logger.debug("setHasDownloaded");
			documentosProvincialesMapper.setHasDownloaded(record);
		} catch (DataAccessException e) {
			throw new MineducException("Error al ejecutar setHasDownloaded DocumentosProvinciales", e);
		}
	}

}
