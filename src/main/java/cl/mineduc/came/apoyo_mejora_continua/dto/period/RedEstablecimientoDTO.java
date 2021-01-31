package cl.mineduc.came.apoyo_mejora_continua.dto.period;

import java.io.Serializable;

public class RedEstablecimientoDTO implements DTOEntity, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8724702325903983570L;

	private Long idRedEstablecimiento;

	private Long idRed;

	private Long idEstablecimiento;

	private Long idPeriodo;

	public Long getIdRedEstablecimiento() {
		return idRedEstablecimiento;
	}

	public Long getIdRed() {
		return idRed;
	}

	public Long getIdEstablecimiento() {
		return idEstablecimiento;
	}

	public Long getIdPeriodo() {
		return idPeriodo;
	}

	public void setIdRedEstablecimiento(Long idRedEstablecimiento) {
		this.idRedEstablecimiento = idRedEstablecimiento;
	}

	public void setIdRed(Long idRed) {
		this.idRed = idRed;
	}

	public void setIdEstablecimiento(Long idEstablecimiento) {
		this.idEstablecimiento = idEstablecimiento;
	}

	public void setIdPeriodo(Long idPeriodo) {
		this.idPeriodo = idPeriodo;
	}

	@Override
	public String toString() {
		return "RedEstablecimientoDTO [idRedEstablecimiento=" + idRedEstablecimiento + ", idRed=" + idRed
				+ ", idEstablecimiento=" + idEstablecimiento + ", idPeriodo=" + idPeriodo + "]";
	}

}
