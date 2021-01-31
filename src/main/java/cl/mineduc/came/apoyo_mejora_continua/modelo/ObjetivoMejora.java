package cl.mineduc.came.apoyo_mejora_continua.modelo;

import java.util.Date;

public class ObjetivoMejora {
    private Long idObjetivoMejora;
    private Long idSesion;
    private Integer numero;
    private String objetivo;
    private Long idUsuarioRegistro;
    private Date fechaRegistro;

    public Long getIdObjetivoMejora() {
        return idObjetivoMejora;
    }

    public void setIdObjetivoMejora(Long idObjetivoMejora) {
        this.idObjetivoMejora = idObjetivoMejora;
    }

    public Long getIdSesion() {
        return idSesion;
    }

    public void setIdSesion(Long idSesion) {
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

    public Long getIdUsuarioRegistro() {
        return idUsuarioRegistro;
    }

    public void setIdUsuarioRegistro(Long idUsuarioRegistro) {
        this.idUsuarioRegistro = idUsuarioRegistro;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
    
}