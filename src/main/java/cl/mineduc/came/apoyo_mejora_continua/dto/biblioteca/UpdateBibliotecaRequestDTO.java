package cl.mineduc.came.apoyo_mejora_continua.dto.biblioteca;

import java.util.List;

public class UpdateBibliotecaRequestDTO {
	private String document;
	private String documentType;
    private String nombre;
    private Integer idRegion;
    private Integer idDeprov;
    private String categoria;
    private List<String> perfil;
    private String idBiblioteca;
    
	
	public String getDocument() {
		return document;
	}
	public void setDocument(String document) {
		this.document = document;
	}
	public String getDocumentType() {
		return documentType;
	}
	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public Integer getIdRegion() {
		return idRegion;
	}
	public void setIdRegion(Integer idRegion) {
		this.idRegion = idRegion;
	}
	
	public Integer getIdDeprov() {
		return idDeprov;
	}
	public void setIdDeprov(Integer idDeprov) {
		this.idDeprov = idDeprov;
	}
	
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String idCategoria) {
		this.categoria = idCategoria;
	}
	
	public List<String> getPerfil() {
		return perfil;
	}
	public void setPerfil(List<String> perfil) {
		this.perfil = perfil;
	}
	
	public String getIdBiblioteca() {
		return idBiblioteca;
	}
	public void setIdBiblioteca(String idBiblioteca) {
		this.idBiblioteca = idBiblioteca;
	}
}
