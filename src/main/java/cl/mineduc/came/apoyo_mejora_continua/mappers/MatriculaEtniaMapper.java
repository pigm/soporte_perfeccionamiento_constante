package cl.mineduc.came.apoyo_mejora_continua.mappers;

import java.util.List;

import cl.mineduc.came.apoyo_mejora_continua.modelo.MatriculaEtnia;

public interface MatriculaEtniaMapper {
    int deleteById(Long idMatriculaEtnia);

    int insert(MatriculaEtnia record);

    List<MatriculaEtnia> getAll();
}