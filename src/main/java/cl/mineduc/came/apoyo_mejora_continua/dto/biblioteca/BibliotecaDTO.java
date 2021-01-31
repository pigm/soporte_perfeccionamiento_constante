package cl.mineduc.came.apoyo_mejora_continua.dto.biblioteca;

import java.util.Date;

public class BibliotecaDTO {
	private String nombreDocumento;
    private Integer categoriaId;
    private Integer regionId;
    private Integer deprov;
    private Integer perfilId;
    private String path;
    private Date uploadDate;
    
	public String getNombreDocumento() {
		return nombreDocumento;
	}
	public void setNombreDocumento(String nombreDocumento) {
		this.nombreDocumento = nombreDocumento;
	}
	
	public Integer getCategoriaId() {
		return categoriaId;
	}
	public void setCategoriaId(Integer categoriaId) {
		this.categoriaId = categoriaId;
	}
	
	public Integer getRegionId() {
		return regionId;
	}
	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}
	
	public Integer getDeprov() {
		return deprov;
	}
	public void setDeprov(Integer deprov) {
		this.deprov = deprov;
	}
	
	public Integer getPerfilId() {
		return perfilId;
	}
	public void setPerfilId(Integer perfilId) {
		this.perfilId = perfilId;
	}
	
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	public Date getUploadDate() {
		return uploadDate;
	}
	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}
    
}