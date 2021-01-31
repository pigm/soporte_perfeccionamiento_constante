package cl.mineduc.came.apoyo_mejora_continua.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.mineduc.came.apoyo_mejora_continua.dto.establishment.EstablecimientosDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.establishment.EstablecimientosRequestDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.integration.region.RegionResponseDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.shared.SelectorDTO;
import cl.mineduc.came.apoyo_mejora_continua.integration.EstablecimientosIntegration;
import cl.mineduc.came.apoyo_mejora_continua.integration.RegionIntegration;
import cl.mineduc.came.apoyo_mejora_continua.integration.SostenedoresIntegration;

import cl.mineduc.came.apoyo_mejora_continua.modelo.EstablecimientoClasificacionSep;
import cl.mineduc.came.apoyo_mejora_continua.modelo.EstablecimientoOrdenacion;
import cl.mineduc.came.apoyo_mejora_continua.modelo.EstablecimientoUbicacion;
import cl.mineduc.came.apoyo_mejora_continua.modelo.api.Establecimientos;
import cl.mineduc.came.apoyo_mejora_continua.repo.EstablecimientoClasificacionSepRepo;
import cl.mineduc.came.apoyo_mejora_continua.repo.EstablecimientoOrdenacionRepo;
import cl.mineduc.came.apoyo_mejora_continua.repo.EstablecimientoUbicacionRepo;

@Service
public class EstablishmentService {

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

  public List<EstablecimientosDTO> getEstablecimientos(EstablecimientosRequestDTO filter) {
    List<EstablecimientosDTO> result = new ArrayList<EstablecimientosDTO>();

    List<EstablecimientoClasificacionSep> estClesep = establecimientoClasificacionSepRepo.get().stream().filter(x -> filter.getClasificacionSEP().isEmpty() || x.getIdClasificacionSep().equals(Long.parseLong(filter.getClasificacionSEP()))).collect(Collectors.toList());
    List<EstablecimientoOrdenacion> ordenacion = establecimientoOrdenacionRepo.get().stream().filter(x -> filter.getCategorizacion().isEmpty() ||  x.getOrdenacion().equalsIgnoreCase(filter.getCategorizacion())).collect(Collectors.toList());

    List<Establecimientos> list = this.establecimientosIntegration.getEstablecimientos(
        filter.getEstado(),
        filter.getDependencia(), filter.getRegion(), filter.getDeprov(), filter.getComuna(), filter.getFilterText(),
        filter.getConvenio(), 999999);
    if (list != null) {

      List<Establecimientos> listPasoFiltered = list.stream()
                                                    .filter(x -> filter.getRegion() == null || (x.getCodigoGeografico()!= null && x.getCodigoGeografico().getCodigoRegion().equals(filter.getRegion())))
                                                    .filter(x -> filter.getDeprov() == null || (x.getCodigoGeografico()!= null && x.getCodigoGeografico().getCodigoDeprov().equals(filter.getDeprov())))
                                                    .filter(x -> filter.getRuralidad().isEmpty() || (x.getRural().equalsIgnoreCase(filter.getRuralidad())))
                                                    .filter(x -> filter.getClasificacionSEP().isEmpty() || estClesep.stream().anyMatch(p -> p.getRbd().equals(x.getRbd())))
                                                    .filter(x -> filter.getCategorizacion().isEmpty() || ordenacion.stream().anyMatch(p -> p.getRbd().equals(x.getRbd())))
                                                    .collect(Collectors.toList());

      listPasoFiltered.forEach(r -> {

        EstablecimientosDTO record = new EstablecimientosDTO();
        record.setRbd(r.getRbd().toString() + "-" + r.getDvRbd());
        record.setNombre(r.getNombre());
        if (r.getCodigoGeografico() != null) {
          record.setRegion(r.getCodigoGeografico().getNombreRegion());
          record.setDeprov(r.getCodigoGeografico().getNombreDeprov());
          record.setComuna(r.getCodigoGeografico().getNombreComuna());
        }

        record.setRuralidad(r.getRural().equalsIgnoreCase("si") ? "Rural" : "Urbano");

        List<EstablecimientoOrdenacion> orden = ordenacion.stream().filter(p -> p.getRbd().equals(r.getRbd()))
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

        Optional<EstablecimientoClasificacionSep> clPaso = estClesep.stream().filter(x -> x.getRbd().equals(r.getRbd()))
            .findFirst();
        if (clPaso.isPresent()) {
          record.setClasificacionSEP(clPaso.get().getClasificacion());
        }

        record.setDependencia(r.getTipoDependencia().getDescripcionTipoDependencia());
        record.setEstado(r.getEstadoEstablecimiento().getGlosaEstado());

        result.add(record);
      });
    }

    return result;
  }

  public Establecimientos getEstablecimientos(int rbd) {
    Establecimientos est = this.establecimientosIntegration.getEstablecimientoByRbd(rbd);

    est.setRural(est.getRural().equalsIgnoreCase("si") ? "Rural" : "Urbano");
    est.setOrden(establecimientoOrdenacionRepo.getByRbd(est.getRbd()));
    Optional<EstablecimientoOrdenacion> pso = est.getOrden().stream().filter(x -> x.getNivel().equalsIgnoreCase("media")
        && (!x.getOrdenacion().isEmpty() && (x.getOrdenacion().length() != (0)))).findFirst();
    if (pso.isPresent()) {
      est.setCategoriaMedia(pso.get().getAnio() + " - " + pso.get().getOrdenacion());
    }

    Optional<EstablecimientoOrdenacion> pso2 = est.getOrden().stream()
        .filter(x -> x.getNivel().equalsIgnoreCase("basica")
            && (!x.getOrdenacion().isEmpty() && (x.getOrdenacion().length() != (0))))
        .findFirst();
    if (pso2.isPresent()) {
      est.setCategoriaBasica(pso2.get().getAnio() + " - " + pso2.get().getOrdenacion());
    }

    EstablecimientoClasificacionSep clPaso = establecimientoClasificacionSepRepo.getByRbd(rbd);
    if (clPaso != null) {
      est.setClasificacionSep(clPaso.getClasificacion());
    }

    EstablecimientoUbicacion ubicacionPaso = establecimientoUbicacionRepo.getByRbd(rbd);
    if (ubicacionPaso != null) {
      est.getCodigoGeografico().setLat(ubicacionPaso.getLatitud());
      est.getCodigoGeografico().setLon(ubicacionPaso.getLongitud());
    }

    return est;
  }

}
