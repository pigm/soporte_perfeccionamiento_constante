package cl.mineduc.came.apoyo_mejora_continua.mappers;

import java.util.List;

import cl.mineduc.came.apoyo_mejora_continua.modelo.EstablecimientoCategorizacionHistorica;

public interface EstablecimientoCategorizacionHistoricaMapper {
    int deleteById(Long idEstablecimientoCategorizacionHistorica);

    int insert(EstablecimientoCategorizacionHistorica record);

    EstablecimientoCategorizacionHistorica getById(Long idEstablecimientoCategorizacionHistorica);

    List<EstablecimientoCategorizacionHistorica> getAll();

    int updateById(EstablecimientoCategorizacionHistorica record);
}