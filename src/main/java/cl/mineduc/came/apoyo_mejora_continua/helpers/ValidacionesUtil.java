package cl.mineduc.came.apoyo_mejora_continua.helpers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cl.mineduc.framework2.exceptions.MineducException;

public class ValidacionesUtil {
	
	private static Logger logger = LogManager.getLogger(ValidacionesUtil.class);

	
	
	/** 
	 * @param rut
	 * @return boolean
	 */
	public static boolean validaRutConDV(String rut) {
		boolean ret = false;
		if (rut != null && rut.trim().length() > 0) {
			try {
				rut = rut.replaceAll("[.]", "").replaceAll("-", "").trim().toUpperCase();
				char dv = rut.charAt(rut.length() - 1);
				String mantisa = rut.substring(0, rut.length() - 1);
				if (isInteger(mantisa)) {
					int mantisaInt = Integer.parseInt(mantisa);
					ret = validarRut(mantisaInt, dv);
				}
			} catch (Exception e) {
				logger.error("Error: validaRutConDV() ", e);
				throw new MineducException("Error al validar el rut");
			}
		}
		return ret;
	}
	
	
	/** 
	 * @param rut
	 * @param dv
	 * @return boolean
	 */
	private static boolean validarRut(int rut, char dv) {
		int m = 0;
		int s = 1;
		for (; rut != 0; rut /= 10) {
			s = (s + rut % 10 * (9 - m++ % 6)) % 11;

		}
		return Character.toUpperCase(dv) == (char) (s != 0 ? s + 47 : 75);
	}

	
	/** 
	 * @param cad
	 * @return boolean
	 */
	public static boolean isInteger(String cad) {
		for (int i = 0; i < cad.length(); i++) {
			if (!Character.isDigit(cad.charAt(i))) {
				return false;
			}
		}
		return true;
	}
	
	
	/** 
	 * @param valor
	 * @return boolean
	 */
	public static boolean validaInteger(String valor) {
		return isFirstLetterInteger(valor);
	}

	
	/** 
	 * @param valor
	 * @return boolean
	 */
	private static boolean isFirstLetterInteger(String valor) {
		char firstLetter = valor.charAt(0);
		return (firstLetter >= '0' && firstLetter <= '9');
	}

}
