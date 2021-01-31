package cl.mineduc.came.apoyo_mejora_continua.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cl.mineduc.came.apoyo_mejora_continua.modelo.ObjetivoMejora;

@Mapper
public interface ObjetivoMejoraMapper {
    int deleteById(Long idObjetivoMejora);

    int insert(ObjetivoMejora record);

    ObjetivoMejora getById(Long idObjetivoMejora);

    List<ObjetivoMejora> getAll();

    int updateById(ObjetivoMejora record);

    List<ObjetivoMejora> getByIdSesion(Long idSesion);
}