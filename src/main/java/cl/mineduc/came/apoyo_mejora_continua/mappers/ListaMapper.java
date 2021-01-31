package cl.mineduc.came.apoyo_mejora_continua.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cl.mineduc.came.apoyo_mejora_continua.modelo.Lista;

@Mapper
public interface ListaMapper {
    int deleteById(Long idLista);

    int insert(Lista record);

    List<Lista> getAll();
    
    Lista getById(Long id);
}