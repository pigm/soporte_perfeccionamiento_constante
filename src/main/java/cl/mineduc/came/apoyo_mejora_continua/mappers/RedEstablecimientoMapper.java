package cl.mineduc.came.apoyo_mejora_continua.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cl.mineduc.came.apoyo_mejora_continua.modelo.RedEstablecimiento;

@Mapper
public interface RedEstablecimientoMapper {
    int deleteById(Long idRedEstablecimiento);

    int insert(RedEstablecimiento record);

    RedEstablecimiento getById(Long idRedEstablecimiento);

    List<RedEstablecimiento> getAll();

    int updateById(RedEstablecimiento record);

    List<RedEstablecimiento> getByIdRed(Long idRed);
}