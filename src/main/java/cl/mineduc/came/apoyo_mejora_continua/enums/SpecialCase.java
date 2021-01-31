package cl.mineduc.came.apoyo_mejora_continua.enums;

public enum SpecialCase {
	REGIONAL(0), PROVINCIALES(1), REDES(2), SUPERVISORES(3), APOYO(4);

	private final Integer special;

	SpecialCase(Integer special) {
		this.special = special;
	}

	public Integer getSpecial() {
		return special;
	}

}
