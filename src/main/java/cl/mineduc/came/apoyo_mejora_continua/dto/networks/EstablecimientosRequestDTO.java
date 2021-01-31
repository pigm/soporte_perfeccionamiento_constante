package cl.mineduc.came.apoyo_mejora_continua.dto.networks;

public class EstablecimientosRequestDTO {
    private Integer idRegion;
    private Integer idDeprov;
    private Integer idComuna;
    private String idTipoRed; 

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

    public Integer getIdComuna() {
        return idComuna;
    }

    public void setIdComuna(Integer idComuna) {
        this.idComuna = idComuna;
    }

    public String getIdTipoRed() {
        return idTipoRed;
    }

    public void setIdTipoRed(String idTipoRed) {
        this.idTipoRed = idTipoRed;
    }    
}
