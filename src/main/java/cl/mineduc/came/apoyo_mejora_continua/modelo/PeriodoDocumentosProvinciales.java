package cl.mineduc.came.apoyo_mejora_continua.modelo;

import java.util.Date;

public class PeriodoDocumentosProvinciales {

	private Long idPeriodoDocumentosProvinciales;

	private Long idDeprov;

	private Long idPeriodo;

	private Long idRegion;

	private Boolean visible;

	private Boolean esCasoEspecial;

	private String templatePath;

	private Date fechaRegistro;

	private Date fechaInicio;

	private Date fechaFin;

	private Long idUsuarioRegistro;
	
	private String descripcion;

	public Long getIdDeprov() {
		return idDeprov;
	}

	public void setIdDeprov(Long idDeprov) {
		this.idDeprov = idDeprov;
	}

	public Long getIdPeriodo() {
		return idPeriodo;
	}

	public void setIdPeriodo(Long idPeriodo) {
		this.idPeriodo = idPeriodo;
	}

	public Long getIdRegion() {
		return idRegion;
	}

	public Boolean isVisible() {
		return visible;
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

	public void setIdRegion(Long idRegion) {
		this.idRegion = idRegion;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
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
	
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	private PeriodoDocumentosProvinciales() {

	}

	private PeriodoDocumentosProvinciales(BuilderDocuments builder) {
		this.idPeriodoDocumentosProvinciales = builder.idPeriodoDocumentosProvinciales;
		this.idDeprov = builder.idDeprov;
		this.idPeriodo = builder.idPeriodo;
		this.idRegion = builder.idRegion;
		this.visible = builder.visible;
		this.esCasoEspecial = builder.esCasoEspecial;
		this.templatePath = builder.templatePath;
		this.fechaInicio = builder.fechaInicio;
		this.fechaFin = builder.fechaFin;
		this.idUsuarioRegistro = builder.idUsuarioRegistro;
		this.descripcion = builder.descripcion;
	}

	public static class BuilderDocuments {
		private Long idPeriodoDocumentosProvinciales;

		private Long idDeprov;

		private Long idPeriodo;

		private Long idRegion;

		private Boolean visible;

		private Boolean esCasoEspecial;

		private String templatePath;

		private Date fechaInicio;

		private Date fechaFin;

		private Long idUsuarioRegistro;
		
		private String descripcion;

		public BuilderDocuments withId(Long val) {
			idPeriodoDocumentosProvinciales = val;
			return this;
		}

		public BuilderDocuments withIdDeprov(Long val) {
			idDeprov = val;
			return this;
		}

		public BuilderDocuments withIdPeriod(Long val) {
			idPeriodo = val;
			return this;
		}
		
		public BuilderDocuments withIdRegion(Long val) {
			idRegion = val;
			return this;
		}
		public BuilderDocuments withVisible(Boolean val) {
			visible = val;
			return this;
		}
		public BuilderDocuments withSpecialCase(Boolean val) {
			esCasoEspecial = val;
			return this;
		}
		public BuilderDocuments withTemplatePath(String val) {
			templatePath = val;
			return this;
		}
		public BuilderDocuments withStartDate(Date val) {
			fechaInicio = val;
			return this;
		}
		public BuilderDocuments withEndDate(Date val) {
			fechaFin = val;
			return this;
		}
		
		public BuilderDocuments withIdUserRegistry(Long val) {
			idUsuarioRegistro = val;
			return this;
		}
		
		public BuilderDocuments withDescription(String val) {
			descripcion = val;
			return this;
		}

		public PeriodoDocumentosProvinciales build() {
			return new PeriodoDocumentosProvinciales(this);
		}

	}

	public Boolean getEsCasoEspecial() {
		return esCasoEspecial;
	}

	public void setEsCasoEspecial(Boolean esCasoEspecial) {
		this.esCasoEspecial = esCasoEspecial;
	}

	public Long getIdPeriodoDocumentosProvinciales() {
		return idPeriodoDocumentosProvinciales;
	}

	public void setIdPeriodoDocumentosProvinciales(Long idPeriodoDocumentosProvinciales) {
		this.idPeriodoDocumentosProvinciales = idPeriodoDocumentosProvinciales;
	}

}