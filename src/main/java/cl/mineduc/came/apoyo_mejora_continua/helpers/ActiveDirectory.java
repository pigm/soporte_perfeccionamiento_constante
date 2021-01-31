package cl.mineduc.came.apoyo_mejora_continua.helpers;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

import org.apache.log4j.Logger;

public class ActiveDirectory {
	
	private static Logger logger = Logger.getLogger(ActiveDirectory.class);

	// required private variables
	private Properties properties;
	private DirContext dirContext;
	private SearchControls searchCtls;
	private String[] returnAttributes = { "sAMAccountName", "givenName", "cn", "mail", "sn", "telephonenumber",
			"employeeid", "description" };
	private String domainBase;
	private String baseFilter = "(&((&(objectCategory=Person)(objectClass=User)))";

	/**
	 * constructor with parameter for initializing a LDAP context
	 * 
	 * @param username a {@link java.lang.String} object - username to establish
	 * a LDAP connection
	 * @param password a {@link java.lang.String} object - password to establish
	 * a LDAP connection
	 * @param domainController a {@link java.lang.String} object - domain
	 * controller name for LDAP connection
	 */
	public ActiveDirectory(String username, String password, String domainController, String domain) {
		properties = new Properties();

		properties.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		properties.put(Context.PROVIDER_URL, "LDAP://" + domainController);
		properties.put(Context.SECURITY_PRINCIPAL, domain + "\\" + username);
		properties.put(Context.SECURITY_CREDENTIALS, password);
		properties.put(Context.REFERRAL, "follow");

		// initializing active directory LDAP connection
		try {
			dirContext = new InitialDirContext(properties);
		} catch (NamingException e) {
			logger.debug("NamingException al iniciar contexto active directory", e);
		}

		// default domain base for search
		domainBase = getDomainBase(domainController);

		// initializing search controls
		searchCtls = new SearchControls();
		searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);
		searchCtls.setReturningAttributes(returnAttributes);
	}

	/**
	 * search the Active directory by username/email id for given search base
	 * 
	 * @param searchValue a {@link java.lang.String} object - search value used
	 * for AD search for eg. username or email
	 * @param searchBy a {@link java.lang.String} object - scope of search by
	 * username or by email id
	 * @param searchBase a {@link java.lang.String} object - search base value
	 * for scope tree for eg. DC=myjeeva,DC=com
	 * @return search result a {@link javax.naming.NamingEnumeration} object -
	 * active directory search result
	 * @throws NamingException
	 */
	public NamingEnumeration<SearchResult> searchUser(String searchValue, String searchBy, String searchBase)
			throws NamingException {
		String filter = getFilter(searchValue, searchBy);
		String base = (null == searchBase) ? domainBase : getDomainBase(searchBase);

		return this.dirContext.search(base, filter, this.searchCtls);
	}

	/**
	 * closes the LDAP connection with Domain controller
	 */
	public void closeLdapConnection() {
		try {
			if (dirContext != null) {
				dirContext.close();
			}
		} catch (NamingException e) {
			logger.debug("NamingException al cerrar conexion active directory", e);
		}
	}

	/**
	 * active directory filter string value
	 * 
	 * @param searchValue a {@link java.lang.String} object - search value of
	 * username/email id for active directory
	 * @param searchBy a {@link java.lang.String} object - scope of search by
	 * username or email id
	 * @return a {@link java.lang.String} object - filter string
	 */
	private String getFilter(String searchValue, String searchBy) {
		String filter = this.baseFilter;
		if (searchBy.equals("email")) {
			filter += "(mail=" + searchValue + "))";
		} else if (searchBy.equals("username")) {
			filter += "(samaccountname=" + searchValue + "))";
		}else {
			throw new IllegalStateException();
		}
		return filter;
	}

	/**
	 * creating a domain base value from domain controller name
	 * 
	 * @param base a {@link java.lang.String} object - name of the domain
	 * controller
	 * @return a {@link java.lang.String} object - base name for eg.
	 * DC=myjeeva,DC=com
	 */
	private static String getDomainBase(String base) {
		char[] namePair = base.toUpperCase().toCharArray();
		String dn = "DC=";
		for (int i = 0; i < namePair.length; i++) {
			if (namePair[i] == '.') {
				dn += ",DC=";
			} else {
				dn += namePair[i];
			}
		}
		return dn;
	}

}

