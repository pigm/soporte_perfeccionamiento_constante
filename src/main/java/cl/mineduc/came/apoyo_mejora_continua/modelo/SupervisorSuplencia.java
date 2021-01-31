package cl.mineduc.came.apoyo_mejora_continua.modelo;

import java.io.Serializable;
import java.util.Date;

public class SupervisorSuplencia implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Long idSupervisorSuplencia;
    private Long idSupervisorAusente;
    private Long idSupervisorSuplente;
    private Date startDate;
    private Date endDate;
    private Long idUsuarioRegistro;
    private Date fechaRegistro;
    private Boolean status;
    private Long idPeriodo;

    private Supervisor supervisorAusente;
    private Supervisor supervisorSuplente;


    public Long getIdSupervisorSuplencia() {
        return idSupervisorSuplencia;
    }

    public void setIdSupervisorSuplencia(Long idSupervisorSuplencia) {
        this.idSupervisorSuplencia = idSupervisorSuplencia;
    }

    public Long getIdSupervisorAusente() {
        return idSupervisorAusente;
    }

    public void setIdSupervisorAusente(Long idSupervisorAusente) {
        this.idSupervisorAusente = idSupervisorAusente;
    }

    public Long getIdSupervisorSuplente() {
        return idSupervisorSuplente;
    }

    public void setIdSupervisorSuplente(Long idSupervisorSuplente) {
        this.idSupervisorSuplente = idSupervisorSuplente;
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
        return "SupervisorSuplencia [endDate=" + endDate + ", fechaRegistro=" + fechaRegistro + ", idSupervisorAusente="
                + idSupervisorAusente + ", idSupervisorSuplencia=" + idSupervisorSuplencia + ", idSupervisorSuplente="
                + idSupervisorSuplente + ", idUsuarioRegistro=" + idUsuarioRegistro + ", startDate=" + startDate + "]";
    }

    public Supervisor getSupervisorAusente() {
        return supervisorAusente;
    }

    public void setSupervisorAusente(Supervisor supervisorAusente) {
        this.supervisorAusente = supervisorAusente;
    }

    public Supervisor getSupervisorSuplente() {
        return supervisorSuplente;
    }

    public void setSupervisorSuplente(Supervisor supervisorSuplente) {
        this.supervisorSuplente = supervisorSuplente;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public Long getIdPeriodo() {
        return idPeriodo;
    }

    public void setIdPeriodo(Long idPeriodo) {
        this.idPeriodo = idPeriodo;
    }
    
}
