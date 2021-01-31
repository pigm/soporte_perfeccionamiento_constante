package cl.mineduc.came.apoyo_mejora_continua.dto.report.directory;

public class DirectoryRequestDTO {
	private String region;
    private String deprov;
    private String comuna;
    
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getDeprov() {
		return deprov;
	}
	public void setDeprov(String deprov) {
		this.deprov = deprov;
	}
	public String getComuna() {
		return comuna;
	}
	public void setComuna(String comuna) {
		this.comuna = comuna;
	}
}