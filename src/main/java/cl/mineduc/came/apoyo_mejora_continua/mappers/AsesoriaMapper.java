package cl.mineduc.came.apoyo_mejora_continua.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cl.mineduc.came.apoyo_mejora_continua.modelo.Asesoria;

@Mapper
public interface AsesoriaMapper {
    int deleteById(Long idAsesoria);

    int insert(Asesoria record);

    Asesoria getById(Long idAsesoria);

    List<Asesoria> getAll();

    int updateById(Asesoria record);

    List<Asesoria> getByIdPeriodo(Long idAsesoria);
}