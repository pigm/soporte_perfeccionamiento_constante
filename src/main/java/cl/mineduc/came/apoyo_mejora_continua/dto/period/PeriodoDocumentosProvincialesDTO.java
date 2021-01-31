package cl.mineduc.came.apoyo_mejora_continua.dto.period;

import java.io.Serializable;
import java.util.Date;

public class PeriodoDocumentosProvincialesDTO implements DTOEntity, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6690176987162421609L;

	private Long periodoDocumentosProvinciales;

	private Long idDeprov;

	private String deprov;

	private Long idPeriodo;

	private Long idRegion;

	private String region;

	private Boolean visible;

	private Boolean casoEspecial;

	private String templatePath;

	private Date fechaRegistro;

	private Date fechaInicio;

	private Date fechaFin;

	private Long idUsuarioRegistro;

	private String usuarioRegistro;

	private String motivo;

	private String provincialDocument;

	private String provincialDocumentName;

	public Long getPeriodoDocumentosProvinciales() {
		return periodoDocumentosProvinciales;
	}

	public Long getIdDeprov() {
		return idDeprov;
	}

	public Long getIdPeriodo() {
		return idPeriodo;
	}

	public Long getIdRegion() {
		return idRegion;
	}

	public Boolean getVisible() {
		return visible;
	}

	public Boolean getCasoEspecial() {
		return casoEspecial;
	}

	public String getTemplatePath() {
		return templatePath;
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

	public void setPeriodoDocumentosProvinciales(Long periodoDocumentosProvinciales) {
		this.periodoDocumentosProvinciales = periodoDocumentosProvinciales;
	}

	public void setIdDeprov(Long idDeprov) {
		this.idDeprov = idDeprov;
	}

	public void setIdPeriodo(Long idPeriodo) {
		this.idPeriodo = idPeriodo;
	}

	public void setIdRegion(Long idRegion) {
		this.idRegion = idRegion;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

	public void setCasoEspecial(Boolean casoEspecial) {
		this.casoEspecial = casoEspecial;
	}

	public void setTemplatePath(String templatePath) {
		this.templatePath = templatePath;
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

	@Override
	public String toString() {
		return "PeriodoDocumentosProvincialesDTO [periodoDocumentosProvinciales=" + periodoDocumentosProvinciales
				+ ", idDeprov=" + idDeprov + ", idPeriodo=" + idPeriodo + ", idRegion=" + idRegion + ", visible="
				+ visible + ", casoEspecial=" + casoEspecial + ", templatePath=" + templatePath + ", fechaRegistro="
				+ fechaRegistro + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", idUsuarioRegistro="
				+ idUsuarioRegistro + "]";
	}

	public String getProvincialDocument() {
		return provincialDocument;
	}

	public void setProvincialDocument(String provincialDocument) {
		this.provincialDocument = provincialDocument;
	}

	public String getProvincialDocumentName() {
		return provincialDocumentName;
	}

	public void setProvincialDocumentName(String provincialDocumentName) {
		this.provincialDocumentName = provincialDocumentName;
	}

	public String getDeprov() {
		return deprov;
	}

	public void setDeprov(String deprov) {
		this.deprov = deprov;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getUsuarioRegistro() {
		return usuarioRegistro;
	}

	public void setUsuarioRegistro(String usuarioRegistro) {
		this.usuarioRegistro = usuarioRegistro;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	
	
}
