package cl.mineduc.came.apoyo_mejora_continua.dto.biblioteca;

import java.util.List;

import cl.mineduc.came.apoyo_mejora_continua.dto.documents.DocumentsViewDTO;

public class BibliotecaListDTO {
    private String nombreDocumento;
	private String categoria;
	private String idCategoria;
    private String perfil;
	private String fechaPublicacion;
	private String idPerfilBiblioteca;
	private String idBiblioteca;
	private String templatePath;
	private Integer idRegion;
	private String region;
	private Integer idDeprov;
	private String deprov;
    
	public String getTemplatePath() {
		return templatePath;
	}
	public void setTemplatePath(String templatePath) {
		this.templatePath = templatePath;
	}
	public String getNombreDocumento() {
		return nombreDocumento;
	}
	public void setNombreDocumento(String nombreDocumento) {
		this.nombreDocumento = nombreDocumento;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getPerfil() {
		return perfil;
	}
	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}
	public String getFechaPublicacion() {
		return fechaPublicacion;
	}
	public void setFechaPublicacion(String fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}

	public String getIdPerfilBiblioteca() {
		return idPerfilBiblioteca;
	}

	public void setIdPerfilBiblioteca(String idPerfilBiblioteca) {
		this.idPerfilBiblioteca = idPerfilBiblioteca;
	}
	public String getIdBiblioteca() {
		return idBiblioteca;
	}
	public void setIdBiblioteca(String idBiblioteca) {
		this.idBiblioteca = idBiblioteca;
	}

	public String getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(String idCategoria) {
		this.idCategoria = idCategoria;
	}

	public Integer getIdRegion() {
		return idRegion;
	}

	public void setIdRegion(Integer idRegion) {
		this.idRegion = idRegion;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public Integer getIdDeprov() {
		return idDeprov;
	}

	public void setIdDeprov(Integer idDeprov) {
		this.idDeprov = idDeprov;
	}

	public String getDeprov() {
		return deprov;
	}

	public void setDeprov(String deprov) {
		this.deprov = deprov;
	}
    
}