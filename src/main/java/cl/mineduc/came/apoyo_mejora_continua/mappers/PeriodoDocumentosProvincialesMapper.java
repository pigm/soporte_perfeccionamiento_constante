package cl.mineduc.came.apoyo_mejora_continua.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cl.mineduc.came.apoyo_mejora_continua.modelo.PeriodoDocumentosProvinciales;

@Mapper
public interface PeriodoDocumentosProvincialesMapper {
    int deleteById(Long periodoDocumentosProvinciales);

    int insert(PeriodoDocumentosProvinciales record);

    PeriodoDocumentosProvinciales getById(Long periodoDocumentosProvinciales);

    List<PeriodoDocumentosProvinciales> getAll();

    int updateById(PeriodoDocumentosProvinciales record);
}