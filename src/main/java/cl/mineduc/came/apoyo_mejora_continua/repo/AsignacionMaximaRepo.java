package cl.mineduc.came.apoyo_mejora_continua.repo;

// import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import cl.mineduc.came.apoyo_mejora_continua.mappers.AsignacionMaximaMapper;
import cl.mineduc.came.apoyo_mejora_continua.modelo.AsignacionMaxima;
import cl.mineduc.framework2.exceptions.MineducException;

@Repository
public class AsignacionMaximaRepo {
    private static Logger logger = LogManager.getLogger(AsignacionMaximaRepo.class);

    @Autowired
    private AsignacionMaximaMapper asignacionMaximaMapper;
    

    public AsignacionMaxima get() {
		try {
			return asignacionMaximaMapper.getAll().stream().iterator().next();
		} catch (DataAccessException e) {
            logger.error("Error al obtener AsignacionMaxima", e);
            throw new MineducException("Error al obtener AsignacionMaxima", e);
		}
    }

    public int update(AsignacionMaxima record) {
		try {
			logger.debug("actualizar asignacionMaxima: " + record.toString());
			return asignacionMaximaMapper.updateById(record);
		} catch (DataAccessException e) {
			throw new MineducException("Error al actualizar el AsignacionMaxima", e);
		}
	}


}
