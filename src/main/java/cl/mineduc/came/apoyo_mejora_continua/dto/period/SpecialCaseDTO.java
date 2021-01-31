package cl.mineduc.came.apoyo_mejora_continua.dto.period;

import java.util.Date;
import java.util.List;

public class SpecialCaseDTO {
    
    private Integer periodo;
    private Integer moduleId;
    private Long regionId;    
    private Date startDate;
    private Date endDate;
    private String observation;
    private String template;
    private String templateFileName;

    private List<Long> deprovId;

    public Integer getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Integer periodo) {
        this.periodo = periodo;
    }

    public Integer getModuleId() {
        return moduleId;
    }

    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }

    public Long getRegionId() {
        return regionId;
    }

    public void setRegionId(Long regionId) {
        this.regionId = regionId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getTemplateFileName() {
        return templateFileName;
    }

    public void setTemplateFileName(String templateFileName) {
        this.templateFileName = templateFileName;
    }

    public List<Long> getDeprovId() {
        return deprovId;
    }

    public void setDeprovId(List<Long> deprovId) {
        this.deprovId = deprovId;
    }
    

}
