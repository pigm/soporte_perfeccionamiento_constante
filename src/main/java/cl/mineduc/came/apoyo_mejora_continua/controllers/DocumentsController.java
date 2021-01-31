package cl.mineduc.came.apoyo_mejora_continua.controllers;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
import org.springframework.web.servlet.view.RedirectView;

import cl.mineduc.came.apoyo_mejora_continua.Excel.DocumentosExcel;
import cl.mineduc.came.apoyo_mejora_continua.dto.documents.DocumentsDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.documents.DocumentsListDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.documents.DocumentsRequestDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.documents.SetDocumentRequestDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.period.PeriodoDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.period.PeriodoDocumentosProvincialesDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.period.PeriodoDocumentosRegionalesDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.shared.SelectorDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.users.UserEnvDTO;
import cl.mineduc.came.apoyo_mejora_continua.helpers.UserHelper;
import cl.mineduc.came.apoyo_mejora_continua.services.DocumentsService;
import cl.mineduc.came.apoyo_mejora_continua.services.PeriodService;
import cl.mineduc.came.apoyo_mejora_continua.services.UsuarioService;

@Controller
@RequestMapping("documents")
public class DocumentsController {
    private static Logger logger = LogManager.getLogger(DocumentsController.class);
	
	@Autowired
	private PeriodService periodService;

	@Autowired
	DocumentsService documentsService;

	@Autowired
	UsuarioService usuarioService;

	@GetMapping(value = "/getProvincias", produces = "application/json")
	public ResponseEntity<Object> getProvincias(String idRegion) {

		List<SelectorDTO> provincias = documentsService.getDeprovByRegion(Integer.parseInt(idRegion));
		return new ResponseEntity<Object>(provincias, HttpStatus.OK);
	}

	@GetMapping(value = "/getDocuments", produces = "application/json")
	public ResponseEntity<Object> getDocuments(DocumentsRequestDTO request) {

		List<DocumentsListDTO> documents = documentsService.getDocuments(request);
		return new ResponseEntity<Object>(documents, HttpStatus.OK);
	}

	@GetMapping(value = "/export/excel")
	public void exportToExcel(HttpServletResponse response, DocumentsRequestDTO request) throws IOException {
		response.setContentType("application/octet-stream");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=documents_" + currentDateTime + ".xlsx";
		response.setHeader(headerKey, headerValue);
		
		List<DocumentsListDTO> records = documentsService.getDocuments(request);

		DocumentosExcel excelExporter = new DocumentosExcel(records);

		excelExporter.export(response);
	}



	@GetMapping(value = "/getDocumentosRegionales", produces = "application/json")
	public ResponseEntity<Object> getDocumentosRegionales(Integer idRegion) {

		DocumentsDTO document = documentsService.getDocumentosRegionales(idRegion);
		return new ResponseEntity<Object>(document, HttpStatus.OK);
	}

	@GetMapping(value = "/getDocumentosProvinciales", produces = "application/json")
	public ResponseEntity<Object> getDocumentosProvinciales(Integer idRegion, Integer idDeprov) {

		DocumentsDTO document = documentsService.getDocumentosProvinciales(idRegion, idDeprov);
		return new ResponseEntity<Object>(document, HttpStatus.OK);
	}

	
	@PostMapping(value = "/setDocumentRegional", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object> setProfile(@Valid @RequestBody SetDocumentRequestDTO record, BindingResult result) {
		if (result.hasErrors()) {
			return new ResponseEntity<Object>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
		}

		documentsService.setDocumentRegional(record);

		return new ResponseEntity<Object>(true, HttpStatus.OK);
	}

	@PostMapping(value = "/setDocumentProvincial", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object> setDocumentProvincial(@Valid @RequestBody SetDocumentRequestDTO record, BindingResult result) {
		if (result.hasErrors()) {
			return new ResponseEntity<Object>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
		}

		documentsService.setDocumentProvincial(record);

		return new ResponseEntity<Object>(true, HttpStatus.OK);
	}

	@PostMapping(value = "/setReadDocument", produces = "application/json")
	public ResponseEntity<Object> setReadDocumentRegional(@RequestParam(name = "documentoId") String documentoId, 
			@RequestParam(name = "tipo") String tipo) {
		
		documentsService.setReadDocument(documentoId, tipo);

		return new ResponseEntity<Object>(true, HttpStatus.OK);
	}

    @GetMapping(value = "index")
	public ModelAndView index(@RequestParam(required = false) Integer error){
		logger.debug("documents view request... ");
		ModelAndView mv = new ModelAndView();
		
		UserEnvDTO userEnv = usuarioService.getUserEnv(UserHelper.getUsuarioRegistrado().getIdUsuario());
		if (userEnv.getIdPerfil().equals("2416264829766468643")) {
			return new ModelAndView("redirect:/documents/index-adm");
		} else if(userEnv.getCurrentPeriod() == null){
			return new ModelAndView("redirect:/home/index");
		} else{
			int year = Calendar.getInstance().get(Calendar.YEAR);
			PeriodoDTO periodo = periodService.getPeriodByYear(year);

			PeriodoDocumentosRegionalesDTO docRegional = documentsService.getPeriodoDocumentosRegionales(year);
			PeriodoDocumentosProvincialesDTO docProvinciales = documentsService.getPeriodoDocumentosProvinciales(year);

			mv.addObject("docRegional", docRegional);
			mv.addObject("docProvinciales", docProvinciales);
			mv.addObject("periodo", periodo);
			mv.addObject("error", error);
			return mv;
		}
	}

	@GetMapping(value = "index-adm")
	public ModelAndView indexAdm(@RequestParam(required = false) Integer error){
		logger.debug("documents view request... ");
		ModelAndView mv = new ModelAndView();
		String[] years = { "2020", "2021", "2022", "2023", "2024", "2025", "2026" };

		int year = Calendar.getInstance().get(Calendar.YEAR);
		
		List<SelectorDTO> regiones = documentsService.getRegiones();

		mv.addObject("currentYear", year);
		mv.addObject("regiones", regiones);
		mv.addObject("years", years);
		mv.addObject("error",error);
		return mv;
	}
}
