package cl.mineduc.came.apoyo_mejora_continua.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cl.mineduc.came.apoyo_mejora_continua.modelo.PerfilMenu;

@Mapper
public interface PerfilMenuMapper {
    int deleteById(Long idPerfilMenu);

    int insert(PerfilMenu record);

    PerfilMenu getById(Long idPerfilMenu);

    List<PerfilMenu> getAll();

    int updateById(PerfilMenu record);
}