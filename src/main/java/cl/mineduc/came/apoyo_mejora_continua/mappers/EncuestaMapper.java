package cl.mineduc.came.apoyo_mejora_continua.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cl.mineduc.came.apoyo_mejora_continua.modelo.Encuesta;

@Mapper
public interface EncuestaMapper {
    int deleteById(Long idEncuesta);

    int insert(Encuesta record);

    Encuesta getById(Long idEncuesta);

    List<Encuesta> getAll();

    int updateById(Encuesta record);

    List<Encuesta> getByIdPeriodo(Long idPeriodo);
}