package cl.mineduc.came.apoyo_mejora_continua.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cl.mineduc.came.apoyo_mejora_continua.modelo.SesionFoco;

@Mapper
public interface SesionFocoMapper {
    int deleteById(Long idSesionFoco);

    int insert(SesionFoco record);

    SesionFoco getById(Long idSesionFoco);

    List<SesionFoco> getAll();

    int updateById(SesionFoco record);

    List<SesionFoco> getByIdSesion(Long idSesion);
}