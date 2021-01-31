package cl.mineduc.came.apoyo_mejora_continua.dto.profile;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotEmpty;

public class ProfileDTO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String idPerfil;    
    @NotEmpty
    private String nombre;
    @NotEmpty
    private String descripcion;
    @NotEmpty
    private String idNivel;    

    private boolean habilitado;
    
    private List<ProfileAccesoDTO> accesos;

    public String getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(String idPerfil) {
        this.idPerfil = idPerfil;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getIdNivel() {
        return idNivel;
    }

    public void setIdNivel(String idNivel) {
        this.idNivel = idNivel;
    }

    public boolean isHabilitado() {
        return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }

    public List<ProfileAccesoDTO> getAccesos() {
        return accesos;
    }

    public void setAccesos(List<ProfileAccesoDTO> accesos) {
        this.accesos = accesos;
    }

    @Override
    public String toString() {
        return "ProfileDTO [accesos=" + accesos + ", descripcion=" + descripcion + ", idPerfil=" + idPerfil + ", nivel="
                + idNivel + ", nombre=" + nombre + "]";
    }   
    
}