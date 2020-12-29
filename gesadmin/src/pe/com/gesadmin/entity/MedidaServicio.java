package pe.com.gesadmin.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * The persistent class for the medida_luz database table.
 * 
 */
@Entity
@Table(name="medida_servicio")
@NamedQuery(name="MedidaServicio.findAll", query="SELECT m FROM MedidaServicio m")
public class MedidaServicio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private Integer estado;

	@ManyToOne
	@JoinColumn(name = "id_periodo", referencedColumnName = "id")
	private Periodo periodo;
	
	@ManyToOne
	@JoinColumn(name = "id_tipo_servicio", referencedColumnName = "id")
	private TipoServicio tipoServicio;

	@ManyToOne
	@JoinColumn(name = "id_puesto", referencedColumnName = "id")
	private Puesto puesto;
	
	@Column(name = "id_usuario")
	private Integer idUsuario; 

	private Double medida;

	@Temporal(TemporalType.TIMESTAMP)
	private Date registro;
	
	
	private Double consumo;
	
	private String descripcion;
	
	@Column(name = "medida_anterior")
	private Double medidaAnterior;

	public MedidaServicio() {
		periodo = new Periodo();
		tipoServicio = new TipoServicio();
		puesto = new Puesto();
	}

	public MedidaServicio(Integer id) {
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

	public Date getRegistro() {
		return registro;
	}

	public void setRegistro(Date registro) {
		this.registro = registro;
	}

	public TipoServicio getTipoServicio() {
		return tipoServicio;
	}

	public void setTipoServicio(TipoServicio tipoServicio) {
		this.tipoServicio = tipoServicio;
	}

	public Double getConsumo() {
		return consumo;
	}

	public void setConsumo(Double consumo) {
		this.consumo = consumo;
	}

	public Double getMedidaAnterior() {
		return medidaAnterior;
	}

	public void setMedidaAnterior(Double medidaAnterior) {
		this.medidaAnterior = medidaAnterior;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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
		MedidaServicio other = (MedidaServicio) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MedidaServicio [id=" + id + ", estado=" + estado + ", periodo=" + periodo + ", tipoServicio="
				+ tipoServicio + ", puesto=" + puesto + ", idUsuario=" + idUsuario + ", medida=" + medida
				+ ", registro=" + registro + ", consumo=" + consumo + ", descripcion=" + descripcion
				+ ", medidaAnterior=" + medidaAnterior + "]";
	}



}