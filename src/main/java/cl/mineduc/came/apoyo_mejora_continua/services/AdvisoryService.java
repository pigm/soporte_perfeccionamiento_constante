package cl.mineduc.came.apoyo_mejora_continua.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.mineduc.came.apoyo_mejora_continua.dto.shared.SelectorDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.users.UserEnvDTO;
import cl.mineduc.came.apoyo_mejora_continua.helpers.StringHelper;
import cl.mineduc.came.apoyo_mejora_continua.helpers.UserHelper;
import cl.mineduc.came.apoyo_mejora_continua.integration.EstablecimientosIntegration;
import cl.mineduc.came.apoyo_mejora_continua.integration.RegionIntegration;
import cl.mineduc.came.apoyo_mejora_continua.modelo.AsesoriaDirecta;
import cl.mineduc.came.apoyo_mejora_continua.modelo.Periodo;
import cl.mineduc.came.apoyo_mejora_continua.modelo.PeriodoConformacionRedes;
import cl.mineduc.came.apoyo_mejora_continua.modelo.api.Establecimientos;
import cl.mineduc.came.apoyo_mejora_continua.repo.AsesoriaDirectaRepo;
import cl.mineduc.came.apoyo_mejora_continua.repo.PeriodoRepo;
import cl.mineduc.came.apoyo_mejora_continua.dto.advisory.AdvisoryDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.advisory.AdvisoryListDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.advisory.AdvisoryRequestDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.integration.region.ComunaResponse;
import cl.mineduc.came.apoyo_mejora_continua.dto.integration.region.DeprovsResponse;
import cl.mineduc.came.apoyo_mejora_continua.dto.integration.region.RegionResponseDTO;

@Service
public class AdvisoryService {
    private static Logger logger = LogManager.getLogger(AdvisoryService.class);

    @Autowired
    private EstablecimientosIntegration establecimientosIntegration;

    @Autowired
    private RegionIntegration regionIntegration;

    @Autowired
    private PeriodoRepo periodRepo;

    @Autowired
    private AsesoriaDirectaRepo asesoriaDirectaRepo;

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

    public List<SelectorDTO> getEstablecimientos(Integer regionId, Integer deprovId, Integer idComuna) {
        List<SelectorDTO> result = new ArrayList<SelectorDTO>();
        Integer estado = 1;
                        
        List<Establecimientos> list = this.establecimientosIntegration.getEstablecimientos(estado, null,
                regionId, deprovId, idComuna, "", "", 999999);

        list.forEach(x -> {
            if(idComuna == null || x.getCodigoGeografico().getCodigoComuna().equals(idComuna)){
                SelectorDTO paso1 = new SelectorDTO();
                paso1.setValue(x.getRbd().toString());
                paso1.setDisplayText(x.getRbd().toString() + "-" + x.getNombre());
                result.add(paso1);
            }
        });      

        return result.stream().sorted(Comparator.comparing(SelectorDTO::getDisplayText)).collect(Collectors.toList());
    }

    public Boolean isValidStartDate(Date startDate) {
        logger.debug("isValidStartDate startDate:" + startDate);
        int year = Calendar.getInstance().get(Calendar.YEAR);
        Periodo period = periodRepo.getPeriodByYear(year);

        UserEnvDTO userPaso = usuarioService.getUserEnv(UserHelper.getUsuarioRegistrado().getIdUsuario());

        PeriodoConformacionRedes redes = period.getConformacionRedes().stream()
                .filter(d -> d.getEsCasoEspecial() == null || !d.getEsCasoEspecial()).iterator().next();

        Optional<PeriodoConformacionRedes> redesCE = period.getConformacionRedes().stream()
                .filter(d -> (d.getEsCasoEspecial() != null && d.getEsCasoEspecial()))
                .filter(d -> (d.getIdRegion().equals(Long.parseLong(userPaso.getIdRegion()))))
                .filter(d -> (userPaso.getIdDeprov().isEmpty()
                        || d.getIdDeprov().equals(Long.parseLong(userPaso.getIdDeprov()))))
                .findAny();
        if (redesCE.isPresent()) {
            return redesCE.get().getFechaInicio().getTime() <= startDate.getTime() && redesCE.get().getFechaFin().getTime() >= startDate.getTime();
        } else {
            return redes.getFechaInicio().getTime() <= startDate.getTime() && redes.getFechaFin().getTime() >= startDate.getTime();
        }
    }


    public void setAdvisory(AdvisoryDTO record) {
        Long idUsuarioRegistrado = UserHelper.getUsuarioRegistrado().getIdUsuario();
        int year = Calendar.getInstance().get(Calendar.YEAR);
        Periodo period = periodRepo.getPeriodByYear(year);

        record.getEstablecimiento().forEach(r ->{
            String rbd = r.split("-")[0];
            String nombre = r.split("-")[1];

            AsesoriaDirecta asesoriaDirecta = new AsesoriaDirecta();
            asesoriaDirecta.setIdAsesoriaDirecta(
                    (record.getIdAsesoriaDirecta() == null || record.getIdAsesoriaDirecta().isEmpty()) ? null
                            : Long.parseLong(record.getIdAsesoriaDirecta()));
            asesoriaDirecta.setIdSupervisor(null);
            asesoriaDirecta.setIdPeriodo(period.getIdPeriodo());
            asesoriaDirecta.setRbd(Integer.parseInt(rbd));
            asesoriaDirecta.setNombre(nombre);
            asesoriaDirecta.setIdRegion(record.getIdRegion());
            asesoriaDirecta.setIdDeprov(record.getIdDeprov());
            asesoriaDirecta.setIdComuna(record.getIdComuna());
            asesoriaDirecta.setIdUsuarioCreacion(idUsuarioRegistrado);
            asesoriaDirecta.setFechaCreacion(new Date());
            asesoriaDirecta.setIdUsuarioRegistro(idUsuarioRegistrado);
            asesoriaDirecta.setFechaRegistro(new Date());
            asesoriaDirecta.setHabilitado(true);
            // verifico si esxiste la asesoria para este periodo
            Optional<AsesoriaDirecta> asesoriaPaso = this.asesoriaDirectaRepo.getByIdPeriodo(period.getIdPeriodo()).stream()
                    .filter(x -> x.getIdRegion().equals(asesoriaDirecta.getIdRegion()))
                    .filter(x -> x.getIdDeprov().equals(asesoriaDirecta.getIdDeprov()))
                    .filter(x -> x.getRbd().equals(asesoriaDirecta.getRbd())).findAny();
            if (asesoriaPaso.isPresent()) {
                asesoriaDirecta.setIdAsesoriaDirecta(asesoriaPaso.get().getIdAsesoriaDirecta());
            }

            if (asesoriaDirecta.getIdAsesoriaDirecta() == null) {
                asesoriaDirectaRepo.insert(asesoriaDirecta);
            } else {
                asesoriaDirectaRepo.update(asesoriaDirecta);
            }
        });
    }

    public List<AdvisoryListDTO> getAdvisory(AdvisoryRequestDTO request){
        List<AdvisoryListDTO> result = new ArrayList<AdvisoryListDTO>();

        Date startDate = StringHelper.convertToDateMDY(request.getStartDate());
        Date endDate = StringHelper.convertToDateMDY(request.getEndDate());

        int year = Calendar.getInstance().get(Calendar.YEAR);
        Periodo period = periodRepo.getPeriodByYear(year);

        List<RegionResponseDTO> regiones = regionIntegration.getRegiones();

        List<AsesoriaDirecta> asesorias = this.asesoriaDirectaRepo.getByIdPeriodo(period.getIdPeriodo()).stream()
                                                                                                        .filter(x -> request.getIdRegion() == null || x.getIdRegion().equals(request.getIdRegion()))
                                                                                                        .filter(x -> request.getIdDeprov() == null || x.getIdDeprov().equals(request.getIdDeprov()))
                                                                                                        .filter(x -> request.getRbd() == null || x.getRbd().equals(request.getRbd()))
                                                                                                        .filter(x -> x.getFechaCreacion().after(startDate))
                                                                                                        .filter(x -> x.getFechaCreacion().before(endDate))
                                                                                                        .collect(Collectors.toList());
        asesorias.forEach(a ->{
            AdvisoryListDTO pso = new AdvisoryListDTO();
            pso.setIdAsesoriaDirecta(a.getIdAsesoriaDirecta().toString());
            pso.setRbd(a.getRbd().toString() + "-"+ a.getNombre());
            RegionResponseDTO reg = regiones.stream().filter(r -> a.getIdRegion().equals(r.getIdRegion())).iterator().next();
            pso.setRegion(reg.getNombreRegion());            
            pso.setIdRegion(a.getIdRegion());
            DeprovsResponse devPaso = reg.getDeprovs().stream().filter(r -> a.getIdDeprov().equals(r.getIdDeprov())).iterator().next();
            pso.setIdDeprov(a.getIdDeprov());
            pso.setDeprov(devPaso.getNombreDeprov());
            pso.setFechaCreacion(a.getFechaCreacion());
            pso.setHabilitado(a.getHabilitado());

            result.add(pso);
        });

        return result;
    }

    public AdvisoryListDTO getAdvisory(Long idAsesoriaDirecta) {             
        List<RegionResponseDTO> regiones = regionIntegration.getRegiones();
        AsesoriaDirecta asesoria = this.asesoriaDirectaRepo.getById(idAsesoriaDirecta);        
        AdvisoryListDTO pso = new AdvisoryListDTO();
        pso.setIdAsesoriaDirecta(asesoria.getIdAsesoriaDirecta().toString());
        pso.setRbd(asesoria.getRbd().toString() + "-" + asesoria.getNombre());
        RegionResponseDTO reg = regiones.stream().filter(r -> asesoria.getIdRegion().equals(r.getIdRegion())).iterator()
                .next();
        pso.setRegion(reg.getNombreRegion());
        pso.setIdRegion(asesoria.getIdRegion());
        DeprovsResponse devPaso = reg.getDeprovs().stream().filter(r -> asesoria.getIdDeprov().equals(r.getIdDeprov()))
                .iterator().next();
        pso.setIdDeprov(asesoria.getIdDeprov());
        pso.setDeprov(devPaso.getNombreDeprov());
        Optional<ComunaResponse> comPaso = devPaso.getComunas().stream().filter(c -> c.getIdComuna().equals(asesoria.getIdComuna())).findFirst();
        if(comPaso.isPresent()){
            pso.setIdComuna(comPaso.get().getIdComuna());
            pso.setComuna(comPaso.get().getNombreComuna());
        }        
        pso.setFechaCreacion(asesoria.getFechaCreacion());
        pso.setHabilitado(asesoria.getHabilitado());
        
        return pso;
    }


    public void setAdvisoryStatus(long idAsesoriaDirecta, boolean status) {
        AsesoriaDirecta asesoria = asesoriaDirectaRepo.getById(idAsesoriaDirecta);
        asesoria.setHabilitado(status);
        // Actualizo el item actual
        asesoriaDirectaRepo.update(asesoria);
    }

}
