package cl.mineduc.came.apoyo_mejora_continua.controllers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

import cl.mineduc.came.apoyo_mejora_continua.Excel.NetworksExcel;
import cl.mineduc.came.apoyo_mejora_continua.dto.networks.EstablecimientosRequestDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.networks.NetworkDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.networks.NetworkDetailDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.networks.NetworksListDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.networks.NetworksRequestDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.shared.SelectorDTO;
import cl.mineduc.came.apoyo_mejora_continua.helpers.StringHelper;
import cl.mineduc.came.apoyo_mejora_continua.services.NetworksService;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("networks")
public class NetworksController {
    private static Logger logger = LogManager.getLogger(NetworksController.class);

    @Autowired
    NetworksService networksService;

    @GetMapping(value = "/getProvincias", produces = "application/json")
    public ResponseEntity<Object> getProvincias(String idRegion) {
        List<SelectorDTO> provincias = networksService.getDeprovByRegion(Integer.parseInt(idRegion));
        return new ResponseEntity<Object>(provincias, HttpStatus.OK);
    }

    @GetMapping(value = "/getComunas", produces = "application/json")
    public ResponseEntity<Object> getComunas(String idDeprov) {
        List<SelectorDTO> comunas = networksService.getComunasByDeprov(Integer.parseInt(idDeprov));
        return new ResponseEntity<Object>(comunas, HttpStatus.OK);
    }

    @GetMapping(value = "/isValidStartDate", produces = "application/json")
    public ResponseEntity<Object> isValidStartDate(String startDate) {
        Date inicio = StringHelper.convertToDate(startDate);       
        Boolean isValid = networksService.isValidStartDate(inicio);
        return new ResponseEntity<Object>(isValid, HttpStatus.OK);
    }

    @GetMapping(value = "/isValidName", produces = "application/json")
    public ResponseEntity<Object> isValidName(String nombreRed) {       
        Boolean isValid = networksService.isValidName(nombreRed);
        return new ResponseEntity<Object>(isValid, HttpStatus.OK);
    }

    @GetMapping(value = "/getEstablecimientos", produces = "application/json")
    public ResponseEntity<Object> getEstablecimientos(Integer idRegion, Integer idDeprov, Integer idComuna, String idTipoRed) {
        EstablecimientosRequestDTO filter = new EstablecimientosRequestDTO();
        filter.setIdRegion(idRegion);
        filter.setIdDeprov(idDeprov);
        filter.setIdComuna(idComuna);
        filter.setIdTipoRed(idTipoRed);
        List<SelectorDTO> records = networksService.getEstablecimientos(filter);
        return new ResponseEntity<Object>(records, HttpStatus.OK);
    }

    @GetMapping(value = "/getNetworkDetail", produces = "application/json")
    public ResponseEntity<Object> getNetworkDetail(Long idRed){
        NetworkDetailDTO record = networksService.getNetworkDetail(idRed);
        return new ResponseEntity<Object>(record, HttpStatus.OK);
    }


    @PostMapping(value = "/setNetwork", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> setNetwork(@Valid @RequestBody NetworkDTO record, BindingResult result,HttpServletRequest request) {
        if (result.hasErrors()) {
            return new ResponseEntity<Object>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        
        networksService.setNetwork(record);        
        return new ResponseEntity<Object>(true, HttpStatus.OK);
    }

    @PostMapping(value = "/setStatusEstablecimiento", produces = "application/json")
    public ResponseEntity<Object> setStatusEstablecimiento(@RequestParam String idRed, 
                                                           @RequestParam String establecimiento, 
                                                           @RequestParam Boolean status) {
      
        networksService.setStatusEstablecimiento(idRed, establecimiento, status);
        return new ResponseEntity<Object>(true, HttpStatus.OK);
    }

    @GetMapping(value = "/getNetworks", produces = "application/json")
    public ResponseEntity<Object> getNetwork(NetworksRequestDTO request) {
        List<NetworksListDTO> records = networksService.getNetworks(request);
        return new ResponseEntity<Object>(records, HttpStatus.OK);
    }

    @GetMapping(value = "/export/excel")
    public void exportToExcel(HttpServletResponse response, NetworksRequestDTO request) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);
       
        List<NetworksListDTO> records = networksService.getNetworks(request);

        NetworksExcel excelExporter = new NetworksExcel(records);

        excelExporter.export(response);
    }


    @GetMapping(value = "index")
    public ModelAndView index(@RequestParam(required = false) Integer error) {
        logger.debug("networks view request... ");
        ModelAndView mv = new ModelAndView();

        List<SelectorDTO> regiones = networksService.getRegiones();
        List<SelectorDTO> tipoRed = networksService.getTipoRed();

        mv.addObject("regiones", regiones);
        mv.addObject("tipoRed", tipoRed);
        mv.addObject("error", error);
        return mv;
    }
}
