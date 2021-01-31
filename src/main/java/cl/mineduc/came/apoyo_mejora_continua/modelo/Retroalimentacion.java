package cl.mineduc.came.apoyo_mejora_continua.modelo;

import java.util.Date;

public class Retroalimentacion {
    private Long idRetroalimentacion;
	private Long idSupervisor;
	private Long idPeriodo; 
	private Long idUsuario;
	private Long idMomentoAsesoria;
    private Long idRetroalimentacionFrecuencia;
    private Integer idRegion;
    private Integer idDeprov;
	private Integer numero;
	private Integer semestre; 
	private Date fechaPlanificacion;
	private Date fechaRegistroSesion; 
	private Date fechaRegistroJefeTecnico;
	private String practicaAbordada; 
	private String acuerdos;
	private String acciones; 
	private String observaciones; 
	private Long idUsuarioRegistro; 
    private Date fechaRegistro;
    private Long idSiguienteRetroalimentacion;

    public Long getIdRetroalimentacion() {
        return idRetroalimentacion;
    }

    public void setIdRetroalimentacion(Long idRetroalimentacion) {
        this.idRetroalimentacion = idRetroalimentacion;
    }

    public Long getIdSupervisor() {
        return idSupervisor;
    }

    public void setIdSupervisor(Long idSupervisor) {
        this.idSupervisor = idSupervisor;
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

    public Long getIdMomentoAsesoria() {
        return idMomentoAsesoria;
    }

    public void setIdMomentoAsesoria(Long idMomentoAsesoria) {
        this.idMomentoAsesoria = idMomentoAsesoria;
    }

    public Long getIdRetroalimentacionFrecuencia() {
        return idRetroalimentacionFrecuencia;
    }

    public void setIdRetroalimentacionFrecuencia(Long idRetroalimentacionFrecuencia) {
        this.idRetroalimentacionFrecuencia = idRetroalimentacionFrecuencia;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Integer getSemestre() {
        return semestre;
    }

    public void setSemestre(Integer semestre) {
        this.semestre = semestre;
    }

    public Date getFechaPlanificacion() {
        return fechaPlanificacion;
    }

    public void setFechaPlanificacion(Date fechaPlanificacion) {
        this.fechaPlanificacion = fechaPlanificacion;
    }

    public Date getFechaRegistroSesion() {
        return fechaRegistroSesion;
    }

    public void setFechaRegistroSesion(Date fechaRegistroSesion) {
        this.fechaRegistroSesion = fechaRegistroSesion;
    }

    public Date getFechaRegistroJefeTecnico() {
        return fechaRegistroJefeTecnico;
    }

    public void setFechaRegistroJefeTecnico(Date fechaRegistroJefeTecnico) {
        this.fechaRegistroJefeTecnico = fechaRegistroJefeTecnico;
    }

    public String getPracticaAbordada() {
        return practicaAbordada;
    }

    public void setPracticaAbordada(String practicaAbordada) {
        this.practicaAbordada = practicaAbordada;
    }

    public String getAcuerdos() {
        return acuerdos;
    }

    public void setAcuerdos(String acuerdos) {
        this.acuerdos = acuerdos;
    }

    public String getAcciones() {
        return acciones;
    }

    public void setAcciones(String acciones) {
        this.acciones = acciones;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Long getIdUsuarioRegistro() {
        return idUsuarioRegistro;
    }

    public void setIdUsuarioRegistro(Long idUsuarioRegistro) {
        this.idUsuarioRegistro = idUsuarioRegistro;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
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

    public Long getIdSiguienteRetroalimentacion() {
        return idSiguienteRetroalimentacion;
    }

    public void setIdSiguienteRetroalimentacion(Long idSiguienteRetroalimentacion) {
        this.idSiguienteRetroalimentacion = idSiguienteRetroalimentacion;
    }
}
