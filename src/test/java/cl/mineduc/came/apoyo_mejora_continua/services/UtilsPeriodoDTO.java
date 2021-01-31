package cl.mineduc.came.apoyo_mejora_continua.services;

import java.util.Date;

import cl.mineduc.came.apoyo_mejora_continua.dto.period.PeriodoAsignacionSupervisoresDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.period.PeriodoConformacionRedesDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.period.PeriodoDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.period.PeriodoDocumentosProvincialesDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.period.PeriodoDocumentosRegionalesDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.period.PeriodoOrganizacionPlanificacionProvincialDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.period.PeriodoPlanificacionImplementacionApoyoDTO;

public class UtilsPeriodoDTO {
	
	public static final long DEFAULT_LONG = 1L;
	
	public static PeriodoDTO getPeriodoMock() {
		PeriodoDTO periodo = new PeriodoDTO();
		periodo.setAnio(2020);
		periodo.setIdUsuarioRegistrado(DEFAULT_LONG);
//		MockMultipartFile firstFile = new MockMultipartFile("data", "filename.txt", "text/plain", "some xml".getBytes());
		periodo.setDocumentoProvincial(null);
		periodo.setDocumentoRegional(null);
		periodo.setDocumentosProvinciales(getDocumentosProvincialesMock());
		periodo.setDocumentosRegionales(getDocumentosRegionalesMock());
		periodo.setPlanificacionesProvinciales(getPlanificacionesProvincialesMock());
		periodo.setImplementacionesApoyo(getImplementacionesApoyoMock());
		periodo.setConformacionRedes(getConformacionRedesMock());
		periodo.setAsignaciones(getAsignacionesMock());
		
		return periodo;
	}

	private static PeriodoAsignacionSupervisoresDTO getAsignacionesMock() {
		PeriodoAsignacionSupervisoresDTO dto = new PeriodoAsignacionSupervisoresDTO();
		dto.setFechaInicio(new Date());
		dto.setFechaFin(new Date());
		dto.setIdPeriodo(DEFAULT_LONG);
		return dto;
	}

	public static PeriodoConformacionRedesDTO getConformacionRedesMock() {
		PeriodoConformacionRedesDTO dto = new PeriodoConformacionRedesDTO();
		dto.setFechaInicio(new Date());
		dto.setFechaFin(new Date());
		dto.setIdPeriodo(DEFAULT_LONG);
		return dto;
	}

	public static PeriodoPlanificacionImplementacionApoyoDTO getImplementacionesApoyoMock() {
		PeriodoPlanificacionImplementacionApoyoDTO dto = new PeriodoPlanificacionImplementacionApoyoDTO();
		dto.setFechaInicio(new Date());
		dto.setFechaFin(new Date());
		dto.setIdPeriodo(DEFAULT_LONG);
		return dto;
	}

	public static PeriodoOrganizacionPlanificacionProvincialDTO getPlanificacionesProvincialesMock() {
		PeriodoOrganizacionPlanificacionProvincialDTO dto = new PeriodoOrganizacionPlanificacionProvincialDTO();
		dto.setIdPeriodo(DEFAULT_LONG);
		return dto;
	}

	public static PeriodoDocumentosRegionalesDTO getDocumentosRegionalesMock() {
		PeriodoDocumentosRegionalesDTO dto = new PeriodoDocumentosRegionalesDTO();
		dto.setFechaInicio(new Date());
		dto.setFechaFin(new Date());
		dto.setIdPeriodo(DEFAULT_LONG);
		return dto;
	}

	public static PeriodoDocumentosProvincialesDTO getDocumentosProvincialesMock() {
		PeriodoDocumentosProvincialesDTO documentosProvinciales = new PeriodoDocumentosProvincialesDTO();
		documentosProvinciales.setFechaInicio(new Date());
		documentosProvinciales.setFechaFin(new Date());
		documentosProvinciales.setIdPeriodo(DEFAULT_LONG);
		return documentosProvinciales;
	}
	
	

}
