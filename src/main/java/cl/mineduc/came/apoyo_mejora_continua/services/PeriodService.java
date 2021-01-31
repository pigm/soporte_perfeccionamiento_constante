package cl.mineduc.came.apoyo_mejora_continua.services;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;
import org.springframework.web.multipart.MultipartFile;

import cl.mineduc.came.apoyo_mejora_continua.dto.integration.region.DeprovsResponse;
import cl.mineduc.came.apoyo_mejora_continua.dto.integration.region.RegionResponseDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.period.DTOEntity;
import cl.mineduc.came.apoyo_mejora_continua.dto.period.DeprovDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.period.PeriodoAsignacionSupervisoresDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.period.PeriodoConformacionRedesDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.period.PeriodoDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.period.PeriodoDocumentosProvincialesDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.period.PeriodoDocumentosRegionalesDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.period.PeriodoPlanificacionImplementacionApoyoDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.period.SpecialCaseDTO;
import cl.mineduc.came.apoyo_mejora_continua.enums.SpecialCase;
import cl.mineduc.came.apoyo_mejora_continua.helpers.CustomMultipartFile;
import cl.mineduc.came.apoyo_mejora_continua.helpers.UserHelper;
import cl.mineduc.came.apoyo_mejora_continua.helpers.Util;
import cl.mineduc.came.apoyo_mejora_continua.integration.RegionIntegration;
import cl.mineduc.came.apoyo_mejora_continua.modelo.Periodo;
import cl.mineduc.came.apoyo_mejora_continua.modelo.PeriodoAsignacionSupervisores;
import cl.mineduc.came.apoyo_mejora_continua.modelo.PeriodoConformacionRedes;
import cl.mineduc.came.apoyo_mejora_continua.modelo.PeriodoDocumentosProvinciales;
import cl.mineduc.came.apoyo_mejora_continua.modelo.PeriodoDocumentosRegionales;
// import cl.mineduc.came.apoyo_mejora_continua.modelo.PeriodoOrganizacionPlanificacionProvincial;
import cl.mineduc.came.apoyo_mejora_continua.modelo.PeriodoPlanificacionImplementacionApoyo;
import cl.mineduc.came.apoyo_mejora_continua.repo.PeriodoAsignacionSupervisoresRepo;
import cl.mineduc.came.apoyo_mejora_continua.repo.PeriodoConformacionRedesRepo;
import cl.mineduc.came.apoyo_mejora_continua.repo.PeriodoDocumentosProvincialesRepo;
import cl.mineduc.came.apoyo_mejora_continua.repo.PeriodoDocumentosRegionalesRepo;
// import cl.mineduc.came.apoyo_mejora_continua.repo.PeriodoOrganizacionPlanificacionProvincialRepo;
import cl.mineduc.came.apoyo_mejora_continua.repo.PeriodoPlanificacionImplementacionApoyoRepo;
import cl.mineduc.came.apoyo_mejora_continua.repo.PeriodoRepo;
import cl.mineduc.framework2.exceptions.MineducException;

@Service
public class PeriodService {
	public static final String FILE_SEPARATOR = "/";

	public static final String PERIODO = "periodo";

	public static final String CASO_ESPECIAL = "especial";

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private PeriodoRepo periodoRepo;

	@Autowired
	private PeriodoDocumentosProvincialesRepo provincialesRepo;

	@Autowired
	private PeriodoDocumentosRegionalesRepo regionalesRepo;

	@Autowired
	RegionIntegration regionIntegration;

	// @Autowired
	// private RedRepo redRepo;
	//
	// @Autowired
	// private RedEstablecimientoRepo establecimientoRepo;

	// @Autowired
	// private PeriodoOrganizacionPlanificacionProvincialRepo provincialRepo;

	@Autowired
	private PeriodoPlanificacionImplementacionApoyoRepo apoyoRepo;

	@Autowired
	private PeriodoAsignacionSupervisoresRepo supervisorRepo;

	@Autowired
	private PeriodoConformacionRedesRepo conformacionRedesRepo;

	@Autowired
	private UsuarioService userService;

	public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
		Set<Object> seen = ConcurrentHashMap.newKeySet();
		return t -> seen.add(keyExtractor.apply(t));
	}

	public DTOEntity convertToDto(Object obj, DTOEntity mapper) {
		return modelMapper.map(obj, mapper.getClass());
	}

	// method private
	public Object convertToEntity(Object obj, DTOEntity mapper) {
		return modelMapper.map(mapper, obj.getClass());
	}

	public Boolean existsPeriod(int period) {
		Periodo periodo = periodoRepo.getPeriodByYear(period);
		return periodo != null;
	}

	private PeriodoDTO periodoDTOFromModel(Periodo periodo) {
		PeriodoDTO dto = new PeriodoDTO();
		dto.setIdPeriodo(periodo.getIdPeriodo().toString());
		dto.setAnio(periodo.getAnio());
		dto.setIdUsuarioRegistrado(periodo.getIdUsuarioRegistro());
		// caso no especial
		dto.setDocumentosProvinciales(documentoProvincialNoEspecial(periodo.getDocumentosProvinciales()));
		dto.setDocumentosRegionales(documentosRegionalesNoEspecial(periodo.getDocumentosRegionales()));
		dto.setImplementacionesApoyo(implementacionesApoyoNoEspcial(periodo.getImplementacionesApoyo()));
		dto.setConformacionRedes(conformacionRedesNoSpecial(periodo.getConformacionRedes()));
		dto.setAsignaciones(asignacionesNoEspecial(periodo.getAsignaciones()));
		// caso especial
		dto.setDocumentosProvincialesEspeciales(documentosProvincialEspecial(periodo.getDocumentosProvinciales()));
		dto.setDocumentosRegionalesEspeciales(documentosRegionalesEspecial(periodo.getDocumentosRegionales()));
		dto.setImplementacionesApoyoEspeciales(implementacionesApoyoEspcial(periodo.getImplementacionesApoyo()));
		dto.setConformacionRedesEspeciales(conformacionRedesSpecial(periodo.getConformacionRedes()));
		dto.setAsignacionesEspeciales(asignacionesEspecial(periodo.getAsignaciones()));

		return dto;
	}

	private PeriodoAsignacionSupervisoresDTO asignacionesNoEspecial(List<PeriodoAsignacionSupervisores> asignaciones) {
		if (!asignaciones.isEmpty()) {
			PeriodoAsignacionSupervisores as = asignaciones.stream()
					.filter(d -> d.getEsCasoEspecial() == null || !d.getEsCasoEspecial()).iterator().next();
			return (PeriodoAsignacionSupervisoresDTO) convertToDto(as, new PeriodoAsignacionSupervisoresDTO());
		} else {
			return null;
		}
	}

	private List<PeriodoAsignacionSupervisoresDTO> asignacionesEspecial(
			List<PeriodoAsignacionSupervisores> asignaciones) {
		List<PeriodoAsignacionSupervisoresDTO> result = new ArrayList<PeriodoAsignacionSupervisoresDTO>();
		if (!asignaciones.isEmpty()) {
			List<RegionResponseDTO> regiones = this.regionIntegration.getRegiones();
			List<PeriodoAsignacionSupervisores> as = asignaciones.stream()
					.filter(d -> d.getEsCasoEspecial() != null && d.getEsCasoEspecial() && d.getIdDeprov() != null).collect(Collectors.toList());
			as.stream().collect(Collectors.groupingBy(PeriodoAsignacionSupervisores::getIdDeprov))
					.forEach((key, values) -> {
						Optional<PeriodoAsignacionSupervisores> paso = values.stream()
								.sorted(Comparator
										.comparingLong(
												PeriodoAsignacionSupervisores::getIdPeriodoAsignacionSupervisores)
										.reversed())
								.findFirst();
						if (paso.isPresent()) {
							PeriodoAsignacionSupervisores d = paso.get();
							PeriodoAsignacionSupervisoresDTO per = (PeriodoAsignacionSupervisoresDTO) convertToDto(d,
									new PeriodoAsignacionSupervisoresDTO());
							RegionResponseDTO reg = regiones.stream()
									.filter(r -> d.getIdRegion().equals((long) r.getIdRegion())).iterator().next();
							per.setRegion(reg.getNombreRegion());
							DeprovsResponse devp = reg.getDeprovs().stream()
									.filter(r -> d.getIdDeprov().equals((long) r.getIdDeprov())).iterator().next();
							per.setDeprov(devp.getNombreDeprov());
							per.setMotivo(d.getDescripcion());
							per.setUsuarioRegistro(userService.getNameUserById(d.getIdUsuarioRegistro()));
							result.add(per);
						}
					});
		}
		return result;
	}

	private PeriodoConformacionRedesDTO conformacionRedesNoSpecial(List<PeriodoConformacionRedes> conformacionRedes) {
		if (!conformacionRedes.isEmpty()) {
			PeriodoConformacionRedes redes = conformacionRedes.stream()
					.filter(d -> d.getEsCasoEspecial() == null || !d.getEsCasoEspecial()).iterator().next();
			return (PeriodoConformacionRedesDTO) convertToDto(redes, new PeriodoConformacionRedesDTO());
		} else {
			return null;
		}
	}

	private List<PeriodoConformacionRedesDTO> conformacionRedesSpecial(
			List<PeriodoConformacionRedes> conformacionRedes) {
		List<PeriodoConformacionRedesDTO> result = new ArrayList<PeriodoConformacionRedesDTO>();
		if (!conformacionRedes.isEmpty()) {
			List<RegionResponseDTO> regiones = this.regionIntegration.getRegiones();
			List<PeriodoConformacionRedes> redes = conformacionRedes.stream()
					.filter(d -> d.getEsCasoEspecial() != null && d.getEsCasoEspecial()).collect(Collectors.toList());
			redes.stream().collect(Collectors.groupingBy(PeriodoConformacionRedes::getIdDeprov))
					.forEach((key, values) -> {
						Optional<PeriodoConformacionRedes> paso = values.stream().sorted(Comparator
								.comparingLong(PeriodoConformacionRedes::getIdPeriodoConformacionRedes).reversed())
								.findFirst();
						if (paso.isPresent()) {
							PeriodoConformacionRedes d = paso.get();
							PeriodoConformacionRedesDTO per = (PeriodoConformacionRedesDTO) convertToDto(d,
									new PeriodoConformacionRedesDTO());
							if (d.getIdRegion() != null) {
								RegionResponseDTO reg = regiones.stream()
										.filter(r -> d.getIdRegion().equals((long) r.getIdRegion())).iterator().next();
								per.setRegion(reg.getNombreRegion());
								DeprovsResponse devp = reg.getDeprovs().stream()
										.filter(r -> d.getIdDeprov().equals((long) r.getIdDeprov())).iterator().next();
								per.setDeprov(devp.getNombreDeprov());
							}

							per.setMotivo(d.getDescripcion());
							per.setUsuarioRegistro(userService.getNameUserById(d.getIdUsuarioRegistro()));
							result.add(per);
						}
					});
		}
		return result;
	}

	private PeriodoPlanificacionImplementacionApoyoDTO implementacionesApoyoNoEspcial(
			List<PeriodoPlanificacionImplementacionApoyo> implementacionesApoyo) {
		if (!implementacionesApoyo.isEmpty()) {
			PeriodoPlanificacionImplementacionApoyo apoyo = implementacionesApoyo.stream()
					.filter(d -> d.getEsCasoEspecial() == null || !d.getEsCasoEspecial()).iterator().next();
			return (PeriodoPlanificacionImplementacionApoyoDTO) convertToDto(apoyo,
					new PeriodoPlanificacionImplementacionApoyoDTO());
		} else {
			return null;
		}
	}

	private List<PeriodoPlanificacionImplementacionApoyoDTO> implementacionesApoyoEspcial(
			List<PeriodoPlanificacionImplementacionApoyo> implementacionesApoyo) {
		List<PeriodoPlanificacionImplementacionApoyoDTO> result = new ArrayList<PeriodoPlanificacionImplementacionApoyoDTO>();
		if (!implementacionesApoyo.isEmpty()) {
			List<RegionResponseDTO> regiones = this.regionIntegration.getRegiones();
			List<PeriodoPlanificacionImplementacionApoyo> apoyos = implementacionesApoyo.stream()
					.filter(d -> d.getEsCasoEspecial() != null && d.getEsCasoEspecial()).collect(Collectors.toList());
			apoyos.stream().collect(Collectors.groupingBy(PeriodoPlanificacionImplementacionApoyo::getIdDeprov))
					.forEach((key, values) -> {
						Optional<PeriodoPlanificacionImplementacionApoyo> paso = values.stream()
								.sorted(Comparator.comparingLong(
										PeriodoPlanificacionImplementacionApoyo::getIdPeriodoPlanificacionImplementacionApoyo)
										.reversed())
								.findFirst();
						if (paso.isPresent()) {
							PeriodoPlanificacionImplementacionApoyo d = paso.get();
							PeriodoPlanificacionImplementacionApoyoDTO per = (PeriodoPlanificacionImplementacionApoyoDTO) convertToDto(
									d, new PeriodoPlanificacionImplementacionApoyoDTO());
							RegionResponseDTO reg = regiones.stream()
									.filter(r -> d.getIdRegion().equals((long) r.getIdRegion())).iterator().next();
							per.setRegion(reg.getNombreRegion());
							DeprovsResponse devp = reg.getDeprovs().stream()
									.filter(r -> d.getIdDeprov().equals((long) r.getIdDeprov())).iterator().next();
							per.setDeprov(devp.getNombreDeprov());
							per.setMotivo(d.getDescripcion());
							per.setUsuarioRegistro(userService.getNameUserById(d.getIdUsuarioRegistro()));
							result.add(per);
						}
					});
		}
		return result;
	}

	private PeriodoDocumentosRegionalesDTO documentosRegionalesNoEspecial(
			List<PeriodoDocumentosRegionales> documentosRegionales) {
		if (!documentosRegionales.isEmpty()) {
			PeriodoDocumentosRegionales regi = documentosRegionales.stream()
					.filter(d -> d.getEsCasoEspecial() == null || !d.getEsCasoEspecial()).iterator().next();
			return (PeriodoDocumentosRegionalesDTO) convertToDto(regi, new PeriodoDocumentosRegionalesDTO());
		} else {
			return null;
		}
	}

	private List<PeriodoDocumentosRegionalesDTO> documentosRegionalesEspecial(
			List<PeriodoDocumentosRegionales> documentosRegionales) {
		List<PeriodoDocumentosRegionalesDTO> result = new ArrayList<PeriodoDocumentosRegionalesDTO>();
		if (!documentosRegionales.isEmpty()) {
			List<RegionResponseDTO> regiones = this.regionIntegration.getRegiones();
			List<PeriodoDocumentosRegionales> regi = documentosRegionales.stream()
					.filter(d -> d.getEsCasoEspecial() != null && d.getEsCasoEspecial()).collect(Collectors.toList());
			regi.stream().collect(Collectors.groupingBy(PeriodoDocumentosRegionales::getIdRegion))
					.forEach((key, values) -> {
						Optional<PeriodoDocumentosRegionales> paso = values.stream()
								.sorted(Comparator
										.comparingLong(PeriodoDocumentosRegionales::getIdPeriodoDocumentosRegionales)
										.reversed())
								.findFirst();
						if (paso.isPresent()) {
							PeriodoDocumentosRegionales d = paso.get();
							PeriodoDocumentosRegionalesDTO dtoPaso = (PeriodoDocumentosRegionalesDTO) convertToDto(d,
									new PeriodoDocumentosRegionalesDTO());
							RegionResponseDTO reg = regiones.stream()
									.filter(r -> d.getIdRegion().equals((long) r.getIdRegion())).iterator().next();
							dtoPaso.setRegion(reg.getNombreRegion());
							Optional<DeprovsResponse> devp = reg.getDeprovs().stream().filter(
									r -> d.getIdDeprov() != null && d.getIdDeprov().equals((long) r.getIdDeprov()))
									.findAny();
							if (devp.isPresent()) {
								dtoPaso.setDeprov(devp.get().getNombreDeprov());
							}
							dtoPaso.setMotivo(d.getDescripcion());
							dtoPaso.setUsuarioRegistro(userService.getNameUserById(d.getIdUsuarioRegistro()));
							result.add(dtoPaso);
						}
					});
		}
		return result;
	}

	private PeriodoDocumentosProvincialesDTO documentoProvincialNoEspecial(
			List<PeriodoDocumentosProvinciales> documentosProvinciales) {
		if (!documentosProvinciales.isEmpty()) {
			PeriodoDocumentosProvinciales docProvincial = documentosProvinciales.stream()
					.filter(d -> d.getEsCasoEspecial() == null || !d.getEsCasoEspecial()).iterator().next();
			return (PeriodoDocumentosProvincialesDTO) convertToDto(docProvincial,
					new PeriodoDocumentosProvincialesDTO());
		} else {
			return null;
		}
	}

	private List<PeriodoDocumentosProvincialesDTO> documentosProvincialEspecial(
			List<PeriodoDocumentosProvinciales> documentosProvinciales) {
		List<PeriodoDocumentosProvincialesDTO> result = new ArrayList<PeriodoDocumentosProvincialesDTO>();
		if (!documentosProvinciales.isEmpty()) {
			List<RegionResponseDTO> regiones = this.regionIntegration.getRegiones();
			List<PeriodoDocumentosProvinciales> docProvincial = documentosProvinciales.stream()
					.filter(d -> d.getEsCasoEspecial() != null && d.getEsCasoEspecial()).collect(Collectors.toList());
			docProvincial.stream().collect(Collectors.groupingBy(PeriodoDocumentosProvinciales::getIdDeprov))
					.forEach((key, values) -> {
						Optional<PeriodoDocumentosProvinciales> paso = values.stream()
								.sorted(Comparator
										.comparingLong(
												PeriodoDocumentosProvinciales::getIdPeriodoDocumentosProvinciales)
										.reversed())
								.findFirst();
						if (paso.isPresent()) {
							PeriodoDocumentosProvinciales d = paso.get();
							PeriodoDocumentosProvincialesDTO per = (PeriodoDocumentosProvincialesDTO) convertToDto(d,
									new PeriodoDocumentosProvincialesDTO());
							RegionResponseDTO reg = regiones.stream()
									.filter(r -> d.getIdRegion().equals((long) r.getIdRegion())).iterator().next();
							per.setRegion(reg.getNombreRegion());
							DeprovsResponse devp = reg.getDeprovs().stream()
									.filter(r -> d.getIdDeprov().equals((long) r.getIdDeprov())).iterator().next();
							per.setDeprov(devp.getNombreDeprov());
							per.setMotivo(d.getDescripcion());
							per.setUsuarioRegistro(userService.getNameUserById(d.getIdUsuarioRegistro()));
							result.add(per);
						}
					});
		}
		return result;
	}

	// #endregion
	/**
	 * Obtiene periodo por año y casos NO especiales
	 * 
	 * @param period
	 * @return
	 */
	public PeriodoDTO getPeriodByYear(int period) {
		Periodo periodo = periodoRepo.getPeriodByYear(period);

		return periodoDTOFromModel(periodo);
	}

	public void setRegionalDocuments(PeriodoDocumentosRegionales regional) {
		if (regional.getIdPeriodoDocumentosRegionales() != null) {
			regionalesRepo.updateDocumentsRegional(regional);
		} else {
			regionalesRepo.insertDocumentsregional(regional);
		}
	}

	public void setProvincialDocument(PeriodoDocumentosProvinciales provincial) {
		if (provincial.getIdPeriodoDocumentosProvinciales() != null) {
			provincialesRepo.updateDocumentsProvincials(provincial);
		} else {
			provincialesRepo.insertDocumentsProvincials(provincial);
		}
	}

	// public void
	// setOrganizationPlanning(PeriodoOrganizacionPlanificacionProvincial
	// planificacion) {
	// if (planificacion.getIdPeriodoOrganizacionPlanificacionProvincial() != null)
	// {
	// provincialRepo.updateProvincialPlanification(planificacion);
	// } else {
	// provincialRepo.insertProvincialPlanification(planificacion);
	// }
	// }

	public void setPlanningImplementation(PeriodoPlanificacionImplementacionApoyo implementation) {
		if (implementation.getIdPeriodoPlanificacionImplementacionApoyo() != null) {
			apoyoRepo.updateSupportPlanification(implementation);
		} else {
			apoyoRepo.insertProvincialPlanification(implementation);
		}
	}

	public void setSupervisorAssignment(PeriodoAsignacionSupervisores supervisor) {
		if (supervisor.getIdPeriodoAsignacionSupervisores() != null) {
			supervisorRepo.updateDocumentsProvincials(supervisor);
		} else {
			supervisorRepo.insertAssignmentSupervisor(supervisor);
		}
	}

	public void setNetworksConformation(PeriodoConformacionRedes networks) {
		if (networks.getIdPeriodoConformacionRedes() != null) {
			conformacionRedesRepo.updateNetworkConformation(networks);
		} else {
			conformacionRedesRepo.insertNetworkConformation(networks);
		}
	}

	@Transactional(rollbackFor = Throwable.class)
	public void setPeriod(PeriodoDTO periodDTO) {
		try {
			Periodo period = (Periodo) convertToEntity(new Periodo.Builder().build(), periodDTO);
			Long idUsuarioRegistrado = UserHelper.getUsuarioRegistrado().getIdUsuario();
			period.setIdUsuarioRegistro(idUsuarioRegistrado);
			Periodo p = periodoRepo.getPeriodByYear(periodDTO.getAnio());
			if (p == null) {
				periodoRepo.insertPeriod(period);

				String ruta = PERIODO + FILE_SEPARATOR + period.getIdPeriodo() + FILE_SEPARATOR;

				PeriodoDocumentosProvinciales documentosProvinciales = (PeriodoDocumentosProvinciales) convertToEntity(
						new PeriodoDocumentosProvinciales.BuilderDocuments()
								.withIdUserRegistry(period.getIdUsuarioRegistro()).withIdPeriod(period.getIdPeriodo())
								.build(),
						periodDTO.getDocumentosProvinciales());
				if (periodDTO.getDocumentosProvinciales().getProvincialDocument() != null
						&& !periodDTO.getDocumentosProvinciales().getProvincialDocument().isEmpty()) {
					MultipartFile fileProv = new CustomMultipartFile(
							Base64Utils.decodeFromString(periodDTO.getDocumentosProvinciales().getProvincialDocument()),
							periodDTO.getDocumentosProvinciales().getProvincialDocumentName());
					periodDTO.setDocumentoProvincial(fileProv);

					if (periodDTO.getDocumentoProvincial() != null && !periodDTO.getDocumentoProvincial().isEmpty()) {
						Util.FileToSave documentoProvincial = new Util.FileToSave();
						documentoProvincial.setFile(periodDTO.getDocumentoProvincial());
						documentosProvinciales.setTemplatePath(ruta + documentoProvincial.getOriginalFilename());
						Util.guardarArchivoEnDirectorio(PERIODO, Long.toString(period.getIdPeriodo()),
								documentoProvincial);
					}
				}

				documentosProvinciales.setIdPeriodo(period.getIdPeriodo());
				documentosProvinciales.setIdUsuarioRegistro(idUsuarioRegistrado);
				setProvincialDocument(documentosProvinciales);

				PeriodoDocumentosRegionales regional = (PeriodoDocumentosRegionales) convertToEntity(
						new PeriodoDocumentosRegionales.BuilderRegionals().withIdPeriod(period.getIdPeriodo())
								.withIdUserRegistry(period.getIdUsuarioRegistro()).build(),
						periodDTO.getDocumentosRegionales());
				if (periodDTO.getDocumentosRegionales().getRegionalDocument() != null
						&& !periodDTO.getDocumentosRegionales().getRegionalDocument().isEmpty()) {
					MultipartFile fileOne = new CustomMultipartFile(
							Base64Utils.decodeFromString(periodDTO.getDocumentosRegionales().getRegionalDocument()),
							periodDTO.getDocumentosRegionales().getRegionalDocumentName());
					periodDTO.setDocumentoRegional(fileOne);

					if (periodDTO.getDocumentoRegional() != null && !periodDTO.getDocumentoRegional().isEmpty()) {
						Util.FileToSave documentoRegional = new Util.FileToSave();
						documentoRegional.setFile(periodDTO.getDocumentoRegional());
						regional.setTemplatePath(ruta + documentoRegional.getOriginalFilename());
						Util.guardarArchivoEnDirectorio(PERIODO, Long.toString(period.getIdPeriodo()),
								documentoRegional);
					}
				}

				regional.setIdPeriodo(period.getIdPeriodo());
				regional.setIdUsuarioRegistro(idUsuarioRegistrado);
				setRegionalDocuments(regional);

				// PeriodoOrganizacionPlanificacionProvincial planificacionProvincial =
				// (PeriodoOrganizacionPlanificacionProvincial) convertToEntity(
				// new PeriodoOrganizacionPlanificacionProvincial.BuilderProvincial()
				// .withIdPeriod(period.getIdPeriodo())
				// .build(), periodDTO.getPlanificacionesProvinciales());
				// setOrganizationPlanning(planificacionProvincial);

				PeriodoPlanificacionImplementacionApoyo implementacionApoyo = (PeriodoPlanificacionImplementacionApoyo) convertToEntity(
						new PeriodoPlanificacionImplementacionApoyo.BuilderApoyo().withIdPeriod(period.getIdPeriodo())
								.withIdUserRegistry(period.getIdUsuarioRegistro()).build(),
						periodDTO.getImplementacionesApoyo());

				implementacionApoyo.setIdPeriodo(period.getIdPeriodo());
				implementacionApoyo.setIdUsuarioRegistro(idUsuarioRegistrado);
				setPlanningImplementation(implementacionApoyo);

				PeriodoConformacionRedes conformacion = (PeriodoConformacionRedes) convertToEntity(
						new PeriodoConformacionRedes.BuilderConformation().withIdPeriod(period.getIdPeriodo())
								.withIdUserRegistry(period.getIdUsuarioRegistro()).build(),
						periodDTO.getConformacionRedes());

				conformacion.setIdPeriodo(period.getIdPeriodo());
				conformacion.setIdUsuarioRegistro(idUsuarioRegistrado);
				setNetworksConformation(conformacion);

				PeriodoAsignacionSupervisores asignaciones = (PeriodoAsignacionSupervisores) convertToEntity(
						new PeriodoAsignacionSupervisores.BuilderAssign().withIdPeriod(period.getIdPeriodo())
								.withIdUserRegistry(period.getIdUsuarioRegistro()).build(),
						periodDTO.getAsignaciones());

				asignaciones.setIdPeriodo(period.getIdPeriodo());
				asignaciones.setIdUsuarioRegistro(idUsuarioRegistrado);
				setSupervisorAssignment(asignaciones);
			}

		} catch (MineducException e) {
			throw new MineducException("Error al insertar periodo", e);
		}
	}

	public void setSpecialCase(SpecialCaseDTO dto) {
		SpecialCase s = SpecialCase.values()[dto.getModuleId()];
		Long idUsuarioRegistrado = UserHelper.getUsuarioRegistrado().getIdUsuario();
		Periodo per = periodoRepo.getPeriodByYear(dto.getPeriodo());
		String ruta = CASO_ESPECIAL + FILE_SEPARATOR + per.getIdPeriodo() + FILE_SEPARATOR;

		dto.getDeprovId().forEach(dev -> {
			MultipartFile fileOne = null;
			switch (s) {
				case REGIONAL:
					PeriodoDocumentosRegionales regional = new PeriodoDocumentosRegionales.BuilderRegionals()
							.withIdPeriod(per.getIdPeriodo()).withIdRegion(dto.getRegionId()).withDeprov(dev)
							.withSpecialCase(true).withStartDate(dto.getStartDate()).withEndDate(dto.getEndDate())
							.withIdUserRegistry(idUsuarioRegistrado).withTemplatePath(dto.getTemplateFileName())
							.withDescription(dto.getObservation()).build();
					if (dto.getTemplate() != null && !dto.getTemplate().isEmpty()) {
						fileOne = new CustomMultipartFile(Base64Utils.decodeFromString(dto.getTemplate()),
								dto.getTemplateFileName());
						if (fileOne != null && !fileOne.isEmpty()) {
							Util.FileToSave documentoRegional = new Util.FileToSave();
							documentoRegional.setFile(fileOne);
							regional.setTemplatePath(ruta + documentoRegional.getOriginalFilename());
							Util.guardarArchivoEnDirectorio(CASO_ESPECIAL, Long.toString(per.getIdPeriodo()),
									documentoRegional);
						}
					}
					setRegionalDocuments(regional);
					break;
				case PROVINCIALES:
					PeriodoDocumentosProvinciales documentosProvinciales = new PeriodoDocumentosProvinciales.BuilderDocuments()
							.withIdPeriod(per.getIdPeriodo()).withIdRegion(dto.getRegionId()).withIdDeprov(dev)
							.withSpecialCase(true).withStartDate(dto.getStartDate()).withEndDate(dto.getEndDate())
							.withIdUserRegistry(idUsuarioRegistrado).withTemplatePath(dto.getTemplateFileName())
							.withDescription(dto.getObservation()).build();
					if (dto.getTemplate() != null && !dto.getTemplate().isEmpty()) {
						fileOne = new CustomMultipartFile(Base64Utils.decodeFromString(dto.getTemplate()),
								dto.getTemplateFileName());
						if (fileOne != null && !fileOne.isEmpty()) {
							Util.FileToSave documentoProv = new Util.FileToSave();
							documentoProv.setFile(fileOne);
							documentosProvinciales.setTemplatePath(ruta + documentoProv.getOriginalFilename());
							Util.guardarArchivoEnDirectorio(CASO_ESPECIAL, Long.toString(per.getIdPeriodo()),
									documentoProv);
						}
					}
					setProvincialDocument(documentosProvinciales);
					break;
				case APOYO:
					PeriodoPlanificacionImplementacionApoyo implementacionApoyo = new PeriodoPlanificacionImplementacionApoyo.BuilderApoyo()
							.withIdPeriod(per.getIdPeriodo()).withIdRegion(dto.getRegionId()).withIdDeprov(dev)
							.withSpecialCase(true).withStartDate(dto.getStartDate()).withEndDate(dto.getEndDate())
							.withIdUserRegistry(idUsuarioRegistrado).withDescription(dto.getObservation()).build();
					setPlanningImplementation(implementacionApoyo);
					break;
				case SUPERVISORES:
					PeriodoAsignacionSupervisores asignaciones = new PeriodoAsignacionSupervisores.BuilderAssign()
							.withIdPeriod(per.getIdPeriodo()).withIdRegion(dto.getRegionId()).withIdDeprov(dev)
							.withSpecialCase(true).withStartDate(dto.getStartDate()).withEndDate(dto.getEndDate())
							.withIdUserRegistry(idUsuarioRegistrado).withDescription(dto.getObservation()).build();
					setSupervisorAssignment(asignaciones);
					break;
				case REDES:
					PeriodoConformacionRedes networks = new PeriodoConformacionRedes.BuilderConformation()
							.withIdPeriod(per.getIdPeriodo()).withIdRegion(dto.getRegionId()).withIdDeprov(dev)
							.withSpecialCase(true).withStartDate(dto.getStartDate()).withEndDate(dto.getEndDate())
							.withIdUserRegistry(idUsuarioRegistrado).withDescription(dto.getObservation()).build();
					setNetworksConformation(networks);
					break;
				default:
					break;
			}
		});

	}

	public List<DeprovDTO> getDeprovSpecialCases(Integer period, Integer moduleId, Integer regionId) {
		List<DeprovDTO> result = new ArrayList<DeprovDTO>();
		RegionResponseDTO regionesPaso = regionIntegration.getRegionByNumber((long) regionId);
		PeriodoDTO periodo = this.getPeriodByYear(period);
		regionesPaso.getDeprovs().forEach(pr -> {
			DeprovDTO dev = new DeprovDTO();
			dev.setDeprov(pr.getNombreDeprov());
			SpecialCase s = SpecialCase.values()[moduleId];
			switch (s) {
				case REGIONAL:
					periodo.getDocumentosRegionalesEspeciales().stream().forEach(cs -> {
						if (cs.getIdRegion().equals((long) regionId)) {
							dev.setFechaInicio(cs.getFechaInicio());
							dev.setFechaFin(cs.getFechaFin());
						}
					});

					dev.setFechaInicio(dev.getFechaInicio() != null ? dev.getFechaInicio()
							: periodo.getDocumentosRegionales().getFechaInicio());
					dev.setFechaFin(dev.getFechaFin() != null ? dev.getFechaFin()
							: periodo.getDocumentosRegionales().getFechaFin());
					break;
				case PROVINCIALES:
					periodo.getDocumentosProvincialesEspeciales().stream().forEach(cs -> {
						if (cs.getIdRegion().equals((long) regionId)) {
							dev.setFechaInicio(cs.getFechaInicio());
							dev.setFechaFin(cs.getFechaFin());
						}
					});
					dev.setFechaInicio(dev.getFechaInicio() != null ? dev.getFechaInicio()
							: periodo.getDocumentosProvinciales().getFechaInicio());
					dev.setFechaFin(dev.getFechaFin() != null ? dev.getFechaFin()
							: periodo.getDocumentosProvinciales().getFechaFin());
					break;
				case REDES:
					periodo.getConformacionRedesEspeciales().stream().forEach(cs -> {
						if (cs.getIdRegion().equals((long) regionId)) {
							dev.setFechaInicio(cs.getFechaInicio());
							dev.setFechaFin(cs.getFechaFin());
						}
					});
					dev.setFechaInicio(dev.getFechaInicio() != null ? dev.getFechaInicio()
							: periodo.getConformacionRedes().getFechaInicio());
					dev.setFechaFin(dev.getFechaFin() != null ? dev.getFechaFin()
							: periodo.getConformacionRedes().getFechaFin());
					break;
				case APOYO:
					periodo.getImplementacionesApoyoEspeciales().stream().forEach(cs -> {
						if (cs.getIdRegion().equals((long) regionId)) {
							dev.setFechaInicio(cs.getFechaInicio());
							dev.setFechaFin(cs.getFechaFin());
						}
					});
					dev.setFechaInicio(dev.getFechaInicio() != null ? dev.getFechaInicio()
							: periodo.getImplementacionesApoyo().getFechaInicio());
					dev.setFechaFin(dev.getFechaFin() != null ? dev.getFechaFin()
							: periodo.getImplementacionesApoyo().getFechaFin());
					break;
				case SUPERVISORES:
					periodo.getAsignacionesEspeciales().stream().forEach(cs -> {
						if (cs.getIdRegion().equals((long) regionId)) {
							dev.setFechaInicio(cs.getFechaInicio());
							dev.setFechaFin(cs.getFechaFin());
						}
					});
					dev.setFechaInicio(dev.getFechaInicio() != null ? dev.getFechaInicio()
							: periodo.getAsignaciones().getFechaInicio());
					dev.setFechaFin(
							dev.getFechaFin() != null ? dev.getFechaFin() : periodo.getAsignaciones().getFechaFin());
					break;
				default:
					break;
			}

			dev.setDeprovId(pr.getIdDeprov());
			dev.setPeriodo(period);
			result.add(dev);
		});

		return result;
	}
}
