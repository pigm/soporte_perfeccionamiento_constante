package cl.mineduc.came.apoyo_mejora_continua.helpers;

import java.util.Hashtable;

import javax.naming.AuthenticationException;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class LdapHelper {
	private static Logger logger = LogManager.getLogger(LdapHelper.class);
	@Autowired private Environment env;
	
	public Boolean validaUsuarioLDAP(String strUsuario, String strPassword) {
		Hashtable<String, String> environment = new Hashtable<String, String>();
		Boolean conectado = false;
		environment.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		environment.put(Context.PROVIDER_URL, "LDAP://" + env.getProperty("cl.mineduc.ldap.domain.controller"));
		environment.put(Context.SECURITY_AUTHENTICATION, "simple");
		environment.put(Context.SECURITY_PRINCIPAL, env.getProperty("cl.mineduc.ldap.domain") + "\\" + strUsuario);
		environment.put(Context.SECURITY_CREDENTIALS, strPassword);
		try {
			DirContext context = new InitialDirContext(environment);
			conectado = true;
			context.close();
		} catch (AuthenticationException e) {
			logger.info("Ha ocurrido una excepcion al autorizar al usuario en ldap ["+strUsuario+"] "+e.getMessage(), e);
			conectado = false;
		} catch (NamingException e) {
			logger.error("Ha ocurrido una excepcion al Iniciar el contexto LDAP "+e.getMessage(), e);
			conectado = false;
		}
		return conectado;
	}
}
