package cl.mineduc.came.apoyo_mejora_continua.mappers;

import java.util.List;

import cl.mineduc.came.apoyo_mejora_continua.modelo.ClasificacionSep;

public interface ClasificacionSepMapper {
    int deleteById(Long idClasificacionSep);

    int insert(ClasificacionSep record);

    List<ClasificacionSep> getAll();
}