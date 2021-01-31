package cl.mineduc.came.apoyo_mejora_continua.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cl.mineduc.came.apoyo_mejora_continua.modelo.SubModulo;

@Mapper
public interface SubModuloMapper {
    int deleteById(Long idSubModulo);

    int insert(SubModulo record);

    SubModulo getById(Long idSubModulo);

    SubModulo getSubModuloByUrl(String url);

    List<SubModulo> getAll();

    int updateById(SubModulo record);

    List<SubModulo> getByIdModulo(Long idModulo);
}