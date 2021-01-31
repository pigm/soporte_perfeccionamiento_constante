package cl.mineduc.came.apoyo_mejora_continua.mappers;

import java.util.List;

import cl.mineduc.came.apoyo_mejora_continua.modelo.EstablecimientoEstado;

public interface EstablecimientoEstadoMapper {
    int deleteById(Long idEstablecimientoEstado);

    int insert(EstablecimientoEstado record);

    List<EstablecimientoEstado> getAll();
}