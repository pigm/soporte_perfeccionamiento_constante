package cl.mineduc.came.apoyo_mejora_continua.dto.planning;

public class ObjetivoMejoraDTO {
    private String idObjetivoMejora;
    private String idSesion;
    private Integer numero;
    private String objetivo;

    public String getIdObjetivoMejora() {
        return idObjetivoMejora;
    }

    public void setIdObjetivoMejora(String idObjetivoMejora) {
        this.idObjetivoMejora = idObjetivoMejora;
    }

    public String getIdSesion() {
        return idSesion;
    }

    public void setIdSesion(String idSesion) {
        this.idSesion = idSesion;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    
}
