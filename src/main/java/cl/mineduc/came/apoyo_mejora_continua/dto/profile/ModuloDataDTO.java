package cl.mineduc.came.apoyo_mejora_continua.dto.profile;

import java.io.Serializable;
import java.util.List;

public class ModuloDataDTO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    
    private long idModulo;
    private long idEstado;
    private String nombre;
    private boolean status;

    private List<SubModuloDataDTO> subModulo;
   
    public long getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(long idModulo) {
        this.idModulo = idModulo;
    }

    public long getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(long idEstado) {
        this.idEstado = idEstado;
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

    public List<SubModuloDataDTO> getSubModulo() {
        return subModulo;
    }

    public void setSubModulo(List<SubModuloDataDTO> subModulo) {
        this.subModulo = subModulo;
    }


    @Override
    public String toString() {
        return "ModuloDataDTO [idEstado=" + idEstado + ", idModulo=" + idModulo + ", nombre=" + nombre + ", status="
                + status + "]";
    }

}