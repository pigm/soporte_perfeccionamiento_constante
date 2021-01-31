package cl.mineduc.came.apoyo_mejora_continua.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class PerfilUsuario implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5751146785562890639L;

	private Long id;
	
	private Long idUsuario;
	
	private Date startDate;
	
	private Date endDate;
	
	private boolean status;
	
	private List<Perfil> perfiles;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public List<Perfil> getPerfiles() {
		return perfiles;
	}

	public void setPerfiles(List<Perfil> perfiles) {
		this.perfiles = perfiles;
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
	
	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "PerfilUsuario [id=" + id + ", idUsuario=" + idUsuario + ", startDate=" + startDate + ", endDate="
				+ endDate + ", status=" + status + ", perfiles=" + perfiles + "]";
	}

}
