package cl.mineduc.came.apoyo_mejora_continua.dto.profile;

import java.io.Serializable;

public class ProfileDataDTO implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private long idPerfil;
	
	private String nombre;

	private NivelDTO perfilNivel;

	private String descripcion;
	
	private boolean habilitado;

	public Long getIdPerfil() {
		return idPerfil;
	}

	public void setIdPerfil(long idPerfil) {
		this.idPerfil = idPerfil;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public boolean isHabilitado() {
		return habilitado;
	}

	public void setHabilitado(boolean habilitar) {
		this.habilitado = habilitar;
	}


	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public NivelDTO getPerfilNivel() {
		return perfilNivel;
	}

	public void setPerfilNivel(NivelDTO perfilNivel) {
		this.perfilNivel = perfilNivel;
	}

	@Override
	public String toString() {
		return "ProfileDataDTO [descripcion=" + descripcion + ", habilitar=" + habilitado + ", idPerfil=" + idPerfil
				+ ", nombre=" + nombre + ", perfilNivel=" + perfilNivel + "]";
	}

	public String getIdStr() {
		return getIdPerfil().toString();
	}
}

class NivelDTO implements Serializable  {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private long idNivel;
	private String nombre;

	public long getIdNivel() {
		return idNivel;
	}

	public void setIdNivel(long idNivel) {
		this.idNivel = idNivel;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
