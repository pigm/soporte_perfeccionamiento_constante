package cl.mineduc.came.apoyo_mejora_continua.dto.users;

import java.io.Serializable;

public class UserDTO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 3763169488763603593L;
    
    private String idUsuario;
    private String userName;
    private String rut;    
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String email;
    private String idPerfil;
    private String idRegion;
    private String idDeprov;
    private boolean habilitado;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public boolean isHabilitado() {
        return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }

    public String getIdDeprov() {
        return idDeprov;
    }

    public void setIdDeprov(String idDeprov) {
        this.idDeprov = idDeprov;
    }

    @Override
    public String toString() {
        return "UserDTO [apellidoMaterno=" + apellidoMaterno + ", apellidoPaterno=" + apellidoPaterno + ", email="
                + email + ", habilitado=" + habilitado + ", idDeprov=" + idDeprov + ", idPerfil=" + idPerfil
                + ", idRegion=" + idRegion + ", idUsuario=" + idUsuario + ", nombre=" + nombre + ", rut=" + rut
                + ", userName=" + userName + "]";
    }

}
