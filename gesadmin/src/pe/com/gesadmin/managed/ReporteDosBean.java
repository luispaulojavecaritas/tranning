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
import pe.com.gesadmin.entity.Puesto;
import pe.com.gesadmin.entity.PuestoPersonaCargo;
import pe.com.gesadmin.entity.transfer.ReporteDos;
import pe.com.gesadmin.entity.transfer.ReportePruebaDos;
import pe.com.gesadmin.entity.transfer.ReportePruebaDos;
import pe.com.gesadmin.service.AnioFiscalService;
import pe.com.gesadmin.service.PuestoPersonaCargoService;
import pe.com.gesadmin.service.PuestoService;
import pe.com.gesadmin.service.ReporteService;
import pe.com.gesadmin.service.impl.AnioFiscalServiceImpl;
import pe.com.gesadmin.service.impl.PuestoPersonaCargoServiceImpl;
import pe.com.gesadmin.service.impl.PuestoServiceImpl;
import pe.com.gesadmin.service.impl.ReporteServiceImpl;
import pe.com.gesadmin.util.Constante;

@ManagedBean
@ViewScoped
public class ReporteDosBean {

	private List<ReporteDos> lista = new ArrayList<>();
	private List<ReporteDos> listafiltro;
	private ReporteDos entidad = new ReporteDos();
	private Integer idPuesto;
	private String propietario;
	
	private boolean booReporte;
	
	JasperPrint reportePrintLocal;

	private String filtro;

	private List<AnioFiscal> listaAnioFiscal = new ArrayList<>();
	private List<Puesto> listaPuesto = new ArrayList<>();

	@EJB
	private ReporteService servicio = new ReporteServiceImpl();
	@EJB
	private AnioFiscalService anioFiscalService = new AnioFiscalServiceImpl();
	@EJB
	private PuestoService puestoService = new PuestoServiceImpl();
	@EJB
	private PuestoPersonaCargoService puestoPersonaCargoService = new PuestoPersonaCargoServiceImpl();

	public ReporteDosBean() {
		// TODO Auto-generated constructor stub
		filtro = null;
		entidad = new ReporteDos();
		idPuesto = null;
		
		propietario = "";
		
		booReporte= false;
	}

	@PostConstruct
	public void init() {
		listarAnioFiscales();
		listarPuestoes();
	}

	public List<ReporteDos> getLista() {
		return lista;
	}

	public void setLista(List<ReporteDos> lista) {
		this.lista = lista;
	}

	public List<ReporteDos> getListafiltro() {
		return listafiltro;
	}

	public void setListafiltro(List<ReporteDos> listafiltro) {
		this.listafiltro = listafiltro;
	}

	public ReporteDos getEntidad() {
		return entidad;
	}

	public void setEntidad(ReporteDos entidad) {
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

	public List<AnioFiscal> getListaAnioFiscal() {
		return listaAnioFiscal;
	}

	public void setListaAnioFiscal(List<AnioFiscal> listaAnioFiscal) {
		this.listaAnioFiscal = listaAnioFiscal;
	}

	public List<Puesto> getListaPuesto() {
		return listaPuesto;
	}

	public void setListaPuesto(List<Puesto> listaPuesto) {
		this.listaPuesto = listaPuesto;
	}

	public void setAnioFiscalService(AnioFiscalService anioFiscalService) {
		this.anioFiscalService = anioFiscalService;
	}

	public void setPuestoService(PuestoService puestoService) {
		this.puestoService = puestoService;
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

	public String getPropietario() {
		return propietario;
	}

	public void setPropietario(String propietario) {
		this.propietario = propietario;
	}

	public void listarAnioFiscales() {
		listaAnioFiscal = new ArrayList<>();

		try {
			listaAnioFiscal = anioFiscalService.listar();
		} catch (Exception e) {
			// TODO: handle exception
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error. Problemas al recuperar lista de años fiscales",
					""));
		}
	}

	public void listarPuestoes() {

		listaPuesto = new ArrayList<>();
		try {
			listaPuesto = puestoService.listarFiltro(true);
		} catch (Exception e) {
			// TODO: handle exception
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error. Problemas al recuperar lista de categorias de operaciones",
					"Problemas al recuperar lista de categorias de operaciones"));
		}
	}
	
	public void obtenerPropietario() {
		
		List<PuestoPersonaCargo> lista = new ArrayList<>();
		
		lista= puestoPersonaCargoService.listarPuestoIdPropietarioActivo(idPuesto);
		
		if(lista == null || lista.isEmpty()) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "No hay socio asociado en este puestoError. Problemas al recuperar lista de categorias de operaciones",""));
			
			propietario = "No registrado";
		}else {
			propietario = lista.get(0).getPersona().getNombre() + " " + lista.get(0).getPersona().getPaterno() + " " + lista.get(0).getPersona().getMaterno();
		}
	}

	public void limpiar() {
		entidad = new ReporteDos();

		lista = null;
		listafiltro = null;
		idPuesto = null;
		propietario = "";
		
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
			if (lista.get(i).getDescripcionPuesto().contains(filtro)
					|| lista.get(i).getDescripcionCategoriaOperacion().contains(filtro)) {
				System.out.println("lista: " + lista.get(i).toString());
				listafiltro.add(lista.get(i));
			}
		}
		filtro = null;
		return "";
	}

	public String consultar() {

		lista = new ArrayList<>();
		listafiltro = new ArrayList<>();

		try {
			lista = servicio.obtenerReporteDosPuestoId(idPuesto);
			listafiltro = lista;
		} catch (Exception e) {
			// TODO: handle exception
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error. Problemas al recuperar registros", ""));
			limpiar();

			return "";
		}
		
		if(lista == null || lista.size() == 0) {
			booReporte = false;
		}else {
			booReporte = true;
		}
		
		listafiltro = new ArrayList<>();
		listafiltro = lista;

		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito. Registros encontrados", ""));

		return "";

	}
	
	public String vercertificadoFinal(ActionEvent actionEvent) throws JRException, IOException {
		

		String absolutePathCerdp =  Constante.RUTA_REPORTES+"reporte_dos.jasper";
		 
		 String personaSocio = "";
		 
		 List<PuestoPersonaCargo> listaPuestoPersonaCargos = new ArrayList<>();
		 
		 listaPuestoPersonaCargos = puestoPersonaCargoService.listarPuestoId(idPuesto);
		 
		 if(listaPuestoPersonaCargos == null || listaPuestoPersonaCargos.size()==0) {
			 personaSocio = "";
			 System.out.println("No se encontraron datos de personas asociadas a los puestos");
		 }
		 
		 String nombre = (listaPuestoPersonaCargos.get(0).getPersona().getNombre()==null)?"":listaPuestoPersonaCargos.get(0).getPersona().getNombre();
		 String paterno = (listaPuestoPersonaCargos.get(0).getPersona().getPaterno()==null)?"":listaPuestoPersonaCargos.get(0).getPersona().getPaterno();
		 String materno = (listaPuestoPersonaCargos.get(0).getPersona().getMaterno()==null)?"":listaPuestoPersonaCargos.get(0).getPersona().getMaterno();
		 String puesto = (listaPuestoPersonaCargos.get(0).getPuesto().getDescripcion()==null)?"":listaPuestoPersonaCargos.get(0).getPuesto().getDescripcion();
		 
		 personaSocio = puesto + " " + nombre + " "+ paterno + " " + materno;
		 		 

		System.out.println("Ruta del reporte: " + absolutePathCerdp);

		// --- MAPEO DE PARAMETROS
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("param_socio", personaSocio);
		
		ReportePruebaDos reportePruebaDos =  new ReportePruebaDos();
		reportePruebaDos.setListaReporte(lista);
		
		List<ReportePruebaDos> listaPruebaReporteUno = new ArrayList<>();
		listaPruebaReporteUno.add(reportePruebaDos);

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
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error. Problemas al generar reporte", ""));
			return "";
		}

		try {
			HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance()
					.getExternalContext().getResponse();
			httpServletResponse.addHeader("Content-disposition", "attachment; filename=estado_de_cuenta_socio.pdf");

			byte[] fichero = JasperExportManager.exportReportToPdf(reportePrintLocal);

			httpServletResponse.setContentType("application/pdf");
			httpServletResponse.setHeader("Content-disposition", "inline; filename=estado_de_cuenta_socio.pdf");
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
