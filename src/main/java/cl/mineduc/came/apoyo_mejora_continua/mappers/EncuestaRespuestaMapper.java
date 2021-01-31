package cl.mineduc.came.apoyo_mejora_continua.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cl.mineduc.came.apoyo_mejora_continua.modelo.EncuestaRespuesta;

@Mapper
public interface EncuestaRespuestaMapper {
    int deleteById(Long idEncuestaRespuesta);

    int insert(EncuestaRespuesta record);

    EncuestaRespuesta getById(Long idEncuestaRespuesta);

    List<EncuestaRespuesta> getAll();

    int updateById(EncuestaRespuesta record);

    List<EncuestaRespuesta> getByIdPregunta(Long idPregunta);    
}