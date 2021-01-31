package cl.mineduc.came.apoyo_mejora_continua.dto.period;

import java.io.Serializable;
import java.util.Date;

public class PeriodoDocumentosRegionalesDTO implements DTOEntity, Serializable {

	/**
	* 
	*/
	private static final long serialVersionUID = 635718314276751198L;

	private Long idPeriodoDocumentosRegionales;

	private Long idRegion;

	private String region;

	private Long idDeprov;

	private String deprov;

	private Long idPeriodo;

	private Boolean visible;

	private Boolean casoEspecial;

	private String templatePath;

	private Date fechaRegistro;

	private Date fechaInicio;

	private Date fechaFin;

	private Long idUsuarioRegistro;

	private String regionalDocument;

	private String regionalDocumentName;

	private String usuarioRegistro;

	private String motivo;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getIdPeriodoDocumentosRegionales() {
		return idPeriodoDocumentosRegionales;
	}

	public Long getIdRegion() {
		return idRegion;
	}

	public Long getIdPeriodo() {
		return idPeriodo;
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

	public void setIdPeriodoDocumentosRegionales(Long idPeriodoDocumentosRegionales) {
		this.idPeriodoDocumentosRegionales = idPeriodoDocumentosRegionales;
	}

	public void setIdRegion(Long idRegion) {
		this.idRegion = idRegion;
	}

	public void setIdPeriodo(Long idPeriodo) {
		this.idPeriodo = idPeriodo;
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
		return "PeriodoDocumentosRegionalesDTO [idPeriodoDocumentosRegionales=" + idPeriodoDocumentosRegionales
				+ ", idRegion=" + idRegion + ", idPeriodo=" + idPeriodo + ", visible=" + visible + ", casoEspecial="
				+ casoEspecial + ", templatePath=" + templatePath + ", fechaRegistro=" + fechaRegistro
				+ ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", idUsuarioRegistro=" + idUsuarioRegistro
				+ "]";
	}

	public String getRegionalDocument() {
		return regionalDocument;
	}

	public void setRegionalDocument(String regionalDocument) {
		this.regionalDocument = regionalDocument;
	}

	public String getRegionalDocumentName() {
		return regionalDocumentName;
	}

	public void setRegionalDocumentName(String regionalDocumentName) {
		this.regionalDocumentName = regionalDocumentName;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public Long getIdDeprov() {
		return idDeprov;
	}

	public void setIdDeprov(Long idDeprov) {
		this.idDeprov = idDeprov;
	}

	public String getDeprov() {
		return deprov;
	}

	public void setDeprov(String deprov) {
		this.deprov = deprov;
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
