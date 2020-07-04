package pe.com.gesadmin.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.*;


/**
 * The persistent class for the amonestacion database table.
 * 
 */
@Entity
@NamedQuery(name="Amonestacion.findAll", query="SELECT a FROM Amonestacion a")
public class Amonestacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private String descripcion;

	private Integer estado;
	
	@Column(insertable=false, updatable=false)
	private Timestamp registro;
	
	@ManyToOne
	@JoinColumn(name="id_periodo", referencedColumnName = "id")
	private Periodo periodo;

	@ManyToOne
	@JoinColumn(name="id_persona", referencedColumnName = "id")
	private Persona persona;

	@ManyToOne
	@JoinColumn(name="id_puesto", referencedColumnName = "id")
	private Puesto puesto;

	//uni-directional many-to-one association to TipoAmonestacion
	@ManyToOne
	@JoinColumn(name="id_tipo_amonestacion", referencedColumnName = "id")
	private TipoAmonestacion tipoAmonestacion;

	public Amonestacion() {
		periodo = new Periodo();
		persona = new Persona();
		puesto = new Puesto();
		tipoAmonestacion = new TipoAmonestacion();
	}

	public Amonestacion(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public Timestamp getRegistro() {
		return registro;
	}

	public void setRegistro(Timestamp registro) {
		this.registro = registro;
	}

	public Periodo getPeriodo() {
		return periodo;
	}

	public void setPeriodo(Periodo periodo) {
		this.periodo = periodo;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public Puesto getPuesto() {
		return puesto;
	}

	public void setPuesto(Puesto puesto) {
		this.puesto = puesto;
	}

	public TipoAmonestacion getTipoAmonestacion() {
		return tipoAmonestacion;
	}

	public void setTipoAmonestacion(TipoAmonestacion tipoAmonestacion) {
		this.tipoAmonestacion = tipoAmonestacion;
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
		Amonestacion other = (Amonestacion) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Amonestacion [id=" + id + ", descripcion=" + descripcion + ", estado=" + estado + ", registro="
				+ registro + ", periodo=" + periodo + ", persona=" + persona + ", puesto=" + puesto
				+ ", tipoAmonestacion=" + tipoAmonestacion + "]";
	}
	
	
	
	



}