package cl.mineduc.came.apoyo_mejora_continua.dto.assignment;

import java.util.List;

public class AssignDTO {
	
	private String id;
	private List<String> supervisores;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<String> getSupervisores() {
		return supervisores;
	}
	public void setSupervisores(List<String> supervisores) {
		this.supervisores = supervisores;
	}
	
}
