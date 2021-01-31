package cl.mineduc.came.apoyo_mejora_continua.modelo;

import java.util.Date;

public class PeriodoPlanificacionImplementacionApoyo {
    private Long idPeriodoPlanificacionImplementacionApoyo;

    private Long idPeriodo;
	
	private Long idRegion;
	
	private Long idDeprov;
	
    private Date fechaRegistro;

	private Date fechaInicio;

	private Date fechaFin;

	private Long idUsuarioRegistro;
	
	private Boolean esCasoEspecial;
	
	private String descripcion;

    public Long getIdPeriodoPlanificacionImplementacionApoyo() {
        return idPeriodoPlanificacionImplementacionApoyo;
    }

    public void setIdPeriodoPlanificacionImplementacionApoyo(Long idPeriodoPlanificacionImplementacionApoyo) {
        this.idPeriodoPlanificacionImplementacionApoyo = idPeriodoPlanificacionImplementacionApoyo;
    }

    public Long getIdPeriodo() {
        return idPeriodo;
    }

    public void setIdPeriodo(Long idPeriodo) {
        this.idPeriodo = idPeriodo;
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

	private PeriodoPlanificacionImplementacionApoyo(BuilderApoyo builder) {
    	this.idPeriodoPlanificacionImplementacionApoyo = builder.idPeriodoPlanificacionImplementacionApoyo;
    	this.idPeriodo = builder.idPeriodo;
    	this.fechaInicio = builder.fechaInicio;
		this.fechaFin = builder.fechaFin;
		this.idUsuarioRegistro = builder.idUsuarioRegistro;
		this.esCasoEspecial = builder.esCasoEspecial;
		this.descripcion = builder.descripcion;
		this.idDeprov = builder.idDeprov;
		this.idRegion = builder.idRegion;
    }
   
    private PeriodoPlanificacionImplementacionApoyo() {
	}

	public static class BuilderApoyo {
    	private Long idPeriodoPlanificacionImplementacionApoyo;
    	
        private Long idPeriodo;

    	private Date fechaInicio;

    	private Date fechaFin;

    	private Long idUsuarioRegistro;
    	
    	private Boolean esCasoEspecial;
    	
    	private String descripcion;
    	
    	private Long idDeprov;
    	
    	private Long idRegion;
        
        public BuilderApoyo withIdImplementationSupport(Long val) {
        	idPeriodoPlanificacionImplementacionApoyo = val;
        	return this;
        }
        
        public BuilderApoyo withIdPeriod(Long val) {
        	idPeriodo = val;
        	return this;
        }
        
        public BuilderApoyo withIdDeprov(Long val) {
        	idDeprov = val;
        	return this;
        }
        
        public BuilderApoyo withIdRegion(Long val) {
        	idRegion = val;
        	return this;
        }
        
        public BuilderApoyo withStartDate(Date val) {
			fechaInicio = val;
			return this;
		}
		public BuilderApoyo withEndDate(Date val) {
			fechaFin = val;
			return this;
		}
		
		public BuilderApoyo withIdUserRegistry(Long val) {
			idUsuarioRegistro = val;
			return this;
		}
		
		public BuilderApoyo withSpecialCase(Boolean val) {
			esCasoEspecial = val;
			return this;
		}
		
		public BuilderApoyo withDescription(String val) {
			descripcion = val;
			return this;
		}
        
        public PeriodoPlanificacionImplementacionApoyo build() {
        	return new PeriodoPlanificacionImplementacionApoyo(this);
        }
        
    }

	public Boolean getEsCasoEspecial() {
		return esCasoEspecial;
	}

	public void setEsCasoEspecial(Boolean esCasoEspecial) {
		this.esCasoEspecial = esCasoEspecial;
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
}