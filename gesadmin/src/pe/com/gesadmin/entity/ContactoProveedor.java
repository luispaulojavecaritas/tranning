package pe.com.gesadmin.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the contacto_proveedor database table.
 * 
 */
@Entity
@Table(name="contacto_proveedor")
@NamedQuery(name="ContactoProveedor.findAll", query="SELECT c FROM ContactoProveedor c")
public class ContactoProveedor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private Integer estado;

	@Column(insertable=false, updatable=false)
	private Timestamp registro;

	//uni-directional many-to-one association to Persona
	@ManyToOne
	@JoinColumn(name="id_persona", referencedColumnName = "id")
	private Persona persona;

	//uni-directional many-to-one association to Proveedor
	@ManyToOne
	@JoinColumn(name="id_proveedor", referencedColumnName = "id")
	private Proveedor proveedor;

	public ContactoProveedor() {
		proveedor = new Proveedor();
		persona = new Persona();
	}
	
	public ContactoProveedor(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getEstado() {
		return this.estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public Timestamp getRegistro() {
		return this.registro;
	}

	public void setRegistro(Timestamp registro) {
		this.registro = registro;
	}

	public Persona getPersona() {
		return this.persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public Proveedor getProveedor() {
		return this.proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ContactoProveedor other = (ContactoProveedor) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ContactoProveedor [id=" + id + ", estado=" + estado + ", registro=" + registro + ", persona=" + persona
				+ ", proveedor=" + proveedor + "]";
	}
	
	

}