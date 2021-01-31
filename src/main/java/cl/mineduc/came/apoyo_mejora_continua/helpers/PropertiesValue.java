package cl.mineduc.came.apoyo_mejora_continua.helpers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PropertiesValue {

	private static String uploadMainFolder;

	
	/** 
	 * @param folder
	 */
	@Value("${app.uploadMainFolder}")
	public void setUploadMainFolder(String folder) {
		PropertiesValue.uploadMainFolder = folder;
	}

	
	/** 
	 * @return String
	 */
	public static String getUploadMainFolder() {
		return uploadMainFolder;
	}
	
	
}
