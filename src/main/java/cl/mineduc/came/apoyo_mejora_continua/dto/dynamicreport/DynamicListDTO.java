package cl.mineduc.came.apoyo_mejora_continua.dto.dynamicreport;

import java.util.List;

public class DynamicListDTO {
    private String rbd;
    private String establecimiento;
    private String estado;
    private String region;
    private String deprov;
    private String comuna;
    private String dependencia;
    private String categorizacion;
    private Integer periodo;
    private String tipoApoyo;
    private String tipoRed;
    private String supervisorDirecta;
    private List<String> supervisorRed;
    private String nombreRed;
    private Integer sesionesProgramadasDirecta;
    private Integer sesionesProgramadasRed;
    private Integer sesionesRealizadasDirecta;
    private Integer sesionesRealizadasRed;

    public String getRbd() {
        return rbd;
    }

    public void setRbd(String rbd) {
        this.rbd = rbd;
    }

    public String getEstablecimiento() {
        return establecimiento;
    }

    public void setEstablecimiento(String establecimiento) {
        this.establecimiento = establecimiento;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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

    public String getDependencia() {
        return dependencia;
    }

    public void setDependencia(String dependencia) {
        this.dependencia = dependencia;
    }

    public String getCategorizacion() {
        return categorizacion;
    }

    public void setCategorizacion(String categorizacion) {
        this.categorizacion = categorizacion;
    }

    public Integer getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Integer periodo) {
        this.periodo = periodo;
    }

    public String getTipoApoyo() {
        return tipoApoyo;
    }

    public void setTipoApoyo(String tipoApoyo) {
        this.tipoApoyo = tipoApoyo;
    }

    public String getTipoRed() {
        return tipoRed;
    }

    public void setTipoRed(String tipoRed) {
        this.tipoRed = tipoRed;
    }

    public String getSupervisorDirecta() {
        return supervisorDirecta;
    }

    public void setSupervisorDirecta(String supervisorDirecta) {
        this.supervisorDirecta = supervisorDirecta;
    }

    public List<String> getSupervisorRed() {
        return supervisorRed;
    }

    public void setSupervisorRed(List<String> supervisorRed) {
        this.supervisorRed = supervisorRed;
    }

    public String getNombreRed() {
        return nombreRed;
    }

    public void setNombreRed(String nombreRed) {
        this.nombreRed = nombreRed;
    }

    public Integer getSesionesProgramadasDirecta() {
        return sesionesProgramadasDirecta;
    }

    public void setSesionesProgramadasDirecta(Integer sesionesProgramadasDirecta) {
        this.sesionesProgramadasDirecta = sesionesProgramadasDirecta;
    }

    public Integer getSesionesProgramadasRed() {
        return sesionesProgramadasRed;
    }

    public void setSesionesProgramadasRed(Integer sesionesProgramadasRed) {
        this.sesionesProgramadasRed = sesionesProgramadasRed;
    }

    public Integer getSesionesRealizadasDirecta() {
        return sesionesRealizadasDirecta;
    }

    public void setSesionesRealizadasDirecta(Integer sesionesRealizadasDirecta) {
        this.sesionesRealizadasDirecta = sesionesRealizadasDirecta;
    }

    public Integer getSesionesRealizadasRed() {
        return sesionesRealizadasRed;
    }

    public void setSesionesRealizadasRed(Integer sesionesRealizadasRed) {
        this.sesionesRealizadasRed = sesionesRealizadasRed;
    }
}
