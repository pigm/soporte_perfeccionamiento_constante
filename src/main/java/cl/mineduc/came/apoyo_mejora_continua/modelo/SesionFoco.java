package cl.mineduc.came.apoyo_mejora_continua.modelo;

import java.util.Date;
import java.util.List;

public class SesionFoco {
    private Long idSesionFoco;
    private Long idSesion;
    private Long idFoco;
    private Date startDate;
    private Date endDate;
    private Long usuarioRegistro;
    private Date fechaRegistro;
    private List<SesionMovimientosClaves> movimientosClaves;
    private List<SesionFocoAccionMejora> accionesMejora;

    public Long getIdSesionFoco() {
        return idSesionFoco;
    }

    public void setIdSesionFoco(Long idSesionFoco) {
        this.idSesionFoco = idSesionFoco;
    }

    public Long getIdSesion() {
        return idSesion;
    }

    public void setIdSesion(Long idSesion) {
        this.idSesion = idSesion;
    }

    public Long getIdFoco() {
        return idFoco;
    }

    public void setIdFoco(Long idFoco) {
        this.idFoco = idFoco;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Long getUsuarioRegistro() {
        return usuarioRegistro;
    }

    public void setUsuarioRegistro(Long usuarioRegistro) {
        this.usuarioRegistro = usuarioRegistro;
    }

    public List<SesionMovimientosClaves> getMovimientosClaves() {
        return movimientosClaves;
    }

    public void setMovimientosClaves(List<SesionMovimientosClaves> movimientosClaves) {
        this.movimientosClaves = movimientosClaves;
    }

    public List<SesionFocoAccionMejora> getAccionesMejora() {
        return accionesMejora;
    }

    public void setAccionesMejora(List<SesionFocoAccionMejora> accionesMejora) {
        this.accionesMejora = accionesMejora;
    }
}