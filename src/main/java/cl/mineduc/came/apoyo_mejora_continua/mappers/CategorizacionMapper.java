package cl.mineduc.came.apoyo_mejora_continua.mappers;

import java.util.List;

import cl.mineduc.came.apoyo_mejora_continua.modelo.Categorizacion;

public interface CategorizacionMapper {
    int deleteById(Long idCategorizacion);

    int insert(Categorizacion record);

    List<Categorizacion> getAll();
}