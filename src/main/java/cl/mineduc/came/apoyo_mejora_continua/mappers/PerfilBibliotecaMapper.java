package cl.mineduc.came.apoyo_mejora_continua.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cl.mineduc.came.apoyo_mejora_continua.modelo.PerfilBiblioteca;


@Mapper
public interface PerfilBibliotecaMapper {
    int deleteById(Long PerfilBiblioteca);

    int insert(PerfilBiblioteca record);

    PerfilBiblioteca getById(Long PerfilBiblioteca);
    
    List<PerfilBiblioteca> getByIdPeriodo(Long idPeriodo);

    List<PerfilBiblioteca> getAll();

    int updateById(PerfilBiblioteca record);
}