package cl.mineduc.came.apoyo_mejora_continua.dto.planning;

import java.util.Date;
import java.util.List;

public class SesionDetailDTO {
    private String idSesion;    
    private Integer numero;
    private Date fechaPlanificacion;
    private Date fechaRalizada;
    private String estado;
    private String idSesionTipo;
    private String tipoSesion;    
    private List<FocoDTO> focos;

   

    private Date fechaProximaPlanificacion;

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Date getFechaPlanificacion() {
        return fechaPlanificacion;
    }

    public void setFechaPlanificacion(Date fechaPlanificacion) {
        this.fechaPlanificacion = fechaPlanificacion;
    }

    public Date getFechaRalizada() {
        return fechaRalizada;
    }

    public void setFechaRalizada(Date fechaRalizada) {
        this.fechaRalizada = fechaRalizada;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getIdSesion() {
        return idSesion;
    }

    public void setIdSesion(String idSesion) {
        this.idSesion = idSesion;
    }

    public String getIdSesionTipo() {
        return idSesionTipo;
    }

    public void setIdSesionTipo(String idSesionTipo) {
        this.idSesionTipo = idSesionTipo;
    }

    public String getTipoSesion() {
        return tipoSesion;
    }

    public void setTipoSesion(String tipoSesion) {
        this.tipoSesion = tipoSesion;
    }

    public List<FocoDTO> getFocos() {
        return focos;
    }

    public void setFocos(List<FocoDTO> focos) {
        this.focos = focos;
    }

    public Date getFechaProximaPlanificacion() {
        return fechaProximaPlanificacion;
    }

    public void setFechaProximaPlanificacion(Date fechaProximaPlanificacion) {
        this.fechaProximaPlanificacion = fechaProximaPlanificacion;
    }

    

}
