package cl.mineduc.came.apoyo_mejora_continua.dto.period;

import java.util.Date;

public class PeriodDataDTO {
    private Integer anio;
	private Date fechaInicioRegional;
	private Date fechaFinRegional;
	private Date fechaInicioProvincial;
	private Date fechaFinProvincial;
    private Date fechaInicioConformacionRedes;
    private Date fechaFinConformacionRedes;
    private Date fechaInicioAsignacionSupervisores;
    private Date fechaFinAsignacionSupervisores;

    private Date fechaInicioImplemetancionApoyo;
    private Date fechaFinImplemetancionApoyo;

    private String fechaFinImpApoyo;


    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public Date getFechaInicioRegional() {
        return fechaInicioRegional;
    }

    public void setFechaInicioRegional(Date fechaInicioRegional) {
        this.fechaInicioRegional = fechaInicioRegional;
    }

    public Date getFechaFinRegional() {
        return fechaFinRegional;
    }

    public void setFechaFinRegional(Date fechaFinRegional) {
        this.fechaFinRegional = fechaFinRegional;
    }

    public Date getFechaInicioProvincial() {
        return fechaInicioProvincial;
    }

    public void setFechaInicioProvincial(Date fechaInicioProvincial) {
        this.fechaInicioProvincial = fechaInicioProvincial;
    }

    public Date getFechaFinProvincial() {
        return fechaFinProvincial;
    }

    public void setFechaFinProvincial(Date fechaFinProvincial) {
        this.fechaFinProvincial = fechaFinProvincial;
    }

    public Date getFechaInicioConformacionRedes() {
        return fechaInicioConformacionRedes;
    }

    public void setFechaInicioConformacionRedes(Date fechaInicioConformacionRedes) {
        this.fechaInicioConformacionRedes = fechaInicioConformacionRedes;
    }

    public Date getFechaFinConformacionRedes() {
        return fechaFinConformacionRedes;
    }

    public void setFechaFinConformacionRedes(Date fechaFinConformacionRedes) {
        this.fechaFinConformacionRedes = fechaFinConformacionRedes;
    }

    public Date getFechaInicioAsignacionSupervisores() {
        return fechaInicioAsignacionSupervisores;
    }

    public void setFechaInicioAsignacionSupervisores(Date fechaInicioAsignacionSupervisores) {
        this.fechaInicioAsignacionSupervisores = fechaInicioAsignacionSupervisores;
    }

    public Date getFechaFinAsignacionSupervisores() {
        return fechaFinAsignacionSupervisores;
    }

    public void setFechaFinAsignacionSupervisores(Date fechaFinAsignacionSupervisores) {
        this.fechaFinAsignacionSupervisores = fechaFinAsignacionSupervisores;
    }

    public Date getFechaInicioImplemetancionApoyo() {
        return fechaInicioImplemetancionApoyo;
    }

    public void setFechaInicioImplemetancionApoyo(Date fechaInicioImplemetancionApoyo) {
        this.fechaInicioImplemetancionApoyo = fechaInicioImplemetancionApoyo;
    }

    public Date getFechaFinImplemetancionApoyo() {
        return fechaFinImplemetancionApoyo;
    }

    public void setFechaFinImplemetancionApoyo(Date fechaFinImplemetancionApoyo) {
        this.fechaFinImplemetancionApoyo = fechaFinImplemetancionApoyo;
    }

    public String getFechaFinImpApoyo() {
        return fechaFinImpApoyo;
    }

    public void setFechaFinImpApoyo(String fechaFinImpApoyo) {
        this.fechaFinImpApoyo = fechaFinImpApoyo;
    }

    
}
