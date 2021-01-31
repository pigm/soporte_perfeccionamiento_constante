package cl.mineduc.came.apoyo_mejora_continua.dto.period;

import java.io.Serializable;
import java.util.Date;

public class PeriodoAsignacionSupervisoresDTO implements DTOEntity, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4135267939051011590L;

	private Long idPeriodoAsignacionSupervisores;

	private Long idPeriodo;

	private Long idRegion;

	private Long idDeprov;

	private Date fechaRegistro;

	private Date fechaInicio;

	private Date fechaFin;

	private Long idUsuarioRegistro;
	
	private Boolean casoEspecial;
	
	private String motivo;
	
	private String region;

	private String usuarioRegistro;

	private String deprov;

	public String getRegion() {
		return region;
	}

	public String getUsuarioRegistro() {
		return usuarioRegistro;
	}

	public String getDeprov() {
		return deprov;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public void setUsuarioRegistro(String usuarioRegistro) {
		this.usuarioRegistro = usuarioRegistro;
	}

	public void setDeprov(String deprov) {
		this.deprov = deprov;
	}

	public Long getIdPeriodoAsignacionSupervisores() {
		return idPeriodoAsignacionSupervisores;
	}

	public Long getIdPeriodo() {
		return idPeriodo;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public Long getIdUsuarioRegistro() {
		return idUsuarioRegistro;
	}

	public void setIdPeriodoAsignacionSupervisores(Long idPeriodoAsignacionSupervisores) {
		this.idPeriodoAsignacionSupervisores = idPeriodoAsignacionSupervisores;
	}

	public void setIdPeriodo(Long idPeriodo) {
		this.idPeriodo = idPeriodo;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public void setIdUsuarioRegistro(Long idUsuarioRegistro) {
		this.idUsuarioRegistro = idUsuarioRegistro;
	}
	
	public Boolean getCasoEspecial() {
		return casoEspecial;
	}

	public void setCasoEspecial(Boolean casoEspecial) {
		this.casoEspecial = casoEspecial;
	}

	@Override
	public String toString() {
		return "PeriodoAsignacionSupervisoresDTO [idPeriodoAsignacionSupervisores=" + idPeriodoAsignacionSupervisores
				+ ", idPeriodo=" + idPeriodo + ", fechaRegistro=" + fechaRegistro + ", fechaInicio=" + fechaInicio
				+ ", fechaFin=" + fechaFin + ", idUsuarioRegistro=" + idUsuarioRegistro + "]";
	}

	public Long getIdRegion() {
		return idRegion;
	}

	public void setIdRegion(Long idRegion) {
		this.idRegion = idRegion;
	}

	public Long getIdDeprov() {
		return idDeprov;
	}

	public void setIdDeprov(Long idDeprov) {
		this.idDeprov = idDeprov;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	
}
