package cl.mineduc.came.apoyo_mejora_continua.controllers_advice;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cl.mineduc.framework2.exceptions.MineducException;


@ControllerAdvice
public class MineducExceptionAdvice {
	private static Logger logger = LogManager.getLogger(MineducExceptionAdvice.class);
	

	@ExceptionHandler(MineducException.class)
	public String handleMineducException(MineducException e, RedirectAttributes redirectAttributes)  {
		logger.info("HANDLING MineducException... "+e.getLocalizedMessage());
		redirectAttributes.addFlashAttribute("exception", e);
		return "redirect:/error/mineduc-error";
		
	}
	
	@ExceptionHandler(Exception.class)
	public String handleException(Exception e, RedirectAttributes redirectAttributes)  {
		logger.info("HANDLING ... "+e.getClass().getName());
		logger.error("EXCEPCION NO CONTROLADA!!! ---->{}",e.getMessage(),e);
		redirectAttributes.addFlashAttribute("exception", e);
		return "redirect:/error/generic-error";
		
	}
}
