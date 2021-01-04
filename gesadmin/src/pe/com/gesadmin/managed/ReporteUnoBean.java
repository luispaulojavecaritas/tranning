package pe.com.gesadmin.managed;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
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
import pe.com.gesadmin.entity.AnioFiscal;
import pe.com.gesadmin.entity.CategoriaOperacion;
import pe.com.gesadmin.entity.transfer.ReportePruebaUno;
import pe.com.gesadmin.entity.transfer.ReporteUno;
import pe.com.gesadmin.service.AnioFiscalService;
import pe.com.gesadmin.service.CategoriaOperacionService;
import pe.com.gesadmin.service.ReporteService;
import pe.com.gesadmin.service.impl.AnioFiscalServiceImpl;
import pe.com.gesadmin.service.impl.CategoriaOperacionServiceImpl;
import pe.com.gesadmin.service.impl.ReporteServiceImpl;
import pe.com.gesadmin.util.Constante;

@ManagedBean
@ViewScoped
public class ReporteUnoBean {

	private List<ReporteUno> lista = new ArrayList<>();
	private List<ReporteUno> listafiltro;
	private ReporteUno entidad = new ReporteUno();
	private Integer idAnioFiscal;
	private Integer idCategoriaOperacion;

	private String filtro;
	private boolean booReporte;
	
	JasperPrint reportePrintLocal;

	private List<AnioFiscal> listaAnioFiscal = new ArrayList<>();
	private List<CategoriaOperacion> listaCategoriaOperacion = new ArrayList<>();

	@EJB
	private ReporteService servicio = new ReporteServiceImpl();
	@EJB
	private AnioFiscalService anioFiscalService = new AnioFiscalServiceImpl();
	@EJB
	private CategoriaOperacionService categoriaOperacionService = new CategoriaOperacionServiceImpl();

	public ReporteUnoBean() {
		// TODO Auto-generated constructor stub
		filtro = null;
		entidad = new ReporteUno();
		idAnioFiscal = null;
		idCategoriaOperacion = null;
		booReporte = false;
		reportePrintLocal = null;
	}

	@PostConstruct
	public void init() {
		listarAnioFiscales();
		listarCategoriaOperaciones();
		
	}

	public List<ReporteUno> getLista() {
		return lista;
	}

	public void setLista(List<ReporteUno> lista) {
		this.lista = lista;
	}

	public List<ReporteUno> getListafiltro() {
		return listafiltro;
	}

	public void setListafiltro(List<ReporteUno> listafiltro) {
		this.listafiltro = listafiltro;
	}

	public ReporteUno getEntidad() {
		return entidad;
	}

	public void setEntidad(ReporteUno entidad) {
		this.entidad = entidad;
	}

	public void setServicio(ReporteService servicio) {
		this.servicio = servicio;
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

	public Integer getIdAnioFiscal() {
		return idAnioFiscal;
	}

	public void setIdAnioFiscal(Integer idAnioFiscal) {
		this.idAnioFiscal = idAnioFiscal;
	}

	public Integer getIdCategoriaOperacion() {
		return idCategoriaOperacion;
	}

	public void setIdCategoriaOperacion(Integer idCategoriaOperacion) {
		this.idCategoriaOperacion = idCategoriaOperacion;
	}

	public List<AnioFiscal> getListaAnioFiscal() {
		return listaAnioFiscal;
	}

	public void setListaAnioFiscal(List<AnioFiscal> listaAnioFiscal) {
		this.listaAnioFiscal = listaAnioFiscal;
	}

	public List<CategoriaOperacion> getListaCategoriaOperacion() {
		return listaCategoriaOperacion;
	}

	public void setListaCategoriaOperacion(List<CategoriaOperacion> listaCategoriaOperacion) {
		this.listaCategoriaOperacion = listaCategoriaOperacion;
	}

	public void setAnioFiscalService(AnioFiscalService anioFiscalService) {
		this.anioFiscalService = anioFiscalService;
	}

	public void setCategoriaOperacionService(CategoriaOperacionService categoriaOperacionService) {
		this.categoriaOperacionService = categoriaOperacionService;
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

	public void listarAnioFiscales() {
		listaAnioFiscal = new ArrayList<>();

		try {
			listaAnioFiscal = anioFiscalService.listar();
		} catch (Exception e) {
			// TODO: handle exception
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Problemas al recuperar lista de años fiscales",
					"Error"));
		}
	}

	public void listarCategoriaOperaciones() {

		listaCategoriaOperacion = new ArrayList<>();
		try {
			listaCategoriaOperacion = categoriaOperacionService.listarPordTipoOperacion(1);
		} catch (Exception e) {
			// TODO: handle exception
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Problemas al recuperar lista de conceptos",
					"Error"));
		}
	}

	public void limpiar() {
		entidad = new ReporteUno();

		lista = null;
		listafiltro = null;
		idAnioFiscal = null;
		idCategoriaOperacion = null;
		
		reportePrintLocal = null;
		booReporte = false;
	}

	public String filtrar() {

		if (lista == null || lista.isEmpty() || lista.size() == 0) {
			filtro = null;
			return "";
		}

		listafiltro = null;
		listafiltro = new ArrayList<>();
		System.out.println("Texto a filtra: " + filtro);
		for (int i = 0; i <= lista.size() - 1; i++) {
			if (lista.get(i).getDescripcionPuesto().contains(filtro) || lista.get(i).getPersona().contains(filtro)) {
				System.out.println("lista: " + lista.get(i).toString());
				listafiltro.add(lista.get(i));
			}
		}
		filtro = null;
		return "";
	}

	public String consultar() {

		lista = new ArrayList<>();

		if (idCategoriaOperacion == null) {

			try {
				lista = servicio.obtenerReporteUnoCategoriaAll(idAnioFiscal);
			} catch (Exception e) {
				// TODO: handle exception
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Problemas al recuperar registros", "Error"));
				limpiar();
				return "";
			}

		} else {
			try {
				lista = servicio.obtenerReporteUnoCategoriaOperacionId(idAnioFiscal, idCategoriaOperacion);
			} catch (Exception e) {
				// TODO: handle exception
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Problemas al recuperar registros", "Error"));
				limpiar();
				return "";
			}
		}
		
		if(lista == null || lista.size() == 0) {
			booReporte = false;
		}else {
			booReporte = true;
		}
		
		listafiltro = new ArrayList<>();
		listafiltro = lista;

		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Problemas al recuperar registros", "Error"));

		return "";

	}

	
	public String vercertificadoFinal(ActionEvent actionEvent) throws JRException, IOException {
		

		String absolutePathCerdp = Constante.RUTA_REPORTES+"reporte_uno.jasper";
		 
		 String anioFiscalDesc = "";
		 String categoriaOperacionDesc = "";
	
		 
		 AnioFiscal anioFiscalLoc = new AnioFiscal();
		 anioFiscalLoc = anioFiscalService.recuperar(idAnioFiscal);
		 anioFiscalDesc = anioFiscalLoc.getDescripcion();
		 
		 CategoriaOperacion categoriaOperacionLoc = new CategoriaOperacion();
		 if(idCategoriaOperacion != null) {
			 categoriaOperacionLoc = categoriaOperacionService.recuperar(idCategoriaOperacion);
			 categoriaOperacionDesc = categoriaOperacionLoc.getDescripcion();
		 }else {
			 categoriaOperacionDesc = "TODAS TODAS LAS CATEGORIAS";
		 }
		 		 

		System.out.println("Ruta del reporte: " + absolutePathCerdp);

		// --- MAPEO DE PARAMETROS
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("param_anio", anioFiscalDesc);
		parametros.put("param_categoria", categoriaOperacionDesc);
		
		ReportePruebaUno reportePruebaUno =  new ReportePruebaUno();
		reportePruebaUno.setListaReporte(lista);
		
		List<ReportePruebaUno> listaPruebaReporteUno = new ArrayList<>();
		listaPruebaReporteUno.add(reportePruebaUno);

		// --- CARGA DE DATOS EN LA COLECCION
		JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(listaPruebaReporteUno);
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
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Problemas al generar reporte", "Error"));
			return "";
		}

		try {
			HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance()
					.getExternalContext().getResponse();
			httpServletResponse.addHeader("Content-disposition", "attachment; filename=estado_cuenta_general_socios.pdf");

			byte[] fichero = JasperExportManager.exportReportToPdf(reportePrintLocal);

			httpServletResponse.setContentType("application/pdf");
			httpServletResponse.setHeader("Content-disposition", "inline; filename=estado_cuenta_general_socios.pdf");
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

			FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error en mostrar estado de cuenta", "");
			FacesContext.getCurrentInstance().addMessage(null, fm);
			return "";
		}

	}
	
	
	

}
