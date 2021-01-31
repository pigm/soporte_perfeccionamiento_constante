package cl.mineduc.came.apoyo_mejora_continua.dto.foco;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import cl.mineduc.came.apoyo_mejora_continua.dto.period.DTOEntity;

public class AccionesMejorasDTO implements DTOEntity, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -408355278653382681L;

	private String idAccionesMejoras;

	private String nombre;

	private String descripcion;

	private LocalDateTime fechaRegistro;

	private LocalDateTime ultimaModificacion;

	private Long idUsuarioModificacion;

	private Long idFoco;

	private List<MovimientosClavesDTO> movimientos;

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

	public Long getIdFoco() {
		return idFoco;
	}

	public List<MovimientosClavesDTO> getMovimientos() {
		return movimientos;
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

	public void setIdFoco(Long idFoco) {
		this.idFoco = idFoco;
	}

	public void setMovimientos(List<MovimientosClavesDTO> movimientos) {
		this.movimientos = movimientos;
	}

	@Override
	public String toString() {
		return "AccionesMejorasDTO [idAccionesMejoras=" + idAccionesMejoras + ", nombre=" + nombre + ", descripcion="
				+ descripcion + ", fechaRegistro=" + fechaRegistro + ", ultimaModificacion=" + ultimaModificacion
				+ ", idUsuarioModificacion=" + idUsuarioModificacion + ", idFoco=" + idFoco + ", movimientos="
				+ movimientos + "]";
	}
	
}
