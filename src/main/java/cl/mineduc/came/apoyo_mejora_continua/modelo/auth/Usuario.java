package cl.mineduc.came.apoyo_mejora_continua.modelo.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown =  true)
public class Usuario implements UserDetails{
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("rut") 
	private String rut;
	@JsonProperty("dv") 
	private String dv;
	@JsonProperty("nombre") 
	private String nombre;
	@JsonProperty("paterno") 
	private String paterno;
	@JsonProperty("materno") 
	private String materno;
	@JsonProperty("email") 
	private String email;
	@JsonProperty("login") 
	private String login;
	
	private boolean habilitado;
	
	private String token;
	private List<Sistema> sistemas;
	
	/** Spring Security related fields **/
	private List<Rol> authorities;
	/** Spring Security related fields **/

	private String ip;

	public String getRut() {
		return rut;
	}

	public void setRut(String v) {
		this.rut = v;
	}

	public String getDv() {
		return dv;
	}

	public void setDv(String v) {
		this.dv = v;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String v) {
		this.nombre = v;
	}

	public String getPaterno() {
		return paterno;
	}

	public void setPaterno(String v) {
		this.paterno = v;
	}

	public String getMaterno() {
		return materno;
	}

	public void setMaterno(String v) {
		this.materno = v;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String v) {
		this.email = v;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String v) {
		this.login = v;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String v) {
		this.token = v;
	}

	public List<Sistema> getSistemas() {
		return sistemas;
	}

	public void setSistemas(List<Sistema> v) {
		this.sistemas = v;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String vIp) {
		ip = vIp;
	}
	
	public boolean isHabilitado() {
		return habilitado;
	}

	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
	}

	@Override
	public String toString() {
		ToStringBuilder.setDefaultStyle(ToStringStyle.MULTI_LINE_STYLE);
		return ToStringBuilder.reflectionToString(this);
	}
	
	/** Spring Security related methods **/
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		/**TODO implement roles y privilegios del usuario de acuerdo a datos de ej:DB?**/
		authorities = new ArrayList<Rol>();
		Rol rol = new Rol();
		rol.setNombre("ADMIN");
		authorities.add(rol);
		
		/** TODO: BORRAR O REACONDICIONAR ESTE EJEMPLO **/
		/**
		authorities = new ArrayList<Rol>();
		List<Sistema> s = this.getSistemas();
		List<Rol> roles = null;
		for (Sistema sistema : s) {
			if (sistema.getNombre().equalsIgnoreCase("came")) {
				roles = sistema.getRoles();
				for (Rol rol : roles) {
					authorities.add(rol);
				}
			}
		}
		**/
		return this.authorities;
	}

	@Override
	public String getPassword() {
		return this.token;
	}

	@Override
	public String getUsername() {
		return this.login;
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	
	@Override
	public boolean isAccountNonLocked() {
		return this.habilitado;
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return this.habilitado;
	}
}
