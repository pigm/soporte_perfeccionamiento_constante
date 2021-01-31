package cl.mineduc.came.apoyo_mejora_continua.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import java.util.stream.Collectors;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cl.mineduc.came.apoyo_mejora_continua.dto.integration.region.DeprovsResponse;
import cl.mineduc.came.apoyo_mejora_continua.dto.integration.region.RegionResponseDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.message.MessageDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.message.MessageProfileDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.message.MessageRequestDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.profile.ProfileDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.shared.SelectorDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.users.ProfileSelectorDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.users.UserEnvDTO;
import cl.mineduc.came.apoyo_mejora_continua.helpers.UserHelper;
import cl.mineduc.came.apoyo_mejora_continua.integration.RegionIntegration;
import cl.mineduc.came.apoyo_mejora_continua.modelo.Mensaje;
import cl.mineduc.came.apoyo_mejora_continua.modelo.MensajePerfil;
import cl.mineduc.came.apoyo_mejora_continua.modelo.Perfil;
import cl.mineduc.came.apoyo_mejora_continua.modelo.Periodo;
import cl.mineduc.came.apoyo_mejora_continua.repo.MensajePerfilRepo;
import cl.mineduc.came.apoyo_mejora_continua.repo.MensajeRepo;
import cl.mineduc.came.apoyo_mejora_continua.repo.PerfilRepo;
import cl.mineduc.came.apoyo_mejora_continua.repo.PeriodoRepo;

@Service
public class MessengerService {

    private static Logger logger = LogManager.getLogger(MessengerService.class);

    @Autowired
    private RegionIntegration regionIntegration;

    @Autowired
    private PerfilRepo perfilRepo;
    
    @Autowired
    private MensajePerfilRepo mensajePerfilRepo;

    @Autowired
    private MensajeRepo mensajeRepo;

    @Autowired
    private PeriodoRepo periodRepo;

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

    public List<SelectorDTO> getDeprovByRegion(String[] value) {
        List<SelectorDTO> result = new ArrayList<SelectorDTO>();
        for (String valRegion : value) {
            RegionResponseDTO regionesPaso = regionIntegration.getRegionByNumber(Long.valueOf(valRegion));
            regionesPaso.getDeprovs().forEach(p -> result.add(SelectorDTO.of(p.getIdDeprov().toString(), p.getNombreDeprov())));            
        }
        return result;
    }

    public List<SelectorDTO> getAllDeprov() {
        List<SelectorDTO> result = new ArrayList<SelectorDTO>();
        SelectorDTO provinciaDTO = null;
        List<RegionResponseDTO> listRegionesPaso = regionIntegration.getRegiones();
        for (RegionResponseDTO regionResponseDTO : listRegionesPaso) {
            for (DeprovsResponse provincia : regionResponseDTO.getDeprovs()) {
                provinciaDTO = new SelectorDTO();
                provinciaDTO.setValue(provincia.getIdDeprov().toString());
                provinciaDTO.setDisplayText(provincia.getNombreDeprov());
                provinciaDTO.setId(regionResponseDTO.getIdRegion().toString());
                result.add(provinciaDTO);
            }
        }
        
        return result;
        
    }

    public List<ProfileSelectorDTO> getProfiles() {
		List<ProfileSelectorDTO> result = new ArrayList<ProfileSelectorDTO>();
		List<Perfil> profilesPaso = this.perfilRepo.obtenerPerfiles().stream().filter(p -> p.getHabilitado())
				.collect(Collectors.toList());

		profilesPaso.forEach(p -> {
			ProfileSelectorDTO selPaso = new ProfileSelectorDTO();
			selPaso.setValue(p.getIdPerfil().toString());
			selPaso.setDisplayText(p.getNombre());
			selPaso.setNivel(p.getPerfilNivel().getNombre());

			result.add(selPaso);
		});

        return result;

    }
    
    @Transactional(rollbackFor = Throwable.class)
    public void setMessage(MessageDTO record){


        if(null == record.getIdPerfilMensaje()){
            Long idUsuarioRegistrado = UserHelper.getUsuarioRegistrado().getIdUsuario();
            
            Mensaje mensaje = new Mensaje();
            mensaje.setAsunto(record.getAsunto());
            mensaje.setMensaje(record.getMensaje());
            mensaje.setMensaje(record.getMensaje());
            mensaje.setFechaRegistro(new Date());
            mensaje.setIdUsuarioEnvia(idUsuarioRegistrado);
            mensaje.setIdUsuarioRegistro(idUsuarioRegistrado);
            mensajeRepo.insertaMensaje(mensaje);
            
            int year = Calendar.getInstance().get(Calendar.YEAR);
            Periodo period = periodRepo.getPeriodByYear(year);           

            List<String> listPerfiles = record.getIdPerfil();
            List<String> listRegion = record.getIdRegion();
        
            for (String idPerfil : listPerfiles) {
                MensajePerfil mensajePerfil = new MensajePerfil();
                mensajePerfil.setIdMensaje(mensaje.getId());
                mensajePerfil.setIdPeriodo(period.getIdPeriodo());
                mensajePerfil.setLeido(Boolean.FALSE);
                mensajePerfil.setIdPerfil(Long.parseLong(idPerfil));
                
                for (String idRegion : listRegion) {
                    RegionResponseDTO region = regionIntegration.getRegionByNumber(Long.valueOf(idRegion));
                    List<String> provsStr = record.getIdProvincia();
                    for (DeprovsResponse provObj : region.getDeprovs()) {
                        if(provsStr.contains(String.valueOf(provObj.getIdDeprov()))){

                            mensajePerfil.setIdRegion(Long.parseLong(idRegion));
                            mensajePerfil.setIdDeprov(provObj.getIdDeprov().longValue());
                            mensajePerfil.setIdUsuarioRegistro(idUsuarioRegistrado);
                            mensajePerfil.setFechaRegistro(new Date());
                            mensajePerfilRepo.insertaMensajePerfil(mensajePerfil);
                        } 
                    }                    
                }
            }
        } else{
            MensajePerfil mensajePerfil = new MensajePerfil();
            mensajePerfil.setIdPerfilMensaje(Long.parseLong(record.getIdPerfilMensaje()));
            mensajePerfil.setLeido(record.getLeido());
            mensajePerfil.setFechaRegistro(new Date());
            mensajePerfilRepo.modificaMensajePerfil(mensajePerfil);
        }
    }

    public List<MessageProfileDTO> getMessages(MessageRequestDTO request){

        List<MessageProfileDTO> listMP = new ArrayList<MessageProfileDTO>();
        
        int year = Calendar.getInstance().get(Calendar.YEAR);
        Periodo period = periodRepo.getPeriodByYear(year);

        List<MensajePerfil> listMPDB = mensajePerfilRepo.getByIdPeriodo(period.getIdPeriodo());
        if (!listMPDB.isEmpty()) {
            listMPDB.stream()
                    .filter(pm -> request.getIdPerfil() == null || request.getIdPerfil().isEmpty()
                            || pm.getIdPerfil().equals(Long.parseLong(request.getIdPerfil())))
                    .filter(pm -> request.getIdRegion() == null
                            || pm.getIdRegion().equals((long) request.getIdRegion()))
                    .filter(pm -> request.getIdDeprov() == null
                            || pm.getIdDeprov().equals((long) request.getIdDeprov()))
                    .filter(pm -> request.getLeido() == null || pm.getLeido().equals(request.getLeido()))
                    .forEach(mensajePerfil -> {                       
                        MessageProfileDTO messageDTO = new MessageProfileDTO();
                        ProfileDTO profileDTO = new ProfileDTO();

                        profileDTO.setIdPerfil(mensajePerfil.getPerfil().getIdPerfil().toString());
                        profileDTO.setNombre(mensajePerfil.getPerfil().getNombre());
                        messageDTO.setIdPerfilMensaje(mensajePerfil.getIdPerfilMensaje().toString());
                        messageDTO.setIdPerfil(mensajePerfil.getPerfil().getIdPerfil().toString());
                        messageDTO.setIdMensaje(mensajePerfil.getMensaje().getId().toString());
                        messageDTO.setLeido(mensajePerfil.getLeido());
                        messageDTO.setPerfil(profileDTO);
                        messageDTO.setFechaCreacion(mensajePerfil.getMensaje().getFechaRegistro());
                        messageDTO.setFechaLeido(mensajePerfil.getFechaRegistro());

                        RegionResponseDTO regionDTO = regionIntegration.getRegionByNumber(mensajePerfil.getIdRegion());

                        messageDTO.setNombreRegion(regionDTO.getNombreRegion());
                        messageDTO.setIdRegion(regionDTO.getIdRegion().toString());
                        for (DeprovsResponse provResp : regionDTO.getDeprovs()) {
                            if (provResp.getIdDeprov().longValue() == mensajePerfil.getIdDeprov()) {
                                messageDTO.setNombreProvincia(provResp.getNombreDeprov());
                                messageDTO.setIdProvincia(provResp.getIdDeprov().toString());
                            }
                        }
                        messageDTO.setAsunto(mensajePerfil.getMensaje().getAsunto());
                        messageDTO.setContenidoMensaje(mensajePerfil.getMensaje().getMensaje());

                        listMP.add(messageDTO);
                    });
        }
        
        return listMP;      
    }

    public MessageDTO getMessageDetail(Long idMensaje){

        MessageDTO msgDTO = new MessageDTO();
        Mensaje msg = mensajeRepo.obtenerMensaje(idMensaje);
        
        if(null != msg ){
            msgDTO.setAsunto(msg.getAsunto());
            msgDTO.setMensaje(msg.getMensaje());
        }

        return msgDTO;
    }

    public void updateMessage(Long MessageProfileDTO){

        MessageDTO msgDTO = new MessageDTO();

    }

}
