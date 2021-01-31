package cl.mineduc.came.apoyo_mejora_continua.dto.survey;

import java.util.List;

public class SuveyAnswerDTO {
    private String idSurvey;
    private String observation;
    private List<AnswerDTO> answers;

    public String getIdSurvey() {
        return idSurvey;
    }

    public void setIdSurvey(String idSurvey) {
        this.idSurvey = idSurvey;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public List<AnswerDTO> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerDTO> answers) {
        this.answers = answers;
    }    
}
