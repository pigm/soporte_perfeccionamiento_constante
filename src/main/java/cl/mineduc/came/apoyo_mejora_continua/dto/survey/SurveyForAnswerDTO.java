package cl.mineduc.came.apoyo_mejora_continua.dto.survey;

import java.util.List;

public class SurveyForAnswerDTO {
    private String idSurvey;
    private String nombre;
    private String fechaInicio;
    private String fechaFin;
    private Boolean permiteObservacion;
    private List<QuestionDTO> preguntas;

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

    public Boolean getPermiteObservacion() {
        return permiteObservacion;
    }

    public void setPermiteObservacion(Boolean permiteObservacion) {
        this.permiteObservacion = permiteObservacion;
    }

    public List<QuestionDTO> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(List<QuestionDTO> preguntas) {
        this.preguntas = preguntas;
    }    
}
