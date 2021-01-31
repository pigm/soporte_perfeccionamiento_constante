package cl.mineduc.came.apoyo_mejora_continua.helpers;

import java.util.Locale;

import org.springframework.util.StringUtils;

public class RutUtil {
	
	private int rut; 
	private String digito;
	
	private RutUtil(int rut, String digito) {
		super();
		this.rut = rut;
		this.digito = digito;
	}
	
	/** 
	 * @return int
	 */
	public int getRut() {
		return rut;
	}
	
	/** 
	 * @return String
	 */
	public String getDigito() {
		return digito;
	}
	
	/** 
	 * @return String
	 */
	@Override
	public String toString() {
		return String.format(new Locale("es", "CL"), "%,d-%s", rut, digito);
	}
	
	
	/** 
	 * @param rut
	 * @return String
	 */
	public static String formatearRut(String rut) {	
		String salida="";
		if (rut != null && StringUtils.hasText(rut.toString())) 	
		{	
		String rutSinFormato = rutSinFormato(rut);
		char lastChar = rutSinFormato.charAt(rutSinFormato.length() - 1);
		int indice = rutSinFormato.lastIndexOf(lastChar);
		String[] rutSinDV = {rutSinFormato.substring(0,indice), rutSinFormato.substring(indice)};
		RutUtil rutUtil = new RutUtil(Integer.parseInt(rutSinDV[0]), rutSinDV[1]);
		salida= rutUtil.toString();
		}
		return salida;
	}
	
	
	/** 
	 * @param rut
	 * @return String
	 */
	public static String rutSinFormato(String rut) {			
		if(rut==null){
			return "";
		}else{
			return rut.replaceAll("[.]", "").replaceAll("-", "");
		}
	}
	
	
	/** 
	 * @param rut
	 * @return int
	 */
	public static int splitRutFromDv(String rut) {
		int mantisaInt = 0;
		rut = rut.replaceAll("[.]", "").replaceAll("-", "").trim().toUpperCase();
		String mantisa = rut.substring(0, rut.length() - 1);
		if(ValidacionesUtil.isInteger(mantisa)) {
			mantisaInt = Integer.parseInt(mantisa);
		}
		return mantisaInt;		
	}
	
	
	/** 
	 * @param rut
	 * @return char
	 */
	public static char splitDVFromRut(String rut) {
		return rut.charAt(rut.length() - 1);
	}
	

}
