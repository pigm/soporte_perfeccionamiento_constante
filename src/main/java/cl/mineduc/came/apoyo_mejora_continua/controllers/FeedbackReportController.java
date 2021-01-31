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

import cl.mineduc.came.apoyo_mejora_continua.Excel.FeedbackReportExcel;
import cl.mineduc.came.apoyo_mejora_continua.dto.feedbackReport.FeedbackListDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.feedbackReport.FeedbackRequestDTO;
import cl.mineduc.came.apoyo_mejora_continua.services.FeedbackReportService;

@Controller
@RequestMapping("feedbackreport")
public class FeedbackReportController {
    private static Logger logger = LogManager.getLogger(FeedbackReportController.class);
    
    @Autowired
    private FeedbackReportService feedbackReportService;

    @GetMapping(value = "/getFeedback", produces = "application/json")
    public ResponseEntity<Object> getFeedBack(FeedbackRequestDTO request) {
        List<FeedbackListDTO> records = feedbackReportService.getFeedBack(request);
        return new ResponseEntity<Object>(records, HttpStatus.OK);
    }

    @GetMapping(value = "/export/excel")
    public void exportToExcel(HttpServletResponse response, FeedbackRequestDTO request) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=retro_reporte_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);
        List<FeedbackListDTO> records = feedbackReportService.getFeedBack(request);
        FeedbackReportExcel excelExporter = new FeedbackReportExcel(records);
        excelExporter.export(response);
    }

    @GetMapping(value = "index")
    public ModelAndView index(@RequestParam(required = false) Integer error) {
        logger.debug("planning view request... ");
        ModelAndView mv = new ModelAndView();

        // List<SelectorDTO> regiones = planningService.getRegiones();
        // List<SelectorDTO> tipoRed = planningService.getTipoRed();

        // mv.addObject("regiones", regiones);
        // mv.addObject("tipoRed", tipoRed);
        mv.addObject("error", error);
        return mv;
    }
}
