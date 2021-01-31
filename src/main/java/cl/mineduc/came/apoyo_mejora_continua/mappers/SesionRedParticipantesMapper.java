package cl.mineduc.came.apoyo_mejora_continua.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cl.mineduc.came.apoyo_mejora_continua.modelo.SesionRedParticipantes;

@Mapper
public interface SesionRedParticipantesMapper {
    int deleteById(Long idSesionRedParticipantes);

    int insert(SesionRedParticipantes record);

    SesionRedParticipantes getById(Long idSesionRedParticipantes);

    List<SesionRedParticipantes> getAll();

    int updateById(SesionRedParticipantes record);

    List<SesionRedParticipantes> getByIdSesion(Long idSesion);
}