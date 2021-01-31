package cl.mineduc.came.apoyo_mejora_continua.enums;

public enum AsignacionTipoEnum {
	DIRECTA(2465568769368393407l), 
	RED(2465568769376782016l);

	private final Long value;

	AsignacionTipoEnum(long value) {
	        this.value = value;
	    }

	public long getValue() {
		return value;
	}

}
