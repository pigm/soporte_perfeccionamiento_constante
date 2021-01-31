package cl.mineduc.came.apoyo_mejora_continua.controllers;

import java.util.Calendar;
import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
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

import cl.mineduc.came.apoyo_mejora_continua.dto.message.MessageDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.message.MessageProfileDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.message.MessageRequestDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.shared.SelectorDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.users.ProfileSelectorDTO;
import cl.mineduc.came.apoyo_mejora_continua.services.MessengerService;

@Controller
@RequestMapping("messenger")
public class MessengerController {
    private static Logger logger = LogManager.getLogger(MessengerController.class);

    @Autowired
    MessengerService messengerService;

    @GetMapping(value = "index")
    public ModelAndView index(@RequestParam(required = false) Integer error) {
        //logger.debug("Mensajeria view request... ");
        ModelAndView mv = new ModelAndView();

        List<SelectorDTO> regiones = messengerService.getRegiones();
        List<SelectorDTO> provincias = messengerService.getAllDeprov();
        List<ProfileSelectorDTO> perfiles = messengerService.getProfiles();
        String year = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
        mv.addObject("provincias", provincias);
        mv.addObject("regiones", regiones);
        
        mv.addObject("perfiles", perfiles);
        mv.addObject("anio",year);
        mv.addObject("error", error);
        return mv;
    }

    @GetMapping(value = "/getProvinciasFilter", produces = "application/json")
    public ResponseEntity<Object> getProvinciasFilter(Integer idRegion) {
        List<SelectorDTO> provincias = messengerService.getDeprovByRegion(idRegion);
        return new ResponseEntity<Object>(provincias, HttpStatus.OK);
    }

    @GetMapping(value = "/getProvincias", produces = "application/json")
    public ResponseEntity<Object> getProvincias(String[] idRegion) {
        List<SelectorDTO> provincias = messengerService.getDeprovByRegion(idRegion);
        return new ResponseEntity<Object>(provincias, HttpStatus.OK);
        
    }

    @PostMapping(value = "/setMessage", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> setMessage(@Valid @RequestBody MessageDTO record, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<Object>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
        }

        messengerService.setMessage(record);        
        return new ResponseEntity<Object>(true, HttpStatus.OK);
    }

    @GetMapping(value = "/getMessages", produces = "application/json")
    public ResponseEntity<Object> getMessages(MessageRequestDTO request) {
        List<MessageProfileDTO> records = messengerService.getMessages(request);

        return new ResponseEntity<Object>(records, HttpStatus.OK);
    }

    @GetMapping(value = "/getMessageDetail", produces = "application/json")
    public ResponseEntity<Object> getMessageDetail(Long idMessage) {
        MessageDTO record = messengerService.getMessageDetail(idMessage);

        return new ResponseEntity<Object>(record, HttpStatus.OK);

    }  
    
}
