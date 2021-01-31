package cl.mineduc.came.apoyo_mejora_continua.controllers;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
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

import cl.mineduc.came.apoyo_mejora_continua.Excel.PlanningExcel;
import cl.mineduc.came.apoyo_mejora_continua.dto.planning.AsesoriaDirectaDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.planning.AsesoriaRedDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.planning.FocoDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.planning.PlanningListDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.planning.PlanningRequestDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.planning.RegistraSesionDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.planning.RegistraSesionRedDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.planning.SesionDetailDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.planning.SesionFocoAccionMejoraDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.planning.SesionInfoDetailDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.planning.SesionRedDetailDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.planning.SesionRedInfoDetailDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.shared.SelectorDTO;
import cl.mineduc.came.apoyo_mejora_continua.services.PlanningService;

@Controller
@RequestMapping("planning")
public class PlanningController {
    private static Logger logger = LogManager.getLogger(PlanningController.class);
    
    @Autowired
    PlanningService planningService;

    @GetMapping(value = "/getFocos", produces = "application/json")
    public ResponseEntity<Object> getFocos(String idRegion) {
        List<FocoDTO> focos = planningService.getFocos();
        return new ResponseEntity<Object>(focos, HttpStatus.OK);
    }

    @GetMapping(value = "/getProvincias", produces = "application/json")
    public ResponseEntity<Object> getProvincias(String idRegion) {
        List<SelectorDTO> provincias = planningService.getDeprovByRegion(Integer.parseInt(idRegion));
        return new ResponseEntity<Object>(provincias, HttpStatus.OK);
    }

    @GetMapping(value = "/getComunas", produces = "application/json")
    public ResponseEntity<Object> getComunas(String idDeprov) {
        List<SelectorDTO> comunas = planningService.getComunasByDeprov(Integer.parseInt(idDeprov));
        return new ResponseEntity<Object>(comunas, HttpStatus.OK);
    }

    @GetMapping(value = "/getSupUserNames", produces = "application/json")
    public ResponseEntity<Object> getSupUserNames(Integer regionId, Integer deprovId) {
        List<String> supervisors = planningService.getSupUserNames(regionId, deprovId);
        return new ResponseEntity<Object>(supervisors, HttpStatus.OK);
    }

    @GetMapping(value = "/getPlanning", produces = "application/json")
    public ResponseEntity<Object> getPlanning(PlanningRequestDTO request) {
        List<PlanningListDTO> records = planningService.getPlanning(request);
        return new ResponseEntity<Object>(records, HttpStatus.OK);
    }

    @GetMapping(value = "/export/excel")
    public void exportToExcel(HttpServletResponse response, PlanningRequestDTO request) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=planning_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<PlanningListDTO> records = planningService.getPlanning(request);

        PlanningExcel excelExporter = new PlanningExcel(records);

        excelExporter.export(response);
    }

    @GetMapping(value = "/getSesionDetail", produces = "application/json")
    public ResponseEntity<Object> getSesionDetail(String idSesion) {
        SesionDetailDTO record = planningService.getSesionDetail(idSesion);
        return new ResponseEntity<Object>(record, HttpStatus.OK);
    }

    @GetMapping(value = "/getSesionRedDetail", produces = "application/json")
    public ResponseEntity<Object> getSesionRedDetail(String idSesion) {
        SesionRedDetailDTO record = planningService.getSesionRedDetail(idSesion);
        return new ResponseEntity<Object>(record, HttpStatus.OK);
    }

    @GetMapping(value = "/getAccionesFoco", produces = "application/json")
    public ResponseEntity<Object> getAccionesFoco(String idSesion, String idFoco) {
        List<SesionFocoAccionMejoraDTO> records = planningService.getAccionesFoco(idSesion, idFoco);
        return new ResponseEntity<Object>(records, HttpStatus.OK);
    }

    @GetMapping(value = "/getSesionInfoDetail", produces = "application/json")
    public ResponseEntity<Object> getSesionInfoDetail(String asesoriaId) {
        SesionInfoDetailDTO record = planningService.getSesionInfoDetail(asesoriaId);
        return new ResponseEntity<Object>(record, HttpStatus.OK);
    }

    @GetMapping(value = "/getSesionRedInfoDetail", produces = "application/json")
    public ResponseEntity<Object> getSesionRedInfoDetail(String asesoriaId) {
        SesionRedInfoDetailDTO record = planningService.getSesionRedInfoDetail(asesoriaId);        
        return new ResponseEntity<Object>(record, HttpStatus.OK);
    }

    @PostMapping(value = "/setPrimeraPlanificacion", produces = "application/json")
    public ResponseEntity<Object> setPrimeraPlanificacion(
            @RequestParam(required = true) String idAsesoria, 
            @RequestParam(required = true) String fechaPlanificacion) {

        planningService.setPrimeraPlanificacion(idAsesoria, fechaPlanificacion);

        return new ResponseEntity<Object>(true, HttpStatus.OK);
    }

    @PostMapping(value = "/setRegistraSesion", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> setRegistraSesion(@Valid @RequestBody RegistraSesionDTO record) {

        planningService.setRegistraSesion(record);

        return new ResponseEntity<Object>(true, HttpStatus.OK);
    }

    @PostMapping(value = "/setRegistraSesionRed", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> setRegistraSesionRed(@Valid @RequestBody RegistraSesionRedDTO record) {

        planningService.setRegistraSesionRed(record);
        
        return new ResponseEntity<Object>(true, HttpStatus.OK);
    }

    @GetMapping(value = "index")
    public ModelAndView index(@RequestParam(required = false) Integer error) {
        logger.debug("planning view request... ");
        ModelAndView mv = new ModelAndView();        

        List<SelectorDTO> regiones = planningService.getRegiones();
        List<SelectorDTO> tipoRed = planningService.getTipoRed();
        
        mv.addObject("regiones", regiones);
        mv.addObject("tipoRed", tipoRed);
        mv.addObject("error", error);
        return mv;
    }

    @GetMapping(value = "registro-directa")
    public ModelAndView registroDirecta(@RequestParam(required = true) String asesoriaId, @RequestParam(required = false) Integer error) {
        logger.debug("advisory view request... ");
        ModelAndView mv = new ModelAndView();
        AsesoriaDirectaDTO planning = planningService.getPlanningByAdvisory(asesoriaId);
        
        boolean puedePlanificar = !planning.getSesiones().isEmpty()
                && planning.getSesiones().stream().noneMatch(s -> s.getFechaRalizada() == null);

        mv.addObject("puedePlanificar", puedePlanificar);
        mv.addObject("maxFoco", planningService.getMaxFoco());
        mv.addObject("planning", planning);
        mv.addObject("error", error);
        return mv;
    }

    @GetMapping(value = "registro-red")
    public ModelAndView registroRed(
            @RequestParam(required = true) String redId,
            @RequestParam(required = false) Integer error) {
        logger.debug("advisory view request... ");
        ModelAndView mv = new ModelAndView();
        AsesoriaRedDTO planning = planningService.getPlanningByRed(redId);

        boolean puedePlanificar = !planning.getSesiones().isEmpty() && planning.getSesiones().stream().noneMatch(s -> s.getFechaRalizada() == null);

        mv.addObject("puedePlanificar", puedePlanificar);
        mv.addObject("maxFoco", planningService.getMaxFoco());
        mv.addObject("planning", planning);
        mv.addObject("error", error);
        return mv;
    }
}
