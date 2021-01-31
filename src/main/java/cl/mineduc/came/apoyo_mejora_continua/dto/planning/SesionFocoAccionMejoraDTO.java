package cl.mineduc.came.apoyo_mejora_continua.dto.planning;

public class SesionFocoAccionMejoraDTO {
    private String idSesionFocoAccionMejora; 
    private String idSesionFoco;
    private String accionMejora;
    private Boolean completado;

    public String getIdSesionFocoAccionMejora() {
        return idSesionFocoAccionMejora;
    }

    public void setIdSesionFocoAccionMejora(String idSesionFocoAccionMejora) {
        this.idSesionFocoAccionMejora = idSesionFocoAccionMejora;
    }

    public String getIdSesionFoco() {
        return idSesionFoco;
    }

    public void setIdSesionFoco(String idSesionFoco) {
        this.idSesionFoco = idSesionFoco;
    }

    public String getAccionMejora() {
        return accionMejora;
    }

    public void setAccionMejora(String accionMejora) {
        this.accionMejora = accionMejora;
    }

    public Boolean getCompletado() {
        return completado;
    }

    public void setCompletado(Boolean completado) {
        this.completado = completado;
    }
    
    

}
