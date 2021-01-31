package cl.mineduc.came.apoyo_mejora_continua.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cl.mineduc.came.apoyo_mejora_continua.modelo.Supervisor;

@Mapper
public interface SupervisorMapper {
    int deleteById(Long idSupervisor);

    int insert(Supervisor record);

    Supervisor getById(Long idSupervisor);

    List<Supervisor> getAll();

    int updateById(Supervisor record);
    
    Supervisor getByIdUsuario(Long idUsuario);
}