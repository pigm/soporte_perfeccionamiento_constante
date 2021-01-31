package cl.mineduc.came.apoyo_mejora_continua.modelo;

import java.time.LocalDateTime;
import java.util.List;

public class Foco {
	private Long idFoco;

	private Integer periodo;

	private String nombre;
	
	private String descripcion;
	
	private LocalDateTime fechaRegistro;
	
	private LocalDateTime ultimaModificacion;
	
	private Long idUsuarioModificacion;
	
	private List<AccionesMejoras> acciones;

	public Long getIdFoco() {
		return idFoco;
	}

	public void setIdFoco(Long idFoco) {
		this.idFoco = idFoco;
	}

	public String getNombre() {
		return nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public LocalDateTime getFechaRegistro() {
		return fechaRegistro;
	}

	public LocalDateTime getUltimaModificacion() {
		return ultimaModificacion;
	}

	public Long getIdUsuarioModificacion() {
		return idUsuarioModificacion;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public void setFechaRegistro(LocalDateTime fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public void setUltimaModificacion(LocalDateTime ultimaModificacion) {
		this.ultimaModificacion = ultimaModificacion;
	}

	public void setIdUsuarioModificacion(Long idUsuarioModificacion) {
		this.idUsuarioModificacion = idUsuarioModificacion;
	}

	public List<AccionesMejoras> getAcciones() {
		return acciones;
	}

	public void setAcciones(List<AccionesMejoras> acciones) {
		this.acciones = acciones;
	}

	public Integer getPeriodo() {
		return periodo;
	}

	public void setPeriodo(Integer periodo) {
		this.periodo = periodo;
	}
	
}