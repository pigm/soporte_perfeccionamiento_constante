package cl.mineduc.came.apoyo_mejora_continua.services;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cl.mineduc.came.apoyo_mejora_continua.dto.integration.region.RegionResponseDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.shared.SelectorDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.survey.AnswerDetailDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.survey.GetSurveyDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.survey.QuestionDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.survey.SurveyAnswerExcelDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.survey.SurveyCompleteDetailDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.survey.SurveyDataDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.survey.SurveyExcelDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.survey.SurveyForAnswerDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.survey.SurveyListDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.survey.SuveyAnswerDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.users.ProfileSelectorDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.users.UserEnvDTO;
import cl.mineduc.came.apoyo_mejora_continua.helpers.StringHelper;
import cl.mineduc.came.apoyo_mejora_continua.helpers.UserHelper;
import cl.mineduc.came.apoyo_mejora_continua.integration.RegionIntegration;
import cl.mineduc.came.apoyo_mejora_continua.modelo.Encuesta;
import cl.mineduc.came.apoyo_mejora_continua.modelo.EncuestaObservacion;
import cl.mineduc.came.apoyo_mejora_continua.modelo.EncuestaPregunta;
import cl.mineduc.came.apoyo_mejora_continua.modelo.EncuestaRespuesta;
import cl.mineduc.came.apoyo_mejora_continua.modelo.Perfil;
import cl.mineduc.came.apoyo_mejora_continua.modelo.PerfilEncuesta;
import cl.mineduc.came.apoyo_mejora_continua.modelo.Periodo;
import cl.mineduc.came.apoyo_mejora_continua.modelo.UsuarioPerfil;
import cl.mineduc.came.apoyo_mejora_continua.modelo.UsuarioRegistrado;
import cl.mineduc.came.apoyo_mejora_continua.repo.EncuestaObservacionRepo;
import cl.mineduc.came.apoyo_mejora_continua.repo.EncuestaPreguntaRepo;
import cl.mineduc.came.apoyo_mejora_continua.repo.EncuestaRepo;
import cl.mineduc.came.apoyo_mejora_continua.repo.EncuestaRespuestaRepo;
import cl.mineduc.came.apoyo_mejora_continua.repo.PerfilEncuestaRepo;
import cl.mineduc.came.apoyo_mejora_continua.repo.PerfilRepo;
import cl.mineduc.came.apoyo_mejora_continua.repo.PeriodoRepo;
import cl.mineduc.came.apoyo_mejora_continua.repo.UsuarioPerfilRepo;
import cl.mineduc.came.apoyo_mejora_continua.repo.UsuarioRepo;

@Service
public class SurveyService {

    @Autowired
    private PerfilRepo perfilRepo;

    @Autowired
    private UsuarioRepo usuarioRepo;

    @Autowired
    private UsuarioPerfilRepo usuarioPerfilRepo;

    @Autowired
    private RegionIntegration regionIntegration;

    @Autowired
    private EncuestaRepo encuestaRepo;

    @Autowired
    private EncuestaPreguntaRepo encuestaPreguntaRepo;

    @Autowired
    private EncuestaRespuestaRepo encuestaRespuestaRepo;

    @Autowired
    private EncuestaObservacionRepo encuestaObservacionRepo;

    @Autowired
    private PerfilEncuestaRepo perfilEncuestaRepo;

    @Autowired
    private PeriodoRepo periodRepo;

    @Autowired
    private UsuarioService usuarioService;

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

    public List<ProfileSelectorDTO> getProfiles() {
        List<ProfileSelectorDTO> result = new ArrayList<ProfileSelectorDTO>();
        List<Perfil> profilesPaso = this.perfilRepo.obtenerPerfiles().stream().filter(p -> p.getHabilitado())
                .collect(Collectors.toList());

        profilesPaso.forEach(p -> {
            ProfileSelectorDTO selPaso = new ProfileSelectorDTO();
            selPaso.setValue(p.getIdPerfil().toString());
            selPaso.setDisplayText(p.getNombre());
            selPaso.setNivel(p.getPerfilNivel().getNombre());

            result.add(selPaso);
        });

        return result;
    }

    @Transactional(rollbackFor = Throwable.class)
    public void setSurvey(SurveyDataDTO request) {

        int year = Calendar.getInstance().get(Calendar.YEAR);
        Periodo period = periodRepo.getPeriodByYear(year);
        // #region Encuesta
        Encuesta encPaso = new Encuesta();
        encPaso.setIdPeriodo(period.getIdPeriodo());
        encPaso.setNombre(request.getNombre());
        encPaso.setFechaInicio(StringHelper.convertToDate(request.getFechaInicio()));
        encPaso.setFechaFin(StringHelper.convertToDate(request.getFechaFin()));
        encPaso.setPermiteComentarios(request.getPermiteObservacion());
        encPaso.setEstado(true);
        encPaso.setIdUsuarioRegistro(UserHelper.getUsuarioRegistrado().getIdUsuario());
        encPaso.setFechaRegistro(new Date());

        if (request.getIdSurvey() == null || request.getIdSurvey().isEmpty()) {
            encPaso.setIdUsuarioCreacion(UserHelper.getUsuarioRegistrado().getIdUsuario());
            encPaso.setFechaCreacion(new Date());
            this.encuestaRepo.insert(encPaso);
        } else {
            encPaso.setIdEncuesta(Long.parseLong(request.getIdSurvey()));
            this.encuestaRepo.updateById(encPaso);
        }
        // #endregion

        // #region Preguntas
        List<EncuestaPregunta> encPregs = this.encuestaPreguntaRepo.getByIdEncuesta(encPaso.getIdEncuesta());
        if (!encPregs.isEmpty()) {
            encPregs.forEach(ep -> {
                if (request.getPreguntas().stream().noneMatch(x -> ep.getPregunta().equalsIgnoreCase(x))) {
                    if (this.encuestaRespuestaRepo.getByIdPregunta(ep.getIdEncuestaPregunta()).isEmpty()) {
                        this.encuestaPreguntaRepo.deleteById(ep.getIdEncuestaPregunta());
                    } else {
                        ep.setEstado(false);
                        this.encuestaPreguntaRepo.updateById(ep);
                    }
                } else {
                    request.getPreguntas().remove(request.getPreguntas().indexOf(ep.getPregunta()));
                }
            });
        }

        request.getPreguntas().forEach(p -> {
            EncuestaPregunta ep = new EncuestaPregunta();
            ep.setIdEncuesta(encPaso.getIdEncuesta());
            ep.setPregunta(p);
            ep.setEstado(true);
            ep.setIdUsuarioRegistro(UserHelper.getUsuarioRegistrado().getIdUsuario());
            ep.setFechaRegistro(new Date());

            this.encuestaPreguntaRepo.insert(ep);
        });
        // #endregion

        // #region Perfiles
        List<PerfilEncuesta> perfEncs = this.perfilEncuestaRepo.getByIdEncuesta(encPaso.getIdEncuesta());
        if (!perfEncs.isEmpty()) {
            perfEncs.stream().forEach(pe -> {
                this.perfilEncuestaRepo.deleteById(pe.getIdPerfilEncuesta());
            });
        }

        request.getPerfiles().forEach(p -> {
            PerfilEncuesta pePaso = new PerfilEncuesta();
            pePaso.setIdEncuesta(encPaso.getIdEncuesta());
            pePaso.setIdPerfil(Long.parseLong(p));
            pePaso.setEstado(true);
            pePaso.setIdUsuarioRegistro(UserHelper.getUsuarioRegistrado().getIdUsuario());
            pePaso.setFechaRegistro(new Date());
            this.perfilEncuestaRepo.insert(pePaso);
        });
        // #endregion
    }

    public void setSurveyStatus(String idSurvey, Boolean status) {
        Encuesta encuesta = this.encuestaRepo.getById(Long.parseLong(idSurvey));
        encuesta.setEstado(status);
        this.encuestaRepo.updateById(encuesta);
    }

    public List<SurveyListDTO> getSurvey(GetSurveyDTO request) {
        List<SurveyListDTO> result = new ArrayList<SurveyListDTO>();

        int year = Calendar.getInstance().get(Calendar.YEAR);
        Periodo period = periodRepo.getPeriodByYear(year);

        List<Perfil> perfiles = this.perfilRepo.obtenerPerfiles();
        DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");

        List<Encuesta> encuestas = this.encuestaRepo.getByIdPeriodo(period.getIdPeriodo()).stream()
                .filter(e -> request.getEstado() == null || e.getEstado().equals(request.getEstado()))
                .collect(Collectors.toList());

        encuestas.forEach(e -> {
            List<PerfilEncuesta> perfilEncuestas = this.perfilEncuestaRepo.getByIdEncuesta(e.getIdEncuesta());
            if (request.getIdPerfil() == null || request.getIdPerfil().isEmpty() || perfilEncuestas.stream()
                    .anyMatch(o -> o.getIdPerfil().equals(Long.parseLong(request.getIdPerfil())))) {
                SurveyListDTO item = new SurveyListDTO();
                item.setIdSurvey(e.getIdEncuesta().toString());
                item.setNombre(e.getNombre());
                List<String> destinatarios = new ArrayList<String>();
                perfilEncuestas.forEach(pe -> {
                    Optional<Perfil> perfil = perfiles.stream().filter(x -> x.getIdPerfil().equals(pe.getIdPerfil()))
                            .findFirst();
                    if (perfil.isPresent()) {
                        destinatarios.add(perfil.get().getNombre());
                    }
                });
                item.setPerfiles(destinatarios);
                item.setFechaInicio(dateFormatter.format(e.getFechaInicio()));
                item.setFechaFin(dateFormatter.format(e.getFechaFin()));
                item.setEstado(e.getEstado());
                result.add(item);
            }
        });

        return result;
    }

    public SurveyExcelDTO getSurveyExcel(String idSurvey) {
        SurveyExcelDTO result = new SurveyExcelDTO();

        List<Perfil> perfiles = this.perfilRepo.obtenerPerfiles();
        DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");

        List<UsuarioRegistrado> usuarios = this.usuarioRepo.findAll();

        List<SurveyAnswerExcelDTO> items = new ArrayList<SurveyAnswerExcelDTO>();

        Encuesta e = this.encuestaRepo.getById(Long.parseLong(idSurvey));
        List<PerfilEncuesta> perfilEncuestas = this.perfilEncuestaRepo.getByIdEncuesta(e.getIdEncuesta());
        List<String> header = Stream.of("Nombre", "Perfiles", "Fecha Inicio", "Fecha Fin", "Usuario")
                .collect(Collectors.toList());
        List<String> destinatarios = new ArrayList<String>();
        perfilEncuestas.forEach(pe -> {
            Optional<Perfil> perfil = perfiles.stream().filter(x -> x.getIdPerfil().equals(pe.getIdPerfil()))
                    .findFirst();
            if (perfil.isPresent()) {
                destinatarios.add(perfil.get().getNombre());
            }
        });

        List<List<Object>> respuestaPaso = new ArrayList<List<Object>>();
        List<EncuestaPregunta> encuestaPreguntas = this.encuestaPreguntaRepo.getByIdEncuesta(e.getIdEncuesta());
        for (EncuestaPregunta ep : encuestaPreguntas) {
            header.add(ep.getPregunta());
            List<EncuestaRespuesta> resp = this.encuestaRespuestaRepo.getByIdPregunta(ep.getIdEncuestaPregunta());
            resp.forEach(x -> {
                respuestaPaso.add(Arrays.asList(x.getIdUsuario(), x.getIdEncuestaPregunta(), x.getValor()));
            });
        }

        header.add("comentario");
        List<EncuestaObservacion> obs = this.encuestaObservacionRepo.getByIdEncuesta(e.getIdEncuesta());
        respuestaPaso.stream().collect(Collectors.groupingBy(x -> x.get(0))).forEach((key, values) -> {
            SurveyAnswerExcelDTO item = new SurveyAnswerExcelDTO();
            item.setNombre(e.getNombre());
            item.setPerfiles(StringUtils.join(destinatarios, ";"));
            item.setFechaInicio(dateFormatter.format(e.getFechaInicio()));
            item.setFechaFin(dateFormatter.format(e.getFechaFin()));
            UsuarioRegistrado usr = usuarios.stream()
                    .filter(u -> u.getIdUsuario().equals(Long.parseLong(key.toString()))).iterator().next();
            item.setUsuario(usr.getUsuarioDominio());
            item.setRespuestas(
                    values.stream().map(r -> Integer.parseInt(r.get(2).toString())).collect(Collectors.toList()));

            Optional<EncuestaObservacion> op = obs.stream()
                    .filter(c -> c.getIdUsuario().equals(Long.parseLong(key.toString()))).findFirst();
            if (op.isPresent()) {
                item.setComentario(op.get().getObservacion());
            }
            items.add(item);
        });
        result.setHeader(header);
        result.setItems(items);

        return result;
    }

    public SurveyDataDTO getSurveyDetail(String idSurvey) {
        SurveyDataDTO result = new SurveyDataDTO();

        Encuesta encuesta = this.encuestaRepo.getById(Long.parseLong(idSurvey));
        result.setIdSurvey(encuesta.getIdEncuesta().toString());
        result.setNombre(encuesta.getNombre());
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        result.setFechaInicio(dateFormatter.format(encuesta.getFechaInicio()));
        result.setFechaFin(dateFormatter.format(encuesta.getFechaFin()));
        result.setPermiteObservacion(encuesta.getPermiteComentarios());
        List<PerfilEncuesta> perfilEncuestas = this.perfilEncuestaRepo.getByIdEncuesta(encuesta.getIdEncuesta());
        List<String> perfiles = new ArrayList<String>();
        perfilEncuestas.forEach(pe -> perfiles.add(pe.getIdPerfil().toString()));
        result.setPerfiles(perfiles);
        List<String> preguntas = new ArrayList<String>();
        List<EncuestaPregunta> encuestaPreguntas = this.encuestaPreguntaRepo.getByIdEncuesta(encuesta.getIdEncuesta());
        encuestaPreguntas.forEach(ep -> preguntas.add(ep.getPregunta()));
        result.setPreguntas(preguntas);

        return result;
    }

    public String getSurveyForUserProfile() {
        UserEnvDTO useEnv = usuarioService.getUserEnv(UserHelper.getUsuarioRegistrado().getIdUsuario());
        int year = Calendar.getInstance().get(Calendar.YEAR);
        List<Long> surveys = new ArrayList<Long>();
        Periodo period = periodRepo.getPeriodByYear(year);
        if(period != null){
            long today = new Date().getTime();
            List<Encuesta> encuestasPeriodo = this.encuestaRepo.getByIdPeriodo(period.getIdPeriodo());
            if (!encuestasPeriodo.isEmpty()) {
                List<Encuesta> encuestas = encuestasPeriodo.stream()
                        .filter(e -> e.getFechaInicio().getTime() <= today && e.getFechaFin().getTime() >= today)
                        .collect(Collectors.toList());
                encuestas.forEach(e -> {
                    Optional<PerfilEncuesta> pe = this.perfilEncuestaRepo.getByIdEncuesta(e.getIdEncuesta()).stream()
                            .filter(x -> x.getIdPerfil().equals(Long.parseLong(useEnv.getIdPerfil()))).findFirst();
                    if (pe.isPresent()) {
                        List<EncuestaPregunta> preguntas = this.encuestaPreguntaRepo.getByIdEncuesta(e.getIdEncuesta());
                        preguntas.forEach(p -> {
                            if (this.encuestaRespuestaRepo.getByIdPregunta(p.getIdEncuestaPregunta()).stream()
                                    .noneMatch(pr -> pr.getIdUsuario()
                                            .equals(UserHelper.getUsuarioRegistrado().getIdUsuario()))) {
                                surveys.add(e.getIdEncuesta());
                            }
                        });
                    }
                });
            }
        }
        
        return surveys.isEmpty() ? "" : surveys.get(0).toString();
    }

    public SurveyForAnswerDTO getSurveyForAnswer(String idSurvey) {
        SurveyForAnswerDTO result = new SurveyForAnswerDTO();

        Encuesta encuesta = this.encuestaRepo.getById(Long.parseLong(idSurvey));
        result.setIdSurvey(encuesta.getIdEncuesta().toString());
        result.setNombre(encuesta.getNombre());
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        result.setFechaInicio(dateFormatter.format(encuesta.getFechaInicio()));
        result.setFechaFin(dateFormatter.format(encuesta.getFechaFin()));
        result.setPermiteObservacion(encuesta.getPermiteComentarios());

        List<QuestionDTO> preguntas = new ArrayList<QuestionDTO>();
        List<EncuestaPregunta> encuestaPreguntas = this.encuestaPreguntaRepo.getByIdEncuesta(encuesta.getIdEncuesta());
        encuestaPreguntas.forEach(ep -> {
            QuestionDTO qp = new QuestionDTO();
            qp.setIdQuestion(ep.getIdEncuestaPregunta().toString());
            qp.setQuestion(ep.getPregunta());
            preguntas.add(qp);
        });
        result.setPreguntas(preguntas);

        return result;
    }

    @Transactional(rollbackFor = Throwable.class)
    public void answerSurvey(SuveyAnswerDTO request) {
        UserEnvDTO useEnv = usuarioService.getUserEnv(UserHelper.getUsuarioRegistrado().getIdUsuario());

        request.getAnswers().forEach(a -> {
            EncuestaRespuesta er = new EncuestaRespuesta();
            er.setIdEncuestaPregunta(Long.parseLong(a.getIdQuestion()));
            er.setIdUsuario(UserHelper.getUsuarioRegistrado().getIdUsuario());
            er.setIdPerfil(Long.parseLong(useEnv.getIdPerfil()));
            er.setValor(a.getAnswer());
            er.setFechaRespuesta(new Date());

            this.encuestaRespuestaRepo.insert(er);
        });

        Encuesta encuesta = this.encuestaRepo.getById(Long.parseLong(request.getIdSurvey()));
        if (encuesta.getPermiteComentarios().booleanValue()) {
            EncuestaObservacion eo = new EncuestaObservacion();
            eo.setIdEncuesta(Long.parseLong(request.getIdSurvey()));
            eo.setIdUsuario(UserHelper.getUsuarioRegistrado().getIdUsuario());
            eo.setIdPerfil(Long.parseLong(useEnv.getIdPerfil()));
            eo.setObservacion(request.getObservation());
            eo.setFechaObservacion(new Date());

            this.encuestaObservacionRepo.insert(eo);
        }
    }

    public SurveyCompleteDetailDTO getSurveyCompleteDetail(String idSurvey) {
        SurveyCompleteDetailDTO result = new SurveyCompleteDetailDTO();
        DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
        Encuesta encuesta = this.encuestaRepo.getById(Long.parseLong(idSurvey));
        result.setIdSurvey(encuesta.getIdEncuesta().toString());
        result.setNombre(encuesta.getNombre());
        result.setFechaInicio(dateFormatter.format(encuesta.getFechaInicio()));
        result.setFechaFin(dateFormatter.format(encuesta.getFechaFin()));
        List<PerfilEncuesta> perfilEncuestas = this.perfilEncuestaRepo.getByIdEncuesta(encuesta.getIdEncuesta());
        List<String> destinatarios = new ArrayList<String>();
        List<Perfil> perfiles = this.perfilRepo.obtenerPerfiles();
        List<UsuarioPerfil> usuarioPerfiles = this.usuarioPerfilRepo.getAll().stream()
                .filter(x -> x.getEndDate() == null).collect(Collectors.toList());
        perfilEncuestas.forEach(pe -> {
            Optional<Perfil> perfil = perfiles.stream().filter(x -> x.getIdPerfil().equals(pe.getIdPerfil()))
                    .findFirst();
            if (perfil.isPresent()) {
                destinatarios.add(perfil.get().getNombre());
                result.setEnviadas((int) usuarioPerfiles.stream()
                        .filter(x -> x.getIdPerfil().equals(perfil.get().getIdPerfil())).count());
            }
        });
        result.setPerfiles(destinatarios);
        List<AnswerDetailDTO> questions = new ArrayList<AnswerDetailDTO>();
        List<EncuestaPregunta> preguntas = this.encuestaPreguntaRepo.getByIdEncuesta(encuesta.getIdEncuesta());
        List<Long> userResposes = new ArrayList<Long>();
        preguntas.forEach(x -> {
            AnswerDetailDTO ans = new AnswerDetailDTO();
            List<EncuestaRespuesta> respuesta = this.encuestaRespuestaRepo.getByIdPregunta(x.getIdEncuestaPregunta());
            ans.setAverage((int) respuesta.stream().mapToInt(EncuestaRespuesta::getValor).average().orElse(0));
            ans.setNumber(preguntas.indexOf(x) + 1);
            ans.setQuestion(x.getPregunta());
            questions.add(ans);

            userResposes.addAll(this.encuestaRespuestaRepo.getByIdPregunta(x.getIdEncuestaPregunta()).stream()
                    .map(u -> u.getIdUsuario()).collect(Collectors.toList()));
        });
        result.setPreguntas(questions);

        result.setRespondidas(userResposes.stream().collect(Collectors.groupingBy(x -> x)).size());

        List<String> obsPaso = new ArrayList<String>();
        List<EncuestaObservacion> encObs = this.encuestaObservacionRepo.getByIdEncuesta(encuesta.getIdEncuesta());
        encObs.forEach(o -> obsPaso.add(o.getObservacion()));
        result.setObservaciones(obsPaso);

        return result;
    }

}
