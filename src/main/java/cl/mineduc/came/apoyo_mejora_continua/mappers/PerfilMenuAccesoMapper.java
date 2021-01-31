package cl.mineduc.came.apoyo_mejora_continua.mappers;

import java.util.List;

import cl.mineduc.came.apoyo_mejora_continua.modelo.PerfilMenuAcceso;

public interface PerfilMenuAccesoMapper {
    int deleteById(Long idPerfilMenuAcceso);

    int insert(PerfilMenuAcceso record);

    PerfilMenuAcceso getById(Long idPerfilMenuAcceso);

    List<PerfilMenuAcceso> getAll();

    int updateById(PerfilMenuAcceso record);
}