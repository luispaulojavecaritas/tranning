package pe.com.gesadmin.entity.transfer;

public class ReporteCuatro {

	private String fechaPagoDate;
	private Integer idTipoOperacion;
	private Integer idCategoriaOperacion;
	private String fechaPagoCadena;
	private String descripcionTipoOperacion;
	private String tipoDocumento;
	private String nroDocumento;
	private String descripcionCategoriaOperacion;
	private String periodoAnioFiscal;
	private String descripcionPuesto;
	private String persona;
	private Double montoIngreso;
	private Double montoEgreso;
	private Double montoSaldo;

	public ReporteCuatro() {
		// TODO Auto-generated constructor stub
	}

	public String getFechaPagoDate() {
		return fechaPagoDate;
	}

	public void setFechaPagoDate(String fechaPagoDate) {
		this.fechaPagoDate = fechaPagoDate;
	}

	public Integer getIdTipoOperacion() {
		return idTipoOperacion;
	}

	public void setIdTipoOperacion(Integer idTipoOperacion) {
		this.idTipoOperacion = idTipoOperacion;
	}

	public Integer getIdCategoriaOperacion() {
		return idCategoriaOperacion;
	}

	public void setIdCategoriaOperacion(Integer idCategoriaOperacion) {
		this.idCategoriaOperacion = idCategoriaOperacion;
	}

	public String getFechaPagoCadena() {
		return fechaPagoCadena;
	}

	public void setFechaPagoCadena(String fechaPagoCadena) {
		this.fechaPagoCadena = fechaPagoCadena;
	}

	public String getDescripcionTipoOperacion() {
		return descripcionTipoOperacion;
	}

	public void setDescripcionTipoOperacion(String descripcionTipoOperacion) {
		this.descripcionTipoOperacion = descripcionTipoOperacion;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getNroDocumento() {
		return nroDocumento;
	}

	public void setNroDocumento(String nroDocumento) {
		this.nroDocumento = nroDocumento;
	}

	public String getDescripcionCategoriaOperacion() {
		return descripcionCategoriaOperacion;
	}

	public void setDescripcionCategoriaOperacion(String descripcionCategoriaOperacion) {
		this.descripcionCategoriaOperacion = descripcionCategoriaOperacion;
	}

	public String getPeriodoAnioFiscal() {
		return periodoAnioFiscal;
	}

	public void setPeriodoAnioFiscal(String periodoAnioFiscal) {
		this.periodoAnioFiscal = periodoAnioFiscal;
	}

	public String getDescripcionPuesto() {
		return descripcionPuesto;
	}

	public void setDescripcionPuesto(String descripcionPuesto) {
		this.descripcionPuesto = descripcionPuesto;
	}

	public String getPersona() {
		return persona;
	}

	public void setPersona(String persona) {
		this.persona = persona;
	}

	public Double getMontoIngreso() {
		return montoIngreso;
	}

	public void setMontoIngreso(Double montoIngreso) {
		this.montoIngreso = montoIngreso;
	}

	public Double getMontoEgreso() {
		return montoEgreso;
	}

	public void setMontoEgreso(Double montoEgreso) {
		this.montoEgreso = montoEgreso;
	}

	public Double getMontoSaldo() {
		return montoSaldo;
	}

	public void setMontoSaldo(Double montoSaldo) {
		this.montoSaldo = montoSaldo;
	}

	@Override
	public String toString() {
		return "ReporteCuatro [fechaPagoDate=" + fechaPagoDate + ", idTipoOperacion=" + idTipoOperacion
				+ ", idCategoriaOperacion=" + idCategoriaOperacion + ", fechaPagoCadena=" + fechaPagoCadena
				+ ", descripcionTipoOperacion=" + descripcionTipoOperacion + ", tipoDocumento=" + tipoDocumento
				+ ", nroDocumento=" + nroDocumento + ", descripcionCategoriaOperacion=" + descripcionCategoriaOperacion
				+ ", periodoAnioFiscal=" + periodoAnioFiscal + ", descripcionPuesto=" + descripcionPuesto + ", persona="
				+ persona + ", montoIngreso=" + montoIngreso + ", montoEgreso=" + montoEgreso + ", montoSaldo="
				+ montoSaldo + "]";
	}

}
