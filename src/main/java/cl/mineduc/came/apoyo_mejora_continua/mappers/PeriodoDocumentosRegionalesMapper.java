package cl.mineduc.came.apoyo_mejora_continua.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cl.mineduc.came.apoyo_mejora_continua.modelo.PeriodoDocumentosRegionales;

@Mapper
public interface PeriodoDocumentosRegionalesMapper {
    int deleteById(Long idPeriodoDocumentosRegionales);

    int insert(PeriodoDocumentosRegionales record);

    PeriodoDocumentosRegionales getById(Long idPeriodoDocumentosRegionales);

    List<PeriodoDocumentosRegionales> getAll();

    int updateById(PeriodoDocumentosRegionales record);
}