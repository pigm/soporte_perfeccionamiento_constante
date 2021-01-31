package cl.mineduc.came.apoyo_mejora_continua.mappers;

import java.util.List;

import cl.mineduc.came.apoyo_mejora_continua.modelo.Establecimiento;

public interface EstablecimientoMapper {
    int deleteById(Long idEstablecimiento);

    int insert(Establecimiento record);

    Establecimiento getById(Long idEstablecimiento);

    List<Establecimiento> getAll();

    int updateById(Establecimiento record);
}