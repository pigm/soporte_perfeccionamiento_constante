package cl.mineduc.came.apoyo_mejora_continua.repo;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import cl.mineduc.came.apoyo_mejora_continua.mappers.MensajePerfilMapper;
import cl.mineduc.came.apoyo_mejora_continua.modelo.MensajePerfil;
import cl.mineduc.framework2.exceptions.MineducException;

@Repository("mensajePerfilRepo")
public class MensajePerfilRepo {

    private static Logger logger = LogManager.getLogger(MensajePerfilRepo.class);
	
	@Autowired
    private MensajePerfilMapper mensajePerfilMapper;

    public void insertaMensajePerfil(MensajePerfil mensajePerfil){

        try {
			logger.debug("Insertando mensaje: " + mensajePerfil.toString());
			mensajePerfilMapper.insert(mensajePerfil);
		} catch (DataAccessException e) {
			throw new MineducException("Error al insertar mensaje", e);
		}

    }

    public MensajePerfil obtenerMensajePerfil(long idMensajePerfil){
		try {
			logger.debug("Consulta mensaje perfil por id");
			return mensajePerfilMapper.getById(idMensajePerfil);
		} catch (DataAccessException e){
			throw new MineducException("Error al obtener mensaje perfil", e);
		}
	}

	public List<MensajePerfil> obtenerMensajesPerfil(){
		try {
			logger.debug("Consulta todos los mensajes perfil");
			return mensajePerfilMapper.getAll();
		} catch (DataAccessException e){
			throw new MineducException("Error al obtener los mensajes perfil", e);
		}
	}

	public void modificaMensajePerfil(MensajePerfil mensajePerfil){

        try {
			logger.debug("Modificando mensaje: " + mensajePerfil.toString());
			mensajePerfilMapper.updateById(mensajePerfil);
		} catch (DataAccessException e) {
			throw new MineducException("Error al modificar mensaje", e);
		}

	}
	
	public List<MensajePerfil> getByIdPeriodo(long idPeriodo) {
		try {
			logger.debug("Consulta mensaje perfil por idperiodo");
			return mensajePerfilMapper.getByIdPeriodo(idPeriodo);
		} catch (DataAccessException e) {
			throw new MineducException("Error al obtener mensaje perfil", e);
		}
	}
    
}
