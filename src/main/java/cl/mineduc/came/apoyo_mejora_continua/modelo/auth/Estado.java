package cl.mineduc.came.apoyo_mejora_continua.modelo.auth;

public enum Estado {
	ACTIVO("Registro Activo"), 
	INACTIVO("Registro Inactivo"),
	ELIMINADO("Registro Eliminado");

	private final String texto;

	Estado(String v) {
		this.texto = v;
	}

	public String getTexto() {
		return texto;
	}
}
