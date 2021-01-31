package cl.mineduc.came.apoyo_mejora_continua.controllers;

import java.util.Calendar;

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

import cl.mineduc.came.apoyo_mejora_continua.Excel.SurveyExcel;
import cl.mineduc.came.apoyo_mejora_continua.dto.shared.SelectorDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.survey.GetSurveyDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.survey.SurveyCompleteDetailDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.survey.SurveyDataDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.survey.SurveyExcelDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.survey.SurveyForAnswerDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.survey.SurveyListDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.survey.SuveyAnswerDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.users.ProfileSelectorDTO;
import cl.mineduc.came.apoyo_mejora_continua.services.SurveyService;

@Controller
@RequestMapping("survey")
public class SurveyController {
    private static Logger logger = LogManager.getLogger(SurveyController.class);
    
    @Autowired
    SurveyService surveyService;

    @GetMapping(value = "/getProvincias", produces = "application/json")
    public ResponseEntity<Object> getProvincias(String idRegion) {
        List<SelectorDTO> provincias = surveyService.getDeprovByRegion(Integer.parseInt(idRegion));
        return new ResponseEntity<Object>(provincias, HttpStatus.OK);
    }

    @GetMapping(value = "/getSurvey", produces = "application/json")
    public ResponseEntity<Object> getSurvey(GetSurveyDTO request) {
       List<SurveyListDTO> records = surveyService.getSurvey(request);
        return new ResponseEntity<Object>(records, HttpStatus.OK);
    }

    @GetMapping(value = "/getSurveyDetail", produces = "application/json")
    public ResponseEntity<Object> getSurveyDetail(String idSurvey) {
        SurveyDataDTO record = surveyService.getSurveyDetail(idSurvey);
        return new ResponseEntity<Object>(record, HttpStatus.OK);
    }

    @GetMapping(value = "/getSurveyForAnswer", produces = "application/json")
    public ResponseEntity<Object> getSurveyForAnswer(String idSurvey) {
        SurveyForAnswerDTO record = surveyService.getSurveyForAnswer(idSurvey);
        return new ResponseEntity<Object>(record, HttpStatus.OK);
    }

    @GetMapping(value = "/getSurveyForUserProfile")
    public ResponseEntity<Object> getSurveyForUserProfile() {
        String idSurvey = surveyService.getSurveyForUserProfile();
        return new ResponseEntity<Object>(idSurvey, HttpStatus.OK);
    }

    @PostMapping(value = "/setSurveyStatus")
    public ResponseEntity<Object> setSurveyStatus(@RequestParam String idSurvey, 
                                                  @RequestParam Boolean status) {

        surveyService.setSurveyStatus(idSurvey, status);

        return new ResponseEntity<Object>(true, HttpStatus.OK);
    }

    @PostMapping(value = "/setSurvey", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> setSurvey(@Valid @RequestBody SurveyDataDTO request, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<Object>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        surveyService.setSurvey(request);
        return new ResponseEntity<Object>(true, HttpStatus.OK);
    }

    @PostMapping(value = "/answerSurvey", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> answerSurvey(@Valid @RequestBody SuveyAnswerDTO request, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<Object>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        surveyService.answerSurvey(request);
        return new ResponseEntity<Object>(true, HttpStatus.OK);
    }

    @GetMapping(value = "/export/excel")
    public void exportToExcel(HttpServletResponse response, 
            @RequestParam(required = true) String idSurvey) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=encuesta_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        SurveyExcelDTO excel = surveyService.getSurveyExcel(idSurvey);

        SurveyExcel excelExporter = new SurveyExcel(excel);

        excelExporter.export(response);
    }

    @GetMapping(value = "index")
    public ModelAndView index(@RequestParam(required = false) Integer error) {
        logger.debug("planning view request... ");
        ModelAndView mv = new ModelAndView();
      
        Integer year = Calendar.getInstance().get(Calendar.YEAR);
        List<ProfileSelectorDTO> profiles = surveyService.getProfiles();
        List<SelectorDTO> regiones = surveyService.getRegiones();
        
        mv.addObject("currentYear", year);
        mv.addObject("profiles", profiles);
        mv.addObject("regiones", regiones);
        mv.addObject("error", error);
        return mv;
    }

    @GetMapping(value = "survey-detail")
    public ModelAndView surveyDetail(
                        @RequestParam(required = true) String idSurvey, 
                        @RequestParam(required = false) Integer error) {
        logger.debug("planning view request... ");
        ModelAndView mv = new ModelAndView();

        Integer year = Calendar.getInstance().get(Calendar.YEAR);
        SurveyCompleteDetailDTO survey = surveyService.getSurveyCompleteDetail(idSurvey);
        
        mv.addObject("survey", survey);
        mv.addObject("currentYear", year);       
        mv.addObject("error", error);
        return mv;
    }
}
