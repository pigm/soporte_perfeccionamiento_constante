package cl.mineduc.came.apoyo_mejora_continua.modelo;

import java.util.Date;

public class RedEstablecimiento {
    private Long idRedEstablecimiento;
    private Long idRed;
    private Integer rbd; 
	private Long idUsuarioRegistro; 
    private Date fechaRegistro;
    private Boolean status;

    public Long getIdRedEstablecimiento() {
        return idRedEstablecimiento;
    }

    public void setIdRedEstablecimiento(Long idRedEstablecimiento) {
        this.idRedEstablecimiento = idRedEstablecimiento;
    }

    public Long getIdRed() {
        return idRed;
    }

    public void setIdRed(Long idRed) {
        this.idRed = idRed;
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

    public Integer getRbd() {
        return rbd;
    }

    public void setRbd(Integer rbd) {
        this.rbd = rbd;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}