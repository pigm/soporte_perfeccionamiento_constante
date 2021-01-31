package cl.mineduc.came.apoyo_mejora_continua.dto.networks;

import java.util.List;

public class NetworkDTO {
    private String idRed;
    private String fechaCreacion;
    private String nombre;
    private Integer idRegion;
    private Integer idDeprov;
    private Integer idComuna;
    private String idTipoRed;
    private List<String> establecimientos;

    public String getIdRed() {
        return idRed;
    }

    public void setIdRed(String idRed) {
        this.idRed = idRed;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getIdRegion() {
        return idRegion;
    }

    public void setIdRegion(Integer idRegion) {
        this.idRegion = idRegion;
    }

    public Integer getIdDeprov() {
        return idDeprov;
    }

    public void setIdDeprov(Integer idDeprov) {
        this.idDeprov = idDeprov;
    }

    public Integer getIdComuna() {
        return idComuna;
    }

    public void setIdComuna(Integer idComuna) {
        this.idComuna = idComuna;
    }

    public String getIdTipoRed() {
        return idTipoRed;
    }

    public void setIdTipoRed(String idTipoRed) {
        this.idTipoRed = idTipoRed;
    }

    public List<String> getEstablecimientos() {
        return establecimientos;
    }

    public void setEstablecimientos(List<String> establecimientos) {
        this.establecimientos = establecimientos;
    }    
}
