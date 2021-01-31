package cl.mineduc.came.apoyo_mejora_continua.dto.advisory;

import java.util.Date;

public class AdvisoryListDTO {
    private String idAsesoriaDirecta;
    private String rbd;
    private Integer idRegion;
    private String region;
    private Integer idDeprov;
    private String deprov;
    private Integer idComuna;
    private String comuna;
    private Date fechaCreacion;
    private String establecimientos;    
    private Boolean habilitado;

    public String getIdAsesoriaDirecta() {
        return idAsesoriaDirecta;
    }

    public void setIdAsesoriaDirecta(String idAsesoriaDirecta) {
        this.idAsesoriaDirecta = idAsesoriaDirecta;
    }

    public Integer getIdRegion() {
        return idRegion;
    }

    public void setIdRegion(Integer idRegion) {
        this.idRegion = idRegion;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Integer getIdDeprov() {
        return idDeprov;
    }

    public void setIdDeprov(Integer idDeprov) {
        this.idDeprov = idDeprov;
    }

    public String getDeprov() {
        return deprov;
    }

    public void setDeprov(String deprov) {
        this.deprov = deprov;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getEstablecimientos() {
        return establecimientos;
    }

    public void setEstablecimientos(String establecimientos) {
        this.establecimientos = establecimientos;
    }

    public Boolean getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(Boolean habilitado) {
        this.habilitado = habilitado;
    }

    public String getRbd() {
        return rbd;
    }

    public void setRbd(String rbd) {
        this.rbd = rbd;
    }

    public Integer getIdComuna() {
        return idComuna;
    }

    public void setIdComuna(Integer idComuna) {
        this.idComuna = idComuna;
    }

    public String getComuna() {
        return comuna;
    }

    public void setComuna(String comuna) {
        this.comuna = comuna;
    }   
    
}
