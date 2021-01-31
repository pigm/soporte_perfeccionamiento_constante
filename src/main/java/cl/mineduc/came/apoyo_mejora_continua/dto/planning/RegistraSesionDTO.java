package cl.mineduc.came.apoyo_mejora_continua.dto.planning;

import java.util.List;

public class RegistraSesionDTO {
    private String idSesion;    
    private String fechaProximaReunion;
    private String tipoAsesoria;
    private List<FocoDTO> focos;

    public String getIdSesion() {
        return idSesion;
    }

    public void setIdSesion(String idSesion) {
        this.idSesion = idSesion;
    }

    public String getFechaProximaReunion() {
        return fechaProximaReunion;
    }

    public void setFechaProximaReunion(String fechaProximaReunion) {
        this.fechaProximaReunion = fechaProximaReunion;
    }

    public String getTipoAsesoria() {
        return tipoAsesoria;
    }

    public void setTipoAsesoria(String tipoAsesoria) {
        this.tipoAsesoria = tipoAsesoria;
    }

    public List<FocoDTO> getFocos() {
        return focos;
    }

    public void setFocos(List<FocoDTO> focos) {
        this.focos = focos;
    }    
}
