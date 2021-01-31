package cl.mineduc.came.apoyo_mejora_continua.dto.profile;

import java.util.List;

public class SubMenuDTO {
    private String idModule;
    private String idSubModule;
    private String name;
    private String idMenuAcceso;    
    private String url;
    private boolean readOnly;
    
    private List<SubMenuDTO> subMenu;

    public String getIdModule() {
        return idModule;
    }

    public void setIdModule(String idModule) {
        this.idModule = idModule;
    }

    public String getIdSubModule() {
        return idSubModule;
    }

    public void setIdSubModule(String idSubModule) {
        this.idSubModule = idSubModule;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SubMenuDTO> getSubMenu() {
        return subMenu;
    }

    public void setSubMenu(List<SubMenuDTO> subMenu) {
        this.subMenu = subMenu;
    }

    public String getIdMenuAcceso() {
        return idMenuAcceso;
    }

    public void setIdMenuAcceso(String idMenuAcceso) {
        this.idMenuAcceso = idMenuAcceso;
    }

    public SubMenuDTO(String idModule, String idSubModule, String idMenuAcceso, String name, String url) {
        this.idModule = idModule;
        this.idSubModule = idSubModule;
        this.name = name;
        this.idMenuAcceso = idMenuAcceso;
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isReadOnly() {
        return readOnly;
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }
}

