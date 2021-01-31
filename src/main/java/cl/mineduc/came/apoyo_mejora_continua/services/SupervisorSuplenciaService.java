package cl.mineduc.came.apoyo_mejora_continua.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import cl.mineduc.came.apoyo_mejora_continua.dto.integration.region.DeprovsResponse;
import cl.mineduc.came.apoyo_mejora_continua.dto.integration.region.RegionResponseDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.supervisor_suplencia.SupervisorDataDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.supervisor_suplencia.SupervisorSuplenciaDTO;
import cl.mineduc.came.apoyo_mejora_continua.helpers.UserHelper;
import cl.mineduc.came.apoyo_mejora_continua.integration.RegionIntegration;
import cl.mineduc.came.apoyo_mejora_continua.modelo.Perfil;
import cl.mineduc.came.apoyo_mejora_continua.modelo.Periodo;
import cl.mineduc.came.apoyo_mejora_continua.modelo.Supervisor;
import cl.mineduc.came.apoyo_mejora_continua.modelo.SupervisorSuplencia;
import cl.mineduc.came.apoyo_mejora_continua.modelo.UsuarioPerfil;
import cl.mineduc.came.apoyo_mejora_continua.modelo.UsuarioRegistrado;
import cl.mineduc.came.apoyo_mejora_continua.repo.BitacoraService;
import cl.mineduc.came.apoyo_mejora_continua.repo.PerfilRepo;
import cl.mineduc.came.apoyo_mejora_continua.repo.PeriodoRepo;
import cl.mineduc.came.apoyo_mejora_continua.repo.SupervisorRepo;
import cl.mineduc.came.apoyo_mejora_continua.repo.SupervisorSuplenciaRepo;
import cl.mineduc.came.apoyo_mejora_continua.repo.UsuarioRepo;
import cl.mineduc.framework2.exceptions.MineducException;
import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

@Service
public class SupervisorSuplenciaService {

    private static Logger logger = Logger.getLogger(SupervisorSuplenciaService.class);

    @Value("${template.suplencia.template}")
    private String templateSuplencia;

    @Value("${mail.to.send}")
    private String mailToSend;

    // #region Constantes
    private static final String PERFIL_SUPERVISOR = "2416264829774857258";
    // #endregion

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private SupervisorSuplenciaRepo supervisorSuplenciaRepo;

    @Autowired
    private UsuarioRepo usuarioRepo;

    @Autowired
    private PerfilRepo perfilRepo;

    @Autowired
    private SupervisorRepo supervisorRepo;

    @Autowired
    RegionIntegration regionIntegration;

    @Autowired
    private MailSenderService mailService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PeriodoRepo periodRepo;

    @Autowired
    BitacoraService bitacoraService;

    private String mailContent;
    private MimeMessage mensaje;

    // #region Private Methods
    private SupervisorSuplenciaDTO convertToDto(SupervisorSuplencia param) {
        SupervisorSuplenciaDTO record = modelMapper.map(param, SupervisorSuplenciaDTO.class);
        return record;
    }

    private SupervisorSuplencia dtoToModel(SupervisorSuplenciaDTO record) {
        SupervisorSuplencia result = modelMapper.map(record, SupervisorSuplencia.class);
        return result;
    }

    private UsuarioRegistrado buscarUsuarioRegistrado(String username) {
        return usuarioRepo.findByUsername(username);
    }
    // #endregion

    public List<SupervisorSuplenciaDTO> get() {
        List<SupervisorSuplenciaDTO> result = new ArrayList<SupervisorSuplenciaDTO>();

        int year = Calendar.getInstance().get(Calendar.YEAR);
        Periodo period = periodRepo.getPeriodByYear(year);
        if(period != null){
            List<SupervisorSuplencia> records = supervisorSuplenciaRepo.getByIdPeriodo(period.getIdPeriodo());
            records.forEach(s -> {
                SupervisorSuplenciaDTO recordDto = new SupervisorSuplenciaDTO();
                recordDto.setIdSupervisorSuplencia(s.getIdSupervisorSuplencia().toString());
                recordDto.setIdUsuarioSupervisorAusente(s.getIdSupervisorAusente().toString());
                recordDto.setIdUsuarioSupervisorSuplente(s.getIdSupervisorSuplente().toString());
                recordDto.setStartDate(s.getStartDate());
                recordDto.setEndDate(s.getEndDate());

                recordDto.setUserNameSA(s.getSupervisorAusente().getUsuario().getUsuarioDominio());
                recordDto.setApellidosSA(s.getSupervisorAusente().getUsuario().getPaterno() + " "
                        + s.getSupervisorAusente().getUsuario().getMaterno());
                recordDto.setNombresSA(s.getSupervisorAusente().getUsuario().getNombre());
                recordDto.setRunSA(s.getSupervisorAusente().getUsuario().getRut() + "-"
                        + s.getSupervisorAusente().getUsuario().getDv());

                recordDto.setUserNameSS(s.getSupervisorSuplente().getUsuario().getUsuarioDominio());
                recordDto.setApellidosSS(s.getSupervisorSuplente().getUsuario().getPaterno() + " "
                        + s.getSupervisorSuplente().getUsuario().getMaterno());
                recordDto.setNombresSS(s.getSupervisorSuplente().getUsuario().getNombre());
                recordDto.setRunSS(s.getSupervisorSuplente().getUsuario().getRut() + "-"
                        + s.getSupervisorSuplente().getUsuario().getDv());

                result.add(recordDto);
            });
        }      

        return result;
    }

    public SupervisorSuplenciaDTO getById(long recorId) {
        return convertToDto(supervisorSuplenciaRepo.getById(recorId));
    }

    public void insertSupervisorSuplencia(SupervisorSuplenciaDTO record) {
        // int year = Calendar.getInstance().get(Calendar.YEAR);
        // Periodo period = periodRepo.getPeriodByYear(year);

        // List<SupervisorSuplencia> recordsRepo = supervisorSuplenciaRepo.getByIdPeriodo(period.getIdPeriodo());
        // if(recordsRepo.isEmpty()){
            insertOrUpdateSuplencia(record);
        // }else{
        //     for (SupervisorSuplencia s : recordsRepo) {
        //         if (s.getIdSupervisorAusente().toString().equals(record.getIdUsuarioSupervisorAusente())) {
        //             if (!record.getStartDate().after(s.getStartDate()) && !record.getEndDate().before(s.getEndDate())) {
        //                 insertOrUpdateSuplencia(record);
        //             } else {
        //                 System.out.println("fechas iguales");
        //             }
        //         } else {
        //             insertOrUpdateSuplencia(record);
        //         }
        //     }
        // }    	
    }

    public Boolean isValidRange(String idSupervisorAusente, Date startDate, Date endDate){        
        int year = Calendar.getInstance().get(Calendar.YEAR);
        Periodo period = periodRepo.getPeriodByYear(year);

        List<Supervisor> supervisors = supervisorRepo.getAll().stream().filter(x -> x.getEndDate() == null)
                .collect(Collectors.toList());

        Supervisor supA = supervisors.stream()
                .filter(x -> x.getIdUsuario().equals(
                        Long.parseLong(idSupervisorAusente))).iterator()
                .next();

        List<SupervisorSuplencia> recordsRepo = supervisorSuplenciaRepo.getByIdPeriodo(period.getIdPeriodo());
        return recordsRepo.stream().filter(x -> x.getIdSupervisorAusente().equals(supA.getIdSupervisor()))
                                   .noneMatch(x -> x.getStartDate().getTime() <= startDate.getTime() && x.getEndDate().getTime() >= startDate.getTime() ||
                                                  x.getStartDate().getTime() <= endDate.getTime() && x.getEndDate().getTime() >= endDate.getTime() ||
                                                  (x.getStartDate().getTime() >= startDate.getTime() && x.getEndDate().getTime() <= endDate.getTime()));
    }
    
    private void insertOrUpdateSuplencia(SupervisorSuplenciaDTO record) {
    	try {
            UriComponentsBuilder builder = ServletUriComponentsBuilder.fromCurrentRequest();
            int year = Calendar.getInstance().get(Calendar.YEAR);
            Periodo period = periodRepo.getPeriodByYear(year);
            
            List<Supervisor> supervisors = supervisorRepo.getAll().stream().filter(x -> x.getEndDate() == null).collect(Collectors.toList());
           
	    	Supervisor supA = supervisors.stream()
                                        .filter(x -> x.getIdUsuario().equals(Long.parseLong(record.getIdUsuarioSupervisorAusente())))
                                        .iterator().next();

	        Supervisor supS = supervisors.stream()
                    .filter(x -> x.getIdUsuario().equals(Long.parseLong(record.getIdUsuarioSupervisorSuplente())))
                    .iterator().next();
	
	        SupervisorSuplencia supSupPaso = new SupervisorSuplencia();
	        if (record.getIdSupervisorSuplencia() != null && !record.getIdSupervisorSuplencia().isEmpty())
	            supSupPaso.setIdSupervisorSuplencia(Long.parseLong(record.getIdSupervisorSuplencia()));
	
	        supSupPaso.setIdSupervisorAusente(supA.getIdSupervisor());
	        supSupPaso.setIdSupervisorSuplente(supS.getIdSupervisor());
	        supSupPaso.setStartDate(record.getStartDate());
	        supSupPaso.setEndDate(record.getEndDate());
	        supSupPaso.setIdUsuarioRegistro(UserHelper.getUsuarioRegistrado().getIdUsuario());
	        supSupPaso.setFechaRegistro(new Date());
            supSupPaso.setIdPeriodo(period.getIdPeriodo());
            
	        if (supSupPaso.getIdSupervisorSuplencia() != null) {
                supervisorSuplenciaRepo.update(supSupPaso);
                bitacoraService.accionBitacora(2, supSupPaso,builder);
	        } else {            	
	    		supervisorSuplenciaRepo.insert(supSupPaso);
	        	bitacoraService.accionBitacora(1, supSupPaso,builder);
	        }
	        UsuarioRegistrado userSuplente = usuarioRepo
	                .findById(Long.parseLong(record.getIdUsuarioSupervisorAusente()));
	        UsuarioRegistrado userAusente = usuarioRepo
	                .findById(Long.parseLong(record.getIdUsuarioSupervisorSuplente()));
	
	        sendEmailSuplencia(userSuplente, userAusente);
    	}
    	catch (MineducException | IOException | TemplateException | MessagingException e) {
            logger.debug("Error insertar supervisor: ", e);
            throw new MineducException("Error al insertar supervisor.", e);
        }
    }

    private void sendEmailSuplencia(UsuarioRegistrado userSuplente, UsuarioRegistrado userAusente)
            throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException,
            TemplateException, MessagingException {
        Map<String, Object> setParameters = new HashMap<String, Object>();
        try {
            setParameters.put("suplente", userSuplente.getNombre()
                    .concat(" " + userSuplente.getPaterno().concat(" " + userSuplente.getMaterno())));
            setParameters.put("ausente", userAusente.getNombre()
                    .concat(" " + userAusente.getPaterno().concat(" " + userAusente.getMaterno())));

            setParameters.put("deprov", "deprov");
            List<UsuarioRegistrado> users = usuarioService.getUsersByProfile(Long.parseLong(PERFIL_SUPERVISOR));

            users.forEach(u -> {
                setParameters.put("nombre", u.getNombre().concat(" " + u.getPaterno().concat(" " + u.getMaterno())));
                try {
                    mailContent = mailService.parseMailTemplate(setParameters, templateSuplencia);
                    mensaje = mailService.prepareMail(u.getEmail(), u.getEmail(), mailContent);
                    mailService.send(mensaje);
                } catch (IOException | TemplateException | MessagingException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            });
            setParameters.put("nombre", "Supervisión");
            mailContent = mailService.parseMailTemplate(setParameters, templateSuplencia);
            mensaje = mailService.prepareMail(mailToSend, mailToSend, mailContent);
            mailService.send(mensaje);

        } catch (Exception e) {            
            this.logger.trace(e.getMessage());
        }
    }

    public List<String> getSupUserNames() {
        List<String> result = new ArrayList<String>();
        List<UsuarioRegistrado> usuarios = usuarioRepo.findAll();

        usuarios.forEach(u -> {
            UsuarioPerfil pu = u.getPerfilesUsuarios().stream().filter(p -> p.getEndDate() == null).iterator().next();
            if (pu.getIdPerfil().equals(Long.parseLong(PERFIL_SUPERVISOR))) {
                result.add(u.getUsuarioDominio());
            }
        });
        return result;
    }

    public SupervisorDataDTO getSupervisorData(String userName) {
        SupervisorDataDTO result = new SupervisorDataDTO();
        UsuarioRegistrado usuario = usuarioRepo.findByUsername(userName);
        if (usuario != null) {
            Perfil profileSup = perfilRepo.obtenerPerfil(Long.parseLong(PERFIL_SUPERVISOR));
            RegionResponseDTO regionPaso = regionIntegration.getRegionByNumber(usuario.getIdRegion());
            DeprovsResponse devPro = regionPaso.getDeprovs().stream()
                    .filter(d -> (long) d.getIdDeprov() == usuario.getIdDeprov()).iterator().next();

            result.setUsuarioId(usuario.getIdUsuario().toString());
            result.setUserName(userName);
            result.setNombre(usuario.getNombre() + " " + usuario.getPaterno() + " " + usuario.getMaterno());
            result.setPerfil(profileSup.getNombre());
            result.setRegion(regionPaso.getNombreRegion());
            result.setDeprov(devPro.getNombreDeprov());
        }
        return result;
    }
}
