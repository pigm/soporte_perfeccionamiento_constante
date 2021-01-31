package cl.mineduc.came.apoyo_mejora_continua.dto.feedback;

import java.util.List;

public class FeedbackDetailDTO {
    private String idFeedback;
    private Integer numero;
    private String supervisor;
    private String supervisorNombre;
    private String region;
    private String deprov;
    private String semestre;
    private String fechaRegistroSesion;
    private String idMomentoAsesoria;
    private String fechaRegistroJefeTecnico;
    private String practicaAbordada;
    private String acuerdos;
    private String acciones;
    private String observaciones;
    private String fechaProxima;
    private List<FeedbackBeforeDTO> feedbackBefore;

    public String getIdFeedback() {
        return idFeedback;
    }

    public void setIdFeedback(String idFeedback) {
        this.idFeedback = idFeedback;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }

    public String getSupervisorNombre() {
        return supervisorNombre;
    }

    public void setSupervisorNombre(String supervisorNombre) {
        this.supervisorNombre = supervisorNombre;
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

    public String getIdMomentoAsesoria() {
        return idMomentoAsesoria;
    }

    public void setIdMomentoAsesoria(String idMomentoAsesoria) {
        this.idMomentoAsesoria = idMomentoAsesoria;
    }

    public String getFechaRegistroJefeTecnico() {
        return fechaRegistroJefeTecnico;
    }

    public void setFechaRegistroJefeTecnico(String fechaRegistroJefeTecnico) {
        this.fechaRegistroJefeTecnico = fechaRegistroJefeTecnico;
    }

    public String getPracticaAbordada() {
        return practicaAbordada;
    }

    public void setPracticaAbordada(String practicaAbordada) {
        this.practicaAbordada = practicaAbordada;
    }

    public String getAcuerdos() {
        return acuerdos;
    }

    public void setAcuerdos(String acuerdos) {
        this.acuerdos = acuerdos;
    }

    public String getAcciones() {
        return acciones;
    }

    public void setAcciones(String acciones) {
        this.acciones = acciones;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getFechaProxima() {
        return fechaProxima;
    }

    public void setFechaProxima(String fechaProxima) {
        this.fechaProxima = fechaProxima;
    }

    public String getFechaRegistroSesion() {
        return fechaRegistroSesion;
    }

    public void setFechaRegistroSesion(String fechaRegistroSesion) {
        this.fechaRegistroSesion = fechaRegistroSesion;
    }

    public List<FeedbackBeforeDTO> getFeedbackBefore() {
        return feedbackBefore;
    }

    public void setFeedbackBefore(List<FeedbackBeforeDTO> feedbackBefore) {
        this.feedbackBefore = feedbackBefore;
    }
}
