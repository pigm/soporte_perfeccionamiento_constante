package cl.mineduc.came.apoyo_mejora_continua.dto.survey;

public class AnswerDetailDTO {
    private Integer number;
    private String question;
    private Integer average;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Integer getAverage() {
        return average;
    }

    public void setAverage(Integer average) {
        this.average = average;
    }    
}
