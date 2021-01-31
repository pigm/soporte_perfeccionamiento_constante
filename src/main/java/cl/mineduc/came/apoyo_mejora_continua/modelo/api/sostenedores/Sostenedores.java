package cl.mineduc.came.apoyo_mejora_continua.modelo.api.sostenedores;

import java.util.List;

import cl.mineduc.came.apoyo_mejora_continua.modelo.api.Establecimientos;

public class Sostenedores {

    private String id;
    private String rut;
    private String dv;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private CalidadJuridica calidadJuridica;
    private TipoPersona tipoPersona;
    private DatosContacto datosContacto;
    private List<Establecimientos> establecimientos = null;
    private Dependencia dependencia;
    private List<RepresentantesLegales> representantesLegales = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getDv() {
        return dv;
    }

    public void setDv(String dv) {
        this.dv = dv;
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

    public CalidadJuridica getCalidadJuridica() {
        return calidadJuridica;
    }

    public void setCalidadJuridica(CalidadJuridica calidadJuridica) {
        this.calidadJuridica = calidadJuridica;
    }

    public TipoPersona getTipoPersona() {
        return tipoPersona;
    }

    public void setTipoPersona(TipoPersona tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    public DatosContacto getDatosContacto() {
        return datosContacto;
    }

    public void setDatosContacto(DatosContacto datosContacto) {
        this.datosContacto = datosContacto;
    }

    public List<Establecimientos> getEstablecimientos() {
        return establecimientos;
    }

    public void setEstablecimientos(List<Establecimientos> establecimientos) {
        this.establecimientos = establecimientos;
    }

    public Dependencia getDependencia() {
        return dependencia;
    }

    public void setDependencia(Dependencia dependencia) {
        this.dependencia = dependencia;
    }

    public List<RepresentantesLegales> getRepresentantesLegales() {
        return representantesLegales;
    }

    public void setRepresentantesLegales(List<RepresentantesLegales> representantesLegales) {
        this.representantesLegales = representantesLegales;
    }

}
