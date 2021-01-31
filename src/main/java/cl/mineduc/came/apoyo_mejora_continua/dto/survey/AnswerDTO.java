package cl.mineduc.came.apoyo_mejora_continua.dto.survey;

public class AnswerDTO {
    private String idQuestion;
    private Integer answer;

	public String getIdQuestion() {
		return idQuestion;
	}
	public void setIdQuestion(String idQuestion) {
		this.idQuestion = idQuestion;
	}
	public Integer getAnswer() {
		return answer;
	}
	public void setAnswer(Integer answer) {
		this.answer = answer;
	}    
}

