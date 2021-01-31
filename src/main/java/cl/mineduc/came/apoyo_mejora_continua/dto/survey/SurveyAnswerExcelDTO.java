package cl.mineduc.came.apoyo_mejora_continua.dto.survey;

import java.util.List;

public class SurveyAnswerExcelDTO {
    private String nombre;
    private String perfiles;
    private String fechaInicio;
    private String fechaFin;
    private String usuario;
    private String comentario;
    private List<Integer> respuestas;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPerfiles() {
        return perfiles;
    }

    public void setPerfiles(String perfiles) {
        this.perfiles = perfiles;
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

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public List<Integer> getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(List<Integer> respuestas) {
        this.respuestas = respuestas;
    }    
}
