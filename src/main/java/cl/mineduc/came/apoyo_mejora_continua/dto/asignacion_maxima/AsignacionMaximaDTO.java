package cl.mineduc.came.apoyo_mejora_continua.dto.asignacion_maxima;
import java.util.Date;

public class AsignacionMaximaDTO {

    private String idAsignacionMaxima;
    private Integer supervisores;
    private Integer foco;
    private Long idUsuarioRegistro;
    private Date fechaRegistro;
    
	public String getIdAsignacionMaxima() {
		return idAsignacionMaxima;
	}
	public void setIdAsignacionMaxima(String idAsignacionMaxima) {
		this.idAsignacionMaxima = idAsignacionMaxima;
	}
	public Integer getSupervisores() {
		return supervisores;
	}
	public void setSupervisores(Integer supervisores) {
		this.supervisores = supervisores;
	}
	public Integer getFoco() {
		return foco;
	}
	public void setFoco(Integer foco) {
		this.foco = foco;
	}
	public Long getIdUsuarioRegistro() {
		return idUsuarioRegistro;
	}
	public void setIdUsuarioRegistro(Long idUsuarioRegistro) {
		this.idUsuarioRegistro = idUsuarioRegistro;
	}
	public Date getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
    }
        
	@Override
	public String toString() {
		return "AsignacionMaximaDTO [fechaRegistro=" + fechaRegistro + ", foco=" + foco + ", idAsignacionMaxima="
				+ idAsignacionMaxima + ", idUsuarioRegistro=" + idUsuarioRegistro + ", supervisores=" + supervisores
				+ "]";
	}
}
