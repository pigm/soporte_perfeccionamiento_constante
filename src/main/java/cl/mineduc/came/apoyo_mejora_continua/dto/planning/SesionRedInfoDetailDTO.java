package cl.mineduc.came.apoyo_mejora_continua.dto.planning;

import java.util.List;

public class SesionRedInfoDetailDTO {
    private String idAsesoria;
    private List<FocoRedDTO> focos;

    public String getIdAsesoria() {
        return idAsesoria;
    }

    public void setIdAsesoria(String idAsesoria) {
        this.idAsesoria = idAsesoria;
    }

    public List<FocoRedDTO> getFocos() {
        return focos;
    }

    public void setFocos(List<FocoRedDTO> focos) {
        this.focos = focos;
    }    
}
