package cl.mineduc.came.apoyo_mejora_continua.dto.profile;

import java.io.Serializable;

public class ProfileStatusDTO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -1826476781106190754L;
    
    private String idProfile;

    private boolean status;

    public String getIdProfile() {
        return idProfile;
    }

    public void setIdProfile(String idProfile) {
        this.idProfile = idProfile;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ProfileStatusDTO [idProfile=" + idProfile + ", status=" + status + "]";
    }   
    
}
