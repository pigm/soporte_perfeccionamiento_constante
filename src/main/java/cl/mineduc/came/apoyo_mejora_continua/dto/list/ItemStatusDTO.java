package cl.mineduc.came.apoyo_mejora_continua.dto.list;

import java.io.Serializable;

public class ItemStatusDTO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -6799547274834750534L;

    private String idElementoLista;

    private boolean status;

    public String getIdElementoLista() {
        return idElementoLista;
    }

    public void setIdElementoLista(String idElementoLista) {
        this.idElementoLista = idElementoLista;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ItemStatusDTO [idElementoLista=" + idElementoLista + ", status=" + status + "]";
    }   
    
}
