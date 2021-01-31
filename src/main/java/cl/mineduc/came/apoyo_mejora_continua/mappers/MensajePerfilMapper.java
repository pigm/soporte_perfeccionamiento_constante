package cl.mineduc.came.apoyo_mejora_continua.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cl.mineduc.came.apoyo_mejora_continua.modelo.MensajePerfil;

@Mapper
public interface MensajePerfilMapper {

    int insert(MensajePerfil record);

    MensajePerfil getById(Long idPerfil);

    List<MensajePerfil> getAll();

    int updateById(MensajePerfil record);

    List<MensajePerfil> getByIdPeriodo(Long idPeriodo);
    
}
