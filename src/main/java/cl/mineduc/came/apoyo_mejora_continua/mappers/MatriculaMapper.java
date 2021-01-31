package cl.mineduc.came.apoyo_mejora_continua.mappers;

import java.util.List;

import cl.mineduc.came.apoyo_mejora_continua.modelo.Matricula;

public interface MatriculaMapper {
    int deleteById(Long idMatricula);

    int insert(Matricula record);

    Matricula getById(Long idMatricula);

    List<Matricula> getAll();

    int updateById(Matricula record);
}