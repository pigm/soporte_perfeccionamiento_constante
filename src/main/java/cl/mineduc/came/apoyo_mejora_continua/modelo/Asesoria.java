package cl.mineduc.came.apoyo_mejora_continua.modelo;

import java.util.Date;

public class Asesoria {
    private Long idAsesoria;
    private Long idPeriodo;
    private Long idRed;
    private Long idAsesoriaDirecta;
    private Long idAsignacionTipo;
    private Date startDate;
    private Date endDate;
    private Long usuarioRegistro;
    private Date fechaRegistro;

    public Long getIdAsesoria() {
        return idAsesoria;
    }

    public void setIdAsesoria(Long idAsesoria) {
        this.idAsesoria = idAsesoria;
    }

    public Long getIdPeriodo() {
        return idPeriodo;
    }

    public void setIdPeriodo(Long idPeriodo) {
        this.idPeriodo = idPeriodo;
    }

    public Long getIdRed() {
        return idRed;
    }

    public void setIdRed(Long idRed) {
        this.idRed = idRed;
    }

    public Long getIdAsesoriaDirecta() {
        return idAsesoriaDirecta;
    }

    public void setIdAsesoriaDirecta(Long idAsesoriaDirecta) {
        this.idAsesoriaDirecta = idAsesoriaDirecta;
    }

    public Long getIdAsignacionTipo() {
        return idAsignacionTipo;
    }

    public void setIdAsignacionTipo(Long idAsignacionTipo) {
        this.idAsignacionTipo = idAsignacionTipo;
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

    public Long getUsuarioRegistro() {
        return usuarioRegistro;
    }

    public void setUsuarioRegistro(Long usuarioRegistro) {
        this.usuarioRegistro = usuarioRegistro;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }    
}