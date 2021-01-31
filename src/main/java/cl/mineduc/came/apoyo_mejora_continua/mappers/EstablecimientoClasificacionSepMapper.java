package cl.mineduc.came.apoyo_mejora_continua.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cl.mineduc.came.apoyo_mejora_continua.modelo.EstablecimientoClasificacionSep;

@Mapper
public interface EstablecimientoClasificacionSepMapper {
    
    List<EstablecimientoClasificacionSep> getAll();

    EstablecimientoClasificacionSep getByRdb(Integer rbd);
}
