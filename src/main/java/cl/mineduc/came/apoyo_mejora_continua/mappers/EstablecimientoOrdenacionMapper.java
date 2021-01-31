package cl.mineduc.came.apoyo_mejora_continua.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cl.mineduc.came.apoyo_mejora_continua.modelo.EstablecimientoOrdenacion;

@Mapper
public interface EstablecimientoOrdenacionMapper {
    
    List<EstablecimientoOrdenacion> getAll();

    List<EstablecimientoOrdenacion> getByRbd(Integer rbd);
}
