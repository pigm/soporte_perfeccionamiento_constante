package cl.mineduc.came.apoyo_mejora_continua.dto.advisory;

import java.util.List;

public class AdvisoryDTO {
    private String idAsesoriaDirecta;
    private String fechaCreacion;
    private Integer idRegion;
    private Integer idDeprov;
    private Integer idComuna;
    private List<String> establecimiento;

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
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
    
    public String getIdAsesoriaDirecta() {
        return idAsesoriaDirecta;
    }

    public void setIdAsesoriaDirecta(String idAsesoriaDirecta) {
        this.idAsesoriaDirecta = idAsesoriaDirecta;
    }

    public List<String> getEstablecimiento() {
        return establecimiento;
    }

    public void setEstablecimiento(List<String> establecimiento) {
        this.establecimiento = establecimiento;
    }    

}
