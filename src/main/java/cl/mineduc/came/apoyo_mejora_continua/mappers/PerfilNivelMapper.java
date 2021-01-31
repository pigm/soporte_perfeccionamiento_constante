package cl.mineduc.came.apoyo_mejora_continua.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cl.mineduc.came.apoyo_mejora_continua.modelo.PerfilNivel;

@Mapper
public interface PerfilNivelMapper {
    int deleteById(Long idPerfilNivel);

    int insert(PerfilNivel record);

    PerfilNivel getById(Long idPerfilNivel);

    List<PerfilNivel> getAll();

    int updateById(PerfilNivel record);
}