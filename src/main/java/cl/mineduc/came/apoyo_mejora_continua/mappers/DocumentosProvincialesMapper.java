package cl.mineduc.came.apoyo_mejora_continua.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cl.mineduc.came.apoyo_mejora_continua.modelo.DocumentosProvinciales;

@Mapper
public interface DocumentosProvincialesMapper {
    int deleteById(Long id);

    int insert(DocumentosProvinciales record);

    List<DocumentosProvinciales> getAll();
    
    List<DocumentosProvinciales> getByPeriod(Long idPeriodo);

    DocumentosProvinciales getById(Long id);

    int setHasDownloaded(DocumentosProvinciales record);
}