package cl.mineduc.came.apoyo_mejora_continua.controllers;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ApoyoMejoraContinuaErrorController implements ErrorController {

	/**
	 * Este es el mapping para los whitelabel errors
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/error")
	public ModelAndView handleError(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		mv.setViewName("errors/generic-error");

		if (!(null == status)) {
			Integer statusCode = Integer.valueOf(status.toString());
			mv.addObject("status", statusCode);

			if (statusCode == HttpStatus.NOT_FOUND.value()) {
				mv.setViewName("errors/not-found");
			}
			if (statusCode == HttpStatus.FORBIDDEN.value()) {
				mv.setViewName("errors/forbidden");
			}
			if (statusCode == HttpStatus.UNAUTHORIZED.value()) {
				mv.setViewName("errors/unauthorized");
			}
			if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
				mv.setViewName("errors/internal-error");
			}
			if (statusCode == HttpStatus.SERVICE_UNAVAILABLE.value()) {
				mv.setViewName("errors/unavailable");
			}
		}

		mv.addObject("exception", request.getAttribute(RequestDispatcher.ERROR_EXCEPTION));
		mv.addObject("message", request.getAttribute(RequestDispatcher.ERROR_MESSAGE));

		return mv;
	}

	@Override
	public String getErrorPath() {
		return "/error";
	}

	@RequestMapping(value = "/error/{nombre-error}", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView errors(@PathVariable(name = "nombre-error") String nombreError, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("errors/" + nombreError);
		return mv;
	}

}
