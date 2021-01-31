package cl.mineduc.came.apoyo_mejora_continua.modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Periodo {
	
    private Long idPeriodo;
    
    private Integer anio;
    
    private Long idUsuarioRegistro;
    
    private Date fechaRegistro;
    
    private List<PeriodoDocumentosProvinciales> documentosProvinciales;
    
    private List<PeriodoDocumentosRegionales> documentosRegionales;
    
    private List<Red> redes;
    
    private List<RedEstablecimiento> redEstablecimientos;
    
    private List<PeriodoPlanificacionImplementacionApoyo> implementacionesApoyo;
    
    private List<PeriodoConformacionRedes> conformacionRedes;
    
    private List<PeriodoAsignacionSupervisores> asignaciones;
    
    private Periodo(Builder builder) {
    	this.idPeriodo = builder.idPeriodo;
    	this.anio = builder.anio;
    	this.idUsuarioRegistro = builder.idUsuarioRegistro;
    	this.fechaRegistro = builder.fechaRegistro;
    	this.documentosProvinciales = builder.documentosProvinciales;
    	this.documentosRegionales = builder.documentosRegionales;
    	this.redes = builder.redes;    
    	this.implementacionesApoyo = builder.implementacionesApoyo;
    	this.redEstablecimientos = builder.redEstablecimientos;
    	this.conformacionRedes = builder.conformacionRedes;
    	this.asignaciones = builder.asignaciones;
    }
    
    private Periodo() {}

	public Long getIdPeriodo() {
		return idPeriodo;
	}
	
	public Integer getAnio() {
		return anio;
	}

	public Long getIdUsuarioRegistro() {
		return idUsuarioRegistro;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public void setAnio(Integer anio) {
		this.anio = anio;
	}

	public void setIdUsuarioRegistro(Long idUsuarioRegistro) {
		this.idUsuarioRegistro = idUsuarioRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public List<PeriodoDocumentosProvinciales> getDocumentosProvinciales() {
		return documentosProvinciales;
	}

	public List<PeriodoDocumentosRegionales> getDocumentosRegionales() {
		return documentosRegionales;
	}

	public List<Red> getRedes() {
		return redes;
	}

	public List<PeriodoPlanificacionImplementacionApoyo> getImplementacionesApoyo() {
		return implementacionesApoyo;
	}

	public void setIdPeriodo(Long idPeriodo) {
		this.idPeriodo = idPeriodo;
	}

	public void setDocumentosProvinciales(List<PeriodoDocumentosProvinciales> documentosProvinciales) {
		this.documentosProvinciales = documentosProvinciales;
	}

	public void setDocumentosRegionales(List<PeriodoDocumentosRegionales> documentosRegionales) {
		this.documentosRegionales = documentosRegionales;
	}

	public void setRedes(List<Red> redes) {
		this.redes = redes;
	}

	public void setImplementacionesApoyo(List<PeriodoPlanificacionImplementacionApoyo> implementacionesApoyo) {
		this.implementacionesApoyo = implementacionesApoyo;
	}

	public List<RedEstablecimiento> getRedEstablecimientos() {
		return redEstablecimientos;
	}

	public void setRedEstablecimientos(List<RedEstablecimiento> redEstablecimientos) {
		this.redEstablecimientos = redEstablecimientos;
	}

	public List<PeriodoConformacionRedes> getConformacionRedes() {
		return conformacionRedes;
	}

	public List<PeriodoAsignacionSupervisores> getAsignaciones() {
		return asignaciones;
	}

	public void setConformacionRedes(List<PeriodoConformacionRedes> conformacionRedes) {
		this.conformacionRedes = conformacionRedes;
	}

	public void setAsignaciones(List<PeriodoAsignacionSupervisores> asignaciones) {
		this.asignaciones = asignaciones;
	}

	public static class Builder {
		
		private Long idPeriodo;
		
		private Integer anio;
	    
	    private Long idUsuarioRegistro;
	    
	    private Date fechaRegistro;
		
		private List<PeriodoDocumentosProvinciales> documentosProvinciales;
	    
	    private List<PeriodoDocumentosRegionales> documentosRegionales;
	    
	    private List<Red> redes;
	      	    
	    private List<PeriodoPlanificacionImplementacionApoyo> implementacionesApoyo;

	    private List<RedEstablecimiento> redEstablecimientos;
	    
	    private List<PeriodoConformacionRedes> conformacionRedes;
	    
	    private List<PeriodoAsignacionSupervisores> asignaciones;
	    
	    public Builder withYear(Integer val) {
	    	anio = val;
	    	return this;
	    }
	    
	    public Builder withIdUsuarioRegistro(Long val) {
	    	idUsuarioRegistro = val;
	    	return this;
	    }
	    
	    public Builder withDateRegistry(Date val) {
	    	fechaRegistro = val;
	    	return this;
	    }
	    
	    public Builder withDocumentsProvincials(PeriodoDocumentosProvinciales provincial) {
	    	if(documentosProvinciales == null) {
	    		documentosProvinciales = new ArrayList<PeriodoDocumentosProvinciales>();
	    		documentosProvinciales.add(provincial);
	    	} else {
	    		documentosProvinciales.add(provincial);
	    	}
	    	return this;
	    }
	    
	    public Builder withDocumentsRegionals(PeriodoDocumentosRegionales regional) {
	    	if(documentosRegionales == null) {
	    		documentosRegionales = new ArrayList<PeriodoDocumentosRegionales>();
	    		documentosRegionales.add(regional);
	    	} else {
	    		documentosRegionales.add(regional);
	    	}
	    	return this;
	    }
	    
	    public Builder withNetworks(Red red) {
	    	if(redes == null) {
	    		redes = new ArrayList<Red>();
	    		redes.add(red);
	    	} else {
	    		redes.add(red);
	    	}
	    	return this;
	    }
	    
	    public Builder withSupportsImplementations(PeriodoPlanificacionImplementacionApoyo supportsImplementations) {
	    	if(implementacionesApoyo == null) {
	    		implementacionesApoyo = new ArrayList<PeriodoPlanificacionImplementacionApoyo>();
	    		implementacionesApoyo.add(supportsImplementations);
	    	} else {
	    		implementacionesApoyo.add(supportsImplementations);
	    	}
	    	return this;
	    }
	    	    
	    public Builder withNetworkEstablishments(RedEstablecimiento redEstablecimiento) {
	    	if(redEstablecimientos == null) {
	    		redEstablecimientos = new ArrayList<RedEstablecimiento>();
	    		redEstablecimientos.add(redEstablecimiento);
	    	} else {
	    		redEstablecimientos.add(redEstablecimiento);
	    	}
	    	return this;
	    }
	    
	    public Builder withNetworksConformation(PeriodoConformacionRedes conformacion) {
	    	if(conformacionRedes == null) {
	    		conformacionRedes = new ArrayList<PeriodoConformacionRedes>();
	    		conformacionRedes.add(conformacion);
	    	} else {
	    		conformacionRedes.add(conformacion);
	    	}
	    	return this;
	    }
	    
	    public Builder withSupervisorAssignment(PeriodoAsignacionSupervisores supervisor) {
	    	if(asignaciones == null) {
	    		asignaciones = new ArrayList<PeriodoAsignacionSupervisores>();
	    		asignaciones.add(supervisor);
	    	} else {
	    		asignaciones.add(supervisor);
	    	}
	    	return this;
	    }
	    
	    public Periodo build() {
	    	return new Periodo(this);
	    }
	}
    
}