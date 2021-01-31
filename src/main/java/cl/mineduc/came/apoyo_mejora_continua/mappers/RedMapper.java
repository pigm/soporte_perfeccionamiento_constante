package cl.mineduc.came.apoyo_mejora_continua.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cl.mineduc.came.apoyo_mejora_continua.modelo.Red;

@Mapper
public interface RedMapper {
    int deleteById(Long idRed);

    int insert(Red record);

    Red getById(Long idRed);

    List<Red> getAll();

    int updateById(Red record);

    List<Red> getByIdPeriodo(Long idPeriodo);
}