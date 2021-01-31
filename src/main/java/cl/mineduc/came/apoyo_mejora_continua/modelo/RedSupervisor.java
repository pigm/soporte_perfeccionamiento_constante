package cl.mineduc.came.apoyo_mejora_continua.modelo;

import java.time.LocalDateTime;

public class RedSupervisor {
	
	private Long idRedSupervisor;
	private Long idRed;
	private Long idSupervisor;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	private Long idAsignacionSupervisor;
	public Long getIdRedSupervisor() {
		return idRedSupervisor;
	}
	public void setIdRedSupervisor(Long idRedSupervisor) {
		this.idRedSupervisor = idRedSupervisor;
	}
	public Long getIdRed() {
		return idRed;
	}
	public void setIdRed(Long idRed) {
		this.idRed = idRed;
	}
	public Long getIdSupervisor() {
		return idSupervisor;
	}
	public void setIdSupervisor(Long idSupervisor) {
		this.idSupervisor = idSupervisor;
	}
	public LocalDateTime getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}
	public LocalDateTime getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}
	public Long getIdAsignacionSupervisor() {
		return idAsignacionSupervisor;
	}
	public void setIdAsignacionSupervisor(Long idAsignacionSupervisor) {
		this.idAsignacionSupervisor = idAsignacionSupervisor;
	}
	
}
