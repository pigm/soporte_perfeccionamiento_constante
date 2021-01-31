package cl.mineduc.came.apoyo_mejora_continua.dto.supervisor_suplencia;

public class SupervisorDataDTO {
    
    private String usuarioId;
    private String userName;
    private String nombre;
    private String perfil;
    private String region;
    private String deprov;

   
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getDeprov() {
        return deprov;
    }

    public void setDeprov(String deprov) {
        this.deprov = deprov;
    }

    @Override
    public String toString() {
        return "SupervisorDataDTO [deprov=" + deprov + ", nombre=" + nombre + ", perfil=" + perfil + ", region="
                + region + ", usuarioId=" + usuarioId + "]";
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
    }
   

}
