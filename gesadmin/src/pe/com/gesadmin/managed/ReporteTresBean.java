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
import pe.com.gesadmin.entity.Puesto;
import pe.com.gesadmin.entity.PuestoPersonaCargo;
import pe.com.gesadmin.entity.CategoriaOperacion;
import pe.com.gesadmin.entity.transfer.ReportePruebaTres;
import pe.com.gesadmin.entity.transfer.ReporteTres;
import pe.com.gesadmin.service.PuestoService;
import pe.com.gesadmin.service.CategoriaOperacionService;
import pe.com.gesadmin.service.PuestoPersonaCargoService;
import pe.com.gesadmin.service.ReporteService;
import pe.com.gesadmin.service.impl.PuestoServiceImpl;
import pe.com.gesadmin.service.impl.CategoriaOperacionServiceImpl;
import pe.com.gesadmin.service.impl.PuestoPersonaCargoServiceImpl;
import pe.com.gesadmin.service.impl.ReporteServiceImpl;

@ManagedBean
@ViewScoped
public class ReporteTresBean {

	private List<ReporteTres> lista = new ArrayList<>();
	private List<ReporteTres> listafiltro;
	private ReporteTres entidad = new ReporteTres();
	private Integer idPuesto;
	private Integer idCategoriaOperacion;

	private boolean booReporte;
	JasperPrint reportePrintLocal;

	private String filtro;

	private List<Puesto> listaPuesto = new ArrayList<>();
	private List<CategoriaOperacion> listaCategoriaOperacion = new ArrayList<>();

	@EJB
	private ReporteService servicio = new ReporteServiceImpl();
	@EJB
	private PuestoService puestoService = new PuestoServiceImpl();
	@EJB
	private CategoriaOperacionService categoriaOperacionService = new CategoriaOperacionServiceImpl();
	@EJB
	private PuestoPersonaCargoService puestoPersonaCargoService = new PuestoPersonaCargoServiceImpl();

	public ReporteTresBean() {
		// TODO Auto-generated constructor stub
		filtro = null;
		entidad = new ReporteTres();
		idPuesto = null;
		idCategoriaOperacion = null;

		booReporte = false;
	}

	@PostConstruct
	public void init() {
		listarPuestoes();
		listarCategoriaOperaciones();
	}

	public List<ReporteTres> getLista() {
		return lista;
	}

	public void setLista(List<ReporteTres> lista) {
		this.lista = lista;
	}

	public List<ReporteTres> getListafiltro() {
		return listafiltro;
	}

	public void setListafiltro(List<ReporteTres> listafiltro) {
		this.listafiltro = listafiltro;
	}

	public ReporteTres getEntidad() {
		return entidad;
	}

	public void setEntidad(ReporteTres entidad) {
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

	public Integer getIdPuesto() {
		return idPuesto;
	}

	public void setIdPuesto(Integer idPuesto) {
		this.idPuesto = idPuesto;
	}

	public Integer getIdCategoriaOperacion() {
		return idCategoriaOperacion;
	}

	public void setIdCategoriaOperacion(Integer idCategoriaOperacion) {
		this.idCategoriaOperacion = idCategoriaOperacion;
	}

	public List<Puesto> getListaPuesto() {
		return listaPuesto;
	}

	public void setListaPuesto(List<Puesto> listaPuesto) {
		this.listaPuesto = listaPuesto;
	}

	public List<CategoriaOperacion> getListaCategoriaOperacion() {
		return listaCategoriaOperacion;
	}

	public void setListaCategoriaOperacion(List<CategoriaOperacion> listaCategoriaOperacion) {
		this.listaCategoriaOperacion = listaCategoriaOperacion;
	}

	public void setPuestoService(PuestoService puestoService) {
		this.puestoService = puestoService;
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

	public void setPuestoPersonaCargoService(PuestoPersonaCargoService puestoPersonaCargoService) {
		this.puestoPersonaCargoService = puestoPersonaCargoService;
	}

	public void listarPuestoes() {
		listaPuesto = new ArrayList<>();

		try {
			listaPuesto = puestoService.listarFiltro(true);
		} catch (Exception e) {
			// TODO: handle exception
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error.",
					"Problemas al recuperar lista de años fiscales"));
		}
	}

	public void listarCategoriaOperaciones() {

		listaCategoriaOperacion = new ArrayList<>();
		try {
			listaCategoriaOperacion = categoriaOperacionService.listarPordTipoOperacion(1);
		} catch (Exception e) {
			// TODO: handle exception
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error.",
					"Problemas al recuperar lista de categorias de operaciones"));
		}
	}

	public void limpiar() {
		entidad = new ReporteTres();

		lista = null;
		listafiltro = null;
		idPuesto = null;
		idCategoriaOperacion = null;

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
			if (lista.get(i).getDescripcionCategoriaOperacion().contains(filtro)
					|| lista.get(i).getDescripcionAnioFiscal().contains(filtro)) {
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
				lista = servicio.obtenerReporteTresCategoriaAll(idPuesto);
			} catch (Exception e) {
				// TODO: handle exception
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error.", "Problemas al recuperar registros"));
				limpiar();
				return "";
			}

		} else {
			try {
				lista = servicio.obtenerReporteTresCategoriaOperacionId(idPuesto, idCategoriaOperacion);
			} catch (Exception e) {
				// TODO: handle exception
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error.", "Problemas al recuperar registros"));
				limpiar();
				return "";
			}
		}

		if (lista == null || lista.size() == 0) {
			booReporte = false;
		} else {
			booReporte = true;
		}

		listafiltro = new ArrayList<>();
		listafiltro = lista;

		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito.", "Registros encontrados"));

		return "";

	}

	public String vercertificadoFinal(ActionEvent actionEvent) throws JRException, IOException {

		String absolutePathCerdp = "C:\\Users\\paulo\\Documents\\reportes_sistemas\\reporte_tres.jasper";

		String personaSocio = "";
		String categoriaOperacionDesc = "";

		List<PuestoPersonaCargo> listaPuestoPersonaCargos = new ArrayList<>();

		listaPuestoPersonaCargos = puestoPersonaCargoService.listarPuestoId(idPuesto);

		if (listaPuestoPersonaCargos == null || listaPuestoPersonaCargos.size() == 0) {
			personaSocio = "";
			System.out.println("No se encontraron datos de personas asociadas a los puestos");
		}

		String nombre = (listaPuestoPersonaCargos.get(0).getPersona().getNombre() == null) ? ""
				: listaPuestoPersonaCargos.get(0).getPersona().getNombre();
		String paterno = (listaPuestoPersonaCargos.get(0).getPersona().getPaterno() == null) ? ""
				: listaPuestoPersonaCargos.get(0).getPersona().getPaterno();
		String materno = (listaPuestoPersonaCargos.get(0).getPersona().getMaterno() == null) ? ""
				: listaPuestoPersonaCargos.get(0).getPersona().getMaterno();
		String puesto = (listaPuestoPersonaCargos.get(0).getPuesto().getDescripcion() == null) ? ""
				: listaPuestoPersonaCargos.get(0).getPuesto().getDescripcion();

		personaSocio = puesto + " " + nombre + " " + paterno + " " + materno;

		CategoriaOperacion categoriaOperacionLoc = new CategoriaOperacion();
		if (idCategoriaOperacion != null) {
			categoriaOperacionLoc = categoriaOperacionService.recuperar(idCategoriaOperacion);
			categoriaOperacionDesc = categoriaOperacionLoc.getDescripcion();
		} else {
			categoriaOperacionDesc = "TODAS TODAS LAS CATEGORIAS";
		}

		System.out.println("Ruta del reporte: " + absolutePathCerdp);

		// --- MAPEO DE PARAMETROS
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("param_socio", personaSocio);
		parametros.put("param_categoria", categoriaOperacionDesc);

		ReportePruebaTres ReportePruebaTres = new ReportePruebaTres();
		ReportePruebaTres.setListaReporte(lista);

		List<ReportePruebaTres> listaPruebaReporteTres = new ArrayList<>();
		listaPruebaReporteTres.add(ReportePruebaTres);

		// --- CARGA DE DATOS EN LA COLECCION
		JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(listaPruebaReporteTres);
		System.out.println();

		try {
			reportePrintLocal = JasperFillManager.fillReport(absolutePathCerdp, parametros, beanCollectionDataSource);
		} catch (JRException e) {
			// TODO Auto-generated catch block
			System.out.println(
					"Error en creacion instancia jasperPrint: " + e.toString() + " TRACE: " + e.getStackTrace());
			e.printStackTrace();
			reportePrintLocal = null;
		}

		if (reportePrintLocal == null) {
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
