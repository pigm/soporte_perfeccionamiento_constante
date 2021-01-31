package cl.mineduc.came.apoyo_mejora_continua.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cl.mineduc.came.apoyo_mejora_continua.modelo.UsuarioRegistrado;

@Mapper
public interface UsuarioRegistradoMapper {
	
	List<UsuarioRegistrado> getAll();
	
	UsuarioRegistrado getUsuarioById(Long id);
	
	UsuarioRegistrado getUsuarioByUsername(String username);
	
	void insert(UsuarioRegistrado usuario);
	
	void update(UsuarioRegistrado usuario);
	
	List<UsuarioRegistrado> getUsersByProfile(Long idPerfil);
	
	

}
