package pe.com.gesadmin.entity.transfer;

public class ReporteDos {

	private Integer idPuesto;
	private String descripcionPuesto;
	private Integer idAnioFiscal;
	private String descripcionAnioFiscal;
	private Integer idPeriodo;
	private String descripcionPeriodo;
	private String anioFiscalPeriodo;
	private Integer idCategoriaOperacion;
	private String descripcionCategoriaOperacion;
	private Double montoDeuda;
	private Double montoPagado;
	private Double saldoDeuda;

	public ReporteDos() {
		// TODO Auto-generated constructor stub
	}

	public Integer getIdPuesto() {
		return idPuesto;
	}

	public void setIdPuesto(Integer idPuesto) {
		this.idPuesto = idPuesto;
	}

	public String getDescripcionPuesto() {
		return descripcionPuesto;
	}

	public void setDescripcionPuesto(String descripcionPuesto) {
		this.descripcionPuesto = descripcionPuesto;
	}

	public Integer getIdAnioFiscal() {
		return idAnioFiscal;
	}

	public void setIdAnioFiscal(Integer idAnioFiscal) {
		this.idAnioFiscal = idAnioFiscal;
	}

	public String getDescripcionAnioFiscal() {
		return descripcionAnioFiscal;
	}

	public void setDescripcionAnioFiscal(String descripcionAnioFiscal) {
		this.descripcionAnioFiscal = descripcionAnioFiscal;
	}

	public Integer getIdPeriodo() {
		return idPeriodo;
	}

	public void setIdPeriodo(Integer idPeriodo) {
		this.idPeriodo = idPeriodo;
	}

	public String getDescripcionPeriodo() {
		return descripcionPeriodo;
	}

	public void setDescripcionPeriodo(String descripcionPeriodo) {
		this.descripcionPeriodo = descripcionPeriodo;
	}

	public String getAnioFiscalPeriodo() {
		return anioFiscalPeriodo;
	}

	public void setAnioFiscalPeriodo(String anioFiscalPeriodo) {
		this.anioFiscalPeriodo = anioFiscalPeriodo;
	}

	public Integer getIdCategoriaOperacion() {
		return idCategoriaOperacion;
	}

	public void setIdCategoriaOperacion(Integer idCategoriaOperacion) {
		this.idCategoriaOperacion = idCategoriaOperacion;
	}

	public String getDescripcionCategoriaOperacion() {
		return descripcionCategoriaOperacion;
	}

	public void setDescripcionCategoriaOperacion(String descripcionCategoriaOperacion) {
		this.descripcionCategoriaOperacion = descripcionCategoriaOperacion;
	}

	public Double getMontoDeuda() {
		return montoDeuda;
	}

	public void setMontoDeuda(Double montoDeuda) {
		this.montoDeuda = montoDeuda;
	}

	public Double getMontoPagado() {
		return montoPagado;
	}

	public void setMontoPagado(Double montoPagado) {
		this.montoPagado = montoPagado;
	}

	public Double getSaldoDeuda() {
		return saldoDeuda;
	}

	public void setSaldoDeuda(Double saldoDeuda) {
		this.saldoDeuda = saldoDeuda;
	}

	@Override
	public String toString() {
		return "ReporteDos [idPuesto=" + idPuesto + ", descripcionPuesto=" + descripcionPuesto + ", idAnioFiscal="
				+ idAnioFiscal + ", descripcionAnioFiscal=" + descripcionAnioFiscal + ", idPeriodo=" + idPeriodo
				+ ", descripcionPeriodo=" + descripcionPeriodo + ", anioFiscalPeriodo=" + anioFiscalPeriodo
				+ ", idCategoriaOperacion=" + idCategoriaOperacion + ", descripcionCategoriaOperacion="
				+ descripcionCategoriaOperacion + ", montoDeuda=" + montoDeuda + ", montoPagado=" + montoPagado
				+ ", saldoDeuda=" + saldoDeuda + "]";
	}

}
