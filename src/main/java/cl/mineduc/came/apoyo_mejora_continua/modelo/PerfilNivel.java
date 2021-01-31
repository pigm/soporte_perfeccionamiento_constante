package cl.mineduc.came.apoyo_mejora_continua.modelo;

import java.io.Serializable;

public class PerfilNivel implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Long idPerfilNivel;

    private String nombre;

    public Long getIdPerfilNivel() {
        return idPerfilNivel;
    }

    public void setIdPerfilNivel(Long idPerfilNivel) {
        this.idPerfilNivel = idPerfilNivel;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "PerfilNivel [idPerfilNivel=" + idPerfilNivel + ", nombre=" + nombre + "]";
    }

    
}