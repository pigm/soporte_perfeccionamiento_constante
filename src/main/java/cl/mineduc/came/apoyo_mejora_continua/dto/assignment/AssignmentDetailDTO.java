package cl.mineduc.came.apoyo_mejora_continua.dto.assignment;

import java.util.List;

import cl.mineduc.came.apoyo_mejora_continua.dto.shared.SelectorDTO;

public class AssignmentDetailDTO {
	private List<SelectorDTO> supervisores;
	private String region;
	private String deprov;
	private String comuna;
	private String tipo;
	private String tipoApoyo;
	private String categoria;
	private String nombreRed;
	private List<SelectorDTO> establecimientos;
	private String idRed;
	private String idSupervisor;

	public List<SelectorDTO> getSupervisores() {
		return supervisores;
	}

	public void setSupervisores(List<SelectorDTO> supervisores) {
		this.supervisores = supervisores;
	}

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

	public String getComuna() {
		return comuna;
	}

	public void setComuna(String comuna) {
		this.comuna = comuna;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getTipoApoyo() {
		return tipoApoyo;
	}

	public void setTipoApoyo(String tipoApoyo) {
		this.tipoApoyo = tipoApoyo;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getNombreRed() {
		return nombreRed;
	}

	public void setNombreRed(String nombreRed) {
		this.nombreRed = nombreRed;
	}

	public List<SelectorDTO> getEstablecimientos() {
		return establecimientos;
	}

	public void setEstablecimientos(List<SelectorDTO> establecimientos) {
		this.establecimientos = establecimientos;
	}

	public String getIdRed() {
		return idRed;
	}

	public void setIdRed(String idRed) {
		this.idRed = idRed;
	}

	public String getIdSupervisor() {
		return idSupervisor;
	}

	public void setIdSupervisor(String idSupervisor) {
		this.idSupervisor = idSupervisor;
	}

}
