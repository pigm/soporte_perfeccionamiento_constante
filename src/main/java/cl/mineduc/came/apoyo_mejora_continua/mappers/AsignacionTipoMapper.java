package cl.mineduc.came.apoyo_mejora_continua.mappers;

import java.util.List;

import cl.mineduc.came.apoyo_mejora_continua.modelo.AsignacionTipo;

public interface AsignacionTipoMapper {
    int deleteById(Long idAsignacionTipo);

    int insert(AsignacionTipo record);

    List<AsignacionTipo> getAll();
}