package cl.mineduc.came.apoyo_mejora_continua.modelo;

import java.util.Date;

public class EncuestaPregunta {
    private Long idEncuestaPregunta;
    private Long idEncuesta;
    private String pregunta;
    private Boolean estado;
    private Long idUsuarioRegistro;
    private Date fechaRegistro;

	public Long getIdEncuestaPregunta() {
		return idEncuestaPregunta;
	}
	public void setIdEncuestaPregunta(Long idEncuestaPregunta) {
		this.idEncuestaPregunta = idEncuestaPregunta;
	}
	public Long getIdEncuesta() {
		return idEncuesta;
	}
	public void setIdEncuesta(Long idEncuesta) {
		this.idEncuesta = idEncuesta;
	}
	public String getPregunta() {
		return pregunta;
	}
	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}
	public Boolean getEstado() {
		return estado;
	}
	public void setEstado(Boolean estado) {
		this.estado = estado;
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
