package cl.mineduc.came.apoyo_mejora_continua.modelo;

import java.io.Serializable;

public class ElementoLista implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -2074019549363648325L;

	private Long idElementoLista;

    private Long idLista;
    
    private String nombre;
    
    private boolean status;

    public Long getIdElementoLista() {
        return idElementoLista;
    }

    public void setIdElementoLista(Long idElementoLista) {
        this.idElementoLista = idElementoLista;
    }

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

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ElementoLista [idElementoLista=" + idElementoLista + ", idLista=" + idLista + ", nombre=" + nombre
				+ ", status=" + status + "]";
	}
    
}