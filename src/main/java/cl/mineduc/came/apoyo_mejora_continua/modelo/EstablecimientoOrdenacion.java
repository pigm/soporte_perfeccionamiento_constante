package cl.mineduc.came.apoyo_mejora_continua.modelo;

public class EstablecimientoOrdenacion {

  private Integer rbd;
  private String ordenacion;
  private String anio;
  private String nivel;

  private String processOrdenacion(String orden) {
    String result = "";

    if (orden == null) {
      result = "";
    } else if (orden.equalsIgnoreCase("s/o")) {
      result = "Sin Ordenacion";
    } else if (orden.equalsIgnoreCase("m")) {
      result = "Medio";
    } else if (orden.equalsIgnoreCase("mb")) {
      result = "Medio Bajo";
    } else {
      // no asigna nada.
    }

    return result;
  }

  public String getOrdenacion() {
    return ordenacion;
  }

  public void setOrdenacion(String ordenacion) {
    this.ordenacion = this.processOrdenacion(ordenacion);
  }

  public String getAnio() {
    return anio;
  }

  public void setAnio(String anio) {
    this.anio = anio;
  }

  public String getNivel() {
    return nivel;
  }

  public void setNivel(String nivel) {
    this.nivel = nivel;
  }

  public Integer getRbd() {
    return rbd;
  }

  public void setRbd(Integer rbd) {
    this.rbd = rbd;
  }

}
