package cl.mineduc.came.apoyo_mejora_continua.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.mineduc.came.apoyo_mejora_continua.dto.integration.region.ComunaResponse;
import cl.mineduc.came.apoyo_mejora_continua.dto.integration.region.DeprovsResponse;
import cl.mineduc.came.apoyo_mejora_continua.dto.integration.region.RegionResponseDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.planning.AccionesMejorasDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.planning.FocoDTO;

import cl.mineduc.came.apoyo_mejora_continua.dto.report.advisory.AdvisoryRequestDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.report.advisory.AdvisoryResultDirectaDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.report.advisory.AdvisoryResultRedDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.report.advisory.MovimientosClavesDTO;
import cl.mineduc.came.apoyo_mejora_continua.helpers.StringHelper;
import cl.mineduc.came.apoyo_mejora_continua.integration.EstablecimientosIntegration;
import cl.mineduc.came.apoyo_mejora_continua.integration.RegionIntegration;
import cl.mineduc.came.apoyo_mejora_continua.modelo.Asesoria;
import cl.mineduc.came.apoyo_mejora_continua.modelo.AsesoriaDirecta;
import cl.mineduc.came.apoyo_mejora_continua.modelo.EstablecimientoOrdenacion;
import cl.mineduc.came.apoyo_mejora_continua.modelo.Foco;
import cl.mineduc.came.apoyo_mejora_continua.modelo.Periodo;
import cl.mineduc.came.apoyo_mejora_continua.modelo.Red;
import cl.mineduc.came.apoyo_mejora_continua.modelo.RedSupervisor;
import cl.mineduc.came.apoyo_mejora_continua.modelo.Sesion;
import cl.mineduc.came.apoyo_mejora_continua.modelo.SesionFoco;
import cl.mineduc.came.apoyo_mejora_continua.modelo.SesionMovimientosClaves;
import cl.mineduc.came.apoyo_mejora_continua.modelo.Supervisor;
import cl.mineduc.came.apoyo_mejora_continua.modelo.UsuarioRegistrado;
import cl.mineduc.came.apoyo_mejora_continua.modelo.api.Establecimientos;
import cl.mineduc.came.apoyo_mejora_continua.repo.AsesoriaDirectaRepo;
import cl.mineduc.came.apoyo_mejora_continua.repo.AsesoriaRepo;
import cl.mineduc.came.apoyo_mejora_continua.repo.EstablecimientoOrdenacionRepo;
import cl.mineduc.came.apoyo_mejora_continua.repo.FocoRepo;
import cl.mineduc.came.apoyo_mejora_continua.repo.PeriodoRepo;
import cl.mineduc.came.apoyo_mejora_continua.repo.SesionFocoRepo;
import cl.mineduc.came.apoyo_mejora_continua.repo.SesionRepo;
import cl.mineduc.came.apoyo_mejora_continua.repo.SupervisorRepo;

@Service
public class AdvisoryReportService {

	@Autowired
    EstablecimientosIntegration establecimientosIntegration;
	
	@Autowired
    EstablecimientoOrdenacionRepo establecimientoOrdenacionRepo;
	
	@Autowired
	PeriodoRepo periodRepo;
	 
	@Autowired
	SupervisorRepo supervisorRepo;
	 
	@Autowired
    RegionIntegration regionIntegration;
	 
	@Autowired
    AsesoriaDirectaRepo asesoriaDirectaRepo;
	
	@Autowired
    AsesoriaRepo asesoriaRepo;
	
	@Autowired
    FocoRepo focoRepo;
	
	 @Autowired
     SesionFocoRepo sesionFocoRepo;

    @Autowired
    SesionRepo sesionRepo;
    Date startDate;
	Date endDate;
	
	private static final Long TYPE_SESION_REGISTRADA = 2465656905259812566l;
    private static final Long TYPE_SESION_PLANIFICADA = 2465656905284978391l;
    private static final String SESION_PLANIFICADA = "Sesión Planificada";
    private static final String SESION_REGISTRADA = "Sesión Registrada";
    int count = 1;
    int total;
    Integer prom = 0;
    
	public List<AdvisoryResultDirectaDTO> getDirecta(AdvisoryRequestDTO request) {
		List<AdvisoryResultDirectaDTO> result = new ArrayList<AdvisoryResultDirectaDTO>();
		
	        // Establecimientos
	        List<Establecimientos> establecimientos = this.establecimientosIntegration.getEstablecimientos(null,
	                null, null, null, null, null, null, 999999);

	        // ordenaciones
	        List<EstablecimientoOrdenacion> ordenacion = establecimientoOrdenacionRepo.get();

	        List<RegionResponseDTO> regiones = regionIntegration.getRegiones();
	        // Supervisores
	        List<Supervisor> supervisores = supervisorRepo.getAll();
	        // periodo
	        int year = Calendar.getInstance().get(Calendar.YEAR);
	       
	        Periodo period = periodRepo.getPeriodByYear(year);
	            
	            List<AsesoriaDirecta> asesoriasDirectas = this.asesoriaDirectaRepo.getByIdPeriodo(period.getIdPeriodo());

	            asesoriasDirectas.forEach(a -> {	            	
	                RegionResponseDTO reg = regiones.stream().filter(r -> a.getIdRegion().equals(r.getIdRegion()))
	                        .iterator().next();
	                              
	                DeprovsResponse devPaso = reg.getDeprovs().stream().filter(r -> a.getIdDeprov().equals(r.getIdDeprov()))
	                        .iterator().next();
	             
	                
                    if ((request.getFechaInicio() != null && request.getFechaInicio() != "") || (request.getFechaFin() != null && request.getFechaFin() != "")) {
                    	 startDate = StringHelper.convertToDate(request.getFechaInicio());
                         endDate = StringHelper.convertToDate(request.getFechaFin());
					}else {
						startDate = StringHelper.convertToDateDMY("01-01-1970");
		        		endDate = StringHelper.convertToDateDMY("31-12-" + year);
					}
	                List<Asesoria> asesorias = asesoriaRepo.getByIdPeriodo(period.getIdPeriodo());
	                
	                List<Asesoria> asesoriaPaso = asesorias.stream()
	                        .filter(x -> x.getIdAsesoriaDirecta() != null && x.getIdAsesoriaDirecta().equals(a.getIdAsesoriaDirecta()))
	                        .filter(x -> x.getStartDate().getTime() >= (startDate.getTime()))
	                        .filter(x -> x.getStartDate().getTime() <= (endDate.getTime()))
	                        .collect(Collectors.toList());
	                
	                if (asesoriaPaso != null) {
	                	asesoriaPaso.forEach(itemAsesoria -> {
	                		
	                		List<Sesion> sesiones = this.sesionRepo.getByIdAsesoria(itemAsesoria.getIdAsesoria());
	    	                if (!sesiones.isEmpty()) {
	    	                	
	    	                    sesiones.forEach(s -> {	 
	    	                    	if (s.getIdSessionEstado().equals(TYPE_SESION_REGISTRADA)) {
	    	                    		AdvisoryResultDirectaDTO pso = new AdvisoryResultDirectaDTO();
		    	                		pso.setRegion(reg.getNombreRegion());	
		    	                		pso.setDeprov(devPaso.getNombreDeprov());
		    	                		
		    	                		if (a.getIdSupervisor() != null) {
		    	    	                    Supervisor sup = supervisores.stream().filter(spv -> spv.getIdSupervisor().equals(a.getIdSupervisor()))
		    	    	                            .iterator().next();
		    	    	                    if (sup != null) {
		    	    	                        pso.setNombreSupervisor(Arrays.asList(sup.getUsuario().getUsuarioDominio()));
		    	    	                        pso.setResponsable(Arrays.asList(sup.getUsuario().getUsuarioDominio()));
		    	    	                    }
		    	    	                }
		    	                		
		    	                		pso.setNumeroSesion(count + "");
		    	    	                pso.setRbd(a.getRbd().toString());
		    	    	                pso.setEstablecimiento(a.getNombre());
		    	    	                pso.setTipoPlanificacion("Directa");
		    	    	                pso.setFechaPlanificada(s.getFechaPlanificacion() != null ? s.getFechaPlanificacion().toLocaleString() :  "");
		    	                        pso.setFechaRealizada(s.getFechaRealizada() != null ? s.getFechaRealizada().toLocaleString() : "");
		    	                        pso.setEstado(s.getIdSessionEstado().equals(TYPE_SESION_REGISTRADA) ? SESION_REGISTRADA : SESION_PLANIFICADA);
		    	                    	List<FocoDTO> sesionFocosPaso = new ArrayList<FocoDTO>();

		    	                
		    	                        List<Foco> focos = focoRepo.getAllFocos().stream().filter(f -> f.getPeriodo().equals(year))
		    	                                .collect(Collectors.toList());
		    	          
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
		    	                                List<MovimientosClavesDTO> movimientos = new ArrayList<MovimientosClavesDTO>();
		    	                                x.getAcciones().forEach(acc -> {
		    	                                    AccionesMejorasDTO am = new AccionesMejorasDTO();
		    	                                    am.setIdAccionesMejoras(acc.getIdAccionesMejoras().toString());
		    	                                    am.setNombre(acc.getNombre());
		    	                                    
		    	                                    List<String>  movNom = new ArrayList<String>();
		    	                                    acc.getMovimientos().forEach(m -> {
		    	                                        MovimientosClavesDTO mc = new MovimientosClavesDTO();
		    	                                        mc.setIdMovimientosClaves(m.getIdMovimientosClaves().toString());
		    	                                        Optional<SesionMovimientosClaves> t = sfp.get().getMovimientosClaves().stream()
		    	                                                .filter(r -> r.getIdMovimientosClaves().equals(m.getIdMovimientosClaves())).findFirst();
		    	                                        mc.setCompletado(t.isPresent() && t.get().getCompletado());
		    	                                        mc.setNombre(m.getNombre());
		    	                                        mc.setIdAccionMejora(acc.getIdAccionesMejoras());
		    	                                        movimientos.add(mc);
		    	                                        movNom.add(m.getNombre());
		    	                                        
		    	                                        
		    	                                       
		    	                                        
		    	                                    });
		    	                                    List<String>  listfocos = new ArrayList<String>();
		    	                                    listfocos.add(x.getNombre());
		    	                                    pso.setFoco(listfocos);
		    	                                    pso.setMovimientoClaveComprometido(movNom);
		    	                                    f.setLogrado(movimientos.stream().filter(c -> c.getCompletado()).count() + f.getLogrado());
		    	                                    f.setTotal(movimientos.size() + f.getTotal());
		    	                                    
		    	                                    List<String>  mov = new ArrayList<String>();
		    	                                    movimientos.forEach(m -> {
		    	                                    	if (m.getCompletado()) {
		    	                                    		mov.add(m.getNombre());
														}
		    	                                    	
		    	                                    });
		    	                                    pso.setMovimientoClaveDesarrollado(mov);
		    	                                    acciones.add(am);
		    	                                    		    	                                    
		    	                                    
		    	                                });
		    	                                List<String>  accMej = new ArrayList<String>();
		    	                                List<MovimientosClavesDTO> movimientosCompletados = new ArrayList<MovimientosClavesDTO>();
		    	                                List<Integer> totales = new ArrayList<Integer>();
		    	                                List<Integer> promedios = new ArrayList<Integer>();
		    	                                acciones.forEach(itemAcc -> {	
		    	                                	 movimientos.forEach(m -> {
			    	                                    	if (m.getIdAccionMejora().equals(Long.parseLong(itemAcc.getIdAccionesMejoras()))) {
			    	                                    		if (m.getCompletado()) {
			    	                                    			movimientosCompletados.add(m);
																}
															}
			    	                                    	
			    	                                    });
		    	                                	total = (movimientosCompletados.size() / movimientos.size())* 100;
		    	                                	totales.add(total);
		    	                                	Integer suma = 0;
			    	                                for(Integer objeto : totales) {
			    	                                	suma += objeto;
			    	                                }
			    	                                prom = suma / totales.size();
			    	                                
		    	                                	promedios.add(prom);
		    	                                	accMej.add(itemAcc.getNombre() + "  (" + prom  + "%)");	
	    	                                    });
		    	                                
		    	                                
		    	                                Integer sumaMovClaves = 0;
		    	                                Integer promAcc = 0;
		    	                                for(Integer objeto : promedios) {
		    	                                	sumaMovClaves += objeto;
		    	                                }
		    	                                promAcc = sumaMovClaves / promedios.size();
	    	                                    pso.setEstadoFoco(promAcc + "%");
		    	                                pso.setAcionesMejorasDesarrollo(accMej);
		    	                               
		    	                                sesionFocosPaso.add(f);
		    	                               
		    	                            }
		    	                        });
		    	                        
		    	                        count = count + 1;
		    	                        result.add(pso);
									}
	    	                    });
    	                    }
	    	                
	    	                
	    	               count = 1;
	    	        	});
	    			}
	            });
	        

	        return result;
	}
	
	private Integer countSesionsAD(Long idAsesoriaDirecta, Long idPeriodo) {
        List<Asesoria> asesorias = asesoriaRepo.getByIdPeriodo(idPeriodo);
        Optional<Asesoria> asesoriaPaso = asesorias.stream()
                .filter(x -> x.getIdAsesoriaDirecta() != null && x.getIdAsesoriaDirecta().equals(idAsesoriaDirecta))
                .findAny();
        return asesoriaPaso.isPresent() ? this.sesionRepo.getByIdAsesoria(asesoriaPaso.get().getIdAsesoria()).size()
                : 0;
    }

}
