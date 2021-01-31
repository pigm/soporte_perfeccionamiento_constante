package cl.mineduc.came.apoyo_mejora_continua.dto.message;

import java.util.Date;

import cl.mineduc.came.apoyo_mejora_continua.dto.profile.ProfileDTO;

public class MessageProfileDTO {

    private String idPerfilMensaje;

    private String idMensaje;

    private String idUsuarioregistro;

    private String idPerfil;

    private ProfileDTO perfil;

    private String idRegion;

    private String nombreProvincia;

    private String idProvincia;

    private String nombreRegion;

    private Date fechaLeido;

    private Date fechaCreacion;

    private String asunto;

    private Boolean leido;

    private String contenidoMensaje;

    public String getIdUsuarioregistro() {
        return idUsuarioregistro;
    }

    public void setIdUsuarioregistro(String idUsuarioregistro) {
        this.idUsuarioregistro = idUsuarioregistro;
    }

    public ProfileDTO getPerfil() {
        return perfil;
    }

    public void setPerfil(ProfileDTO perfil) {
        this.perfil = perfil;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getNombreRegion() {
        return nombreRegion;
    }

    public void setNombreRegion(String nombreRegion) {
        this.nombreRegion = nombreRegion;
    }

    public String getIdMensaje() {
        return idMensaje;
    }

    public void setIdMensaje(String idMensaje) {
        this.idMensaje = idMensaje;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaLeido() {
        return fechaLeido;
    }

    public void setFechaLeido(Date fechaLeido) {
        this.fechaLeido = fechaLeido;
    }

    public Boolean getLeido() {
        return leido;
    }

    public void setLeido(Boolean leido) {
        this.leido = leido;
    }

    public String getContenidoMensaje() {
        return contenidoMensaje;
    }

    public void setContenidoMensaje(String contenidoMensaje) {
        this.contenidoMensaje = contenidoMensaje;
    }

	public String getIdPerfil() {
		return idPerfil;
	}

	public void setIdPerfil(String idPerfil) {
		this.idPerfil = idPerfil;
	}

    public String getIdRegion() {
        return idRegion;
    }

    public void setIdRegion(String idRegion) {
        this.idRegion = idRegion;
    }

    public String getIdPerfilMensaje() {
        return idPerfilMensaje;
    }

    public void setIdPerfilMensaje(String idPerfilMensaje) {
        this.idPerfilMensaje = idPerfilMensaje;
    }

    public String getNombreProvincia() {
        return nombreProvincia;
    }

    public void setNombreProvincia(String nombreProvincia) {
        this.nombreProvincia = nombreProvincia;
    }

    public String getIdProvincia() {
        return idProvincia;
    }

    public void setIdProvincia(String idProvincia) {
        this.idProvincia = idProvincia;
    }

    

}
