package cl.mineduc.came.apoyo_mejora_continua.dto.planning;

import java.util.List;

public class RegistraSesionRedDTO {
    private String idSesion;
    private String fechaProximaReunion;
    private String tipoAsesoria;
    private List<FocoRedDTO> focos;
    private List<ObjetivoMejoraDTO> objetivos;
    private List<SesionRedParticipanteDTO> participantes;

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

    public List<FocoRedDTO> getFocos() {
        return focos;
    }

    public void setFocos(List<FocoRedDTO> focos) {
        this.focos = focos;
    }

    public List<ObjetivoMejoraDTO> getObjetivos() {
        return objetivos;
    }

    public void setObjetivos(List<ObjetivoMejoraDTO> objetivos) {
        this.objetivos = objetivos;
    }

    public List<SesionRedParticipanteDTO> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(List<SesionRedParticipanteDTO> participantes) {
        this.participantes = participantes;
    }
    
}
