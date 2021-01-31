package cl.mineduc.came.apoyo_mejora_continua.services;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.web.multipart.MultipartFile;

import cl.mineduc.came.apoyo_mejora_continua.dto.documents.DocumentsDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.documents.DocumentsListDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.documents.DocumentsRequestDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.documents.DocumentsViewDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.documents.SetDocumentRequestDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.integration.region.DeprovsResponse;
import cl.mineduc.came.apoyo_mejora_continua.dto.integration.region.RegionResponseDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.period.DTOEntity;
import cl.mineduc.came.apoyo_mejora_continua.dto.period.PeriodoDocumentosProvincialesDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.period.PeriodoDocumentosRegionalesDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.shared.SelectorDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.users.UserEnvDTO;
import cl.mineduc.came.apoyo_mejora_continua.helpers.CustomMultipartFile;
import cl.mineduc.came.apoyo_mejora_continua.helpers.UserHelper;
import cl.mineduc.came.apoyo_mejora_continua.helpers.Util;
import cl.mineduc.came.apoyo_mejora_continua.integration.RegionIntegration;
import cl.mineduc.came.apoyo_mejora_continua.modelo.DocumentosProvinciales;
import cl.mineduc.came.apoyo_mejora_continua.modelo.DocumentosRegionales;
import cl.mineduc.came.apoyo_mejora_continua.modelo.Periodo;
import cl.mineduc.came.apoyo_mejora_continua.modelo.PeriodoDocumentosProvinciales;
import cl.mineduc.came.apoyo_mejora_continua.modelo.PeriodoDocumentosRegionales;
import cl.mineduc.came.apoyo_mejora_continua.repo.DocumentosProvincialesRepo;
import cl.mineduc.came.apoyo_mejora_continua.repo.DocumentosRegionalesRepo;
import cl.mineduc.came.apoyo_mejora_continua.repo.PeriodoRepo;

@Service
public class DocumentsService {
	private static Logger logger = Logger.getLogger(DocumentsService.class);

	public static final String DOCUMENTOS = "DOCUMENTOS";

	public static final String TYPE_DOCUMENTS = "document";

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	RegionIntegration regionIntegration;

	@Autowired
	DocumentosProvincialesRepo documentosProvincialesRepo;

	@Autowired
	DocumentosRegionalesRepo documentosRegionalesRepo;

	@Autowired
	PeriodoRepo periodRepo;

	@Autowired
	private UsuarioService usuarioService;

	public DTOEntity convertToDto(Object obj, DTOEntity mapper) {
		return modelMapper.map(obj, mapper.getClass());
	}

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

	public Boolean isValidStartDateDG(Date startDate) {
		logger.debug("isValidStartDate startDate:" + startDate);
		int year = Calendar.getInstance().get(Calendar.YEAR);
		Periodo period = periodRepo.getPeriodByYear(year);

		UserEnvDTO userPaso = usuarioService.getUserEnv(UserHelper.getUsuarioRegistrado().getIdUsuario());

		PeriodoDocumentosRegionales docRegionals = period.getDocumentosRegionales().stream()
				.filter(d -> d.getEsCasoEspecial() == null || !d.getEsCasoEspecial()).iterator().next();

		Optional<PeriodoDocumentosRegionales> docRegCE = period.getDocumentosRegionales().stream()
				.filter(d -> (d.getEsCasoEspecial() != null && d.getEsCasoEspecial()))
				.filter(d -> (d.getIdRegion().equals(Long.parseLong(userPaso.getIdRegion()))))
				.filter(d -> (userPaso.getIdDeprov().isEmpty()
						|| d.getIdDeprov().equals(Long.parseLong(userPaso.getIdDeprov()))))
				.findAny();
		if (docRegCE.isPresent()) {
			return docRegCE.get().getFechaInicio().getTime() <= startDate.getTime()
					&& docRegCE.get().getFechaFin().getTime() >= startDate.getTime();
		} else {
			return docRegionals.getFechaInicio().getTime() <= startDate.getTime()
					&& docRegionals.getFechaFin().getTime() >= startDate.getTime();
		}
	}

	public PeriodoDocumentosRegionalesDTO getPeriodoDocumentosRegionales(Integer year){
		Periodo period = periodRepo.getPeriodByYear(year);
		UserEnvDTO userPaso = usuarioService.getUserEnv(UserHelper.getUsuarioRegistrado().getIdUsuario());

		PeriodoDocumentosRegionales docRegionals = period.getDocumentosRegionales().stream()
				.filter(d -> d.getEsCasoEspecial() == null || !d.getEsCasoEspecial()).iterator().next();

		Optional<PeriodoDocumentosRegionales> docRegCE = period.getDocumentosRegionales().stream()
				.filter(d -> (d.getEsCasoEspecial() != null && d.getEsCasoEspecial()))
				.filter(d -> (d.getIdRegion().equals(Long.parseLong(userPaso.getIdRegion()))))
				.filter(d -> (userPaso.getIdDeprov().isEmpty()
						|| d.getIdDeprov().equals(Long.parseLong(userPaso.getIdDeprov()))))
				.findAny();
		if (docRegCE.isPresent()) {
			return (PeriodoDocumentosRegionalesDTO)convertToDto(docRegCE.get(), new PeriodoDocumentosRegionalesDTO());
		} else{
			return (PeriodoDocumentosRegionalesDTO)convertToDto(docRegionals, new PeriodoDocumentosRegionalesDTO());
		}
	}

	public PeriodoDocumentosProvincialesDTO getPeriodoDocumentosProvinciales(Integer year) {
		Periodo period = periodRepo.getPeriodByYear(year);
		UserEnvDTO userPaso = usuarioService.getUserEnv(UserHelper.getUsuarioRegistrado().getIdUsuario());

		PeriodoDocumentosProvinciales docProvinciales = period.getDocumentosProvinciales().stream()
				.filter(d -> d.getEsCasoEspecial() == null || !d.getEsCasoEspecial()).iterator().next();

		Optional<PeriodoDocumentosProvinciales> docRegCE = period.getDocumentosProvinciales().stream()
				.filter(d -> (d.getEsCasoEspecial() != null && d.getEsCasoEspecial()))
				.filter(d -> (d.getIdRegion().equals(Long.parseLong(userPaso.getIdRegion()))))
				.filter(d -> (userPaso.getIdDeprov().isEmpty() || d.getIdDeprov().equals(Long.parseLong(userPaso.getIdDeprov()))))
				.findAny();
		if (docRegCE.isPresent()) {
			return (PeriodoDocumentosProvincialesDTO) convertToDto(docRegCE.get(), new PeriodoDocumentosProvincialesDTO());
		} else {
			return (PeriodoDocumentosProvincialesDTO) convertToDto(docProvinciales, new PeriodoDocumentosProvincialesDTO());
		}
	}

	public PeriodoDocumentosProvincialesDTO getPeriodoDocumentosProvinciales(Integer year, Integer idRegion, Integer idDeprov) {
		Periodo period = periodRepo.getPeriodByYear(year);
		
		PeriodoDocumentosProvinciales docProvinciales = period.getDocumentosProvinciales().stream()
				.filter(d -> d.getEsCasoEspecial() == null || !d.getEsCasoEspecial()).iterator().next();

		Optional<PeriodoDocumentosProvinciales> docRegCE = period.getDocumentosProvinciales().stream()
				.filter(d -> (d.getEsCasoEspecial() != null && d.getEsCasoEspecial()))
				.filter(d -> (d.getIdRegion().equals((long)idRegion)))
				.filter(d -> d.getIdDeprov().equals((long)idDeprov))
				.sorted(Comparator.comparingLong(PeriodoDocumentosProvinciales::getIdPeriodoDocumentosProvinciales).reversed())
				.findFirst();
		if (docRegCE.isPresent()) {
			return (PeriodoDocumentosProvincialesDTO) convertToDto(docRegCE.get(),
					new PeriodoDocumentosProvincialesDTO());
		} else {
			return (PeriodoDocumentosProvincialesDTO) convertToDto(docProvinciales,
					new PeriodoDocumentosProvincialesDTO());
		}
	}


	public List<DocumentsListDTO> getDocuments(DocumentsRequestDTO request) {
		List<DocumentsListDTO> result = new ArrayList<DocumentsListDTO>();
		DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
		
		Periodo period = periodRepo.getPeriodByYear(request.getAnio());
		List<DocumentosRegionales> docRegionales = documentosRegionalesRepo.getByPeriod(period.getIdPeriodo())
													.stream()
													.filter(x -> request.getRegionId() == null || x.getIdRegion().equals((long)request.getRegionId()))
													.filter(x -> request.getEstado() == null || (x.getTypeDocument().equalsIgnoreCase(TYPE_DOCUMENTS) && x.getHasDownloaded().equals(request.getEstado())))
													.collect(Collectors.toList());		
		
		List<DocumentosProvinciales> docProvinciales = documentosProvincialesRepo.getByPeriod(period.getIdPeriodo())
														.stream()
														.filter(x -> request.getRegionId() == null || x.getIdRegion().equals((long) request.getRegionId()))
														.filter(x -> request.getDeprovId() == null || x.getIdDeprov().equals((long) request.getDeprovId()))
														.filter(x -> request.getEstado() == null || (x.getTypeDocument().equalsIgnoreCase(TYPE_DOCUMENTS) && x.getHasDownloaded().equals(request.getEstado())))
														.collect(Collectors.toList());

		List<RegionResponseDTO> regiones = this.regionIntegration.getRegiones();
		// proceso los docs regionales
		if(!docRegionales.isEmpty()){
			Map<Long, List<DocumentosRegionales>> regionsGroup = docRegionales.stream().collect(Collectors.groupingBy(DocumentosRegionales::getIdRegion));
			regionsGroup.forEach((key, v) -> {
				DocumentsListDTO doc = new DocumentsListDTO();
				RegionResponseDTO reg = regiones.stream().filter(r -> key.equals((long) r.getIdRegion())).iterator().next();
				doc.setIdRegion(reg.getIdRegion());
				doc.setRegion(reg.getNombreRegion());
				doc.setTipo("Regional");
				DocumentosRegionales hasDownloaded = v.stream().filter(x -> x.getTypeDocument().equalsIgnoreCase(TYPE_DOCUMENTS)).iterator().next();
				doc.setEstado(hasDownloaded == null || !hasDownloaded.getHasDownloaded() ? "No Observado" : "Observado");
				List<DocumentsViewDTO> files = new ArrayList<DocumentsViewDTO>();
				v.forEach(x -> {
					DocumentsViewDTO docViewDoc = new DocumentsViewDTO();
					docViewDoc.setIdDocumento(x.getIdDocumentosRegionales().toString());
					docViewDoc.setTypeDocument(x.getTypeDocument());
					docViewDoc.setName(x.getFileName());
					docViewDoc.setPath(x.getFilePath());
					docViewDoc.setUploadDate(dateFormatter.format(x.getFechaRegistro()));
					files.add(docViewDoc);
				});
				doc.setDocumentos(files.stream().sorted(Comparator.comparing(DocumentsViewDTO::getIdDocumento)
						.reversed()).collect(Collectors.toList()));
				result.add(doc);
			});
		}
		
		// proceso los docs provinciales
		if(!docProvinciales.isEmpty()){
			Map<Long, List<DocumentosProvinciales>> provGroup = docProvinciales.stream()
					.collect(Collectors.groupingBy(DocumentosProvinciales::getIdRegion));
			provGroup.forEach((key, v) -> {
				Map<Long, List<DocumentosProvinciales>> devproGroup = v.stream()
						.collect(Collectors.groupingBy(DocumentosProvinciales::getIdDeprov));
				devproGroup.forEach((key2, v2) -> {
					DocumentsListDTO doc = new DocumentsListDTO();
					RegionResponseDTO reg = regiones.stream().filter(r -> key.equals((long) r.getIdRegion())).iterator()
							.next();
					doc.setIdRegion(reg.getIdRegion());		
					doc.setRegion(reg.getNombreRegion());
					DeprovsResponse devp = reg.getDeprovs().stream().filter(r -> key2.equals((long) r.getIdDeprov()))
							.iterator().next();
					doc.setDeprov(devp.getNombreDeprov());
					doc.setTipo("Provincial");

					Optional<DocumentosProvinciales> hasDownloaded = v.stream().filter(x -> x.getTypeDocument().equalsIgnoreCase(TYPE_DOCUMENTS)).findFirst();
					doc.setEstado(!hasDownloaded.isPresent() || !(hasDownloaded.get().getHasDownloaded().booleanValue()) ? "No Observado" : "Observado");

					List<DocumentsViewDTO> files = new ArrayList<DocumentsViewDTO>();
					v.forEach(x -> {
						DocumentsViewDTO docViewDoc = new DocumentsViewDTO();
						docViewDoc.setIdDocumento(x.getIdDocumentosProvinciales().toString());
						docViewDoc.setTypeDocument(x.getTypeDocument());
						docViewDoc.setName(x.getFileName());
						docViewDoc.setPath(x.getFilePath());
						docViewDoc.setUploadDate(dateFormatter.format(x.getFechaRegistro()));
						files.add(docViewDoc);
					});
					doc.setDocumentos(files.stream().sorted(Comparator.comparing(DocumentsViewDTO::getIdDocumento)
							.reversed()).collect(Collectors.toList()));
					result.add(doc);
				});
			});
		}		

		return result.stream().sorted(Comparator.comparing(DocumentsListDTO::getIdRegion)).collect(Collectors.toList());
	}

	public DocumentsDTO getDocumentosRegionales(Integer regionId) {
		DocumentsDTO result = new DocumentsDTO();
		DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
		int year = Calendar.getInstance().get(Calendar.YEAR);
		Periodo period = periodRepo.getPeriodByYear(year);
		List<DocumentosRegionales> docRegionales = documentosRegionalesRepo.getByPeriod(period.getIdPeriodo());
		List<DocumentosRegionales> regPaso = docRegionales.stream().filter(d -> d.getIdRegion().equals((long) regionId))
				.collect(Collectors.toList());
		regPaso.forEach(d -> {
			if (d.getTypeDocument().equalsIgnoreCase(TYPE_DOCUMENTS)) {
				DocumentsViewDTO docViewDoc = new DocumentsViewDTO();
				docViewDoc.setName(d.getFileName());
				docViewDoc.setPath(d.getFilePath());
				docViewDoc.setUploadDate(dateFormatter.format(d.getFechaRegistro()));
				result.setDocumento(docViewDoc);
			} else if (d.getTypeDocument().equalsIgnoreCase("anexo1")) {
				DocumentsViewDTO docViewA1 = new DocumentsViewDTO();
				docViewA1.setName(d.getFileName());
				docViewA1.setPath(d.getFilePath());
				docViewA1.setUploadDate(dateFormatter.format(d.getFechaRegistro()));
				result.setAnexo1(docViewA1);
			} else if (d.getTypeDocument().equalsIgnoreCase("anexo2")) {
				DocumentsViewDTO docViewA2 = new DocumentsViewDTO();
				docViewA2.setName(d.getFileName());
				docViewA2.setPath(d.getFilePath());
				docViewA2.setUploadDate(dateFormatter.format(d.getFechaRegistro()));
				result.setAnexo2(docViewA2);
			} else {
				// no carga documentos
			}
		});

		return result;
	}

	public DocumentsDTO getDocumentosProvinciales(Integer regionId, Integer deprovId) {
		DocumentsDTO result = new DocumentsDTO();
		DateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");
		int year = Calendar.getInstance().get(Calendar.YEAR);
		Periodo period = periodRepo.getPeriodByYear(year);
		result.setDocProvincial(this.getPeriodoDocumentosProvinciales(year, regionId, deprovId));
		List<DocumentosProvinciales> docProvinciales = documentosProvincialesRepo.getByPeriod(period.getIdPeriodo());
		List<DocumentosProvinciales> regPaso = docProvinciales.stream()
				.filter(d -> d.getIdRegion().equals((long) regionId) && d.getIdDeprov().equals((long) deprovId))
				.collect(Collectors.toList());
		regPaso.forEach(d -> {
			if (d.getTypeDocument().equalsIgnoreCase(TYPE_DOCUMENTS)) {
				DocumentsViewDTO docViewDoc = new DocumentsViewDTO();
				docViewDoc.setName(d.getFileName());
				docViewDoc.setPath(d.getFilePath());
				docViewDoc.setUploadDate(dateFormatter.format(d.getFechaRegistro()));
				result.setDocumento(docViewDoc);
			} else if (d.getTypeDocument().equalsIgnoreCase("anexo1")) {
				DocumentsViewDTO docViewA1 = new DocumentsViewDTO();
				docViewA1.setName(d.getFileName());
				docViewA1.setPath(d.getFilePath());
				docViewA1.setUploadDate(dateFormatter.format(d.getFechaRegistro()));
				result.setAnexo1(docViewA1);
			} else if (d.getTypeDocument().equalsIgnoreCase("anexo2")) {
				DocumentsViewDTO docViewA2 = new DocumentsViewDTO();
				docViewA2.setName(d.getFileName());
				docViewA2.setPath(d.getFilePath());
				docViewA2.setUploadDate(dateFormatter.format(d.getFechaRegistro()));
				result.setAnexo2(docViewA2);
			} else {
				// no carga documentos
			}
		});

		return result;
	}

	public boolean setDocumentRegional(SetDocumentRequestDTO request) {

		int year = Calendar.getInstance().get(Calendar.YEAR);
		Periodo periodo = periodRepo.getPeriodByYear(year);

		String ruta = DOCUMENTOS + File.separator + periodo.getIdPeriodo().toString() + File.separator;

		MultipartFile fileProv = new CustomMultipartFile(Base64Utils.decodeFromString(request.getDocument()),
				request.getDocumentName());

		Util.FileToSave documentoProvincial = new Util.FileToSave();
		documentoProvincial.setFile(fileProv);
		String pathFile = ruta + documentoProvincial.getOriginalFilename();
		Util.guardarArchivoEnDirectorio(DOCUMENTOS, periodo.getIdPeriodo().toString(), documentoProvincial);

		PeriodoDocumentosRegionales regi = periodo.getDocumentosRegionales().stream()
				.filter(d -> d.getEsCasoEspecial() == null || !d.getEsCasoEspecial()).iterator().next();

		DocumentosRegionales docPasoRegistra = new DocumentosRegionales();
		docPasoRegistra.setIdPeriodo(periodo.getIdPeriodo());
		docPasoRegistra.setFechaRegistro(new Date());
		docPasoRegistra.setIdPeriodoDocumentosRegionales(regi.getIdPeriodoDocumentosRegionales());
		docPasoRegistra.setIdUsuarioRegistro(UserHelper.getUsuarioRegistrado().getIdUsuario());
		docPasoRegistra.setFilePath(pathFile);
		docPasoRegistra.setIdRegion((long) request.getIdRegion());
		docPasoRegistra.setFileName(request.getDocumentName());
		docPasoRegistra.setTypeDocument(request.getDocumentType());
		docPasoRegistra.setHasDownloaded(false);

		documentosRegionalesRepo.insertDocumentosRegionales(docPasoRegistra);

		return false;
	}

	public boolean setDocumentProvincial(SetDocumentRequestDTO request) {

		int year = Calendar.getInstance().get(Calendar.YEAR);
		Periodo periodo = periodRepo.getPeriodByYear(year);

		String ruta = DOCUMENTOS + File.separator + periodo.getIdPeriodo().toString() + File.separator;

		MultipartFile fileProv = new CustomMultipartFile(Base64Utils.decodeFromString(request.getDocument()),
				request.getDocumentName());

		Util.FileToSave documentoProvincial = new Util.FileToSave();
		documentoProvincial.setFile(fileProv);
		String pathFile = ruta + documentoProvincial.getOriginalFilename();
		Util.guardarArchivoEnDirectorio(DOCUMENTOS, periodo.getIdPeriodo().toString(), documentoProvincial);

		PeriodoDocumentosProvinciales docProvincial = periodo.getDocumentosProvinciales().stream()
				.filter(d -> d.getEsCasoEspecial() == null || !d.getEsCasoEspecial()).iterator().next();

		DocumentosProvinciales docPasoRegistra = new DocumentosProvinciales();
		docPasoRegistra.setIdPeriodo(periodo.getIdPeriodo());
		docPasoRegistra.setFechaRegistro(new Date());
		docPasoRegistra.setIdDocumentosProvinciales(docProvincial.getIdPeriodoDocumentosProvinciales());
		docPasoRegistra.setIdUsuarioRegistro(UserHelper.getUsuarioRegistrado().getIdUsuario());
		docPasoRegistra.setFilePath(pathFile);
		docPasoRegistra.setIdRegion((long) request.getIdRegion());
		docPasoRegistra.setIdDeprov((long) request.getIdDeprov());
		docPasoRegistra.setFileName(request.getDocumentName());
		docPasoRegistra.setTypeDocument(request.getDocumentType());
		docPasoRegistra.setHasDownloaded(false);

		documentosProvincialesRepo.insertDocumentosProvinciales(docPasoRegistra);

		return false;
	}

	public void setReadDocument(String documentoId, String tipo) {
		if (tipo.equalsIgnoreCase("Regional")) {
			DocumentosRegionales docRegInserta = documentosRegionalesRepo.getById(Long.parseLong(documentoId));
			if (docRegInserta != null) {
				docRegInserta.setHasDownloaded(true);
				documentosRegionalesRepo.setHasDownloaded(docRegInserta);
			}
		} else if (tipo.equalsIgnoreCase("Provincial")) {
			DocumentosProvinciales docDepInserta = documentosProvincialesRepo.getById(Long.parseLong(documentoId));
			if (docDepInserta != null) {
				docDepInserta.setHasDownloaded(true);
				documentosProvincialesRepo.setHasDownloaded(docDepInserta);
			}
		} else {
			// no hace nada
		}

	}

}
