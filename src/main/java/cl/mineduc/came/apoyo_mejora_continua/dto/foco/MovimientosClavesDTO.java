package cl.mineduc.came.apoyo_mejora_continua.dto.foco;

import java.io.Serializable;
import java.time.LocalDateTime;

import cl.mineduc.came.apoyo_mejora_continua.dto.period.DTOEntity;

public class MovimientosClavesDTO implements DTOEntity, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1644488245737130129L;

	private String idMovimientosClaves;

	private String idAccionesMejoras;

	private String nombre;

	private String descripcion;

	private LocalDateTime fechaRegistro;

	private LocalDateTime ultimaModificacion;

	private Long idUsuarioModificacion;

	public String getIdMovimientosClaves() {
		return idMovimientosClaves;
	}

	public String getIdAccionesMejoras() {
		return idAccionesMejoras;
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

	public void setIdMovimientosClaves(String idMovimientosClaves) {
		this.idMovimientosClaves = idMovimientosClaves;
	}

	public void setIdAccionesMejoras(String idAccionesMejoras) {
		this.idAccionesMejoras = idAccionesMejoras;
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
		return "MovimientosClavesDTO [idMovimientosClaves=" + idMovimientosClaves + ", idAccionesMejoras="
				+ idAccionesMejoras + ", nombre=" + nombre + ", descripcion=" + descripcion + ", fechaRegistro="
				+ fechaRegistro + ", ultimaModificacion=" + ultimaModificacion + ", idUsuarioModificacion="
				+ idUsuarioModificacion + "]";
	}
	
}
