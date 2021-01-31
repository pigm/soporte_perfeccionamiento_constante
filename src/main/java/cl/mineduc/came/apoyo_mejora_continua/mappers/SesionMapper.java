package cl.mineduc.came.apoyo_mejora_continua.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cl.mineduc.came.apoyo_mejora_continua.modelo.Sesion;

@Mapper
public interface SesionMapper {
    int deleteById(Long idSesion);

    int insert(Sesion record);

    Sesion getById(Long idSesion);

    List<Sesion> getAll();

    List<Sesion> getByIdAsesoria(Long idAsesoria);

    int updateById(Sesion record);
}