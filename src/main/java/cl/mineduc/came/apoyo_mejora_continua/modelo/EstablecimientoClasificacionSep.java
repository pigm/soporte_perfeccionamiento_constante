package cl.mineduc.came.apoyo_mejora_continua.modelo;

public class EstablecimientoClasificacionSep {
    
    private Integer rbd;
    private Long idClasificacionSep;
    private String clasificacion;

   

    public Long getIdClasificacionSep() {
      return idClasificacionSep;
    }

    public void setIdClasificacionSep(Long idClasificacionSep) {
      this.idClasificacionSep = idClasificacionSep;
    }

    public String getClasificacion() {
      return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
      this.clasificacion = clasificacion;
    }

    public Integer getRbd() {
      return rbd;
    }

    public void setRbd(Integer rbd) {
      this.rbd = rbd;
    }

}
