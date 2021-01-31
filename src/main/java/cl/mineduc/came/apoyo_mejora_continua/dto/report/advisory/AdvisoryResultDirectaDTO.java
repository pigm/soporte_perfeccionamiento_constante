package cl.mineduc.came.apoyo_mejora_continua.dto.report.advisory;

import java.util.List;

public class AdvisoryResultDirectaDTO {
	private String region;   
	private String deprov;   
	private String rbd; 
	private String establecimiento; 
	private List<String> nombreSupervisor; 
	private String numeroSesion; 
	private String tipoPlanificacion; 
	private String fechaPlanificada; 
	private String fechaRealizada; 
	private String estado;
	private List<String> foco;
	private String estadoFoco;
	private List<String> acionesMejorasDesarrollo;
	private List<String> movimientoClaveComprometido;
	private List<String> movimientoClaveDesarrollado;
	private String plazo;
	private List<String> responsable;
	
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getDeprov() {
		return deprov;
	}
	public void setDeprov(String deprov) {
		this.deprov = deprov;
	}
	public String getRbd() {
		return rbd;
	}
	public void setRbd(String rbd) {
		this.rbd = rbd;
	}
	public String getEstablecimiento() {
		return establecimiento;
	}
	public void setEstablecimiento(String establecimiento) {
		this.establecimiento = establecimiento;
	}
	public List<String> getNombreSupervisor() {
		return nombreSupervisor;
	}
	public void setNombreSupervisor(List<String> nombreSupervisor) {
		this.nombreSupervisor = nombreSupervisor;
	}
	public String getNumeroSesion() {
		return numeroSesion;
	}
	public void setNumeroSesion(String numeroSesion) {
		this.numeroSesion = numeroSesion;
	}
	public String getTipoPlanificacion() {
		return tipoPlanificacion;
	}
	public void setTipoPlanificacion(String tipoPlanificacion) {
		this.tipoPlanificacion = tipoPlanificacion;
	}
	public String getFechaPlanificada() {
		return fechaPlanificada;
	}
	public void setFechaPlanificada(String fechaPlanificada) {
		this.fechaPlanificada = fechaPlanificada;
	}
	public String getFechaRealizada() {
		return fechaRealizada;
	}
	public void setFechaRealizada(String fechaRealizada) {
		this.fechaRealizada = fechaRealizada;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public List<String> getFoco() {
		return foco;
	}
	public void setFoco(List<String> foco) {
		this.foco = foco;
	}
	public String getEstadoFoco() {
		return estadoFoco;
	}
	public void setEstadoFoco(String estadoFoco) {
		this.estadoFoco = estadoFoco;
	}
	public List<String> getAcionesMejorasDesarrollo() {
		return acionesMejorasDesarrollo;
	}
	public void setAcionesMejorasDesarrollo(List<String> acionesMejorasDesarrollo) {
		this.acionesMejorasDesarrollo = acionesMejorasDesarrollo;
	}
	public  List<String> getMovimientoClaveComprometido() {
		return movimientoClaveComprometido;
	}
	public void setMovimientoClaveComprometido( List<String> movimientoClaveComprometido) {
		this.movimientoClaveComprometido = movimientoClaveComprometido;
	}
	public List<String> getMovimientoClaveDesarrollado() {
		return movimientoClaveDesarrollado;
	}
	public void setMovimientoClaveDesarrollado(List<String> movimientoClaveDesarrollado) {
		this.movimientoClaveDesarrollado = movimientoClaveDesarrollado;
	}
	public String getPlazo() {
		return plazo;
	}
	public void setPlazo(String plazo) {
		this.plazo = plazo;
	}
	public List<String>  getResponsable() {
		return responsable;
	}
	public void setResponsable(List<String> responsable) {
		this.responsable = responsable;
	}
}
