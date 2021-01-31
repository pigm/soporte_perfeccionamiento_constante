package cl.mineduc.came.apoyo_mejora_continua.modelo;

import java.io.Serializable;
import java.util.List;

public class Modulo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8134634979187493266L;

	private Long idModulo;

	private Long idEstado;

	private String nombre;

	private boolean status;

	private Integer index;

	private List<SubModulo> subModulo;

	public Long getIdModulo() {
		return idModulo;
	}

	public void setIdModulo(Long idModulo) {
		this.idModulo = idModulo;
	}

	public Long getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(Long idEstado) {
		this.idEstado = idEstado;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public List<SubModulo> getSubModulo() {
		return subModulo;
	}

	public void setSubModulo(List<SubModulo> subModulo) {
		this.subModulo = subModulo;
	}

	@Override
	public String toString() {
		return "Modulo [id=" + idModulo + ", idEstado=" + idEstado + ", nombre=" + nombre + ", status=" + status
				+ ", subModulo=" + subModulo + "]";
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}
}
