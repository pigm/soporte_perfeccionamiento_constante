package cl.mineduc.came.apoyo_mejora_continua.modelo;

import java.io.Serializable;
import java.util.Date;

public class PerfilMenu implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3850828210599062L;

	private Long idPerfilMenu; 
	
	private Long idPerfil;
	
	private Long idSubModulo;
	
	private Long idPerfilMenuAcceso;
	
	private boolean status;
	
	private Date startDate;
	
	private Date endDate;

	public Long getIdPerfilMenu() {
		return idPerfilMenu;
	}

	public void setIdPerfilMenu(Long idPerfilMenu) {
		this.idPerfilMenu = idPerfilMenu;
	}

	public Long getIdPerfil() {
		return idPerfil;
	}

	public void setIdPerfil(Long idPerfil) {
		this.idPerfil = idPerfil;
	}

	public Long getIdSubModulo() {
		return idSubModulo;
	}

	public void setIdSubModulo(Long idSubModulo) {
		this.idSubModulo = idSubModulo;
	}

	public Long getIdPerfilMenuAcceso() {
		return idPerfilMenuAcceso;
	}

	public void setIdPerfilMenuAcceso(Long idPerfilMenuAcceso) {
		this.idPerfilMenuAcceso = idPerfilMenuAcceso;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Override
	public String toString() {
		return "PerfilMenu [id=" + idPerfilMenu + ", idPerfil=" + idPerfil + ", idSubModulo=" + idSubModulo
				+ ", idPerfilMenuAcceso=" + idPerfilMenuAcceso + ", status=" + status + ", startDate=" + startDate
				+ ", endDate=" + endDate + "]";
	}	
	
}