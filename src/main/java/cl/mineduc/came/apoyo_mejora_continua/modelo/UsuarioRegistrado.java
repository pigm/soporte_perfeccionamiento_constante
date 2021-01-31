package cl.mineduc.came.apoyo_mejora_continua.modelo;

import java.util.Date;
import java.util.List;

import cl.mineduc.came.apoyo_mejora_continua.modelo.auth.Usuario;

public class UsuarioRegistrado extends Usuario {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7972222661698690463L;

	private Long idUsuario;

	private Long idEstado;

	private Long idRegion;

	private Long idProvincia;
	
	private Long idDeprov;

	private String usuarioDominio;

	private Date ultimaModificacion;

	private Long idUsuarioModificado;

	private List<UsuarioPerfil> perfilesUsuarios;

	private Date fechaRegistro;
	
	private Estado estado;

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Long getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(Long idEstado) {
		this.idEstado = idEstado;
	}

	public Long getIdRegion() {
		return idRegion;
	}

	public void setIdRegion(Long idRegion) {
		this.idRegion = idRegion;
	}

	public Long getIdProvincia() {
		return idProvincia;
	}

	public void setIdProvincia(Long idProvincia) {
		this.idProvincia = idProvincia;
	}

	public String getUsuarioDominio() {
		return usuarioDominio;
	}

	public void setUsuarioDominio(String usuarioDominio) {
		this.usuarioDominio = usuarioDominio;
	}

	public Date getUltimaModificacion() {
		return ultimaModificacion;
	}

	public void setUltimaModificacion(Date ultimaModificacion) {
		this.ultimaModificacion = ultimaModificacion;
	}

	public Long getIdUsuarioModificado() {
		return idUsuarioModificado;
	}

	public void setIdUsuarioModificado(Long idUsuarioModificado) {
		this.idUsuarioModificado = idUsuarioModificado;
	}

	public List<UsuarioPerfil> getPerfilesUsuarios() {
		return perfilesUsuarios;
	}

	public void setPerfilesUsuarios(List<UsuarioPerfil> perfilesUsuarios) {
		this.perfilesUsuarios = perfilesUsuarios;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	
	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Long getIdDeprov() {
		return idDeprov;
	}

	public void setIdDeprov(Long idDeprov) {
		this.idDeprov = idDeprov;
	}

	@Override
	public String toString() {
		return "UsuarioRegistrado [idUsuario=" + idUsuario + ", idEstado=" + idEstado + ", idRegion=" + idRegion
				+ ", idProvincia=" + idProvincia + ", idDeprov=" + idDeprov + ", usuarioDominio=" + usuarioDominio
				+ ", ultimaModificacion=" + ultimaModificacion + ", idUsuarioModificado=" + idUsuarioModificado
				+ ", perfilesUsuarios=" + perfilesUsuarios + ", fechaRegistro=" + fechaRegistro + ", estado=" + estado
				+ "]";
	}
	
}
