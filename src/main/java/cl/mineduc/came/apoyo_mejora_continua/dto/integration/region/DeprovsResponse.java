package cl.mineduc.came.apoyo_mejora_continua.dto.integration.region;

import java.util.List;

public class DeprovsResponse {
	private Integer idDeprov;
    private String nombreDeprov;
    private List<ComunaResponse> comunas;

    public Integer getIdDeprov() {
        return idDeprov;
    }

    public void setIdDeprov(Integer idDeprov) {
        this.idDeprov = idDeprov;
    }

    public String getNombreDeprov() {
        return nombreDeprov;
    }

    public void setNombreDeprov(String nombreDeprov) {
        this.nombreDeprov = nombreDeprov;
    }

    public List<ComunaResponse> getComunas() {
        return comunas;
    }

    public void setComunas(List<ComunaResponse> comunas) {
        this.comunas = comunas;
    }
}
