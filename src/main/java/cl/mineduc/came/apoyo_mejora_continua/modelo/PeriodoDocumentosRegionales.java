package cl.mineduc.came.apoyo_mejora_continua.modelo;

import java.util.Date;

public class PeriodoDocumentosRegionales {
    private Long idPeriodoDocumentosRegionales;

    private Long idRegion;
	
	private Long idDeprov;

    private Long idPeriodo;

	private Boolean visible;

	private Boolean esCasoEspecial;

	private String templatePath;

	private Date fechaRegistro;

	private Date fechaInicio;

	private Date fechaFin;

	private Long idUsuarioRegistro;
	
	private String descripcion;

    public Long getIdPeriodoDocumentosRegionales() {
        return idPeriodoDocumentosRegionales;
    }

    public void setIdPeriodoDocumentosRegionales(Long idPeriodoDocumentosRegionales) {
        this.idPeriodoDocumentosRegionales = idPeriodoDocumentosRegionales;
    }

    public Long getIdRegion() {
        return idRegion;
    }

    public void setIdRegion(Long idRegion) {
        this.idRegion = idRegion;
    }

    public Long getIdPeriodo() {
        return idPeriodo;
    }

    public void setIdPeriodo(Long idPeriodo) {
        this.idPeriodo = idPeriodo;
    }
    
    public Boolean getVisible() {
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

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

	public void setCasoEspecial(Boolean esCasoEspecial) {
		this.esCasoEspecial = esCasoEspecial;
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

	private PeriodoDocumentosRegionales() {
		
	}

	private PeriodoDocumentosRegionales(BuilderRegionals builder) {
    	this.idPeriodoDocumentosRegionales = builder.idPeriodoDocumentosRegionales;
    	this.idRegion = builder.idRegion;
    	this.idPeriodo = builder.idPeriodo;
    	this.visible = builder.visible;
		this.esCasoEspecial = builder.esCasoEspecial;
		this.templatePath = builder.templatePath;
		this.fechaInicio = builder.fechaInicio;
		this.fechaFin = builder.fechaFin;
		this.idUsuarioRegistro = builder.idUsuarioRegistro;
		this.descripcion = builder.descripcion;
		this.idDeprov = builder.idDeprov;
    }
    
    public static class BuilderRegionals {
    	
    	private Long idPeriodoDocumentosRegionales;

        private Long idRegion;
        
        private Long idDeprov;

        private Long idPeriodo;

    	private Boolean visible;

    	private Boolean esCasoEspecial;

    	private String templatePath;

    	private Date fechaInicio;

    	private Date fechaFin;

    	private Long idUsuarioRegistro;
    	
    	private String descripcion;
        
        public BuilderRegionals withIdPeriodDocumentsRegionals(Long val) {
        	idPeriodoDocumentosRegionales = val; return this;
        }
        
        public BuilderRegionals withIdRegion(Long val) {
        	idRegion = val; return this;
        }
        
        public BuilderRegionals withIdPeriod(Long val) {
        	idPeriodo = val; return this;
        }
        
        public BuilderRegionals withVisible(Boolean val) {
			visible = val;
			return this;
		}
        
        public BuilderRegionals withDeprov(Long val) {
        	idDeprov = val;
			return this;
		}
        
		public BuilderRegionals withSpecialCase(Boolean val) {
			esCasoEspecial = val;
			return this;
		}
		public BuilderRegionals withTemplatePath(String val) {
			templatePath = val;
			return this;
		}
		public BuilderRegionals withStartDate(Date val) {
			fechaInicio = val;
			return this;
		}
		public BuilderRegionals withEndDate(Date val) {
			fechaFin = val;
			return this;
		}
		
		public BuilderRegionals withIdUserRegistry(Long val) {
			idUsuarioRegistro = val;
			return this;
		}

		public BuilderRegionals withDescription(String val) {
			descripcion = val;
			return this;
		}

		public PeriodoDocumentosRegionales build() {
			return new PeriodoDocumentosRegionales(this);
		}
        
    }

	public Boolean getEsCasoEspecial() {
		return esCasoEspecial;
	}

	public void setEsCasoEspecial(Boolean esCasoEspecial) {
		this.esCasoEspecial = esCasoEspecial;
	}

	public Long getIdDeprov() {
		return idDeprov;
	}

	public void setIdDeprov(Long idDeprov) {
		this.idDeprov = idDeprov;
	}
}