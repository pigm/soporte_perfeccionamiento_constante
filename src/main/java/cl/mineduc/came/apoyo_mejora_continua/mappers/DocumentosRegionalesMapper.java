package cl.mineduc.came.apoyo_mejora_continua.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cl.mineduc.came.apoyo_mejora_continua.modelo.DocumentosRegionales;

@Mapper
public interface DocumentosRegionalesMapper {
    int deleteById(Long id);

    int insert(DocumentosRegionales record);

    List<DocumentosRegionales> getAll();
    
    List<DocumentosRegionales> getByPeriod(Long idPeriodo);

    DocumentosRegionales getById(Long id);

    int setHasDownloaded(DocumentosRegionales record);
}