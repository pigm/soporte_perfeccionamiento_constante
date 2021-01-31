package cl.mineduc.came.apoyo_mejora_continua.dto.profile;

import java.io.Serializable;

public class ProfileAccesoDTO implements Serializable {   
    
    /**
     *
     */
    private static final long serialVersionUID = -5696632569276034319L;

    private String id;
    private String referred;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReferred() {
        return referred;
    }

    public void setReferred(String referred) {
        this.referred = referred;
    }

    @Override
    public String toString() {
        return "Selector [id=" + id + ", referred=" + referred + "]";
    }
}
