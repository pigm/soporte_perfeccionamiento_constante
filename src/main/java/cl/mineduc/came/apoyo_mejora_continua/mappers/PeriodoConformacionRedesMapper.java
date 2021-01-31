package cl.mineduc.came.apoyo_mejora_continua.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cl.mineduc.came.apoyo_mejora_continua.modelo.PeriodoConformacionRedes;

@Mapper
public interface PeriodoConformacionRedesMapper {
	int deleteById(Long idPeriodoConformacionRedes);

    int insert(PeriodoConformacionRedes record);

    PeriodoConformacionRedes getById(Long idPeriodoConformacionRedes);

    List<PeriodoConformacionRedes> getAll();

    int updateById(PeriodoConformacionRedes record);
}