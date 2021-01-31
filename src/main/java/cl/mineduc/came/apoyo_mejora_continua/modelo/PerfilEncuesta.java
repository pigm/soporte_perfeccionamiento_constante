package cl.mineduc.came.apoyo_mejora_continua.modelo;

import java.util.Date;

public class PerfilEncuesta {
    private Long idPerfilEncuesta;
    private Long idPerfil;
    private Long idEncuesta;
    private Boolean estado;
    private Date fechaRegistro;
    private Long idUsuarioRegistro;

    public Long getIdPerfilEncuesta() {
        return idPerfilEncuesta;
    }

    public void setIdPerfilEncuesta(Long idPerfilEncuesta) {
        this.idPerfilEncuesta = idPerfilEncuesta;
    }

    public Long getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(Long idPerfil) {
        this.idPerfil = idPerfil;
    }

    public Long getIdEncuesta() {
        return idEncuesta;
    }

    public void setIdEncuesta(Long idEncuesta) {
        this.idEncuesta = idEncuesta;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Long getIdUsuarioRegistro() {
        return idUsuarioRegistro;
    }

    public void setIdUsuarioRegistro(Long idUsuarioRegistro) {
        this.idUsuarioRegistro = idUsuarioRegistro;
    }    
}
