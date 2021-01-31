package cl.mineduc.came.apoyo_mejora_continua.dto.profile;

import java.io.Serializable;

public class SubModuloDataDTO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private long idSubModulo;
    private long idEstado;
    private long idModulo;
    private String nombre;
    private boolean status;
   
    public long getIdSubModulo() {
        return idSubModulo;
    }

    public void setIdSubModulo(long idSubModulo) {
        this.idSubModulo = idSubModulo;
    }

    public long getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(long idEstado) {
        this.idEstado = idEstado;
    }

    public long getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(long idModulo) {
        this.idModulo = idModulo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    

}