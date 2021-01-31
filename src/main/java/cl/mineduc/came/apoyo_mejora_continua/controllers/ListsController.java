package cl.mineduc.came.apoyo_mejora_continua.controllers;

import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cl.mineduc.came.apoyo_mejora_continua.dto.list.ElementoListaDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.list.ItemStatusDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.list.ListDTO;
import cl.mineduc.came.apoyo_mejora_continua.services.ListService;

@Controller
@RequestMapping("lists")
public class ListsController {
	private static Logger logger = LogManager.getLogger(ListsController.class);

	@Autowired
	private ListService listService;

	// #region REST

	@GetMapping(value = "/getItems", produces = "application/json")
	public ResponseEntity<Object> getItems(String idList) {

		List<ElementoListaDTO> items = listService.getElementsListByIdList(Long.parseLong(idList));
		return new ResponseEntity<Object>(items, HttpStatus.OK);
	}

	@PostMapping(value = "/setItem", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object> setItem(@Valid @RequestBody ElementoListaDTO item, BindingResult result) {		
		if(result.hasErrors()){			
			return  new ResponseEntity<Object>(result.getAllErrors(), HttpStatus.BAD_REQUEST);	
		}

		listService.insertElement(item);
		
		return  new ResponseEntity<Object>(item, HttpStatus.OK);
	}

	@PostMapping(value = "/setItemStatus", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object> setItemStatus(@RequestBody ItemStatusDTO itemStatus, BindingResult result) {
		
		listService.setItemStatus(Long.parseLong(itemStatus.getIdElementoLista()), itemStatus.isStatus());
		
		return  new ResponseEntity<Object>(itemStatus, HttpStatus.OK);
	}

	@PostMapping(value = "/removeItem", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object> removeItem(@RequestBody ElementoListaDTO item, BindingResult result) {
		
		listService.removeItem(Long.parseLong(item.getIdElementoLista()));		
		return  new ResponseEntity<Object>(item, HttpStatus.OK);
	}
	// #endregion


	@GetMapping(value = "lists")
	public ModelAndView index(@RequestParam(required = false) Integer error) {
		logger.debug("lists view request... ");
		ModelAndView mv = new ModelAndView();

		List<ListDTO> lists = listService.getLists();
		
		mv.addObject("listas", lists);
		mv.addObject("error", error);
		return mv;
	} 
}
