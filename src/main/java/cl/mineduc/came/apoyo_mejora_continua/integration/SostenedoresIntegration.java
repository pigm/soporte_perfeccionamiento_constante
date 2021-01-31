package cl.mineduc.came.apoyo_mejora_continua.integration;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import cl.mineduc.came.apoyo_mejora_continua.modelo.api.sostenedores.Sostenedores;

@Component
public class SostenedoresIntegration {
	private static Logger logger = LogManager.getLogger(SostenedoresIntegration.class);

	@Autowired
	RestTemplate restTemplate;

	@Value("${cl.mineduc.api.sostenedores.url}")
	private String apiSostenedoresUrl;

	public List<Sostenedores> getSostenedores(int rows) {
		logger.debug("CONSULTANDO SOSTENEDORES {} A API SOSTENEDORES...", rows);
		String metodo = "sostenedores?rows=" + rows;
		Sostenedores[] sostenedores = restTemplate.getForObject(apiSostenedoresUrl + metodo, Sostenedores[].class);

		logger.debug("CONSULTANDO SOSTENEDORES, {} obtenidas...", sostenedores.length);
		return Lists.newArrayList(sostenedores);
	}

	public Sostenedores getSostenedorByRut(String rut) {
		logger.debug("CONSULTANDO SOSTENEDORES RBD {} A API SOSTENEDORES...", rut);
		String metodo = "sostenedores/" + rut;
		Sostenedores sostenedor = restTemplate.getForObject(apiSostenedoresUrl + metodo, Sostenedores.class);

		logger.debug("CONSULTANDO SOSTENEDOR {} segun RUT...", rut);
		return sostenedor;
	}
}
