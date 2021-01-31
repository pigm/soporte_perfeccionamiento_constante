package cl.mineduc.came.apoyo_mejora_continua.services;

import java.util.Date;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.mineduc.came.apoyo_mejora_continua.repo.AsignacionMaximaRepo;
import cl.mineduc.came.apoyo_mejora_continua.dto.asignacion_maxima.AsignacionMaximaDTO;
import cl.mineduc.came.apoyo_mejora_continua.helpers.UserHelper;
import cl.mineduc.came.apoyo_mejora_continua.modelo.AsignacionMaxima;

@Service
public class AsignacionMaximaService {

  @Autowired
  private ModelMapper modelMapper;

  @Autowired
  private AsignacionMaximaRepo asignacionMaximaRepo;

  // #region Private Methods
  private AsignacionMaximaDTO convertToDto(AsignacionMaxima param) {
    AsignacionMaximaDTO record = modelMapper.map(param, AsignacionMaximaDTO.class);
    return record;
  }

  private AsignacionMaxima dtoToModel(AsignacionMaximaDTO record) {
    AsignacionMaxima result = modelMapper.map(record, AsignacionMaxima.class);
    return result;
  }
  // #endregion

  public AsignacionMaximaDTO get() {
    return convertToDto(asignacionMaximaRepo.get());
  }

  public void updateAsignacionMaxima(AsignacionMaximaDTO record) {
    record.setIdUsuarioRegistro(UserHelper.getUsuarioRegistrado().getIdUsuario());
    record.setFechaRegistro(new Date());
    asignacionMaximaRepo.update(dtoToModel(record));
  }
}
