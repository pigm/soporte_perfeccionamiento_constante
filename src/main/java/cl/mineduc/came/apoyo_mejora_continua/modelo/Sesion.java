package cl.mineduc.came.apoyo_mejora_continua.modelo;

import java.util.Date;

public class Sesion {
    private Long idSesion; 
    private Long idAsesoria; 
    private Long idSessionEstado;
    private Long idSesionTipo; 
    private Date fechaPlanificacion; 
    private Date fechaRealizada;
    private Long usuarioRegistro; 
    private Date fechaRegistro;

    public Long getIdSesion() {
        return idSesion;
    }

    public void setIdSesion(Long idSesion) {
        this.idSesion = idSesion;
    }

    public Long getIdAsesoria() {
        return idAsesoria;
    }

    public void setIdAsesoria(Long idAsesoria) {
        this.idAsesoria = idAsesoria;
    }

    public Long getIdSessionEstado() {
        return idSessionEstado;
    }

    public void setIdSessionEstado(Long idSessionEstado) {
        this.idSessionEstado = idSessionEstado;
    }

    public Date getFechaPlanificacion() {
        return fechaPlanificacion;
    }

    public void setFechaPlanificacion(Date fechaPlanificacion) {
        this.fechaPlanificacion = fechaPlanificacion;
    }

    public Date getFechaRealizada() {
        return fechaRealizada;
    }

    public void setFechaRealizada(Date fechaRealizada) {
        this.fechaRealizada = fechaRealizada;
    }

    public Long getUsuarioRegistro() {
        return usuarioRegistro;
    }

    public void setUsuarioRegistro(Long usuarioRegistro) {
        this.usuarioRegistro = usuarioRegistro;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Long getIdSesionTipo() {
        return idSesionTipo;
    }

    public void setIdSesionTipo(Long idSesionTipo) {
        this.idSesionTipo = idSesionTipo;
    }

}