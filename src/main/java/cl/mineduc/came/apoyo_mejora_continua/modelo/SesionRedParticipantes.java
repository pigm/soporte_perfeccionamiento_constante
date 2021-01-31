package cl.mineduc.came.apoyo_mejora_continua.modelo;

import java.util.Date;

public class SesionRedParticipantes {
    private Long idSesionRedParticipantes;
    private Long  idSesion;
    private String tipo;
    private Integer rbd; 
    private String rutSostenedor;
    private Boolean presente; 
    private Long idUsuarioRegistro; 
    private Date fechaRegistro;

    public Long getIdSesionRedParticipantes() {
        return idSesionRedParticipantes;
    }

    public void setIdSesionRedParticipantes(Long idSesionRedParticipantes) {
        this.idSesionRedParticipantes = idSesionRedParticipantes;
    }

    public Long getIdSesion() {
        return idSesion;
    }

    public void setIdSesion(Long idSesion) {
        this.idSesion = idSesion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getRbd() {
        return rbd;
    }

    public void setRbd(Integer rbd) {
        this.rbd = rbd;
    }

    public String getRutSostenedor() {
        return rutSostenedor;
    }

    public void setRutSostenedor(String rutSostenedor) {
        this.rutSostenedor = rutSostenedor;
    }

    public Boolean getPresente() {
        return presente;
    }

    public void setPresente(Boolean presente) {
        this.presente = presente;
    }

    public Long getIdUsuarioRegistro() {
        return idUsuarioRegistro;
    }

    public void setIdUsuarioRegistro(Long idUsuarioRegistro) {
        this.idUsuarioRegistro = idUsuarioRegistro;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
    
}
