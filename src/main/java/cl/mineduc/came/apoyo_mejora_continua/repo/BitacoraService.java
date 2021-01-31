package cl.mineduc.came.apoyo_mejora_continua.repo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import cl.mineduc.came.apoyo_mejora_continua.dto.users.UserEnvDTO;
import cl.mineduc.came.apoyo_mejora_continua.helpers.UserHelper;
import cl.mineduc.came.apoyo_mejora_continua.helpers.Util;
import cl.mineduc.came.apoyo_mejora_continua.modelo.Bitacora;
import cl.mineduc.came.apoyo_mejora_continua.modelo.Modulo;
import cl.mineduc.came.apoyo_mejora_continua.modelo.SubModulo;
import cl.mineduc.came.apoyo_mejora_continua.modelo.UsuarioRegistrado;
import cl.mineduc.came.apoyo_mejora_continua.services.UsuarioService;

@Service
public class BitacoraService {

    private static Logger logger = LogManager.getLogger(BitacoraService.class);

    @Autowired
    private BitacoraRepo bitacoraRepo;

    @Autowired
    private SubModuloRepo subModuloRepo;

    @Autowired
    private ModuloRepo moduloRepo;

    @Autowired
    private UsuarioRepo usuarioRepo;

    @Autowired
    private UsuarioService usuarioService;

    public void accionBitacora(int accion,Object obj,UriComponentsBuilder builder){

        Bitacora bitacora = new Bitacora();
        String evento = "";
        String url ="";
        SimpleDateFormat dt = new SimpleDateFormat("yyyyy-mm-dd hh:mm:ss"); 
        Date date = new Date();
        UserEnvDTO userPaso = usuarioService.getUserEnv(UserHelper.getUsuarioRegistrado().getIdUsuario());
        UsuarioRegistrado user = this.usuarioRepo.findById(UserHelper.getUsuarioRegistrado().getIdUsuario());
        String data = Util.objToJson(obj);
        String path = builder.buildAndExpand().getPath();
        if(null!=path && path.length()>0){
            url = path.substring(0, (path.lastIndexOf("/") + 1));
            SubModulo subMod = subModuloRepo.getSubModuloByUrl(url);
            bitacora.setSubModulo(null!=subMod?subMod.getNombre():"");
            Modulo mod = moduloRepo.getById(subMod.getIdModulo());
            bitacora.setModulo(null!=subMod?mod.getNombre():"");
            
        }
        
        
        bitacora.setUsuarioDominio(user.getUsuarioDominio());
        bitacora.setProfile(userPaso.getPerfil());
        bitacora.setData(data);
        bitacora.setFecha(new Timestamp(date.getTime()));
        try {

            switch (accion) {
                case 1:
                    evento = "Adición";
                    bitacora.setEvento(evento);
                    bitacoraRepo.insert(bitacora);
                    break;

                case 2:
                    evento = "Edición";
                    bitacora.setEvento(evento);
                    bitacoraRepo.insert(bitacora);
                    break;    
            
                case 3:
                    evento = "Eliminar";
                    bitacora.setEvento(evento);
                    bitacoraRepo.insert(bitacora);
                    break; 

                default:
                    break;
            }
            
        } catch (Exception e) {
            logger.debug("Error insertar registro en bitacora: ", e);
        }finally{
            logger.info("Tarea inserción de Bitacora finalizado ");
        }

    }
    
}
