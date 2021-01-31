/**
 * 
 */
package cl.mineduc.came.apoyo_mejora_continua;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import cl.mineduc.came.apoyo_mejora_continua.interceptors.MDCInterceptor;

/**
 * @author Alvaro Tellez
 *
 */
@SpringBootApplication
public class ApoyoMejoraContinua {
	private static Logger logger = LogManager.getLogger(ApoyoMejoraContinua.class);
	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws Exception {
		MDCInterceptor.appIsBooting();
		Class<?>[] configClasses = {
				ApoyoMejoraContinuaConfiguration.class, 
				ApoyoMejoraContinuaDataSourcesConfiguration.class,
				ApoyoMejoraContinuaSecurityConfiguration.class
				};
		SpringApplication.run(configClasses, args);		
		logger.info(ApoyoMejoraContinua.class.getCanonicalName()+ " HAS DEBUG ENABLED :"+logger.isDebugEnabled());
		logger.info(ApoyoMejoraContinua.class.getName()+" UP & RUNNING!!!");
	}
}
