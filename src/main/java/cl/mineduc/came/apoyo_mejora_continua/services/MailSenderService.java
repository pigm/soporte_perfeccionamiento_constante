package cl.mineduc.came.apoyo_mejora_continua.services;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import cl.mineduc.framework2.utils.StringUtils;
import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

@Component("mailerApoyoMejoraContinua")
public class MailSenderService {
	private static final Logger log = LoggerFactory.getLogger(MailSenderService.class);

	@Autowired private JavaMailSender javaMailSender;
	@Autowired private Configuration configuration;
	
	/**
	 * Metodo que prepara el mail a enviar
	 * 
	 * @param mimeMessage
	 * @param mailTo
	 * @param nameTo
	 * @throws MessagingException
	 * @throws UnsupportedEncodingException
	 */
	public MimeMessage prepareMail(String mailTo, String nameTo, String mailContent)
			throws MessagingException, UnsupportedEncodingException {
		log.info("CREANDO MAIL DE NOTIFICACION...");
		MimeMessage mimeMessage = this.javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, StandardCharsets.UTF_8.name());
		helper.setTo(new InternetAddress(mailTo, StringUtils.removeAccents(nameTo)));
		helper.setReplyTo(new InternetAddress("noreply@mineduc.cl", "Notificación Mineduc"));
		helper.setFrom(new InternetAddress("noreply@mineduc.cl", "Notificación Mineduc"));
		helper.setSubject("Notificación del Ministerio de Educación");
		helper.setText(mailContent, true);

		mimeMessage = helper.getMimeMessage();
		log.info("MIME MULTIPART MESSAGE CREATED --> {}", helper.isMultipart());

		helper = null;

		return mimeMessage;
	}

	/** 
	 * @param setParameters
	 * @param template
	 * @return String
	 * @throws TemplateNotFoundException
	 * @throws MalformedTemplateNameException
	 * @throws ParseException
	 * @throws IOException
	 * @throws TemplateException
	 * @throws MessagingException
	 */
	public String parseMailTemplate(Map<String, Object> setParameters, String template) throws TemplateNotFoundException, MalformedTemplateNameException,
			ParseException, IOException, TemplateException, MessagingException {
		log.info("PREPARANDO TEMPLATE PARA MAIL DE NOTIFICACION...");

		Map<String, Object> notificacionMap = setParameters;
		

		Template mailTemplate = configuration.getTemplate(template);
		String parsedTemplate = FreeMarkerTemplateUtils.processTemplateIntoString(mailTemplate, notificacionMap);
		log.info("TEMPLATE LISTO!");

		mailTemplate = null;
		notificacionMap = null;
		return parsedTemplate;
	}
	
	/**
	 * wrapper de envio
	 * @param mail
	 */
	public void send(MimeMessage mail) {
		this.javaMailSender.send(mail);
	}
}