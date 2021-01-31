package cl.mineduc.came.apoyo_mejora_continua.modelo;

import java.io.Serializable;
import java.util.Date;


public class MensajePerfil implements Serializable{

    /**
	 *
	 */
    private static final long serialVersionUID = -4267058855132494130L;
    
    private Long idPerfilMensaje;
    
    private Long idPerfil;

    private Long idMensaje;

    private Long idPeriodo;

    private Long idRegion;

    private Long idDeprov;

    private Boolean leido;

    private Long idUsuarioRegistro;

    private Date fechaRegistro;

    private Mensaje mensaje;

    private Perfil perfil;

    

    public Long getIdPerfilMensaje() {
        return idPerfilMensaje;
    }

    public void setIdPerfilMensaje(Long idPerfilMensaje) {
        this.idPerfilMensaje = idPerfilMensaje;
    }    

    public Long getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(Long idPerfil) {
        this.idPerfil = idPerfil;
    }

    public Long getIdPeriodo() {
        return idPeriodo;
    }

    public void setIdPeriodo(Long idPeriodo) {
        this.idPeriodo = idPeriodo;
    }

    public Long getIdRegion() {
        return idRegion;
    }

    public void setIdRegion(Long idRegion) {
        this.idRegion = idRegion;
    }

    public Boolean getLeido() {
        return leido;
    }

    public void setLeido(Boolean leido) {
        this.leido = leido;
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

    public Mensaje getMensaje() {
        return mensaje;
    }

    public void setMensaje(Mensaje mensaje) {
        this.mensaje = mensaje;
    }

    public Long getIdMensaje() {
        return idMensaje;
    }

    public void setIdMensaje(Long idMensaje) {
        this.idMensaje = idMensaje;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    @Override
    public String toString() {
        return "MensajePerfil [fechaRegistro=" + fechaRegistro + ", idPerfilMensaje=" + idPerfilMensaje + ", idMensaje=" + idMensaje
                + ", idPerfil=" + idPerfil + ", idPeriodo=" + idPeriodo + ", idProvincia=" + idDeprov + ", idRegion="
                + idRegion + ", idUsuarioRegistro=" + idUsuarioRegistro + ", leido=" + leido + ", mensaje=" + mensaje
                + ", perfil=" + perfil + "]";
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public Long getIdDeprov() {
        return idDeprov;
    }

    public void setIdDeprov(Long idDeprov) {
        this.idDeprov = idDeprov;
    }
    
}
