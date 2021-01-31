package cl.mineduc.came.apoyo_mejora_continua.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import cl.mineduc.came.apoyo_mejora_continua.dto.assignment.AssignDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.assignment.AssignSupervisorDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.assignment.AssignmentDetailDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.assignment.AssignmentListDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.assignment.AssignmentRequestDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.integration.region.ComunaResponse;
import cl.mineduc.came.apoyo_mejora_continua.dto.integration.region.DeprovsResponse;
import cl.mineduc.came.apoyo_mejora_continua.dto.integration.region.RegionResponseDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.shared.SelectorDTO;
import cl.mineduc.came.apoyo_mejora_continua.enums.AsignacionTipoEnum;
import cl.mineduc.came.apoyo_mejora_continua.helpers.UserHelper;
import cl.mineduc.came.apoyo_mejora_continua.integration.EstablecimientosIntegration;
import cl.mineduc.came.apoyo_mejora_continua.integration.RegionIntegration;
import cl.mineduc.came.apoyo_mejora_continua.integration.SostenedoresIntegration;
import cl.mineduc.came.apoyo_mejora_continua.modelo.AsesoriaDirecta;
import cl.mineduc.came.apoyo_mejora_continua.modelo.AsignacionSupervisor;
import cl.mineduc.came.apoyo_mejora_continua.modelo.ElementoLista;
import cl.mineduc.came.apoyo_mejora_continua.modelo.Periodo;
import cl.mineduc.came.apoyo_mejora_continua.modelo.Red;
import cl.mineduc.came.apoyo_mejora_continua.modelo.RedEstablecimiento;
import cl.mineduc.came.apoyo_mejora_continua.modelo.RedSostenedor;
import cl.mineduc.came.apoyo_mejora_continua.modelo.RedSupervisor;
import cl.mineduc.came.apoyo_mejora_continua.modelo.Supervisor;
import cl.mineduc.came.apoyo_mejora_continua.modelo.UsuarioRegistrado;
import cl.mineduc.came.apoyo_mejora_continua.modelo.api.Establecimientos;
import cl.mineduc.came.apoyo_mejora_continua.modelo.api.sostenedores.Sostenedores;
import cl.mineduc.came.apoyo_mejora_continua.repo.AsesoriaDirectaRepo;
import cl.mineduc.came.apoyo_mejora_continua.repo.AsignacionSupervisorRepo;
import cl.mineduc.came.apoyo_mejora_continua.repo.BitacoraService;
import cl.mineduc.came.apoyo_mejora_continua.repo.ElementoListaRepo;
import cl.mineduc.came.apoyo_mejora_continua.repo.PeriodoRepo;
import cl.mineduc.came.apoyo_mejora_continua.repo.RedEstablecimientoRepo;
import cl.mineduc.came.apoyo_mejora_continua.repo.RedRepo;
import cl.mineduc.came.apoyo_mejora_continua.repo.RedSostenedorRepo;
import cl.mineduc.came.apoyo_mejora_continua.repo.RedSupervisorRepo;
import cl.mineduc.came.apoyo_mejora_continua.repo.SupervisorRepo;
import cl.mineduc.came.apoyo_mejora_continua.repo.UsuarioRepo;

@Service
public class SupervisorsAssignmentService {

	private static final Long TYPE_NETWORK = 2420130603941758041l;

	private static final Long TYPE_CATEGORY = 2420130603857871959l;

	private static final String RED_COMPLEMENTARIA = "2446779961856492708";

	@Autowired
	private RegionIntegration regionIntegration;

	@Autowired
	private EstablecimientosIntegration establecimientosIntegration;

	@Autowired
	private ElementoListaRepo elementRepo;

	@Autowired
	private AsignacionSupervisorRepo asignacionSupervisorRepo;

	@Autowired
	PeriodoRepo periodRepo;

	@Autowired
	private RedRepo redRepo;

	@Autowired
	private RedSostenedorRepo redSostenedorRepo;

	@Autowired
	private RedEstablecimientoRepo redEstablecimientoRepo;

	@Autowired
	private AsesoriaDirectaRepo asesoriaDirectaRepo;

	@Autowired
	private UsuarioRepo usuarioRepo;

	@Autowired
	private RedSupervisorRepo redSupervisorRepo;

	@Autowired
	private SupervisorRepo supervisorRepo;

	@Autowired
	private AsignacionMaximaService asignacionMaximaService;

	@Autowired
	private SostenedoresIntegration sostenedoresIntegration;

	@Autowired
	BitacoraService bitacoraService;

	public List<SelectorDTO> getRegiones() {
		List<SelectorDTO> result = new ArrayList<SelectorDTO>();
		List<RegionResponseDTO> regionesPaso = regionIntegration.getRegiones();
		regionesPaso.forEach(p -> result.add(SelectorDTO.of(p.getIdRegion().toString(), p.getNombreRegion())));
		return result;
	}

	public List<SelectorDTO> getDeprovByRegion(int value) {
		List<SelectorDTO> result = new ArrayList<SelectorDTO>();
		RegionResponseDTO regionesPaso = regionIntegration.getRegionByNumber((long) value);
		regionesPaso.getDeprovs()
				.forEach(p -> result.add(SelectorDTO.of(p.getIdDeprov().toString(), p.getNombreDeprov())));
		return result;
	}

	public List<SelectorDTO> getComunasByDeprov(Integer value) {
		List<SelectorDTO> result = new ArrayList<SelectorDTO>();
		List<RegionResponseDTO> regionesPaso = regionIntegration.getRegiones();
		List<DeprovsResponse> paso1 = regionesPaso.stream()
				.filter(x -> x.getDeprovs().stream().anyMatch(j -> j.getIdDeprov().equals(value)))
				.map(RegionResponseDTO::getDeprovs).iterator().next();
		paso1.iterator().next().getComunas()
				.forEach(c -> result.add(SelectorDTO.of(c.getIdComuna().toString(), c.getNombreComuna())));
		return result;
	}

	public List<SelectorDTO> getTipoRed() {
		List<SelectorDTO> result = new ArrayList<SelectorDTO>();
		List<ElementoLista> elements = elementRepo.getElementsByIdLista(TYPE_NETWORK);
		elements.forEach(e -> result.add(SelectorDTO.of(e.getIdElementoLista().toString(), e.getNombre())));
		return result;
	}

	public List<SelectorDTO> getCategoria() {
		List<SelectorDTO> result = new ArrayList<SelectorDTO>();
		List<ElementoLista> elements = elementRepo.getElementsByIdLista(TYPE_CATEGORY);
		elements.forEach(e -> result.add(SelectorDTO.of(e.getIdElementoLista().toString(), e.getNombre())));
		return result;
	}

	public List<AssignmentListDTO> getAssignments(AssignmentRequestDTO request) {
		List<RegionResponseDTO> regiones = regionIntegration.getRegiones();

		List<AssignmentListDTO> result = new ArrayList<AssignmentListDTO>();
		int year = Calendar.getInstance().get(Calendar.YEAR);
		Periodo period = periodRepo.getPeriodByYear(year);
		List<Red> redes = redRepo.getByIdPeriodo(period.getIdPeriodo());

		Long idTipo = NumberUtils.toLong(request.getTipoId());
		if (idTipo == 0L || idTipo == AsignacionTipoEnum.DIRECTA.getValue()) {
			List<AsesoriaDirecta> asesorias = this.asesoriaDirectaRepo.getByIdPeriodo(period.getIdPeriodo()).stream()
					.filter(x -> request.getRegionId() == null || x.getIdRegion().equals(request.getRegionId()))
					.filter(x -> request.getDeprovId() == null || x.getIdDeprov().equals(request.getDeprovId()))
					.filter(x -> request.getComunaId() == null || (x.getIdComuna() != null && x.getIdComuna().equals(request.getComunaId())))
					.filter(x -> request.getRbd() == null || x.getRbd().equals(request.getRbd()))
					.collect(Collectors.toList());
			asesorias.forEach(a -> {
				AssignmentListDTO pso = new AssignmentListDTO();
				RegionResponseDTO reg = regiones.stream().filter(r -> a.getIdRegion().equals(r.getIdRegion()))
						.iterator().next();
				if (a.getIdAsignacionSupervisor() != null) {
					Supervisor supervisor = supervisorRepo.getSupervisorById(a.getIdSupervisor());
					UsuarioRegistrado user = usuarioRepo.findById(supervisor.getIdUsuario());
					pso.setSupervisors(Stream.of(user.getUsuarioDominio()).collect(Collectors.toList()));
					pso.setIdAsignacionSupervisor(a.getIdAsignacionSupervisor().toString());
				}
				pso.setId(a.getIdAsesoriaDirecta().toString());
				pso.setRegion(reg.getNombreRegion());

				// Establecimientos estPaso = establecimientosIntegration.getEstablecimientoByRbd(a.getRbd());
				
				pso.setRbd(a.getRbd() + "-" + a.getNombre());
				DeprovsResponse devPaso = reg.getDeprovs().stream().filter(r -> a.getIdDeprov().equals(r.getIdDeprov()))
						.iterator().next();
				pso.setDeprov(devPaso.getNombreDeprov());
				Optional<ComunaResponse> comunaPaso = devPaso.getComunas().stream()
						.filter(c -> c.getIdComuna() != null && c.getIdComuna().equals(a.getIdComuna())).findFirst();
				if (comunaPaso.isPresent()) {
					pso.setComuna(comunaPaso.get().getNombreComuna());
				}

				pso.setNombreRed(a.getNombre());
				pso.setTipo("Directa");
				// ElementoLista elements =
				// elementRepo.getElementsByIdLista(TYPE_CATEGORY).stream()
				// .filter(r ->
				// r.getIdElementoLista().equals(a.tipo(long)request.getTipoRedId())).iterator().next();
				// pso.setTipoRed(elements.getNombre());
				result.add(pso);
			});
		}

		if (idTipo == 0L || idTipo == AsignacionTipoEnum.RED.getValue()) {
			List<Red> redFiltered = redes.stream()
					.filter(x -> request.getNombreRed().isEmpty() || x.getNombre().contains(request.getNombreRed()))
					.filter(x -> request.getRegionId() == null || x.getIdRegion().equals((long)request.getRegionId()))
					.filter(x -> request.getDeprovId() == null || x.getIdDeprov().equals((long)request.getDeprovId()))
					.filter(x -> (request.getTipoRedId() == null || request.getTipoRedId().isEmpty())
							|| x.getIdTipoRed().equals(Long.parseLong(request.getTipoRedId())))
					.collect(Collectors.toList());

			redFiltered.forEach(x -> {
				AssignmentListDTO pso = new AssignmentListDTO();
				List<RedSupervisor> redSupervisores = redSupervisorRepo.getByIdRed(x.getIdRed());
				List<Long> supervisores = redSupervisores.stream().map(RedSupervisor::getIdSupervisor)
						.collect(Collectors.toList());
				List<String> usernames = new ArrayList<String>();
				supervisores.forEach(s -> {
					Supervisor supervisor = supervisorRepo.getSupervisorById(s);
					UsuarioRegistrado user = usuarioRepo.findById(supervisor.getIdUsuario());
					usernames.add(user.getUsuarioDominio());
				});
				pso.setSupervisors(usernames);
				pso.setIdAsignacionSupervisor(redSupervisores.stream().map(RedSupervisor::getIdAsignacionSupervisor)
						.findFirst().orElse(null) != null
								? redSupervisores.stream().map(RedSupervisor::getIdAsignacionSupervisor).findFirst()
										.orElse(null).toString()
								: null);
				RegionResponseDTO reg = regiones.stream().filter(r -> x.getIdRegion().equals((long) r.getIdRegion()))
						.iterator().next();
				pso.setRegion(reg.getNombreRegion());
				DeprovsResponse devPaso = reg.getDeprovs().stream()
						.filter(r -> x.getIdDeprov().equals((long) r.getIdDeprov())).iterator().next();
				if (request.getComunaId() != null) {
					Optional<ComunaResponse> comunaPaso = devPaso.getComunas().stream()
							.filter(c -> c.getIdComuna().equals(request.getComunaId())).findFirst();
					if(comunaPaso.isPresent()){
						pso.setComuna(comunaPaso.get().getNombreComuna());
					}					
				}
				pso.setTipo("Red");
				pso.setDeprov(devPaso.getNombreDeprov());
				ElementoLista elements = elementRepo.getElementsByIdLista(TYPE_NETWORK).stream()
						.filter(r -> r.getIdElementoLista().equals(x.getIdTipoRed())).iterator().next();
				pso.setTipoRed(elements.getNombre());
				// if(x.getIdTipoRed() != null){
				// ElementoLista category =
				// elementRepo.getElementsByIdLista(TYPE_CATEGORY).stream()
				// .filter(r ->
				// r.getIdElementoLista().equals(x.getIdTipoRed())).iterator().next();
				// pso.setCategoria(category.getNombre());
				// }
				pso.setNombreRed(x.getNombre());
				pso.setId(x.getIdRed().toString());

				if (x.getIdTipoRed().equals(Long.parseLong(RED_COMPLEMENTARIA))) {
					pso.setRbd(redSostenedorRepo.getByIdRed(x.getIdRed()).size() + " Sostenedores");
				} else {
					pso.setRbd(redEstablecimientoRepo.getByIdRed(x.getIdRed()).size() + " Establecimientos");
				}
				result.add(pso);
			});
		}

		return result;
	}

	public AssignmentDetailDTO getAssignmentDetail(Long idRed, Long idAsignacionSupervisor) {

		AsignacionSupervisor as = asignacionSupervisorRepo.getAssignmentSupervisorById(idAsignacionSupervisor);
		AssignmentDetailDTO a = new AssignmentDetailDTO();
		if (as.getIdAsignacionTipo() == AsignacionTipoEnum.DIRECTA.getValue()) {
			AsesoriaDirecta asesoria = this.asesoriaDirectaRepo.getByIdAsignacionSupervisor(idAsignacionSupervisor);
			Supervisor supervisor = supervisorRepo.getSupervisorById(asesoria.getIdSupervisor());
			SelectorDTO s = new SelectorDTO();
			UsuarioRegistrado user = usuarioRepo.findById(supervisor.getIdUsuario());
			s.setValue(user.getEmail());
			s.setDisplayText(user.getUsuarioDominio());
			s.setId(supervisor.getIdSupervisor().toString());
			a.setSupervisores(Stream.of(s).collect(Collectors.toList()));

			RegionResponseDTO region = regionIntegration.getRegionByNumber(Long.valueOf(asesoria.getIdRegion()));
			a.setRegion(region.getNombreRegion());
			DeprovsResponse devPaso = region.getDeprovs().stream()
					.filter(r -> asesoria.getIdDeprov().equals(r.getIdDeprov())).iterator().next();
			a.setDeprov(devPaso.getNombreDeprov());
			if (asesoria.getIdComuna() != null) {
				Optional<ComunaResponse> comunaPaso = devPaso.getComunas().stream()
						.filter(c -> c.getIdComuna().equals(asesoria.getIdComuna())).findFirst();
				if(comunaPaso.isPresent()){
					a.setComuna(comunaPaso.get().getNombreComuna());
				}				
			}
			a.setNombreRed(asesoria.getRbd() + "-" + asesoria.getNombre());
			a.setTipo("Directa");

		} else {
			Red rd = redRepo.getNetworkById(idRed);
			List<RedSupervisor> redSupervisores = redSupervisorRepo.getByIdRed(rd.getIdRed());
			List<SelectorDTO> supervisores = new ArrayList<SelectorDTO>();
			redSupervisores.forEach(r -> {
				Supervisor supervisor = supervisorRepo.getSupervisorById(r.getIdSupervisor());
				SelectorDTO s = new SelectorDTO();
				UsuarioRegistrado user = usuarioRepo.findById(supervisor.getIdUsuario());
				s.setValue(user.getEmail());
				s.setDisplayText(user.getUsuarioDominio());
				s.setId(r.getIdSupervisor().toString());
				supervisores.add(s);
			});
			a.setSupervisores(supervisores);
			RegionResponseDTO region = regionIntegration.getRegionByNumber(rd.getIdRegion());
			a.setRegion(region.getNombreRegion());
			DeprovsResponse devPaso = region.getDeprovs().stream()
					.filter(r -> rd.getIdDeprov().equals((long) r.getIdDeprov())).iterator().next();
			a.setDeprov(devPaso.getNombreDeprov());
			if (rd.getIdComuna() != null) {
				Optional<ComunaResponse> comunaPaso = devPaso.getComunas().stream()
						.filter(c -> c.getIdComuna().equals(rd.getIdComuna().intValue())).findAny();
				if(comunaPaso.isPresent()){
					a.setComuna(comunaPaso.get().getNombreComuna());
				}
				
			}
			a.setTipo("Red");
			ElementoLista tipoRed = elementRepo.getElementsByIdLista(TYPE_NETWORK).stream().filter(r -> r.getIdElementoLista().equals(rd.getIdTipoRed())).iterator().next();
			a.setTipoApoyo(tipoRed.getNombre());
			// ElementoLista categoria =
			// elementRepo.getElementsByIdLista(TYPE_CATEGORY).stream()
			// .filter(r ->
			// r.getIdElementoLista().equals(rd.getIdTipoRed())).iterator().next();
			// a.setCategoria(categoria.getNombre());						
			a.setNombreRed(rd.getNombre());
									
			List<Establecimientos> list = this.establecimientosIntegration.getEstablecimientos(null, null,
					rd.getIdRegion().intValue(), rd.getIdDeprov().intValue(), null, "", "", 999999);

			if(rd.getIdTipoRed().equals(Long.parseLong(RED_COMPLEMENTARIA))){
				List<RedSostenedor> redSostenedores = redSostenedorRepo.getByIdRed(rd.getIdRed());

				List<SelectorDTO> sostenedoresDTO = new ArrayList<SelectorDTO>();
				redSostenedores.forEach(s ->{
					Sostenedores sp = this.sostenedoresIntegration.getSostenedorByRut(s.getRut());
					SelectorDTO dto = new SelectorDTO();
					dto.setValue(sp.getRut());
					dto.setDisplayText(sp.getNombre() + " " + sp.getApellidoPaterno() + " " + sp.getApellidoMaterno());
					sostenedoresDTO.add(dto);
				});

				a.setEstablecimientos(sostenedoresDTO);
			} else {
				List<RedEstablecimiento> establecimientos = redEstablecimientoRepo.getByIdRed(rd.getIdRed());
				List<SelectorDTO> establecimientosDTO = new ArrayList<SelectorDTO>();
				establecimientos.forEach(e -> {
					Establecimientos estPaso1 = list.stream().filter(s -> s.getRbd().equals(e.getRbd())).iterator().next();
					SelectorDTO dto = new SelectorDTO();
					dto.setValue(estPaso1.getRbd().toString());
					dto.setDisplayText(estPaso1.getRbd() + " - " + estPaso1.getNombre());
					establecimientosDTO.add(dto);
				});

				a.setEstablecimientos(establecimientosDTO);
			}			
		}

		return a;
	}

	public AssignSupervisorDTO getAssignSupervisor(Long id) {
		AssignSupervisorDTO assign = new AssignSupervisorDTO();
		Red red = redRepo.getNetworkById(id);
		assign.setId(id.toString());

		if (red != null) {
			assign.setTipo("Red");
			assign.setNombre(red.getNombre());
			assign.setAsignacionMaxima(asignacionMaximaService.get().getSupervisores());

			if(red.getIdTipoRed().equals(Long.parseLong(RED_COMPLEMENTARIA))){
				List<RedSostenedor> redSostenedores = redSostenedorRepo.getByIdRed(red.getIdRed());
				List<SelectorDTO> sostenedoresDTO = new ArrayList<SelectorDTO>();
				redSostenedores.forEach(s -> {
					Sostenedores sp = this.sostenedoresIntegration.getSostenedorByRut(s.getRut());
					SelectorDTO dto = new SelectorDTO();
					dto.setValue(sp.getRut());
					dto.setDisplayText(sp.getNombre() + " " + sp.getApellidoPaterno() + " " + sp.getApellidoMaterno());
					sostenedoresDTO.add(dto);
				});

				assign.setEstablecimientos(sostenedoresDTO);
			} else {
				List<Establecimientos> list = this.establecimientosIntegration.getEstablecimientos(null, null,
						red.getIdRegion().intValue(), red.getIdDeprov().intValue(), null, "", "", 999999);

				List<RedEstablecimiento> establecimientos = redEstablecimientoRepo.getByIdRed(id);
				List<SelectorDTO> establecimientosDTO = new ArrayList<SelectorDTO>();
				establecimientos.forEach(e -> {
					Establecimientos estPaso1 = list.stream().filter(s -> s.getRbd().equals(e.getRbd())).iterator()
							.next();
					SelectorDTO dto = new SelectorDTO();
					dto.setValue(estPaso1.getRbd().toString());
					dto.setDisplayText(estPaso1.getRbd() + " - " + estPaso1.getNombre());
					establecimientosDTO.add(dto);
				});

				assign.setEstablecimientos(establecimientosDTO);
			}

			List<SelectorDTO> supervisoresDTO = new ArrayList<SelectorDTO>();
			List<Supervisor> supervisores = supervisorRepo.getAll().stream()
					.filter(s -> s.getIdDeprov().equals(red.getIdDeprov()) && s.getEndDate() == null)
					.collect(Collectors.toList());
			supervisores.forEach(s -> {
				SelectorDTO supervisor = new SelectorDTO();
				UsuarioRegistrado user = usuarioRepo.findById(s.getIdUsuario());
				supervisor.setValue(s.getIdSupervisor().toString());
				supervisor.setDisplayText(user.getUsuarioDominio());
				supervisoresDTO.add(supervisor);
			});
			assign.setSupervisores(supervisoresDTO);

		} else {
			AsesoriaDirecta asesoria = this.asesoriaDirectaRepo.getById(id);
			assign.setTipo("Directa");
			assign.setNombre(asesoria.getNombre());
			assign.setAsignacionMaxima(1);

			List<SelectorDTO> supervisoresDTO = new ArrayList<SelectorDTO>();
			List<Supervisor> supervisores = supervisorRepo.getAll().stream()
					.filter(s -> s.getIdDeprov().equals(Long.valueOf(asesoria.getIdDeprov())) && s.getEndDate() == null)
					.collect(Collectors.toList());
			supervisores.removeIf(s -> s.getEndDate() != null);
			supervisores.forEach(s -> {
				SelectorDTO supervisor = new SelectorDTO();
				UsuarioRegistrado user = usuarioRepo.findById(s.getIdUsuario());
				supervisor.setValue(s.getIdSupervisor().toString());
				supervisor.setDisplayText(user.getUsuarioDominio());
				supervisoresDTO.add(supervisor);
			});
			assign.setSupervisores(supervisoresDTO);
		}
		return assign;

	}

	public void setAssign(AssignDTO record) {
		Long idUsuarioRegistrado = UserHelper.getUsuarioRegistrado().getIdUsuario();
		UriComponentsBuilder builder = ServletUriComponentsBuilder.fromCurrentRequest();
		Red red = redRepo.getNetworkById(Long.valueOf(record.getId()));
		if (red != null) {
			AsignacionSupervisor as = new AsignacionSupervisor();
			as.setIdPeriodo(red.getIdPeriodo());
			as.setIdAsignacionTipo(AsignacionTipoEnum.RED.getValue());
			as.setIdUsuarioRegistro(idUsuarioRegistrado);
			asignacionSupervisorRepo.insert(as);

			record.getSupervisores().forEach(s -> {
				RedSupervisor rs = new RedSupervisor();
				rs.setIdRed(red.getIdRed());
				rs.setIdSupervisor(Long.parseLong(s));
				rs.setIdAsignacionSupervisor(as.getIdAsignacionSupervisor());
				redSupervisorRepo.insert(rs);
				bitacoraService.accionBitacora(1, rs,builder);
			});
			
		} else {
			AsesoriaDirecta asesoria = this.asesoriaDirectaRepo.getById(Long.valueOf(record.getId()));
			AsignacionSupervisor as = new AsignacionSupervisor();
			as.setIdPeriodo(asesoria.getIdPeriodo());
			as.setIdAsignacionTipo(AsignacionTipoEnum.DIRECTA.getValue());
			as.setIdUsuarioRegistro(idUsuarioRegistrado);
			asignacionSupervisorRepo.insert(as);
			bitacoraService.accionBitacora(1, as,builder);

			asesoria.setIdAsignacionSupervisor(as.getIdAsignacionSupervisor());
			asesoria.setIdSupervisor(Long.parseLong(record.getSupervisores().get(0)));
			asesoriaDirectaRepo.update(asesoria);
			bitacoraService.accionBitacora(2, asesoria,builder);
		}

	}

}
