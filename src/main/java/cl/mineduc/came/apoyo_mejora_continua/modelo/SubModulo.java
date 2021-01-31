package cl.mineduc.came.apoyo_mejora_continua.modelo;

import java.io.Serializable;
import java.util.List;

public class SubModulo implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7404973915217672353L;

	private Long idSubModulo;
	
	private Long idModulo;
	
	private Long idEstado;
	
	private String nombre;
	
	private boolean status;

	private String url;
	
	private List<PerfilMenu> perfilMenu;

	public Long getIdSubModulo() {
		return idSubModulo;
	}

	public void setIdSubModulo(Long idSubModulo) {
		this.idSubModulo = idSubModulo;
	}

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

	public List<PerfilMenu> getPerfilMenu() {
		return perfilMenu;
	}

	public void setPerfilMenu(List<PerfilMenu> perfilMenu) {
		this.perfilMenu = perfilMenu;
	}

	@Override
	public String toString() {
		return "SubModulo [id=" + idSubModulo + ", idModulo=" + idModulo + ", idEstado=" + idEstado + ", nombre=" + nombre
				+ ", status=" + status + ", perfilMenu=" + perfilMenu + "]";
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	
}
