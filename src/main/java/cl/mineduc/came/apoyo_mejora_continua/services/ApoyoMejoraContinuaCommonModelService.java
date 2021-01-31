package cl.mineduc.came.apoyo_mejora_continua.services;

import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.DeviceResolverHandlerInterceptor;
import org.springframework.mobile.device.DeviceUtils;
import org.springframework.mobile.device.site.SitePreferenceHandlerInterceptor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;

import cl.mineduc.came.apoyo_mejora_continua.modelo.auth.Usuario;
import cl.mineduc.framework2.utils.StringUtils;

/**
 * 
 * @author alvaro.tellez, ChanchitoData My Friend
 * Una Reimplementación interna de la clase de framework mineduc
 */
public class ApoyoMejoraContinuaCommonModelService {
	private static Logger logger = LogManager.getLogger(ApoyoMejoraContinuaCommonModelService.class);
	
	private DeviceResolverHandlerInterceptor deviceInterceptor = new DeviceResolverHandlerInterceptor();
	private SitePreferenceHandlerInterceptor siteInterceptor = new SitePreferenceHandlerInterceptor();
	
	private String developIPs = null;
	private String[] ipAddresses = null;
	
	@Value("${app.uploadMainFolder}")
	private String uploadMainFolder;
	
	// Getters
	public String getDevelopIPs() {return developIPs;}
	public DeviceResolverHandlerInterceptor getDeviceInterceptor() {return deviceInterceptor;}
	public SitePreferenceHandlerInterceptor getSiteInterceptor() {return siteInterceptor;}

	// Setters
	public void setDevelopIPs(String v) {this.developIPs = v;}
	public void setDeviceInterceptor(DeviceResolverHandlerInterceptor v) {this.deviceInterceptor = v;}
	public void setSiteInterceptor(SitePreferenceHandlerInterceptor v) {this.siteInterceptor = v;}
	
	public void fillModel(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView mv) throws Exception{
		String contentType = response.getContentType();		
		getDeviceInterceptor().preHandle(request, response, handler);
		getSiteInterceptor().preHandle(request, response, handler);
		
		if (StringUtils.isEmpty(contentType)){
			response.setContentType("text/html; charset=UTF-8");
		}		
		setSecurityHeader(response);			
		// Add request Parameters
		Enumeration<String> params = request.getParameterNames();
		Map<String,Object> requestModel = new HashMap<String,Object>();		
		while (params.hasMoreElements()) {
			String parameterName = (String) params.nextElement();
			requestModel.put(parameterName, request.getParameter(parameterName));
			logger.debug("request: parameterName = "+parameterName+ " valor= "+request.getParameter(parameterName) );
		}
		if (mv != null){
			mv.addObject("currDate", new Date());
			mv.addObject("request", requestModel);
			mv.addObject("context", request.getContextPath());
			mv.addObject("uploadMainFolder", uploadMainFolder);
			mv.addObject("ip",getClientIP(request));
			mv.addObject("isDevelopment",checkIsDevelopment(getClientIP(request)));					
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if (auth != null && auth.getDetails() != null){
				mv.addObject("currentUser", auth.getDetails());
			}else{
				if (auth != null && auth.getPrincipal() != null){
					((Usuario) auth.getPrincipal()).setIp(this.getClientIP(request));
					mv.addObject("currentUser", auth.getPrincipal());
				}
			}
			Device device = DeviceUtils.getCurrentDevice(request);
			checkDevice(mv, device);
		}
	}
	private void setSecurityHeader(HttpServletResponse response) {
		response.setHeader("X-XSS-Protection","1");
		response.setHeader("mode","block");

		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Content-Security-Policy","frame-ancestors *");
		response.setHeader("X-Frame-Options","Allow-From *");
	}
	
	private void checkDevice(ModelAndView mv, Device device) {
		if (device != null){
			if (device.isMobile()){
				mv.addObject("isMobile",true);
				mv.addObject("isTablet",false);
				mv.addObject("isNormal",false);
			}else if (device.isTablet()){
				mv.addObject("isMobile",false);
				mv.addObject("isTablet",true);
				mv.addObject("isNormal",false);
			}else{
				mv.addObject("isMobile",false);
				mv.addObject("isTablet",false);
				mv.addObject("isNormal",true);
			}
		}
	}	
	
	public String getClientIP(HttpServletRequest request){
		String ip = request.getHeader("X-FORWARDED-FOR");
		
		if (StringUtils.isEmpty(ip)){
			ip = request.getRemoteAddr();
		}
		
		return ip;
	}
	
	public boolean checkIsDevelopment(String clientIP){
		if (ipAddresses == null && getDevelopIPs() != null){
			logger.debug("Client IP Address found: {}", clientIP);
			ipAddresses = getDevelopIPs().split("\\,|\\s");
		}
		boolean notEmptyIP = StringUtils.hasContent(clientIP);
		for (int i = 0;notEmptyIP && ipAddresses != null && i < ipAddresses.length; i++){
			if (StringUtils.hasContent(ipAddresses[i]) && clientIP.equals(ipAddresses[i].trim())){
				return true;
			}
		}
		return false;
	}
}
