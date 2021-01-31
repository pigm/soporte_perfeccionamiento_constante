package cl.mineduc.came.apoyo_mejora_continua.services;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.assertj.core.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.web.multipart.MultipartFile;

import cl.mineduc.came.apoyo_mejora_continua.dto.biblioteca.BibliotecaDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.biblioteca.BibliotecaListDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.biblioteca.BibliotecaRequestDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.biblioteca.SetBibliotecaRequestDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.biblioteca.UpdateBibliotecaRequestDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.documents.DocumentsDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.documents.DocumentsListDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.documents.DocumentsRequestDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.documents.DocumentsViewDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.documents.SetDocumentRequestDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.integration.region.DeprovsResponse;
import cl.mineduc.came.apoyo_mejora_continua.dto.integration.region.RegionResponseDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.shared.SelectorDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.users.ProfileSelectorDTO;
import cl.mineduc.came.apoyo_mejora_continua.helpers.CustomMultipartFile;
import cl.mineduc.came.apoyo_mejora_continua.helpers.StringHelper;
import cl.mineduc.came.apoyo_mejora_continua.helpers.UserHelper;
import cl.mineduc.came.apoyo_mejora_continua.helpers.Util;
import cl.mineduc.came.apoyo_mejora_continua.integration.RegionIntegration;
import cl.mineduc.came.apoyo_mejora_continua.modelo.Biblioteca;
import cl.mineduc.came.apoyo_mejora_continua.modelo.DocumentosProvinciales;
import cl.mineduc.came.apoyo_mejora_continua.modelo.DocumentosRegionales;
import cl.mineduc.came.apoyo_mejora_continua.modelo.ElementoLista;
import cl.mineduc.came.apoyo_mejora_continua.modelo.Foco;
import cl.mineduc.came.apoyo_mejora_continua.modelo.Perfil;
import cl.mineduc.came.apoyo_mejora_continua.modelo.PerfilBiblioteca;
import cl.mineduc.came.apoyo_mejora_continua.modelo.Periodo;
import cl.mineduc.came.apoyo_mejora_continua.modelo.PeriodoDocumentosProvinciales;
import cl.mineduc.came.apoyo_mejora_continua.modelo.PeriodoDocumentosRegionales;
import cl.mineduc.came.apoyo_mejora_continua.repo.BibliotecaRepo;
import cl.mineduc.came.apoyo_mejora_continua.repo.DocumentosProvincialesRepo;
import cl.mineduc.came.apoyo_mejora_continua.repo.DocumentosRegionalesRepo;
import cl.mineduc.came.apoyo_mejora_continua.repo.ElementoListaRepo;
import cl.mineduc.came.apoyo_mejora_continua.repo.PerfilBibliotecaRepo;
import cl.mineduc.came.apoyo_mejora_continua.repo.PerfilRepo;
import cl.mineduc.came.apoyo_mejora_continua.repo.PeriodoRepo;
import cl.mineduc.framework2.exceptions.MineducException;

import org.apache.commons.lang3.StringUtils;

@Service
public class BibliotecaService {
	private static Logger logger = Logger.getLogger(BibliotecaService.class);

	public static final String DOCUMENTOS = "BIBLIOTECA";

	public static final String TYPE_DOCUMENTS = "document";

	private static final Long TYPE_CATEGORY = 2420130603857871959l;

	@Autowired
	RegionIntegration regionIntegration;

	@Autowired
	BibliotecaRepo bibliotecaRepo;

	@Autowired
	PerfilBibliotecaRepo perfilBibliotecaRepo;

	@Autowired
	PeriodoRepo periodRepo;

	@Autowired
	PerfilRepo perfilRepo;

	@Autowired
	ElementoListaRepo elementRepo;

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

	public List<SelectorDTO> getCategoria() {
		List<SelectorDTO> result = new ArrayList<SelectorDTO>();
		List<ElementoLista> elements = elementRepo.getElementsByIdLista(TYPE_CATEGORY);
		elements.forEach(e -> result.add(SelectorDTO.of(e.getIdElementoLista().toString(), e.getNombre())));
		return result;
	}

	public void getDeleteDocuments(String idbiblioteca) {
		try {
			this.deletePerfilesBiblioteca(idbiblioteca);
			this.bibliotecaRepo.deleteById(Long.parseLong(idbiblioteca));
		} catch (MineducException e) {
			throw new MineducException("Error al eliminar biblioteca", e);
		}
	}

	public void deletePerfilesBiblioteca(String idbiblioteca) {
		int year = Calendar.getInstance().get(Calendar.YEAR);
		Periodo period = periodRepo.getPeriodByYear(year);
		List<PerfilBiblioteca> docPerfil = perfilBibliotecaRepo.getByIdPeriodo(period.getIdPeriodo()).stream()
				.collect(Collectors.toList());
		for (PerfilBiblioteca item : docPerfil) {
			if (item.getIdBiblioteca() == Long.parseLong(idbiblioteca)) {
				perfilBibliotecaRepo.deletePerfilBibliotecaById(item.getIdPerfilBiblioteca());
			}
		}
	}

	public UpdateBibliotecaRequestDTO getUpdateBiblioteca(String idBiblioteca, Integer idRegion, Integer idDeprov) {

		int year = Calendar.getInstance().get(Calendar.YEAR);
		Periodo periodo = periodRepo.getPeriodByYear(year);

		Biblioteca biblioteca = bibliotecaRepo.getById(Long.parseLong(idBiblioteca));
		List<PerfilBiblioteca> perfilBiblioteca = perfilBibliotecaRepo.getByIdPeriodo(periodo.getIdPeriodo()).stream()
				.filter(pf -> pf.getIdBiblioteca().equals(biblioteca.getIdBiblioteca())
						&& pf.getIdRegion().equals(idRegion) && pf.getIdDeprov().equals(idDeprov))
				.collect(Collectors.toList());
		List<String> perfiles = new ArrayList<String>();
		UpdateBibliotecaRequestDTO docPaso = new UpdateBibliotecaRequestDTO();
		for (PerfilBiblioteca item : perfilBiblioteca) {
			if (item.getIdBiblioteca().equals(biblioteca.getIdBiblioteca())) {
				perfiles.add(item.getIdPerfil().toString());
			}
		}

		docPaso.setIdRegion(idRegion);
		docPaso.setIdDeprov(idDeprov);
		docPaso.setCategoria(biblioteca.getIdCategoria().toString());
		docPaso.setIdBiblioteca(biblioteca.getIdBiblioteca().toString());
		docPaso.setNombre(biblioteca.getNombreDocumento());
		docPaso.setPerfil(perfiles);
		docPaso.setDocument(biblioteca.getTemplatePath());

		return docPaso;
	}

	public boolean updateBiblioteca(UpdateBibliotecaRequestDTO request) {
		int year = Calendar.getInstance().get(Calendar.YEAR);
		Periodo periodo = periodRepo.getPeriodByYear(year);
		List<ElementoLista> elements = elementRepo.getElementsByIdLista(TYPE_CATEGORY);
		ElementoLista categoria = elements.stream()
				.filter(p -> p.getIdElementoLista().equals(Long.parseLong(request.getCategoria()))).iterator().next();

		Biblioteca biblioteca = new Biblioteca();
		biblioteca.setIdBiblioteca(Long.parseLong(request.getIdBiblioteca()));
		biblioteca.setNombreDocumento(request.getNombre());
		biblioteca.setIdCategoria(categoria.getIdElementoLista());

		bibliotecaRepo.updateBiblioteca(biblioteca);
		this.deletePerfilesBiblioteca(request.getIdBiblioteca());

		for (String itemPerfil : request.getPerfil()) {
			List<Perfil> profilesPaso = this.perfilRepo.obtenerPerfiles();
			PerfilBiblioteca pBiblioteca = new PerfilBiblioteca();
			Perfil perfil = profilesPaso.stream().filter(p -> p.getIdPerfil().toString().equals(itemPerfil)).iterator()
					.next();

			pBiblioteca.setIdBiblioteca(Long.parseLong(request.getIdBiblioteca()));
			pBiblioteca.setIdPerfil(perfil.getIdPerfil());
			pBiblioteca.setFechaRegistro(new Date());
			pBiblioteca.setIdDeprov(request.getIdDeprov());
			pBiblioteca.setIdPeriodo(periodo.getIdPeriodo());
			pBiblioteca.setIdRegion(request.getIdRegion());
			pBiblioteca.setIdUsuarioRegistro(biblioteca.getIdUsuarioRegistro());
			perfilBibliotecaRepo.insertPerfilBiblioteca(pBiblioteca);
		}

		return false;
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

	public List<BibliotecaListDTO> getDocuments(BibliotecaRequestDTO request) {
		List<BibliotecaListDTO> result = new ArrayList<BibliotecaListDTO>();

		Periodo period = periodRepo.getPeriodByYear(request.getAnio());
		List<PerfilBiblioteca> docPerfil = perfilBibliotecaRepo.getByIdPeriodo(period.getIdPeriodo()).stream()
				.filter(x -> request.getRegionId() == null || request.getRegionId().isEmpty()
						|| x.getIdRegion() == Integer.parseInt(request.getRegionId()))
				.filter(x -> request.getDeprov() == null || request.getDeprov().isEmpty()
						|| x.getIdDeprov().intValue() == Integer.parseInt(request.getDeprov()))
				.filter(x -> (request.getPerfilId() == null || request.getPerfilId().isEmpty())
						|| x.getIdPerfil() == Long.parseLong(request.getPerfilId()))
				.collect(Collectors.toList());

		List<Perfil> profilesPaso = this.perfilRepo.obtenerPerfiles();
		List<ElementoLista> elements = elementRepo.getElementsByIdLista(TYPE_CATEGORY);
		DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
		List<RegionResponseDTO> regionesPaso = regionIntegration.getRegiones().stream()
				.filter(r -> request.getRegionId() == null || request.getRegionId().isEmpty()
						|| r.getIdRegion().equals(Integer.parseInt(request.getRegionId())))
				.collect(Collectors.toList());

		List<Long> bibliotecasKeys = docPerfil.stream().map(pb -> pb.getIdBiblioteca()).distinct().collect(Collectors.toList());
		bibliotecasKeys.forEach(b -> {
			Biblioteca biblioteca = bibliotecaRepo.getById(b);
			if ((request.getCategoriaId() == null || request.getCategoriaId().isEmpty()
					|| biblioteca.getIdCategoria().equals(Long.parseLong(request.getCategoriaId())))
					&& request.getNombreDocumento() == null || request.getNombreDocumento().isEmpty()
					|| biblioteca.getNombreDocumento().toLowerCase()
							.contains(request.getNombreDocumento().toLowerCase())) {

				regionesPaso.forEach(r -> {
					r.getDeprovs().stream().filter(d -> request.getDeprov() == null || request.getDeprov().isEmpty()
							|| d.getIdDeprov().equals(Integer.parseInt(request.getDeprov()))).forEach(d -> {
								List<PerfilBiblioteca> perfBibl = docPerfil.stream().filter(dp -> dp.getIdBiblioteca().equals(b) && 
																	  dp.getIdRegion().equals(r.getIdRegion()) &&
																	  dp.getIdDeprov().equals(d.getIdDeprov())).collect(Collectors.toList());								
								if (!perfBibl.isEmpty()) {
									BibliotecaListDTO doc = new BibliotecaListDTO();
									doc.setIdBiblioteca(biblioteca.getIdBiblioteca().toString());
									Optional<ElementoLista> categoria = elements.stream()
											.filter(p -> p.getIdElementoLista().equals(biblioteca.getIdCategoria()))
											.findFirst();
									if (categoria.isPresent()) {
										doc.setCategoria(categoria.get().getNombre());
									}

									doc.setNombreDocumento(biblioteca.getNombreDocumento());
									doc.setFechaPublicacion(dateFormatter.format(biblioteca.getFechaRegistro()));
									doc.setTemplatePath(biblioteca.getTemplatePath());

									List<String> perfiles = new ArrayList<String>();
									for (PerfilBiblioteca item : perfBibl) {
										if (item.getIdBiblioteca().equals(biblioteca.getIdBiblioteca())) {
											Perfil pp = profilesPaso.stream()
													.filter(p -> p.getIdPerfil().equals(item.getIdPerfil())).iterator()
													.next();
											perfiles.add(pp.getNombre());
										}
									}
									doc.setPerfil(StringUtils.join(perfiles, ","));								
									doc.setIdRegion(r.getIdRegion());
									doc.setRegion(r.getNombreRegion());
									doc.setIdDeprov(d.getIdDeprov());
									doc.setDeprov(d.getNombreDeprov());
									
									result.add(doc);

								}
							});
				});
			}
		});		

		return result;
	}

	public void setDocument(SetBibliotecaRequestDTO request) {
		int year = Calendar.getInstance().get(Calendar.YEAR);
		Periodo periodo = periodRepo.getPeriodByYear(year);
		List<ElementoLista> elements = elementRepo.getElementsByIdLista(TYPE_CATEGORY);
		String ruta = DOCUMENTOS + File.separator + periodo.getIdPeriodo().toString() + File.separator;

		MultipartFile fileProv = new CustomMultipartFile(Base64Utils.decodeFromString(request.getDocument()),
				request.getDocumentName());

		Util.FileToSave documento = new Util.FileToSave();
		documento.setFile(fileProv);
		String pathFile = ruta + documento.getOriginalFilename();
		Util.guardarArchivoEnDirectorio(DOCUMENTOS, periodo.getIdPeriodo().toString(), documento);

		ElementoLista categoria = elements.stream()
				.filter(p -> p.getIdElementoLista().equals(Long.parseLong(request.getCategoria()))).iterator().next();

		Biblioteca docPasoRegistra = new Biblioteca();
		docPasoRegistra.setNombreDocumento(request.getNombre());
		docPasoRegistra.setFechaRegistro(new Date());
		docPasoRegistra.setIdCategoria(categoria.getIdElementoLista());
		docPasoRegistra.setIdUsuarioRegistro(UserHelper.getUsuarioRegistrado().getIdUsuario());
		docPasoRegistra.setTemplatePath(pathFile);

		bibliotecaRepo.insertBiblioteca(docPasoRegistra);

		List<Perfil> profilesPaso = this.perfilRepo.obtenerPerfiles();

		List<RegionResponseDTO> regionesPaso = regionIntegration.getRegiones().stream()
				.filter(r -> request.getIdRegion() == null || r.getIdRegion().equals(request.getIdRegion()))
				.collect(Collectors.toList());
		regionesPaso.forEach(r -> {
			r.getDeprovs().stream()
					.filter(d -> request.getIdDeprov() == null || d.getIdDeprov().equals(request.getIdDeprov()))
					.forEach(d -> {
						for (String itemPerfil : request.getPerfil()) {
							PerfilBiblioteca pBiblioteca = new PerfilBiblioteca();
							Perfil ppaso = profilesPaso.stream()
									.filter(p -> p.getIdPerfil().toString().equals(itemPerfil)).iterator().next();

							pBiblioteca.setIdBiblioteca(docPasoRegistra.getIdBiblioteca());
							pBiblioteca.setIdPerfil(ppaso.getIdPerfil());
							pBiblioteca.setFechaRegistro(new Date());

							pBiblioteca.setIdRegion(r.getIdRegion());
							pBiblioteca.setIdDeprov(d.getIdDeprov());

							pBiblioteca.setIdPeriodo(periodo.getIdPeriodo());
							pBiblioteca.setIdUsuarioRegistro(docPasoRegistra.getIdUsuarioRegistro());

							perfilBibliotecaRepo.insertPerfilBiblioteca(pBiblioteca);
						}
					});
		});
	}

}
