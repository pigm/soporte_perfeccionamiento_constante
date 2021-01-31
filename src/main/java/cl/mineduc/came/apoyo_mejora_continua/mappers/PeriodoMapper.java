package cl.mineduc.came.apoyo_mejora_continua.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cl.mineduc.came.apoyo_mejora_continua.modelo.Periodo;

@Mapper
public interface PeriodoMapper {
    int deleteById(Long idPeriodo);

    int insert(Periodo record);

    List<Periodo> getAll();
    
    Periodo getPeriodoById(Long idPeriodo);

	Periodo getPeriodoByYear(int period);
}