package cl.mineduc.came.apoyo_mejora_continua.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cl.mineduc.came.apoyo_mejora_continua.modelo.AsignacionMaxima;

@Mapper
public interface AsignacionMaximaMapper {

    int deleteById(Long idAsignacionMaxima);

    int insert(AsignacionMaxima record);

    int updateById(AsignacionMaxima record);

    List<AsignacionMaxima> getAll();
    
    AsignacionMaxima getById(Long id);    
}
