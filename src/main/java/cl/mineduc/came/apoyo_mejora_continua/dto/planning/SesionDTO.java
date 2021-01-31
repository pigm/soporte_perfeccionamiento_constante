package cl.mineduc.came.apoyo_mejora_continua.dto.planning;

import java.util.Date;

public class SesionDTO {
    private String idSesion;    
    private Integer numero;
    private Date fechaPlanificacion;
    private Date fechaRalizada;
    private String estado;
    
    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Date getFechaPlanificacion() {
        return fechaPlanificacion;
    }

    public void setFechaPlanificacion(Date fechaPlanificacion) {
        this.fechaPlanificacion = fechaPlanificacion;
    }

    public Date getFechaRalizada() {
        return fechaRalizada;
    }

    public void setFechaRalizada(Date fechaRalizada) {
        this.fechaRalizada = fechaRalizada;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getIdSesion() {
        return idSesion;
    }

    public void setIdSesion(String idSesion) {
        this.idSesion = idSesion;
    }
    
}
