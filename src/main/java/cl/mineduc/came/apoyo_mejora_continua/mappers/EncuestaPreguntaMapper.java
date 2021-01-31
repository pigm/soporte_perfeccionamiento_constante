package cl.mineduc.came.apoyo_mejora_continua.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cl.mineduc.came.apoyo_mejora_continua.modelo.EncuestaPregunta;

@Mapper
public interface EncuestaPreguntaMapper {
    int deleteById(Long idEncuestaPregunta);

    int insert(EncuestaPregunta record);

    EncuestaPregunta getById(Long idEncuestaPregunta);

    List<EncuestaPregunta> getAll();

    int updateById(EncuestaPregunta record);

    List<EncuestaPregunta> getByIdEncuesta(Long idEncuesta);
}