package cl.mineduc.came.apoyo_mejora_continua.dto.establishment;

import cl.mineduc.came.apoyo_mejora_continua.modelo.EstablecimientoOrdenacion;

public class EstablecimientosDTO {
    
    private String rbd;    
    private String nombre;
    private String region;
    private String deprov;
    private String comuna;
    private String ruralidad;
    private String categorizacion;
    private String clasificacionSEP;
    private String dependencia;
    private String estado;
   

    public String getRbd() {
        return rbd;
    }

    public void setRbd(String rbd) {
        this.rbd = rbd;
    }
   
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public String getComuna() {
        return comuna;
    }

    public void setComuna(String comuna) {
        this.comuna = comuna;
    }

    public String getRuralidad() {
        return ruralidad;
    }

    public void setRuralidad(String ruralidad) {
        this.ruralidad = ruralidad;
    }

    public String getCategorizacion() {
        return categorizacion;
    }

    public void setCategorizacion(String categorizacion) {
        this.categorizacion = categorizacion;
    }

    public String getClasificacionSEP() {
        return clasificacionSEP;
    }

    public void setClasificacionSEP(String clasificacionSEP) {
        this.clasificacionSEP = clasificacionSEP;
    }

    public String getDependencia() {
        return dependencia;
    }

    public void setDependencia(String dependencia) {
        this.dependencia = dependencia;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }    
}
