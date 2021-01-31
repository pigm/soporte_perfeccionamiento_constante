package cl.mineduc.came.apoyo_mejora_continua.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cl.mineduc.came.apoyo_mejora_continua.modelo.EstablecimientoUbicacion;

@Mapper
public interface EstablecimientoUbicacionMapper {
    
    List<EstablecimientoUbicacion> getAll();

    EstablecimientoUbicacion getByRbd(Integer rbd);
}
