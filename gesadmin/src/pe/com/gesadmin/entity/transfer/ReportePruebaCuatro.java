package pe.com.gesadmin.entity.transfer;

import java.util.List;

public class ReportePruebaCuatro {
	
	private List<ReporteCuatro> listaReporte;
	
	private List<ReporteCuatroAnulado> listaReporteAnulado;

	public ReportePruebaCuatro() {
		// TODO Auto-generated constructor stub
	}

	public List<ReporteCuatro> getListaReporte() {
		return listaReporte;
	}

	public void setListaReporte(List<ReporteCuatro> listaReporte) {
		this.listaReporte = listaReporte;
	}

	public List<ReporteCuatroAnulado> getListaReporteAnulado() {
		return listaReporteAnulado;
	}

	public void setListaReporteAnulado(List<ReporteCuatroAnulado> listaReporteAnulado) {
		this.listaReporteAnulado = listaReporteAnulado;
	}
	

}
