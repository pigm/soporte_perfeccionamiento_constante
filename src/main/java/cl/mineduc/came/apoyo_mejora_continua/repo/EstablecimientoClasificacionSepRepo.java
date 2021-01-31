package cl.mineduc.came.apoyo_mejora_continua.repo;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import cl.mineduc.came.apoyo_mejora_continua.mappers.EstablecimientoClasificacionSepMapper;
import cl.mineduc.came.apoyo_mejora_continua.modelo.EstablecimientoClasificacionSep;
import cl.mineduc.framework2.exceptions.MineducException;

@Repository
public class EstablecimientoClasificacionSepRepo {
    private static Logger logger = LogManager.getLogger(EstablecimientoClasificacionSepRepo.class);

	@Autowired
	private EstablecimientoClasificacionSepMapper establecimientoClasificacionSepMapper;
    
    public List<EstablecimientoClasificacionSep> get() {
		try {
			return establecimientoClasificacionSepMapper.getAll();
		} catch (DataAccessException e) {
			throw new MineducException("Error al obtener establecimientoClasificacionSepMapper", e);
		}
	}

	public EstablecimientoClasificacionSep getByRbd(Integer rbd) {
		try {
			return establecimientoClasificacionSepMapper.getByRdb(rbd);
		} catch (DataAccessException e) {
			throw new MineducException("Error al obtener establecimientoClasificacionSepMapper por rbd", e);
		}
	}


}
