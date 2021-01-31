package cl.mineduc.came.apoyo_mejora_continua.dto.biblioteca;

import java.util.Date;

public class BibliotecaRequestDTO {
    private int anio;
    private String categoriaId;
    private String perfilId;    
    private String fechaCreacion;
    private String nombreDocumento;
    private String regionId;
    private String deprov;
    
    
	public int getAnio() {
		return anio;
	}
	public void setAnio(int anio) {
		this.anio = anio;
	}
	
	public String getCategoriaId() {
		return categoriaId;
	}
	public void setCategoriaId(String categoriaId) {
		this.categoriaId = categoriaId;
	}
	
	public String getPerfilId() {
		return perfilId;
	}
	public void setPerfilId(String perfilId) {
		this.perfilId = perfilId;
	}
	
	public String getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	
	public String getNombreDocumento() {
		return nombreDocumento;
	}
	public void setNombreDocumento(String nombreDocumento) {
		this.nombreDocumento = nombreDocumento;
	}	
	
	public String getRegionId() {
		return regionId;
	}
	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}
	
	public String getDeprov() {
		return deprov;
	}
	public void setDeprov(String deprov) {
		this.deprov = deprov;
	}
	
	
}
