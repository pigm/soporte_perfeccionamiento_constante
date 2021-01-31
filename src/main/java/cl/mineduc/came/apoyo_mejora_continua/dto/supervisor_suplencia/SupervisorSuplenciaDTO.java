package cl.mineduc.came.apoyo_mejora_continua.dto.supervisor_suplencia;

import java.util.Date;

public class SupervisorSuplenciaDTO {
    
    private String idSupervisorSuplencia;
    private String idUsuarioSupervisorAusente;
    private String idUsuarioSupervisorSuplente;
    private Date startDate;
    private Date endDate;   

    private String userNameSA;
    private String apellidosSA;
    private String nombresSA;
    private String runSA;

    private String userNameSS;
    private String apellidosSS;
    private String nombresSS;
    private String runSS;

    public String getIdSupervisorSuplencia() {
        return idSupervisorSuplencia;
    }

    public void setIdSupervisorSuplencia(String idSupervisorSuplencia) {
        this.idSupervisorSuplencia = idSupervisorSuplencia;
    }   
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    
    public String getIdUsuarioSupervisorAusente() {
        return idUsuarioSupervisorAusente;
    }

    public void setIdUsuarioSupervisorAusente(String idUsuarioSupervisorAusente) {
        this.idUsuarioSupervisorAusente = idUsuarioSupervisorAusente;
    }

    public String getIdUsuarioSupervisorSuplente() {
        return idUsuarioSupervisorSuplente;
    }

    public void setIdUsuarioSupervisorSuplente(String idUsuarioSupervisorSuplente) {
        this.idUsuarioSupervisorSuplente = idUsuarioSupervisorSuplente;
    }

    public String getApellidosSA() {
        return apellidosSA;
    }

    public void setApellidosSA(String apellidosSA) {
        this.apellidosSA = apellidosSA;
    }

    public String getNombresSA() {
        return nombresSA;
    }

    public void setNombresSA(String nombresSA) {
        this.nombresSA = nombresSA;
    }

    public String getRunSA() {
        return runSA;
    }

    public void setRunSA(String runSA) {
        this.runSA = runSA;
    }

    public String getApellidosSS() {
        return apellidosSS;
    }

    public void setApellidosSS(String apellidosSS) {
        this.apellidosSS = apellidosSS;
    }

    public String getNombresSS() {
        return nombresSS;
    }

    public void setNombresSS(String nombresSS) {
        this.nombresSS = nombresSS;
    }

    public String getRunSS() {
        return runSS;
    }

    public void setRunSS(String runSS) {
        this.runSS = runSS;
    }

    @Override
    public String toString() {
        return "SupervisorSuplenciaDTO [apellidosSA=" + apellidosSA + ", apellidosSS=" + apellidosSS + ", endDate="
                + endDate + ", idSupervisorSuplencia=" + idSupervisorSuplencia + ", idUsuarioSupervisorAusente="
                + idUsuarioSupervisorAusente + ", idUsuarioSupervisorSuplente=" + idUsuarioSupervisorSuplente
                + ", nombresSA=" + nombresSA + ", nombresSS=" + nombresSS + ", runSA=" + runSA + ", runSS=" + runSS
                + ", startDate=" + startDate + "]";
    }

    public String getUserNameSA() {
        return userNameSA;
    }

    public void setUserNameSA(String userNameSA) {
        this.userNameSA = userNameSA;
    }

    public String getUserNameSS() {
        return userNameSS;
    }

    public void setUserNameSS(String userNameSS) {
        this.userNameSS = userNameSS;
    }
}
