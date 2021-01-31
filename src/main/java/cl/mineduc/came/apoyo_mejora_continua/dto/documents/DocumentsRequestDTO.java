package cl.mineduc.came.apoyo_mejora_continua.dto.documents;

public class DocumentsRequestDTO {
    private int anio;
    private Integer regionId;
    private Integer deprovId;
    private Boolean estado;

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    public Integer getDeprovId() {
        return deprovId;
    }

    public void setDeprovId(Integer deprovId) {
        this.deprovId = deprovId;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    
    
}
