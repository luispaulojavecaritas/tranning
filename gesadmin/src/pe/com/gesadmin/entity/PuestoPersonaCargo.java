package pe.com.gesadmin.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the puesto_persona database table.
 * 
 */
@Entity
@Table(name="puesto_persona_cargo")
@NamedQuery(name="PuestoPersonaCargo.findAll", query="SELECT p FROM PuestoPersonaCargo p")
public class PuestoPersonaCargo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private String descripcion;

	private Integer estado;

	@Column(insertable=false, updatable=false)
	private Timestamp registro;

	//uni-directional many-to-one association to Persona
	@ManyToOne
	@JoinColumn(name="id_persona", referencedColumnName = "id")
	private Persona persona;

	//uni-directional many-to-one association to Puesto
	@ManyToOne
	@JoinColumn(name="id_puesto", referencedColumnName = "id")
	private Puesto puesto;

	//uni-directional many-to-one association to TipoEstatus
	@ManyToOne
	@JoinColumn(name="id_cargo", referencedColumnName = "id")
	private Cargo cargo;

	public PuestoPersonaCargo() {
		persona = new Persona();
		puesto = new Puesto();
		cargo = new Cargo();
	}

	public PuestoPersonaCargo(Integer id) {
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

	public Persona getPersona() {
		return this.persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public Puesto getPuesto() {
		return this.puesto;
	}

	public void setPuesto(Puesto puesto) {
		this.puesto = puesto;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
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
		PuestoPersonaCargo other = (PuestoPersonaCargo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PuestoPersonaCargo [id=" + id + ", descripcion=" + descripcion + ", estado=" + estado + ", registro="
				+ registro + ", persona=" + persona + ", puesto=" + puesto + ", cargo=" + cargo + "]";
	}
	
	


}