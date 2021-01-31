package cl.mineduc.came.apoyo_mejora_continua.dto.feedback;

public class PlanificarFeedbackDTO {
    private String supervisor;
    private String frecuenciaId;
    private Integer semestre;
    private String fechaPlanificacion;

    public String getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }

    public String getFrecuenciaId() {
        return frecuenciaId;
    }

    public void setFrecuenciaId(String frecuenciaId) {
        this.frecuenciaId = frecuenciaId;
    }

    public String getFechaPlanificacion() {
        return fechaPlanificacion;
    }

    public void setFechaPlanificacion(String fechaPlanificacion) {
        this.fechaPlanificacion = fechaPlanificacion;
    }

    public Integer getSemestre() {
        return semestre;
    }

    public void setSemestre(Integer semestre) {
        this.semestre = semestre;
    }
}
