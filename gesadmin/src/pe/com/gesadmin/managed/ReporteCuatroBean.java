package pe.com.gesadmin.managed;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import pe.com.gesadmin.entity.transfer.ReporteCuatro;
import pe.com.gesadmin.entity.transfer.ReporteCuatroAnulado;
import pe.com.gesadmin.entity.transfer.ReportePruebaCuatro;
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
	
	private boolean booReporte;
	
	JasperPrint reportePrintLocal;

	@EJB
	private ReporteService servicio = new ReporteServiceImpl();


	public ReporteCuatroBean() {
		// TODO Auto-generated constructor stub
		fechaConsulta = null;
		fechaMaxima = new Date();
		
		listaAnulado= new ArrayList<>();
		lista = new ArrayList<>();
		
		booReporte = false;
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

	public boolean isBooReporte() {
		return booReporte;
	}

	public void setBooReporte(boolean booReporte) {
		this.booReporte = booReporte;
	}

	public JasperPrint getReportePrintLocal() {
		return reportePrintLocal;
	}

	public void setReportePrintLocal(JasperPrint reportePrintLocal) {
		this.reportePrintLocal = reportePrintLocal;
	}

	public void limpiar() {

		lista = null;
		listaAnulado = null;
		fechaConsulta = new Date();
		
		booReporte = false;
		
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

		if(lista == null || lista.size() == 0) {
			booReporte = false;
		}else {
			booReporte = true;
		}

		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito.", "Registros encontrados"));

		return "";

	}
	
	
	public String vercertificadoFinal(ActionEvent actionEvent) throws JRException, IOException {
		
		UtilFechas utilFechas = new UtilFechas();
		String absolutePathCerdp = "C:\\Users\\paulo\\Documents\\reportes_sistemas\\reporte_cuatro.jasper";
		 
		 String dia = "";
		 dia = utilFechas.converDateToString2(fechaConsulta);
		 		 		 

		System.out.println("Ruta del reporte: " + absolutePathCerdp);

		// --- MAPEO DE PARAMETROS
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("param_fecha", dia);
		parametros.put("param_subreporte", "C:\\Users\\paulo\\Documents\\reportes_sistemas\\subuno.jasper");
		
		ReportePruebaCuatro reportePruebaCuatro =  new ReportePruebaCuatro();
		reportePruebaCuatro.setListaReporte(lista);
		reportePruebaCuatro.setListaReporteAnulado(listaAnulado);
		
		List<ReportePruebaCuatro> listaPruebaReporteCuatro = new ArrayList<>();
		listaPruebaReporteCuatro.add(reportePruebaCuatro);

		// --- CARGA DE DATOS EN LA COLECCION
		JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(listaPruebaReporteCuatro);
		System.out.println();

		try {
			reportePrintLocal = JasperFillManager.fillReport(absolutePathCerdp, parametros, beanCollectionDataSource);
		} catch (JRException e) {
			// TODO Auto-generated catch block
			System.out
					.println("Error en creacion instancia jasperPrint: " + e.toString() + " TRACE: " + e.getStackTrace());
			e.printStackTrace();
			reportePrintLocal = null;
		}	
		
		
		if(reportePrintLocal == null) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error.", "Problemas al generar reporte"));
			return "";
		}

		try {
			HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance()
					.getExternalContext().getResponse();
			httpServletResponse.addHeader("Content-disposition", "attachment; filename=cerdp_online.pdf");

			byte[] fichero = JasperExportManager.exportReportToPdf(reportePrintLocal);

			httpServletResponse.setContentType("application/pdf");
			httpServletResponse.setHeader("Content-disposition", "inline; filename=estado_de_cuenta.pdf");
			httpServletResponse.setHeader("Cache-Control", "max-age=30");
			httpServletResponse.setHeader("Pragma", "No-cache");
			httpServletResponse.setDateHeader("Expires", 0);
			httpServletResponse.setContentLength(fichero.length);

			ServletOutputStream out;
			out = httpServletResponse.getOutputStream();

			out.write(fichero, 0, fichero.length);
			out.flush();
			out.close();

			FacesContext.getCurrentInstance().responseComplete();
			return "";
		} catch (Exception e) {
			System.out.println("Error en responder vista: " + e.toString());

			FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error en mostrar reporte", "");
			FacesContext.getCurrentInstance().addMessage(null, fm);
			return "";
		}

	}	

}
