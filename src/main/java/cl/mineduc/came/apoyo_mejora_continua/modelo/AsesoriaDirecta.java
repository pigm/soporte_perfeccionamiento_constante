package cl.mineduc.came.apoyo_mejora_continua.modelo;

import java.util.Date;

public class AsesoriaDirecta {
    private Long idAsesoriaDirecta;
    private Long idSupervisor;
    private Integer rbd;
    private String nombre;
    private Integer idRegion;
    private Integer idDeprov;
    private Integer idComuna;
    private Date fechaCreacion;
    private Date fechaRegistro;
    private Long idUsuarioCreacion;
    private Long idUsuarioRegistro;
    private Long idPeriodo;
    private Long idAsignacionSupervisor;
    private Boolean habilitado;

    public Long getIdAsesoriaDirecta() {
        return idAsesoriaDirecta;
    }

    public void setIdAsesoriaDirecta(Long idAsesoriaDirecta) {
        this.idAsesoriaDirecta = idAsesoriaDirecta;
    }

    public Long getIdSupervisor() {
        return idSupervisor;
    }

    public void setIdSupervisor(Long idSupervisor) {
        this.idSupervisor = idSupervisor;
    }

    public Integer getRbd() {
        return rbd;
    }

    public void setRbd(Integer rbd) {
        this.rbd = rbd;
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

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Long getIdUsuarioCreacion() {
        return idUsuarioCreacion;
    }

    public void setIdUsuarioCreacion(Long idUsuarioCreacion) {
        this.idUsuarioCreacion = idUsuarioCreacion;
    }

    public Long getIdUsuarioRegistro() {
        return idUsuarioRegistro;
    }

    public void setIdUsuarioRegistro(Long idUsuarioRegistro) {
        this.idUsuarioRegistro = idUsuarioRegistro;
    }

    public Long getIdPeriodo() {
        return idPeriodo;
    }

    public void setIdPeriodo(Long idPeriodo) {
        this.idPeriodo = idPeriodo;
    }

    public Long getIdAsignacionSupervisor() {
        return idAsignacionSupervisor;
    }

    public void setIdAsignacionSupervisor(Long idAsignacionSupervisor) {
        this.idAsignacionSupervisor = idAsignacionSupervisor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(Boolean habilitado) {
        this.habilitado = habilitado;
    }

}