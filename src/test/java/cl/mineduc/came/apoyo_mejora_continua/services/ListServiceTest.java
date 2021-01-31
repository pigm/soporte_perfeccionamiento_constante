package cl.mineduc.came.apoyo_mejora_continua.services;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.testng.annotations.BeforeMethod;

import cl.mineduc.came.apoyo_mejora_continua.dto.list.ElementoListaDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.list.ListDTO;
import cl.mineduc.came.apoyo_mejora_continua.modelo.ElementoLista;
import cl.mineduc.came.apoyo_mejora_continua.modelo.Lista;
import cl.mineduc.came.apoyo_mejora_continua.repo.ElementoListaRepo;
import cl.mineduc.came.apoyo_mejora_continua.repo.ListaRepo;

@RunWith(MockitoJUnitRunner.class)
public class ListServiceTest {

	@Spy
	private ModelMapper modelMapper;

	@Mock
	private ListaRepo listRepo;

	@Mock
	private ElementoListaRepo elementRepo;

	@InjectMocks
	private ListService service;

	List<Lista> lists = new ArrayList<Lista>();

	List<ElementoLista> elements = new ArrayList<ElementoLista>();

	@BeforeMethod
	public void setUp() {
		Lista lista = new Lista();
		lista.setIdLista(1l);
		ElementoLista elemento = new ElementoLista();
		elemento.setIdElementoLista(2l);
		elemento.setIdLista(1l);
		elements.add(elemento);
		lista.setElementosListas(elements);
		lists.add(lista);
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void test_getLists_HappyPath() {
		when(listRepo.getLists()).thenReturn(lists);
		List<ListDTO> listsDto = service.getLists();
		assertNotNull(listsDto);
	}

	@Test
	public void test_getElementsList_HappyPath() {
		when(elementRepo.getAllElements()).thenReturn(elements);
		List<ElementoListaDTO> elementDto = service.getElementsList();
		assertNotNull(elementDto);
	}

	@Test
	public void test_getListById_HappyPath() {
		Lista lista = new Lista();
		lista.setIdLista(1l);
		when(listRepo.getListById(1l)).thenReturn(lista);
		ListDTO listDto = service.getList(1l);
		assertNotNull(listDto);
	}

	@Test
	public void test_getElementsListByIdList_HappyPath() {
		when(elementRepo.getElementsByIdLista(1l)).thenReturn(elements);
		List<ElementoListaDTO> elementsDto = service.getElementsListByIdList(1l);
		assertNotNull(elementsDto);
	}

	@Test
	public void test_insertElementBlank_HappyPath() {
//		List<ElementoListaDTO> elementsDto = service.getElementsListByIdList(1l);
//		assertNotNull(elementsDto);
	}

}
