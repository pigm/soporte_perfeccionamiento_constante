package cl.mineduc.came.apoyo_mejora_continua.modelo;

import java.time.LocalDateTime;

public class MovimientosClaves {
	private Long idMovimientosClaves;

	private Long idAccionesMejoras;

	private String nombre;

	private String descripcion;

	private LocalDateTime fechaRegistro;

	private LocalDateTime ultimaModificacion;

	private Long idUsuarioModificacion;

	public Long getIdMovimientosClaves() {
		return idMovimientosClaves;
	}

	public void setIdMovimientosClaves(Long idMovimientosClaves) {
		this.idMovimientosClaves = idMovimientosClaves;
	}

	public Long getIdAccionesMejoras() {
		return idAccionesMejoras;
	}

	public void setIdAccionesMejoras(Long idAccionesMejoras) {
		this.idAccionesMejoras = idAccionesMejoras;
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

	@Override
	public String toString() {
		return "MovimientosClaves [idMovimientosClaves=" + idMovimientosClaves + ", idAccionesMejoras="
				+ idAccionesMejoras + ", nombre=" + nombre + ", descripcion=" + descripcion + ", fechaRegistro="
				+ fechaRegistro + ", ultimaModificacion=" + ultimaModificacion + ", idUsuarioModificacion="
				+ idUsuarioModificacion + "]";
	}

	
	
}