package cl.mineduc.came.apoyo_mejora_continua.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cl.mineduc.came.apoyo_mejora_continua.modelo.Foco;

@Mapper
public interface FocoMapper {
    int deleteById(Long idFoco);

    int insert(Foco record);

    List<Foco> getAll();
    
    Foco getFocoById(Long id);
    
    int updateById(Foco foco);
}