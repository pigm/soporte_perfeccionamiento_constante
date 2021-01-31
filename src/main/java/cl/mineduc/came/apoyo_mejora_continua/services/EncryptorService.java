package cl.mineduc.came.apoyo_mejora_continua.services;

import javax.annotation.PostConstruct;

import org.jasypt.digest.StandardStringDigester;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EncryptorService implements InitializingBean{
	private static final Logger log = LoggerFactory.getLogger(EncryptorService.class);

	private StandardPBEStringEncryptor textEncryptor;
	private StandardStringDigester digester;
	
	@Value("${cl.mineduc.came.apoyo_mejora_continua.encryptor.algorithm}")
	private String algorithm;
	@Value("${cl.mineduc.came.apoyo_mejora_continua.encryptor.text.encrypt.output.type}")
	private String textOutput;
	@Value("${cl.mineduc.came.apoyo_mejora_continua.encryptor.text.encrypt.password}")
	private String password;
	@Value("${cl.mineduc.came.apoyo_mejora_continua.encryptor.iterations}")
	private Integer iterations;
	
	@PostConstruct
	private void encryptor() {
		/** sets the text encrypt **/
		this.textEncryptor = new StandardPBEStringEncryptor();
		this.textEncryptor.setPassword(this.password);
		this.textEncryptor.setStringOutputType(this.textOutput);
		log.debug("ENCRYPTOR SERVICE TEXT SETTINGS OK");
		/** sets the digester **/
		this.digester = new StandardStringDigester();
		this.digester.setAlgorithm(this.algorithm);
		this.digester.setIterations(this.iterations);
		log.debug("ENCRYPTOR SERVICE DIGESTER SETTINGS OK");
	}

	
	@Override
	public void afterPropertiesSet() throws Exception {
		log.info("BEAN ENCRYPTOR INICIALIZADO!");
	}
	
	public String encryptMessage(String message){
		return this.textEncryptor.encrypt(message);
	}
	
	public String decryptMessage(String message){
		return this.textEncryptor.decrypt(message);
	}	
	
	public String encryptPassword(String plainPassword){
		return this.digester.digest(plainPassword);
	}
	
	public boolean checkPassword(String plainPassword, String digestedPassword){
		return digester.matches(plainPassword, digestedPassword);
	}

}
