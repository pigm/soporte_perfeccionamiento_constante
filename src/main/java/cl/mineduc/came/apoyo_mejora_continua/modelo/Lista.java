package cl.mineduc.came.apoyo_mejora_continua.modelo;

import java.io.Serializable;
import java.util.List;

public class Lista implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 3718992678589361496L;
	
	private Long idLista;
	
	private String nombre;
	
	private List<ElementoLista> elementosListas;

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

	public List<ElementoLista> getElementosListas() {
		return elementosListas;
	}

	public void setElementosListas(List<ElementoLista> elementosListas) {
		this.elementosListas = elementosListas;
	}

	@Override
	public String toString() {
		return "Lista [idLista=" + idLista + ", nombre=" + nombre + ", elementosListas=" + elementosListas + "]";
	}
    
}