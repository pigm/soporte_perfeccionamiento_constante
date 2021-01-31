package cl.mineduc.came.apoyo_mejora_continua.services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import cl.mineduc.came.apoyo_mejora_continua.dto.feedback.FeedbackBeforeDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.feedback.FeedbackDetailDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.feedback.FeedbackListDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.feedback.FeedbackRequestDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.feedback.PlanificarFeedbackDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.feedback.SetFeedbackResponseDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.feedback.SupervisorDataDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.integration.region.DeprovsResponse;
import cl.mineduc.came.apoyo_mejora_continua.dto.integration.region.RegionResponseDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.shared.SelectorDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.users.UserEnvDTO;
import cl.mineduc.came.apoyo_mejora_continua.helpers.StringHelper;
import cl.mineduc.came.apoyo_mejora_continua.helpers.UserHelper;
import cl.mineduc.came.apoyo_mejora_continua.integration.RegionIntegration;
import cl.mineduc.came.apoyo_mejora_continua.modelo.Bitacora;
import cl.mineduc.came.apoyo_mejora_continua.modelo.Perfil;
import cl.mineduc.came.apoyo_mejora_continua.modelo.Periodo;
import cl.mineduc.came.apoyo_mejora_continua.modelo.Retroalimentacion;
import cl.mineduc.came.apoyo_mejora_continua.modelo.Supervisor;
import cl.mineduc.came.apoyo_mejora_continua.modelo.UsuarioPerfil;
import cl.mineduc.came.apoyo_mejora_continua.modelo.UsuarioRegistrado;
import cl.mineduc.came.apoyo_mejora_continua.repo.BitacoraService;
import cl.mineduc.came.apoyo_mejora_continua.repo.PerfilRepo;
import cl.mineduc.came.apoyo_mejora_continua.repo.PeriodoRepo;
import cl.mineduc.came.apoyo_mejora_continua.repo.RetroalimentacionRepo;
import cl.mineduc.came.apoyo_mejora_continua.repo.SupervisorRepo;
import cl.mineduc.came.apoyo_mejora_continua.repo.UsuarioRepo;


@Service
public class FeedbackService {
    
    // #region Constantes
    private static final String PERFIL_SUPERVISOR = "2416264829774857258";
    private static final String PERFIL_JEFE_TECNICO = "2416264829774857257";
    // #endregion

    @Autowired
    private RetroalimentacionRepo retroalimentacionRepo;

    @Autowired
    private RegionIntegration regionIntegration;

    @Autowired
    private UsuarioRepo usuarioRepo;

    @Autowired
    private PerfilRepo perfilRepo;
    
    @Autowired
    private PeriodoRepo periodRepo;

    @Autowired
    private SupervisorRepo supervisorRepo;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private BitacoraService bitacoraService;

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

    public List<String> getSupUserNames(Integer regionId, Integer deprovId) {
        List<String> result = new ArrayList<String>();
        List<UsuarioRegistrado> usuarios = usuarioRepo.findAll().stream().filter(x -> regionId == null || (x.getIdRegion() != null && x.getIdRegion().intValue() == regionId.intValue()))
                                                                         .filter(x -> deprovId == null || (x.getIdDeprov() != null && x.getIdDeprov().intValue() == deprovId.intValue()))
                                                                         .collect(Collectors.toList());

        usuarios.forEach(u -> {
            UsuarioPerfil pu = u.getPerfilesUsuarios().stream().filter(p -> p.getEndDate() == null).iterator().next();
            if (pu.getIdPerfil().equals(Long.parseLong(PERFIL_SUPERVISOR))) {
                result.add(u.getUsuarioDominio());
            }
        });
        return result;
    }

    public List<String> getJefeTecUserNames() {
        List<String> result = new ArrayList<String>();
        List<UsuarioRegistrado> usuarios = usuarioRepo.findAll();

        usuarios.forEach(u -> {
            UsuarioPerfil pu = u.getPerfilesUsuarios().stream().filter(p -> p.getEndDate() == null).iterator().next();
            if (pu.getIdPerfil().equals(Long.parseLong(PERFIL_JEFE_TECNICO))) {
                result.add(u.getUsuarioDominio());
            }
        });
        return result;
    }

    public SupervisorDataDTO getSupervisorData(String userName) {
        SupervisorDataDTO result = new SupervisorDataDTO();
        UsuarioRegistrado usuario = usuarioRepo.findByUsername(userName);
        if (usuario != null) {
            Perfil profileSup = perfilRepo.obtenerPerfil(Long.parseLong(PERFIL_SUPERVISOR));
            RegionResponseDTO regionPaso = regionIntegration.getRegionByNumber(usuario.getIdRegion());
            DeprovsResponse devPro = regionPaso.getDeprovs().stream()
                    .filter(d -> (long) d.getIdDeprov() == usuario.getIdDeprov()).iterator().next();

            result.setUsuarioId(usuario.getIdUsuario().toString());
            result.setUserName(userName);
            result.setNombre(usuario.getNombre() + " " + usuario.getPaterno() + " " + usuario.getMaterno());
            result.setPerfil(profileSup.getNombre());
            result.setRegion(regionPaso.getNombreRegion());
            result.setDeprov(devPro.getNombreDeprov());
        }
        return result;
    }

    public List<FeedbackListDTO> getFeedBackOthers(String idFeedback){
        List<FeedbackListDTO> result = new ArrayList<FeedbackListDTO>();

        List<Supervisor> supers = this.supervisorRepo.getAll();
        List<UsuarioRegistrado> usuarios = this.usuarioRepo.findAll();

        Retroalimentacion retroPaso =  this.retroalimentacionRepo.getById(Long.parseLong(idFeedback));
        List<Retroalimentacion> retros = this.retroalimentacionRepo.getByIdPeriodo(retroPaso.getIdPeriodo());
        if(!retros.isEmpty()){
            List<Retroalimentacion> retrosPaso = retros.stream()
                    .filter(x -> x.getIdUsuario().equals(retroPaso.getIdUsuario()))
                    .filter(x -> x.getIdSupervisor().equals(retroPaso.getIdSupervisor()))
                    .filter(x -> x.getIdRegion().equals(retroPaso.getIdRegion()))
                    .filter(x -> x.getIdDeprov().equals(retroPaso.getIdDeprov()))     
                    .filter(x -> !x.getIdRetroalimentacion().equals(Long.parseLong(idFeedback)))
                    .collect(Collectors.toList());
            
            retrosPaso.sort(Comparator.comparingLong(Retroalimentacion::getIdRetroalimentacion).reversed());
            
            retrosPaso.forEach(x ->{
                FeedbackListDTO paso = new FeedbackListDTO();
                paso.setIdFeedback(x.getIdRetroalimentacion().toString());
                Supervisor sp = supers.stream().filter(sup -> sup.getIdSupervisor().equals(x.getIdSupervisor())).findFirst().get();                
                paso.setIdSupervisor(sp.getIdSupervisor().toString());
                paso.setSupervisor(sp.getUsuario().getUsuarioDominio());

                Optional<UsuarioRegistrado> jefePaso = usuarios.stream().filter(j -> j.getIdUsuario().equals(x.getIdUsuario())).findFirst();
                if(jefePaso.isPresent()){
                    paso.setJefeTecnico(jefePaso.get().getUsuarioDominio());
                    paso.setIdUsuarioJefeTecnico(x.getIdUsuario().toString());
                }                

                RegionResponseDTO region = regionIntegration.getRegionByNumber((long)x.getIdRegion());
                paso.setRegion(region.getNombreRegion());
                DeprovsResponse devPaso = region.getDeprovs().stream().filter(r -> x.getIdDeprov().equals(r.getIdDeprov())).iterator().next();
                paso.setDeprov(devPaso.getNombreDeprov());
                paso.setSemestre(x.getSemestre() == 1 ? "1er semestre": "2er semestre");
                paso.setNumero(x.getNumero());
                paso.setFechaPlanificada(x.getFechaPlanificacion());
                paso.setFechaRealizada(x.getFechaRegistroSesion());
                result.add(paso);
            });
        }

        return result;
    }

    public List<FeedbackListDTO> getFeedBack(FeedbackRequestDTO request){
        List<FeedbackListDTO> result = new ArrayList<FeedbackListDTO>();        

        List<UsuarioRegistrado> usuarios = this.usuarioRepo.findAll();        
        UsuarioRegistrado usuarioJF = request.getJefeTecnico() != null && !request.getJefeTecnico().isEmpty() ? usuarios.stream()
                .filter(x -> x.getUsuarioDominio().equalsIgnoreCase(request.getJefeTecnico())).iterator().next() : null;

        List<Supervisor> supers = this.supervisorRepo.getAll();

        int year = Calendar.getInstance().get(Calendar.YEAR);
        Periodo period = periodRepo.getPeriodByYear(year);

        List<Retroalimentacion> retros = this.retroalimentacionRepo.getByIdPeriodo(period.getIdPeriodo());
        if(!retros.isEmpty()){
            List<Retroalimentacion> retrosPaso = retros.stream().filter(x -> (request.getJefeTecnico() == null || request.getJefeTecnico().isEmpty()) || x.getIdUsuario().equals(usuarioJF.getIdUsuario()))
                           .filter(x -> request.getIdRegion() == null || x.getIdRegion().equals(request.getIdRegion()))
                           .filter(x -> request.getIdDeprov() == null || x.getIdDeprov().equals(request.getIdDeprov()))
                           .filter(x -> request.getSemestre() == null || x.getSemestre().equals(request.getSemestre()))
                           .collect(Collectors.toList());

            Map<Long, List<Retroalimentacion>> retrosGroup = retrosPaso.stream().collect(Collectors.groupingBy(Retroalimentacion::getIdSupervisor));           

            retrosGroup.forEach((key, v) -> {
                Retroalimentacion x = v.stream().sorted(Comparator.comparing(Retroalimentacion::getNumero))
                        .collect(Collectors.toList()).get(v.size() - 1);
                FeedbackListDTO paso = new FeedbackListDTO();
                paso.setIdFeedback(x.getIdRetroalimentacion().toString());
                Supervisor sp = supers.stream().filter(sup -> sup.getIdSupervisor().equals(x.getIdSupervisor())).findFirst().get();                
                paso.setIdSupervisor(sp.getIdSupervisor().toString());
                paso.setSupervisor(sp.getUsuario().getUsuarioDominio());

                Optional<UsuarioRegistrado> jefePaso = usuarios.stream().filter(j -> j.getIdUsuario().equals(x.getIdUsuario())).findFirst();
                if(jefePaso.isPresent()){
                    paso.setJefeTecnico(jefePaso.get().getUsuarioDominio());
                    paso.setIdUsuarioJefeTecnico(x.getIdUsuario().toString());
                }                

                RegionResponseDTO region = regionIntegration.getRegionByNumber((long)x.getIdRegion());
                paso.setRegion(region.getNombreRegion());
                DeprovsResponse devPaso = region.getDeprovs().stream().filter(r -> x.getIdDeprov().equals(r.getIdDeprov())).iterator().next();
                paso.setDeprov(devPaso.getNombreDeprov());
                paso.setSemestre(x.getSemestre() == 1 ? "1er semestre": "2er semestre");
                paso.setNumero(x.getNumero());
                paso.setFechaPlanificada(x.getFechaPlanificacion());
                paso.setFechaRealizada(x.getFechaRegistroSesion());
                result.add(paso);
            });
        }

        return result;
    }

    public FeedbackDetailDTO getFeedbackDetail(String idFeedback){
        
        DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        
        Retroalimentacion retro = this.retroalimentacionRepo.getById(Long.parseLong(idFeedback));
        FeedbackDetailDTO result = new FeedbackDetailDTO();
        result.setIdFeedback(retro.getIdRetroalimentacion().toString());
        result.setNumero(retro.getNumero() != null ? retro.getNumero() : 1);
        // Supervisor
        Supervisor sup = this.supervisorRepo.getSupervisorById(retro.getIdSupervisor());
        result.setSupervisorNombre(sup.getUsuario().getNombre() + " " + sup.getUsuario().getPaterno() + " " + sup.getUsuario().getMaterno());
        result.setSupervisor(sup.getUsuario().getUsuarioDominio());
        RegionResponseDTO region = regionIntegration.getRegionByNumber((long) retro.getIdRegion());
        result.setRegion(region.getNombreRegion());
        DeprovsResponse devPaso = region.getDeprovs().stream().filter(r -> retro.getIdDeprov().equals(r.getIdDeprov())).iterator().next();
        result.setDeprov(devPaso.getNombreDeprov());
        result.setSemestre(retro.getSemestre() == 1 ? "1er semestre" : "2er semestre");
        result.setFechaRegistroSesion(retro.getFechaRegistroSesion() != null ? dateFormatter.format(retro.getFechaRegistroSesion()) : null);
        result.setIdMomentoAsesoria(retro.getIdMomentoAsesoria() != null ? retro.getIdMomentoAsesoria().toString() : null);
        result.setFechaRegistroJefeTecnico(retro.getFechaRegistroJefeTecnico() != null ? dateFormatter.format(retro.getFechaRegistroJefeTecnico()) : null);
        result.setPracticaAbordada(retro.getPracticaAbordada());
        result.setAcuerdos(retro.getAcuerdos());
        result.setAcciones(retro.getAcuerdos());
        result.setObservaciones(retro.getObservaciones());      

        List<FeedbackBeforeDTO> before = new ArrayList<FeedbackBeforeDTO>();
        if(result.getNumero() > 1){
            List<FeedbackListDTO> others = this.getFeedBackOthers(idFeedback);
            if (!others.isEmpty()) {
                Optional<FeedbackListDTO> paso = others.stream()
                        .filter(x -> x.getNumero().equals((result.getNumero() - 1))).findFirst();
                if (paso.isPresent()) {
                    Retroalimentacion retroOld = this.retroalimentacionRepo
                            .getById(Long.parseLong(paso.get().getIdFeedback()));
                    FeedbackBeforeDTO pa = new FeedbackBeforeDTO();
                    pa.setAcuerdo("Practica abordada");
                    pa.setAccion(retroOld.getPracticaAbordada());
                    before.add(pa);

                    FeedbackBeforeDTO aa = new FeedbackBeforeDTO();
                    aa.setAcuerdo("Acuerdos sobre aspectos a reforzar por el supervisor");
                    aa.setAccion(retroOld.getAcuerdos());
                    before.add(aa);

                    FeedbackBeforeDTO ac = new FeedbackBeforeDTO();
                    ac.setAcuerdo("Acciones a desarrollar por el supervisor");
                    ac.setAccion(retroOld.getAcciones());
                    before.add(ac);
                }
            }
        }        
        result.setFeedbackBefore(before);
        
        return result;
    }

    public void setPlanificarFeedback(PlanificarFeedbackDTO request){
        
        UriComponentsBuilder builder = ServletUriComponentsBuilder.fromCurrentRequest();
        UsuarioRegistrado usuario = usuarioRepo.findByUsername(request.getSupervisor());
        Supervisor sup = this.supervisorRepo.getAll().stream().filter(x -> x.getIdUsuario().equals(usuario.getIdUsuario()) && x.getEndDate() == null).iterator().next();

        UserEnvDTO userPaso = usuarioService.getUserEnv(UserHelper.getUsuarioRegistrado().getIdUsuario());
        
        int year = Calendar.getInstance().get(Calendar.YEAR);
        Periodo period = periodRepo.getPeriodByYear(year);

        Retroalimentacion retro = new Retroalimentacion();
        
        retro.setIdPeriodo(period.getIdPeriodo());
        retro.setIdSupervisor(sup.getIdSupervisor());
        retro.setIdUsuario(UserHelper.getUsuarioRegistrado().getIdUsuario());
        retro.setIdRetroalimentacionFrecuencia(Long.valueOf(request.getFrecuenciaId()));
        retro.setSemestre(request.getSemestre());
        retro.setFechaPlanificacion(StringHelper.convertToDate(request.getFechaPlanificacion()));

        retro.setIdRegion(Integer.parseInt(userPaso.getIdRegion()));
        retro.setIdDeprov(Integer.parseInt(userPaso.getIdDeprov()));

        retro.setIdUsuarioRegistro(UserHelper.getUsuarioRegistrado().getIdUsuario());
        retro.setFechaRegistro(new Date());

        this.retroalimentacionRepo.insert(retro);
        bitacoraService.accionBitacora(1, retro,builder);
    }

    @Transactional(rollbackFor = Throwable.class)
    public SetFeedbackResponseDTO setFeedback(FeedbackDetailDTO request){

        UriComponentsBuilder builder = ServletUriComponentsBuilder.fromCurrentRequest();
        Retroalimentacion retro = this.retroalimentacionRepo.getById(Long.parseLong(request.getIdFeedback()));
        retro.setNumero(request.getNumero());
        retro.setIdMomentoAsesoria(Long.parseLong(request.getIdMomentoAsesoria()));
        retro.setFechaRegistroSesion(StringHelper.convertToDateHour(request.getFechaRegistroSesion()));
        
        if(request.getFechaRegistroJefeTecnico() != null && !request.getFechaRegistroJefeTecnico().isEmpty()){
            retro.setFechaRegistroJefeTecnico(StringHelper.convertToDate(request.getFechaRegistroJefeTecnico()));
        }        
        
        retro.setPracticaAbordada(request.getPracticaAbordada());
        retro.setAcuerdos(request.getAcuerdos());
        retro.setAcciones(request.getAcciones());
        retro.setObservaciones(request.getObservaciones());

        retro.setIdUsuarioRegistro(UserHelper.getUsuarioRegistrado().getIdUsuario());
        retro.setFechaRegistro(new Date());

        this.retroalimentacionRepo.update(retro);
        // registra proxima planificacion
        Retroalimentacion nextRetro = new Retroalimentacion();
        nextRetro.setIdPeriodo(retro.getIdPeriodo());
        nextRetro.setNumero(retro.getNumero() + 1);
        nextRetro.setIdUsuario(retro.getIdUsuario());
        nextRetro.setIdSupervisor(retro.getIdSupervisor());
        nextRetro.setIdRetroalimentacionFrecuencia(retro.getIdRetroalimentacionFrecuencia());
        nextRetro.setSemestre(retro.getSemestre());
        nextRetro.setFechaPlanificacion(StringHelper.convertToDate(request.getFechaProxima()));

        nextRetro.setIdRegion(retro.getIdRegion());
        nextRetro.setIdDeprov(retro.getIdDeprov());

        nextRetro.setIdUsuarioRegistro(UserHelper.getUsuarioRegistrado().getIdUsuario());
        nextRetro.setFechaRegistro(new Date());

        this.retroalimentacionRepo.insert(nextRetro);

        retro.setIdSiguienteRetroalimentacion(nextRetro.getIdRetroalimentacion());
        this.retroalimentacionRepo.update(retro);
        
        bitacoraService.accionBitacora(2, nextRetro,builder);
        SetFeedbackResponseDTO result = new SetFeedbackResponseDTO();
        result.setIdFeedback(retro.getIdRetroalimentacion().toString());
        return result;
    }
}
