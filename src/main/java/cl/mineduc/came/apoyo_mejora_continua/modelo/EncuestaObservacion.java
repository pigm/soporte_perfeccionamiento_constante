package cl.mineduc.came.apoyo_mejora_continua.modelo;

import java.util.Date;

public class EncuestaObservacion {
    private Long idEncuestaObservacion;
    private Long idEncuesta;
    private Long idUsuario;
    private Long idPerfil;
    private String observacion;
    private Date fechaObservacion;

    public Long getIdEncuestaObservacion() {
        return idEncuestaObservacion;
    }

    public void setIdEncuestaObservacion(Long idEncuestaObservacion) {
        this.idEncuestaObservacion = idEncuestaObservacion;
    }

    public Long getIdEncuesta() {
        return idEncuesta;
    }

    public void setIdEncuesta(Long idEncuesta) {
        this.idEncuesta = idEncuesta;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Long getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(Long idPerfil) {
        this.idPerfil = idPerfil;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Date getFechaObservacion() {
        return fechaObservacion;
    }

    public void setFechaObservacion(Date fechaObservacion) {
        this.fechaObservacion = fechaObservacion;
    }    
}
