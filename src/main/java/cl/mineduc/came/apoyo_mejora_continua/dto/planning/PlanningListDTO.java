package cl.mineduc.came.apoyo_mejora_continua.dto.planning;

import java.util.List;

public class PlanningListDTO {
    private String idPlanning;
    private Integer idRegion;
    private String region;
    private Integer idDeprov;
    private String deprov;
    private Integer idComuna;
    private String comuna;
    private List<String> supervisores;
    private String tipo;
    private String idRed;
    private String idAsesoriaDirecta;
    private String nombreRed;
    private String dependencias;
    private String categorizacion;
    private Integer sessiones;
    private String establecimientos;

    public String getIdPlanning() {
        return idPlanning;
    }

    public void setIdPlanning(String idPlanning) {
        this.idPlanning = idPlanning;
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

    public List<String> getSupervisores() {
        return supervisores;
    }

    public void setSupervisores(List<String> supervisores) {
        this.supervisores = supervisores;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getIdRed() {
        return idRed;
    }

    public void setIdRed(String idRed) {
        this.idRed = idRed;
    }

    public String getNombreRed() {
        return nombreRed;
    }

    public void setNombreRed(String nombreRed) {
        this.nombreRed = nombreRed;
    }

    public String getDependencias() {
        return dependencias;
    }

    public void setDependencias(String dependencias) {
        this.dependencias = dependencias;
    }

    public String getCategorizacion() {
        return categorizacion;
    }

    public void setCategorizacion(String categorizacion) {
        this.categorizacion = categorizacion;
    }

    public Integer getSessiones() {
        return sessiones;
    }

    public void setSessiones(Integer sessiones) {
        this.sessiones = sessiones;
    }

    public String getEstablecimientos() {
        return establecimientos;
    }

    public void setEstablecimientos(String establecimientos) {
        this.establecimientos = establecimientos;
    }

    public String getIdAsesoriaDirecta() {
        return idAsesoriaDirecta;
    }

    public void setIdAsesoriaDirecta(String idAsesoriaDirecta) {
        this.idAsesoriaDirecta = idAsesoriaDirecta;
    }
}
