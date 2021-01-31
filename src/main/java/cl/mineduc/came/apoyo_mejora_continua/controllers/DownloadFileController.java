package cl.mineduc.came.apoyo_mejora_continua.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/downloadController")
public class DownloadFileController {
	
	private static Logger logger = LogManager.getLogger(DownloadFileController.class);

	@Autowired
	ServletContext context;

	
	/** 
	 * @param filePath
	 * @param request
	 * @param response
	 */
	@GetMapping(value = "/getFile", produces = "application/json")
	public void getFile(@RequestParam(required = false) String filePath, HttpServletRequest request,
			HttpServletResponse response) {

		Path file = Paths.get(filePath);
		
		if (Files.exists(file)) {

			response.setContentType(new MimetypesFileTypeMap().getContentType(filePath));
			response.addHeader("Content-Disposition", "attachment; filename=" + file.getFileName());
			try {
				Files.copy(file, response.getOutputStream());
				response.getOutputStream().flush();
			} catch (IOException e) {
				logger.error("Error ", e);
			}
		} else {
			logger.error("El archivo no ha sido encontrado!");
		}
	}

}
