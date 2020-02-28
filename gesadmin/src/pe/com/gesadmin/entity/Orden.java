package pe.com.gesadmin.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;


/**
 * The persistent class for the orden database table.
 * 
 */
@Entity
@NamedQuery(name="Orden.findAll", query="SELECT o FROM Orden o")
public class Orden implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private String descripcion;

	private Integer estado;

	@Column(name="fecha_pago")
	private Timestamp fechaPago;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_vencimiento")
	private Date fechaVencimiento;

	private Double monto;

	@Column(insertable=false, updatable=false)
	private Timestamp registro;

	//uni-directional many-to-one association to Persona
	@ManyToOne
	@JoinColumn(name="id_persona_responsable_operacion", referencedColumnName = "id")
	private Persona persona;

	//uni-directional many-to-one association to Proveedor
	@ManyToOne
	@JoinColumn(name="id_proveedor", referencedColumnName = "id")
	private Proveedor proveedor;

	//uni-directional many-to-one association to Puesto
	@ManyToOne
	@JoinColumn(name="id_puesto", referencedColumnName = "id")
	private Puesto puesto;

	//uni-directional many-to-one association to SituacionOrden
	@ManyToOne
	@JoinColumn(name="id_situacion_orden", referencedColumnName = "id")
	private SituacionOrden situacionOrden;

	//uni-directional many-to-one association to TipoOrden
	@ManyToOne
	@JoinColumn(name="id_tipo_orden", referencedColumnName = "id")
	private TipoOrden tipoOrden;
	
	@ManyToOne
	@JoinColumn(name="id_periodo", referencedColumnName = "id")
	private Periodo periodo;

	public Orden() {
		persona = new Persona();
		proveedor = new Proveedor();
		puesto = new Puesto();
		situacionOrden = new SituacionOrden();
		tipoOrden = new TipoOrden();
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

	public Timestamp getFechaPago() {
		return this.fechaPago;
	}

	public void setFechaPago(Timestamp fechaPago) {
		this.fechaPago = fechaPago;
	}

	public Date getFechaVencimiento() {
		return this.fechaVencimiento;
	}

	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public Double getMonto() {
		return this.monto;
	}

	public void setMonto(Double monto) {
		this.monto = monto;
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

	public Puesto getPuesto() {
		return this.puesto;
	}

	public void setPuesto(Puesto puesto) {
		this.puesto = puesto;
	}

	public SituacionOrden getSituacionOrden() {
		return this.situacionOrden;
	}

	public void setSituacionOrden(SituacionOrden situacionOrden) {
		this.situacionOrden = situacionOrden;
	}

	public TipoOrden getTipoOrden() {
		return this.tipoOrden;
	}

	public void setTipoOrden(TipoOrden tipoOrden) {
		this.tipoOrden = tipoOrden;
	}

	public Periodo getPeriodo() {
		return periodo;
	}

	public void setPeriodo(Periodo periodo) {
		this.periodo = periodo;
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
		Orden other = (Orden) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Orden [id=" + id + ", descripcion=" + descripcion + ", estado=" + estado + ", fechaPago=" + fechaPago
				+ ", fechaVencimiento=" + fechaVencimiento + ", monto=" + monto + ", registro=" + registro
				+ ", persona=" + persona + ", proveedor=" + proveedor + ", puesto=" + puesto + ", situacionOrden="
				+ situacionOrden + ", tipoOrden=" + tipoOrden + ", periodo=" + periodo + "]";
	}
	
	

}