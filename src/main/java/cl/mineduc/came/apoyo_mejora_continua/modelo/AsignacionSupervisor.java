package cl.mineduc.came.apoyo_mejora_continua.modelo;

import java.io.Serializable;
import java.time.LocalDateTime;

public class AsignacionSupervisor implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -7683430376740363040L;

	private Long idAsignacionSupervisor;

    private Long idPeriodo;
    
    private Long idAsignacionTipo;
    
    private LocalDateTime fechaRegistro;
    
    private LocalDateTime fechaCreacion;
    
    private Long idUsuarioRegistro;

	public Long getIdAsignacionSupervisor() {
		return idAsignacionSupervisor;
	}

	public Long getIdPeriodo() {
		return idPeriodo;
	}

	public Long getIdAsignacionTipo() {
		return idAsignacionTipo;
	}

	public LocalDateTime getFechaRegistro() {
		return fechaRegistro;
	}

	public LocalDateTime getFechaCreacion() {
		return fechaCreacion;
	}

	public Long getIdUsuarioRegistro() {
		return idUsuarioRegistro;
	}

	public void setIdAsignacionSupervisor(Long idAsignacionSupervisor) {
		this.idAsignacionSupervisor = idAsignacionSupervisor;
	}

	public void setIdPeriodo(Long idPeriodo) {
		this.idPeriodo = idPeriodo;
	}

	public void setIdAsignacionTipo(Long idAsignacionTipo) {
		this.idAsignacionTipo = idAsignacionTipo;
	}

	public void setFechaRegistro(LocalDateTime fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public void setFechaCreacion(LocalDateTime fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public void setIdUsuarioRegistro(Long idUsuarioRegistro) {
		this.idUsuarioRegistro = idUsuarioRegistro;
	}

}