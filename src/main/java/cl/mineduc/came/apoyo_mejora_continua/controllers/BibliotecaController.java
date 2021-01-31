package cl.mineduc.came.apoyo_mejora_continua.controllers;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
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

import cl.mineduc.came.apoyo_mejora_continua.Excel.DocumentosExcel;
import cl.mineduc.came.apoyo_mejora_continua.dto.biblioteca.BibliotecaDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.biblioteca.BibliotecaListDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.biblioteca.BibliotecaRequestDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.biblioteca.SetBibliotecaRequestDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.biblioteca.UpdateBibliotecaRequestDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.documents.DocumentsDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.documents.DocumentsListDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.documents.DocumentsRequestDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.documents.SetDocumentRequestDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.period.PeriodoDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.shared.SelectorDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.users.ProfileSelectorDTO;
import cl.mineduc.came.apoyo_mejora_continua.repo.ElementoListaRepo;
import cl.mineduc.came.apoyo_mejora_continua.services.BibliotecaService;
import cl.mineduc.came.apoyo_mejora_continua.services.DocumentsService;
import cl.mineduc.came.apoyo_mejora_continua.services.PeriodService;
import cl.mineduc.came.apoyo_mejora_continua.services.UsuarioService;

@Controller
@RequestMapping("biblioteca")
public class BibliotecaController{
    private static Logger logger = LogManager.getLogger(BibliotecaController.class);
	
	@Autowired
	private PeriodService periodService;

	@Autowired
	BibliotecaService bibliotecaService;
	
	@Autowired
	ElementoListaRepo elementRepo;
	
	@Autowired
	private UsuarioService usuarioService;

	@GetMapping(value = "/getDocuments", produces = "application/json")
	public ResponseEntity<Object> getDocuments(BibliotecaRequestDTO request) {

		List<BibliotecaListDTO> documents = bibliotecaService.getDocuments(request);
		return new ResponseEntity<Object>(documents, HttpStatus.OK);
	}
	
	@GetMapping(value = "/getUpdateBiblioteca", produces = "application/json")
	public ResponseEntity<Object> getUpdateBiblioteca(String idBiblioteca, Integer idRegion, Integer idDeprov) {

		UpdateBibliotecaRequestDTO documents = bibliotecaService.getUpdateBiblioteca(idBiblioteca, idRegion, idDeprov);
		return new ResponseEntity<Object>(documents, HttpStatus.OK);
	}
	
	@PostMapping(value = "/getDeleteDocuments")
	public ResponseEntity<Object> getDeleteDocuments(@RequestParam(name = "idBiblioteca") String idBiblioteca) {					
		bibliotecaService.getDeleteDocuments(idBiblioteca);		
		return  new ResponseEntity<Object>(true, HttpStatus.OK);
	}
	
	@GetMapping(value = "/getProvincias", produces = "application/json")
	public ResponseEntity<Object> getProvincias(String idRegion) {

		List<SelectorDTO> provincias = bibliotecaService.getDeprovByRegion(Integer.parseInt(idRegion));
		return new ResponseEntity<Object>(provincias, HttpStatus.OK);
	}

	@GetMapping(value = "/export/excel")
	public void exportToExcel(HttpServletResponse response, BibliotecaRequestDTO request) throws IOException {
		response.setContentType("application/octet-stream");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=documents_" + currentDateTime + ".xlsx";
		response.setHeader(headerKey, headerValue);
		
		List<BibliotecaListDTO> records = bibliotecaService.getDocuments(request);

		//DocumentosExcel excelExporter = new DocumentosExcel(records);
		//excelExporter.export(response);
	}



	@PostMapping(value = "/setDocument", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object> setDocument(@Valid @RequestBody SetBibliotecaRequestDTO record, BindingResult result) {
		if (result.hasErrors()) {
			return new ResponseEntity<Object>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
		}

		bibliotecaService.setDocument(record);
		return new ResponseEntity<Object>(true, HttpStatus.OK);
	}
	
	@PostMapping(value = "/updateBiblioteca", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object> updateBiblioteca(@Valid @RequestBody UpdateBibliotecaRequestDTO record, BindingResult result) {
		if (result.hasErrors()) {
			return new ResponseEntity<Object>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
		}

		bibliotecaService.updateBiblioteca(record);
		return new ResponseEntity<Object>(true, HttpStatus.OK);
	}

	@GetMapping(value = "index")
	public ModelAndView index(@RequestParam(required = false) Integer error){
		logger.debug("documents view request... ");
		ModelAndView mv = new ModelAndView();
		String[] years = { "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030" };
		Integer year = Calendar.getInstance().get(Calendar.YEAR);
		List<SelectorDTO> tipoCategoria = bibliotecaService.getCategoria();
		List<ProfileSelectorDTO> profiles = bibliotecaService.getProfiles();
		List<SelectorDTO> regiones = bibliotecaService.getRegiones();

		mv.addObject("regiones", regiones);
		mv.addObject("years", years);
		mv.addObject("currentYear", year);
		mv.addObject("tipoCategoria", tipoCategoria);
		mv.addObject("profiles", profiles);
		mv.addObject("error",error);
		return mv;
	}


}
