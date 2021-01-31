package cl.mineduc.came.apoyo_mejora_continua.repo;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import cl.mineduc.came.apoyo_mejora_continua.mappers.EstablecimientoOrdenacionMapper;
import cl.mineduc.came.apoyo_mejora_continua.modelo.EstablecimientoOrdenacion;
import cl.mineduc.framework2.exceptions.MineducException;

@Repository
public class EstablecimientoOrdenacionRepo {
    private static Logger logger = LogManager.getLogger(EstablecimientoOrdenacionRepo.class);

	@Autowired
	private EstablecimientoOrdenacionMapper establecimientoOrdenacionMapper;
    
    public List<EstablecimientoOrdenacion> get() {
		try {
			return establecimientoOrdenacionMapper.getAll();
		} catch (DataAccessException e) {
			throw new MineducException("Error al obtener EstablecimientoOrdenacion", e);
		}
	}

	public List<EstablecimientoOrdenacion> getByRbd(Integer rbd) {
		try {
			return establecimientoOrdenacionMapper.getByRbd(rbd);
		} catch (DataAccessException e) {
			throw new MineducException("Error al obtener EstablecimientoOrdenacion por rbd", e);
		}
	}


}
