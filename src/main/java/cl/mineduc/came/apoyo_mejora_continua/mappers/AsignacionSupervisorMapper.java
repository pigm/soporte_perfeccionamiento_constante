package cl.mineduc.came.apoyo_mejora_continua.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cl.mineduc.came.apoyo_mejora_continua.modelo.AsignacionSupervisor;

@Mapper
public interface AsignacionSupervisorMapper {
    int deleteById(Long idAsignacionSupervisor);

    int insert(AsignacionSupervisor record);

    AsignacionSupervisor getById(Long idAsignacionSupervisor);

    List<AsignacionSupervisor> getAll();

    int updateById(AsignacionSupervisor record);
}