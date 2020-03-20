package pe.com.gesadmin.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the puesto database table.
 * 
 */
@Entity
@NamedQuery(name="Puesto.findAll", query="SELECT p FROM Puesto p")
public class Puesto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private String descripcion;

	private Integer estado;

	@Column(insertable=false, updatable=false)
	private Timestamp registro;

	//uni-directional many-to-one association to Bloque
	@ManyToOne
	@JoinColumn(name="id_bloque", referencedColumnName = "id")
	private Bloque bloque;

	public Puesto() {
		bloque = new Bloque();
	}

	public Puesto(Integer id, String descripcion) {
		this.id = id;
		this.descripcion = descripcion;
	}

	public Puesto(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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

	public Bloque getBloque() {
		return this.bloque;
	}

	public void setBloque(Bloque bloque) {
		this.bloque = bloque;
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
		Puesto other = (Puesto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Puesto [id=" + id + ", descripcion=" + descripcion + ", estado=" + estado + ", registro=" + registro
				+ ", bloque=" + bloque + "]";
	}
	
	

}