package cl.mineduc.came.apoyo_mejora_continua.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cl.mineduc.came.apoyo_mejora_continua.modelo.RedSupervisor;

@Mapper
public interface RedSupervisorMapper {
    int deleteById(Long idRedSupervisor);

    int insert(RedSupervisor record);

    RedSupervisor getById(Long idRedSupervisor);

    List<RedSupervisor> getAll();

    int updateById(RedSupervisor record);

    List<RedSupervisor> getByIdRed(Long idRed);
}