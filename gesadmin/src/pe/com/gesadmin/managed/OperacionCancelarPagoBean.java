package pe.com.gesadmin.managed;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import pe.com.gesadmin.entity.AnioFiscal;
import pe.com.gesadmin.entity.ComprobanteCorreccion;
import pe.com.gesadmin.entity.Operacion;
import pe.com.gesadmin.entity.Periodo;
import pe.com.gesadmin.entity.Puesto;
import pe.com.gesadmin.entity.PuestoPersonaCargo;
import pe.com.gesadmin.entity.transfer.ReporteComprobanteCorreccion;
import pe.com.gesadmin.service.AnioFiscalService;
import pe.com.gesadmin.service.OperacionService;
import pe.com.gesadmin.service.PeriodoService;
import pe.com.gesadmin.service.PuestoPersonaCargoService;
import pe.com.gesadmin.service.PuestoService;
import pe.com.gesadmin.service.ReporteService;
import pe.com.gesadmin.service.impl.AnioFiscalServiceImpl;
import pe.com.gesadmin.service.impl.OperacionServiceImpl;
import pe.com.gesadmin.service.impl.PeriodoServiceImpl;
import pe.com.gesadmin.service.impl.PuestoPersonaCargoServiceImpl;
import pe.com.gesadmin.service.impl.PuestoServiceImpl;
import pe.com.gesadmin.service.impl.ReporteServiceImpl;
import pe.com.gesadmin.util.Constante;
import pe.com.gesadmin.util.Conversiones;
import pe.com.gesadmin.util.DescriptivoNumero;

@ManagedBean
@ViewScoped
public class OperacionCancelarPagoBean {

	@ManagedProperty("#{usuarioSesionBean}")
	private UsuarioSesionBean usuarioSesionBean;

	private List<Operacion> lista = new ArrayList<>();
	private List<Operacion> listafiltro;
	private Operacion entidad = new Operacion();
	private Operacion entidadseleccionada = new Operacion();

	private List<AnioFiscal> listaAnioFiscal;
	private List<Periodo> listaPeriodo;
	private List<Puesto> listaPuesto;
	private List<PuestoPersonaCargo> listaPuestoPersonaCargo;

	private Integer idAnioFiscal;
	private Integer idPeriodo;
	private Integer idPuesto;
	private Integer idPersona;

	private String motivo;

	private Integer cantidadRegistros;

	private boolean booDetalle;
	private boolean booRegistro;
	private boolean booReporte;

	JasperPrint reportePrintLocal;
	private ComprobanteCorreccion comprobanteCorreccion;

	private String filtro;

	@EJB
	private OperacionService servicio = new OperacionServiceImpl();

	@EJB
	private AnioFiscalService anioFiscalService = new AnioFiscalServiceImpl();

	@EJB
	private PeriodoService periodoService = new PeriodoServiceImpl();

	@EJB
	private PuestoService puestoService = new PuestoServiceImpl();

	@EJB
	private PuestoPersonaCargoService puestoPersonaCargoService = new PuestoPersonaCargoServiceImpl();

	@EJB
	private ReporteService reporteService = new ReporteServiceImpl();

	public OperacionCancelarPagoBean() {
		// TODO Auto-generated constructor stub
		filtro = null;
		entidad = new Operacion();

		listaPeriodo = null;
		listaPuesto = null;
		listaPuestoPersonaCargo = null;

		idAnioFiscal = null;
		idPeriodo = null;
		idPuesto = null;
		idPersona = null;
		motivo = null;
		cantidadRegistros = 0;

		booReporte = false;
		comprobanteCorreccion = null;
	}

	@PostConstruct
	public void init() {
		listarEntidad();
		listarAnioFiscal();
		listarPuestos();

	}

	public List<Operacion> getLista() {
		return lista;
	}

	public void setLista(List<Operacion> lista) {
		this.lista = lista;
	}

	public List<Operacion> getListafiltro() {
		return listafiltro;
	}

	public void setListafiltro(List<Operacion> listafiltro) {
		this.listafiltro = listafiltro;
	}

	public Operacion getEntidad() {
		return entidad;
	}

	public void setEntidad(Operacion entidad) {
		this.entidad = entidad;
	}

	public Operacion getEntidadseleccionada() {
		return entidadseleccionada;
	}

	public void setEntidadseleccionada(Operacion entidadseleccionada) {
		this.entidadseleccionada = entidadseleccionada;
	}

	public void setServicio(OperacionService servicio) {
		this.servicio = servicio;
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

	public void setAnioFiscalService(AnioFiscalService anioFiscalService) {
		this.anioFiscalService = anioFiscalService;
	}

	public void setPeriodoService(PeriodoService periodoService) {
		this.periodoService = periodoService;
	}

	public Integer getIdAnioFiscal() {
		return idAnioFiscal;
	}

	public void setIdAnioFiscal(Integer idAnioFiscal) {
		this.idAnioFiscal = idAnioFiscal;
	}

	public Integer getIdPeriodo() {
		return idPeriodo;
	}

	public void setIdPeriodo(Integer idPeriodo) {
		this.idPeriodo = idPeriodo;
	}

	public List<AnioFiscal> getListaAnioFiscal() {
		return listaAnioFiscal;
	}

	public void setListaAnioFiscal(List<AnioFiscal> listaAnioFiscal) {
		this.listaAnioFiscal = listaAnioFiscal;
	}

	public List<Periodo> getListaPeriodo() {
		return listaPeriodo;
	}

	public void setListaPeriodo(List<Periodo> listaPeriodo) {
		this.listaPeriodo = listaPeriodo;
	}

	public boolean isBooDetalle() {
		return booDetalle;
	}

	public void setBooDetalle(boolean booDetalle) {
		this.booDetalle = booDetalle;
	}

	public boolean isBooRegistro() {
		return booRegistro;
	}

	public void setBooRegistro(boolean booRegistro) {
		this.booRegistro = booRegistro;
	}

	public void setPuestoService(PuestoService puestoService) {
		this.puestoService = puestoService;
	}

	public Integer getIdPuesto() {
		return idPuesto;
	}

	public void setIdPuesto(Integer idPuesto) {
		this.idPuesto = idPuesto;
	}

	public List<Puesto> getListaPuesto() {
		return listaPuesto;
	}

	public void setListaPuesto(List<Puesto> listaPuesto) {
		this.listaPuesto = listaPuesto;
	}

	public void setPuestoPersonaCargoService(PuestoPersonaCargoService puestoPersonaCargoService) {
		this.puestoPersonaCargoService = puestoPersonaCargoService;
	}

	public List<PuestoPersonaCargo> getListaPuestoPersonaCargo() {
		return listaPuestoPersonaCargo;
	}

	public void setListaPuestoPersonaCargo(List<PuestoPersonaCargo> listaPuestoPersonaCargo) {
		this.listaPuestoPersonaCargo = listaPuestoPersonaCargo;
	}

	public Integer getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(Integer idPersona) {
		this.idPersona = idPersona;
	}

	public Integer getCantidadRegistros() {
		return cantidadRegistros;
	}

	public void setCantidadRegistros(Integer cantidadRegistros) {
		this.cantidadRegistros = cantidadRegistros;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public UsuarioSesionBean getUsuarioSesionBean() {
		return usuarioSesionBean;
	}

	public void setUsuarioSesionBean(UsuarioSesionBean usuarioSesionBean) {
		this.usuarioSesionBean = usuarioSesionBean;
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

	public ComprobanteCorreccion getComprobanteCorreccion() {
		return comprobanteCorreccion;
	}

	public void setComprobanteCorreccion(ComprobanteCorreccion comprobanteCorreccion) {
		this.comprobanteCorreccion = comprobanteCorreccion;
	}

	public String cancelar_Pago() {

		Integer idEstatusOperacionCancelada = 4;
		try {
			servicio.cancelarPago(entidadseleccionada.getId(), idPersona, idEstatusOperacionCancelada);
			booDetalle = false;
			entidad = new Operacion();
			entidadseleccionada = new Operacion();
			idPersona = null;
		} catch (Exception e) {
			// TODO: handle exception
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al realizar reversion de pago", ""));
			return "";
		}

		listarEntidad();

		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Reversion de pago exitoso", ""));
		return "";

	}

	public String cancelar_Pago2() {

		Conversiones conversiones = new Conversiones();

		Integer idEstatusOperacion = 4;

		String numerodes = conversiones
				.descripcionLiteral(conversiones.formatoMontos(entidadseleccionada.getMonto()) + "", "SOLES");

		List<Operacion> listaOperacionPagada = new ArrayList<>();
		listaOperacionPagada = servicio.listarPorReciboPago(entidadseleccionada.getTipoDoc(),
				entidadseleccionada.getNroDoc());

		if (listaOperacionPagada == null || listaOperacionPagada.isEmpty() || listaOperacionPagada.size() == 0) {

			try {
				comprobanteCorreccion = new ComprobanteCorreccion();
				comprobanteCorreccion = servicio.cancelarPagoDos(entidadseleccionada.getId(), usuarioSesionBean.getUsuario(), motivo, numerodes, idEstatusOperacion, 1);
				
				booReporte = true;
				entidad = new Operacion();
				entidadseleccionada = new Operacion();
				idPersona = null;
			} catch (Exception e) {
				// TODO: handle exception
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al realizar reversion de pago", ""));
				booReporte = false;
				return "";
			}			

		} else {
			
			try {

				for (int i = 0; i <= listaOperacionPagada.size() - 1; i++) {
					comprobanteCorreccion = new ComprobanteCorreccion();
					comprobanteCorreccion = servicio.cancelarPagoDos(listaOperacionPagada.get(i).getId(),
							usuarioSesionBean.getUsuario(), motivo, numerodes, idEstatusOperacion, 1);
				}

				booReporte = true;
				entidad = new Operacion();
				entidadseleccionada = new Operacion();
				idPersona = null;

			} catch (Exception e) {
				// TODO: handle exception
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al realizar reversion de pago", ""));
				booReporte = false;
				return "";
			}
			
		}

		listarEntidad();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Se procedio a revertir Movimiento, Puede consultar el comprobante en el modulo Consultas de Movimientos",""));
		return "";

	}

	public String vercertificadoComprobanteCorreccion(ActionEvent actionEvent) throws JRException, IOException {

		String absolutePathCerdp = Constante.RUTA_REPORTES + "reporte_comprobante_correccion.jasper";
		List<ReporteComprobanteCorreccion> listaLocalReporte = reporteService
				.obtenerReporteComprobanteCorreccion(comprobanteCorreccion.getIdOperacion());

		// --- CARGA DE DATOS EN LA COLECCION
		JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(listaLocalReporte);
		System.out.println();

		try {
			reportePrintLocal = JasperFillManager.fillReport(absolutePathCerdp, null, beanCollectionDataSource);
		} catch (JRException e) {
			// TODO Auto-generated catch block
			System.out.println(
					"Error en creacion instancia jasperPrint: " + e.toString() + " TRACE: " + e.getStackTrace());
			e.printStackTrace();
			reportePrintLocal = null;
		}

		if (reportePrintLocal == null) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error. Problemas al generar reporte", ""));
			return "";
		}

		try {
			HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance()
					.getExternalContext().getResponse();
			httpServletResponse.addHeader("Content-disposition", "attachment; filename=comprobante_correccion.pdf");

			byte[] fichero = JasperExportManager.exportReportToPdf(reportePrintLocal);

			httpServletResponse.setContentType("application/pdf");
			httpServletResponse.setHeader("Content-disposition", "inline; filename=comprobante_correccion.pdf");
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

	public String eliminar() {

		Conversiones conversiones = new Conversiones();

		if (entidadseleccionada.getId() == null) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Seleccione registro a eliminar", ""));
			limpiar();
			listarEntidad();
			return "";
		}

		String numerodes = null;
		numerodes = conversiones.descripcionLiteral(conversiones.formatoMontos(entidadseleccionada.getMonto()) + "",
				"SOLES");
		String nombreUsuarioEliminacion = usuarioSesionBean.getUsuario().getPersona().getNombre() + " "
				+ usuarioSesionBean.getUsuario().getPersona().getPaterno() + " "
				+ usuarioSesionBean.getUsuario().getPersona().getMaterno();

		entidad = new Operacion();
		entidad = entidadseleccionada;

		entidad.setIdUsuarioEliminacion(usuarioSesionBean.getUsuario().getId());
		entidad.setDescripcionMonto(numerodes);
		entidad.setRegistroEliminacion(new Date());
		entidad.setEstado(0);

		ComprobanteCorreccion comprobanteCorreccion = new ComprobanteCorreccion();
		comprobanteCorreccion.setIdOperacion(entidad.getId());
		comprobanteCorreccion.setEstado(1);

		try {
			servicio.actualizar(entidad);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro eliminado", ""));
		} catch (Exception e) {
			// TODO: handle exception
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al eliminar registro", ""));
		}
		limpiar();
		listarEntidad();
		return "";
	}

	public void recuperar() {

		entidad = new Operacion();

		try {
			entidad = servicio.recuperar(entidadseleccionada.getId());
		} catch (Exception e) {
			// TODO: handle exception
			entidad = null;
			entidadseleccionada = null;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Problemas al recuperar registro", ""));
		}
	}

	public void listarAnioFiscal() {
		listaAnioFiscal = new ArrayList<>();
		try {
			listaAnioFiscal = anioFiscalService.listar();
		} catch (Exception e) {
			// TODO: handle exception
			listaAnioFiscal = null;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Problemas al recuperar registros año fiscal", ""));
		}
	}

	public void listarEntidad() {
		lista = new ArrayList<>();
		try {
			lista = servicio.buscarPorPeriodoIdPuestoIdCorreccion(idPeriodo, idPuesto);
		} catch (Exception e) {
			// TODO: handle exception
			lista = null;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Problemas al recuperar registros", ""));
		}

		listafiltro = lista;

		obtenerCantidadRegistrosEntidad();
	}

	public void listarPeriodo() {

		if (idAnioFiscal == null) {

			listaPeriodo = null;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Seleccione periodo de año fiscal", ""));
		} else {

			listaPeriodo = new ArrayList<>();
			try {
				listaPeriodo = periodoService.listarPorIdAnioFiscal(idAnioFiscal);
			} catch (Exception e) {
				// TODO: handle exception
				listaPeriodo = null;
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Problemas al recuperar registros periodo", ""));
			}
		}
	}

	public void onRowSelect(SelectEvent event) {

		if (entidadseleccionada == null) {
			entidad = new Operacion();
			entidadseleccionada = new Operacion();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "No se selecciono ningun registro", ""));
		} else {
			entidad = entidadseleccionada;
			booDetalle = true;

			try {
				listaPuestoPersonaCargo = new ArrayList<>();
				listaPuestoPersonaCargo = puestoPersonaCargoService.listarPuestoId(idPuesto);
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("Excepcion al listar listaPuestoPersonaCargo: " + e.toString());
				listaPuestoPersonaCargo = null;
			}

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Seleccion de registro exitosa", ""));
		}
	}

	public void onRowUnselect(UnselectEvent event) {
		entidad = new Operacion();
		entidadseleccionada = new Operacion();

		idAnioFiscal = null;
		idPeriodo = null;
		idPersona = null;
		motivo = null;
		idPuesto = null;

		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Se anulo seleccion de registro ", ""));
	}

	public void limpiar() {
		entidad = new Operacion();
		entidadseleccionada = new Operacion();

		idAnioFiscal = null;
		idPeriodo = null;
		idPuesto = null;
		idPersona = null;
		motivo = null;

		cantidadRegistros = 0;

		listafiltro = null;
		listaPuestoPersonaCargo = null;
		listaPeriodo = null;

		booDetalle = false;

		lista = null;

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
			if (lista.get(i).getDescripcion().contains(filtro)
					|| lista.get(i).getCategoriaOperacion().getDescripcion().contains(filtro)) {
				System.out.println("lista: " + lista.get(i).toString());
				listafiltro.add(lista.get(i));
			}
		}
		filtro = null;
		return "";
	}

	public void listarPuestos() {

		listaPuesto = new ArrayList<>();

		try {
			listaPuesto = puestoService.listarActivo();
		} catch (Exception e) {
			// TODO: handle exception
			listaPuesto = null;
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Problemas al recuperar registros puestos (deudores)", ""));
		}
	}

	public void obtenerCantidadRegistrosEntidad() {

		if (listafiltro == null || listafiltro.isEmpty()) {
			cantidadRegistros = 0;
		} else {
			cantidadRegistros = listafiltro.size();
		}
	}

}
