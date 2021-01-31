package cl.mineduc.came.apoyo_mejora_continua.dto.planning;

public class MovimientosClavesDTO {
    private String idMovimientosClaves;
    private String nombre;
    private Boolean completado;

    public String getIdMovimientosClaves() {
        return idMovimientosClaves;
    }

    public void setIdMovimientosClaves(String idMovimientosClaves) {
        this.idMovimientosClaves = idMovimientosClaves;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getCompletado() {
        return completado;
    }

    public void setCompletado(Boolean completado) {
        this.completado = completado;
    }
}
