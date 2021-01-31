package cl.mineduc.came.apoyo_mejora_continua.dto.feedback;

import java.util.Date;

public class FeedbackListDTO {
    private String idFeedback;
    private String idSupervisor;
    private String supervisor;
    private String idUsuarioJefeTecnico;
    private String jefeTecnico;
    private String region;
    private String deprov;
    private String semestre;
    private Integer numero;
    private Date fechaPlanificada;
    private Date fechaRealizada;

    public String getIdSupervisor() {
        return idSupervisor;
    }

    public void setIdSupervisor(String idSupervisor) {
        this.idSupervisor = idSupervisor;
    }

    public String getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }

    public String getIdUsuarioJefeTecnico() {
        return idUsuarioJefeTecnico;
    }

    public void setIdUsuarioJefeTecnico(String idUsuarioJefeTecnico) {
        this.idUsuarioJefeTecnico = idUsuarioJefeTecnico;
    }

    public String getJefeTecnico() {
        return jefeTecnico;
    }

    public void setJefeTecnico(String jefeTecnico) {
        this.jefeTecnico = jefeTecnico;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getDeprov() {
        return deprov;
    }

    public void setDeprov(String deprov) {
        this.deprov = deprov;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Date getFechaPlanificada() {
        return fechaPlanificada;
    }

    public void setFechaPlanificada(Date fechaPlanificada) {
        this.fechaPlanificada = fechaPlanificada;
    }

    public Date getFechaRealizada() {
        return fechaRealizada;
    }

    public void setFechaRealizada(Date fechaRealizada) {
        this.fechaRealizada = fechaRealizada;
    }

    public String getIdFeedback() {
        return idFeedback;
    }

    public void setIdFeedback(String idFeedback) {
        this.idFeedback = idFeedback;
    }
}
