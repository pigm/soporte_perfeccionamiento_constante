package cl.mineduc.came.apoyo_mejora_continua.modelo;

import java.util.Date;
import java.util.List;

public class Red {
    private Long idRed;
    private Long idTipoRed; 
	private String nombre;
	private Long idPeriodo; 
	private Long idUsuario;
	private Long idRegion; 
	private Long idDeprov; 
	private Long idComuna; 
	private Date fechaCreacion;
	private Date fechaRegistro;
	private Long idUsuarioRegistro;
    private List<RedEstablecimiento> establecimientos;
    private List<RedSostenedor> sostenedores;
    private List<RedSupervisor> supervisores;

    public Long getIdRed() {
        return idRed;
    }

    public void setIdRed(Long idRed) {
        this.idRed = idRed;
    }

    public Long getIdTipoRed() {
        return idTipoRed;
    }

    public void setIdTipoRed(Long idTipoRed) {
        this.idTipoRed = idTipoRed;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getIdPeriodo() {
        return idPeriodo;
    }

    public void setIdPeriodo(Long idPeriodo) {
        this.idPeriodo = idPeriodo;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Long getIdRegion() {
        return idRegion;
    }

    public void setIdRegion(Long idRegion) {
        this.idRegion = idRegion;
    }

    public Long getIdDeprov() {
        return idDeprov;
    }

    public void setIdDeprov(Long idDeprov) {
        this.idDeprov = idDeprov;
    }

    public Long getIdComuna() {
        return idComuna;
    }

    public void setIdComuna(Long idComuna) {
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

    public Long getIdUsuarioRegistro() {
        return idUsuarioRegistro;
    }

    public void setIdUsuarioRegistro(Long idUsuarioRegistro) {
        this.idUsuarioRegistro = idUsuarioRegistro;
    }

    public List<RedEstablecimiento> getEstablecimientos() {
        return establecimientos;
    }

    public void setEstablecimientos(List<RedEstablecimiento> establecimientos) {
        this.establecimientos = establecimientos;
    }

    public List<RedSostenedor> getSostenedores() {
        return sostenedores;
    }

    public void setSostenedores(List<RedSostenedor> sostenedores) {
        this.sostenedores = sostenedores;
    }

    public List<RedSupervisor> getSupervisores() {
        return supervisores;
    }

    public void setSupervisores(List<RedSupervisor> supervisores) {
        this.supervisores = supervisores;
    }
}
