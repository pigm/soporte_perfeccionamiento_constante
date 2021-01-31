package cl.mineduc.came.apoyo_mejora_continua.repo;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import cl.mineduc.came.apoyo_mejora_continua.mappers.BibliotecaMapper;
import cl.mineduc.came.apoyo_mejora_continua.modelo.Biblioteca;
import cl.mineduc.framework2.exceptions.MineducException;

@Repository
public class BibliotecaRepo {

	private static Logger logger = LogManager.getLogger(BibliotecaRepo.class);

	@Autowired
	private BibliotecaMapper bibliotecaMapper;

	public void insertBiblioteca(Biblioteca biblioteca) {
		try {
			logger.debug("Insertando biblioteca");
			bibliotecaMapper.insert(biblioteca);
		} catch (DataAccessException e) {
			throw new MineducException("Error al insertar biblioteca", e);
		}
	}
	
	public void updateBiblioteca(Biblioteca biblioteca) {
		try {
			logger.debug("Actualizando biblioteca");
			bibliotecaMapper.update(biblioteca);
		} catch (DataAccessException e) {
			throw new MineducException("Error al actualizar biblioteca", e);
		}
	}

	public List<Biblioteca> getAll() {
		try {
			return bibliotecaMapper.getAll();
		} catch (DataAccessException e) {
			throw new MineducException("Error al obtener biblioteca", e);
		}
	}

	public Biblioteca getById(Long idBiblioteca) {
		try {
			return bibliotecaMapper.getById(idBiblioteca);
		} catch (DataAccessException e) {
			throw new MineducException("Error al obtener Biblioteca por id", e);
		}
	}
	
	public void deleteById(Long id) {
		try {
			logger.debug("Elimina biblioteca " + id);
			bibliotecaMapper.deleteById(id);
		} catch (DataAccessException e) {
			throw new MineducException("Error al elimina  Bitacora: " + id, e);
		}
	}
	

}