package cl.mineduc.came.apoyo_mejora_continua.dto.planning;

public class PlanningRequestDTO {
    private Integer idRegion;
    private Integer idDeprov;
    private Integer idComuna;
    private String userNameSupervisor;
    private String categorizacion;
    private Integer dependencia;
    private String rbd;
    private String nombreEstablecimiento;
    private String tipo;
    private String idTipoRed;
    private String nombreRed;

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

    public String getUserNameSupervisor() {
        return userNameSupervisor;
    }

    public void setUserNameSupervisor(String userNameSupervisor) {
        this.userNameSupervisor = userNameSupervisor;
    }

    public String getCategorizacion() {
        return categorizacion;
    }

    public void setCategorizacion(String categorizacion) {
        this.categorizacion = categorizacion;
    }

    public Integer getDependencia() {
        return dependencia;
    }

    public void setDependencia(Integer dependencia) {
        this.dependencia = dependencia;
    }

    public String getRbd() {
        return rbd;
    }

    public void setRbd(String rbd) {
        this.rbd = rbd;
    }

    public String getNombreEstablecimiento() {
        return nombreEstablecimiento;
    }

    public void setNombreEstablecimiento(String nombreEstablecimiento) {
        this.nombreEstablecimiento = nombreEstablecimiento;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
   
    public String getNombreRed() {
        return nombreRed;
    }

    public void setNombreRed(String nombreRed) {
        this.nombreRed = nombreRed;
    }

    public String getIdTipoRed() {
        return idTipoRed;
    }

    public void setIdTipoRed(String idTipoRed) {
        this.idTipoRed = idTipoRed;
    }
    
}
