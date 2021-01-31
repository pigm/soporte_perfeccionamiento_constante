package cl.mineduc.came.apoyo_mejora_continua.dto.survey;

import java.util.List;

public class SurveyDataDTO {
    private String idSurvey;
    private String nombre;
    private String fechaInicio;
    private String fechaFin;
    private Boolean permiteObservacion;
    private List<String> preguntas;
    private List<String> perfiles;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getPermiteObservacion() {
        return permiteObservacion;
    }

    public void setPermiteObservacion(Boolean permiteObservacion) {
        this.permiteObservacion = permiteObservacion;
    }

    public List<String> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(List<String> preguntas) {
        this.preguntas = preguntas;
    }

    public List<String> getPerfiles() {
        return perfiles;
    }

    public void setPerfiles(List<String> perfiles) {
        this.perfiles = perfiles;
    }

    public String getIdSurvey() {
        return idSurvey;
    }

    public void setIdSurvey(String idSurvey) {
        this.idSurvey = idSurvey;
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
}
