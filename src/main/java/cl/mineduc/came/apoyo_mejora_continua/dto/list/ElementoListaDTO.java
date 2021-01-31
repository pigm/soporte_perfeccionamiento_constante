package cl.mineduc.came.apoyo_mejora_continua.dto.list;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

public class ElementoListaDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3491589724883172009L;

	private String idElementoLista;

    private String idLista;
    @NotEmpty
    private String nombre;
    
    private boolean status;

	private boolean removable;

	public String getIdElementoLista() {
		return idElementoLista;
	}

	public void setIdElementoLista(String idElementoLista) {
		this.idElementoLista = idElementoLista;
	}

	public String getIdLista() {
		return idLista;
	}

	public void setIdLista(String idLista) {
		this.idLista = idLista;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ElementoListaDTO [idElementoLista=" + idElementoLista + ", idLista=" + idLista + ", nombre=" + nombre
				+ ", status=" + status + "]";
	}

	public boolean isRemovable() {
		return removable;
	}

	public void setRemovable(boolean removable) {
		this.removable = removable;
	}
    
}
