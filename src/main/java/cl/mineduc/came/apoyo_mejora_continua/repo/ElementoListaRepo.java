package cl.mineduc.came.apoyo_mejora_continua.repo;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import cl.mineduc.came.apoyo_mejora_continua.mappers.ElementoListaMapper;
import cl.mineduc.came.apoyo_mejora_continua.modelo.ElementoLista;
import cl.mineduc.framework2.exceptions.MineducException;

@Repository
public class ElementoListaRepo {
	
	private static Logger logger = LogManager.getLogger(ElementoListaRepo.class);
	
	@Autowired
	private ElementoListaMapper elementoMapper;
	
	public void insertElemento(ElementoLista elemento) {
		try {
			logger.debug("Insertando elemento");
			elementoMapper.insert(elemento);
		} catch (DataAccessException e) {
			throw new MineducException("Error al insertar elemento", e);
		}
	}
	
	public void deleteById(Long id) {
		try {
			logger.debug("Eliminar elemento por id" + id);
			elementoMapper.deleteByPrimaryKey(id);
		} catch (DataAccessException e) {
			throw new MineducException("Error al eliminar elemento", e);
		}
	}
	
	public ElementoLista getElementById(Long id) {
		try {
			logger.debug("Obtener elemento por id" + id);
			return elementoMapper.getById(id);
		} catch (DataAccessException e) {
			throw new MineducException("Error al obtener el elemento", e);
		}
	}
	
	public int updateElementById(ElementoLista elemento) {
		try {
			logger.debug("actualizar elemento: " + elemento.toString());
			return elementoMapper.updateById(elemento);
		} catch (DataAccessException e) {
			throw new MineducException("Error al actualizar el elemento", e);
		}
	}
	
	public List<ElementoLista> getElementsByIdLista(Long id) {
		try {
			logger.debug("Obtener elementos por id lista" + id);
			return elementoMapper.getByIdLista(id);
		} catch (DataAccessException e) {
			throw new MineducException("Error al obtener elementos por id Lista", e);
		}
	}
	
	public List<ElementoLista> getAllElements() {
		try {
			return elementoMapper.getAll();
		} catch (DataAccessException e) {
			throw new MineducException("Error al obtener todos los elementos", e);
		}
	}
	
	

}
