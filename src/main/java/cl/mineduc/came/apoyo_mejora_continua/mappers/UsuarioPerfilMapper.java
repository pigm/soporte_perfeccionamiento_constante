package cl.mineduc.came.apoyo_mejora_continua.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cl.mineduc.came.apoyo_mejora_continua.modelo.UsuarioPerfil;

@Mapper
public interface UsuarioPerfilMapper {
    int deleteById(Long idUsuarioPerfil);

    int insert(UsuarioPerfil record);

    UsuarioPerfil getById(Long idUsuarioPerfil);

    List<UsuarioPerfil> getAll();

    int updateById(UsuarioPerfil record);
}