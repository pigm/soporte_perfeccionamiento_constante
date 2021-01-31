package cl.mineduc.came.apoyo_mejora_continua.dto.list;

import java.io.Serializable;
import java.util.List;

public class ListDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 17418791349637750L;

	private Long idLista;
	
	private String nombre;

	private List<ElementoListaDTO> elementosListas;

	public Long getIdLista() {
		return idLista;
	}

	public void setIdLista(Long idLista) {
		this.idLista = idLista;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<ElementoListaDTO> getElementosListas() {
		return elementosListas;
	}

	public void setElementosListas(List<ElementoListaDTO> elementosListas) {
		this.elementosListas = elementosListas;
	}

	@Override
	public String toString() {
		return "ListDTO [idLista=" + idLista + ", nombre=" + nombre + ", elementosListas=" + elementosListas + "]";
	}

}
