package cl.mineduc.came.apoyo_mejora_continua.dto.survey;

import java.util.List;

public class SurveyCompleteDetailDTO {
    private String idSurvey;
    private String nombre;
    private String fechaInicio;
    private String fechaFin;
    private Integer enviadas;
    private Integer respondidas;
    private List<String> perfiles;
    private List<AnswerDetailDTO> preguntas;
    private List<String> observaciones;

    public String getIdSurvey() {
        return idSurvey;
    }

    public void setIdSurvey(String idSurvey) {
        this.idSurvey = idSurvey;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Integer getEnviadas() {
        return enviadas;
    }

    public void setEnviadas(Integer enviadas) {
        this.enviadas = enviadas;
    }

    public Integer getRespondidas() {
        return respondidas;
    }

    public void setRespondidas(Integer respondidas) {
        this.respondidas = respondidas;
    }

    public List<String> getPerfiles() {
        return perfiles;
    }

    public void setPerfiles(List<String> perfiles) {
        this.perfiles = perfiles;
    }

    public List<AnswerDetailDTO> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(List<AnswerDetailDTO> preguntas) {
        this.preguntas = preguntas;
    }

    public List<String> getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(List<String> observaciones) {
        this.observaciones = observaciones;
    }
}
