package cl.mineduc.came.apoyo_mejora_continua.modelo.api;

import java.util.List;

import cl.mineduc.came.apoyo_mejora_continua.modelo.EstablecimientoOrdenacion;
import cl.mineduc.came.apoyo_mejora_continua.modelo.api.director.EstablecimientoDirector;

public class Establecimientos {

    private Integer rbd;
    private String dvRbd;
    private String nombre;
    private TipoDependencia tipoDependencia;
    private EstadoEstablecimiento estadoEstablecimiento;
    private String fechaIngresoSistema;
    private String fechaActualizacion;
    private String rural;
    private String tipoEstablecimiento;
    private String tipoAlumnado;
    private String fechaInicioReconocimientoOficial;
    private String fechaTerminoReconocimientoOficial;
    private String fechaInicioRecibeSubvencion;
    private String clasificacionSep;

    private String categoriaBasica;
    private String categoriaMedia;

    private CodigoGeografico codigoGeografico;
    private DatosContacto datosContacto;
    private Sostenedor sostenedor;
    private EstablecimientoDirector establecimientoDirector;
    private List<Convenio> convenios = null;

    private List<EstablecimientoOrdenacion> orden;

    public Integer getRbd() {
        return rbd;
    }

    public void setRbd(Integer rbd) {
        this.rbd = rbd;
    }

    public String getDvRbd() {
        return dvRbd;
    }

    public void setDvRbd(String dvRbd) {
        this.dvRbd = dvRbd;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TipoDependencia getTipoDependencia() {
        return tipoDependencia;
    }

    public void setTipoDependencia(TipoDependencia tipoDependencia) {
        this.tipoDependencia = tipoDependencia;
    }

    public EstadoEstablecimiento getEstadoEstablecimiento() {
        return estadoEstablecimiento;
    }

    public void setEstadoEstablecimiento(EstadoEstablecimiento estadoEstablecimiento) {
        this.estadoEstablecimiento = estadoEstablecimiento;
    }

    public String getFechaIngresoSistema() {
        return fechaIngresoSistema;
    }

    public void setFechaIngresoSistema(String fechaIngresoSistema) {
        this.fechaIngresoSistema = fechaIngresoSistema;
    }

    public String getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(String fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public String getRural() {
        return rural;
    }

    public void setRural(String rural) {
        this.rural = rural;
    }

    public String getTipoEstablecimiento() {
        return tipoEstablecimiento;
    }

    public void setTipoEstablecimiento(String tipoEstablecimiento) {
        this.tipoEstablecimiento = tipoEstablecimiento;
    }

    public String getTipoAlumnado() {
        return tipoAlumnado;
    }

    public void setTipoAlumnado(String tipoAlumnado) {
        this.tipoAlumnado = tipoAlumnado;
    }

    public String getFechaInicioReconocimientoOficial() {
        return fechaInicioReconocimientoOficial;
    }

    public void setFechaInicioReconocimientoOficial(String fechaInicioReconocimientoOficial) {
        this.fechaInicioReconocimientoOficial = fechaInicioReconocimientoOficial;
    }

    public String getFechaTerminoReconocimientoOficial() {
        return fechaTerminoReconocimientoOficial;
    }

    public void setFechaTerminoReconocimientoOficial(String fechaTerminoReconocimientoOficial) {
        this.fechaTerminoReconocimientoOficial = fechaTerminoReconocimientoOficial;
    }

    public String getFechaInicioRecibeSubvencion() {
        return fechaInicioRecibeSubvencion;
    }

    public void setFechaInicioRecibeSubvencion(String fechaInicioRecibeSubvencion) {
        this.fechaInicioRecibeSubvencion = fechaInicioRecibeSubvencion;
    }

    public CodigoGeografico getCodigoGeografico() {
        return codigoGeografico;
    }

    public void setCodigoGeografico(CodigoGeografico codigoGeografico) {
        this.codigoGeografico = codigoGeografico;
    }

    public DatosContacto getDatosContacto() {
        return datosContacto;
    }

    public void setDatosContacto(DatosContacto datosContacto) {
        this.datosContacto = datosContacto;
    }

    public Sostenedor getSostenedor() {
        return sostenedor;
    }

    public void setSostenedor(Sostenedor sostenedor) {
        this.sostenedor = sostenedor;
    }

    public List<Convenio> getConvenios() {
        return convenios;
    }

    public void setConvenios(List<Convenio> convenios) {
        this.convenios = convenios;
    }

    public EstablecimientoDirector getEstablecimientoDirector() {
        return establecimientoDirector;
    }

    public void setEstablecimientoDirector(EstablecimientoDirector establecimientoDirector) {
        this.establecimientoDirector = establecimientoDirector;
    }

    public List<EstablecimientoOrdenacion> getOrden() {
        return orden;
    }

    public void setOrden(List<EstablecimientoOrdenacion> orden) {
        this.orden = orden;
    }

    public String getClasificacionSep() {
        return clasificacionSep;
    }

    public void setClasificacionSep(String clasificacionSep) {
        this.clasificacionSep = clasificacionSep;
    }

    public String getCategoriaBasica() {
        return categoriaBasica;
    }

    public void setCategoriaBasica(String categoriaBasica) {
        this.categoriaBasica = categoriaBasica;
    }

    public String getCategoriaMedia() {
        return categoriaMedia;
    }

    public void setCategoriaMedia(String categoriaMedia) {
        this.categoriaMedia = categoriaMedia;
    }
}
