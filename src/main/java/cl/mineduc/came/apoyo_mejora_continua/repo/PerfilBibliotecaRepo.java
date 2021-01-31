package cl.mineduc.came.apoyo_mejora_continua.repo;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import cl.mineduc.came.apoyo_mejora_continua.mappers.PerfilBibliotecaMapper;
import cl.mineduc.came.apoyo_mejora_continua.modelo.PerfilBiblioteca;
import cl.mineduc.framework2.exceptions.MineducException;

@Repository
public class PerfilBibliotecaRepo {
	
	private static Logger logger = LogManager.getLogger(PerfilBibliotecaRepo.class);

	@Autowired
	private PerfilBibliotecaMapper perfilBibliotecaMapper;
	
	
	public void insertPerfilBiblioteca(PerfilBiblioteca perfilBiblioteca) {
		try {
			logger.debug("Insert perfilBiblioteca");
			perfilBibliotecaMapper.insert(perfilBiblioteca);
		} catch (DataAccessException e) {
			throw new MineducException("Error al insertar perfilBiblioteca", e);
		}
	}

	public void deletePerfilBibliotecaById(Long idPerfilBiblioteca) {
		try {
			logger.debug("Delete perfil Biblioteca by id: " + idPerfilBiblioteca);
			perfilBibliotecaMapper.deleteById(idPerfilBiblioteca);
		} catch (DataAccessException e) {
			throw new MineducException("Error al eliminar perfil Biblioteca", e);
		}
	}
	
	
	public PerfilBiblioteca getPerfilBibliotecaById(Long idPerfilBiblioteca) {
		try {
			logger.debug("Get perfil Biblioteca by id: " + idPerfilBiblioteca);
			return perfilBibliotecaMapper.getById(idPerfilBiblioteca);
		} catch (DataAccessException e) {
			throw new MineducException("Error al obtener perfil Biblioteca", e);
		}
	}
	
	public List<PerfilBiblioteca> getByIdPeriodo(Long idPeriodo) {
		try {
			logger.debug("Get perfil Biblioteca by id: " + idPeriodo);
			return perfilBibliotecaMapper.getByIdPeriodo(idPeriodo);
		} catch (DataAccessException e) {
			throw new MineducException("Error al obtener perfil Biblioteca", e);
		}
	}
	
	public List<PerfilBiblioteca> getAllPerfilBiblioteca() {
		try {
			logger.debug("Get all perfil Biblioteca");
			return perfilBibliotecaMapper.getAll();
		} catch (DataAccessException e) {
			throw new MineducException("Error al obtener todos los perfil Biblioteca", e);
		}
	}
	

	public void updatePerfilBiblioteca(PerfilBiblioteca perfilBiblioteca) {
		try {
			logger.debug("Update perfil Biblioteca by id: " + perfilBiblioteca.getIdPerfilBiblioteca());
			perfilBibliotecaMapper.updateById(perfilBiblioteca);
		} catch (DataAccessException e) {
			throw new MineducException("Error al actualizar perfil Biblioteca", e);
		}
	}
}