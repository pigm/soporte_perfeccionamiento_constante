package cl.mineduc.came.apoyo_mejora_continua.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cl.mineduc.came.apoyo_mejora_continua.modelo.SupervisorSuplencia;

@Mapper
public interface SupervisorSuplenciaMapper {
 
    int deleteById(Long idSupervisorSuplencia);

    int insert(SupervisorSuplencia record);

    int updateById(SupervisorSuplencia record);

    List<SupervisorSuplencia> getAll();

    List<SupervisorSuplencia> getByIdPeriodo(Long idPeriodo);
    
    SupervisorSuplencia getById(Long id);
}
