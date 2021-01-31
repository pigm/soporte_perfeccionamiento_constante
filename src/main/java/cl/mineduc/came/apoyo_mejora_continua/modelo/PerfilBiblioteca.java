package cl.mineduc.came.apoyo_mejora_continua.modelo;

import java.util.Date;

public class PerfilBiblioteca {
	private Long idPerfilBiblioteca;
	private Long idBiblioteca;
	private Long idPerfil;
	private Long idPeriodo;
	private Integer idRegion ;
	private Integer idDeprov;
	private Long idUsuarioRegistro;
	private Date fechaRegistro;
	
	
	
	public Long getIdPerfilBiblioteca() {
		return idPerfilBiblioteca;
	}
	public void setIdPerfilBiblioteca(Long idPerfilBiblioteca) {
		this.idPerfilBiblioteca = idPerfilBiblioteca;
	}
	public Long getIdBiblioteca() {
		return idBiblioteca;
	}
	public void setIdBiblioteca(Long idBiblioteca) {
		this.idBiblioteca = idBiblioteca;
	}
	public Long getIdPerfil() {
		return idPerfil;
	}
	public void setIdPerfil(Long idPerfil) {
		this.idPerfil = idPerfil;
	}
	public Long getIdPeriodo() {
		return idPeriodo;
	}
	public void setIdPeriodo(Long idPeriodo) {
		this.idPeriodo = idPeriodo;
	}
	public Integer getIdRegion() {
		return idRegion;
	}
	public void setIdRegion(Integer idRegion) {
		this.idRegion = idRegion;
	}
	public Integer getIdDeprov() {
		return idDeprov;
	}
	public void setIdDeprov(Integer idDeprov) {
		this.idDeprov = idDeprov;
	}
	public Long getIdUsuarioRegistro() {
		return idUsuarioRegistro;
	}
	public void setIdUsuarioRegistro(Long idUsuarioRegistro) {
		this.idUsuarioRegistro = idUsuarioRegistro;
	}
	public Date getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	
	
}
