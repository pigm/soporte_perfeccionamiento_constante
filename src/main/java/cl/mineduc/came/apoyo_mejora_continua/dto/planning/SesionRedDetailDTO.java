package cl.mineduc.came.apoyo_mejora_continua.dto.planning;

import java.util.Date;
import java.util.List;

public class SesionRedDetailDTO {
    private String idSesion;
    private Integer numero;
    private Date fechaPlanificacion;
    private Date fechaRalizada;
    private String estado;
    private String idSesionTipo;
    private String tipoSesion;
    private String tipoRed;
    private List<FocoRedDTO> focos;
    private List<ObjetivoMejoraDTO> objetivos;
    private List<SesionRedParticipanteDTO> participantes;

    public String getIdSesion() {
        return idSesion;
    }

    public void setIdSesion(String idSesion) {
        this.idSesion = idSesion;
    }

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

    public List<FocoRedDTO> getFocos() {
        return focos;
    }

    public void setFocos(List<FocoRedDTO> focos) {
        this.focos = focos;
    }

    public List<ObjetivoMejoraDTO> getObjetivos() {
        return objetivos;
    }

    public void setObjetivos(List<ObjetivoMejoraDTO> objetivos) {
        this.objetivos = objetivos;
    }

    public List<SesionRedParticipanteDTO> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(List<SesionRedParticipanteDTO> participantes) {
        this.participantes = participantes;
    }

    public String getTipoRed() {
        return tipoRed;
    }

    public void setTipoRed(String tipoRed) {
        this.tipoRed = tipoRed;
    }

}