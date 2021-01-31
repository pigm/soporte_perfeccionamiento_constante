package cl.mineduc.came.apoyo_mejora_continua.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cl.mineduc.came.apoyo_mejora_continua.modelo.Modulo;

@Mapper
public interface ModuloMapper {
    int deleteById(Long idModulo);

    int insert(Modulo record);

    Modulo getById(Long idModulo);

    List<Modulo> getAll();

    int updateById(Modulo record);
}