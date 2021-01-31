package cl.mineduc.came.apoyo_mejora_continua.dto.foco;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import cl.mineduc.came.apoyo_mejora_continua.dto.period.DTOEntity;

public class FocoDTO implements DTOEntity, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1384131619362766433L;

	private String idFoco;

	private Integer periodo;

	private String nombre;
	
	private String descripcion;
	
	private LocalDateTime fechaRegistro;
	
	private LocalDateTime ultimaModificacion;
	
	private Long idUsuarioModificacion;
	
	private List<AccionesMejorasDTO> acciones;

	public String getIdFoco() {
		return idFoco;
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

	public List<AccionesMejorasDTO> getAcciones() {
		return acciones;
	}

	public void setIdFoco(String idFoco) {
		this.idFoco = idFoco;
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

	public void setAcciones(List<AccionesMejorasDTO> acciones) {
		this.acciones = acciones;
	}

	@Override
	public String toString() {
		return "FocoDTO [idFoco=" + idFoco + ", nombre=" + nombre + ", descripcion=" + descripcion + ", fechaRegistro="
				+ fechaRegistro + ", ultimaModificacion=" + ultimaModificacion + ", idUsuarioModificacion="
				+ idUsuarioModificacion + ", acciones=" + acciones + "]";
	}

	public Integer getPeriodo() {
		return periodo;
	}

	public void setPeriodo(Integer periodo) {
		this.periodo = periodo;
	}
	
}
