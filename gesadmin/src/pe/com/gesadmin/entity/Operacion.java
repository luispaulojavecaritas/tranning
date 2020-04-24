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
@NamedQuery(name="Operacion.findAll", query="SELECT o FROM Operacion o")
public class Operacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private String descripcion;

	private Integer estado;

	@Column(name="fecha_pago")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaPago;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_vencimiento")
	private Date fechaVencimiento;

	private Double monto;

	@Column(insertable=false, updatable=false)
	private Timestamp registro;

	//uni-directional many-to-one association to Persona
	@ManyToOne
	@JoinColumn(name="id_persona_responsable_operacion", referencedColumnName = "id", nullable = true)
	private Persona personaResponsableOperacion;

	//uni-directional many-to-one association to Proveedor
	@ManyToOne
	@JoinColumn(name="id_proveedor", referencedColumnName = "id", nullable = true)
	private Proveedor proveedor;

	//uni-directional many-to-one association to Puesto
	@ManyToOne
	@JoinColumn(name="id_puesto", referencedColumnName = "id", nullable = true)
	private Puesto puesto;

	//uni-directional many-to-one association to SituacionOrden
	@ManyToOne
	@JoinColumn(name="id_estatus_operacion", referencedColumnName = "id")
	private EstatusOperacion estatusOperacion;

	//uni-directional many-to-one association to TipoOrden
	@ManyToOne
	@JoinColumn(name="id_tipo_operacion", referencedColumnName = "id")
	private TipoOperacion tipoOperacion;
	
	@ManyToOne
	@JoinColumn(name="id_periodo", referencedColumnName = "id")
	private Periodo periodo;
	
	@ManyToOne
	@JoinColumn(name="id_categoria_operacion", referencedColumnName = "id")
	private CategoriaOperacion categoriaOperacion;

	public Operacion() {
		personaResponsableOperacion = new Persona();
		proveedor = new Proveedor();
		puesto = new Puesto();
		estatusOperacion = new EstatusOperacion();
		tipoOperacion = new TipoOperacion();
		periodo = new Periodo();
		categoriaOperacion = new CategoriaOperacion();
	}

	public Operacion(Integer id) {
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

	public Date getFechaPago() {
		return this.fechaPago;
	}

	public void setFechaPago(Date fechaPago) {
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

	public Persona getPersonaResponsableOperacion() {
		return personaResponsableOperacion;
	}

	public void setPersonaResponsableOperacion(Persona personaResponsableOperacion) {
		this.personaResponsableOperacion = personaResponsableOperacion;
	}

	public CategoriaOperacion getCategoriaOperacion() {
		return categoriaOperacion;
	}

	public void setCategoriaOperacion(CategoriaOperacion categoriaOperacion) {
		this.categoriaOperacion = categoriaOperacion;
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

	public EstatusOperacion getEstatusOperacion() {
		return estatusOperacion;
	}

	public void setEstatusOperacion(EstatusOperacion estatusOperacion) {
		this.estatusOperacion = estatusOperacion;
	}

	public TipoOperacion getTipoOperacion() {
		return tipoOperacion;
	}

	public void setTipoOperacion(TipoOperacion tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
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
		Operacion other = (Operacion) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Operacion [id=" + id + ", descripcion=" + descripcion + ", estado=" + estado + ", fechaPago="
				+ fechaPago + ", fechaVencimiento=" + fechaVencimiento + ", monto=" + monto + ", registro=" + registro
				+ ", personaResponsableOperacion=" + personaResponsableOperacion + ", proveedor=" + proveedor + ", puesto=" + puesto + ", estatusOperacion="
				+ estatusOperacion + ", tipoOperacion=" + tipoOperacion + ", periodo=" + periodo + "]";
	}
	
}