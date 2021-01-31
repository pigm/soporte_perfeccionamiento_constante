package cl.mineduc.came.apoyo_mejora_continua.services;

import java.util.ArrayList;
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
import cl.mineduc.came.apoyo_mejora_continua.dto.networks.EstablecimientosDetailsDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.planning.EncargadoRedDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.planning.PlanningListDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.planning.SesionDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.planning.SesionRedParticipanteDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.report.advisory.AdvisoryRequestDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.report.advisory.AdvisoryResultRedDTO;
import cl.mineduc.came.apoyo_mejora_continua.helpers.StringHelper;
import cl.mineduc.came.apoyo_mejora_continua.integration.EstablecimientosIntegration;
import cl.mineduc.came.apoyo_mejora_continua.integration.RegionIntegration;
import cl.mineduc.came.apoyo_mejora_continua.modelo.Asesoria;
import cl.mineduc.came.apoyo_mejora_continua.modelo.ElementoLista;
import cl.mineduc.came.apoyo_mejora_continua.modelo.EstablecimientoOrdenacion;
import cl.mineduc.came.apoyo_mejora_continua.modelo.Periodo;
import cl.mineduc.came.apoyo_mejora_continua.modelo.Red;
import cl.mineduc.came.apoyo_mejora_continua.modelo.RedSupervisor;
import cl.mineduc.came.apoyo_mejora_continua.modelo.Sesion;
import cl.mineduc.came.apoyo_mejora_continua.modelo.SesionRedParticipantes;
import cl.mineduc.came.apoyo_mejora_continua.modelo.Supervisor;
import cl.mineduc.came.apoyo_mejora_continua.modelo.UsuarioPerfil;
import cl.mineduc.came.apoyo_mejora_continua.modelo.UsuarioRegistrado;
import cl.mineduc.came.apoyo_mejora_continua.modelo.api.Establecimientos;
import cl.mineduc.came.apoyo_mejora_continua.repo.AsesoriaRepo;
import cl.mineduc.came.apoyo_mejora_continua.repo.ElementoListaRepo;
import cl.mineduc.came.apoyo_mejora_continua.repo.EstablecimientoOrdenacionRepo;
import cl.mineduc.came.apoyo_mejora_continua.repo.PerfilRepo;
import cl.mineduc.came.apoyo_mejora_continua.repo.PeriodoRepo;
import cl.mineduc.came.apoyo_mejora_continua.repo.RedEstablecimientoRepo;
import cl.mineduc.came.apoyo_mejora_continua.repo.RedRepo;
import cl.mineduc.came.apoyo_mejora_continua.repo.RedSupervisorRepo;
import cl.mineduc.came.apoyo_mejora_continua.repo.SesionRedParticipantesRepo;
import cl.mineduc.came.apoyo_mejora_continua.repo.SesionRepo;
import cl.mineduc.came.apoyo_mejora_continua.repo.SupervisorRepo;
import cl.mineduc.came.apoyo_mejora_continua.repo.UsuarioRepo;

@Service
public class AdvisoryNetworksReportService {
	
	private static final String RED_COMPLEMENTARIA = "2446779961856492708";
	private static final Long TYPE_CATEGORY = 2420130603941758041l;
	private static final Long TYPE_SESION_REGISTRADA = 2465656905259812566l;
    private static final Long TYPE_SESION_PLANIFICADA = 2465656905284978391l;
    private static final String SESION_PLANIFICADA = "Sesión Planificada";
    private static final String SESION_REGISTRADA = "Sesión Registrada";
	
	@Autowired
    RegionIntegration regionIntegration;

    @Autowired
    UsuarioRepo usuarioRepo;

    @Autowired
    SupervisorRepo supervisorRepo;

    @Autowired
    ElementoListaRepo elementRepo;

    @Autowired
    PeriodoRepo periodRepo;
    
	@Autowired
	EstablecimientoOrdenacionRepo establecimientoOrdenacionRepo;
	
	@Autowired
	EstablecimientosIntegration establecimientosIntegration;
	
	@Autowired
    RedRepo redRepo;
	
	@Autowired
    RedSupervisorRepo redSupervisorRepo;
	
	@Autowired
    RedEstablecimientoRepo redEstablecimientoRepo;
	
	@Autowired
	AsesoriaRepo asesoriaRepo;

    @Autowired
    SesionRepo sesionRepo;
    
    @Autowired
    PerfilRepo perfilRepo;
    
    @Autowired
    SesionRedParticipantesRepo sesionRedParticipantesRepo;
    
    Date startDate;
	Date endDate;
	    
	public List<AdvisoryResultRedDTO> getRed(AdvisoryRequestDTO request) {
		List<AdvisoryResultRedDTO> result = new ArrayList<AdvisoryResultRedDTO>();

        // Establecimientos
        List<Establecimientos> establecimientos = this.establecimientosIntegration.getEstablecimientos(null, null, null, null, null, null, null, 999999);

        // ordenaciones
        List<EstablecimientoOrdenacion> ordenacion = establecimientoOrdenacionRepo.get();

        List<RegionResponseDTO> regiones = regionIntegration.getRegiones();
        // Supervisores
        List<Supervisor> supervisores = supervisorRepo.getAll();
        // periodo
        int year = Calendar.getInstance().get(Calendar.YEAR);
        Periodo period = periodRepo.getPeriodByYear(year);
        
        List<Red> redes = redRepo.getByIdPeriodo(period.getIdPeriodo());
        List<EstablecimientosDetailsDTO> ests = new ArrayList<EstablecimientosDetailsDTO>();
        List<SesionRedParticipanteDTO> participantes = new ArrayList<SesionRedParticipanteDTO>();
        redes.forEach(red -> {
        	

            RegionResponseDTO reg = regiones.stream().filter(r -> red.getIdRegion().equals((long) r.getIdRegion()))
                    .iterator().next();
           
            DeprovsResponse devPaso = reg.getDeprovs().stream()
                    .filter(r -> red.getIdDeprov().equals((long) r.getIdDeprov())).iterator().next();
            
           
            List<RedSupervisor> redSupervisores = redSupervisorRepo.getByIdRed(red.getIdRed());
            List<Long> supers = redSupervisores.stream().map(RedSupervisor::getIdSupervisor)
                    .collect(Collectors.toList());
            List<String> usernames = new ArrayList<String>();
            supers.forEach(s -> {
                Supervisor supervisor = supervisorRepo.getSupervisorById(s);
                UsuarioRegistrado user = usuarioRepo.findById(supervisor.getIdUsuario());
                usernames.add(user.getUsuarioDominio());
            });
            
            if ((request.getFechaInicio() != null && request.getFechaInicio() != "") || (request.getFechaFin() != null && request.getFechaFin() != "")) {
           	 startDate = StringHelper.convertToDate(request.getFechaInicio());
                endDate = StringHelper.convertToDate(request.getFechaFin());
			}else {
				startDate = StringHelper.convertToDateDMY("01-01-1970");
       		endDate = StringHelper.convertToDateDMY("31-12-" + year);
			}
           List<Asesoria> asesorias = asesoriaRepo.getByIdPeriodo(period.getIdPeriodo());
           
           List<Asesoria> asesoriaPaso = asesorias.stream()
                   .filter(x -> x.getIdRed() != null && x.getIdRed().equals(red.getIdRed()))
                   .filter(x -> x.getStartDate().getTime() >= (startDate.getTime()))
                   .filter(x -> x.getStartDate().getTime() <= (endDate.getTime()))
                   .collect(Collectors.toList());

           if (asesoriaPaso != null) {
           	asesoriaPaso.forEach(itemAsesoria -> {
           	 if (!red.getIdTipoRed().equals(Long.parseLong(RED_COMPLEMENTARIA))) {
             	List<Sesion> sesiones = this.sesionRepo.getByIdAsesoria(itemAsesoria.getIdAsesoria());
                 if (!sesiones.isEmpty()) {
                     sesiones.forEach(s -> {
                     	if (s.getIdSessionEstado().equals(TYPE_SESION_REGISTRADA)) {
                     		List<SesionRedParticipantes> parts = this.sesionRedParticipantesRepo.getByIdSesion(s.getIdSesion());
                     		 AdvisoryResultRedDTO pso = new AdvisoryResultRedDTO();
                 			 pso.setDeprov(devPaso.getNombreDeprov());
                     		 pso.setRegion(reg.getNombreRegion());
                     		 pso.setSupervisores(usernames);
                              pso.setNumeroSesion((sesiones.indexOf(s) + 1) + "");
                              pso.setFechaPlanificacion(s.getFechaPlanificacion().toLocaleString());
                              pso.setFechaRealizada(s.getFechaRealizada().toLocaleString());
                              pso.setEstado(SESION_REGISTRADA);

                          	redEstablecimientoRepo.getByIdRed(red.getIdRed()).forEach( x ->{
								Establecimientos estPaso1 = establecimientos.stream().filter(es -> es.getRbd().equals(x.getRbd())).iterator().next();
								
					                SesionRedParticipanteDTO srp = new SesionRedParticipanteDTO();
					                srp.setIdParticipante(x.getIdRedEstablecimiento().toString());
					                srp.setTipoParticipante("E");
					                srp.setNumero(establecimientos.indexOf(x) + 1);
					                srp.setRbd(estPaso1.getRbd().toString());
					                srp.setParticipante(estPaso1.getNombre());

					                Optional<SesionRedParticipantes> pa = parts.stream().filter(d -> d.getRbd().equals(Integer.parseInt(srp.getRbd()))).findFirst();
					                srp.setPresente(pa.isPresent() && pa.get().getPresente());
					                participantes.add(srp);
								
								EstablecimientosDetailsDTO e = new EstablecimientosDetailsDTO();								
								e.setValue(estPaso1.getRbd().toString());
								e.setDisplayText(estPaso1.getNombre());
								e.setStatus(x.getStatus());
								ests.add(e);
                          	});
                          	List<String> listaNombreEstablecimientos = new ArrayList<String>();
                          	List<String> listaNombreParticipantes = new ArrayList<String>();
                          	participantes.forEach(e ->{
                          		if (e.getPresente()) {
                          			listaNombreParticipantes.add(e.getParticipante());
								}
                          	});
                          	ests.forEach(e ->{
                          		listaNombreEstablecimientos.add(e.getDisplayText());
                          	});
                          	pso.setEstablecimientosPresentesReunion(listaNombreEstablecimientos);
                          	pso.setEstablecimientosEnRed(listaNombreEstablecimientos);
                          	ElementoLista elements = elementRepo.getElementsByIdLista(TYPE_CATEGORY).stream().filter(r -> r.getIdElementoLista().equals(red.getIdTipoRed())).iterator().next();
                             UsuarioRegistrado usuarioPaso = this.usuarioRepo.findById(red.getIdUsuario());
                             UsuarioPerfil usuarioPerfil = usuarioPaso.getPerfilesUsuarios().stream().filter(p -> p.getEndDate() == null)
                                     .iterator().next();
                             pso.setCargoEncargado(perfilRepo.obtenerPerfil(usuarioPerfil.getIdPerfil()).getNombre());
                             pso.setTipoRed(elements.getNombre());
                             pso.setNombreRed(red.getNombre());         
                             pso.setNumeroSesion(countSesionsRed(red.getIdRed(), period.getIdPeriodo()).toString());
                             pso.setEncargadoRed(usuarioPaso.getUsuarioDominio());
                             pso.setEmailEncargado(usuarioPaso.getEmail());
                             pso.setTipoPlanificacion("Red");
                             
                             
                             result.add(pso);
 						}
                     	
                     });
                 }
             	
             }
           	});
           }
           
            
            
            
        });
        

		return result;
	}
	
	private Integer countSesionsRed(Long idRed, Long idPeriodo) {
        List<Asesoria> asesorias = asesoriaRepo.getByIdPeriodo(idPeriodo);
        Optional<Asesoria> asesoriaPaso = asesorias.stream()
                .filter(x -> x.getIdRed() != null && x.getIdRed().equals(idRed)).findAny();
        return asesoriaPaso.isPresent() ? this.sesionRepo.getByIdAsesoria(asesoriaPaso.get().getIdAsesoria()).size()
                : 0;
    }
}
