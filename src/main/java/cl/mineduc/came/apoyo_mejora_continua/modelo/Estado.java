package cl.mineduc.came.apoyo_mejora_continua.modelo;

import java.io.Serializable;

public class Estado implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 905759571512975500L;

    private Long idEstado;

    private String nombre;

    public Long getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Long idEstado) {
        this.idEstado = idEstado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}