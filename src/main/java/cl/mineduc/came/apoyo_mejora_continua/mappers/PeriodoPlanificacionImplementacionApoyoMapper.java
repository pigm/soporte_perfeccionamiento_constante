package cl.mineduc.came.apoyo_mejora_continua.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cl.mineduc.came.apoyo_mejora_continua.modelo.PeriodoPlanificacionImplementacionApoyo;

@Mapper
public interface PeriodoPlanificacionImplementacionApoyoMapper {

	int deleteById(Long idPeriodoPlanificacionImplementacionApoyo);

	int insert(PeriodoPlanificacionImplementacionApoyo record);

	PeriodoPlanificacionImplementacionApoyo getById(Long idPeriodoPlanificacionImplementacionApoyo);

	List<PeriodoPlanificacionImplementacionApoyo> getAll();

	int updateById(PeriodoPlanificacionImplementacionApoyo record);

}