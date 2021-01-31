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

import cl.mineduc.came.apoyo_mejora_continua.dto.dynamicreport.DynamicListDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.dynamicreport.DynamicRequestDTO;
import cl.mineduc.came.apoyo_mejora_continua.integration.EstablecimientosIntegration;
import cl.mineduc.came.apoyo_mejora_continua.modelo.Asesoria;
import cl.mineduc.came.apoyo_mejora_continua.modelo.AsesoriaDirecta;
import cl.mineduc.came.apoyo_mejora_continua.modelo.ElementoLista;
import cl.mineduc.came.apoyo_mejora_continua.modelo.Periodo;
import cl.mineduc.came.apoyo_mejora_continua.modelo.Red;
import cl.mineduc.came.apoyo_mejora_continua.modelo.RedEstablecimiento;
import cl.mineduc.came.apoyo_mejora_continua.modelo.RedSupervisor;
import cl.mineduc.came.apoyo_mejora_continua.modelo.Sesion;
import cl.mineduc.came.apoyo_mejora_continua.modelo.Supervisor;
import cl.mineduc.came.apoyo_mejora_continua.modelo.UsuarioRegistrado;
import cl.mineduc.came.apoyo_mejora_continua.modelo.api.Establecimientos;
import cl.mineduc.came.apoyo_mejora_continua.repo.AsesoriaDirectaRepo;
import cl.mineduc.came.apoyo_mejora_continua.repo.AsesoriaRepo;
import cl.mineduc.came.apoyo_mejora_continua.repo.ElementoListaRepo;
import cl.mineduc.came.apoyo_mejora_continua.repo.PeriodoRepo;
import cl.mineduc.came.apoyo_mejora_continua.repo.RedEstablecimientoRepo;
import cl.mineduc.came.apoyo_mejora_continua.repo.RedRepo;
import cl.mineduc.came.apoyo_mejora_continua.repo.RedSupervisorRepo;
import cl.mineduc.came.apoyo_mejora_continua.repo.SesionRepo;
import cl.mineduc.came.apoyo_mejora_continua.repo.SupervisorRepo;
import cl.mineduc.came.apoyo_mejora_continua.repo.UsuarioRepo;

@Service
public class DynamicReportService {
    private static Logger logger = LogManager.getLogger(DynamicReportService.class);

    private static final Long TYPE_CATEGORY = 2420130603941758041l;

    @Autowired
    private UsuarioRepo usuarioRepo;

    @Autowired
    private PeriodoRepo periodRepo;

    @Autowired
    private SupervisorRepo supervisorRepo;

    @Autowired
    private AsesoriaDirectaRepo asesoriaDirectaRepo;

    @Autowired
    private RedEstablecimientoRepo redEstablecimientoRepo;

    @Autowired
    private RedRepo redRepo;

    @Autowired
    private AsesoriaRepo asesoriaRepo;

    @Autowired
    private SesionRepo sesionRepo;

    @Autowired
    private ElementoListaRepo elementRepo;

    @Autowired
    private RedSupervisorRepo redSupervisorRepo;

    @Autowired
    EstablecimientosIntegration establecimientosIntegration;

    public List<DynamicListDTO> getDynamic(DynamicRequestDTO request) {
        List<DynamicListDTO> result = new ArrayList<DynamicListDTO>();

        int year = Calendar.getInstance().get(Calendar.YEAR);
        Periodo period = periodRepo.getPeriodByYear(year);

        List<Asesoria> asesorias = this.asesoriaRepo.getByIdPeriodo(period.getIdPeriodo());

        // obtengo las asesorias directas
        List<AsesoriaDirecta> asesoriasDirectas = asesoriaDirectaRepo.getByIdPeriodo(period.getIdPeriodo());
        List<Integer> establecimientos = asesoriasDirectas.stream().map(AsesoriaDirecta::getRbd)
                .collect(Collectors.toList());

        // obtengo los tipos de red
        List<ElementoLista> elements = elementRepo.getElementsByIdLista(TYPE_CATEGORY);

        // obtengo las redes
        List<Red> redes = this.redRepo.getByIdPeriodo(period.getIdPeriodo());
        redes.forEach(r -> {
            r.getEstablecimientos().forEach(re -> {
                establecimientos.add(re.getRbd());
            });
        });

        establecimientos.stream().distinct().forEach(e -> {
            Establecimientos estInt = establecimientosIntegration.getEstablecimientoByRbd(e, false);
            if (estInt != null) {
                DynamicListDTO dyn = new DynamicListDTO();
                dyn.setRbd(estInt.getRbd().toString());
                dyn.setEstablecimiento(estInt.getNombre());
                dyn.setEstado("");
                dyn.setRegion(estInt.getCodigoGeografico().getNombreRegion());
                dyn.setDeprov(estInt.getCodigoGeografico().getNombreDeprov());
                dyn.setComuna(estInt.getCodigoGeografico().getNombreComuna());
                dyn.setDependencia(estInt.getTipoDependencia().getDescripcionTipoDependencia());
                dyn.setCategorizacion(estInt.getCategoriaBasica());
                dyn.setPeriodo(period.getAnio());
                // Aqui comienza el juego!!!
                String tipoApoyo = "";
                Optional<AsesoriaDirecta> ad = asesoriasDirectas.stream().filter(x -> x.getRbd().equals(e)).findFirst();
                if (ad.isPresent()) {
                    tipoApoyo = "Directa";
                    if (ad.get().getIdSupervisor() != null) {
                        Supervisor supervisor = supervisorRepo.getSupervisorById(ad.get().getIdSupervisor());
                        dyn.setSupervisorDirecta(usuarioRepo.findById(supervisor.getIdUsuario()).getUsuarioDominio());
                        List<Asesoria> ass = asesorias.stream()
                                .filter(a -> a.getIdAsesoriaDirecta() != null
                                        && a.getIdAsesoriaDirecta().equals(ad.get().getIdAsesoriaDirecta()))
                                .collect(Collectors.toList());
                        ass.forEach(x -> {
                            List<Sesion> sesionsD = this.sesionRepo.getByIdAsesoria(x.getIdAsesoria());
                            dyn.setSesionesProgramadasDirecta(sesionsD.size());
                            dyn.setSesionesRealizadasDirecta(
                                    (int) sesionsD.stream().filter(x1 -> x1.getFechaRealizada() != null).count());
                        });
                    }
                }
                dyn.setSesionesProgramadasDirecta(
                        dyn.getSesionesProgramadasDirecta() != null ? dyn.getSesionesProgramadasDirecta() : 0);
                dyn.setSesionesRealizadasDirecta(
                        dyn.getSesionesRealizadasDirecta() != null ? dyn.getSesionesRealizadasDirecta() : 0);

                Optional<Red> red = redes.stream()
                        .filter(x -> x.getEstablecimientos().stream().anyMatch(est -> est.getRbd().equals(e)))
                        .findAny();
                if (red.isPresent()) {
                    tipoApoyo = tipoApoyo + (tipoApoyo.length() > 0 ? " / " : "") + "Red";
                    ElementoLista tipoRed = elements.stream()
                            .filter(tr -> tr.getIdElementoLista().equals(red.get().getIdTipoRed())).iterator().next();
                    dyn.setTipoRed(tipoRed.getNombre());
                    List<RedSupervisor> redSupervisores = redSupervisorRepo.getByIdRed(red.get().getIdRed());
                    List<Long> supervisores = redSupervisores.stream().map(RedSupervisor::getIdSupervisor)
                            .collect(Collectors.toList());
                    List<String> usernames = new ArrayList<String>();
                    supervisores.forEach(s -> {
                        Supervisor supervisor = supervisorRepo.getSupervisorById(s);
                        UsuarioRegistrado user = usuarioRepo.findById(supervisor.getIdUsuario());
                        usernames.add(user.getUsuarioDominio());
                    });
                    dyn.setSupervisorRed(usernames);
                    dyn.setNombreRed(red.get().getNombre());
                    List<Asesoria> ass = asesorias.stream()
                            .filter(a -> a.getIdRed() != null && a.getIdRed().equals(red.get().getIdRed())).collect(Collectors.toList());
                    ass.forEach(x -> {
                        List<Sesion> sesionsD = this.sesionRepo.getByIdAsesoria(x.getIdAsesoria());
                        dyn.setSesionesProgramadasRed(sesionsD.size());
                        dyn.setSesionesRealizadasRed((int)sesionsD.stream().filter(x1 -> x1.getFechaRealizada() != null).count());
                    });
                }
                dyn.setSesionesProgramadasRed(
                        dyn.getSesionesProgramadasRed() != null ? dyn.getSesionesProgramadasRed() : 0);
                dyn.setSesionesRealizadasRed(
                        dyn.getSesionesRealizadasRed() != null ? dyn.getSesionesRealizadasRed() : 0);

                dyn.setTipoApoyo(tipoApoyo);

                result.add(dyn);
            }
        });

        return result;
    }

}
