package cl.mineduc.came.apoyo_mejora_continua.dto.period;

import java.io.Serializable;

public class PeriodoOrganizacionPlanificacionProvincialDTO implements DTOEntity, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -690638803507654046L;

	private Long idPeriodoOrganizacionPlanificacionProvincial;

	private Long idDeprov;

	private Long idPeriodo;
	
	private Boolean casoEspecial;

	public Long getIdPeriodoOrganizacionPlanificacionProvincial() {
		return idPeriodoOrganizacionPlanificacionProvincial;
	}

	public Long getIdDeprov() {
		return idDeprov;
	}

	public Long getIdPeriodo() {
		return idPeriodo;
	}

	public void setIdPeriodoOrganizacionPlanificacionProvincial(Long idPeriodoOrganizacionPlanificacionProvincial) {
		this.idPeriodoOrganizacionPlanificacionProvincial = idPeriodoOrganizacionPlanificacionProvincial;
	}

	public void setIdDeprov(Long idDeprov) {
		this.idDeprov = idDeprov;
	}

	public void setIdPeriodo(Long idPeriodo) {
		this.idPeriodo = idPeriodo;
	}
	
	public Boolean getCasoEspecial() {
		return casoEspecial;
	}

	public void setCasoEspecial(Boolean casoEspecial) {
		this.casoEspecial = casoEspecial;
	}

	@Override
	public String toString() {
		return "PeriodoOrganizacionPlanificacionProvincialDTO [idPeriodoOrganizacionPlanificacionProvincial="
				+ idPeriodoOrganizacionPlanificacionProvincial + ", idDeprov=" + idDeprov + ", idPeriodo=" + idPeriodo
				+ "]";
	}
}
