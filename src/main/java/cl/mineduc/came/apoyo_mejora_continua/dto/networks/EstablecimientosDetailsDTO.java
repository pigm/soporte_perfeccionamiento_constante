package cl.mineduc.came.apoyo_mejora_continua.dto.networks;

import cl.mineduc.came.apoyo_mejora_continua.dto.shared.SelectorDTO;

public class EstablecimientosDetailsDTO extends SelectorDTO {
    private Boolean status;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }    
}
