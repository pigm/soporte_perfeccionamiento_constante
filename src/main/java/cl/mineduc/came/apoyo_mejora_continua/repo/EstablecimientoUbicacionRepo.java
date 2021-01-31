package cl.mineduc.came.apoyo_mejora_continua.repo;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import cl.mineduc.came.apoyo_mejora_continua.mappers.EstablecimientoUbicacionMapper;
import cl.mineduc.came.apoyo_mejora_continua.modelo.EstablecimientoUbicacion;
import cl.mineduc.framework2.exceptions.MineducException;

@Repository
public class EstablecimientoUbicacionRepo {
    private static Logger logger = LogManager.getLogger(EstablecimientoUbicacionRepo.class);

	@Autowired
	private EstablecimientoUbicacionMapper establecimientoUbicacionMapper;
    
    public List<EstablecimientoUbicacion> get() {
		try {
			return establecimientoUbicacionMapper.getAll();
		} catch (DataAccessException e) {
			throw new MineducException("Error al obtener EstablecimientoOrdenacion", e);
		}
	}

	public EstablecimientoUbicacion getByRbd(Integer rbd) {
		try {
			return establecimientoUbicacionMapper.getByRbd(rbd);
		} catch (DataAccessException e) {
			throw new MineducException("Error al obtener EstablecimientoOrdenacion por rbd", e);
		}
	}


}
