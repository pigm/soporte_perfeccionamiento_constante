package cl.mineduc.came.apoyo_mejora_continua.dto.assignment;

public class AssignmentRequestDTO {
	private Integer regionId;
	private Integer deprovId;
	private Integer comunaId;
	private String tipoId;
	private String tipoRedId;
	private String categoriaId;
	private Integer rbd;
	private String nombreEstablecimiento;
	private String nombreRed;

	public Integer getRegionId() {
		return regionId;
	}

	public Integer getDeprovId() {
		return deprovId;
	}

	public Integer getComunaId() {
		return comunaId;
	}
	
	public String getNombreEstablecimiento() {
		return nombreEstablecimiento;
	}

	public String getNombreRed() {
		return nombreRed;
	}

	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}

	public void setDeprovId(Integer deprovId) {
		this.deprovId = deprovId;
	}

	public void setComunaId(Integer comunaId) {
		this.comunaId = comunaId;
	}
	
	public void setNombreEstablecimiento(String nombreEstablecimiento) {
		this.nombreEstablecimiento = nombreEstablecimiento;
	}

	public void setNombreRed(String nombreRed) {
		this.nombreRed = nombreRed;
	}

	public Integer getRbd() {
		return rbd;
	}

	public void setRbd(Integer rbd) {
		this.rbd = rbd;
	}

	public String getTipoId() {
		return tipoId;
	}

	public void setTipoId(String tipoId) {
		this.tipoId = tipoId;
	}

	public String getTipoRedId() {
		return tipoRedId;
	}

	public void setTipoRedId(String tipoRedId) {
		this.tipoRedId = tipoRedId;
	}

	public String getCategoriaId() {
		return categoriaId;
	}

	public void setCategoriaId(String categoriaId) {
		this.categoriaId = categoriaId;
	}

}
