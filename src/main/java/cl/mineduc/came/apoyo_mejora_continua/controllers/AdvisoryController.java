package cl.mineduc.came.apoyo_mejora_continua.controllers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
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

import cl.mineduc.came.apoyo_mejora_continua.Excel.AdvisorysExcel;
import cl.mineduc.came.apoyo_mejora_continua.dto.advisory.AdvisoryDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.advisory.AdvisoryListDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.advisory.AdvisoryRequestDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.shared.SelectorDTO;
import cl.mineduc.came.apoyo_mejora_continua.helpers.StringHelper;
import cl.mineduc.came.apoyo_mejora_continua.services.AdvisoryService;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("advisory")
public class AdvisoryController {
    private static Logger logger = LogManager.getLogger(AdvisoryController.class);

    @Autowired
    AdvisoryService advisoryService;

    @GetMapping(value = "/isValidStartDate", produces = "application/json")
    public ResponseEntity<Object> isValidStartDate(String startDate) {
        Date inicio = StringHelper.convertToDate(startDate);
        Boolean isValid = advisoryService.isValidStartDate(inicio);
        return new ResponseEntity<Object>(isValid, HttpStatus.OK);
    }

    @GetMapping(value = "/getProvincias", produces = "application/json")
    public ResponseEntity<Object> getProvincias(String idRegion) {
        List<SelectorDTO> provincias = advisoryService.getDeprovByRegion(Integer.parseInt(idRegion));
        return new ResponseEntity<Object>(provincias, HttpStatus.OK);
    }

    @GetMapping(value = "/getComunas", produces = "application/json")
    public ResponseEntity<Object> getComunas(String idDeprov) {
        List<SelectorDTO> comunas = advisoryService.getComunasByDeprov(Integer.parseInt(idDeprov));
        return new ResponseEntity<Object>(comunas, HttpStatus.OK);
    }

    @GetMapping(value = "/getRbd", produces = "application/json")
    public ResponseEntity<Object> getProvincias(Integer regionId, Integer deprovId, Integer comunaId) {
        List<SelectorDTO> records = advisoryService.getEstablecimientos(regionId, deprovId, comunaId);
        return new ResponseEntity<Object>(records, HttpStatus.OK);
    }

    @PostMapping(value = "/setAdvisory", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> setAdvisory(@Valid @RequestBody AdvisoryDTO record, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<Object>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
        }

        advisoryService.setAdvisory(record);

        return new ResponseEntity<Object>(true, HttpStatus.OK);
    }

    @GetMapping(value = "/getAdvisory", produces = "application/json")
    public ResponseEntity<Object> getAdvisory(AdvisoryRequestDTO request) {
        List<AdvisoryListDTO> records = advisoryService.getAdvisory(request);
        return new ResponseEntity<Object>(records, HttpStatus.OK);
    }

    @GetMapping(value = "/export/excel")
    public void exportToExcel(HttpServletResponse response, AdvisoryRequestDTO request) throws IOException {
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=asesorias.xlsx";
        response.setHeader(headerKey, headerValue);

        List<AdvisoryListDTO> records = advisoryService.getAdvisory(request);

        AdvisorysExcel excelExporter = new AdvisorysExcel(records);

        excelExporter.export(response);
    }

    @GetMapping(value = "/getAdvisoryDetail", produces = "application/json")
    public ResponseEntity<Object> getAdvisoryDetail(Long idAsesoriaDirecta) {
        AdvisoryListDTO record = advisoryService.getAdvisory(idAsesoriaDirecta);
        return new ResponseEntity<Object>(record, HttpStatus.OK);
    }

    @PostMapping(value = "/setStatusAdvisory", produces = "application/json")
    public ResponseEntity<Object> setStatusAdvisory(@RequestParam String idAsesoriaDirecta, @RequestParam Boolean status) {

        advisoryService.setAdvisoryStatus(Long.parseLong(idAsesoriaDirecta), status);
        return new ResponseEntity<Object>(true, HttpStatus.OK);
    }

    @GetMapping(value = "index")
    public ModelAndView index(@RequestParam(required = false) Integer error) {
        logger.debug("advisory view request... ");
        ModelAndView mv = new ModelAndView();

        List<SelectorDTO> regiones = advisoryService.getRegiones();

        mv.addObject("regiones", regiones);
        mv.addObject("error", error);
        return mv;
    }
}
