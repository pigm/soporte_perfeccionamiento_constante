package cl.mineduc.came.apoyo_mejora_continua.modelo;

import java.util.Date;

public class DocumentosRegionales {
    private Long idDocumentosRegionales; 
    private long idPeriodo;
    private Long idPeriodoDocumentosRegionales;
    private Date fechaRegistro;
    private Long idUsuarioRegistro; 
    private String filePath;
    private String fileName; 
    private Long idRegion;
    private String typeDocument;
    private Boolean hasDownloaded;

    public Long getIdDocumentosRegionales() {
        return idDocumentosRegionales;
    }

    public void setIdDocumentosRegionales(Long idDocumentosRegionales) {
        this.idDocumentosRegionales = idDocumentosRegionales;
    }

    

    public Long getIdPeriodoDocumentosRegionales() {
        return idPeriodoDocumentosRegionales;
    }

    public void setIdPeriodoDocumentosRegionales(Long idPeriodoDocumentosRegionales) {
        this.idPeriodoDocumentosRegionales = idPeriodoDocumentosRegionales;
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

    public long getIdPeriodo() {
        return idPeriodo;
    }

    public void setIdPeriodo(long idPeriodo) {
        this.idPeriodo = idPeriodo;
    }

    public Boolean getHasDownloaded() {
        return hasDownloaded;
    }

    public void setHasDownloaded(Boolean hasDownloaded) {
        this.hasDownloaded = hasDownloaded;
    }
}
