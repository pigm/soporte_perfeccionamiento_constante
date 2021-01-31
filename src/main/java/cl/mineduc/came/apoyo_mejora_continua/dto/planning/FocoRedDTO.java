package cl.mineduc.came.apoyo_mejora_continua.dto.planning;

import java.util.List;

public class FocoRedDTO {
    private String idFoco;
    private String nombre;    
    private String descripcion;    
    private Integer logro;

    private List<SesionFocoAccionMejoraDTO> accionesMejora;

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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<SesionFocoAccionMejoraDTO> getAccionesMejora() {
        return accionesMejora;
    }

    public void setAccionesMejora(List<SesionFocoAccionMejoraDTO> accionesMejora) {
        this.accionesMejora = accionesMejora;
    }

    public Integer getLogro() {
        return logro;
    }

    public void setLogro(Integer logro) {
        this.logro = logro;
    }

    
}
