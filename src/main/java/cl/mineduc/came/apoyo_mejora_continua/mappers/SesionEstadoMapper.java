package cl.mineduc.came.apoyo_mejora_continua.mappers;

import java.util.List;

import cl.mineduc.came.apoyo_mejora_continua.modelo.SesionEstado;

public interface SesionEstadoMapper {
    int deleteById(Long idSesionEstado);

    int insert(SesionEstado record);

    List<SesionEstado> getAll();
}