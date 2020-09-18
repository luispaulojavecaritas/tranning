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

	@Column(name="operacion_descripcion")
	private String operacionDescripcion;

	@Column(name="operacion_fecha_pago")
	private String operacionFechaPago;

	@Column(name="operacion_fecha_vencimiento")
	private String operacionFechaVencimiento;

	@Column(name="operacion_id")
	private Integer operacionId;

	@Column(name="operacion_importe")
	private Double operacionImporte;

	@Column(name="operacion_situacion")
	private String operacionSituacion;

	@Column(name="operacion_tipo")
	private String operacionTipo;

	private String periodo;

	@Column(name="persona_materno")
	private String personaMaterno;

	@Column(name="persona_nombre")
	private String personaNombre;

	@Column(name="persona_nro_doc")
	private String personaNroDoc;

	@Column(name="persona_paterno")
	private String personaPaterno;

	@Column(name="persona_tipo_doc")
	private String personaTipoDoc;

	@Column(name="proveedor_razon_social")
	private String proveedorRazonSocial;

	@Column(name="proveedor_ruc")
	private String proveedorRuc;
	
	@Column(name = "anio_fiscal")
	private String anio_fiscal;

	private String puesto;
	
	@Column(name = "tipo_doc")
	private String tipoDoc; 
	
	@Column(name = "nro_doc")
	private String nroDoc; 

	@Column(insertable=false, updatable=false)
	private Timestamp registro;

	public Movimiento() {
	}

	public Movimiento(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBloque() {
		return bloque;
	}

	public void setBloque(String bloque) {
		this.bloque = bloque;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public String getOperacionDescripcion() {
		return operacionDescripcion;
	}

	public void setOperacionDescripcion(String operacionDescripcion) {
		this.operacionDescripcion = operacionDescripcion;
	}

	public String getOperacionFechaPago() {
		return operacionFechaPago;
	}

	public void setOperacionFechaPago(String operacionFechaPago) {
		this.operacionFechaPago = operacionFechaPago;
	}

	public String getOperacionFechaVencimiento() {
		return operacionFechaVencimiento;
	}

	public void setOperacionFechaVencimiento(String operacionFechaVencimiento) {
		this.operacionFechaVencimiento = operacionFechaVencimiento;
	}

	public Integer getOperacionId() {
		return operacionId;
	}

	public void setOperacionId(Integer operacionId) {
		this.operacionId = operacionId;
	}

	public Double getOperacionImporte() {
		return operacionImporte;
	}

	public void setOperacionImporte(Double operacionImporte) {
		this.operacionImporte = operacionImporte;
	}

	public String getOperacionSituacion() {
		return operacionSituacion;
	}

	public void setOperacionSituacion(String operacionSituacion) {
		this.operacionSituacion = operacionSituacion;
	}

	public String getOperacionTipo() {
		return operacionTipo;
	}

	public void setOperacionTipo(String operacionTipo) {
		this.operacionTipo = operacionTipo;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public String getPersonaMaterno() {
		return personaMaterno;
	}

	public void setPersonaMaterno(String personaMaterno) {
		this.personaMaterno = personaMaterno;
	}

	public String getPersonaNombre() {
		return personaNombre;
	}

	public void setPersonaNombre(String personaNombre) {
		this.personaNombre = personaNombre;
	}

	public String getPersonaNroDoc() {
		return personaNroDoc;
	}

	public void setPersonaNroDoc(String personaNroDoc) {
		this.personaNroDoc = personaNroDoc;
	}

	public String getPersonaPaterno() {
		return personaPaterno;
	}

	public void setPersonaPaterno(String personaPaterno) {
		this.personaPaterno = personaPaterno;
	}

	public String getPersonaTipoDoc() {
		return personaTipoDoc;
	}

	public void setPersonaTipoDoc(String personaTipoDoc) {
		this.personaTipoDoc = personaTipoDoc;
	}

	public String getProveedorRazonSocial() {
		return proveedorRazonSocial;
	}

	public void setProveedorRazonSocial(String proveedorRazonSocial) {
		this.proveedorRazonSocial = proveedorRazonSocial;
	}

	public String getProveedorRuc() {
		return proveedorRuc;
	}

	public void setProveedorRuc(String proveedorRuc) {
		this.proveedorRuc = proveedorRuc;
	}

	public String getAnio_fiscal() {
		return anio_fiscal;
	}

	public void setAnio_fiscal(String anio_fiscal) {
		this.anio_fiscal = anio_fiscal;
	}

	public String getPuesto() {
		return puesto;
	}

	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}

	public Timestamp getRegistro() {
		return registro;
	}

	public void setRegistro(Timestamp registro) {
		this.registro = registro;
	}

	public String getTipoDoc() {
		return tipoDoc;
	}

	public void setTipoDoc(String tipoDoc) {
		this.tipoDoc = tipoDoc;
	}

	public String getNroDoc() {
		return nroDoc;
	}

	public void setNroDoc(String nroDoc) {
		this.nroDoc = nroDoc;
	}

}