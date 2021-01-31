package cl.mineduc.came.apoyo_mejora_continua.repo;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import cl.mineduc.came.apoyo_mejora_continua.mappers.DocumentosRegionalesMapper;
import cl.mineduc.came.apoyo_mejora_continua.modelo.DocumentosRegionales;
import cl.mineduc.framework2.exceptions.MineducException;

@Repository
public class DocumentosRegionalesRepo {

	private static Logger logger = LogManager.getLogger(DocumentosRegionalesRepo.class);

	@Autowired
	private DocumentosRegionalesMapper documentosRegionalesMapper;

	public void insertDocumentosRegionales(DocumentosRegionales documentosRegionales) {
		try {
			logger.debug("Insertando DocumentosRegionales");
			documentosRegionalesMapper.insert(documentosRegionales);
		} catch (DataAccessException e) {
			throw new MineducException("Error al insertar DocumentosRegionales", e);
		}
	}

	public List<DocumentosRegionales> get() {
		try {
			return documentosRegionalesMapper.getAll();
		} catch (DataAccessException e) {
			throw new MineducException("Error al obtener DocumentosRegionaless", e);
		}
	}

	public List<DocumentosRegionales> getByPeriod(Long idPeriodo) {
		try {
			return documentosRegionalesMapper.getByPeriod(idPeriodo);
		} catch (DataAccessException e) {
			throw new MineducException("Error al obtener DocumentosRegionaless", e);
		}
	}

	public DocumentosRegionales getById(Long id) {
		try {
			return documentosRegionalesMapper.getById(id);
		} catch (DataAccessException e) {
			throw new MineducException("Error al obtener DocumentosRegionales por id", e);
		}
	}

	public void setHasDownloaded(DocumentosRegionales record) {
		try {
			logger.debug("setHasDownloaded");
			documentosRegionalesMapper.setHasDownloaded(record);
		} catch (DataAccessException e) {
			throw new MineducException("Error al ejecutar setHasDownloaded DocumentosRegionales", e);
		}
	}

}
