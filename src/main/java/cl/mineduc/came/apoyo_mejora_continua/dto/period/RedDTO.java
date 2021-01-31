package cl.mineduc.came.apoyo_mejora_continua.dto.period;

import java.io.Serializable;

public class RedDTO implements DTOEntity, Serializable {

	/**
	* 
	*/
	private static final long serialVersionUID = 8118883427451702203L;

	private Long idRed;

	private Long idRedTipo;

	private Long idPeriodo;

	private Long idDeprov;

	private Long idUsuario;

	public Long getIdRed() {
		return idRed;
	}

	public Long getIdRedTipo() {
		return idRedTipo;
	}

	public Long getIdPeriodo() {
		return idPeriodo;
	}

	public Long getIdDeprov() {
		return idDeprov;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdRed(Long idRed) {
		this.idRed = idRed;
	}

	public void setIdRedTipo(Long idRedTipo) {
		this.idRedTipo = idRedTipo;
	}

	public void setIdPeriodo(Long idPeriodo) {
		this.idPeriodo = idPeriodo;
	}

	public void setIdDeprov(Long idDeprov) {
		this.idDeprov = idDeprov;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	@Override
	public String toString() {
		return "RedDTO [idRed=" + idRed + ", idRedTipo=" + idRedTipo + ", idPeriodo=" + idPeriodo + ", idDeprov="
				+ idDeprov + ", idUsuario=" + idUsuario + "]";
	}

}
