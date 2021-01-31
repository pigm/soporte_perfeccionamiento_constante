package cl.mineduc.came.apoyo_mejora_continua.repo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import cl.mineduc.came.apoyo_mejora_continua.mappers.UsuarioPerfilMapper;
import cl.mineduc.came.apoyo_mejora_continua.modelo.UsuarioPerfil;
import cl.mineduc.framework2.exceptions.MineducException;

@Repository
public class UsuarioPerfilRepo {
	
	@Autowired
	private UsuarioPerfilMapper upMapper;
	
	/**
	 * 
	 * @param up
	 */
	public void insertProfileMenu(UsuarioPerfil up) {
		try {
			upMapper.insert(up);
		} catch (DataAccessException e) {
			throw new MineducException("Error al insertar Perfil Usuario", e);
		}
	}
	
	public void updateProfileMenu(UsuarioPerfil up) {
		try {
			upMapper.updateById(up);
		} catch (DataAccessException e) {
			throw new MineducException("Error al actualizar Perfil Usuario", e);
		}
	}
	
	public UsuarioPerfil getUsuarioPerfilById(Long idUsuarioPerfil) {
		try {
			return upMapper.getById(idUsuarioPerfil);
		} catch (DataAccessException e) {
			throw new MineducException("Error al consultar por el id del usuario perfil", e);
		}
	}

	public List<UsuarioPerfil> getAll() {
		try {
			return upMapper.getAll();
		} catch (DataAccessException e) {
			throw new MineducException("Error al consultar usuario perfil", e);
		}
	}
}
