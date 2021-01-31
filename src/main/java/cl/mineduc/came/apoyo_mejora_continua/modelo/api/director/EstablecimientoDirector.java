package cl.mineduc.came.apoyo_mejora_continua.modelo.api.director;

public class EstablecimientoDirector {
    private int rbd;
    private String dvRbd;
    private String nombre;
    private Director director;

    public int getRbd() {
        return rbd;
    }

    public void setRbd(int rbd) {
        this.rbd = rbd;
    }

    public String getDvRbd() {
        return dvRbd;
    }

    public void setDvRbd(String dvRbd) {
        this.dvRbd = dvRbd;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }    
}

class Director{
    private int rut;
    private String dv;
    private String nombres;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private DatosContacto datosContacto;

    public int getRut() {
        return rut;
    }

    public void setRut(int rut) {
        this.rut = rut;
    }

    public String getDv() {
        return dv;
    }

    public void setDv(String dv) {
        this.dv = dv;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
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

    public DatosContacto getDatosContacto() {
        return datosContacto;
    }

    public void setDatosContacto(DatosContacto datosContacto) {
        this.datosContacto = datosContacto;
    }    
}

class DatosContacto{
    private String codigoAreaTelefono;
    private String telefono;
    private String celular;
    private String mail;

    public String getCodigoAreaTelefono() {
        return codigoAreaTelefono;
    }

    public void setCodigoAreaTelefono(String codigoAreaTelefono) {
        this.codigoAreaTelefono = codigoAreaTelefono;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }    
}