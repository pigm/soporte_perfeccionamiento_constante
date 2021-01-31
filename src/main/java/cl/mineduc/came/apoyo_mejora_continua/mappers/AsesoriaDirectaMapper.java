package cl.mineduc.came.apoyo_mejora_continua.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cl.mineduc.came.apoyo_mejora_continua.modelo.AsesoriaDirecta;

@Mapper
public interface AsesoriaDirectaMapper {
    int deleteById(Long idAsesoria);

    int insert(AsesoriaDirecta record);

    AsesoriaDirecta getById(Long idAsesoria);

    List<AsesoriaDirecta> getAll();

    int updateById(AsesoriaDirecta record);

    List<AsesoriaDirecta> getByIdPeriodo(Long idAsesoria);

	AsesoriaDirecta getByIdAsignacionSupervisor(Long idAsignacionSupervisor);
}