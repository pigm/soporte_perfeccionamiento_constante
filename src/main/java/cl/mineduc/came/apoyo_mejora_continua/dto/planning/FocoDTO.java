package cl.mineduc.came.apoyo_mejora_continua.dto.planning;

import java.util.List;

public class FocoDTO {
    private String idFoco;
    private String nombre;
    private String descripcion;
    private List<AccionesMejorasDTO> accionesMejoras;

    private long logrado;
    private int total;

    public String getIdFoco() {
        return idFoco;
    }

    public void setIdFoco(String idFoco) {
        this.idFoco = idFoco;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<AccionesMejorasDTO> getAccionesMejoras() {
        return accionesMejoras;
    }

    public void setAccionesMejoras(List<AccionesMejorasDTO> accionesMejoras) {
        this.accionesMejoras = accionesMejoras;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public long getLogrado() {
        return logrado;
    }

    public void setLogrado(long logrado) {
        this.logrado = logrado;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
    
}
