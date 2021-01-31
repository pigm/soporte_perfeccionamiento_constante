package cl.mineduc.came.apoyo_mejora_continua.services;

import static org.mockito.ArgumentMatchers.nullable;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.mail.Session;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cl.mineduc.came.apoyo_mejora_continua.dto.feedbackReport.FeedbackListDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.feedbackReport.FeedbackRequestDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.integration.region.RegionResponseDTO;
import cl.mineduc.came.apoyo_mejora_continua.helpers.StringHelper;
import cl.mineduc.came.apoyo_mejora_continua.dto.integration.region.DeprovsResponse;
import cl.mineduc.came.apoyo_mejora_continua.integration.RegionIntegration;
import cl.mineduc.came.apoyo_mejora_continua.modelo.Periodo;
import cl.mineduc.came.apoyo_mejora_continua.modelo.Retroalimentacion;
import cl.mineduc.came.apoyo_mejora_continua.modelo.Supervisor;
import cl.mineduc.came.apoyo_mejora_continua.modelo.UsuarioRegistrado;
import cl.mineduc.came.apoyo_mejora_continua.repo.PeriodoRepo;
import cl.mineduc.came.apoyo_mejora_continua.repo.RetroalimentacionRepo;
import cl.mineduc.came.apoyo_mejora_continua.repo.SupervisorRepo;
import cl.mineduc.came.apoyo_mejora_continua.repo.UsuarioRepo;

@Service
public class FeedbackReportService {
    private static Logger logger = LogManager.getLogger(FeedbackReportService.class);

    // #region Constantes
    private static final long FRECUENCIA_SEMANAL = 2484537666033419784l;
    private static final long FRECUENCIA_QUINCENAL = 2484537666050197001l;
    private static final long FRECUENCIA_MENSUAL = 2484537666050197002l;
    // #endregion

    @Autowired
    private RetroalimentacionRepo retroalimentacionRepo;

    @Autowired
    private RegionIntegration regionIntegration;

    @Autowired
    private UsuarioRepo usuarioRepo;

    @Autowired
    private PeriodoRepo periodRepo;

    @Autowired
    private SupervisorRepo supervisorRepo;

    @Autowired
    private UsuarioService usuarioService;

    private String getFrecuencia(long frecuencia){
        String result = "";

        if(frecuencia == FRECUENCIA_SEMANAL){
            result = "Semanal";
        } else if(frecuencia == FRECUENCIA_QUINCENAL){
            result = "Quincenal";
        } else if (frecuencia == FRECUENCIA_MENSUAL) {
            result = "Mensual";
        }

        return result;
    }
    
    public List<FeedbackListDTO> getFeedBack(FeedbackRequestDTO request) {
        List<FeedbackListDTO> result = new ArrayList<FeedbackListDTO>();

        DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
        DateFormat hourFormatter = new SimpleDateFormat("HH:mm");

        List<UsuarioRegistrado> usuarios = this.usuarioRepo.findAll();        
        List<Supervisor> supers = this.supervisorRepo.getAll();

        int year = Calendar.getInstance().get(Calendar.YEAR);
        Periodo period = periodRepo.getPeriodByYear(year);
        
        List<Retroalimentacion> retros = this.retroalimentacionRepo.getByIdPeriodo(period.getIdPeriodo());
        if (!retros.isEmpty()) {
            List<Retroalimentacion> retrosPaso = retros.stream()
                                                       .filter(x -> x.getFechaRegistroSesion() != null)
                                                       .filter(x -> request.getStartDate().isEmpty() || x.getFechaRegistroSesion().getTime() >= StringHelper.convertToDate(request.getStartDate()).getTime())
                                                       .filter(x -> request.getEndDate().isEmpty() || x.getFechaRegistroSesion().getTime() <= StringHelper.convertToDate(request.getEndDate()).getTime())
                                                       .collect(Collectors.toList());

            Map<Long, List<Retroalimentacion>> retrosGroup = retrosPaso.stream().collect(Collectors.groupingBy(Retroalimentacion::getIdSupervisor));

            retrosPaso.forEach(x ->{
                FeedbackListDTO paso = new FeedbackListDTO();
                RegionResponseDTO region = regionIntegration.getRegionByNumber((long) x.getIdRegion());
                paso.setRegion(region.getNombreRegion());
                DeprovsResponse devPaso = region.getDeprovs().stream().filter(r -> x.getIdDeprov().equals(r.getIdDeprov())).iterator().next();
                paso.setDeprov(devPaso.getNombreDeprov());
                paso.setSemestre(x.getSemestre() == 1 ? "1er semestre" : "2er semestre");

                Optional<UsuarioRegistrado> jefePaso = usuarios.stream().filter(j -> j.getIdUsuario().equals(x.getIdUsuario())).findFirst();
                if (jefePaso.isPresent()) {
                    paso.setJefeTecnico(jefePaso.get().getUsuarioDominio());                 
                }
                Supervisor sp = supers.stream().filter(sup -> sup.getIdSupervisor().equals(x.getIdSupervisor())).findFirst().get();
                paso.setSupervisor(sp.getUsuario().getUsuarioDominio());                
                paso.setFrecuencia(this.getFrecuencia(x.getIdRetroalimentacionFrecuencia()));

                paso.setFechaPlanificada(dateFormatter.format(x.getFechaPlanificacion()));
                paso.setFechaRealizada(dateFormatter.format(x.getFechaRegistroSesion()));
                paso.setHoraRealizada(hourFormatter.format(x.getFechaRegistroSesion()));
                paso.setTipoRetroalimentacion("");
                paso.setFechaAcompaniamiento(x.getFechaRegistroJefeTecnico() != null ? dateFormatter.format(x.getFechaRegistroJefeTecnico()) : "");

                paso.setFocoAbordado(x.getPracticaAbordada());
                paso.setAspectosReforzar(x.getAcuerdos());
                paso.setAcciones(x.getAcciones());

                paso.setResponsable("");
                paso.setPlazos("");
                paso.setComentarios(x.getObservaciones());
                
                if(x.getIdSiguienteRetroalimentacion() != null){
                    Retroalimentacion next = this.retroalimentacionRepo.getById(x.getIdSiguienteRetroalimentacion());
                    paso.setFechaProxima(dateFormatter.format(next.getFechaPlanificacion()));
                }

                result.add(paso);
            });
        }

        return result;
    }

}
