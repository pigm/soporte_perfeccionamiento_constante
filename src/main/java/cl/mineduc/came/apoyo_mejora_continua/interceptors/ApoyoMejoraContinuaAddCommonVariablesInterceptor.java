package cl.mineduc.came.apoyo_mejora_continua.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cl.mineduc.came.apoyo_mejora_continua.services.ApoyoMejoraContinuaCommonModelService;

public class ApoyoMejoraContinuaAddCommonVariablesInterceptor extends HandlerInterceptorAdapter{
	private static Logger logger = LogManager.getLogger(ApoyoMejoraContinuaAddCommonVariablesInterceptor.class);
	private ApoyoMejoraContinuaCommonModelService commonService;
	
	// Getters
	public ApoyoMejoraContinuaCommonModelService getCommonService() {return commonService;}

	// Setters
	public void setCommonService(ApoyoMejoraContinuaCommonModelService v) {this.commonService = v;}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView mv) throws Exception {
		// Call super first of all
		super.postHandle(request, response, handler, mv);
		logger.debug("adding ApoyoMejoraContinua common model service");
		if (getCommonService() != null){
			getCommonService().fillModel(request,response,handler,mv);
			logger.debug("request model filled!");
		}
	}
}
