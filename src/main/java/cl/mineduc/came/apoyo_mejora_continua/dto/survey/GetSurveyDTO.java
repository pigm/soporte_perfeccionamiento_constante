package cl.mineduc.came.apoyo_mejora_continua.dto.survey;

public class GetSurveyDTO {
    private String idPerfil;
    // private Integer idRegion;
    // private Integer idDeprov;
    private Boolean estado;

    public String getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(String idPerfil) {
        this.idPerfil = idPerfil;
    }

    // public Integer getIdRegion() {
    //     return idRegion;
    // }

    // public void setIdRegion(Integer idRegion) {
    //     this.idRegion = idRegion;
    // }

    // public Integer getIdDeprov() {
    //     return idDeprov;
    // }

    // public void setIdDeprov(Integer idDeprov) {
    //     this.idDeprov = idDeprov;
    // }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }    
}
