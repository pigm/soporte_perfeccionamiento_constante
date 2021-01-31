package cl.mineduc.came.apoyo_mejora_continua.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cl.mineduc.came.apoyo_mejora_continua.modelo.Perfil;

@Mapper
public interface PerfilMapper {
    int deleteById(Long idPerfil);

    int insert(Perfil record);

    Perfil getById(Long idPerfil);

    List<Perfil> getAll();

    int updateById(Perfil record);
    
}