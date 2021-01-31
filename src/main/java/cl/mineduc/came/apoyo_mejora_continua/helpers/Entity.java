package cl.mineduc.came.apoyo_mejora_continua.helpers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cl.mineduc.came.apoyo_mejora_continua.dto.period.DTOEntity;

@Component
public class Entity {
	
	@Autowired
	private static ModelMapper modelMapper;

	public static DTOEntity convertToDto(Object obj, DTOEntity mapper) {
		return modelMapper.map(obj, mapper.getClass());
	}

	public static Object convertToEntity(Object obj, DTOEntity mapper) {
		return modelMapper.map(mapper, obj.getClass());
	}

}
