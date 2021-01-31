
package cl.mineduc.came.apoyo_mejora_continua.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cl.mineduc.came.apoyo_mejora_continua.modelo.Biblioteca;

@Mapper
public interface BibliotecaMapper {

    int insert(Biblioteca record);

    List<Biblioteca> getAll();
    
    void update(Biblioteca record);

    Biblioteca getById(Long id);
    
    void deleteById(Long id);

}