package cl.mineduc.came.apoyo_mejora_continua.modelo.api;

public class Convenio {

    private String id;
    private String glosaConvenio;
    private String grupoId;
    private Object valor;
    private String numeroFolio;
    private String fechaInicioConvenio;
    private String fechaFinConvenio;
    private String fechaCreacionConvenio;
    private String fechaModificacionConvenio;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGlosaConvenio() {
        return glosaConvenio;
    }

    public void setGlosaConvenio(String glosaConvenio) {
        this.glosaConvenio = glosaConvenio;
    }

    public String getGrupoId() {
        return grupoId;
    }

    public void setGrupoId(String grupoId) {
        this.grupoId = grupoId;
    }

    public Object getValor() {
        return valor;
    }

    public void setValor(Object valor) {
        this.valor = valor;
    }

    public String getNumeroFolio() {
        return numeroFolio;
    }

    public void setNumeroFolio(String numeroFolio) {
        this.numeroFolio = numeroFolio;
    }

    public String getFechaInicioConvenio() {
        return fechaInicioConvenio;
    }

    public void setFechaInicioConvenio(String fechaInicioConvenio) {
        this.fechaInicioConvenio = fechaInicioConvenio;
    }

    public String getFechaFinConvenio() {
        return fechaFinConvenio;
    }

    public void setFechaFinConvenio(String fechaFinConvenio) {
        this.fechaFinConvenio = fechaFinConvenio;
    }

    public String getFechaCreacionConvenio() {
        return fechaCreacionConvenio;
    }

    public void setFechaCreacionConvenio(String fechaCreacionConvenio) {
        this.fechaCreacionConvenio = fechaCreacionConvenio;
    }

    public String getFechaModificacionConvenio() {
        return fechaModificacionConvenio;
    }

    public void setFechaModificacionConvenio(String fechaModificacionConvenio) {
        this.fechaModificacionConvenio = fechaModificacionConvenio;
    }

}
