package cl.mineduc.came.apoyo_mejora_continua.dto.users;

public class UserDataDTO {
    
    private String idUsuario;
    private String username;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String nombre;
    private String rut;
    private String region;
    private String deprov;
    private String perfil;
    private String lastConnection;
    private Boolean habilitar;

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public String getLastConnection() {
        return lastConnection;
    }

    public void setLastConnection(String lastConnection) {
        this.lastConnection = lastConnection;
    }

    public Boolean getHabilitar() {
        return habilitar;
    }

    public void setHabilitar(Boolean habilitar) {
        this.habilitar = habilitar;
    }   

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDeprov() {
        return deprov;
    }

    public void setDeprov(String deprovp) {
        deprov = deprovp;
    }

    @Override
    public String toString() {
        return "UserDataDTO [deprov=" + deprov + ", apellidoMaterno=" + apellidoMaterno + ", apellidoPaterno="
                + apellidoPaterno + ", habilitar=" + habilitar + ", idUsuario=" + idUsuario + ", lastConnection="
                + lastConnection + ", nombre=" + nombre + ", perfil=" + perfil + ", region=" + region + ", rut=" + rut
                + ", username=" + username + "]";
    }

}
