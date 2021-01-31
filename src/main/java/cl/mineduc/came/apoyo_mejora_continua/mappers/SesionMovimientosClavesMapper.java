package cl.mineduc.came.apoyo_mejora_continua.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cl.mineduc.came.apoyo_mejora_continua.modelo.SesionMovimientosClaves;

@Mapper
public interface SesionMovimientosClavesMapper {
    int deleteById(Long idSesionMovimientosClaves);

    int insert(SesionMovimientosClaves record);

    SesionMovimientosClaves getById(Long idSesionMovimientosClaves);

    List<SesionMovimientosClaves> getAll();

    int updateById(SesionMovimientosClaves record);

    List<SesionMovimientosClaves> getByIdSesionFoco(Long idSesionFoco);
}