package cl.mineduc.came.apoyo_mejora_continua.repo;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;


import cl.mineduc.came.apoyo_mejora_continua.mappers.SesionMapper;
import cl.mineduc.came.apoyo_mejora_continua.modelo.Sesion;
import cl.mineduc.framework2.exceptions.MineducException;

@Repository
public class SesionRepo {
	
	private static Logger logger = LogManager.getLogger(SesionRepo.class);
	
	@Autowired
	private SesionMapper sesionMapper;
	
	
	/**
	 * Inserta Sesion
	 * 
	 * @param SesionDirecta
	 */
	public void insert(Sesion sesion) {
		try {
			logger.debug("Insert Sesion");
			sesionMapper.insert(sesion);
		} catch (DataAccessException e) {
			throw new MineducException("Error al insertar SesionDirecta", e);
		}
	}
	
	/**
	 * Elimina Sesion por id
	 * 
	 * @param id
	 */
	public void delete(Long id) {
		try {
			logger.debug("Delete Sesion by id: " + id);
			sesionMapper.deleteById(id);
		} catch (DataAccessException e) {
			throw new MineducException("Error al eliminar SesionDirecta", e);
		}
	}
	
	/**
	 * Obtiene Sesion por id
	 * 
	 * @param id
	 * @return
	 */
	public Sesion getById(Long id) {
		try {
			logger.debug("Get Sesion by id: " + id);
			return sesionMapper.getById(id);
		} catch (DataAccessException e) {
			throw new MineducException("Error al obtener SesionDirecta", e);
		}
	}
	
	/**
	 * Obtiene todas las Sesion
	 * 
	 * @param id
	 * @return
	 */
	public List<Sesion> getAll() {
		try {
			logger.debug("Get all Sessions");
			return sesionMapper.getAll();
		} catch (DataAccessException e) {
			throw new MineducException("Error al obtener todas las SesionDirectaes", e);
		}
	}
	
	public List<Sesion> getByIdAsesoria(Long idAsesoria) {
		try {
			logger.debug("Get all Networks");
			return sesionMapper.getByIdAsesoria(idAsesoria);
		} catch (DataAccessException e) {
			throw new MineducException("Error al obtener todas las SesionDirectaes", e);
		}
	}

	/**
	 * Actualiza Sesion
	 * 
	 * @param Sesion
	 */
	public void update(Sesion sesion) {
		try {
			logger.debug("Update Networks by id: " + sesion.getIdSesion());
			sesionMapper.updateById(sesion);
		} catch (DataAccessException e) {
			throw new MineducException("Error al actualizar SesionDirecta", e);
		}
	}
}
