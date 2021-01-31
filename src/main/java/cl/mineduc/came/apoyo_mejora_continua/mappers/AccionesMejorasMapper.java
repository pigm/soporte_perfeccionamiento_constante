package cl.mineduc.came.apoyo_mejora_continua.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cl.mineduc.came.apoyo_mejora_continua.modelo.AccionesMejoras;

@Mapper
public interface AccionesMejorasMapper {
    int deleteById(Long idAccionesMejoras);

    int insert(AccionesMejoras record);

    AccionesMejoras getById(Long idAccionesMejoras);

    AccionesMejoras getByName(String name);

    List<AccionesMejoras> getAll();

    int updateById(AccionesMejoras record);
}