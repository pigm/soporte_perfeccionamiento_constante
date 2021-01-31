package cl.mineduc.came.apoyo_mejora_continua.mappers;

import java.util.List;

import cl.mineduc.came.apoyo_mejora_continua.modelo.Nivel;

public interface NivelMapper {
    int deleteById(Long idNivel);

    int insert(Nivel record);

    List<Nivel> getAll();
}