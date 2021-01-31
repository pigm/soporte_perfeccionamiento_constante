package cl.mineduc.came.apoyo_mejora_continua.dto.networks;

import java.util.Date;

public class NetworksRequestDTO {
	private String nombre;
	private String rbd;
	private Integer regionId;
	private Integer deprovId;
	private String tipoRedId;
	private String startDate;
	private String endDate;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getRbd() {
		return rbd;
	}

	public void setRbd(String rbd) {
		this.rbd = rbd;
	}

	public Integer getRegionId() {
		return regionId;
	}

	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}

	public Integer getDeprovId() {
		return deprovId;
	}

	public void setDeprovId(Integer deprovId) {
		this.deprovId = deprovId;
	}

	public String getTipoRedId() {
		return tipoRedId;
	}

	public void setTipoRedId(String tipoRedId) {
		this.tipoRedId = tipoRedId;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	
}