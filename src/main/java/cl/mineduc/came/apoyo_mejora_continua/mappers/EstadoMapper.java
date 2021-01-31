package cl.mineduc.came.apoyo_mejora_continua.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cl.mineduc.came.apoyo_mejora_continua.modelo.Estado;

@Mapper
public interface EstadoMapper {
    int deleteById(Long idEstado);

    int insert(Estado record);

    Estado getById(Long idEstado);

    List<Estado> getAll();

    int updateById(Estado record);
}