package cl.mineduc.came.apoyo_mejora_continua.modelo;

import java.util.Date;

public class SesionFocoAccionMejora {
    private Long idSesionFocoAccionMejora;
    private Long idSesionFoco;
    private String accionMejora;
    private Boolean completado;
    private Long idUsuarioRegistro;
    private Date fechaRegistro;

    public Long getIdSesionFocoAccionMejora() {
        return idSesionFocoAccionMejora;
    }

    public void setIdSesionFocoAccionMejora(Long idSesionFocoAccionMejora) {
        this.idSesionFocoAccionMejora = idSesionFocoAccionMejora;
    }

    public Long getIdSesionFoco() {
        return idSesionFoco;
    }

    public void setIdSesionFoco(Long idSesionFoco) {
        this.idSesionFoco = idSesionFoco;
    }

    public Boolean getCompletado() {
        return completado;
    }

    public void setCompletado(Boolean completado) {
        this.completado = completado;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Long getIdUsuarioRegistro() {
        return idUsuarioRegistro;
    }

    public void setIdUsuarioRegistro(Long idUsuarioRegistro) {
        this.idUsuarioRegistro = idUsuarioRegistro;
    }

    public String getAccionMejora() {
        return accionMejora;
    }

    public void setAccionMejora(String accionMejora) {
        this.accionMejora = accionMejora;
    }
}
