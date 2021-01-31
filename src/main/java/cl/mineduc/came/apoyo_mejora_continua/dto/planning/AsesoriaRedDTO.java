package cl.mineduc.came.apoyo_mejora_continua.dto.planning;

import java.util.List;

public class AsesoriaRedDTO {
    private String idAsesoria;
    private String idRed;
    private String nombreRed;
    private String idTipoRed;
    private String tipoRed;
    private Integer idRegion;
    private String region;
    private Integer idDeprov;
    private String deprov;

    private EncargadoRedDTO encargado;
    private List<EstablecimientoRedDTO> establecimientos;
    private List<SupervisorDTO> supervisores;
    private List<SesionDTO> sesiones;

    public String getIdAsesoria() {
        return idAsesoria;
    }

    public void setIdAsesoria(String idAsesoria) {
        this.idAsesoria = idAsesoria;
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

    public String getIdTipoRed() {
        return idTipoRed;
    }

    public void setIdTipoRed(String idTipoRed) {
        this.idTipoRed = idTipoRed;
    }

    public String getTipoRed() {
        return tipoRed;
    }

    public void setTipoRed(String tipoRed) {
        this.tipoRed = tipoRed;
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

    public EncargadoRedDTO getEncargado() {
        return encargado;
    }

    public void setEncargado(EncargadoRedDTO encargado) {
        this.encargado = encargado;
    }

    public List<EstablecimientoRedDTO> getEstablecimientos() {
        return establecimientos;
    }

    public void setEstablecimientos(List<EstablecimientoRedDTO> establecimientos) {
        this.establecimientos = establecimientos;
    }

    public List<SupervisorDTO> getSupervisores() {
        return supervisores;
    }

    public void setSupervisores(List<SupervisorDTO> supervisores) {
        this.supervisores = supervisores;
    }

    public List<SesionDTO> getSesiones() {
        return sesiones;
    }

    public void setSesiones(List<SesionDTO> sesiones) {
        this.sesiones = sesiones;
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

}