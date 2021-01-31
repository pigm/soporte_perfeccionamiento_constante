package cl.mineduc.came.apoyo_mejora_continua.dto.assignment;

import java.util.List;

public class AssignmentListDTO {
	private List<String> supervisors;
	private String id;
	private String rbd;
	private String region;
	private String deprov;
	private String comuna;
	private String tipo;
	private String tipoRed;
	private String categoria;
	private String nombreRed;
	private String idAsignacionSupervisor;

	public String getRbd() {
		return rbd;
	}

	public String getRegion() {
		return region;
	}

	public String getDeprov() {
		return deprov;
	}

	public String getComuna() {
		return comuna;
	}

	public String getTipo() {
		return tipo;
	}

	public String getCategoria() {
		return categoria;
	}

	public String getNombreRed() {
		return nombreRed;
	}

	public void setRbd(String rbd) {
		this.rbd = rbd;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public void setDeprov(String deprov) {
		this.deprov = deprov;
	}

	public void setComuna(String comuna) {
		this.comuna = comuna;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public void setNombreRed(String nombreRed) {
		this.nombreRed = nombreRed;
	}

	public String getTipoRed() {
		return tipoRed;
	}

	public void setTipoRed(String tipoRed) {
		this.tipoRed = tipoRed;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIdAsignacionSupervisor() {
		return idAsignacionSupervisor;
	}

	public void setIdAsignacionSupervisor(String idAsignacionSupervisor) {
		this.idAsignacionSupervisor = idAsignacionSupervisor;
	}

	public List<String> getSupervisors() {
		return supervisors;
	}

	public void setSupervisors(List<String> supervisors) {
		this.supervisors = supervisors;
	}
	
}
