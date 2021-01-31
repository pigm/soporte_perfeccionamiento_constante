package cl.mineduc.came.apoyo_mejora_continua.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cl.mineduc.came.apoyo_mejora_continua.dto.foco.AccionesMejorasDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.foco.FocoDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.foco.MovimientosClavesDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.shared.SelectorDTO;
import cl.mineduc.came.apoyo_mejora_continua.helpers.Entity;
import cl.mineduc.came.apoyo_mejora_continua.helpers.UserHelper;
import cl.mineduc.came.apoyo_mejora_continua.modelo.AccionesMejoras;
import cl.mineduc.came.apoyo_mejora_continua.modelo.Foco;
import cl.mineduc.came.apoyo_mejora_continua.modelo.MovimientosClaves;
import cl.mineduc.came.apoyo_mejora_continua.modelo.Periodo;
import cl.mineduc.came.apoyo_mejora_continua.repo.AccionesMejorasRepo;
import cl.mineduc.came.apoyo_mejora_continua.repo.FocoRepo;
import cl.mineduc.came.apoyo_mejora_continua.repo.MovimientosClavesRepo;
import cl.mineduc.came.apoyo_mejora_continua.repo.PeriodoRepo;
import cl.mineduc.framework2.exceptions.MineducException;

@Service
public class FocoService {

	@Autowired
	private FocoRepo focoRepo;

	@Autowired
	private MovimientosClavesRepo movRepo;

	@Autowired
	private AccionesMejorasRepo accionesRepo;

	@Autowired
	private PeriodoRepo periodoRepo;

	public List<FocoDTO> getFocus(Integer year) {

		List<Foco> focos = focoRepo.getAllFocos().stream().filter(x -> x.getPeriodo().equals(year))
				.collect(Collectors.toList());
		List<FocoDTO> dtos = new ArrayList<FocoDTO>();		
		focos.forEach(foco -> {			
			FocoDTO focoDto = new FocoDTO();				
			focoDto.setIdFoco(foco.getIdFoco().toString());
			focoDto.setNombre(foco.getNombre());
			focoDto.setDescripcion(foco.getDescripcion());
			focoDto.setPeriodo(foco.getPeriodo());
			focoDto.setFechaRegistro(foco.getFechaRegistro());
			focoDto.setUltimaModificacion(foco.getUltimaModificacion());
			List<AccionesMejorasDTO> acciones = new ArrayList<AccionesMejorasDTO>();
			foco.getAcciones().forEach(a -> {
				AccionesMejorasDTO am = new AccionesMejorasDTO();
				am.setNombre(a.getNombre());
				List<MovimientosClavesDTO> movimientos = new ArrayList<MovimientosClavesDTO>();		
				a.getMovimientos().forEach(m -> {
					MovimientosClavesDTO mov = new MovimientosClavesDTO();
					mov.setNombre(m.getNombre());
					movimientos.add(mov);
				});		
				am.setMovimientos(movimientos);
				acciones.add(am);	
			});
			focoDto.setAcciones(acciones);

			dtos.add(focoDto);
		});
		return dtos;
	}

	public FocoDTO getFocusById(Long id) {
		Foco foco = focoRepo.getFocoById(id);
		FocoDTO focoDto = new FocoDTO();				
		focoDto.setIdFoco(foco.getIdFoco().toString());
		focoDto.setNombre(foco.getNombre());
		focoDto.setDescripcion(foco.getDescripcion());
		focoDto.setPeriodo(foco.getPeriodo());
		List<AccionesMejorasDTO> acciones = new ArrayList<AccionesMejorasDTO>();
		foco.getAcciones().forEach(a -> {
			AccionesMejorasDTO am = new AccionesMejorasDTO();
			am.setIdAccionesMejoras(a.getIdAccionesMejoras().toString());
			am.setNombre(a.getNombre());
			List<MovimientosClavesDTO> movimientos = new ArrayList<MovimientosClavesDTO>();		
			a.getMovimientos().forEach(m -> {
				MovimientosClavesDTO mov = new MovimientosClavesDTO();
				mov.setIdMovimientosClaves(m.getIdMovimientosClaves().toString());
				mov.setIdAccionesMejoras(a.getIdAccionesMejoras().toString());
				mov.setNombre(m.getNombre());
				movimientos.add(mov);
			});		
			am.setMovimientos(movimientos);
			acciones.add(am);	
		});
		focoDto.setAcciones(acciones);
		return focoDto;
	}

	@Transactional(rollbackFor = Throwable.class)
	public void setFocus(FocoDTO dto) {
		try {
			Foco foco = new Foco();
			foco.setIdUsuarioModificacion(UserHelper.getUsuarioRegistrado().getIdUsuario());			
			foco.setIdFoco(dto.getIdFoco() != null && !dto.getIdFoco().isEmpty() ? Long.parseLong(dto.getIdFoco()) : 0);
			foco.setNombre(dto.getNombre());
			foco.setDescripcion(dto.getDescripcion());
			foco.setPeriodo(dto.getPeriodo());
			List<AccionesMejoras> acciones = new ArrayList<AccionesMejoras>();
			dto.getAcciones().forEach(a -> {
				AccionesMejoras am = new AccionesMejoras();
				am.setIdAccionesMejoras(a.getIdAccionesMejoras() != null && !a.getIdAccionesMejoras().isEmpty() ? Long.parseLong(a.getIdAccionesMejoras()) : 0);
				am.setNombre(a.getNombre());
				List<MovimientosClaves> movimientos = new ArrayList<MovimientosClaves>();		
				a.getMovimientos().forEach(m -> {
					MovimientosClaves mov = new MovimientosClaves();
					mov.setIdMovimientosClaves(m.getIdMovimientosClaves() != null && !m.getIdMovimientosClaves().isEmpty() ? Long.parseLong(m.getIdMovimientosClaves()) : 0);
					mov.setIdAccionesMejoras(m.getIdAccionesMejoras() != null && !m.getIdAccionesMejoras().isEmpty() ? Long.parseLong(m.getIdAccionesMejoras()) : 0);
					mov.setNombre(m.getNombre());
					movimientos.add(mov);
				});		
				am.setMovimientos(movimientos);
				acciones.add(am);	
			});
			foco.setAcciones(acciones);

			if (foco.getIdFoco() != 0) {				
				focoRepo.updateFocoById(foco);
			} else {
				focoRepo.insertFoco(foco);
			}
			setActions(foco.getIdFoco(), foco.getAcciones());

		} catch (MineducException e) {
			throw new MineducException("Error al insertar Foco", e);
		}
	}

	private void setActions(Long focoId, List<AccionesMejoras> acciones){

		Foco foco = focoRepo.getFocoById(focoId);
		acciones.forEach(a ->{
			if(!foco.getAcciones().isEmpty()){
				Optional<AccionesMejoras> acc = foco.getAcciones().stream().filter(x -> x.getNombre().equalsIgnoreCase(a.getNombre())).findFirst();
				if(acc.isPresent()){
					a.setIdAccionesMejoras(acc.get().getIdAccionesMejoras());
				}
			}
			a.setIdFoco(focoId);
			a.setIdUsuarioModificacion(UserHelper.getUsuarioRegistrado().getIdUsuario());
			if(a.getIdAccionesMejoras() != 0){
				accionesRepo.updateAccionMejora(a);
			} else {
				accionesRepo.insertAccionMejora(a);
			}
			setMovimientos(a.getIdAccionesMejoras(), a.getMovimientos());
		});
	}

	private void setMovimientos(long accionId, List<MovimientosClaves> movimientos){		
        AccionesMejoras acc = accionesRepo.getAccionMejoraById(accionId);		
		acc.getMovimientos().forEach(m ->{
			Optional<MovimientosClaves> mov = movimientos.stream().filter(x -> x.getNombre().equalsIgnoreCase(m.getNombre())).findFirst();
			if(mov.isPresent()){
				movimientos.remove(mov.get());
			} else{
				movRepo.deleteById(m.getIdMovimientosClaves());
			}
		});
        		
        for(MovimientosClaves m: movimientos) {	
			m.setIdAccionesMejoras(accionId);
			m.setIdUsuarioModificacion(UserHelper.getUsuarioRegistrado().getIdUsuario());	
			movRepo.insertKeyMov(m);			
		}
	}

	public void setKeysMov(MovimientosClavesDTO dto) {
		try {
			MovimientosClaves mov = (MovimientosClaves) Entity.convertToEntity(new MovimientosClaves(), dto);
			mov.setIdUsuarioModificacion(UserHelper.getUsuarioRegistrado().getIdUsuario());
			if (mov.getIdMovimientosClaves() != null) {
				mov.setUltimaModificacion(LocalDateTime.now());
				movRepo.updateKeyMovById(mov);
			} else {
				movRepo.insertKeyMov(mov);
			}
		} catch (MineducException e) {
			throw new MineducException("Error al insertar Movimientos Claves", e);
		}
	}

	public void setActionsImprovements(AccionesMejorasDTO dto) {
		try {
			AccionesMejoras ac = (AccionesMejoras) Entity.convertToEntity(new AccionesMejoras(), dto);
			ac.setIdUsuarioModificacion(UserHelper.getUsuarioRegistrado().getIdUsuario());
			if (ac.getIdAccionesMejoras() != null) {
				ac.setUltimaModificacion(LocalDateTime.now());
				accionesRepo.updateAccionMejora(ac);
			} else {
				accionesRepo.insertAccionMejora(ac);
			}
		} catch (MineducException e) {
			throw new MineducException("Error al insertar Acciones Mejoras", e);
		}
	}

	// TODO cambiar al Servicio correcto
	public List<SelectorDTO> getYears() {
		List<SelectorDTO> dtos = new ArrayList<SelectorDTO>();
		List<Periodo> periodos = periodoRepo.getAllPeriods();
		periodos.forEach(p -> {
			SelectorDTO dto = new SelectorDTO();
			dto.setValue(p.getAnio().toString());
			dto.setDisplayText(p.getAnio().toString());
			dtos.add(dto);
		});
		return dtos;
	}

	public void removeAction(String idAccionesMejoras){

		try {
			AccionesMejoras acc = this.accionesRepo.getAccionMejoraById(Long.parseLong(idAccionesMejoras));
			acc.getMovimientos().forEach(m ->{
				this.movRepo.deleteById(m.getIdMovimientosClaves());
			});
			accionesRepo.deleteAccionMejoraById(Long.parseLong(idAccionesMejoras));
		} catch (MineducException e) {
			throw new MineducException("Error al eliminar Acciones Mejoras", e);
		}
	}

	public void removeFoco(String focoId){
		try {

			Foco foco = this.focoRepo.getFocoById(Long.parseLong(focoId));
			foco.getAcciones().forEach(acc ->{
				acc.getMovimientos().forEach(m -> {
					this.movRepo.deleteById(m.getIdMovimientosClaves());
				});
				accionesRepo.deleteAccionMejoraById(acc.getIdAccionesMejoras());	
			});
			this.focoRepo.deleteById(Long.parseLong(focoId));
		} catch (MineducException e) {
			throw new MineducException("Error al eliminar Acciones Mejoras", e);
		}
	}
}
