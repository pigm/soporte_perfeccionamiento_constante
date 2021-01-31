package cl.mineduc.came.apoyo_mejora_continua.dto.planning;

import java.util.List;

public class AccionesMejorasDTO {
    private String idAccionesMejoras;
    private String nombre;
    private List<MovimientosClavesDTO> movimientosClaves;

    public String getIdAccionesMejoras() {
        return idAccionesMejoras;
    }

    public void setIdAccionesMejoras(String idAccionesMejoras) {
        this.idAccionesMejoras = idAccionesMejoras;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<MovimientosClavesDTO> getMovimientosClaves() {
        return movimientosClaves;
    }

    public void setMovimientosClaves(List<MovimientosClavesDTO> movimientosClaves) {
        this.movimientosClaves = movimientosClaves;
    }
}
