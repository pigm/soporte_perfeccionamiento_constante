package cl.mineduc.came.apoyo_mejora_continua.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cl.mineduc.came.apoyo_mejora_continua.modelo.SesionFocoAccionMejora;

@Mapper
public interface SesionFocoAccionMejoraMapper {
    int deleteById(Long idSesionFocoAccionMejora);

    int insert(SesionFocoAccionMejora record);

    SesionFocoAccionMejora getById(Long idSesionFoco);

    List<SesionFocoAccionMejora> getAll();

    int updateById(SesionFocoAccionMejora record);

    List<SesionFocoAccionMejora> getByIdSesionFoco(Long idSesionFocoAccionMejora);
}
