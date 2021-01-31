package cl.mineduc.came.apoyo_mejora_continua.modelo;

import java.io.Serializable;
import java.util.Date;

public class AsignacionMaxima implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -1018604248827026598L;
    
    private Long idAsignacionMaxima;
    private Integer supervisores;
    private Integer foco;
    private Long idUsuarioRegistro;
    private Date fechaRegistro;

    public Long getIdAsignacionMaxima() {
        return idAsignacionMaxima;
    }

    public void setIdAsignacionMaxima(Long idAsignacionMaxima) {
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
        return "AsignacionMaxima [fechaRegistro=" + fechaRegistro + ", foco=" + foco + ", idAsignacionMaxima="
                + idAsignacionMaxima + ", idUsuarioRegistro=" + idUsuarioRegistro + ", supervisores=" + supervisores
                + "]";
    }


}
