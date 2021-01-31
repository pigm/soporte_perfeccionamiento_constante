package cl.mineduc.came.apoyo_mejora_continua.repo;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import cl.mineduc.came.apoyo_mejora_continua.mappers.ListaMapper;
import cl.mineduc.came.apoyo_mejora_continua.modelo.Lista;
import cl.mineduc.framework2.exceptions.MineducException;

@Repository
public class ListaRepo {

	private static Logger logger = LogManager.getLogger(ListaRepo.class);

	@Autowired
	private ListaMapper listaMapper;

	public void insertLista(Lista lista) {
		try {
			logger.debug("Insertando lista");
			listaMapper.insert(lista);
		} catch (DataAccessException e) {
			throw new MineducException("Error al insertar Lista", e);
		}
	}

	public List<Lista> getLists() {
		try {
			return listaMapper.getAll();
		} catch (DataAccessException e) {
			throw new MineducException("Error al obtener Listas", e);
		}
	}

	public Lista getListById(Long id) {
		try {
			return listaMapper.getById(id);
		} catch (DataAccessException e) {
			throw new MineducException("Error al obtener lista por id", e);
		}
	}

}
