package cl.mineduc.came.apoyo_mejora_continua.integration;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import cl.mineduc.came.apoyo_mejora_continua.dto.integration.region.RegionResponseDTO;

@Component
public class RegionIntegration {
	private static Logger logger = LogManager.getLogger(RegionIntegration.class);

	@Autowired
	RestTemplate restTemplate;

	@Value("${cl.mineduc.api.regiones.url}")
	private String apiRegionesUrl;

	public List<RegionResponseDTO> getRegiones() {
		logger.debug("CONSULTANDO REGIONES {} A API REGIONES...", 1);
		String metodo = "regiones";

		RegionResponseDTO[] regiones = restTemplate.getForObject(apiRegionesUrl + metodo, RegionResponseDTO[].class);

		logger.debug("CONSULTANDO REGIONES, {} obtenidas...", regiones.length);
		return Lists.newArrayList(regiones);
	}

	public RegionResponseDTO getRegionByNumber(Long region) {
		String metodo = "region/" + String.valueOf(region);
		RegionResponseDTO rg = restTemplate.getForObject(apiRegionesUrl + metodo, RegionResponseDTO.class);
		return rg;
	}
	
}
