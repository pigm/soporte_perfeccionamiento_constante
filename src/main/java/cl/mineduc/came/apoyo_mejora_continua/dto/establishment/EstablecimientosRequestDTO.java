package cl.mineduc.came.apoyo_mejora_continua.dto.establishment;

public class EstablecimientosRequestDTO {
    private Integer estado;
    private Integer dependencia;
    private Integer region;
    private Integer deprov;
    private Integer comuna;
    private Integer rows;
    private String ruralidad;
    private String filterText;
    private String convenio;
    private String clasificacionSEP;
    private String categorizacion;

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Integer getDependencia() {
        return dependencia;
    }

    public void setDependencia(Integer dependencia) {
        this.dependencia = dependencia;
    }

    public Integer getRegion() {
        return region;
    }

    public void setRegion(Integer region) {
        this.region = region;
    }

    public Integer getDeprov() {
        return deprov;
    }

    public void setDeprov(Integer deprov) {
        this.deprov = deprov;
    }

    public Integer getComuna() {
        return comuna;
    }

    public void setComuna(Integer comuna) {
        this.comuna = comuna;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public String getFilterText() {
        return filterText;
    }

    public void setFilterText(String filterText) {
        this.filterText = filterText;
    }

    public String getConvenio() {
        return convenio;
    }

    public void setConvenio(String convenio) {
        this.convenio = convenio;
    }

    public String getRuralidad() {
        return ruralidad;
    }

    public void setRuralidad(String ruralidad) {
        this.ruralidad = ruralidad;
    }

    public String getClasificacionSEP() {
        return clasificacionSEP;
    }

    public void setClasificacionSEP(String clasificacionSEP) {
        this.clasificacionSEP = clasificacionSEP;
    }

    public String getCategorizacion() {
        return categorizacion;
    }

    public void setCategorizacion(String categorizacion) {
        this.categorizacion = categorizacion;
    }
}
