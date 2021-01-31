package cl.mineduc.came.apoyo_mejora_continua.mappers;

import java.util.List;

import cl.mineduc.came.apoyo_mejora_continua.modelo.EstandaresIndicativosDesempenio;

public interface EstandaresIndicativosDesempenioMapper {
    int deleteById(Long idEstandaresIndicativosDesempenio);

    int insert(EstandaresIndicativosDesempenio record);

    EstandaresIndicativosDesempenio getById(Long idEstandaresIndicativosDesempenio);

    List<EstandaresIndicativosDesempenio> getAll();

    int updateById(EstandaresIndicativosDesempenio record);
}