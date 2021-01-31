package cl.mineduc.came.apoyo_mejora_continua.dto.planning;

import java.util.List;

public class SesionInfoDetailDTO {
    private String idAsesoria;
    private List<FocoDTO> focos;
    
    public List<FocoDTO> getFocos() {
        return focos;
    }

    public void setFocos(List<FocoDTO> focos) {
        this.focos = focos;
    }

    public String getIdAsesoria() {
        return idAsesoria;
    }

    public void setIdAsesoria(String idAsesoria) {
        this.idAsesoria = idAsesoria;
    }
    
}