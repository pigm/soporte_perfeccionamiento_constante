package cl.mineduc.came.apoyo_mejora_continua.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cl.mineduc.came.apoyo_mejora_continua.modelo.PeriodoAsignacionSupervisores;

@Mapper
public interface PeriodoAsignacionSupervisorMapper {
	int deleteById(Long idPeriodoAsignacionSupervisores);

    int insert(PeriodoAsignacionSupervisores record);

    PeriodoAsignacionSupervisores getById(Long idPeriodoAsignacionSupervisores);

    List<PeriodoAsignacionSupervisores> getAll();

    int updateById(PeriodoAsignacionSupervisores record);
}