package cl.mineduc.came.apoyo_mejora_continua.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.mineduc.came.apoyo_mejora_continua.dto.assignment.AssignmentListDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.integration.region.ComunaResponse;
import cl.mineduc.came.apoyo_mejora_continua.dto.integration.region.DeprovsResponse;
import cl.mineduc.came.apoyo_mejora_continua.dto.integration.region.RegionResponseDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.networks.EstablecimientosDetailsDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.planning.AccionesMejorasDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.planning.FocoDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.planning.SesionRedParticipanteDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.report.advisory.AdvisoryResultDirectaDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.report.advisory.AdvisoryResultRedDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.report.advisory.MovimientosClavesDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.report.supervisors.SupervisorsRequestDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.report.supervisors.SupervisorsResultDTO;
import cl.mineduc.came.apoyo_mejora_continua.enums.AsignacionTipoEnum;
import cl.mineduc.came.apoyo_mejora_continua.helpers.StringHelper;
import cl.mineduc.came.apoyo_mejora_continua.integration.EstablecimientosIntegration;
import cl.mineduc.came.apoyo_mejora_continua.integration.RegionIntegration;
import cl.mineduc.came.apoyo_mejora_continua.integration.SostenedoresIntegration;
import cl.mineduc.came.apoyo_mejora_continua.modelo.Asesoria;
import cl.mineduc.came.apoyo_mejora_continua.modelo.AsesoriaDirecta;
import cl.mineduc.came.apoyo_mejora_continua.modelo.ElementoLista;
import cl.mineduc.came.apoyo_mejora_continua.modelo.Foco;
import cl.mineduc.came.apoyo_mejora_continua.modelo.Periodo;
import cl.mineduc.came.apoyo_mejora_continua.modelo.Red;
import cl.mineduc.came.apoyo_mejora_continua.modelo.RedSupervisor;
import cl.mineduc.came.apoyo_mejora_continua.modelo.Sesion;
import cl.mineduc.came.apoyo_mejora_continua.modelo.SesionFoco;
import cl.mineduc.came.apoyo_mejora_continua.modelo.SesionMovimientosClaves;
import cl.mineduc.came.apoyo_mejora_continua.modelo.SesionRedParticipantes;
import cl.mineduc.came.apoyo_mejora_continua.modelo.Supervisor;
import cl.mineduc.came.apoyo_mejora_continua.modelo.UsuarioPerfil;
import cl.mineduc.came.apoyo_mejora_continua.modelo.UsuarioRegistrado;
import cl.mineduc.came.apoyo_mejora_continua.modelo.api.Establecimientos;
import cl.mineduc.came.apoyo_mejora_continua.repo.AsesoriaDirectaRepo;
import cl.mineduc.came.apoyo_mejora_continua.repo.AsesoriaRepo;
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
public class SupervisorsReportService {
	private static final Long TYPE_NETWORK = 2420130603941758041l;

	private static final Long TYPE_CATEGORY = 2420130603857871959l;

	private static final String RED_COMPLEMENTARIA = "2446779961856492708";

	@Autowired
	private RegionIntegration regionIntegration;

	@Autowired
	private ElementoListaRepo elementRepo;

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
    AsesoriaRepo asesoriaRepo;

	Date startDate;
	Date endDate;

	@Autowired
	BitacoraService bitacoraService;
	public List<SupervisorsResultDTO> getSupervisores(SupervisorsRequestDTO request) {
		List<SupervisorsResultDTO> result = new ArrayList<SupervisorsResultDTO>();

		int year = Calendar.getInstance().get(Calendar.YEAR);
		Periodo period = periodRepo.getPeriodByYear(year);

		List<Red> redes = redRepo.getByIdPeriodo(period.getIdPeriodo());
		List<Supervisor> supervisores = supervisorRepo.getAll();
		
		List<AsesoriaDirecta> asesoriasDirectas = this.asesoriaDirectaRepo.getByIdPeriodo(period.getIdPeriodo());
		
			if ((request.getFechaInicio() != null && !request.getFechaInicio().isEmpty()) || (request.getFechaFin() != null && !request.getFechaFin().isEmpty())) {
           	 startDate = StringHelper.convertToDate(request.getFechaInicio());
                endDate = StringHelper.convertToDate(request.getFechaFin());
			}else {
				startDate = StringHelper.convertToDateDMY("01-01-1970");
       			endDate = StringHelper.convertToDateDMY("31-12-" + year);
			}
			SupervisorsResultDTO pso = new SupervisorsResultDTO();
			
            
            if (supervisores != null) {
            	supervisores.forEach(spr -> {
            		pso.setNombre(spr.getUsuario().getUsuarioDominio());
                    UsuarioRegistrado user = usuarioRepo.findById(spr.getIdUsuario());
                    pso.setEmail(user.getEmail());
                    pso.setRut(user.getRut()+"-"+user.getDv());
                    List<Asesoria> asesorias = asesoriaRepo.getByIdPeriodo(period.getIdPeriodo());
                    asesoriasDirectas.forEach(a -> {
                    	// Supervisor sup = supervisores.stream().filter(spv -> spv.getIdSupervisor().equals(a.getIdSupervisor())).iterator().next();
                        List<Asesoria> asesoriaPaso = asesorias.stream()
                                .filter(x -> x.getIdAsesoriaDirecta() != null && x.getIdAsesoriaDirecta().equals(a.getIdAsesoriaDirecta()))
                                .filter(x -> x.getStartDate().getTime() >= (startDate.getTime()))
                                .filter(x -> x.getStartDate().getTime() <= (endDate.getTime()))
                                .collect(Collectors.toList());
                        List<String> nombreColegios = new ArrayList<String>();
                        nombreColegios.add(a.getNombre());
                        if (asesoriaPaso != null) {
                        	asesoriaPaso.forEach(itemAsesoria -> {
                        		RegionResponseDTO region = regionIntegration.getRegionByNumber(Long.valueOf(a.getIdRegion()));
                    			pso.setRegion(region.getNombreRegion());
                    			DeprovsResponse devPaso = region.getDeprovs().stream()
                    					.filter(r -> a.getIdDeprov().equals(r.getIdDeprov())).iterator().next();
                    			pso.setDeprov(devPaso.getNombreDeprov());
                    			
                    			pso.setAsesoriasDirectasRegistradas(nombreColegios.size() +  "");
                    			pso.setEstablecimientosApoyadosFormaDirecta(nombreColegios);
            	                
            	        	});
            			}
                    });  
                    
                    redes.forEach(red -> {
                    	List<String> nombreRedes = new ArrayList<String>();
                    	List<Asesoria> asesoriaPaso = asesorias.stream()
                                .filter(x -> x.getIdRed() != null && x.getIdRed().equals(red.getIdRed()))
                                .filter(x -> x.getStartDate().getTime() >= (startDate.getTime()))
                                .filter(x -> x.getStartDate().getTime() <= (endDate.getTime()))
                                .collect(Collectors.toList());
                    	if (asesoriaPaso != null) {
                    		asesoriaPaso.forEach(itemAsesoria -> {
                              	 red.getSupervisores().forEach(supRed -> {
                              		 if(supRed.getIdSupervisor().equals(spr.getIdSupervisor())) {
                              			 nombreRedes.add(red.getNombre());
                              		 }
                              	 });
                            });
                    		pso.setRedesApoyadas(nombreRedes);
                    		pso.setAsesoriasRedRegistradas(nombreRedes.size() + "");
                    	}                    	
                    });                    
            	});
            }
            
	    return result;
	}
}
