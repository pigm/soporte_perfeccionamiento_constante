package cl.mineduc.came.apoyo_mejora_continua.repo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import cl.mineduc.came.apoyo_mejora_continua.mappers.MensajeMapper;
import cl.mineduc.came.apoyo_mejora_continua.modelo.Mensaje;
import cl.mineduc.framework2.exceptions.MineducException;

@Repository("mensajeRepo")
public class MensajeRepo {

    private static Logger logger = LogManager.getLogger(MensajeRepo.class);
	
	@Autowired
    private MensajeMapper mensajeMapper;
    
    public void insertaMensaje(Mensaje mensaje){

        try {
			logger.debug("Insertando mensaje: " + mensaje.toString());
			mensajeMapper.insert(mensaje);
		} catch (DataAccessException e) {
			throw new MineducException("Error al insertar mensaje", e);
		}

    }

    public Mensaje obtenerMensaje(long idMensaje){
		try {
			logger.debug("Consulta mensaje por id");
			return mensajeMapper.getById(idMensaje);
		} catch (DataAccessException e){
			throw new MineducException("Error al obtener mensaje", e);
		}
	}
    
}
