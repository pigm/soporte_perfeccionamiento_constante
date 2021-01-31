package cl.mineduc.came.apoyo_mejora_continua.repo;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;


import cl.mineduc.came.apoyo_mejora_continua.mappers.SesionRedParticipantesMapper;
import cl.mineduc.came.apoyo_mejora_continua.modelo.SesionRedParticipantes;
import cl.mineduc.framework2.exceptions.MineducException;

@Repository
public class SesionRedParticipantesRepo {
	
	private static Logger logger = LogManager.getLogger(SesionRedParticipantesRepo.class);
	
	@Autowired
	private SesionRedParticipantesMapper sesionRedParticipantesMapper;
	
	
	/**
	 * Inserta SesionRedParticipantes
	 * 
	 * @param SesionRedParticipantesDirecta
	 */
	public void insert(SesionRedParticipantes sesionRedParticipantes) {
		try {
			logger.debug("Insert SesionRedParticipantes");
			sesionRedParticipantesMapper.insert(sesionRedParticipantes);
		} catch (DataAccessException e) {
			throw new MineducException("Error al insertar SesionRedParticipantesDirecta", e);
		}
	}
	
	/**
	 * Elimina SesionRedParticipantes por id
	 * 
	 * @param id
	 */
	public void delete(Long id) {
		try {
			logger.debug("Delete SesionRedParticipantes by id: " + id);
			sesionRedParticipantesMapper.deleteById(id);
		} catch (DataAccessException e) {
			throw new MineducException("Error al eliminar SesionRedParticipantesDirecta", e);
		}
	}
	
	/**
	 * Obtiene SesionRedParticipantes por id
	 * 
	 * @param id
	 * @return
	 */
	public SesionRedParticipantes getById(Long id) {
		try {
			logger.debug("Get SesionRedParticipantes by id: " + id);
			return sesionRedParticipantesMapper.getById(id);
		} catch (DataAccessException e) {
			throw new MineducException("Error al obtener SesionRedParticipantesDirecta", e);
		}
	}
	
	/**
	 * Obtiene todas las SesionRedParticipantes
	 * 
	 * @param id
	 * @return
	 */
	public List<SesionRedParticipantes> getAll() {
		try {
			logger.debug("Get all Sessions");
			return sesionRedParticipantesMapper.getAll();
		} catch (DataAccessException e) {
			throw new MineducException("Error al obtener todas las SesionRedParticipantesDirectaes", e);
		}
	}
	
	public List<SesionRedParticipantes> getByIdSesion(Long idSesion) {
		try {
			logger.debug("Get all Networks");
			return sesionRedParticipantesMapper.getByIdSesion(idSesion);
		} catch (DataAccessException e) {
			throw new MineducException("Error al obtener todas las SesionRedParticipantesDirectaes", e);
		}
	}

	/**
	 * Actualiza SesionRedParticipantes
	 * 
	 * @param SesionRedParticipantes
	 */
	public void update(SesionRedParticipantes sesionRedParticipantes) {
		try {
			logger.debug("Update SesionRedParticipantes by id: " + sesionRedParticipantes.getIdSesionRedParticipantes());
			sesionRedParticipantesMapper.updateById(sesionRedParticipantes);
		} catch (DataAccessException e) {
			throw new MineducException("Error al actualizar SesionRedParticipantesDirecta", e);
		}
	}
}
