package cl.mineduc.came.apoyo_mejora_continua.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cl.mineduc.came.apoyo_mejora_continua.modelo.ElementoLista;

@Mapper
public interface ElementoListaMapper {
    int deleteByPrimaryKey(Long idElementoLista);

    int insert(ElementoLista record);

    ElementoLista getById(Long idElementoLista);

    List<ElementoLista> getAll();

    int updateById(ElementoLista record);
    
    List<ElementoLista> getByIdLista(Long idLista);
}