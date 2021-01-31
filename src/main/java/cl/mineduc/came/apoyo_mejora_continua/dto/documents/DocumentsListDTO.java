package cl.mineduc.came.apoyo_mejora_continua.dto.documents;

import java.util.List;

public class DocumentsListDTO {
    private Integer idRegion;
    private String region;
    private String tipo;
    private String deprov;
    private String estado;
    private List<DocumentsViewDTO> documentos;

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDeprov() {
        return deprov;
    }

    public void setDeprov(String deprov) {
        this.deprov = deprov;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<DocumentsViewDTO> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(List<DocumentsViewDTO> documentos) {
        this.documentos = documentos;
    }

    public Integer getIdRegion() {
        return idRegion;
    }

    public void setIdRegion(Integer idRegion) {
        this.idRegion = idRegion;
    }
}
