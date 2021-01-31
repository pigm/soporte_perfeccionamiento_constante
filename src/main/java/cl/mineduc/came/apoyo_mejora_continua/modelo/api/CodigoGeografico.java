package cl.mineduc.came.apoyo_mejora_continua.modelo.api;

public class CodigoGeografico {

	private Integer codigoRegion;
	private String nombreRegion;
	private Integer codigoDeprov;
	private String nombreDeprov;
	private Integer codigoComuna;
	private String nombreComuna;

	private String lat;
	private String lon;

	public Integer getCodigoRegion() {
		return codigoRegion;
	}

	public void setCodigoRegion(Integer codigoRegion) {
		this.codigoRegion = codigoRegion;
	}

	public String getNombreRegion() {
		return nombreRegion;
	}

	public void setNombreRegion(String nombreRegion) {
		this.nombreRegion = nombreRegion;
	}

	public Integer getCodigoDeprov() {
		return codigoDeprov;
	}

	public void setCodigoDeprov(Integer codigoDeprov) {
		this.codigoDeprov = codigoDeprov;
	}

	public String getNombreDeprov() {
		return nombreDeprov;
	}

	public void setNombreDeprov(String nombreDeprov) {
		this.nombreDeprov = nombreDeprov;
	}

	public Integer getCodigoComuna() {
		return codigoComuna;
	}

	public void setCodigoComuna(Integer codigoComuna) {
		this.codigoComuna = codigoComuna;
	}

	public String getNombreComuna() {
		return nombreComuna;
	}

	public void setNombreComuna(String nombreComuna) {
		this.nombreComuna = nombreComuna;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLon() {
		return lon;
	}

	public void setLon(String lon) {
		this.lon = lon;
	}

}
