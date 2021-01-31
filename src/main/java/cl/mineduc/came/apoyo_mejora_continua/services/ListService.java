package cl.mineduc.came.apoyo_mejora_continua.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.mineduc.came.apoyo_mejora_continua.dto.list.ElementoListaDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.list.ListDTO;
import cl.mineduc.came.apoyo_mejora_continua.modelo.ElementoLista;
import cl.mineduc.came.apoyo_mejora_continua.modelo.Lista;
import cl.mineduc.came.apoyo_mejora_continua.repo.ElementoListaRepo;
import cl.mineduc.came.apoyo_mejora_continua.repo.ListaRepo;
import cl.mineduc.came.apoyo_mejora_continua.repo.RedRepo;

@Service
public class ListService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private ListaRepo listRepo;

	@Autowired
	private ElementoListaRepo elementRepo;

	@Autowired
	private RedRepo redRepo;

	// #region Private Methods
	private ListDTO convertToDto(Lista lista) {
		ListDTO list = modelMapper.map(lista, ListDTO.class);
		return list;
	}

	private ElementoListaDTO convertToDto(ElementoLista element) {
		ElementoListaDTO elemento = modelMapper.map(element, ElementoListaDTO.class);
		return elemento;
	}

	private ElementoLista dtoToModel(ElementoListaDTO element) {
		ElementoLista elemento = modelMapper.map(element, ElementoLista.class);
		return elemento;
	}
	// #endregion

	// #region Public Methods
	public List<ListDTO> getLists() {
		// List<Lista> lists = listRepo.getLists().stream().filter(p -> !String.valueOf(p.getNombre()).isEmpty()).collect(Collectors.toList());
		List<Lista> lists = listRepo.getLists();
		return lists.stream().map(this::convertToDto).collect(Collectors.toList());
	}

	public List<ElementoListaDTO> getElementsList() {
		List<ElementoLista> elements = elementRepo.getAllElements();
		return elements.stream().map(this::convertToDto).collect(Collectors.toList());
	}

	public ListDTO getList(long id) {
		return convertToDto(listRepo.getListById(id));
	}

	public List<ElementoListaDTO> getElementsListByIdList(long id) {
		List<ElementoLista> elements = elementRepo.getElementsByIdLista(id);
		List<ElementoListaDTO> result = elements.stream().map(this::convertToDto).collect(Collectors.toList());
		
		result.forEach(el ->{			
			if(el.getIdLista().equals("2420130603857871959")){
				// categorias

			} else if(el.getIdLista().equals("2420130603924980824")) {
				// porcentaje
				
			} else if (el.getIdLista().equals("2420130603941758041")) {
				// tipos de red
				el.setRemovable(redRepo.getAllNetworks().stream().anyMatch(n -> n.getIdTipoRed().toString().equals(el.getIdElementoLista())));
			} else if (el.getIdLista().equals("2420130603950146650")) {
				// Momento asesoria

			}
		});

		return result;
	}

	public void insertElement(ElementoListaDTO elementDto) {
		if (elementDto.getIdElementoLista() != null) {			
			elementRepo.updateElementById(dtoToModel(elementDto));
		} else {
			elementDto.setStatus(true);
			elementRepo.insertElemento(dtoToModel(elementDto));
		}
	}
	
	public void setItemStatus(long idElementoLista, boolean status) {
		ElementoLista itemPaso = elementRepo.getElementById(idElementoLista);
		itemPaso.setStatus(status);
		// Actualizo el item actual
		elementRepo.updateElementById(itemPaso);
	}

	public void removeItem(long idElementoLista) {
				
		// Elimino el item actual
		elementRepo.deleteById(idElementoLista);
	}


	//#endregion

}
