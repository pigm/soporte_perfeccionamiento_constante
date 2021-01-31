package cl.mineduc.came.apoyo_mejora_continua.dto.networks;

import java.util.Date;
import java.util.List;

public class NetworkDetailDTO {
    private String idRed;    
    private String nombre;
    private Date fechaCreacion;
    private int idRegion; 
    private String region;
    private int idDeprov;
    private String deprov;
    private int idComuna;
    private String comuna;

    private String tipoRed;
    private String idTipoRed;
    
    private List<EstablecimientosDetailsDTO> establecimientos;

    public String getIdRed() {
        return idRed;
    }

    public void setIdRed(String idRed) {
        this.idRed = idRed;
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

    public String getTipoRed() {
        return tipoRed;
    }

    public void setTipoRed(String tipoRed) {
        this.tipoRed = tipoRed;
    }

    public List<EstablecimientosDetailsDTO> getEstablecimientos() {
        return establecimientos;
    }

    public void setEstablecimientos(List<EstablecimientosDetailsDTO> establecimientos) {
        this.establecimientos = establecimientos;
    }

    

    public int getIdRegion() {
        return idRegion;
    }

    public void setIdRegion(int idRegion) {
        this.idRegion = idRegion;
    }

    public int getIdDeprov() {
        return idDeprov;
    }

    public void setIdDeprov(int idDeprov) {
        this.idDeprov = idDeprov;
    }

    public int getIdComuna() {
        return idComuna;
    }

    public void setIdComuna(int idComuna) {
        this.idComuna = idComuna;
    }

    public String getComuna() {
        return comuna;
    }

    public void setComuna(String comuna) {
        this.comuna = comuna;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getIdTipoRed() {
        return idTipoRed;
    }

    public void setIdTipoRed(String idTipoRed) {
        this.idTipoRed = idTipoRed;
    }

}
