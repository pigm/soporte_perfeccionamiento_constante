package cl.mineduc.came.apoyo_mejora_continua.integration;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import cl.mineduc.came.apoyo_mejora_continua.modelo.api.Establecimientos;
import cl.mineduc.came.apoyo_mejora_continua.modelo.api.director.EstablecimientoDirector;

@Component
public class EstablecimientosIntegration {
	
	private static Logger logger = LogManager.getLogger(EstablecimientosIntegration.class);

	@Autowired
	RestTemplate restTemplate;

	@Value("${cl.mineduc.api.establecimientos.url}")
	private String apiEstablecimientosUrl;
	
	private EstablecimientoDirector getEstablecimientoDirector(int rbd) {
		logger.debug("CONSULTANDO DIRECTOR ESTABLECIMIENTO RBD {} A API ESTABLECIMIENTOS...", rbd);
		String metodo = "v4/establecimientos/" + rbd + "/director";
		EstablecimientoDirector establecimientoDirector = restTemplate.getForObject(apiEstablecimientosUrl + metodo, EstablecimientoDirector.class);
		return establecimientoDirector;
	}

	public List<Establecimientos> getEstablecimientos(Integer estado, Integer dependencia, Integer region, Integer provincia, Integer comuna, String textFilter, String convenio, int rows) {
		logger.debug("CONSULTANDO ESTABLECIMIENTOS {} A API ESTABLECIMIENTOS...", rows);
		String metodo = "v1/establecimientos?rows=" + rows;
		if(estado != null){
			metodo += "&ee=" + estado;
		}

		if(dependencia != null){
			metodo += "&td=" + dependencia;
		}

		if(region != null){
			metodo += "&r=" + region;
		}

		if(provincia != null){
			metodo += "&d=" + provincia;
		}

		if(comuna != null){
			metodo += "&c=" + comuna;
		}

		if(textFilter != null && !textFilter.isEmpty()){
			metodo += "&f=" + textFilter;
		}

		if(convenio != null && !convenio.isEmpty()){
			metodo += "&f=" + convenio;
		}

		Establecimientos[] establecimientos = restTemplate.getForObject(apiEstablecimientosUrl + metodo, Establecimientos[].class);

		logger.debug("CONSULTANDO ESTABLECIMIENTOS, {} obtenidas...", establecimientos == null? 0 : establecimientos.length);
		return establecimientos == null ? null : Lists.newArrayList(establecimientos);
	}

	public Establecimientos getEstablecimientoByRbd(int rbd) {
		logger.debug("CONSULTANDO ESTABLECIMIENTO RBD {} A API ESTABLECIMIENTOS...", rbd);
		String metodo = "v1/establecimientos/" + rbd;
		Establecimientos establecimientos = restTemplate.getForObject(apiEstablecimientosUrl + metodo, Establecimientos.class);
		if(establecimientos != null && establecimientos.getEstadoEstablecimiento().getCodigoEstado().equals(1)){
			establecimientos.setEstablecimientoDirector(this.getEstablecimientoDirector(rbd));
		}		
		logger.debug("CONSULTANDO ESTABLECIMIENTO {} segun rbd...{}{}", rbd, apiEstablecimientosUrl, metodo);
		return establecimientos;
	}

	public Establecimientos getEstablecimientoByRbd(int rbd, boolean getdirector) {
		logger.debug("CONSULTANDO ESTABLECIMIENTO RBD {} A API ESTABLECIMIENTOS...", rbd);
		String metodo = "v1/establecimientos/" + rbd;
		Establecimientos establecimientos = restTemplate.getForObject(apiEstablecimientosUrl + metodo, Establecimientos.class);
		
		if (getdirector && establecimientos != null && establecimientos.getEstadoEstablecimiento().getCodigoEstado().equals(1)) {
			establecimientos.setEstablecimientoDirector(this.getEstablecimientoDirector(rbd));
		}
		
		logger.debug("CONSULTANDO ESTABLECIMIENTO {} segun rbd...{}{}", rbd, apiEstablecimientosUrl, metodo);
		return establecimientos;
	}
}
