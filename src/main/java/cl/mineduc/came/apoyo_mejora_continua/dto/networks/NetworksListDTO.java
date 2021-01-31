package cl.mineduc.came.apoyo_mejora_continua.dto.networks;

import java.util.Date;
import java.util.List;

public class NetworksListDTO {
    private String idRed;
    
    private String nombre;
    private String region;
    private String deprov;
    private String tipoRed;
    private Date fechaCreacion;
    private String establecimientos;
    private List<String> establecimientosList;

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

    public String getTipoRed() {
        return tipoRed;
    }

    public void setTipoRed(String tipoRed) {
        this.tipoRed = tipoRed;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
    
    public String getIdRed() {
        return idRed;
    }

    public void setIdRed(String idRed) {
        this.idRed = idRed;
    }

    public String getEstablecimientos() {
        return establecimientos;
    }

    public void setEstablecimientos(String establecimientos) {
        this.establecimientos = establecimientos;
    }

    public List<String> getEstablecimientosList() {
        return establecimientosList;
    }

    public void setEstablecimientosList(List<String> establecimientosList) {
        this.establecimientosList = establecimientosList;
    }    
}
