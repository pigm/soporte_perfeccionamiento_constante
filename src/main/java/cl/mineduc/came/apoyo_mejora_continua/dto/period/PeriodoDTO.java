package cl.mineduc.came.apoyo_mejora_continua.dto.period;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class PeriodoDTO implements DTOEntity {

	private String idPeriodo;

	private Integer anio;

	private Long idUsuarioRegistrado;

	private PeriodoDocumentosProvincialesDTO documentosProvinciales;

	private  List<PeriodoDocumentosProvincialesDTO> documentosProvincialesEspeciales;

	private PeriodoDocumentosRegionalesDTO documentosRegionales;

	private List<PeriodoDocumentosRegionalesDTO> documentosRegionalesEspeciales;

	private RedDTO redes;

	private RedEstablecimientoDTO redEstablecimientos;

	private PeriodoOrganizacionPlanificacionProvincialDTO planificacionesProvinciales;

	private List<PeriodoOrganizacionPlanificacionProvincialDTO> planificacionesProvincialesEspeciales;

	private PeriodoPlanificacionImplementacionApoyoDTO implementacionesApoyo;

	private List<PeriodoPlanificacionImplementacionApoyoDTO> implementacionesApoyoEspeciales;

	private PeriodoConformacionRedesDTO conformacionRedes;

	private List<PeriodoConformacionRedesDTO> conformacionRedesEspeciales;

	private PeriodoAsignacionSupervisoresDTO asignaciones;

	private List<PeriodoAsignacionSupervisoresDTO> asignacionesEspeciales;

	private MultipartFile documentoRegional;

	private MultipartFile documentoProvincial;

	public String getIdPeriodo() {
		return idPeriodo;
	}

	public Integer getAnio() {
		return anio;
	}

	public Long getIdUsuarioRegistrado() {
		return idUsuarioRegistrado;
	}

	public PeriodoDocumentosProvincialesDTO getDocumentosProvinciales() {
		return documentosProvinciales;
	}

	public PeriodoDocumentosRegionalesDTO getDocumentosRegionales() {
		return documentosRegionales;
	}

	public RedDTO getRedes() {
		return redes;
	}

	public RedEstablecimientoDTO getRedEstablecimientos() {
		return redEstablecimientos;
	}

	public PeriodoOrganizacionPlanificacionProvincialDTO getPlanificacionesProvinciales() {
		return planificacionesProvinciales;
	}

	public PeriodoPlanificacionImplementacionApoyoDTO getImplementacionesApoyo() {
		return implementacionesApoyo;
	}

	public PeriodoConformacionRedesDTO getConformacionRedes() {
		return conformacionRedes;
	}

	public PeriodoAsignacionSupervisoresDTO getAsignaciones() {
		return asignaciones;
	}

	public MultipartFile getDocumentoRegional() {
		return documentoRegional;
	}

	public MultipartFile getDocumentoProvincial() {
		return documentoProvincial;
	}

	public void setIdPeriodo(String idPeriodo) {
		this.idPeriodo = idPeriodo;
	}

	public void setAnio(Integer anio) {
		this.anio = anio;
	}

	public void setIdUsuarioRegistrado(Long idUsuarioRegistrado) {
		this.idUsuarioRegistrado = idUsuarioRegistrado;
	}

	public void setDocumentosProvinciales(PeriodoDocumentosProvincialesDTO documentosProvinciales) {
		this.documentosProvinciales = documentosProvinciales;
	}

	public void setDocumentosRegionales(PeriodoDocumentosRegionalesDTO documentosRegionales) {
		this.documentosRegionales = documentosRegionales;
	}

	public void setRedes(RedDTO redes) {
		this.redes = redes;
	}

	public void setRedEstablecimientos(RedEstablecimientoDTO redEstablecimientos) {
		this.redEstablecimientos = redEstablecimientos;
	}

	public void setPlanificacionesProvinciales(
			PeriodoOrganizacionPlanificacionProvincialDTO planificacionesProvinciales) {
		this.planificacionesProvinciales = planificacionesProvinciales;
	}

	public void setImplementacionesApoyo(PeriodoPlanificacionImplementacionApoyoDTO implementacionesApoyo) {
		this.implementacionesApoyo = implementacionesApoyo;
	}

	public void setConformacionRedes(PeriodoConformacionRedesDTO conformacionRedes) {
		this.conformacionRedes = conformacionRedes;
	}

	public void setAsignaciones(PeriodoAsignacionSupervisoresDTO asignaciones) {
		this.asignaciones = asignaciones;
	}

	public void setDocumentoRegional(MultipartFile documentoRegional) {
		this.documentoRegional = documentoRegional;
	}

	public void setDocumentoProvincial(MultipartFile documentoProvincial) {
		this.documentoProvincial = documentoProvincial;
	}

	@Override
	public String toString() {
		return "PeriodoDTO [idPeriodo=" + idPeriodo + ", anio=" + anio + ", idUsuarioRegistrado=" + idUsuarioRegistrado
				+ ", documentosProvinciales=" + documentosProvinciales + ", documentosRegionales="
				+ documentosRegionales + ", redes=" + redes + ", redEstablecimientos=" + redEstablecimientos
				+ ", planificacionesProvinciales=" + planificacionesProvinciales + ", implementacionesApoyo="
				+ implementacionesApoyo + ", conformacionRedes=" + conformacionRedes + ", asignaciones=" + asignaciones
				+ ", documentoRegional=" + documentoRegional + ", documentoProvincial=" + documentoProvincial + "]";
	}

	public List<PeriodoDocumentosProvincialesDTO> getDocumentosProvincialesEspeciales() {
		return documentosProvincialesEspeciales;
	}

	public void setDocumentosProvincialesEspeciales(
			List<PeriodoDocumentosProvincialesDTO> documentosProvincialesEspeciales) {
		this.documentosProvincialesEspeciales = documentosProvincialesEspeciales;
	}

	public List<PeriodoDocumentosRegionalesDTO> getDocumentosRegionalesEspeciales() {
		return documentosRegionalesEspeciales;
	}

	public void setDocumentosRegionalesEspeciales(List<PeriodoDocumentosRegionalesDTO> documentosRegionalesEspeciales) {
		this.documentosRegionalesEspeciales = documentosRegionalesEspeciales;
	}

	public List<PeriodoOrganizacionPlanificacionProvincialDTO> getPlanificacionesProvincialesEspeciales() {
		return planificacionesProvincialesEspeciales;
	}

	public void setPlanificacionesProvincialesEspeciales(
			List<PeriodoOrganizacionPlanificacionProvincialDTO> planificacionesProvincialesEspeciales) {
		this.planificacionesProvincialesEspeciales = planificacionesProvincialesEspeciales;
	}

	public List<PeriodoPlanificacionImplementacionApoyoDTO> getImplementacionesApoyoEspeciales() {
		return implementacionesApoyoEspeciales;
	}

	public void setImplementacionesApoyoEspeciales(
			List<PeriodoPlanificacionImplementacionApoyoDTO> implementacionesApoyoEspeciales) {
		this.implementacionesApoyoEspeciales = implementacionesApoyoEspeciales;
	}

	public List<PeriodoConformacionRedesDTO> getConformacionRedesEspeciales() {
		return conformacionRedesEspeciales;
	}

	public void setConformacionRedesEspeciales(List<PeriodoConformacionRedesDTO> conformacionRedesEspeciales) {
		this.conformacionRedesEspeciales = conformacionRedesEspeciales;
	}

	public List<PeriodoAsignacionSupervisoresDTO> getAsignacionesEspeciales() {
		return asignacionesEspeciales;
	}

	public void setAsignacionesEspeciales(List<PeriodoAsignacionSupervisoresDTO> asignacionesEspeciales) {
		this.asignacionesEspeciales = asignacionesEspeciales;
	}

}
