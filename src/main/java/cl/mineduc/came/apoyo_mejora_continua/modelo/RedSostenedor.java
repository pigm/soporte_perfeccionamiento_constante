package cl.mineduc.came.apoyo_mejora_continua.modelo;

import java.util.Date;

public class RedSostenedor {
    private Long idRedSostenedor;    
    private Long idRed;
    private String rut;
    private String dv;
    private Long idUsuarioRegistro;
    private Date fechaRegistro;
    private Boolean status;

    public Long getIdRedSostenedor() {
        return idRedSostenedor;
    }

    public void setIdRedSostenedor(Long idRedSostenedor) {
        this.idRedSostenedor = idRedSostenedor;
    }

    public Long getIdRed() {
        return idRed;
    }

    public void setIdRed(Long idRed) {
        this.idRed = idRed;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getDv() {
        return dv;
    }

    public void setDv(String dv) {
        this.dv = dv;
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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
    
}