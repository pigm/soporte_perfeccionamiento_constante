package cl.mineduc.came.apoyo_mejora_continua.dto.integration.region;

import java.util.List;

import org.springframework.data.redis.core.RedisHash;

@RedisHash(value = "region")
public class RegionResponseDTO {
    
    private Integer idRegion;
    private String nombreRegion; 
    private List<DeprovsResponse> deprovs;

    public Integer getIdRegion() {
        return idRegion;
    }

    public void setIdRegion(Integer idRegion) {
        this.idRegion = idRegion;
    }

    public String getNombreRegion() {
        return nombreRegion;
    }

    public void setNombreRegion(String nombreRegion) {
        this.nombreRegion = nombreRegion;
    }

    public List<DeprovsResponse> getDeprovs() {
        return deprovs;
    }

    public void setDeprovs(List<DeprovsResponse> deprovs) {
        this.deprovs = deprovs;
    }
}