package cl.mineduc.came.apoyo_mejora_continua.dto.profile;

import java.util.List;

public class MenuDTO {
    private String idModule;
    private String name;
    private boolean access;    
    private List<SubMenuDTO> subMenu;

    public String getIdModule() {
        return idModule;
    }

    public void setIdModule(String idModule) {
        this.idModule = idModule;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAccess() {
        return access;
    }

    public void setAccess(boolean access) {
        this.access = access;
    }

    public List<SubMenuDTO> getSubMenu() {
        return subMenu;
    }

    public void setSubMenu(List<SubMenuDTO> subMenu) {
        this.subMenu = subMenu;
    }
}

