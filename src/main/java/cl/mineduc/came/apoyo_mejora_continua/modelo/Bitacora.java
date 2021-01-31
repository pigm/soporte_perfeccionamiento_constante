package cl.mineduc.came.apoyo_mejora_continua.modelo;

import java.util.Date;

public class Bitacora {
	private Long idBitacora;
	private String usuarioDominio;
	private String profile;
	private String modulo;
	private String subModulo;
	private String evento;
	private String data;
	private Date fecha;

	public Long getIdBitacora() {
		return idBitacora;
	}

	public void setIdBitacora(Long idBitacora) {
		this.idBitacora = idBitacora;
	}

	public String getUsuarioDominio() {
		return usuarioDominio;
	}

	public void setUsuarioDominio(String usuarioDominio) {
		this.usuarioDominio = usuarioDominio;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public String getModulo() {
		return modulo;
	}

	public void setModulo(String modulo) {
		this.modulo = modulo;
	}
	
	public String getEvento() {
		return evento;
	}

	public void setEvento(String evento) {
		this.evento = evento;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getSubModulo() {
		return subModulo;
	}

	public void setSubModulo(String subModulo) {
		this.subModulo = subModulo;
	}

	@Override
	public String toString() {
		return "Bitacora [data=" + data + ", evento=" + evento + ", fecha=" + fecha + ", idBitacora=" + idBitacora
				+ ", modulo=" + modulo + ", profile=" + profile + ", subModulo=" + subModulo + ", usuarioDominio="
				+ usuarioDominio + "]";
	}
	
}