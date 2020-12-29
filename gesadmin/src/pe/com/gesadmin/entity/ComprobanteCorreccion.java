package pe.com.gesadmin.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;

/**
 * The persistent class for the tipo_estatus database table.
 * 
 */
@Entity
@Table(name = "comprobante_correccion")
@NamedQuery(name = "ComprobanteCorreccion.findAll", query = "SELECT t FROM ComprobanteCorreccion t")
public class ComprobanteCorreccion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "id_operacion", referencedColumnName = "id")
	private Operacion operacion;

	@Column(name = "id_usuario")
	private Integer idUsuario;

	@Column(name = "descripcion_usuario")
	private String descripcionUsuario;

	private String motivo;

	private Double monto;

	@Column(name = "monto_descripcion")
	private String montoDescripcion;

	private Integer estado;

	@Column(insertable = false, updatable = false)
	private Timestamp registro;

	public ComprobanteCorreccion() {
		operacion = new Operacion();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Operacion getOperacion() {
		return operacion;
	}

	public void setOperacion(Operacion operacion) {
		this.operacion = operacion;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getDescripcionUsuario() {
		return descripcionUsuario;
	}

	public void setDescripcionUsuario(String descripcionUsuario) {
		this.descripcionUsuario = descripcionUsuario;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public Double getMonto() {
		return monto;
	}

	public void setMonto(Double monto) {
		this.monto = monto;
	}

	public String getMontoDescripcion() {
		return montoDescripcion;
	}

	public void setMontoDescripcion(String montoDescripcion) {
		this.montoDescripcion = montoDescripcion;
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
		ComprobanteCorreccion other = (ComprobanteCorreccion) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ComprobanteCorreccion [id=" + id + ", montoDescripcion=" + montoDescripcion + ", descripcionUsuario="
				+ descripcionUsuario + ", motivo=" + motivo + ", operacion=" + operacion + ", estado="
				+ estado + ", registro=" + registro + "]";
	}

}