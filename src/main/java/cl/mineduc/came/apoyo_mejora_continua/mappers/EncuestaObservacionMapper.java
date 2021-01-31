package cl.mineduc.came.apoyo_mejora_continua.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cl.mineduc.came.apoyo_mejora_continua.modelo.EncuestaObservacion;

@Mapper
public interface EncuestaObservacionMapper {
    int deleteById(Long idEncuestaObservacion);

    int insert(EncuestaObservacion record);

    EncuestaObservacion getById(Long idEncuestaObservacion);

    List<EncuestaObservacion> getAll();

    int updateById(EncuestaObservacion record);

    List<EncuestaObservacion> getByIdEncuesta(Long idEncuesta);
}