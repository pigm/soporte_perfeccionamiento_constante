package cl.mineduc.came.apoyo_mejora_continua.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cl.mineduc.came.apoyo_mejora_continua.modelo.MovimientosClaves;

@Mapper
public interface MovimientosClavesMapper {
    int deleteById(Long idMovimientosClaves);

    int insert(MovimientosClaves record);

    MovimientosClaves getById(Long idMovimientosClaves);

    MovimientosClaves getByName(String name);

    List<MovimientosClaves> getAll();

    int updateById(MovimientosClaves record);
}