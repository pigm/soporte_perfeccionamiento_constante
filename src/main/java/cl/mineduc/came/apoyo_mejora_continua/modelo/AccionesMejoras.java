package cl.mineduc.came.apoyo_mejora_continua.modelo;

import java.time.LocalDateTime;
import java.util.List;

public class AccionesMejoras {
    private Long idAccionesMejoras;
    
	private String nombre;
	
	private String descripcion;
	
	private LocalDateTime fechaRegistro;
	
	private LocalDateTime ultimaModificacion;
	
	private Long idUsuarioModificacion;

    private Long idFoco;
    
    private List<MovimientosClaves> movimientos;

	public Long getIdAccionesMejoras() {
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

	public void setIdAccionesMejoras(Long idAccionesMejoras) {
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

	public List<MovimientosClaves> getMovimientos() {
		return movimientos;
	}

	public void setMovimientos(List<MovimientosClaves> movimientos) {
		this.movimientos = movimientos;
	}

	@Override
	public String toString() {
		return "AccionesMejoras [idAccionesMejoras=" + idAccionesMejoras + ", nombre=" + nombre + ", descripcion="
				+ descripcion + ", fechaRegistro=" + fechaRegistro + ", ultimaModificacion=" + ultimaModificacion
				+ ", idUsuarioModificacion=" + idUsuarioModificacion + ", idFoco=" + idFoco + ", movimientos="
				+ movimientos + "]";
	}
	   
}