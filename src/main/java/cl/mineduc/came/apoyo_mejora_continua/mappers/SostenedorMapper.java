package cl.mineduc.came.apoyo_mejora_continua.mappers;

import java.util.List;

import cl.mineduc.came.apoyo_mejora_continua.modelo.Sostenedor;

public interface SostenedorMapper {
    int deleteById(Long idSostenedor);

    int insert(Sostenedor record);

    List<Sostenedor> getAll();
}