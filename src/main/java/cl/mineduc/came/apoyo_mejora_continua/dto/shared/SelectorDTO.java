package cl.mineduc.came.apoyo_mejora_continua.dto.shared;

import java.io.Serializable;

public class SelectorDTO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -2175424922117572875L;
    
    private String value;
    private String displayText;
    private String id;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDisplayText() {
        return this.displayText;
    }

    public void setDisplayText(String displayText) {
        this.displayText = displayText;
    }


    
    
    public static SelectorDTO of(String value, String displayText) {
    	return new SelectorDTO(value, displayText);
    }

    public SelectorDTO(){    
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "SelectorDTO [displayText=" + displayText + ", id=" + id + ", value=" + value + "]";
    }

    public SelectorDTO(String value, String displayText, String id) {
        this.value = value;
        this.displayText = displayText;
        this.id = id;
    }

    public SelectorDTO(String value, String displayText) {
        this.value = value;
        this.displayText = displayText;
    }

    

    
}