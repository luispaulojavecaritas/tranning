package pe.com.gesadmin.entity.transfer;


public class OperacionAdministracionTransfer {

	private Integer anioFiscalId;
	private String anioFiscalDes;
	
	private Integer periodoFiscalId;
	private String periodoFiscalDes;
	
	
	private Integer puestoId;
	private String puestoDes;
	
	private Integer tipoOperacionId;
	private String tipoOperacionDes;

	private Integer categoriaId;
	private String categoriaDes;
	
	private Integer estatusOperacionId;
	private String estatusOperacionDes;	
	
	private String fechaVencimiento;

	private Double montoUnitario;
	private Double montoTotal;
	private Integer dias;

	private Integer idProveedorAdministracion;	
	private Integer idPeriodo;

	public OperacionAdministracionTransfer() {
		// TODO Auto-generated constructor stub
	}

	public OperacionAdministracionTransfer(Integer puestoId, String puestoDes, Integer categoriaId, String categoriaDes,
			Double montoTotal, Integer dias) {
		this.puestoId = puestoId;
		this.puestoDes = puestoDes;
		this.categoriaId = categoriaId;
		this.categoriaDes = categoriaDes;
		this.montoTotal = montoTotal;
		this.dias = dias;
	}

	public Integer getAnioFiscalId() {
		return anioFiscalId;
	}

	public void setAnioFiscalId(Integer anioFiscalId) {
		this.anioFiscalId = anioFiscalId;
	}

	public String getAnioFiscalDes() {
		return anioFiscalDes;
	}

	public void setAnioFiscalDes(String anioFiscalDes) {
		this.anioFiscalDes = anioFiscalDes;
	}

	public Integer getPeriodoFiscalId() {
		return periodoFiscalId;
	}

	public void setPeriodoFiscalId(Integer periodoFiscalId) {
		this.periodoFiscalId = periodoFiscalId;
	}

	public String getPeriodoFiscalDes() {
		return periodoFiscalDes;
	}

	public void setPeriodoFiscalDes(String periodoFiscalDes) {
		this.periodoFiscalDes = periodoFiscalDes;
	}

	public Integer getPuestoId() {
		return puestoId;
	}

	public void setPuestoId(Integer puestoId) {
		this.puestoId = puestoId;
	}

	public String getPuestoDes() {
		return puestoDes;
	}

	public void setPuestoDes(String puestoDes) {
		this.puestoDes = puestoDes;
	}

	public Integer getTipoOperacionId() {
		return tipoOperacionId;
	}

	public void setTipoOperacionId(Integer tipoOperacionId) {
		this.tipoOperacionId = tipoOperacionId;
	}

	public String getTipoOperacionDes() {
		return tipoOperacionDes;
	}

	public void setTipoOperacionDes(String tipoOperacionDes) {
		this.tipoOperacionDes = tipoOperacionDes;
	}

	public Integer getCategoriaId() {
		return categoriaId;
	}

	public void setCategoriaId(Integer categoriaId) {
		this.categoriaId = categoriaId;
	}

	public String getCategoriaDes() {
		return categoriaDes;
	}

	public void setCategoriaDes(String categoriaDes) {
		this.categoriaDes = categoriaDes;
	}

	public Integer getEstatusOperacionId() {
		return estatusOperacionId;
	}

	public void setEstatusOperacionId(Integer estatusOperacionId) {
		this.estatusOperacionId = estatusOperacionId;
	}

	public String getEstatusOperacionDes() {
		return estatusOperacionDes;
	}

	public void setEstatusOperacionDes(String estatusOperacionDes) {
		this.estatusOperacionDes = estatusOperacionDes;
	}

	public String getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(String fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public Double getMontoUnitario() {
		return montoUnitario;
	}

	public void setMontoUnitario(Double montoUnitario) {
		this.montoUnitario = montoUnitario;
	}

	public Double getMontoTotal() {
		return montoTotal;
	}

	public void setMontoTotal(Double montoTotal) {
		this.montoTotal = montoTotal;
	}

	public Integer getDias() {
		return dias;
	}

	public void setDias(Integer dias) {
		this.dias = dias;
	}

	public Integer getIdProveedorAdministracion() {
		return idProveedorAdministracion;
	}

	public void setIdProveedorAdministracion(Integer idProveedorAdministracion) {
		this.idProveedorAdministracion = idProveedorAdministracion;
	}

	public Integer getIdPeriodo() {
		return idPeriodo;
	}

	public void setIdPeriodo(Integer idPeriodo) {
		this.idPeriodo = idPeriodo;
	}

	@Override
	public String toString() {
		return "OperacionAdministracionTransfer [anioFiscalId=" + anioFiscalId + ", anioFiscalDes=" + anioFiscalDes
				+ ", periodoFiscalId=" + periodoFiscalId + ", periodoFiscalDes=" + periodoFiscalDes + ", puestoId="
				+ puestoId + ", puestoDes=" + puestoDes + ", tipoOperacionId=" + tipoOperacionId + ", tipoOperacionDes="
				+ tipoOperacionDes + ", categoriaId=" + categoriaId + ", categoriaDes=" + categoriaDes
				+ ", estatusOperacionId=" + estatusOperacionId + ", estatusOperacionDes=" + estatusOperacionDes
				+ ", fechaVencimiento=" + fechaVencimiento + ", montoUnitario=" + montoUnitario + ", montoTotal="
				+ montoTotal + ", dias=" + dias + ", idProveedorAdministracion=" + idProveedorAdministracion
				+ ", idPeriodo=" + idPeriodo + "]";
	}
}
