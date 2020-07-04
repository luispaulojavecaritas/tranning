package pe.com.gesadmin.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.*;


/**
 * The persistent class for the medida_agua database table.
 * 
 */
@Entity
@Table(name="medida_agua")
@NamedQuery(name="MedidaAgua.findAll", query="SELECT m FROM MedidaAgua m")
public class MedidaAgua implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private Integer estado;
	
	@ManyToOne
	@JoinColumn(name="id_periodo", referencedColumnName = "id")
	private Periodo periodo;
	
	@ManyToOne
	@JoinColumn(name="id_puesto", referencedColumnName = "id")
	private Puesto puesto;

	private Double medida;

	@Column(insertable=false, updatable=false)
	private Timestamp registro;

	public MedidaAgua() {
		periodo = new Periodo();
		puesto = new Puesto();
	}

	public MedidaAgua(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public Periodo getPeriodo() {
		return periodo;
	}

	public void setPeriodo(Periodo periodo) {
		this.periodo = periodo;
	}

	public Puesto getPuesto() {
		return puesto;
	}

	public void setPuesto(Puesto puesto) {
		this.puesto = puesto;
	}

	public Double getMedida() {
		return medida;
	}

	public void setMedida(Double medida) {
		this.medida = medida;
	}

	public Timestamp getRegistro() {
		return registro;
	}

	public void setRegistro(Timestamp registro) {
		this.registro = registro;
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
		MedidaAgua other = (MedidaAgua) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MedidaAgua [id=" + id + ", estado=" + estado + ", periodo=" + periodo + ", puesto=" + puesto
				+ ", medida=" + medida + ", registro=" + registro + "]";
	}
	
	
	
	

}