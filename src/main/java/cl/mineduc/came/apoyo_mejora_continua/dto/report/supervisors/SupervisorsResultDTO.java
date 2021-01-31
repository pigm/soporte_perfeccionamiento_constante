package cl.mineduc.came.apoyo_mejora_continua.dto.report.supervisors;

import java.util.List;

public class SupervisorsResultDTO {
	private String nombre;
	private String rut;
	private String email;
	private String region;
	private String deprov;
	private List<String> establecimientosApoyadosFormaDirecta;
	private List<String> redesApoyadas;
	private String asesoriasRedRegistradas;
	private String asesoriasDirectasRegistradas;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getRut() {
		return rut;
	}
	public void setRut(String rut) {
		this.rut = rut;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public List<String> getEstablecimientosApoyadosFormaDirecta() {
		return establecimientosApoyadosFormaDirecta;
	}
	public void setEstablecimientosApoyadosFormaDirecta(List<String> establecimientosApoyadosFormaDirecta) {
		this.establecimientosApoyadosFormaDirecta = establecimientosApoyadosFormaDirecta;
	}
	public List<String> getRedesApoyadas() {
		return redesApoyadas;
	}
	public void setRedesApoyadas(List<String> redesApoyadas) {
		this.redesApoyadas = redesApoyadas;
	}
	public String getAsesoriasRedRegistradas() {
		return asesoriasRedRegistradas;
	}
	public void setAsesoriasRedRegistradas(String asesoriasRedRegistradas) {
		this.asesoriasRedRegistradas = asesoriasRedRegistradas;
	}
	public String getAsesoriasDirectasRegistradas() {
		return asesoriasDirectasRegistradas;
	}
	public void setAsesoriasDirectasRegistradas(String asesoriasDirectasRegistradas) {
		this.asesoriasDirectasRegistradas = asesoriasDirectasRegistradas;
	}
	
	
}

