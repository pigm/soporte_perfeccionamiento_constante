package cl.mineduc.came.apoyo_mejora_continua.modelo;

import java.util.Date;

public class PeriodoConformacionRedes {

	private Long idPeriodoConformacionRedes;

	private Long idPeriodo;

	private Long idRegion;
	
	private Long idDeprov;

	private Date fechaRegistro;

	private Date fechaInicio;

	private Date fechaFin;

	private Long idUsuarioRegistro;
	
	private Boolean esCasoEspecial;
	
	private String descripcion;

	public Long getIdPeriodoConformacionRedes() {
		return idPeriodoConformacionRedes;
	}

	public void setIdPeriodoConformacionRedes(Long idPeriodoConformacionRedes) {
		this.idPeriodoConformacionRedes = idPeriodoConformacionRedes;
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

	private PeriodoConformacionRedes() {

	}

	private PeriodoConformacionRedes(BuilderConformation builder) {
		this.idPeriodo = builder.idPeriodo;
		this.fechaInicio = builder.fechaInicio;
		this.fechaFin = builder.fechaFin;
		this.idUsuarioRegistro = builder.idUsuarioRegistro;
		this.esCasoEspecial = builder.esCasoEspecial;
		this.descripcion = builder.descripcion;
		this.idRegion = builder.idRegion;
		this.idDeprov = builder.idDeprov;
	}

	public static class BuilderConformation {

		private Long idPeriodo;
		
		private Long idRegion;
		
		private Long idDeprov;

		private Date fechaInicio;

		private Date fechaFin;

		private Long idUsuarioRegistro;
		
		private Boolean esCasoEspecial;
		
		private String descripcion;

		public BuilderConformation withIdPeriod(Long val) {
			idPeriodo = val;
			return this;
		}
		
		public BuilderConformation withIdDeprov(Long val) {
			idDeprov = val;
			return this;
		}
		
		public BuilderConformation withIdRegion(Long val) {
			idRegion = val;
			return this;
		}
		
		public BuilderConformation withStartDate(Date val) {
			fechaInicio = val;
			return this;
		}
		public BuilderConformation withEndDate(Date val) {
			fechaFin = val;
			return this;
		}
		
		public BuilderConformation withIdUserRegistry(Long val) {
			idUsuarioRegistro = val;
			return this;
		}
		
		public BuilderConformation withSpecialCase(Boolean val) {
			esCasoEspecial = val;
			return this;
		}
		
		public BuilderConformation withDescription(String val) {
			descripcion = val;
			return this;
		}

		public PeriodoConformacionRedes build() {
			return new PeriodoConformacionRedes(this);
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