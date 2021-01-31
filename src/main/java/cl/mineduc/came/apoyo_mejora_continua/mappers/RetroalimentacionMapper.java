package cl.mineduc.came.apoyo_mejora_continua.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cl.mineduc.came.apoyo_mejora_continua.modelo.Retroalimentacion;

@Mapper
public interface RetroalimentacionMapper {
    int deleteById(Long idRetroalimentacion);

    int insert(Retroalimentacion record);

    Retroalimentacion getById(Long idRetroalimentacion);

    List<Retroalimentacion> getAll();

    int updateById(Retroalimentacion record);

    List<Retroalimentacion> getByIdPeriodo(Long idPeriodo);
}