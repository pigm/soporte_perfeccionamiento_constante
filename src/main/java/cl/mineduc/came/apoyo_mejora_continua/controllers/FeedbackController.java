package cl.mineduc.came.apoyo_mejora_continua.controllers;

import org.springframework.http.HttpStatus;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cl.mineduc.came.apoyo_mejora_continua.dto.feedback.FeedbackDetailDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.feedback.FeedbackListDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.feedback.FeedbackRequestDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.feedback.PlanificarFeedbackDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.feedback.SetFeedbackResponseDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.shared.SelectorDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.feedback.SupervisorDataDTO;
import cl.mineduc.came.apoyo_mejora_continua.services.FeedbackService;

@Controller
@RequestMapping("feedback")
public class FeedbackController {
    private static Logger logger = LogManager.getLogger(FeedbackController.class);

    @Autowired
    private FeedbackService feedbackService;

    @GetMapping(value = "/getProvincias", produces = "application/json")
    public ResponseEntity<Object> getProvincias(String idRegion) {
        List<SelectorDTO> provincias = feedbackService.getDeprovByRegion(Integer.parseInt(idRegion));
        return new ResponseEntity<Object>(provincias, HttpStatus.OK);
    }

    @GetMapping(value = "/getSupUserNames", produces = "application/json")
    public ResponseEntity<Object> getSupUserNames(Integer idRegion, Integer idDeprov) {
        List<String> supervisors = feedbackService.getSupUserNames(idRegion, idDeprov);
        return new ResponseEntity<Object>(supervisors, HttpStatus.OK);
    }

    @GetMapping(value = "/getSupervisorData", produces = "application/json")
    public ResponseEntity<Object> getSupervisorData(String userName) {
        SupervisorDataDTO supData = feedbackService.getSupervisorData(userName);
        return new ResponseEntity<Object>(supData, HttpStatus.OK);
    }

    @GetMapping(value = "/getFeedback", produces = "application/json")
    public ResponseEntity<Object> getFeedBack(FeedbackRequestDTO request) {
        List<FeedbackListDTO> records = feedbackService.getFeedBack(request);
        return new ResponseEntity<Object>(records, HttpStatus.OK);
    }

    @GetMapping(value = "/getFeedBackOthers", produces = "application/json")
    public ResponseEntity<Object> getFeedBackOthers(String idFeedback) {
        List<FeedbackListDTO> records = feedbackService.getFeedBackOthers(idFeedback);
        return new ResponseEntity<Object>(records, HttpStatus.OK);
    }

    @GetMapping(value = "/getFeedbackDetail", produces = "application/json")
    public ResponseEntity<Object> getFeedbackDetail(String idFeedback) {
        FeedbackDetailDTO record = feedbackService.getFeedbackDetail(idFeedback);
        return new ResponseEntity<Object>(record, HttpStatus.OK);
    }

    @PostMapping(value = "/setPlanificarFeedback", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> setPlanificarFeedback(@Valid @RequestBody PlanificarFeedbackDTO record) {
        feedbackService.setPlanificarFeedback(record);
        return new ResponseEntity<Object>(true, HttpStatus.OK);
    }

    @PostMapping(value = "/setFeedback", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> setFeedback(@Valid @RequestBody FeedbackDetailDTO record) {
        SetFeedbackResponseDTO response = feedbackService.setFeedback(record);
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }

    @GetMapping(value = "registra-feedback")
    public ModelAndView registraFeedback(@RequestParam(required = true) String idFeedback, @RequestParam(required = false) Integer error) {
        logger.debug("registra feedback view request... ");
        ModelAndView mv = new ModelAndView();
        FeedbackDetailDTO feedback = feedbackService.getFeedbackDetail(idFeedback);

        mv.addObject("feedback", feedback);
        mv.addObject("error", error);
        return mv;
    }

    @GetMapping(value = "detalle-feedback")
    public ModelAndView detalleFeedback(
            @RequestParam(required = true) String idFeedback,
            @RequestParam(required = false) Integer error) {
        logger.debug("detalle feedback view request... ");
        ModelAndView mv = new ModelAndView();
        FeedbackDetailDTO feedback = feedbackService.getFeedbackDetail(idFeedback);

        mv.addObject("feedback", feedback);
        mv.addObject("error", error);
        return mv;
    }

    @GetMapping(value = "index")
    public ModelAndView index(@RequestParam(required = false) Integer error) {
        logger.debug("feedback view request... ");
        ModelAndView mv = new ModelAndView();
     
        List<String> jefesTecnico = this.feedbackService.getJefeTecUserNames();
        List<SelectorDTO> regiones = this.feedbackService.getRegiones();

        Integer year = Calendar.getInstance().get(Calendar.YEAR);

        int currentSemester = new Date().getMonth() < 6 ? 1: 2;

        mv.addObject("currentSemester", currentSemester);
        mv.addObject("currentYear", year);
        mv.addObject("jefesTecnico", jefesTecnico);
        mv.addObject("regiones", regiones);
        mv.addObject("error", error);
        return mv;
    }
}
