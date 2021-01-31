package cl.mineduc.came.apoyo_mejora_continua.dto.planning;

import java.util.List;

public class AsesoriaDirectaDTO {
    private String idAsesoria;
    private String idAsesoriaDirecta;

    private Integer rbd;
    private String nombreEstablecimiento;

    private List<SupervisorDTO> supervisores;
    private List<SesionDTO> sesiones;

    public String getIdAsesoria() {
        return idAsesoria;
    }

    public void setIdAsesoria(String idAsesoria) {
        this.idAsesoria = idAsesoria;
    }

    public String getIdAsesoriaDirecta() {
        return idAsesoriaDirecta;
    }

    public void setIdAsesoriaDirecta(String idAsesoriaDirecta) {
        this.idAsesoriaDirecta = idAsesoriaDirecta;
    }

    public Integer getRbd() {
        return rbd;
    }

    public void setRbd(Integer rbd) {
        this.rbd = rbd;
    }

    public List<SupervisorDTO> getSupervisores() {
        return supervisores;
    }

    public void setSupervisores(List<SupervisorDTO> supervisores) {
        this.supervisores = supervisores;
    }

    public List<SesionDTO> getSesiones() {
        return sesiones;
    }

    public void setSesiones(List<SesionDTO> sesiones) {
        this.sesiones = sesiones;
    }

    public String getNombreEstablecimiento() {
        return nombreEstablecimiento;
    }

    public void setNombreEstablecimiento(String nombreEstablecimiento) {
        this.nombreEstablecimiento = nombreEstablecimiento;
    }
}
