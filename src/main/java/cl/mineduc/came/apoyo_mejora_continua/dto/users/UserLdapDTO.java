package cl.mineduc.came.apoyo_mejora_continua.dto.users;

import java.io.Serializable;

public class UserLdapDTO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 3763169488763603593L;
        
    private String userName;
    private String rut;    
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String email;    
  
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

    

}
