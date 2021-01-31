package cl.mineduc.came.apoyo_mejora_continua.mappers;

import java.util.List;

import cl.mineduc.came.apoyo_mejora_continua.modelo.Director;

public interface DirectorMapper {
    int deleteById(Long idDirector);

    int insert(Director record);

    List<Director> getAll();
}