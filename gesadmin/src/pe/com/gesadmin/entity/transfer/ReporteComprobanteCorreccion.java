package pe.com.gesadmin.entity.transfer;

public class ReporteComprobanteCorreccion {

	private Integer id;
	private String fechaTexto;
	private String usuarioRegistro;
	private Double monto;
	private String montoLiteral;
	private String motivo;
	private String tipoOperacion;
	private String categoriaOperacion;
	private String puestoOperacion;

	public ReporteComprobanteCorreccion() {
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Double getMonto() {
		return monto;
	}

	public void setMonto(Double monto) {
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
