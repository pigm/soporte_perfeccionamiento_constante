package cl.mineduc.came.apoyo_mejora_continua.mappers;

import java.util.List;

import cl.mineduc.came.apoyo_mejora_continua.modelo.SupervisorEstablecimiento;

public interface SupervisorEstablecimientoMapper {
    int deleteById(Long idSupervisorEstablecimiento);

    int insert(SupervisorEstablecimiento record);

    SupervisorEstablecimiento getById(Long idSupervisorEstablecimiento);

    List<SupervisorEstablecimiento> getAll();

    int updateById(SupervisorEstablecimiento record);
}