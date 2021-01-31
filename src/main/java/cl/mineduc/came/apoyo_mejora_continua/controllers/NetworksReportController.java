package cl.mineduc.came.apoyo_mejora_continua.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("networksreport")
public class NetworksReportController {
	private static Logger logger = LogManager.getLogger(NetworksReportController.class);
	
	@GetMapping(value = "index")
	public ModelAndView index(@RequestParam(required = false) Integer error){
		logger.debug("networks report  view request... ");
		ModelAndView mv = new ModelAndView();
	
		mv.addObject("error",error);
		return mv;
	}
}
