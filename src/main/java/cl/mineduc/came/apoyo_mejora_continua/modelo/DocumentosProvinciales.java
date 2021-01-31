package cl.mineduc.came.apoyo_mejora_continua.modelo;

import java.util.Date;

public class DocumentosProvinciales {
    private Long idDocumentosProvinciales; 
    private Long idPeriodo;
    private Long idPeriodoDocumentosProvinciales;
    private Date fechaRegistro;
    private Long idUsuarioRegistro; 
    private String filePath; 
    private String fileName;
    private Long idRegion;
    private Long idDeprov;
    private String typeDocument;
    private Boolean hasDownloaded;
    
    public Long getIdPeriodo() {
        return idPeriodo;
    }

    public void setIdPeriodo(Long idPeriodo) {
        this.idPeriodo = idPeriodo;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Long getIdUsuarioRegistro() {
        return idUsuarioRegistro;
    }

    public void setIdUsuarioRegistro(Long idUsuarioRegistro) {
        this.idUsuarioRegistro = idUsuarioRegistro;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Long getIdRegion() {
        return idRegion;
    }

    public void setIdRegion(Long idRegion) {
        this.idRegion = idRegion;
    }

    public Long getIdDeprov() {
        return idDeprov;
    }

    public void setIdDeprov(Long idDeprov) {
        this.idDeprov = idDeprov;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getTypeDocument() {
        return typeDocument;
    }

    public void setTypeDocument(String typeDocument) {
        this.typeDocument = typeDocument;
    }

    public Long getIdDocumentosProvinciales() {
        return idDocumentosProvinciales;
    }

    public void setIdDocumentosProvinciales(Long idDocumentosProvinciales) {
        this.idDocumentosProvinciales = idDocumentosProvinciales;
    }

    public Long getIdPeriodoDocumentosProvinciales() {
        return idPeriodoDocumentosProvinciales;
    }

    public void setIdPeriodoDocumentosProvinciales(Long idPeriodoDocumentosProvinciales) {
        this.idPeriodoDocumentosProvinciales = idPeriodoDocumentosProvinciales;
    }

    public Boolean getHasDownloaded() {
        return hasDownloaded;
    }

    public void setHasDownloaded(Boolean hasDownloaded) {
        this.hasDownloaded = hasDownloaded;
    }
}
