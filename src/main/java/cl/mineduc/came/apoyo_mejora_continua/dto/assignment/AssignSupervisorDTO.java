package cl.mineduc.came.apoyo_mejora_continua.dto.assignment;

import java.util.List;

import cl.mineduc.came.apoyo_mejora_continua.dto.shared.SelectorDTO;

public class AssignSupervisorDTO {
	
	private String tipo;
	private String id;
	private String nombre;
	private List<SelectorDTO> establecimientos;
	private List<SelectorDTO> supervisores;
	private Integer asignacionMaxima;
	
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public List<SelectorDTO> getEstablecimientos() {
		return establecimientos;
	}
	public void setEstablecimientos(List<SelectorDTO> establecimientos) {
		this.establecimientos = establecimientos;
	}
	public List<SelectorDTO> getSupervisores() {
		return supervisores;
	}
	public void setSupervisores(List<SelectorDTO> supervisores) {
		this.supervisores = supervisores;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getAsignacionMaxima() {
		return asignacionMaxima;
	}
	public void setAsignacionMaxima(Integer asignacionMaxima) {
		this.asignacionMaxima = asignacionMaxima;
	}
	
}
