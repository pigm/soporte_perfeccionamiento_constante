package cl.mineduc.came.apoyo_mejora_continua.services;

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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import cl.mineduc.came.apoyo_mejora_continua.dto.integration.region.ComunaResponse;
import cl.mineduc.came.apoyo_mejora_continua.dto.integration.region.DeprovsResponse;
import cl.mineduc.came.apoyo_mejora_continua.dto.integration.region.RegionResponseDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.networks.EstablecimientosDetailsDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.networks.EstablecimientosRequestDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.networks.NetworkDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.networks.NetworkDetailDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.networks.NetworksListDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.networks.NetworksRequestDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.shared.SelectorDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.users.UserEnvDTO;
import cl.mineduc.came.apoyo_mejora_continua.helpers.StringHelper;
import cl.mineduc.came.apoyo_mejora_continua.helpers.UserHelper;
import cl.mineduc.came.apoyo_mejora_continua.integration.EstablecimientosIntegration;
import cl.mineduc.came.apoyo_mejora_continua.integration.RegionIntegration;
import cl.mineduc.came.apoyo_mejora_continua.integration.SostenedoresIntegration;
import cl.mineduc.came.apoyo_mejora_continua.modelo.ElementoLista;
import cl.mineduc.came.apoyo_mejora_continua.modelo.Periodo;
import cl.mineduc.came.apoyo_mejora_continua.modelo.PeriodoConformacionRedes;
import cl.mineduc.came.apoyo_mejora_continua.modelo.Red;
import cl.mineduc.came.apoyo_mejora_continua.modelo.RedEstablecimiento;
import cl.mineduc.came.apoyo_mejora_continua.modelo.RedSostenedor;
import cl.mineduc.came.apoyo_mejora_continua.modelo.api.Establecimientos;
import cl.mineduc.came.apoyo_mejora_continua.modelo.api.sostenedores.Sostenedores;
import cl.mineduc.came.apoyo_mejora_continua.repo.BitacoraService;
import cl.mineduc.came.apoyo_mejora_continua.repo.ElementoListaRepo;
import cl.mineduc.came.apoyo_mejora_continua.repo.PeriodoRepo;
import cl.mineduc.came.apoyo_mejora_continua.repo.RedEstablecimientoRepo;
import cl.mineduc.came.apoyo_mejora_continua.repo.RedRepo;
import cl.mineduc.came.apoyo_mejora_continua.repo.RedSostenedorRepo;

@Service
public class NetworksService {
    private static Logger logger = LogManager.getLogger(NetworksService.class);

    private static final Long TYPE_CATEGORY = 2420130603941758041l;
    private static final String RED_COMPLEMENTARIA = "2446779961856492708";

    @Autowired
    private RegionIntegration regionIntegration;

    @Autowired
    private SostenedoresIntegration sostenedoresIntegration;

    @Autowired
    private ElementoListaRepo elementRepo;

    @Autowired
    private RedRepo redRepo;

    @Autowired
    private RedEstablecimientoRepo redEstablecimientoRepo;

    @Autowired
    private RedSostenedorRepo redSostenedorRepo;

    @Autowired
    private PeriodoRepo periodRepo;

    @Autowired
    private BitacoraService bitacoraService;

    @Autowired
    private EstablecimientosIntegration establecimientosIntegration;

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
        regionesPaso.getDeprovs().forEach(p -> result.add(SelectorDTO.of(p.getIdDeprov().toString(), p.getNombreDeprov())));
        return result;
    }

    public List<SelectorDTO> getComunasByDeprov(Integer value){
        List<SelectorDTO> result = new ArrayList<SelectorDTO>();
        List<RegionResponseDTO> regionesPaso = regionIntegration.getRegiones();
        List<DeprovsResponse> paso1 = regionesPaso.stream().filter(x -> x.getDeprovs().stream().anyMatch(j -> j.getIdDeprov().equals(value))).map(RegionResponseDTO::getDeprovs).iterator().next();
        paso1.iterator().next().getComunas().forEach(c -> result.add(SelectorDTO.of(c.getIdComuna().toString(), c.getNombreComuna())));
        return result;
    }

    public List<SelectorDTO> getTipoRed(){
        List<SelectorDTO> result = new ArrayList<SelectorDTO>();
        List<ElementoLista> elements = elementRepo.getElementsByIdLista(TYPE_CATEGORY);
        elements.forEach(e ->{
            if (e.isStatus()) {
                result.add(SelectorDTO.of(e.getIdElementoLista().toString(), e.getNombre()));
            }
        });
        return result;
    }

    public NetworkDetailDTO getNetworkDetail(Long idRed){
        NetworkDetailDTO result = new NetworkDetailDTO();
        Red red = redRepo.getNetworkById(idRed);
        if(red != null){
            result.setIdRed(red.getIdRed().toString());
            result.setNombre(red.getNombre());
            result.setFechaCreacion(red.getFechaCreacion());
            Integer estado = null;
            Integer dependencia = null;
            Integer comuna = null;
            
            List<Establecimientos> list = this.establecimientosIntegration.getEstablecimientos(
                    estado, dependencia, red.getIdRegion().intValue(), red.getIdDeprov().intValue(), comuna, "", "", 999999);

            RegionResponseDTO region = regionIntegration.getRegionByNumber(red.getIdRegion());
            result.setRegion(region.getNombreRegion());
            result.setIdRegion(region.getIdRegion());
            DeprovsResponse devPaso = region.getDeprovs().stream().filter(r -> red.getIdDeprov().equals((long) r.getIdDeprov())).iterator().next();
            result.setDeprov(devPaso.getNombreDeprov());
            result.setIdDeprov(devPaso.getIdDeprov());
            if(red.getIdComuna() != null){
                Optional<ComunaResponse> comunaPaso = devPaso.getComunas().stream()
                        .filter(c -> c.getIdComuna().equals(red.getIdComuna().intValue())).findFirst();
                if(comunaPaso.isPresent()){
                    result.setComuna(comunaPaso.get().getNombreComuna());
                    result.setIdComuna(comunaPaso.get().getIdComuna());
                }
                
            }

            ElementoLista elements = elementRepo.getElementsByIdLista(TYPE_CATEGORY).stream()
                    .filter(r -> r.getIdElementoLista().equals(red.getIdTipoRed())).iterator().next();
            result.setTipoRed(elements.getNombre());
            result.setIdTipoRed(elements.getIdElementoLista().toString());
            List<EstablecimientosDetailsDTO> ests = new ArrayList<EstablecimientosDetailsDTO>();
            if (red.getIdTipoRed().equals(Long.parseLong(RED_COMPLEMENTARIA))) {
                redSostenedorRepo.getByIdRed(red.getIdRed()).forEach(x ->{
                    Establecimientos estPaso1 = list.stream().filter(s -> s.getSostenedor() != null && s.getSostenedor().getRutSostenedor().equals(x.getRut())).iterator().next();                    
                    EstablecimientosDetailsDTO e = new EstablecimientosDetailsDTO();
                    e.setValue(estPaso1.getSostenedor().getRutSostenedor());
                    e.setDisplayText(estPaso1.getSostenedor().getNombreSostenedor());
                    e.setStatus(x.getStatus());
                    ests.add(e);
                });
            } else {
               redEstablecimientoRepo.getByIdRed(red.getIdRed()).forEach( x ->{
                    Establecimientos estPaso1 = list.stream().filter(s -> s.getRbd().equals(x.getRbd())).iterator().next();
                    EstablecimientosDetailsDTO e = new EstablecimientosDetailsDTO();
                   e.setValue(estPaso1.getRbd().toString());
                   e.setDisplayText(estPaso1.getNombre());
                   e.setStatus(x.getStatus());
                   ests.add(e);

                   
               }); 
            }
           
            result.setEstablecimientos(ests);
        }

        return result;
    }

    public List<NetworksListDTO> getNetworks(NetworksRequestDTO request){
        List<RegionResponseDTO> regiones = regionIntegration.getRegiones();

        Date startDate = StringHelper.convertToDateDMY(request.getStartDate());
        Date endDate = StringHelper.convertToDateDMY(request.getEndDate());

        List<NetworksListDTO> result = new ArrayList<NetworksListDTO>();
        int year = Calendar.getInstance().get(Calendar.YEAR);
        Periodo period = periodRepo.getPeriodByYear(year);        
        List<Red> redes = redRepo.getByIdPeriodo(period.getIdPeriodo());
        List<Red> redFiltered = redes.stream().filter(x -> request.getNombre().isEmpty() || x.getNombre().contains(request.getNombre()))                                              
                                              .filter(x -> request.getRegionId() == null || x.getIdRegion().equals((long)request.getRegionId()))
                                              .filter(x -> request.getDeprovId() == null || x.getIdDeprov().equals((long)request.getDeprovId())) 
                                              .filter(x -> request.getTipoRedId().isEmpty() || x.getIdTipoRed().equals(Long.parseLong(request.getTipoRedId())))
                                              .filter(x -> x.getFechaCreacion().getTime() >= startDate.getTime())
                                              .filter(x -> x.getFechaCreacion().getTime() <= endDate.getTime())                                              
                                              .collect(Collectors.toList());

        redFiltered.forEach(x ->{
            NetworksListDTO pso = new NetworksListDTO();
            pso.setIdRed(x.getIdRed().toString());
            pso.setNombre(x.getNombre());
            RegionResponseDTO reg = regiones.stream().filter(r -> x.getIdRegion().equals((long) r.getIdRegion())).iterator().next();
            pso.setRegion(reg.getNombreRegion());
            DeprovsResponse devPaso = reg.getDeprovs().stream().filter(r -> x.getIdDeprov().equals((long)r.getIdDeprov())).iterator().next();
            pso.setDeprov(devPaso.getNombreDeprov());
            ElementoLista elements = elementRepo.getElementsByIdLista(TYPE_CATEGORY).stream().filter(r -> r.getIdElementoLista().equals(x.getIdTipoRed())).iterator().next();
            pso.setTipoRed(elements.getNombre());
            pso.setFechaCreacion(x.getFechaCreacion());

            if(x.getIdTipoRed().equals(Long.parseLong(RED_COMPLEMENTARIA))){
               List<RedSostenedor> sostenedores = redSostenedorRepo.getByIdRed(x.getIdRed());
               pso.setEstablecimientos(sostenedores.size() + " Sostenedores");
               List<String> listSostenedores = new ArrayList<String>();
               sostenedores.forEach(rs ->{
                   Sostenedores sp = this.sostenedoresIntegration.getSostenedorByRut(rs.getRut());
                   listSostenedores.add(sp.getNombre() + " " + sp.getApellidoPaterno() + " " + sp.getApellidoMaterno());                   
               });
                pso.setEstablecimientosList(listSostenedores);
            } else{
                List<String> listEstablecimientos = new ArrayList<String>();
                List<RedEstablecimiento> establecimientos = redEstablecimientoRepo.getByIdRed(x.getIdRed());
                pso.setEstablecimientos(establecimientos.size() + " Establecimientos");
                establecimientos.forEach(re ->{
                    Establecimientos estPaso = this.establecimientosIntegration.getEstablecimientoByRbd(re.getRbd());
                    listEstablecimientos.add(estPaso.getRbd() + "-" + estPaso.getNombre());
                });
                pso.setEstablecimientosList(listEstablecimientos);
            }

            result.add(pso);
        });

        return result;
    }

    public Boolean isValidName(String nombreRed){
        int year = Calendar.getInstance().get(Calendar.YEAR);
        Periodo period = periodRepo.getPeriodByYear(year);
        List<Red> redesPaso = redRepo.getByIdPeriodo(period.getIdPeriodo());
        return redesPaso.isEmpty() || redesPaso.stream().noneMatch(x -> x.getNombre().equalsIgnoreCase(nombreRed));
    }

    public Boolean isValidStartDate(Date startDate){
        logger.debug("isValidStartDate startDate:" + startDate);
        int year = Calendar.getInstance().get(Calendar.YEAR);
        Periodo period = periodRepo.getPeriodByYear(year);

        UserEnvDTO userPaso = usuarioService.getUserEnv(UserHelper.getUsuarioRegistrado().getIdUsuario());

        PeriodoConformacionRedes redes = period.getConformacionRedes().stream().filter(d -> d.getEsCasoEspecial() == null || !d.getEsCasoEspecial()).iterator().next();

        Optional<PeriodoConformacionRedes> redesCE = period.getConformacionRedes().stream().filter(d -> (d.getEsCasoEspecial() != null && d.getEsCasoEspecial()))
                                                                                 .filter(d ->  (d.getIdRegion().equals(Long.parseLong(userPaso.getIdRegion()))))
                                                                                 .filter(d -> (userPaso.getIdDeprov().isEmpty() || d.getIdDeprov().equals(Long.parseLong(userPaso.getIdDeprov()))))
                                                                                 .findAny();
        if (redesCE.isPresent()) {
            return redesCE.get().getFechaInicio().getTime() <= startDate.getTime()
                    && redesCE.get().getFechaFin().getTime() >= startDate.getTime();
        } else {
            return redes.getFechaInicio().getTime() <= startDate.getTime()
                    && redes.getFechaFin().getTime() >= startDate.getTime();
        }
    }

    public List<SelectorDTO> getEstablecimientos(EstablecimientosRequestDTO filter){
        List<SelectorDTO> result = new ArrayList<SelectorDTO>();
        Integer estado = 1;
        Integer dependencia = null;
        String filterText = null;
        String convenio = null;
        List<Establecimientos> list = this.establecimientosIntegration.getEstablecimientos(
                                            estado, dependencia, filter.getIdRegion(), filter.getIdDeprov(), filter.getIdComuna(),
                                            filterText, convenio, 999999);

        List<Establecimientos> listTwo = list.stream().filter(x -> filter.getIdComuna() == null || x.getCodigoGeografico().getCodigoComuna().equals(filter.getIdComuna()))
                .collect(Collectors.toList());

        if(filter.getIdTipoRed() != null && filter.getIdTipoRed().equals(RED_COMPLEMENTARIA)){
            List<SelectorDTO> sostenedoresPaso = new ArrayList<SelectorDTO>();
            listTwo.forEach(x -> {
                if(x.getSostenedor() != null){
                    SelectorDTO paso1 = new SelectorDTO();
                    paso1.setValue(x.getSostenedor().getRutSostenedor());
                    paso1.setDisplayText(x.getSostenedor().getNombreSostenedor());
                    sostenedoresPaso.add(paso1);
                }                
            });

            sostenedoresPaso.stream().collect(Collectors.groupingBy(SelectorDTO::getValue))
                                     .forEach((x,y) ->{
                SelectorDTO paso1 = new SelectorDTO();
                paso1.setValue(x);
                paso1.setDisplayText(y.iterator().next().getDisplayText());
                result.add(paso1);
            });

        } else {
            listTwo.forEach(x -> {
                SelectorDTO paso1 = new SelectorDTO();
                paso1.setValue(x.getRbd().toString());
                paso1.setDisplayText(x.getRbd().toString() + "-" + x.getNombre());
                result.add(paso1);
            });
        }
        
        return result.stream().sorted(Comparator.comparing(SelectorDTO::getDisplayText)).collect(Collectors.toList());
    }

    @Transactional(rollbackFor = Throwable.class)
    public void setNetwork(NetworkDTO record){

        UriComponentsBuilder builder = ServletUriComponentsBuilder.fromCurrentRequest();
        Long idUsuarioRegistrado = UserHelper.getUsuarioRegistrado().getIdUsuario();
        int year = Calendar.getInstance().get(Calendar.YEAR);
        Periodo period = periodRepo.getPeriodByYear(year);
        
        Red red = new Red();
        red.setIdRed(!record.getIdRed().isEmpty() ? Long.parseLong(record.getIdRed()) : null);
        red.setFechaCreacion(StringHelper.convertToDate(record.getFechaCreacion()));
        red.setNombre(record.getNombre());
        red.setIdRegion((long)record.getIdRegion());
        red.setIdDeprov((long)record.getIdDeprov());
        if(record.getIdComuna() != null){
            red.setIdComuna((long) record.getIdComuna());
        }
        red.setIdTipoRed(Long.parseLong(record.getIdTipoRed()));
        red.setIdPeriodo(period.getIdPeriodo());
        
        red.setIdUsuarioRegistro(idUsuarioRegistrado);
        red.setFechaRegistro(new Date());
        
        if(red.getIdRed() == null){            
            red.setIdUsuario(idUsuarioRegistrado);
            this.redRepo.insertNetwork(red);
            
            bitacoraService.accionBitacora(1,red,builder);
        } else {
            this.redRepo.updateNetwork(red);
            bitacoraService.accionBitacora(2,red,builder);
        }

        if(red.getIdTipoRed().equals(Long.parseLong(RED_COMPLEMENTARIA))){            
            List<RedSostenedor> listRs = redSostenedorRepo.getByIdRed(red.getIdRed());
            listRs.forEach(x ->{
                if(record.getEstablecimientos().stream().noneMatch(r -> x.getRut().equals(r))){
                    redSostenedorRepo.deleteById(x.getIdRedSostenedor());
                }
            });
            record.getEstablecimientos().forEach(x -> {
                if (listRs.stream().noneMatch(r -> r.getRut().equals(x))) {
                    Sostenedores sp = sostenedoresIntegration.getSostenedorByRut(x);
                    if(sp != null){
                        RedSostenedor rs = new RedSostenedor();
                        rs.setIdRed(red.getIdRed());
                        rs.setRut(sp.getRut());
                        rs.setDv(sp.getDv());
                        rs.setIdUsuarioRegistro(idUsuarioRegistrado);
                        rs.setFechaRegistro(new Date());
                        rs.setStatus(true);
                        redSostenedorRepo.insert(rs);
                    }
                }
            });            
        } else {
            List<RedEstablecimiento> listRs = redEstablecimientoRepo.getByIdRed(red.getIdRed());
            listRs.forEach(x -> {
                if (record.getEstablecimientos().stream().noneMatch(r -> x.getRbd().equals(Integer.parseInt(r)))) {
                    redEstablecimientoRepo.deleteById(x.getIdRedEstablecimiento());
                }
            });
            record.getEstablecimientos().forEach(x ->{
                Establecimientos est = establecimientosIntegration.getEstablecimientoByRbd(Integer.parseInt(x));
                if(listRs.stream().noneMatch(d -> d.getRbd().equals(Integer.parseInt(x)))){
                    RedEstablecimiento re = new RedEstablecimiento();
                    re.setIdRed(red.getIdRed());
                    re.setRbd(est.getRbd());
                    re.setIdUsuarioRegistro(idUsuarioRegistrado);
                    re.setFechaRegistro(new Date());
                    re.setStatus(true);
                    redEstablecimientoRepo.insert(re);
                }
            });
        }
    }

    public void setStatusEstablecimiento(String idRed, String establecimiento, Boolean status){
        Red red = redRepo.getNetworkById(Long.parseLong(idRed));
        if(red != null){
            if(red.getIdTipoRed().equals(Long.parseLong(RED_COMPLEMENTARIA))){
                RedSostenedor redSosPaso = redSostenedorRepo.getByIdRed(red.getIdRed()).stream().filter(x-> x.getRut().equals(establecimiento)).iterator().next();                
                redSosPaso.setStatus(status);
                redSostenedorRepo.updateById(redSosPaso);
            } else{
                RedEstablecimiento redEstPaso = redEstablecimientoRepo.getByIdRed(red.getIdRed()).stream().filter(x -> x.getRbd().equals(Integer.parseInt(establecimiento))).iterator().next();
                redEstPaso.setStatus(status);
                redEstablecimientoRepo.updateById(redEstPaso);
            }
        }
    }


}
