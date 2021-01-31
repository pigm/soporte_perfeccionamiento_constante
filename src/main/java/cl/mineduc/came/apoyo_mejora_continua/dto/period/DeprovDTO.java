package cl.mineduc.came.apoyo_mejora_continua.dto.period;

import java.util.Date;

public class DeprovDTO {
    private Integer periodo;
    private Integer deprovId;
    private String deprov;
    private Date fechaInicio;
    private Date fechaFin;

    public Integer getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Integer periodo) {
        this.periodo = periodo;
    }

    public Integer getDeprovId() {
        return deprovId;
    }

    public void setDeprovId(Integer deprovId) {
        this.deprovId = deprovId;
    }

    public String getDeprov() {
        return deprov;
    }

    public void setDeprov(String deprov) {
        this.deprov = deprov;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }    
}
