package cl.mineduc.came.apoyo_mejora_continua.helpers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import com.google.gson.Gson;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import cl.mineduc.framework2.exceptions.MineducException;

@Component
public class Util {
	private static Logger logger = LogManager.getLogger(Util.class);
	
	public static Date obtenerFecha() {
		return Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
	}
	
	public static String formatDate(Date date) {
		LocalDate dl = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		return dl.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}
	
	/** 
	 * @param arr
	 * @param start
	 * @param end
	 * @return slice
	 */
	public static String[] getSliceOfArray(String[] arr, int start, int end) {

		// Get the slice of the Array
		String[] slice = new String[end - start];

		// Copy elements of arr to slice
		for (int i = 0; i < slice.length; i++) {
			slice[i] = arr[start + i];
		}

		// return the slice
		return slice;
	}
	
	/** 
	 * @param parentFolder
	 * @param subFolder
	 * @param fileToSave
	 */
	public static void guardarArchivoEnDirectorio(String parentFolder, String subFolder, FileToSave fileToSave) {
		MultipartFile file = fileToSave.getFile();
		String filename = fileToSave.getFileName();
		String uploadMainFolder = PropertiesValue.getUploadMainFolder();
		try {

			File saveFolder = new File(uploadMainFolder);
			if (!saveFolder.exists()) {
				saveFolder.mkdir();
			}

			File fParent = new File(uploadMainFolder + parentFolder);
			if (!fParent.exists()) {
				fParent.mkdir();
			}

			File fSub = new File(uploadMainFolder + parentFolder + File.separator + subFolder + File.separator);
			if (!fSub.exists()) {
				fSub.mkdir();
			}
			if (filename!=null && !filename.isEmpty()) {
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(
						uploadMainFolder + parentFolder + File.separator + subFolder + File.separator + filename)));
				stream.write(file.getBytes());
				stream.flush();
				stream.close();
			}
		} catch (IOException e) {
			String path = uploadMainFolder + parentFolder + File.separator + subFolder + File.separator;
			logger.error("Error al guardar el archivo en el dictorio: " + path + " el archivo: " + filename, e);
			throw new MineducException("Error al guardar el archivo " + filename, e);
		}

	}
	
	public static class FileToSave {
		private String fileName;
		private MultipartFile file;

		public FileToSave(MultipartFile file) {
			this.file = file;
		}

		public FileToSave() {
		}

		/**
		 * @return the fileName
		 */
		public String getFileName() {
			if (this.fileName == null && this.file != null) {
				if (this.file.getOriginalFilename()!=null && !this.file.getOriginalFilename().isEmpty()) {
					String[] fileNameA = this.file.getOriginalFilename().split("\\.");
					String ext = fileNameA.length > 1 ? "." + fileNameA[fileNameA.length - 1] : "";
					String[] nameArr = Util.getSliceOfArray(fileNameA, 0, fileNameA.length - 1);
					String name = String.join(".", nameArr);
					this.fileName = name + "_" + this.hashCode() + ext;
				} else {
					this.fileName = this.file.getOriginalFilename();
				}
			}
			return fileName;
		}

		/**
		 * @param fileName the fileName to set
		 */
		public void setFileName(String fileName) {
			this.fileName = fileName;
		}

		/**
		 * @return the file
		 */
		public MultipartFile getFile() {
			return file;
		}

		/**
		 * @param file the file to set
		 */
		public void setFile(MultipartFile file) {
			this.file = file;
		}

		public String getOriginalFilename() {
			return this.getFileName();
		}

		/**
		 * @param file
		 */

	}

	public static String objToJson(Object obj){

		String jsResp = "Sin Informacion";
		
		if(null != obj){
			Gson gs = new Gson();
			jsResp = gs.toJson(obj);
		}
		
		return jsResp;
	}


}
