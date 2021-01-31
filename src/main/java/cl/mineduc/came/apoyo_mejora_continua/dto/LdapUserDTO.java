package cl.mineduc.came.apoyo_mejora_continua.dto;

public class LdapUserDTO {
	
	private String login;
	private String rut;
	private String dv;
	private String nombre;
	private String paterno;
	private String materno;
	private String email;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getRut() {
		return rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}

	public String getDv() {
		return dv;
	}

	public void setDv(String dv) {
		this.dv = dv;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPaterno() {
		return paterno;
	}

	public void setPaterno(String paterno) {
		this.paterno = paterno;
	}

	public String getMaterno() {
		return materno;
	}

	public void setMaterno(String materno) {
		this.materno = materno;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "LdapUserList [login=" + login + ", rut=" + rut + ", dv=" + dv + ", nombre=" + nombre + ", paterno="
				+ paterno + ", materno=" + materno + ", email=" + email + "]";
	}


}
