package cl.mineduc.came.apoyo_mejora_continua.dto.users;

public class UserEnvDTO {
    private String idUsuario;
    private String userName;
    private String nombreCompleto;
    private String idPerfil;
    private String perfil;
    private String idRegion;
    private String idDeprov;
    private String deprov;
    private String idPerfilNivel;    
    private String nombrePerfilNivel;
    private Integer currentPeriod;

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getIdDeprov() {
        return idDeprov;
    }

    public void setIdDeprov(String idDeprov) {
        this.idDeprov = idDeprov;
    }

    public String getIdPerfilNivel() {
        return idPerfilNivel;
    }

    public void setIdPerfilNivel(String idPerfilNivel) {
        this.idPerfilNivel = idPerfilNivel;
    }

    public String getNombrePerfilNivel() {
        return nombrePerfilNivel;
    }

    public void setNombrePerfilNivel(String nombrePerfilNivel) {
        this.nombrePerfilNivel = nombrePerfilNivel;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public String getDeprov() {
        return deprov;
    }

    public void setDeprov(String deprov) {
        this.deprov = deprov;
    }

    public Integer getCurrentPeriod() {
        return currentPeriod;
    }

    public void setCurrentPeriod(Integer currentPeriod) {
        this.currentPeriod = currentPeriod;
    }
}
