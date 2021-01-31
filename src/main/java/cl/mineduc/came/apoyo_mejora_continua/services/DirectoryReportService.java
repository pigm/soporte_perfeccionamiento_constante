package cl.mineduc.came.apoyo_mejora_continua.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.mineduc.came.apoyo_mejora_continua.dto.establishment.EstablecimientosDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.integration.region.DeprovsResponse;
import cl.mineduc.came.apoyo_mejora_continua.dto.integration.region.RegionResponseDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.report.directory.DirectoryListDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.report.directory.DirectoryRequestDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.shared.SelectorDTO;
import cl.mineduc.came.apoyo_mejora_continua.integration.EstablecimientosIntegration;
import cl.mineduc.came.apoyo_mejora_continua.integration.RegionIntegration;
import cl.mineduc.came.apoyo_mejora_continua.integration.SostenedoresIntegration;
import cl.mineduc.came.apoyo_mejora_continua.modelo.AsesoriaDirecta;
import cl.mineduc.came.apoyo_mejora_continua.modelo.ElementoLista;
import cl.mineduc.came.apoyo_mejora_continua.modelo.EstablecimientoClasificacionSep;
import cl.mineduc.came.apoyo_mejora_continua.modelo.EstablecimientoOrdenacion;
import cl.mineduc.came.apoyo_mejora_continua.modelo.Periodo;
import cl.mineduc.came.apoyo_mejora_continua.modelo.Red;
import cl.mineduc.came.apoyo_mejora_continua.modelo.Supervisor;
import cl.mineduc.came.apoyo_mejora_continua.modelo.UsuarioRegistrado;
import cl.mineduc.came.apoyo_mejora_continua.modelo.api.Establecimientos;
import cl.mineduc.came.apoyo_mejora_continua.repo.AsesoriaDirectaRepo;
import cl.mineduc.came.apoyo_mejora_continua.repo.ElementoListaRepo;
import cl.mineduc.came.apoyo_mejora_continua.repo.EstablecimientoClasificacionSepRepo;
import cl.mineduc.came.apoyo_mejora_continua.repo.EstablecimientoOrdenacionRepo;
import cl.mineduc.came.apoyo_mejora_continua.repo.EstablecimientoUbicacionRepo;
import cl.mineduc.came.apoyo_mejora_continua.repo.PeriodoRepo;
import cl.mineduc.came.apoyo_mejora_continua.repo.RedRepo;
import cl.mineduc.came.apoyo_mejora_continua.repo.SupervisorRepo;
import cl.mineduc.came.apoyo_mejora_continua.repo.UsuarioRepo;

@Service
public class DirectoryReportService {
	private static Logger logger = Logger.getLogger(BibliotecaService.class);
	private static final Long TYPE_CATEGORY = 2420130603941758041l;

	@Autowired
	RegionIntegration regionIntegration;
	
	@Autowired
	  SostenedoresIntegration sostenedoresIntegration;

	@Autowired
	EstablecimientosIntegration establecimientosIntegration;

	@Autowired
	EstablecimientoOrdenacionRepo establecimientoOrdenacionRepo;

	@Autowired
	EstablecimientoClasificacionSepRepo establecimientoClasificacionSepRepo;

	@Autowired
	EstablecimientoUbicacionRepo establecimientoUbicacionRepo;
	
	@Autowired
	private AsesoriaDirectaRepo asesoriaDirectaRepo;
	
	@Autowired
	private SupervisorRepo supervisorRepo;
	
	@Autowired
	private UsuarioRepo usuarioRepo;
	
	@Autowired
	private RedRepo redRepo;
	
	@Autowired
	PeriodoRepo periodRepo;
	
	@Autowired
    private ElementoListaRepo elementRepo;
	
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
	
	public List<DirectoryListDTO> getDirectoryReport(DirectoryRequestDTO filter){
		 List<DirectoryListDTO> result = new ArrayList<DirectoryListDTO>();

	    List<EstablecimientoClasificacionSep> estClesep = establecimientoClasificacionSepRepo.get();
	    List<EstablecimientoOrdenacion> ordenacion = establecimientoOrdenacionRepo.get();
	    Integer region = filter.getRegion() != null && filter.getRegion() != "" ? Integer.parseInt(filter.getRegion()): null;
	    Integer deprov =  filter.getDeprov() != null && filter.getDeprov() != "" ? Integer.parseInt(filter.getDeprov()): null;
	    Integer comuna =  filter.getComuna() != null && filter.getComuna() != "" ? Integer.parseInt(filter.getComuna()): null;
	    
	    List<Establecimientos> establecimientos = this.establecimientosIntegration.getEstablecimientos(null, null, region, deprov, comuna, null, null, 999999);
	    int year = Calendar.getInstance().get(Calendar.YEAR);
		Periodo period = periodRepo.getPeriodByYear(year);
		List<Red> redes = redRepo.getByIdPeriodo(period.getIdPeriodo());
		List<ElementoLista> elements = elementRepo.getElementsByIdLista(TYPE_CATEGORY);
	    if (establecimientos != null) {


	      establecimientos.forEach(itemEstablecimiento -> {

    	  DirectoryListDTO record = new DirectoryListDTO();
	        record.setRbd(itemEstablecimiento.getRbd().toString() + "-" + itemEstablecimiento.getDvRbd());
	        record.setNombre(itemEstablecimiento.getNombre());
	        record.setRuralidad(itemEstablecimiento.getRural().equalsIgnoreCase("si") ? "Rural" : "Urbano");

	        List<EstablecimientoOrdenacion> orden = ordenacion.stream().filter(p -> p.getRbd().equals(itemEstablecimiento.getRbd()))
	            .collect(Collectors.toList());
	        if (!orden.isEmpty()) {
	          EstablecimientoOrdenacion o1 = new EstablecimientoOrdenacion();
	          EstablecimientoOrdenacion o2 = new EstablecimientoOrdenacion();

	          if(orden.size() >= 2){
	            o1 = orden.get(orden.size() - 2);
	          }
	                    
	          o2 = orden.get(orden.size() - 1);          
	          
	          record.setCategorizacion(
	              o1.getNivel() + ": " + o1.getOrdenacion() + " / " + o2.getNivel() + ": " + o2.getOrdenacion());
	        }

	        Optional<EstablecimientoClasificacionSep> clPaso = estClesep.stream().filter(x -> x.getRbd().equals(itemEstablecimiento.getRbd()))
	            .findFirst();
	        if (clPaso.isPresent()) {
	          record.setClasificacionSEP(clPaso.get().getClasificacion());
	        }

	        record.setDependencia(itemEstablecimiento.getTipoDependencia().getDescripcionTipoDependencia());
	        record.setEstado(itemEstablecimiento.getEstadoEstablecimiento().getGlosaEstado());
	        
	        
	        if(redes != null) {
	        	redes.forEach(itemRed -> {
	        		itemRed.getEstablecimientos().forEach(itemEstablecimientoRed -> {
			        	if(itemEstablecimientoRed.getRbd().equals(itemEstablecimiento.getRbd())) {
			        		itemRed.getSupervisores().forEach(s -> {
								Supervisor supervisor = supervisorRepo.getSupervisorById(s.getIdSupervisor());
								if (supervisor != null) {
									UsuarioRegistrado user = usuarioRepo.findById(supervisor.getIdUsuario());
									record.setSupervisor(user.getUsuarioDominio());
								}
								if (elements != null) {
									elements.forEach(e ->{
							            if (e.getIdElementoLista().equals(itemRed.getIdTipoRed())) {
							            	record.setTipoDeApoyo(e.getNombre());
							            }
							        });
								}
								
							});
			        	}
			        });
		        });
	        }
	       
	        List<AsesoriaDirecta> asesorias = this.asesoriaDirectaRepo.getByIdPeriodo(period.getIdPeriodo()).stream()
					.filter(x -> itemEstablecimiento.getRbd() == null || x.getRbd().equals(itemEstablecimiento.getRbd()))
					.collect(Collectors.toList());
	        
	        if (asesorias != null) {
	        	asesorias.forEach(itemAsesoria -> {
						Supervisor supervisor = supervisorRepo.getSupervisorById(itemAsesoria.getIdSupervisor());
						if (supervisor != null) {
							UsuarioRegistrado user = usuarioRepo.findById(supervisor.getIdUsuario());
							record.setSupervisor(user.getUsuarioDominio());							
						}
						record.setTipoDeApoyo("Directa");
	        	});
			}
	        

	        result.add(record);
	      });
	    }

	    return result;
	}
	
	
}
