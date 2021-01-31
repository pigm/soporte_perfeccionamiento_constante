package cl.mineduc.came.apoyo_mejora_continua.dto.users;

import cl.mineduc.came.apoyo_mejora_continua.dto.shared.SelectorDTO;

public class ProfileSelectorDTO extends SelectorDTO {

    /**
     *
     */
    private static final long serialVersionUID = -194393219833709099L;
    
    private String nivel;

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }
}
