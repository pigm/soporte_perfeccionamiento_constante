package cl.mineduc.came.apoyo_mejora_continua.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cl.mineduc.came.apoyo_mejora_continua.modelo.Bitacora;

@Mapper
public interface BitacoraMapper {
    int deleteById(Long idBitacora);

    int insert(Bitacora record);

    Bitacora getById(Long idBitacora);

    List<Bitacora> getAll();

    int updateById(Bitacora record);
}