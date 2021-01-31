package cl.mineduc.came.apoyo_mejora_continua.dto.report.advisory;

public class AdvisoryRequestDTO {
	private String fechaInicio;
    private String fechaFin;
    
	public String getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public String getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}

}
