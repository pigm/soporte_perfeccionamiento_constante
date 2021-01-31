package cl.mineduc.came.apoyo_mejora_continua.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cl.mineduc.came.apoyo_mejora_continua.modelo.PerfilEncuesta;

@Mapper
public interface PerfilEncuestaMapper {
    int deleteById(Long idPerfilEncuesta);

    int insert(PerfilEncuesta record);

    PerfilEncuesta getById(Long idPerfilEncuesta);

    List<PerfilEncuesta> getAll();

    int updateById(PerfilEncuesta record);

    List<PerfilEncuesta> getByIdEncuesta(Long idEncuesta);
}