package pe.com.gesadmin.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the puesto_persona database table.
 * 
 */
@Entity
@Table(name="puesto_persona")
@NamedQuery(name="PuestoPersona.findAll", query="SELECT p FROM PuestoPersona p")
public class PuestoPersona implements Serializable {
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
	@JoinColumn(name="id_tipo_estatus", referencedColumnName = "id")
	private TipoEstatus tipoEstatus;

	public PuestoPersona() {
		persona = new Persona();
		puesto = new Puesto();
		tipoEstatus = new TipoEstatus();
	}

	public PuestoPersona(Integer id) {
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

	public TipoEstatus getTipoEstatus() {
		return this.tipoEstatus;
	}

	public void setTipoEstatus(TipoEstatus tipoEstatus) {
		this.tipoEstatus = tipoEstatus;
	}

}