package pe.com.gesadmin.entity.transfer;

public class ReporteCuatroAnulado {

	private String anuTipoDocumento;
	private String anuNroDocumento;
	private String anuCategoriaOperacion;
	private String anuMotivoAnulacion;
	private String anuTipoOperacion;
	private Double anuImporte;

	public ReporteCuatroAnulado() {
		// TODO Auto-generated constructor stub
	}

	public String getAnuTipoDocumento() {
		return anuTipoDocumento;
	}

	public void setAnuTipoDocumento(String anuTipoDocumento) {
		this.anuTipoDocumento = anuTipoDocumento;
	}

	public String getAnuNroDocumento() {
		return anuNroDocumento;
	}

	public void setAnuNroDocumento(String anuNroDocumento) {
		this.anuNroDocumento = anuNroDocumento;
	}

	public String getAnuCategoriaOperacion() {
		return anuCategoriaOperacion;
	}

	public void setAnuCategoriaOperacion(String anuCategoriaOperacion) {
		this.anuCategoriaOperacion = anuCategoriaOperacion;
	}

	public String getAnuMotivoAnulacion() {
		return anuMotivoAnulacion;
	}

	public void setAnuMotivoAnulacion(String anuMotivoAnulacion) {
		this.anuMotivoAnulacion = anuMotivoAnulacion;
	}

	public String getAnuTipoOperacion() {
		return anuTipoOperacion;
	}

	public void setAnuTipoOperacion(String anuTipoOperacion) {
		this.anuTipoOperacion = anuTipoOperacion;
	}

	public Double getAnuImporte() {
		return anuImporte;
	}

	public void setAnuImporte(Double anuImporte) {
		this.anuImporte = anuImporte;
	}

	@Override
	public String toString() {
		return "ReporteCuatroAnulado [anuTipoDocumento=" + anuTipoDocumento + ", anuNroDocumento=" + anuNroDocumento
				+ ", anuCategoriaOperacion=" + anuCategoriaOperacion + ", anuMotivoAnulacion=" + anuMotivoAnulacion
				+ ", anuTipoOperacion=" + anuTipoOperacion + ", anuImporte=" + anuImporte + "]";
	}
	
}
