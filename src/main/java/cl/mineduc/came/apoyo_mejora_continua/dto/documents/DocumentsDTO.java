package cl.mineduc.came.apoyo_mejora_continua.dto.documents;

import cl.mineduc.came.apoyo_mejora_continua.dto.period.PeriodoDocumentosProvincialesDTO;
import cl.mineduc.came.apoyo_mejora_continua.dto.period.PeriodoDocumentosRegionalesDTO;

public class DocumentsDTO {
    private String type;
    private DocumentsViewDTO documento;
    private DocumentsViewDTO anexo1;
    private DocumentsViewDTO anexo2;

    PeriodoDocumentosRegionalesDTO docRegional;
    PeriodoDocumentosProvincialesDTO docProvincial;
    
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public DocumentsViewDTO getDocumento() {
        return documento;
    }

    public void setDocumento(DocumentsViewDTO documento) {
        this.documento = documento;
    }

    public DocumentsViewDTO getAnexo1() {
        return anexo1;
    }

    public void setAnexo1(DocumentsViewDTO anexo1) {
        this.anexo1 = anexo1;
    }

    public DocumentsViewDTO getAnexo2() {
        return anexo2;
    }

    public void setAnexo2(DocumentsViewDTO anexo2) {
        this.anexo2 = anexo2;
    }

	public PeriodoDocumentosRegionalesDTO getDocRegional() {
		return docRegional;
	}

	public void setDocRegional(PeriodoDocumentosRegionalesDTO docRegional) {
		this.docRegional = docRegional;
	}

	public PeriodoDocumentosProvincialesDTO getDocProvincial() {
		return docProvincial;
	}

	public void setDocProvincial(PeriodoDocumentosProvincialesDTO docProvincial) {
		this.docProvincial = docProvincial;
	}
}
