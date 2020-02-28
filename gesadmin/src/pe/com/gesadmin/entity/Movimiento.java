package pe.com.gesadmin.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the movimiento database table.
 * 
 */
@Entity
@NamedQuery(name="Movimiento.findAll", query="SELECT m FROM Movimiento m")
public class Movimiento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private String bloque;

	private Integer estado;

	@Column(name="orden_descripcion")
	private String ordenDescripcion;

	@Column(name="orden_fecha_pago")
	private String ordenFechaPago;

	@Column(name="orden_fecha_vencimiento")
	private String ordenFechaVencimiento;

	@Column(name="orden_id")
	private Integer ordenId;

	@Column(name="orden_importe")
	private String ordenImporte;

	@Column(name="orden_situacion")
	private String ordenSituacion;

	@Column(name="orden_tipo")
	private String ordenTipo;

	private String periodo;

	@Column(name="persona_celular")
	private String personaCelular;

	@Column(name="persona_correo")
	private String personaCorreo;

	@Column(name="persona_fijo")
	private String personaFijo;

	@Column(name="persona_materno")
	private String personaMaterno;

	@Column(name="persona_nombre")
	private String personaNombre;

	@Column(name="persona_nro_doc")
	private String personaNroDoc;

	@Column(name="persona_pais")
	private String personaPais;

	@Column(name="persona_paterno")
	private String personaPaterno;

	@Column(name="persona_sexo")
	private String personaSexo;

	@Column(name="persona_tipo_doc")
	private String personaTipoDoc;

	@Column(name="proveedor_razon_social")
	private String proveedorRazonSocial;

	@Column(name="proveedor_ruc")
	private String proveedorRuc;

	private String puesto;

	@Column(insertable=false, updatable=false)
	private Timestamp registro;

	public Movimiento() {
	}

	public Movimiento(Integer id) {
		this.id = id;
	}
	

	public Movimiento(Integer id, String bloque, Integer estado, String ordenDescripcion, String ordenFechaPago,
			String ordenFechaVencimiento, Integer ordenId, String ordenImporte, String ordenSituacion, String ordenTipo,
			String periodo, String personaCelular, String personaCorreo, String personaFijo, String personaMaterno,
			String personaNombre, String personaNroDoc, String personaPais, String personaPaterno, String personaSexo,
			String personaTipoDoc, String proveedorRazonSocial, String proveedorRuc, String puesto,
			Timestamp registro) {
		this.id = id;
		this.bloque = bloque;
		this.estado = estado;
		this.ordenDescripcion = ordenDescripcion;
		this.ordenFechaPago = ordenFechaPago;
		this.ordenFechaVencimiento = ordenFechaVencimiento;
		this.ordenId = ordenId;
		this.ordenImporte = ordenImporte;
		this.ordenSituacion = ordenSituacion;
		this.ordenTipo = ordenTipo;
		this.periodo = periodo;
		this.personaCelular = personaCelular;
		this.personaCorreo = personaCorreo;
		this.personaFijo = personaFijo;
		this.personaMaterno = personaMaterno;
		this.personaNombre = personaNombre;
		this.personaNroDoc = personaNroDoc;
		this.personaPais = personaPais;
		this.personaPaterno = personaPaterno;
		this.personaSexo = personaSexo;
		this.personaTipoDoc = personaTipoDoc;
		this.proveedorRazonSocial = proveedorRazonSocial;
		this.proveedorRuc = proveedorRuc;
		this.puesto = puesto;
		this.registro = registro;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBloque() {
		return this.bloque;
	}

	public void setBloque(String bloque) {
		this.bloque = bloque;
	}

	public Integer getEstado() {
		return this.estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public String getOrdenDescripcion() {
		return this.ordenDescripcion;
	}

	public void setOrdenDescripcion(String ordenDescripcion) {
		this.ordenDescripcion = ordenDescripcion;
	}

	public String getOrdenFechaPago() {
		return this.ordenFechaPago;
	}

	public void setOrdenFechaPago(String ordenFechaPago) {
		this.ordenFechaPago = ordenFechaPago;
	}

	public String getOrdenFechaVencimiento() {
		return this.ordenFechaVencimiento;
	}

	public void setOrdenFechaVencimiento(String ordenFechaVencimiento) {
		this.ordenFechaVencimiento = ordenFechaVencimiento;
	}

	public Integer getOrdenId() {
		return this.ordenId;
	}

	public void setOrdenId(Integer ordenId) {
		this.ordenId = ordenId;
	}

	public String getOrdenImporte() {
		return this.ordenImporte;
	}

	public void setOrdenImporte(String ordenImporte) {
		this.ordenImporte = ordenImporte;
	}

	public String getOrdenSituacion() {
		return this.ordenSituacion;
	}

	public void setOrdenSituacion(String ordenSituacion) {
		this.ordenSituacion = ordenSituacion;
	}

	public String getOrdenTipo() {
		return this.ordenTipo;
	}

	public void setOrdenTipo(String ordenTipo) {
		this.ordenTipo = ordenTipo;
	}

	public String getPeriodo() {
		return this.periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public String getPersonaCelular() {
		return this.personaCelular;
	}

	public void setPersonaCelular(String personaCelular) {
		this.personaCelular = personaCelular;
	}

	public String getPersonaCorreo() {
		return this.personaCorreo;
	}

	public void setPersonaCorreo(String personaCorreo) {
		this.personaCorreo = personaCorreo;
	}

	public String getPersonaFijo() {
		return this.personaFijo;
	}

	public void setPersonaFijo(String personaFijo) {
		this.personaFijo = personaFijo;
	}

	public String getPersonaMaterno() {
		return this.personaMaterno;
	}

	public void setPersonaMaterno(String personaMaterno) {
		this.personaMaterno = personaMaterno;
	}

	public String getPersonaNombre() {
		return this.personaNombre;
	}

	public void setPersonaNombre(String personaNombre) {
		this.personaNombre = personaNombre;
	}

	public String getPersonaNroDoc() {
		return this.personaNroDoc;
	}

	public void setPersonaNroDoc(String personaNroDoc) {
		this.personaNroDoc = personaNroDoc;
	}

	public String getPersonaPais() {
		return this.personaPais;
	}

	public void setPersonaPais(String personaPais) {
		this.personaPais = personaPais;
	}

	public String getPersonaPaterno() {
		return this.personaPaterno;
	}

	public void setPersonaPaterno(String personaPaterno) {
		this.personaPaterno = personaPaterno;
	}

	public String getPersonaSexo() {
		return this.personaSexo;
	}

	public void setPersonaSexo(String personaSexo) {
		this.personaSexo = personaSexo;
	}

	public String getPersonaTipoDoc() {
		return this.personaTipoDoc;
	}

	public void setPersonaTipoDoc(String personaTipoDoc) {
		this.personaTipoDoc = personaTipoDoc;
	}

	public String getProveedorRazonSocial() {
		return this.proveedorRazonSocial;
	}

	public void setProveedorRazonSocial(String proveedorRazonSocial) {
		this.proveedorRazonSocial = proveedorRazonSocial;
	}

	public String getProveedorRuc() {
		return this.proveedorRuc;
	}

	public void setProveedorRuc(String proveedorRuc) {
		this.proveedorRuc = proveedorRuc;
	}

	public String getPuesto() {
		return this.puesto;
	}

	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}

	public Timestamp getRegistro() {
		return this.registro;
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
		Movimiento other = (Movimiento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Movimiento [id=" + id + ", bloque=" + bloque + ", estado=" + estado + ", ordenDescripcion="
				+ ordenDescripcion + ", ordenFechaPago=" + ordenFechaPago + ", ordenFechaVencimiento="
				+ ordenFechaVencimiento + ", ordenId=" + ordenId + ", ordenImporte=" + ordenImporte
				+ ", ordenSituacion=" + ordenSituacion + ", ordenTipo=" + ordenTipo + ", periodo=" + periodo
				+ ", personaCelular=" + personaCelular + ", personaCorreo=" + personaCorreo + ", personaFijo="
				+ personaFijo + ", personaMaterno=" + personaMaterno + ", personaNombre=" + personaNombre
				+ ", personaNroDoc=" + personaNroDoc + ", personaPais=" + personaPais + ", personaPaterno="
				+ personaPaterno + ", personaSexo=" + personaSexo + ", personaTipoDoc=" + personaTipoDoc
				+ ", proveedorRazonSocial=" + proveedorRazonSocial + ", proveedorRuc=" + proveedorRuc + ", puesto="
				+ puesto + ", registro=" + registro + "]";
	}
	

}