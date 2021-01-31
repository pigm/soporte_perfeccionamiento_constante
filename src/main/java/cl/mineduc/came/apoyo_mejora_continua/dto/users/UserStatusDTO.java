package cl.mineduc.came.apoyo_mejora_continua.dto.users;

import java.io.Serializable;

public class UserStatusDTO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -1826476781106190754L;
    
    private String idUser;

    private boolean status;

    
    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    @Override
    public String toString() {
        return "UserStatusDTO [idUser=" + idUser + ", status=" + status + "]";
    }   
    
}
