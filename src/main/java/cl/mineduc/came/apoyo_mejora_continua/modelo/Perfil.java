package cl.mineduc.came.apoyo_mejora_continua.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Perfil implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5468378952347981140L;

	private Long idPerfil;
	
	private Long idEstado;
	
	private Long idPerfilNivel;
	
	private String nombre;
	
	private String descripcion;
	
	private boolean habilitado;
	
	private Date ultimaModificacion;
	
	private Long idUsuarioModificacion;
	
	private PerfilNivel perfilNivel;

	private List<PerfilMenu> perfilMenu;

	public Long getIdPerfil() {
		return idPerfil;
	}

	public void setIdPerfil(Long idPerfil) {
		this.idPerfil = idPerfil;
	}

	public Long getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(Long idEstado) {
		this.idEstado = idEstado;
	}

	public Long getIdPerfilNivel() {
		return idPerfilNivel;
	}

	public void setIdPerfilNivel(Long idPerfilNivel) {
		this.idPerfilNivel = idPerfilNivel;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public boolean getHabilitado() {
		return habilitado;
	}

	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
	}

	public Date getUltimaModificacion() {
		return ultimaModificacion;
	}

	public void setUltimaModificacion(Date ultimaModificacion) {
		this.ultimaModificacion = ultimaModificacion;
	}

	public Long getIdUsuarioModificacion() {
		return idUsuarioModificacion;
	}

	public void setIdUsuarioModificacion(Long idUsuarioModificacion) {
		this.idUsuarioModificacion = idUsuarioModificacion;
	}	

	public PerfilNivel getPerfilNivel() {
		return perfilNivel;
	}

	public void setPerfilNivel(PerfilNivel perfilNivel) {
		this.perfilNivel = perfilNivel;
	}

	public List<PerfilMenu> getPerfilMenu() {
		return perfilMenu;
	}

	public void setPerfilMenu(List<PerfilMenu> perfilMenu) {
		this.perfilMenu = perfilMenu;
	}

	@Override
	public String toString() {
		return "Perfil [idPerfil=" + idPerfil + ", idEstado=" + idEstado + ", idPerfilNivel=" + idPerfilNivel + ", nombre=" + nombre
				+ ", descripcion=" + descripcion + ", habilitado=" + habilitado + ", ultimaModificacion="
				+ ultimaModificacion + ", idUsuarioModificacion=" + idUsuarioModificacion + ", perfilNivel="
				+ perfilNivel + "]";
	}
	
}
