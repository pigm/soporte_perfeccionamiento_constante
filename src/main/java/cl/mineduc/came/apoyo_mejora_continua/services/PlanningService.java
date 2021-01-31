package cl.mineduc.came.apoyo_mejora_continua.services;

import static org.mockito.ArgumentMatchers.nullable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.mail.Session;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//#region Imports MINEDUC
import cl.mineduc.came.apoyo_mejora_continua.dto.shared.SelectorDTO;
import cl.mineduc.came.apoyo_mejora_continua.helpers.StringHelper;
import cl.mineduc.came.apoyo_mejora_continua.helpers.UserHelper;
import cl.mineduc.came.apoyo_mejora_continua.integration.EstablecimientosIntegration;
import cl.mineduc.came.apoyo_mejora_continua.integration.RegionIntegration;
import cl.mineduc.came.apoyo_mejora_continua.modelo.Asesoria;
import cl.mineduc.came.apoyo_mejora_continua.modelo.AsesoriaDirecta;
import cl.mineduc.came.apoyo_mejora_continua.modelo.ElementoLista;
import cl.mineduc.came.apoyo_mejora_continua.modelo.EstablecimientoOrdenacion;
import cl.mineduc.came.apoyo_mejora_continua.modelo.Foco;
import cl.mineduc.came.apoyo_mejora_continua.modelo.ObjetivoMejora;
import cl.mineduc.came.apoyo_mejora_continua.modelo.Periodo;
import cl.mineduc.came.apoyo_mejora_continua.modelo.Red;
import cl.mineduc.came.apoyo_mejora_continua.modelo.RedEstablecimiento;
import cl.mineduc.came.apoyo_mejora_continua.modelo.RedSostenedor;
import cl.mineduc.came.apoyo_mejora_continua.modelo.RedSupervisor;
import cl.mineduc.came.apoyo_mejora_continua.modelo.Sesion;
import cl.mineduc.came.apoyo_mejora_continua.modelo.SesionFoco;
import cl.mineduc.came.apoyo_mejora_continua.modelo.SesionFocoAccionMejora;
import cl.mineduc.came.apoyo_mejora_continua.modelo.SesionMovimientosClaves;
import cl.mineduc.came.apoyo_mejora_continua.modelo.SesionRedParticipantes;
import cl.mineduc.came.apoyo_mejora_continua.modelo.Supervisor;
import cl.mineduc.came.apoyo_mejora_continua.modelo.UsuarioPerfil;
import cl.mineduc.came.apoyo_mejora_continua.modelo.UsuarioRegistrado;
import cl.mineduc.came.apoyo_mejora_continua.modelo.api.Establecimientos;
import cl.mineduc.came.apoyo_mejora_continua.repo.AsesoriaDirectaRepo;
import cl.mineduc.came.apoyo_mejora_continua.repo.AsesoriaRepo;
import cl.mineduc.came.apoyo_mejora_continua.repo.AsignacionMaximaRepo;
import cl.mineduc.came.apoyo_mejora_continua.repo.ElementoListaRepo;
import cl.mineduc.came.apoyo_mejora_continua.repo.EstablecimientoOrdenacionRepo;
import cl.mineduc.came.apoyo_mejora_continua.repo.FocoRepo;
import cl.mineduc.came.apoyo_mejora_continua.repo.ObjetivoMejoraRepo;
import cl.mineduc.came.apoyo_mejora_continua.repo.PerfilRepo;
import cl.mineduc.came.apoyo_mejora_continua.repo.PeriodoRepo;
import cl.mineduc.came.apoyo_mejora_continua.repo.RedEstablecimientoRepo;
import cl.mineduc.came.apoyo_mejora_continua.repo.RedRepo;
import cl.mineduc.came.apoyo_mejora_continua.repo.RedSostenedorRepo;
import cl.mineduc.came.apoyo_mejora_continua.repo.RedSupervisorRepo;
import cl.mineduc.came.apoyo_mejora_continua.repo.SesionFocoAccionMejoraRepo;
import cl.mineduc.came.apoyo_mejora_continua.repo.SesionFocoRepo;
import cl.mineduc.came.apoyo_mejora_continua.repo.SesionMovimientosClavesRepo;
import cl.mineduc.came.apoyo_mejora_continua.repo.SesionRedParticipantesRepo;
import cl.mineduc.came.apoyo_mejora_continua.repo.SesionRepo;
import cl.mineduc.came.apoyo_mejora_continua.repo.SupervisorRepo;
import cl.mineduc.came.apoyo_mejora_continua.repo.UsuarioRepo;
import cl.mineduc.came.apoyo_mejora_continua.dto.integration.region.ComunaResponse;
import cl.mineduc.came.apoyo_mejora_continua.dto.integration.region.DeprovsResponse;
import cl.mineduc.came.apoyo_mejora_continua.dto.integration.region.RegionResponseDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.planning.AsesoriaDirectaDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.planning.AsesoriaRedDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.planning.EncargadoRedDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.planning.EstablecimientoRedDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.planning.FocoDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.planning.FocoRedDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.planning.AccionesMejorasDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.planning.MovimientosClavesDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.planning.ObjetivoMejoraDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.planning.PlanningListDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.planning.PlanningRequestDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.planning.RegistraSesionDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.planning.RegistraSesionRedDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.planning.SesionDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.planning.SesionDetailDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.planning.SesionFocoAccionMejoraDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.planning.SesionInfoDetailDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.planning.SesionRedDetailDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.planning.SesionRedInfoDetailDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.planning.SesionRedParticipanteDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.planning.SupervisorDTO;
//#endregion

@Service
public class PlanningService {
    private static Logger logger = LogManager.getLogger(PlanningService.class);

    // #region CONSTANT
    private static final Long TYPE_CATEGORY = 2420130603941758041l;

    private static final String RED_COMPLEMENTARIA = "2446779961856492708";

    private static final Long TYPE_ASSIGNMENT_DIRECT = 2465568769368393407l;
    private static final Long TYPE_ASSIGNMENT_NETWORK = 2465568769376782016l;

    private static final Long TYPE_SESION_REGISTRADA = 2465656905259812566l;
    private static final Long TYPE_SESION_PLANIFICADA = 2465656905284978391l;

    private static final Long TYPE_SESION_PRESENCIAL = 2467153660761605894l;
    private static final Long TYPE_SESION_REMOTA = 2467153660769994503l;

    private static final String TYPE_SESION_PRESENCIAL_TEXT = "Presencial";
    private static final String TYPE_SESION_REMOTA_TEXT = "Remota";

    private static final String PERFIL_SUPERVISOR = "2416264829774857258";

    private static final String SESION_PLANIFICADA = "Sesión Planificada";
    private static final String SESION_REGISTRADA = "Sesión Registrada";
    // #endregion

    // #region DEF REPO
    @Autowired
    private RegionIntegration regionIntegration;

    @Autowired
    private UsuarioRepo usuarioRepo;

    @Autowired
    private SupervisorRepo supervisorRepo;

    @Autowired
    private ElementoListaRepo elementRepo;

    @Autowired
    private PeriodoRepo periodRepo;

    @Autowired
    private RedRepo redRepo;

    @Autowired
    private AsesoriaDirectaRepo asesoriaDirectaRepo;

    @Autowired
    private AsesoriaRepo asesoriaRepo;

    @Autowired
    private SesionRepo sesionRepo;

    @Autowired
    private SesionFocoRepo sesionFocoRepo;

    @Autowired
    private SesionMovimientosClavesRepo sesionMovimientosClavesRepo;

    @Autowired
    EstablecimientoOrdenacionRepo establecimientoOrdenacionRepo;

    @Autowired
    EstablecimientosIntegration establecimientosIntegration;

    @Autowired
    private FocoRepo focoRepo;

    @Autowired
    private AsignacionMaximaRepo asignacionMaximaRepo;

    @Autowired
    private RedEstablecimientoRepo redEstablecimientoRepo;

    @Autowired
    private RedSostenedorRepo redSostenedorRepo;

    @Autowired
    private RedSupervisorRepo redSupervisorRepo;

    @Autowired
    private PerfilRepo perfilRepo;

    @Autowired
    private SesionFocoAccionMejoraRepo sesionFocoAccionMejoraRepo;
    
    @Autowired
    private ObjetivoMejoraRepo objetivoMejoraRepo;

    @Autowired
    private SesionRedParticipantesRepo sesionRedParticipantesRepo;
    // #endregion

    private Integer countSesionsAD(Long idAsesoriaDirecta, Long idPeriodo) {
        List<Asesoria> asesorias = asesoriaRepo.getByIdPeriodo(idPeriodo);
        Optional<Asesoria> asesoriaPaso = asesorias.stream()
                .filter(x -> x.getIdAsesoriaDirecta() != null && x.getIdAsesoriaDirecta().equals(idAsesoriaDirecta))
                .findAny();
        return asesoriaPaso.isPresent() ? this.sesionRepo.getByIdAsesoria(asesoriaPaso.get().getIdAsesoria()).size()
                : 0;
    }

    private Integer countSesionsRed(Long idRed, Long idPeriodo) {
        List<Asesoria> asesorias = asesoriaRepo.getByIdPeriodo(idPeriodo);
        Optional<Asesoria> asesoriaPaso = asesorias.stream()
                .filter(x -> x.getIdRed() != null && x.getIdRed().equals(idRed)).findAny();
        return asesoriaPaso.isPresent() ? this.sesionRepo.getByIdAsesoria(asesoriaPaso.get().getIdAsesoria()).size()
                : 0;
    }

    public Integer getMaxFoco() {
        return asignacionMaximaRepo.get().getFoco();
    }

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

    public List<String> getSupUserNames(Integer regionId, Integer deprovId) {
        List<String> result = new ArrayList<String>();
        List<UsuarioRegistrado> usuarios = usuarioRepo.findAll().stream()
                .filter(x -> regionId == null || (x.getIdRegion() != null && x.getIdRegion().equals((long) regionId)))
                .filter(x -> deprovId == null || (x.getIdDeprov() != null && x.getIdDeprov().equals((long) deprovId)))
                .collect(Collectors.toList());

        usuarios.forEach(u -> {
            UsuarioPerfil pu = u.getPerfilesUsuarios().stream().filter(p -> p.getEndDate() == null).iterator().next();
            if (pu.getIdPerfil().equals(Long.parseLong(PERFIL_SUPERVISOR))) {
                result.add(u.getUsuarioDominio());
            }
        });
        return result;
    }

    public List<SelectorDTO> getTipoRed() {
        List<SelectorDTO> result = new ArrayList<SelectorDTO>();
        List<ElementoLista> elements = elementRepo.getElementsByIdLista(TYPE_CATEGORY);
        elements.forEach(e -> result.add(SelectorDTO.of(e.getIdElementoLista().toString(), e.getNombre())));
        return result;
    }

    public List<PlanningListDTO> getPlanning(PlanningRequestDTO request) {
        List<PlanningListDTO> result = new ArrayList<PlanningListDTO>();

        // Establecimientos
        List<Establecimientos> establecimientos = this.establecimientosIntegration.getEstablecimientos(null,
                request.getDependencia(), request.getIdRegion(), request.getIdDeprov(), request.getIdComuna(), null,
                null, 999999);

        // ordenaciones
        List<EstablecimientoOrdenacion> ordenacion = establecimientoOrdenacionRepo.get().stream()
                .filter(x -> (request.getCategorizacion() == null || request.getCategorizacion().isEmpty())
                        || x.getOrdenacion().equalsIgnoreCase(request.getCategorizacion()))
                .collect(Collectors.toList());

        List<RegionResponseDTO> regiones = regionIntegration.getRegiones();
        // Supervisores
        List<Supervisor> supervisores = supervisorRepo.getAll().stream()
                .filter(s -> (request.getUserNameSupervisor() == null || request.getUserNameSupervisor().isEmpty())
                        || s.getUsuario().getUsuarioDominio().equalsIgnoreCase(request.getUserNameSupervisor()))
                .collect(Collectors.toList());
        // periodo
        int year = Calendar.getInstance().get(Calendar.YEAR);
        Periodo period = periodRepo.getPeriodByYear(year);
        // redes
        if(request.getTipo().isEmpty() || request.getTipo().equalsIgnoreCase("red")){
            List<Red> redes = redRepo.getByIdPeriodo(period.getIdPeriodo());
            List<Red> redFiltered = redes.stream()
                    .filter(x -> request.getNombreRed().isEmpty() || x.getNombre().contains(request.getNombreRed()))

                    .filter(x -> request.getIdRegion() == null
                            || x.getIdRegion().intValue() == request.getIdRegion().intValue())
                    .filter(x -> request.getIdDeprov() == null
                            || x.getIdDeprov().intValue() == request.getIdDeprov().intValue())

                    .filter(x -> request.getIdTipoRed().isEmpty()
                            || x.getIdTipoRed().equals(Long.parseLong(request.getIdTipoRed())))
                    .filter(x -> request.getRbd() == null || request.getRbd().isEmpty()
                            || redEstablecimientoRepo.getByIdRed(x.getIdRed()).stream()
                                    .anyMatch(re -> re.getRbd().equals(Integer.parseInt(request.getRbd()))))
                    .filter(x -> request.getUserNameSupervisor() == null
                            || redSupervisorRepo.getByIdRed(x.getIdRed()).stream()
                                    .anyMatch(rs -> supervisores.stream()
                                            .anyMatch(s -> rs.getIdSupervisor().equals(s.getIdSupervisor()))))
                    .filter(x -> request.getCategorizacion() == null || redEstablecimientoRepo.getByIdRed(x.getIdRed())
                            .stream().anyMatch(re -> ordenacion.stream().anyMatch(o -> o.getRbd().equals(re.getRbd()))))
                    .filter(x -> request.getDependencia() == null
                            || redEstablecimientoRepo.getByIdRed(x.getIdRed()).stream().anyMatch(
                                    re -> establecimientos.stream().anyMatch(e -> e.getRbd().equals(re.getRbd()))))
                    .collect(Collectors.toList());

            redFiltered.forEach(red -> {
                PlanningListDTO pso = new PlanningListDTO();

                RegionResponseDTO reg = regiones.stream().filter(r -> red.getIdRegion().equals((long) r.getIdRegion()))
                        .iterator().next();
                pso.setRegion(reg.getNombreRegion());
                pso.setIdRegion(red.getIdRegion().intValue());
                DeprovsResponse devPaso = reg.getDeprovs().stream()
                        .filter(r -> red.getIdDeprov().equals((long) r.getIdDeprov())).iterator().next();
                pso.setIdDeprov(red.getIdDeprov().intValue());
                pso.setDeprov(devPaso.getNombreDeprov());
                if (red.getIdComuna() != null) {
                    Optional<ComunaResponse> comPaso = devPaso.getComunas().stream()
                            .filter(c -> c.getIdComuna().equals(red.getIdComuna().intValue())).findFirst();
                    if (comPaso.isPresent()) {
                        pso.setIdComuna(comPaso.get().getIdComuna());
                        pso.setComuna(comPaso.get().getNombreComuna());
                    }

                }
                List<RedSupervisor> redSupervisores = redSupervisorRepo.getByIdRed(red.getIdRed());
                List<Long> supers = redSupervisores.stream().map(RedSupervisor::getIdSupervisor)
                        .collect(Collectors.toList());
                List<String> usernames = new ArrayList<String>();
                supers.forEach(s -> {
                    Supervisor supervisor = supervisorRepo.getSupervisorById(s);
                    UsuarioRegistrado user = usuarioRepo.findById(supervisor.getIdUsuario());
                    usernames.add(user.getUsuarioDominio());
                });
                pso.setSupervisores(usernames);
                if (red.getIdTipoRed().equals(Long.parseLong(RED_COMPLEMENTARIA))) {
                    pso.setEstablecimientos(redSostenedorRepo.getByIdRed(red.getIdRed()).size() + " Sostenedores");
                } else {
                    pso.setEstablecimientos(
                            redEstablecimientoRepo.getByIdRed(red.getIdRed()).size() + " Establecimientos");
                }

                pso.setNombreRed(red.getNombre());
                pso.setSessiones(countSesionsRed(red.getIdRed(), period.getIdPeriodo()));

                pso.setTipo("Red");
                pso.setIdRed(red.getIdRed().toString());
                result.add(pso);
            });
        }

        // asesorias directas
        if(request.getTipo().isEmpty() || request.getTipo().equalsIgnoreCase("directa")) {            
            List<AsesoriaDirecta> asesorias = this.asesoriaDirectaRepo.getByIdPeriodo(period.getIdPeriodo()).stream()
                    .filter(x -> request.getIdRegion() == null || x.getIdRegion().equals(request.getIdRegion()))
                    .filter(x -> request.getIdDeprov() == null || x.getIdDeprov().equals(request.getIdDeprov()))
                    .filter(x -> (request.getRbd() == null || request.getRbd().isEmpty())
                            || x.getRbd().equals(Integer.parseInt(request.getRbd())))
                    .filter(x -> (request.getNombreEstablecimiento() == null
                            || request.getNombreEstablecimiento().isEmpty())
                            || x.getNombre().equals(request.getNombreEstablecimiento()))
                    .filter(x -> (request.getUserNameSupervisor() == null || request.getUserNameSupervisor().isEmpty())
                            || supervisores.stream().anyMatch(s -> s.getIdSupervisor().equals(x.getIdSupervisor())))
                    .filter(x -> (request.getCategorizacion() == null || request.getCategorizacion().isEmpty())
                            || ordenacion.stream().anyMatch(o -> o.getRbd().equals(x.getRbd())))
                    .filter(x -> (request.getDependencia() == null)
                            || establecimientos.stream().anyMatch(e -> e.getRbd().equals(x.getRbd())))
                    .collect(Collectors.toList());

            asesorias.forEach(a -> {
                PlanningListDTO pso = new PlanningListDTO();

                RegionResponseDTO reg = regiones.stream().filter(r -> a.getIdRegion().equals(r.getIdRegion()))
                        .iterator().next();
                pso.setRegion(reg.getNombreRegion());
                pso.setIdRegion(a.getIdRegion());
                DeprovsResponse devPaso = reg.getDeprovs().stream().filter(r -> a.getIdDeprov().equals(r.getIdDeprov()))
                        .iterator().next();
                pso.setIdDeprov(a.getIdDeprov());
                pso.setDeprov(devPaso.getNombreDeprov());
                if (a.getIdComuna() != null) {
                    Optional<ComunaResponse> comPaso = devPaso.getComunas().stream()
                            .filter(c -> c.getIdComuna().equals(a.getIdComuna())).findFirst();
                    if (comPaso.isPresent()) {
                        pso.setIdComuna(comPaso.get().getIdComuna());
                        pso.setComuna(comPaso.get().getNombreComuna());
                    }

                }

                if (a.getIdSupervisor() != null) {
                    Supervisor sup = supervisores.stream().filter(s -> s.getIdSupervisor().equals(a.getIdSupervisor()))
                            .iterator().next();
                    if (sup != null) {
                        pso.setSupervisores(Arrays.asList(sup.getUsuario().getUsuarioDominio()));
                    }
                }
                pso.setNombreRed("Asesoria Directa");
                pso.setSessiones(this.countSesionsAD(a.getIdAsesoriaDirecta(), period.getIdPeriodo()));
                pso.setEstablecimientos(a.getRbd() + "-" + a.getNombre());
                pso.setTipo("Directa");
                pso.setIdAsesoriaDirecta(a.getIdAsesoriaDirecta().toString());
                result.add(pso);
            });
        }

        return result;
    }

    public AsesoriaDirectaDTO getPlanningByAdvisory(String advisoryId) {
        AsesoriaDirectaDTO result = new AsesoriaDirectaDTO();

        Long idUsuarioRegistrado = UserHelper.getUsuarioRegistrado().getIdUsuario();

        AsesoriaDirecta advisoryPaso = this.asesoriaDirectaRepo.getById(Long.parseLong(advisoryId));
        Periodo periodo = periodRepo.getPeriodById(advisoryPaso.getIdPeriodo());
        Long idAsesoria = null;
        List<Asesoria> asesorias = asesoriaRepo.getByIdPeriodo(periodo.getIdPeriodo());
        Optional<Asesoria> asesoriaPaso = asesorias.stream().filter(x -> x.getIdAsesoriaDirecta() != null && x.getIdAsesoriaDirecta().equals(advisoryPaso.getIdAsesoriaDirecta())).findAny();
        if (!asesoriaPaso.isPresent()) {
            // aqui debo obtener la info de la asesoria
            Asesoria registra = new Asesoria();
            registra.setIdPeriodo(periodo.getIdPeriodo());
            registra.setIdAsesoriaDirecta(advisoryPaso.getIdAsesoriaDirecta());
            registra.setIdAsignacionTipo(TYPE_ASSIGNMENT_DIRECT);
            registra.setStartDate(new Date());
            registra.setEndDate(null);
            registra.setUsuarioRegistro(idUsuarioRegistrado);
            registra.setFechaRegistro(new Date());

            asesoriaRepo.insert(registra);
            idAsesoria = registra.getIdAsesoria();
        } else {
            idAsesoria = asesoriaPaso.get().getIdAsesoria();
        }

        result.setIdAsesoria(idAsesoria.toString());
        result.setIdAsesoriaDirecta(advisoryPaso.getIdAsesoriaDirecta().toString());
        result.setRbd(advisoryPaso.getRbd());
        result.setNombreEstablecimiento(advisoryPaso.getNombre());
        Supervisor supervisor = supervisorRepo.getSupervisorById(advisoryPaso.getIdSupervisor());
        SupervisorDTO supPaso = new SupervisorDTO();
        supPaso.setNombres(supervisor.getUsuario().getNombre());
        supPaso.setApellidoPaterno(supervisor.getUsuario().getPaterno());
        supPaso.setApellidoMaterno(supervisor.getUsuario().getMaterno());
        result.setSupervisores(Arrays.asList(supPaso));

        List<SesionDTO> sesionesPaso = new ArrayList<SesionDTO>();
        List<Sesion> sesiones = this.sesionRepo.getByIdAsesoria(idAsesoria);
        if (!sesiones.isEmpty()) {
            sesiones.forEach(s -> {
                SesionDTO sad = new SesionDTO();
                sad.setIdSesion(s.getIdSesion().toString());

                sad.setNumero(sesiones.indexOf(s) + 1);
                sad.setFechaPlanificacion(s.getFechaPlanificacion());
                sad.setFechaRalizada(s.getFechaRealizada());
                sad.setEstado(s.getIdSessionEstado().equals(TYPE_SESION_PLANIFICADA) ? SESION_PLANIFICADA
                        : SESION_REGISTRADA);

                sesionesPaso.add(sad);
            });
        }
        result.setSesiones(sesionesPaso);

        return result;
    }

    public AsesoriaRedDTO getPlanningByRed(String redId) {
        AsesoriaRedDTO result = new AsesoriaRedDTO();

        List<RegionResponseDTO> regiones = regionIntegration.getRegiones();

        Long idUsuarioRegistrado = UserHelper.getUsuarioRegistrado().getIdUsuario();
        Red red = this.redRepo.getNetworkById(Long.parseLong(redId));

        List<Establecimientos> list = this.establecimientosIntegration.getEstablecimientos(null, null,
                red.getIdRegion().intValue(), red.getIdDeprov().intValue(), null, "", "", 999999);

        Periodo periodo = periodRepo.getPeriodById(red.getIdPeriodo());
        Long idAsesoria = null;
        List<Asesoria> asesorias = asesoriaRepo.getByIdPeriodo(periodo.getIdPeriodo());
        Optional<Asesoria> asesoriaPaso = asesorias.stream()
                .filter(x -> x.getIdRed() != null && x.getIdRed().equals(red.getIdRed())).findAny();
        if (!asesoriaPaso.isPresent()) {
            // aqui debo obtener la info de la asesoria
            Asesoria registra = new Asesoria();
            registra.setIdPeriodo(periodo.getIdPeriodo());
            registra.setIdAsesoriaDirecta(null);
            registra.setIdRed(red.getIdRed());
            registra.setIdAsignacionTipo(TYPE_ASSIGNMENT_NETWORK);
            registra.setStartDate(new Date());
            registra.setEndDate(null);
            registra.setUsuarioRegistro(idUsuarioRegistrado);
            registra.setFechaRegistro(new Date());

            asesoriaRepo.insert(registra);
            idAsesoria = registra.getIdAsesoria();
        } else {
            idAsesoria = asesoriaPaso.get().getIdAsesoria();
        }

        result.setIdAsesoria(idAsesoria.toString());
        result.setNombreRed(red.getNombre());
        result.setIdTipoRed(red.getIdTipoRed().toString());
        ElementoLista elements = elementRepo.getElementsByIdLista(TYPE_CATEGORY).stream()
                .filter(r -> r.getIdElementoLista().equals(red.getIdTipoRed())).iterator().next();
        result.setTipoRed(elements.getNombre());
        RegionResponseDTO reg = regiones.stream().filter(r -> red.getIdRegion().equals((long) r.getIdRegion()))
                .iterator().next();
        result.setRegion(reg.getNombreRegion());
        DeprovsResponse devPaso = reg.getDeprovs().stream()
                .filter(r -> red.getIdDeprov().equals((long) r.getIdDeprov())).iterator().next();
        result.setDeprov(devPaso.getNombreDeprov());
        UsuarioRegistrado usuarioPaso = this.usuarioRepo.findById(red.getIdUsuario());
        EncargadoRedDTO encargado = new EncargadoRedDTO();
        encargado.setIdUsuario(usuarioPaso.getIdUsuario().toString());
        encargado.setNombre(usuarioPaso.getNombre());
        encargado.setApellido(usuarioPaso.getPaterno() + " " + usuarioPaso.getMaterno());
        UsuarioPerfil usuarioPerfil = usuarioPaso.getPerfilesUsuarios().stream().filter(p -> p.getEndDate() == null)
                .iterator().next();
        encargado.setCargo(perfilRepo.obtenerPerfil(usuarioPerfil.getIdPerfil()).getNombre());
        encargado.setCorreo(usuarioPaso.getEmail());
        result.setEncargado(encargado);

        List<EstablecimientoRedDTO> establecimientos = new ArrayList<EstablecimientoRedDTO>();
        List<RedEstablecimiento> redEstablecimientos = redEstablecimientoRepo.getByIdRed(red.getIdRed());

        redEstablecimientos.forEach(re -> {
            Establecimientos estPaso1 = list.stream().filter(s -> s.getRbd().equals(re.getRbd())).iterator().next();
            EstablecimientoRedDTO rePaso = new EstablecimientoRedDTO();
            rePaso.setRbd(re.getRbd());
            rePaso.setEstablecimiento(estPaso1.getNombre());
            establecimientos.add(rePaso);
        });
        result.setEstablecimientos(establecimientos);

        List<SupervisorDTO> supervisors = new ArrayList<SupervisorDTO>();
        this.redSupervisorRepo.getByIdRed(red.getIdRed()).forEach(rs -> {
            UsuarioRegistrado supervisor = this.usuarioRepo.findById(red.getIdUsuario());
            SupervisorDTO supPaso = new SupervisorDTO();
            supPaso.setNombres(supervisor.getNombre());
            supPaso.setApellidoPaterno(supervisor.getPaterno());
            supPaso.setApellidoMaterno(supervisor.getMaterno());
            supervisors.add(supPaso);
        });
        result.setSupervisores(supervisors);

        List<SesionDTO> sesionesPaso = new ArrayList<SesionDTO>();
        List<Sesion> sesiones = this.sesionRepo.getByIdAsesoria(idAsesoria);
        if (!sesiones.isEmpty()) {
            sesiones.forEach(s -> {
                SesionDTO sad = new SesionDTO();
                sad.setIdSesion(s.getIdSesion().toString());

                sad.setNumero(sesiones.indexOf(s) + 1);
                sad.setFechaPlanificacion(s.getFechaPlanificacion());
                sad.setFechaRalizada(s.getFechaRealizada());
                sad.setEstado(s.getIdSessionEstado().equals(TYPE_SESION_PLANIFICADA) ? SESION_PLANIFICADA
                        : SESION_REGISTRADA);

                sesionesPaso.add(sad);
            });
        }
        result.setSesiones(sesionesPaso);

        return result;
    }

    public SesionDTO getSesion(String idSesion) {
        SesionDTO result = new SesionDTO();
        Sesion s = this.sesionRepo.getById(Long.parseLong(idSesion));

        SesionDTO sad = new SesionDTO();
        sad.setIdSesion(s.getIdSesion().toString());
        sad.setFechaPlanificacion(s.getFechaPlanificacion());
        sad.setFechaRalizada(s.getFechaRealizada());
        sad.setEstado(s.getIdSessionEstado().equals(TYPE_SESION_PLANIFICADA) ? SESION_PLANIFICADA : SESION_REGISTRADA);

        return result;
    }

    public SesionDetailDTO getSesionDetail(String idSesion) {
        SesionDetailDTO sad = new SesionDetailDTO();

        Sesion s = this.sesionRepo.getById(Long.parseLong(idSesion));
        sad.setIdSesion(s.getIdSesion().toString());
        sad.setFechaPlanificacion(s.getFechaPlanificacion());
        sad.setFechaRalizada(s.getFechaRealizada());

        sad.setIdSesionTipo(s.getIdSesionTipo() != null ? s.getIdSesionTipo().toString() : null);

        sad.setTipoSesion(
                s.getIdSesionTipo() != null
                        ? (s.getIdSesionTipo().equals(TYPE_SESION_PRESENCIAL) ? TYPE_SESION_PRESENCIAL_TEXT
                                : TYPE_SESION_REMOTA_TEXT)
                        : null);

        sad.setEstado(s.getIdSessionEstado() != null
                ? s.getIdSessionEstado().equals(TYPE_SESION_PLANIFICADA) ? SESION_PLANIFICADA : SESION_REGISTRADA
                : null);

        List<SesionFoco> sesionFocos = this.sesionFocoRepo.getByIdSesion(s.getIdSesion());
        int year = Calendar.getInstance().get(Calendar.YEAR);
        List<Foco> focos = focoRepo.getAllFocos().stream().filter(f -> f.getPeriodo().equals(year))
                .collect(Collectors.toList());
        List<FocoDTO> sesionFocosPaso = new ArrayList<FocoDTO>();
        focos.forEach(x -> {
            Optional<SesionFoco> sfp = sesionFocos.stream().filter(f -> f.getIdFoco().equals(x.getIdFoco())).findFirst();
            if (sfp.isPresent()) {
                FocoDTO f = new FocoDTO();
                f.setIdFoco(x.getIdFoco().toString());
                List<AccionesMejorasDTO> acciones = new ArrayList<AccionesMejorasDTO>();
                x.getAcciones().forEach(a -> {
                    AccionesMejorasDTO am = new AccionesMejorasDTO();
                    am.setIdAccionesMejoras(a.getIdAccionesMejoras().toString());
                    List<MovimientosClavesDTO> movimientos = new ArrayList<MovimientosClavesDTO>();
                    a.getMovimientos().forEach(m -> {
                        MovimientosClavesDTO mc = new MovimientosClavesDTO();
                        mc.setIdMovimientosClaves(m.getIdMovimientosClaves().toString());
                        Optional<SesionMovimientosClaves> t = sfp.get().getMovimientosClaves().stream()
                                .filter(r -> r.getIdMovimientosClaves().equals(m.getIdMovimientosClaves())).findFirst();
                        mc.setCompletado(t.isPresent() && t.get().getCompletado());
                        movimientos.add(mc);
                    });
                    f.setLogrado(movimientos.stream().filter(c -> c.getCompletado()).count() + f.getLogrado());
                    f.setTotal(movimientos.size() + f.getTotal());
                    am.setMovimientosClaves(movimientos);
                    acciones.add(am);
                });
                f.setAccionesMejoras(acciones);
                sesionFocosPaso.add(f);
            }
        });

        sad.setFocos(sesionFocosPaso);

        return sad;
    }

    public SesionInfoDetailDTO getSesionInfoDetail(String idAsesoria){        
        List<FocoDTO> sesionFocosPaso = new ArrayList<FocoDTO>();

        AsesoriaDirecta advisoryPaso = this.asesoriaDirectaRepo.getById(Long.parseLong(idAsesoria));
        int year = Calendar.getInstance().get(Calendar.YEAR);
        List<Foco> focos = focoRepo.getAllFocos().stream().filter(f -> f.getPeriodo().equals(year))
                .collect(Collectors.toList());

        List<Asesoria> asesorias = asesoriaRepo.getByIdPeriodo(advisoryPaso.getIdPeriodo());
        Asesoria asesoriaPaso = asesorias.stream().filter(x -> x.getIdAsesoriaDirecta() != null && x.getIdAsesoriaDirecta().equals(advisoryPaso.getIdAsesoriaDirecta())).iterator().next();

        List<Sesion> sesiones = this.sesionRepo.getByIdAsesoria(asesoriaPaso.getIdAsesoria());
        List<SesionFoco> sesionFocos = new ArrayList<SesionFoco>();
        sesiones.forEach(x ->{
            List<SesionFoco> sfs = this.sesionFocoRepo.getByIdSesion(x.getIdSesion());
            sfs.forEach(fs ->{
                Optional<SesionFoco> ps = sesionFocos.stream().filter(o -> o.getIdFoco().equals(fs.getIdFoco())).findAny();
                if(ps.isPresent()){
                    fs.getMovimientosClaves().forEach(m -> {
                        ps.get().getMovimientosClaves().forEach(mc ->{
                            if(m.getIdMovimientosClaves().equals(mc.getIdMovimientosClaves()) && m.getCompletado().booleanValue()){
                                mc.setCompletado(m.getCompletado());
                            }
                        });
                    });
                } else{
                    sesionFocos.add(fs);
                }
            });
        });

        focos.forEach(x -> {
            Optional<SesionFoco> sfp = sesionFocos.stream().filter(f -> f.getIdFoco().equals(x.getIdFoco()))
                    .findFirst();
            if (sfp.isPresent()) {
                FocoDTO f = new FocoDTO();
                f.setIdFoco(x.getIdFoco().toString());
                List<AccionesMejorasDTO> acciones = new ArrayList<AccionesMejorasDTO>();
                x.getAcciones().forEach(a -> {
                    AccionesMejorasDTO am = new AccionesMejorasDTO();
                    am.setIdAccionesMejoras(a.getIdAccionesMejoras().toString());
                    List<MovimientosClavesDTO> movimientos = new ArrayList<MovimientosClavesDTO>();
                    a.getMovimientos().forEach(m -> {
                        MovimientosClavesDTO mc = new MovimientosClavesDTO();
                        mc.setIdMovimientosClaves(m.getIdMovimientosClaves().toString());
                        Optional<SesionMovimientosClaves> t = sfp.get().getMovimientosClaves().stream()
                                .filter(r -> r.getIdMovimientosClaves().equals(m.getIdMovimientosClaves())).findFirst();
                        mc.setCompletado(t.isPresent() && t.get().getCompletado());
                        movimientos.add(mc);
                    });
                    f.setLogrado(movimientos.stream().filter(c -> c.getCompletado()).count() + f.getLogrado());
                    f.setTotal(movimientos.size() + f.getTotal());
                    am.setMovimientosClaves(movimientos);
                    acciones.add(am);
                });
                f.setAccionesMejoras(acciones);
                sesionFocosPaso.add(f);
            }
        });
        SesionInfoDetailDTO result = new SesionInfoDetailDTO();
        result.setIdAsesoria(idAsesoria);
        result.setFocos(sesionFocosPaso);
        return result;
    }

    public SesionRedDetailDTO getSesionRedDetail(String idSesion){
        SesionRedDetailDTO result = new SesionRedDetailDTO();

        Sesion s = this.sesionRepo.getById(Long.parseLong(idSesion));
        result.setIdSesion(s.getIdSesion().toString());
        result.setFechaPlanificacion(s.getFechaPlanificacion());
        result.setFechaRalizada(s.getFechaRealizada());

        result.setIdSesionTipo(s.getIdSesionTipo() != null ? s.getIdSesionTipo().toString() : null);

        result.setTipoSesion(
                s.getIdSesionTipo() != null
                        ? (s.getIdSesionTipo().equals(TYPE_SESION_PRESENCIAL) ? TYPE_SESION_PRESENCIAL_TEXT
                                : TYPE_SESION_REMOTA_TEXT)
                        : null);

        result.setEstado(s.getIdSessionEstado() != null
                ? s.getIdSessionEstado().equals(TYPE_SESION_PLANIFICADA) ? SESION_PLANIFICADA : SESION_REGISTRADA
                : null);

        List<SesionFoco> sesionFocos = this.sesionFocoRepo.getByIdSesion(s.getIdSesion());
        int year = Calendar.getInstance().get(Calendar.YEAR);
        List<Foco> focos = focoRepo.getAllFocos().stream().filter(f -> f.getPeriodo().equals(year))
                .collect(Collectors.toList());
        List<FocoRedDTO> sesionFocosPaso = new ArrayList<FocoRedDTO>();        
        focos.forEach(x -> {
            List<SesionFocoAccionMejoraDTO> acciones = new ArrayList<SesionFocoAccionMejoraDTO>();
            Optional<SesionFoco> sfp = sesionFocos.stream().filter(f -> f.getIdFoco().equals(x.getIdFoco())).findFirst();
            if (sfp.isPresent()) {
                FocoRedDTO f = new FocoRedDTO();
                f.setIdFoco(x.getIdFoco().toString());
                f.setNombre(x.getNombre());                
                if(!sfp.get().getAccionesMejora().isEmpty()){
                    f.setLogro(Math.round(
                            (sfp.get().getAccionesMejora().stream().filter(acc -> acc.getCompletado()).count() * 100)
                                    / sfp.get().getAccionesMejora().size()));
                }

                sfp.get().getAccionesMejora().forEach(p ->{
                    SesionFocoAccionMejoraDTO pso = new SesionFocoAccionMejoraDTO();
                    pso.setAccionMejora(p.getAccionMejora());
                    pso.setCompletado(p.getCompletado());
                    pso.setIdSesionFoco(p.getIdSesionFoco().toString());
                    pso.setIdSesionFocoAccionMejora(p.getIdSesionFocoAccionMejora().toString());
                    acciones.add(pso);
                });
                f.setAccionesMejora(acciones);
                sesionFocosPaso.add(f);
            }
        });
        result.setFocos(sesionFocosPaso);
        List<ObjetivoMejoraDTO> objMejora = new ArrayList<ObjetivoMejoraDTO>();
        List<ObjetivoMejora> objetivos = this.objetivoMejoraRepo.getByIdSesion(s.getIdSesion());
        objetivos.forEach(om ->{
            ObjetivoMejoraDTO obj = new ObjetivoMejoraDTO();
            obj.setIdObjetivoMejora(om.getIdObjetivoMejora().toString());
            obj.setIdSesion(om.getIdSesion().toString());
            obj.setNumero(om.getNumero());
            obj.setObjetivo(om.getObjetivo());
            objMejora.add(obj);
        });

        result.setObjetivos(objMejora);
        // obtener Participantes
        List<SesionRedParticipantes> parts = this.sesionRedParticipantesRepo.getByIdSesion(s.getIdSesion());

        Asesoria asp = this.asesoriaRepo.getById(s.getIdAsesoria());
        Red red = this.redRepo.getNetworkById(asp.getIdRed());
        List<Establecimientos> list = this.establecimientosIntegration.getEstablecimientos(null, null, red.getIdRegion().intValue(), red.getIdDeprov().intValue(), null, "", "", 999999);
        List<SesionRedParticipanteDTO> participantes = new ArrayList<SesionRedParticipanteDTO>();
        result.setTipoRed(red.getIdTipoRed().equals(Long.parseLong(RED_COMPLEMENTARIA)) ? "S" : "E");
        if(red.getIdTipoRed().equals(Long.parseLong(RED_COMPLEMENTARIA))){            
            List<RedSostenedor> sostenedores = redSostenedorRepo.getByIdRed(red.getIdRed());
            sostenedores.forEach(x -> {
                Establecimientos estPaso1 = list.stream().filter(
                        sost -> sost.getSostenedor() != null && sost.getSostenedor().getRutSostenedor().equals(x.getRut()))
                        .iterator().next();
                SesionRedParticipanteDTO e = new SesionRedParticipanteDTO();
                e.setIdParticipante(x.getIdRedSostenedor().toString());
                e.setTipoParticipante("S");
                e.setNumero(sostenedores.indexOf(x) + 1);
                e.setRbd(estPaso1.getSostenedor().getRutSostenedor());
                e.setParticipante(estPaso1.getSostenedor().getNombreSostenedor());
               
                participantes.add(e);
            });
        } else{
            List<RedEstablecimiento> establecimientos = redEstablecimientoRepo.getByIdRed(red.getIdRed());
            establecimientos.forEach(x -> {
                Establecimientos estPaso1 = list.stream().filter(est -> est.getRbd().equals(x.getRbd())).iterator().next();
                SesionRedParticipanteDTO e = new SesionRedParticipanteDTO();
                e.setIdParticipante(x.getIdRedEstablecimiento().toString());
                e.setTipoParticipante("E");
                e.setNumero(establecimientos.indexOf(x) + 1);
                e.setRbd(estPaso1.getRbd().toString());
                e.setParticipante(estPaso1.getNombre());

                Optional<SesionRedParticipantes> pa = parts.stream().filter(d -> d.getRbd().equals(Integer.parseInt(e.getRbd()))).findFirst();
                e.setPresente(pa.isPresent() && pa.get().getPresente());

                participantes.add(e);
            });
        }
        result.setParticipantes(participantes);
        
        return result;
    }

    public SesionRedInfoDetailDTO getSesionRedInfoDetail(String idAsesoria){
        List<FocoRedDTO> sesionFocosPaso = new ArrayList<FocoRedDTO>();

        int year = Calendar.getInstance().get(Calendar.YEAR);
        List<Foco> focos = focoRepo.getAllFocos().stream().filter(f -> f.getPeriodo().equals(year)).collect(Collectors.toList());

        Asesoria asesoriaPaso = asesoriaRepo.getById(Long.parseLong(idAsesoria));        
        List<Sesion> sesiones = this.sesionRepo.getByIdAsesoria(asesoriaPaso.getIdAsesoria());
        List<SesionFoco> sesionFocos = new ArrayList<SesionFoco>();
        sesiones.forEach(x -> {
            List<SesionFoco> sfs = this.sesionFocoRepo.getByIdSesion(x.getIdSesion());
            sfs.forEach(fs -> {
                Optional<SesionFoco> ps = sesionFocos.stream().filter(o -> o.getIdFoco().equals(fs.getIdFoco())).findAny();
                if (ps.isPresent()) {
                    fs.getAccionesMejora().forEach(m ->{
                        ps.get().getAccionesMejora().forEach(mc ->{
                            if (m.getAccionMejora().equals(mc.getAccionMejora()) && m.getCompletado().booleanValue()){
                                mc.setCompletado(m.getCompletado());
                            }
                        });
                    });
                } else {
                    sesionFocos.add(fs);
                }
            });
        });

        focos.forEach(x -> {
            List<SesionFocoAccionMejoraDTO> acciones = new ArrayList<SesionFocoAccionMejoraDTO>();
            Optional<SesionFoco> sfp = sesionFocos.stream().filter(f -> f.getIdFoco().equals(x.getIdFoco()))
                    .findFirst();
            if (sfp.isPresent()) {
                FocoRedDTO f = new FocoRedDTO();
                f.setIdFoco(x.getIdFoco().toString());
                f.setNombre(x.getNombre());
                if (!sfp.get().getAccionesMejora().isEmpty()) {
                    f.setLogro(Math.round(
                            (sfp.get().getAccionesMejora().stream().filter(acc -> acc.getCompletado()).count() * 100)
                                    / sfp.get().getAccionesMejora().size()));
                }

                sfp.get().getAccionesMejora().forEach(p -> {
                    SesionFocoAccionMejoraDTO pso = new SesionFocoAccionMejoraDTO();
                    pso.setAccionMejora(p.getAccionMejora());
                    pso.setCompletado(p.getCompletado());
                    pso.setIdSesionFoco(p.getIdSesionFoco().toString());
                    pso.setIdSesionFocoAccionMejora(p.getIdSesionFocoAccionMejora().toString());
                    acciones.add(pso);
                });
                f.setAccionesMejora(acciones);
                sesionFocosPaso.add(f);
            }
        });

        SesionRedInfoDetailDTO result = new SesionRedInfoDetailDTO();
        result.setIdAsesoria(idAsesoria);
        result.setFocos(sesionFocosPaso);
        return result;
    }

    public void setPrimeraPlanificacion(String idAsesoria, String fechaPlanificacion) {
        Date planificacion = StringHelper.convertToDateHour(fechaPlanificacion);

        if(this.sesionRepo.getByIdAsesoria(Long.parseLong(idAsesoria)).isEmpty()){
            Sesion sesionPaso = new Sesion();
            sesionPaso.setIdAsesoria(Long.parseLong(idAsesoria));
            sesionPaso.setIdSessionEstado(TYPE_SESION_PLANIFICADA);
            sesionPaso.setFechaPlanificacion(planificacion);
            sesionPaso.setFechaRealizada(null);
            sesionPaso.setUsuarioRegistro(UserHelper.getUsuarioRegistrado().getIdUsuario());
            sesionPaso.setFechaRegistro(new Date());

            this.sesionRepo.insert(sesionPaso);
        } else {
            Asesoria asesoria = this.asesoriaRepo.getById(Long.parseLong(idAsesoria));
            if(asesoria.getIdAsesoriaDirecta() != null){
                this.registraProximaSesion(asesoria.getIdAsesoria(), fechaPlanificacion);
            } else if(asesoria.getIdAsesoria() != null){
                this.registraProximaSesionRed(asesoria.getIdAsesoria(), fechaPlanificacion);
            }
        }        
    }

    @Transactional(rollbackFor = Throwable.class)
    private void registraProximaSesion(Long idAsesoria, String fechaPlanificacion) {
        Date planificacion = StringHelper.convertToDateHour(fechaPlanificacion);

        List<SesionFoco> sesionFoco = new ArrayList<SesionFoco>();
        List<Sesion> sesiones = this.sesionRepo.getByIdAsesoria(idAsesoria);
        if (!sesiones.isEmpty()) {
            Sesion s = sesiones.get(sesiones.size() - 1);
            List<SesionFoco> sf = this.sesionFocoRepo.getByIdSesion(s.getIdSesion());
            sf.forEach(m -> {
                if (m.getMovimientosClaves().stream().anyMatch(mc -> !mc.getCompletado())) {
                    sesionFoco.add(m);
                }
            });
        }

        Sesion sesionPaso = new Sesion();
        sesionPaso.setIdAsesoria(idAsesoria);
        sesionPaso.setIdSessionEstado(TYPE_SESION_PLANIFICADA);
        sesionPaso.setFechaPlanificacion(planificacion);
        sesionPaso.setFechaRealizada(null);
        sesionPaso.setUsuarioRegistro(UserHelper.getUsuarioRegistrado().getIdUsuario());
        sesionPaso.setFechaRegistro(new Date());

        this.sesionRepo.insert(sesionPaso);

        sesionFoco.forEach(sf -> {
            sf.setIdSesion(sesionPaso.getIdSesion());
            sf.setStartDate(new Date());
            sf.setEndDate(null);
            sf.setUsuarioRegistro(UserHelper.getUsuarioRegistrado().getIdUsuario());
            sf.setFechaRegistro(new Date());
            this.sesionFocoRepo.insert(sf);

            sf.getMovimientosClaves().forEach(smc -> {
                smc.setIdSesionFoco(sf.getIdSesionFoco());
                smc.setIdFoco(sf.getIdFoco());
                smc.setUsuarioRegistro(UserHelper.getUsuarioRegistrado().getIdUsuario());
                smc.setFechaRegistro(new Date());

                sesionMovimientosClavesRepo.insert(smc);
            });
        });
    }

    @Transactional(rollbackFor = Throwable.class)
    private void registraProximaSesionRed(Long idAsesoria, String fechaPlanificacion) {
        Date planificacion = StringHelper.convertToDateHour(fechaPlanificacion);

        List<ObjetivoMejora> objetivos = new ArrayList<ObjetivoMejora>();
        List<SesionFoco> sesionFoco = new ArrayList<SesionFoco>();
        List<Sesion> sesiones = this.sesionRepo.getByIdAsesoria(idAsesoria);
        if (!sesiones.isEmpty()) {
            Sesion s = sesiones.get(sesiones.size() - 1);
            List<SesionFoco> sf = this.sesionFocoRepo.getByIdSesion(s.getIdSesion());
            sf.forEach(m -> {                
                if(m.getAccionesMejora().stream().anyMatch(am -> !am.getCompletado())){
                    sesionFoco.add(m);
                }
            });

            this.objetivoMejoraRepo.getByIdSesion(s.getIdSesion()).forEach(o -> objetivos.add(o));
        }

        Sesion sesionPaso = new Sesion();
        sesionPaso.setIdAsesoria(idAsesoria);
        sesionPaso.setIdSessionEstado(TYPE_SESION_PLANIFICADA);
        sesionPaso.setFechaPlanificacion(planificacion);
        sesionPaso.setFechaRealizada(null);
        sesionPaso.setUsuarioRegistro(UserHelper.getUsuarioRegistrado().getIdUsuario());
        sesionPaso.setFechaRegistro(new Date());

        this.sesionRepo.insert(sesionPaso);

        objetivos.forEach(o ->{
            o.setIdSesion(sesionPaso.getIdSesion());
            o.setIdUsuarioRegistro(UserHelper.getUsuarioRegistrado().getIdUsuario());
            o.setFechaRegistro(new Date());
            this.objetivoMejoraRepo.insert(o);
        });

        sesionFoco.stream().forEach(sf -> {
            sf.setIdSesion(sesionPaso.getIdSesion());
            sf.setStartDate(new Date());
            sf.setEndDate(null);
            sf.setUsuarioRegistro(UserHelper.getUsuarioRegistrado().getIdUsuario());
            sf.setFechaRegistro(new Date());
            this.sesionFocoRepo.insert(sf);
            
            sf.getAccionesMejora().forEach(x ->{
                x.setIdSesionFocoAccionMejora(null);
                x.setIdSesionFoco(sf.getIdSesionFoco());
                x.setIdUsuarioRegistro(UserHelper.getUsuarioRegistrado().getIdUsuario());
                x.setFechaRegistro(new Date());

                this.sesionFocoAccionMejoraRepo.insert(x);
            });
        });
    }

    @Transactional(rollbackFor = Throwable.class)
    public void setRegistraSesion(RegistraSesionDTO sesion) {
        Sesion sesionPaso = this.sesionRepo.getById(Long.parseLong(sesion.getIdSesion()));
        sesionPaso.setFechaRealizada(new Date());
        sesionPaso.setIdSessionEstado(TYPE_SESION_REGISTRADA);
        sesionPaso.setIdSesionTipo(
                sesion.getTipoAsesoria().equalsIgnoreCase(TYPE_SESION_PRESENCIAL_TEXT) ? TYPE_SESION_PRESENCIAL
                        : TYPE_SESION_REMOTA);
        sesionPaso.setUsuarioRegistro(UserHelper.getUsuarioRegistrado().getIdUsuario());
        sesionPaso.setFechaRegistro(new Date());

        this.sesionRepo.update(sesionPaso);

        sesion.getFocos().forEach(f -> {
            SesionFoco sf = new SesionFoco();
            sf.setIdSesion(sesionPaso.getIdSesion());
            sf.setIdFoco(Long.parseLong(f.getIdFoco()));
            sf.setStartDate(new Date());
            sf.setEndDate(null);
            sf.setUsuarioRegistro(UserHelper.getUsuarioRegistrado().getIdUsuario());
            sf.setFechaRegistro(new Date());

            this.sesionFocoRepo.insert(sf);
            this.logger.trace(f);
            f.getAccionesMejoras().forEach(a -> {
                this.logger.trace(a);
                a.getMovimientosClaves().forEach(m -> {
                    SesionMovimientosClaves smc = new SesionMovimientosClaves();
                    smc.setIdSesionFoco(sf.getIdSesionFoco());
                    smc.setIdFoco(sf.getIdFoco());
                    smc.setIdMovimientosClaves(Long.parseLong(m.getIdMovimientosClaves()));
                    smc.setCompletado(m.getCompletado());
                    smc.setUsuarioRegistro(UserHelper.getUsuarioRegistrado().getIdUsuario());
                    smc.setFechaRegistro(new Date());

                    sesionMovimientosClavesRepo.insert(smc);
                });
            });
        });

        // registra proxima sesion        
        if(sesion.getFechaProximaReunion() != null && !sesion.getFechaProximaReunion().isEmpty()){
            registraProximaSesion(sesionPaso.getIdAsesoria(), sesion.getFechaProximaReunion());
        }
    }

    @Transactional(rollbackFor = Throwable.class)
    public void setRegistraSesionRed(RegistraSesionRedDTO sesion) {
        Sesion sesionPaso = this.sesionRepo.getById(Long.parseLong(sesion.getIdSesion()));
        sesionPaso.setFechaRealizada(new Date());
        sesionPaso.setIdSessionEstado(TYPE_SESION_REGISTRADA);
        sesionPaso.setIdSesionTipo(
                sesion.getTipoAsesoria().equalsIgnoreCase(TYPE_SESION_PRESENCIAL_TEXT) ? TYPE_SESION_PRESENCIAL
                        : TYPE_SESION_REMOTA);
        sesionPaso.setUsuarioRegistro(UserHelper.getUsuarioRegistrado().getIdUsuario());
        sesionPaso.setFechaRegistro(new Date());

        this.sesionRepo.update(sesionPaso);

        sesion.getFocos().forEach(f -> {
            SesionFoco sf = new SesionFoco();
            sf.setIdSesion(sesionPaso.getIdSesion());
            sf.setIdFoco(Long.parseLong(f.getIdFoco()));
            sf.setStartDate(new Date());
            sf.setEndDate(null);
            sf.setUsuarioRegistro(UserHelper.getUsuarioRegistrado().getIdUsuario());
            sf.setFechaRegistro(new Date());

            this.sesionFocoRepo.insert(sf);
            this.logger.trace(f);
            
            f.getAccionesMejora().forEach(a ->{
                SesionFocoAccionMejora sfam = new SesionFocoAccionMejora();
                sfam.setIdSesionFoco(sf.getIdSesionFoco());
                sfam.setAccionMejora(a.getAccionMejora());
                sfam.setCompletado(a.getCompletado());
                sfam.setIdUsuarioRegistro(UserHelper.getUsuarioRegistrado().getIdUsuario());
                sfam.setFechaRegistro(new Date());
                this.sesionFocoAccionMejoraRepo.insert(sfam);
            });           
        });

        sesion.getObjetivos().forEach(o ->{
            ObjetivoMejora om = new ObjetivoMejora();
            om.setIdSesion(sesionPaso.getIdSesion());
            om.setNumero(o.getNumero());
            om.setObjetivo(o.getObjetivo());
            om.setIdUsuarioRegistro(UserHelper.getUsuarioRegistrado().getIdUsuario());
            om.setFechaRegistro(new Date());

            this.objetivoMejoraRepo.insert(om);
        });

        sesion.getParticipantes().forEach(p ->{
            SesionRedParticipantes part = new SesionRedParticipantes();
            part.setIdSesion(sesionPaso.getIdSesion());
            part.setTipo(p.getTipoParticipante());
            if(p.getTipoParticipante().equals("E")){
                part.setRbd(Integer.parseInt(p.getRbd()));
            } else{
                part.setRutSostenedor(p.getRbd());
            }
            part.setPresente(p.getPresente());
            part.setIdUsuarioRegistro(UserHelper.getUsuarioRegistrado().getIdUsuario());
            part.setFechaRegistro(new Date());

            this.sesionRedParticipantesRepo.insert(part);
        });

        // registra proxima sesion
        if(sesion.getFechaProximaReunion() != null && !sesion.getFechaProximaReunion().isEmpty()){
            registraProximaSesionRed(sesionPaso.getIdAsesoria(), sesion.getFechaProximaReunion());
        }
        
    }

    public List<FocoDTO> getFocos() {
        List<FocoDTO> result = new ArrayList<FocoDTO>();
        int year = Calendar.getInstance().get(Calendar.YEAR);
        List<Foco> focos = focoRepo.getAllFocos().stream().filter(f -> f.getPeriodo().equals(year))
                .collect(Collectors.toList());
        focos.forEach(foco -> {
            FocoDTO focoDto = new FocoDTO();
            focoDto.setIdFoco(foco.getIdFoco().toString());
            focoDto.setNombre(foco.getNombre());
            focoDto.setDescripcion(foco.getDescripcion());
            List<AccionesMejorasDTO> acciones = new ArrayList<AccionesMejorasDTO>();
            foco.getAcciones().forEach(a -> {
                AccionesMejorasDTO am = new AccionesMejorasDTO();
                am.setIdAccionesMejoras(a.getIdAccionesMejoras().toString());
                am.setNombre(a.getNombre());
                List<MovimientosClavesDTO> movimientos = new ArrayList<MovimientosClavesDTO>();
                a.getMovimientos().forEach(m -> {
                    MovimientosClavesDTO mov = new MovimientosClavesDTO();
                    mov.setIdMovimientosClaves(m.getIdMovimientosClaves().toString());
                    mov.setNombre(m.getNombre());
                    movimientos.add(mov);
                });
                am.setMovimientosClaves(movimientos);
                acciones.add(am);
            });
            focoDto.setAccionesMejoras(acciones);
            result.add(focoDto);
        });
        return result;
    }

    public List<SesionFocoAccionMejoraDTO> getAccionesFoco(String idSesion, String idFoco){
        List<SesionFocoAccionMejoraDTO> result = new ArrayList<SesionFocoAccionMejoraDTO>();

        List<SesionFoco> sesionFocos = this.sesionFocoRepo.getByIdSesion(Long.parseLong(idSesion));
        if(!sesionFocos.isEmpty()){
            Optional<SesionFoco> sesionFoco = sesionFocos.stream().filter(f -> f.getIdFoco().equals(Long.parseLong(idFoco))).findFirst();
            if (sesionFoco.isPresent()) {
                List<SesionFocoAccionMejora> sfam = this.sesionFocoAccionMejoraRepo.getByIdSesionFoco(sesionFoco.get().getIdSesionFoco());
                sfam.forEach(p -> {
                    SesionFocoAccionMejoraDTO pso = new SesionFocoAccionMejoraDTO();
                    pso.setAccionMejora(p.getAccionMejora());
                    pso.setCompletado(p.getCompletado());
                    pso.setIdSesionFoco(p.getIdSesionFoco().toString());
                    pso.setIdSesionFocoAccionMejora(p.getIdSesionFocoAccionMejora().toString());
                    result.add(pso);
                });
            }
        }       

        return result;        
    }
}
