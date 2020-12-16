package pe.com.gesadmin.managed;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import pe.com.gesadmin.entity.transfer.ReporteCuatro;
import pe.com.gesadmin.entity.transfer.ReporteCuatroAnulado;
import pe.com.gesadmin.service.ReporteService;
import pe.com.gesadmin.service.impl.ReporteServiceImpl;
import pe.com.gesadmin.util.UtilFechas;

@ManagedBean
@ViewScoped
public class ReporteCuatroBean {

	private List<ReporteCuatro> lista = new ArrayList<>();
	private List<ReporteCuatroAnulado> listaAnulado = new ArrayList<>();
	private Date fechaConsulta;
	private Date fechaMaxima;

	@EJB
	private ReporteService servicio = new ReporteServiceImpl();


	public ReporteCuatroBean() {
		// TODO Auto-generated constructor stub
		fechaConsulta = null;
		fechaMaxima = new Date();
		
		listaAnulado= new ArrayList<>();
		lista = new ArrayList<>();
	}

	public List<ReporteCuatro> getLista() {
		return lista;
	}

	public void setLista(List<ReporteCuatro> lista) {
		this.lista = lista;
	}

	public void setServicio(ReporteService servicio) {
		this.servicio = servicio;
	}

	public List<ReporteCuatroAnulado> getListaAnulado() {
		return listaAnulado;
	}

	public void setListaAnulado(List<ReporteCuatroAnulado> listaAnulado) {
		this.listaAnulado = listaAnulado;
	}

	public Date getFechaMaxima() {
		return fechaMaxima;
	}

	public void setFechaMaxima(Date fechaMaxima) {
		this.fechaMaxima = fechaMaxima;
	}

	public Date getFechaConsulta() {
		return fechaConsulta;
	}

	public void setFechaConsulta(Date fechaConsulta) {
		this.fechaConsulta = fechaConsulta;
	}

	public void limpiar() {

		lista = null;
		listaAnulado = null;
		fechaConsulta = new Date();
		
	}

	public String consultar() {
		
		UtilFechas utilFechas = new UtilFechas();

		lista = new ArrayList<>();
		listaAnulado = new ArrayList<>();
		
		String fechaCadena = utilFechas.converDateToString(fechaConsulta);

		try {
			lista = servicio.obtenerReporteCuatroDiaNoAnulados(fechaCadena);
			listaAnulado = servicio.obtenerReporteCuatroDiaAnulados(fechaCadena);
		} catch (Exception e) {
			// TODO: handle exception
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error.", "Problemas al recuperar registros"));
			limpiar();

			return "";
		}

		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito.", "Registros encontrados"));

		return "";

	}

}
