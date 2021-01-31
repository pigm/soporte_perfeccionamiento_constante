package cl.mineduc.came.apoyo_mejora_continua.modelo;

import java.util.Date;

public class SesionMovimientosClaves {
    private Long idSesionMovimientosClaves;
    private Long idSesionFoco;
    private Long idFoco;
    private Long idMovimientosClaves;
    private Boolean completado;
    private Long usuarioRegistro;
    private Date fechaRegistro;

    public Long getIdSesionMovimientosClaves() {
        return idSesionMovimientosClaves;
    }

    public void setIdSesionMovimientosClaves(Long idSesionMovimientosClaves) {
        this.idSesionMovimientosClaves = idSesionMovimientosClaves;
    }

    public Long getIdSesionFoco() {
        return idSesionFoco;
    }

    public void setIdSesionFoco(Long idSesionFoco) {
        this.idSesionFoco = idSesionFoco;
    }

    public Long getIdFoco() {
        return idFoco;
    }

    public void setIdFoco(Long idFoco) {
        this.idFoco = idFoco;
    }

    public Long getIdMovimientosClaves() {
        return idMovimientosClaves;
    }

    public void setIdMovimientosClaves(Long idMovimientosClaves) {
        this.idMovimientosClaves = idMovimientosClaves;
    }

    public Boolean getCompletado() {
        return completado;
    }

    public void setCompletado(Boolean completado) {
        this.completado = completado;
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
}