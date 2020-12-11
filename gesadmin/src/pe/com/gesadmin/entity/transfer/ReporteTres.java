package pe.com.gesadmin.entity.transfer;

public class ReporteTres {

	private Integer idAnioFiscal;
	private String descripcionAnioFiscal;
	private Integer idPeriodo;
	private String descripcionPeriodo;
	private Integer idCategoriaOperacion;
	private String descripcionCategoriaOperacion;
	private String fecha;
	private String descripcionDocumento;
	private String nroDocumento;
	private Double ingreso;
	private Double egreso;
	private Double saldo;

	public ReporteTres() {
		// TODO Auto-generated constructor stub
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

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getDescripcionDocumento() {
		return descripcionDocumento;
	}

	public void setDescripcionDocumento(String descripcionDocumento) {
		this.descripcionDocumento = descripcionDocumento;
	}

	public String getNroDocumento() {
		return nroDocumento;
	}

	public void setNroDocumento(String nroDocumento) {
		this.nroDocumento = nroDocumento;
	}

	public Double getIngreso() {
		return ingreso;
	}

	public void setIngreso(Double ingreso) {
		this.ingreso = ingreso;
	}

	public Double getEgreso() {
		return egreso;
	}

	public void setEgreso(Double egreso) {
		this.egreso = egreso;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	@Override
	public String toString() {
		return "ReporteTres [idAnioFiscal=" + idAnioFiscal + ", descripcionAnioFiscal=" + descripcionAnioFiscal
				+ ", idPeriodo=" + idPeriodo + ", descripcionPeriodo=" + descripcionPeriodo + ", idCategoriaOperacion="
				+ idCategoriaOperacion + ", descripcionCategoriaOperacion=" + descripcionCategoriaOperacion + ", fecha="
				+ fecha + ", descripcionDocumento=" + descripcionDocumento + ", nroDocumento=" + nroDocumento
				+ ", ingreso=" + ingreso + ", egreso=" + egreso + ", saldo=" + saldo + "]";
	}

}
