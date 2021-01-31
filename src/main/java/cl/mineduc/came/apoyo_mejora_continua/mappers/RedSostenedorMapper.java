package cl.mineduc.came.apoyo_mejora_continua.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cl.mineduc.came.apoyo_mejora_continua.modelo.RedSostenedor;

@Mapper
public interface RedSostenedorMapper {
    int deleteById(Long idRedSostenedor);

    int insert(RedSostenedor record);

    RedSostenedor getById(Long idRedSostenedor);

    List<RedSostenedor> getAll();

    int updateById(RedSostenedor record);

    List<RedSostenedor> getByIdRed(Long idRed);
}