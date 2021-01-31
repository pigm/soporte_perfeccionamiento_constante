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

import cl.mineduc.came.apoyo_mejora_continua.Excel.DynamicReportExcel;
import cl.mineduc.came.apoyo_mejora_continua.dto.dynamicreport.DynamicListDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.dynamicreport.DynamicRequestDTO;
import cl.mineduc.came.apoyo_mejora_continua.services.DynamicReportService;

@Controller
@RequestMapping("dynamicreport")
public class DynamicReportController {
    private static Logger logger = LogManager.getLogger(DynamicReportController.class);

    @Autowired
    private DynamicReportService dynamicReportService;

    @GetMapping(value = "/getDynamic", produces = "application/json")
    public ResponseEntity<Object> getFeedBack(DynamicRequestDTO request) {
        List<DynamicListDTO> records = dynamicReportService.getDynamic(request);
        return new ResponseEntity<Object>(records, HttpStatus.OK);
    }

    @GetMapping(value = "/export/excel")
    public void exportToExcel(HttpServletResponse response, DynamicRequestDTO request) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Reporte_Dinamico_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<DynamicListDTO> records = dynamicReportService.getDynamic(request);
        DynamicReportExcel excelExporter = new DynamicReportExcel(records);
        excelExporter.export(response);
    }

    @GetMapping(value = "index")
    public ModelAndView index(@RequestParam(required = false) Integer error) {
        logger.debug("planning view request... ");
        ModelAndView mv = new ModelAndView();
                
        mv.addObject("error", error);
        return mv;
    }
}
