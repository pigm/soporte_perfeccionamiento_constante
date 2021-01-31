package cl.mineduc.came.apoyo_mejora_continua.dto.feedback;

public class FeedbackRequestDTO {
    private String jefeTecnico;
    private Integer idRegion;
    private Integer idDeprov;
    private Integer semestre;

    public String getJefeTecnico() {
        return jefeTecnico;
    }

    public void setJefeTecnico(String jefeTecnico) {
        this.jefeTecnico = jefeTecnico;
    }

    public Integer getIdRegion() {
        return idRegion;
    }

    public void setIdRegion(Integer idRegion) {
        this.idRegion = idRegion;
    }

    public Integer getIdDeprov() {
        return idDeprov;
    }

    public void setIdDeprov(Integer idDeprov) {
        this.idDeprov = idDeprov;
    }

    public Integer getSemestre() {
        return semestre;
    }

    public void setSemestre(Integer semestre) {
        this.semestre = semestre;
    }    
}
