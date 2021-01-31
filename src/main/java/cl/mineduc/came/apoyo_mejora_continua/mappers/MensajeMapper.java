package cl.mineduc.came.apoyo_mejora_continua.mappers;

import org.apache.ibatis.annotations.Mapper;

import cl.mineduc.came.apoyo_mejora_continua.modelo.Mensaje;

@Mapper
public interface MensajeMapper {
    

    int insert(Mensaje record);

    Mensaje getById(Long idPerfil);
}
