package pe.com.gesadmin.entity.transfer;

public class ReporteComprobanteCorreccion {

	private String id;
	private String fechaTexto;
	private String usuarioRegistro;
	private String monto;
	private String montoLiteral;
	private String motivo;
	private String tipoOperacion;
	private String categoriaOperacion;
	private String puestoOperacion;
	private String tipoComprobante;
	private String nroComprobante;

	public ReporteComprobanteCorreccion() {
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTipoComprobante() {
		return tipoComprobante;
	}

	public void setTipoComprobante(String tipoComprobante) {
		this.tipoComprobante = tipoComprobante;
	}

	public String getNroComprobante() {
		return nroComprobante;
	}

	public void setNroComprobante(String nroComprobante) {
		this.nroComprobante = nroComprobante;
	}

	public String getFechaTexto() {
		return fechaTexto;
	}

	public void setFechaTexto(String fechaTexto) {
		this.fechaTexto = fechaTexto;
	}

	public String getUsuarioRegistro() {
		return usuarioRegistro;
	}

	public void setUsuarioRegistro(String usuarioRegistro) {
		this.usuarioRegistro = usuarioRegistro;
	}

	public String getMonto() {
		return monto;
	}

	public void setMonto(String monto) {
		this.monto = monto;
	}

	public String getMontoLiteral() {
		return montoLiteral;
	}

	public void setMontoLiteral(String montoLiteral) {
		this.montoLiteral = montoLiteral;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public String getTipoOperacion() {
		return tipoOperacion;
	}

	public void setTipoOperacion(String tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
	}

	public String getCategoriaOperacion() {
		return categoriaOperacion;
	}

	public void setCategoriaOperacion(String categoriaOperacion) {
		this.categoriaOperacion = categoriaOperacion;
	}

	public String getPuestoOperacion() {
		return puestoOperacion;
	}

	public void setPuestoOperacion(String puestoOperacion) {
		this.puestoOperacion = puestoOperacion;
	}

}
