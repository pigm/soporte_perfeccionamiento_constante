package cl.mineduc.came.apoyo_mejora_continua.modelo;

import java.util.Date;

public class EncuestaRespuesta {
    private Long idEncuestaRespuesta;
    private Long idEncuestaPregunta;
    private Long idUsuario;
    private Long idPerfil;
    private Integer valor;
    private Date fechaRespuesta;

    public Long getIdEncuestaRespuesta() {
        return idEncuestaRespuesta;
    }

    public void setIdEncuestaRespuesta(Long idEncuestaRespuesta) {
        this.idEncuestaRespuesta = idEncuestaRespuesta;
    }

    public Long getIdEncuestaPregunta() {
        return idEncuestaPregunta;
    }

    public void setIdEncuestaPregunta(Long idEncuestaPregunta) {
        this.idEncuestaPregunta = idEncuestaPregunta;
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

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    public Date getFechaRespuesta() {
        return fechaRespuesta;
    }

    public void setFechaRespuesta(Date fechaRespuesta) {
        this.fechaRespuesta = fechaRespuesta;
    }    

}
