package pe.com.gesadmin.entity.transfer;

public class ReciboTransfers {

	private String reNrocomprobante;
	private String reTipocomprobante;
	private String reUsuario;
	private String reMonto;
	private String reMotivo;
	private String reMontoDescriocion;
	private String opeId;
	private String opCategoria;
	private String opDescripcion;
	private String opeTipo;
	private String opeEstatus;
	private String opeFechaPago;
	private String opeMonto;
	private String opeAnio;
	private String opePeriodo;
	private String opeFechaRegistro;
	private String opePuesto;
	private String opeObservacion;
	

	public ReciboTransfers() {
		// TODO Auto-generated constructor stub
	}

	public String getReNrocomprobante() {
		return reNrocomprobante;
	}

	public void setReNrocomprobante(String reNrocomprobante) {
		this.reNrocomprobante = reNrocomprobante;
	}

	public String getReTipocomprobante() {
		return reTipocomprobante;
	}

	public void setReTipocomprobante(String reTipocomprobante) {
		this.reTipocomprobante = reTipocomprobante;
	}

	public String getOpeId() {
		return opeId;
	}

	public void setOpeId(String opeId) {
		this.opeId = opeId;
	}

	public String getOpCategoria() {
		return opCategoria;
	}

	public void setOpCategoria(String opCategoria) {
		this.opCategoria = opCategoria;
	}

	public String getOpDescripcion() {
		return opDescripcion;
	}

	public void setOpDescripcion(String opDescripcion) {
		this.opDescripcion = opDescripcion;
	}

	public String getOpeTipo() {
		return opeTipo;
	}

	public void setOpeTipo(String opeTipo) {
		this.opeTipo = opeTipo;
	}

	public String getOpeEstatus() {
		return opeEstatus;
	}

	public void setOpeEstatus(String opeEstatus) {
		this.opeEstatus = opeEstatus;
	}

	public String getOpeFechaPago() {
		return opeFechaPago;
	}

	public void setOpeFechaPago(String opeFechaPago) {
		this.opeFechaPago = opeFechaPago;
	}

	public String getOpeMonto() {
		return opeMonto;
	}

	public void setOpeMonto(String opeMonto) {
		this.opeMonto = opeMonto;
	}

	public String getReUsuario() {
		return reUsuario;
	}

	public void setReUsuario(String reUsuario) {
		this.reUsuario = reUsuario;
	}

	public String getReMonto() {
		return reMonto;
	}

	public void setReMonto(String reMonto) {
		this.reMonto = reMonto;
	}

	public String getReMontoDescriocion() {
		return reMontoDescriocion;
	}

	public void setReMontoDescriocion(String reMontoDescriocion) {
		this.reMontoDescriocion = reMontoDescriocion;
	}

	public String getOpeAnio() {
		return opeAnio;
	}

	public void setOpeAnio(String opeAnio) {
		this.opeAnio = opeAnio;
	}

	public String getOpePeriodo() {
		return opePeriodo;
	}

	public void setOpePeriodo(String opePeriodo) {
		this.opePeriodo = opePeriodo;
	}

	public String getOpeFechaRegistro() {
		return opeFechaRegistro;
	}

	public void setOpeFechaRegistro(String opeFechaRegistro) {
		this.opeFechaRegistro = opeFechaRegistro;
	}

	public String getOpePuesto() {
		return opePuesto;
	}

	public void setOpePuesto(String opePuesto) {
		this.opePuesto = opePuesto;
	}

	public String getReMotivo() {
		return reMotivo;
	}

	public void setReMotivo(String reMotivo) {
		this.reMotivo = reMotivo;
	}

	public String getOpeObservacion() {
		return opeObservacion;
	}

	public void setOpeObservacion(String opeObservacion) {
		this.opeObservacion = opeObservacion;
	}

	@Override
	public String toString() {
		return "ReciboTransfers [reNrocomprobante=" + reNrocomprobante + ", reTipocomprobante=" + reTipocomprobante
				+ ", opeId=" + opeId + ", opCategoria=" + opCategoria + ", opDescripcion=" + opDescripcion
				+ ", opeTipo=" + opeTipo + ", opeEstatus=" + opeEstatus + ", opeFechaPago=" + opeFechaPago
				+ ", opeMonto=" + opeMonto + ", reUsuario=" + reUsuario + ", reMonto=" + reMonto
				+ ", reMontoDescriocion=" + reMontoDescriocion + "]";
	}

}
