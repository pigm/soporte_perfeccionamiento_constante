package cl.mineduc.came.apoyo_mejora_continua.controllers;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cl.mineduc.came.apoyo_mejora_continua.Excel.EstablecimientosExcel;
import cl.mineduc.came.apoyo_mejora_continua.dto.establishment.EstablecimientosDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.establishment.EstablecimientosRequestDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.shared.SelectorDTO;
import cl.mineduc.came.apoyo_mejora_continua.modelo.api.Establecimientos;
import cl.mineduc.came.apoyo_mejora_continua.services.EstablishmentService;

@Controller
@RequestMapping("establishment")
public class EstablishmentController {
    private static Logger logger = LogManager.getLogger(EstablishmentController.class);

	@Autowired
	private EstablishmentService establishmentService;

	@GetMapping(value = "/getProvincias", produces = "application/json")
	public ResponseEntity<Object> getProvincias(String idRegion) {

		List<SelectorDTO> provincias = establishmentService.getDeprovByRegion(Integer.parseInt(idRegion));
		return new ResponseEntity<Object>(provincias, HttpStatus.OK);
	}

	@GetMapping(value = "/getEstablecimientos", produces = "application/json")
	public ResponseEntity<Object> getEstablecimientos(EstablecimientosRequestDTO filter) {
		List<EstablecimientosDTO> records = establishmentService.getEstablecimientos(filter);
		return new ResponseEntity<Object>(records, HttpStatus.OK);
	}

	@GetMapping(value = "/getEstablecimiento", produces = "application/json")
	public ResponseEntity<Object> getEstablecimiento(String rbd) {
		Establecimientos record = establishmentService.getEstablecimientos(Integer.parseInt(rbd.split("-")[0]));
		return new ResponseEntity<Object>(record, HttpStatus.OK);
	}


	@GetMapping(value = "/export/excel")
    public void exportToExcel(HttpServletResponse response, EstablecimientosRequestDTO filter) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
         
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);
		// EstablecimientosRequestDTO filter = new EstablecimientosRequestDTO();
		// filter.setRegion(1);
		// filter.setComuna(11);
        List<EstablecimientosDTO> records = establishmentService.getEstablecimientos(filter);
         
        EstablecimientosExcel excelExporter = new EstablecimientosExcel(records);
         
        excelExporter.export(response);    
    }  

    @GetMapping(value = "index")
	public ModelAndView index(@RequestParam(required = false) Integer error){
		logger.debug("max assing view request... ");
		ModelAndView mv = new ModelAndView();
		
		List<SelectorDTO> regiones = establishmentService.getRegiones();

		mv.addObject("regiones", regiones);
		mv.addObject("error",error);
		return mv;
	}   
}
