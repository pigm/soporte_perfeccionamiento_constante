package cl.mineduc.came.apoyo_mejora_continua.modelo;

import java.io.Serializable;
import java.util.Date;

public class Mensaje implements Serializable{

    /**
     *
     */
    private static final long serialVersionUID = -4908287512228185022L;

    private Long id;

    private Long idUsuarioEnvia;

    private String asunto;

    private String mensaje;
    
    private Long idUsuarioRegistro;

    private Date fechaRegistro;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Long getIdUsuarioEnvia() {
        return idUsuarioEnvia;
    }

    public void setIdUsuarioEnvia(Long idUsuarioEnvia) {
        this.idUsuarioEnvia = idUsuarioEnvia;
    }

    public Long getIdUsuarioRegistro() {
        return idUsuarioRegistro;
    }

    public void setIdUsuarioRegistro(Long idUsuarioRegistro) {
        this.idUsuarioRegistro = idUsuarioRegistro;
    }

    @Override
    public String toString() {
        return "Mensaje [asunto=" + asunto + ", fechaRegistro=" + fechaRegistro + ", id=" + id + ", idUsuarioEnvia="
                + idUsuarioEnvia + ", idUsuarioRegistro=" + idUsuarioRegistro + ", mensaje=" + mensaje + "]";
    }
    
}
