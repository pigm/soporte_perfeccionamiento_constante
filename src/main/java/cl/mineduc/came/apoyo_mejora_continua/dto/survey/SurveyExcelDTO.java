package cl.mineduc.came.apoyo_mejora_continua.dto.survey;

import java.util.List;

public class SurveyExcelDTO {
    private List<String> header;
    private List<SurveyAnswerExcelDTO> items;

    public List<String> getHeader() {
        return header;
    }

    public void setHeader(List<String> header) {
        this.header = header;
    }

    public List<SurveyAnswerExcelDTO> getItems() {
        return items;
    }

    public void setItems(List<SurveyAnswerExcelDTO> items) {
        this.items = items;
    }

    
}
