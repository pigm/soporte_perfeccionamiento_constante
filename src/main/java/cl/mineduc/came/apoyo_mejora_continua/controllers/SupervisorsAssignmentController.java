package cl.mineduc.came.apoyo_mejora_continua.controllers;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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

import cl.mineduc.came.apoyo_mejora_continua.Excel.SupervisoresExcel;
import cl.mineduc.came.apoyo_mejora_continua.dto.assignment.AssignDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.assignment.AssignSupervisorDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.assignment.AssignmentDetailDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.assignment.AssignmentListDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.assignment.AssignmentRequestDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.establishment.EstablecimientosRequestDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.networks.NetworkDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.shared.SelectorDTO;
import cl.mineduc.came.apoyo_mejora_continua.services.SupervisorsAssignmentService;

@Controller
@RequestMapping("assignment")
public class SupervisorsAssignmentController {
    private static Logger logger = LogManager.getLogger(SupervisorsAssignmentController.class);

    @Autowired
    SupervisorsAssignmentService supervisorsAssignmentService;

    @GetMapping(value = "index")
    public ModelAndView index(@RequestParam(required = false) Integer error) {
        logger.debug("networks view request... ");
        ModelAndView mv = new ModelAndView();

        List<SelectorDTO> regiones = supervisorsAssignmentService.getRegiones();
        List<SelectorDTO> tipoRed = supervisorsAssignmentService.getTipoRed();
        List<SelectorDTO> tipoCategoria = supervisorsAssignmentService.getCategoria();

        mv.addObject("regiones", regiones);
        mv.addObject("tipoRed", tipoRed);
        mv.addObject("tipoCategoria", tipoCategoria);
        mv.addObject("error", error);
        return mv;
    }

    @GetMapping(value = "/getProvincias", produces = "application/json")
    public ResponseEntity<Object> getProvincias(String idRegion) {
        List<SelectorDTO> provincias = supervisorsAssignmentService.getDeprovByRegion(Integer.parseInt(idRegion));
        return new ResponseEntity<Object>(provincias, HttpStatus.OK);
    }

    @GetMapping(value = "/getComunas", produces = "application/json")
    public ResponseEntity<Object> getComunas(String idDeprov) {
        List<SelectorDTO> comunas = supervisorsAssignmentService.getComunasByDeprov(Integer.parseInt(idDeprov));
        return new ResponseEntity<Object>(comunas, HttpStatus.OK);
    }

    @GetMapping(value = "/getAssignments", produces = "application/json")
    public ResponseEntity<Object> getAssignments(AssignmentRequestDTO request) {
        List<AssignmentListDTO> records = supervisorsAssignmentService.getAssignments(request);
        return new ResponseEntity<Object>(records, HttpStatus.OK);
    }

    @GetMapping(value = "/getAssignmentsDetails", produces = "application/json")
    public ResponseEntity<Object> getNetworkDetail(Long idRed, Long idAsignacionSupervisor) {
        AssignmentDetailDTO record = supervisorsAssignmentService.getAssignmentDetail(idRed, idAsignacionSupervisor);
        return new ResponseEntity<Object>(record, HttpStatus.OK);

    }

    @GetMapping(value = "/getAssignmentsSet", produces = "application/json")
    public ResponseEntity<Object> getAssignSupervisorSet(Long id) {
        AssignSupervisorDTO record = supervisorsAssignmentService.getAssignSupervisor(id);
        return new ResponseEntity<Object>(record, HttpStatus.OK);
    }

    @PostMapping(value = "/setAssignSupervisor", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> setAssignSupervisor(@Valid @RequestBody AssignDTO record, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<Object>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        supervisorsAssignmentService.setAssign(record);
        return new ResponseEntity<Object>(true, HttpStatus.OK);
    }

    @GetMapping(value = "/export/excel")
    public void exportToExcel(HttpServletResponse response, AssignmentRequestDTO request) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
         
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Supervisores_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

		// EstablecimientosRequestDTO filter = new EstablecimientosRequestDTO();
		// filter.setRegion(1);
        // filter.setComuna(11);
        List<AssignmentListDTO> records = supervisorsAssignmentService.getAssignments(request);
        //List<EstablecimientosDTO> records = establishmentService.getEstablecimientos(filter);
         
        SupervisoresExcel excelExporter = new SupervisoresExcel(records);
         
        excelExporter.export(response);    
    } 
}
