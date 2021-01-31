package cl.mineduc.came.apoyo_mejora_continua.dto.report.directory;

public class DirectoryRequestExcelDTO {
	private String rbd;    
    private String nombre;
    private String ruralidad;
    private String categorizacion;
    private String clasificacionSEP;
    private String dependencia;
    private String estado;
    private String matriculasTotal;
    private String tipoDeApoyo;
    private String supervisor;
    private Integer row;
    
	public String getRbd() {
		return rbd;
	}
	public void setRbd(String rbd) {
		this.rbd = rbd;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getRuralidad() {
		return ruralidad;
	}
	public void setRuralidad(String ruralidad) {
		this.ruralidad = ruralidad;
	}
	public String getCategorizacion() {
		return categorizacion;
	}
	public void setCategorizacion(String categorizacion) {
		this.categorizacion = categorizacion;
	}
	public String getClasificacionSEP() {
		return clasificacionSEP;
	}
	public void setClasificacionSEP(String clasificacionSEP) {
		this.clasificacionSEP = clasificacionSEP;
	}
	public String getDependencia() {
		return dependencia;
	}
	public void setDependencia(String dependencia) {
		this.dependencia = dependencia;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getMatriculasTotal() {
		return matriculasTotal;
	}
	public void setMatriculasTotal(String matriculasTotal) {
		this.matriculasTotal = matriculasTotal;
	}
	public String getTipoDeApoyo() {
		return tipoDeApoyo;
	}
	public void setTipoDeApoyo(String tipoDeApoyo) {
		this.tipoDeApoyo = tipoDeApoyo;
	}
	public String getSupervisor() {
		return supervisor;
	}
	public void setSupervisor(String supervisor) {
		this.supervisor = supervisor;
	}
	
	public Integer getRow() {
		return row;
	}
	public void setRow(Integer row) {
		this.row = row;
	}
    
    
}
