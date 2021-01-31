package cl.mineduc.came.apoyo_mejora_continua.dto.message;

import java.util.List;

public class MessageDTO {

    private String idPerfilMensaje;

    private String asunto;

    private String mensaje;

    private Long idUsuarioregistro;

    private String fechaRegistro;

    private Boolean leido;

    private List<String> idPerfil;

    private Long idPeridod;

    private List<String> idRegion;

    private List<String> idProvincia;

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

    public Long getIdUsuarioregistro() {
        return idUsuarioregistro;
    }

    public void setIdUsuarioregistro(Long idUsuarioregistro) {
        this.idUsuarioregistro = idUsuarioregistro;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Long getIdPeridod() {
        return idPeridod;
    }

    public void setIdPeridod(Long idPeridod) {
        this.idPeridod = idPeridod;
    }

    public List<String> getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(List<String> idPerfil) {
        this.idPerfil = idPerfil;
    }

    public List<String> getIdRegion() {
        return idRegion;
    }

    public void setIdRegion(List<String> idRegion) {
        this.idRegion = idRegion;
    }

    public List<String> getIdProvincia() {
        return idProvincia;
    }

    public void setIdProvincia(List<String> idProvincia) {
        this.idProvincia = idProvincia;
    }

    public String getIdPerfilMensaje() {
        return idPerfilMensaje;
    }

    public void setIdPerfilMensaje(String idPerfilMensaje) {
        this.idPerfilMensaje = idPerfilMensaje;
    }

    public Boolean getLeido() {
        return leido;
    }

    public void setLeido(Boolean leido) {
        this.leido = leido;
    }

}
