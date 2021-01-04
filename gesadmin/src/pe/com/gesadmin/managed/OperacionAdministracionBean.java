package pe.com.gesadmin.managed;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import pe.com.gesadmin.entity.Amonestacion;
import pe.com.gesadmin.entity.CategoriaOperacion;
import pe.com.gesadmin.entity.EstatusOperacion;
import pe.com.gesadmin.entity.MedidaServicio;
import pe.com.gesadmin.entity.Operacion;
import pe.com.gesadmin.entity.Periodo;
import pe.com.gesadmin.entity.Proveedor;
import pe.com.gesadmin.entity.Puesto;
import pe.com.gesadmin.entity.TipoOperacion;
import pe.com.gesadmin.entity.TipoServicio;
import pe.com.gesadmin.entity.Variable;
import pe.com.gesadmin.entity.transfer.LecturasMedidasPreOperacion;
import pe.com.gesadmin.entity.transfer.OperacionAdministracionTransfer;
import pe.com.gesadmin.service.MedidaServicioService;
import pe.com.gesadmin.service.OperacionService;
import pe.com.gesadmin.service.PeriodoService;
import pe.com.gesadmin.service.PuestoService;
import pe.com.gesadmin.service.TipoServicioService;
import pe.com.gesadmin.service.VariableService;
import pe.com.gesadmin.service.impl.MedidaServicioServiceImpl;
import pe.com.gesadmin.service.impl.OperacionServiceImpl;
import pe.com.gesadmin.service.impl.PeriodoServiceImpl;
import pe.com.gesadmin.service.impl.PuestoServiceImpl;
import pe.com.gesadmin.service.impl.TipoServicioServiceImpl;
import pe.com.gesadmin.service.impl.VariableServiceImpl;
import pe.com.gesadmin.util.Conversiones;

@ManagedBean
@ViewScoped
public class OperacionAdministracionBean {

	@ManagedProperty("#{usuarioSesionBean}")
	private UsuarioSesionBean usuarioSesionBean;
	
	private List<OperacionAdministracionTransfer> listaResumen = new ArrayList<>();
	private List<OperacionAdministracionTransfer> listaFiltroResumen;
	private Operacion entidad = new Operacion();

	private Operacion entidadseleccionada = new Operacion();

	private Periodo periodoActual = new Periodo();
	private Periodo periodoAnterior = new Periodo();
	private TipoServicio tipoServicio = new TipoServicio();
	private TipoServicio tipoServicioActual = new TipoServicio();

	private List<Puesto> listaPuesto = new ArrayList<>();
	private List<Puesto> listaPuestoFiltro = new ArrayList<>();

	List<OperacionAdministracionTransfer> listaPreOperacion = new ArrayList<>();

	private Integer cantidadAdministracion;
	private String claseAdministracion;
	private String mensajeAdministracion;

	private List<Operacion> listaEntidadAdministracion = new ArrayList<>();
	private List<Operacion> listaEntidadAdministracionFiltro = new ArrayList<>();

	private boolean booBtnEliminar = false;

	private boolean booRegistroEntidad = false;

	private Variable variableCostoAdministracion = new Variable();

	private String filtro;
	
	private Conversiones conversiones = new Conversiones(); 

	@EJB
	private OperacionService servicio = new OperacionServiceImpl();
	@EJB
	private MedidaServicioService medidaServicioService = new MedidaServicioServiceImpl();
	@EJB
	private PeriodoService periodoService = new PeriodoServiceImpl();
	@EJB
	private TipoServicioService tipoServicioService = new TipoServicioServiceImpl();
	@EJB
	private PuestoService puestoService = new PuestoServiceImpl();
	@EJB
	private VariableService variableService = new VariableServiceImpl();

	public OperacionAdministracionBean() {
		// TODO Auto-generated constructor stub
		filtro = null;
		entidad = new Operacion();
		listaResumen = new ArrayList<>();

		periodoActual = new Periodo();
		periodoAnterior = new Periodo();

		tipoServicio = new TipoServicio();
		tipoServicioActual = new TipoServicio();
		listaPuesto = new ArrayList<>();

		cantidadAdministracion = 0;
		claseAdministracion = "RedBack";
		mensajeAdministracion = "";

		listaEntidadAdministracion = new ArrayList<>();
		listaEntidadAdministracionFiltro = new ArrayList<>();
		listaPuestoFiltro = new ArrayList<>();
		


		listaPreOperacion = new ArrayList<>();
		variableCostoAdministracion = new Variable();

		booBtnEliminar = false;
		booRegistroEntidad = false;

		listaFiltroResumen = new ArrayList<>();

	}

	@PostConstruct
	public void init() {
		
		/*
		obtenerVariables();
		listarPuestos();
		obtenerPeriodoActual();
		listarEntidad();

		filtrarPuestosAdministracionSinCompletarOperacion();
		obtenerInputPeriodo();
		obtenerCantidadAdministracion();
		*/
		
		cargaInicial();
	}

	public List<OperacionAdministracionTransfer> getListaResumen() {
		return listaResumen;
	}

	public void setListaResumen(List<OperacionAdministracionTransfer> listaResumen) {
		this.listaResumen = listaResumen;
	}

	public List<OperacionAdministracionTransfer> getListaFiltroResumen() {
		return listaFiltroResumen;
	}

	public void setListaFiltroResumen(List<OperacionAdministracionTransfer> listaFiltroResumen) {
		this.listaFiltroResumen = listaFiltroResumen;
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

	public Periodo getPeriodoActual() {
		return periodoActual;
	}

	public void setPeriodoActual(Periodo periodoActual) {
		this.periodoActual = periodoActual;
	}

	public void setPeriodoService(PeriodoService periodoService) {
		this.periodoService = periodoService;
	}

	public void setTipoServicioService(TipoServicioService tipoServicioService) {
		this.tipoServicioService = tipoServicioService;
	}

	public TipoServicio getTipoServicio() {
		return tipoServicio;
	}

	public void setTipoServicio(TipoServicio tipoServicio) {
		this.tipoServicio = tipoServicio;
	}

	public List<Puesto> getListaPuesto() {
		return listaPuesto;
	}

	public void setListaPuesto(List<Puesto> listaPuesto) {
		this.listaPuesto = listaPuesto;
	}

	public void setPuestoService(PuestoService puestoService) {
		this.puestoService = puestoService;
	}

	public TipoServicio getTipoServicioActual() {
		return tipoServicioActual;
	}

	public void setTipoServicioActual(TipoServicio tipoServicioActual) {
		this.tipoServicioActual = tipoServicioActual;
	}

	public Integer getCantidadAdministracion() {
		return cantidadAdministracion;
	}

	public void setCantidadAdministracion(Integer cantidadAdministracion) {
		this.cantidadAdministracion = cantidadAdministracion;
	}

	public String getClaseAdministracion() {
		return claseAdministracion;
	}

	public void setClaseAdministracion(String claseAdministracion) {
		this.claseAdministracion = claseAdministracion;
	}

	public String getMensajeAdministracion() {
		return mensajeAdministracion;
	}

	public void setMensajeAdministracion(String mensajeAdministracion) {
		this.mensajeAdministracion = mensajeAdministracion;
	}

	public List<Operacion> getListaEntidadAdministracion() {
		return listaEntidadAdministracion;
	}

	public void setListaEntidadAdministracion(List<Operacion> listaEntidadAdministracion) {
		this.listaEntidadAdministracion = listaEntidadAdministracion;
	}

	public List<Puesto> getListaPuestoFiltro() {
		return listaPuestoFiltro;
	}

	public void setListaPuestoFiltro(List<Puesto> listaPuestoFiltro) {
		this.listaPuestoFiltro = listaPuestoFiltro;
	}

	public boolean isBooBtnEliminar() {
		return booBtnEliminar;
	}

	public void setBooBtnEliminar(boolean booBtnEliminar) {
		this.booBtnEliminar = booBtnEliminar;
	}

	public Periodo getPeriodoAnterior() {
		return periodoAnterior;
	}

	public void setPeriodoAnterior(Periodo periodoAnterior) {
		this.periodoAnterior = periodoAnterior;
	}

	public void setMedidaServicioService(MedidaServicioService medidaServicioService) {
		this.medidaServicioService = medidaServicioService;
	}

	public Variable getVariableCostoAdministracion() {
		return variableCostoAdministracion;
	}

	public void setVariableCostoAdministracion(Variable variableCostoAdministracion) {
		this.variableCostoAdministracion = variableCostoAdministracion;
	}

	public void setVariableService(VariableService variableService) {
		this.variableService = variableService;
	}

	public List<OperacionAdministracionTransfer> getListaPreOperacion() {
		return listaPreOperacion;
	}

	public void setListaPreOperacion(List<OperacionAdministracionTransfer> listaPreOperacion) {
		this.listaPreOperacion = listaPreOperacion;
	}


	public boolean isBooRegistroEntidad() {
		return booRegistroEntidad;
	}

	public UsuarioSesionBean getUsuarioSesionBean() {
		return usuarioSesionBean;
	}

	public void setUsuarioSesionBean(UsuarioSesionBean usuarioSesionBean) {
		this.usuarioSesionBean = usuarioSesionBean;
	}

	public void setBooRegistroEntidad(boolean booRegistroEntidad) {
		this.booRegistroEntidad = booRegistroEntidad;
	}

	public List<Operacion> getListaEntidadAdministracionFiltro() {
		return listaEntidadAdministracionFiltro;
	}

	public void setListaEntidadAdministracionFiltro(List<Operacion> listaEntidadAdministracionFiltro) {
		this.listaEntidadAdministracionFiltro = listaEntidadAdministracionFiltro;
	}

	public String guardar() {
		System.out.println("Periodo: " + periodoActual.toString());
		System.out.println("Tipo Servicio: " + tipoServicioActual.toString());
		System.out.println("Entidad: " + entidad.toString());
		entidad.setEstado(1);

		if (entidad.getId() == null) {
			System.out.println("A guardar");
			try {

				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro creado", ""));

			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("Excepcion: " + e.getCause().toString());
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al crear registro", ""));
			}

		} else {
			System.out.println("A actualizar");
			try {

				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro actualizado", ""));
			} catch (Exception e) {
				// TODO: handle exception
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al actualizar registro", ""));
			}
		}
		limpiarEntidad();
		listarEntidad();

		filtrarPuestosAdministracionSinCompletarOperacion();
		obtenerInputPeriodo();
		obtenerCantidadAdministracion();

		if (tipoServicioActual.getId() == 1) {
			filtrarPuestosAdministracionSinCompletarOperacion();
		} else if (tipoServicioActual.getId() == 2) {
			filtrarPuestosAdministracionSinCompletarOperacion();
		} else {
			listaPuestoFiltro = listaPuesto;
		}

		return "";
	}

	public String eliminar() {

		if (entidadseleccionada.getPuesto().getId() == null) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Seleccione registro a eliminar", ""));
			limpiarEntidad();
			listarEntidad();

			filtrarPuestosAdministracionSinCompletarOperacion();
			obtenerInputPeriodo();
			obtenerCantidadAdministracion();

			return "";
		}

		try {
			/*
			servicio.eliminarPorPeriodoidPuestoidCategoriaid(periodoActual.getId(),
					entidadseleccionadaTransfer.getPuestoId(), entidadseleccionadaTransfer.getCategoriaId());
			*/
			if(entidadseleccionada.getEstatusOperacion().getId() == 1 || entidadseleccionada.getEstatusOperacion().getId() == 3) {
				Operacion operacionLocalIn = new Operacion();
				operacionLocalIn = servicio.recuperar(entidadseleccionada.getId());
				operacionLocalIn.setEstado(0);
				operacionLocalIn.setIdUsuario(usuarioSesionBean.getUsuario().getId());
				servicio.actualizar(operacionLocalIn);	
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro eliminado", ""));
				
			}else {
				
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_WARN, "Solo puede eliminar movimientos con estatus pendiente o vencida", ""));
				return "";
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al eliminar registros", ""));
		}

		limpiarEntidad();
		listarEntidad();

		filtrarPuestosAdministracionSinCompletarOperacion();
		obtenerInputPeriodo();
		obtenerCantidadAdministracion();

		booBtnEliminar = false;

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
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error.", "Problemas al recuperar registro"));
		}
	}

	public void listarEntidad() {
		listaResumen = new ArrayList<>();
		listaEntidadAdministracion = new ArrayList<>();
		try {
			listaResumen = servicio.listarPorPeriodoactualCategoriaAdministracionTransfer();
			System.out.println("listaResumen Transfer recuperada: " + listaResumen.toString());
			listaEntidadAdministracion = servicio.listarPorPeriodoactualCategoriaAdministracion();
			listaEntidadAdministracionFiltro = listaEntidadAdministracion;
			System.out.println("listaResumen no Ransfers recuperada: " + listaEntidadAdministracion.toString());
		} catch (Exception e) {
			// TODO: handle exception
			listaResumen = null;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error.", "Problemas al recuperar registros"));
		}

		listaFiltroResumen = listaResumen;		

	}

	public void obtenerCantidadAdministracion() {

		cantidadAdministracion = listaPuesto.size() * periodoActual.getDias();
		System.out.println("cantidadAdministracion = " + cantidadAdministracion);

		if (listaFiltroResumen == null || listaFiltroResumen.isEmpty()) {
			claseAdministracion = "RedBack";
			mensajeAdministracion = "(" + listaPreOperacion.size()
					+ ") Puestos no tienen registrado su cuota de Administracion del presente periodo";

			booRegistroEntidad = true;

		} else if (cantidadAdministracion == listaEntidadAdministracion.size()) {
			System.out.println("Registro de administracion completa");
			claseAdministracion = "GreenBack";
			mensajeAdministracion = "Todos los Puestos tienen registrado su cuota de Administracion del presente periodo";

			booRegistroEntidad = false;
		} else {
			System.out.println("Registro de administracion incompleta");
			// int diferencia = cantidadAdministracion - listaEntidadAdministracion.size();
			int diferencia2 = listaPreOperacion.size();
			claseAdministracion = "RedBack";
			mensajeAdministracion = "(" + diferencia2
					+ ") Puestos no tienen registrado todas sus cuotas de Administracion del presente periodo";

			booRegistroEntidad = true;
		}
	}

	public boolean validarOperacionesAdministrativo() {

		List<Puesto> listaPuestos = new ArrayList<>();
		List<Operacion> listaOperaciones = new ArrayList<>();

		listaPuestos = puestoService.listarFiltro(true);
		listaOperaciones = servicio.listarPorPeriodoactualCategoriaAdministracion();

		int cantidadDias = periodoActual.getDias();

		Integer diferencia = (listaPuestos.size() * cantidadDias) - listaOperaciones.size();

		if (diferencia == 0) {
			return true;
		} else {
			return false;
		}

	}

	public void filtrarPuestosAdministracionSinCompletarOperacion() {

		System.out.println("Filtro puestos por listaResumen de Administracion");

		listaPuestoFiltro = new ArrayList<>();

		if (listaResumen == null || listaResumen.isEmpty()) {

			listaPuestoFiltro = listaPuesto;

		} else {

			for (int i = 0; i <= listaPuesto.size() - 1; i++) {

				boolean consideracion = false;

				for (int j = 0; j <= listaResumen.size() - 1; j++) {
					if (listaPuesto.get(i).getId() == listaResumen.get(j).getPuestoId()) {
						System.out.println("El valor " + listaPuesto.get(i).getId() + " SI Coincide con: "
								+ listaResumen.get(j).getPuestoId());
						if (listaResumen.get(j).getDias() == periodoActual.getDias()) {
							consideracion = false;
							break;
						} else {
							consideracion = true;
							break;
						}
					} else {
						System.out.println("El valor " + listaPuesto.get(i).getId() + " NO Coincide con: "
								+ listaResumen.get(j).getPuestoId());
						consideracion = true;
					}
				}

				if (consideracion) {
					System.out.println("SI Agregaremos el puesto: " + listaPuesto.get(i).toString());
					listaPuestoFiltro.add(listaPuesto.get(i));
				} else {
					System.out.println("NO Agregaremos el puesto: " + listaPuesto.get(i).toString());
				}
			}
		}

	}

	public void listarPuestos() {
		listaPuesto = new ArrayList<>();
		try {
			listaPuesto = puestoService.listarFiltroNoAdminNiPropiedad();
			System.out.println("listaResumen Puesto: " + listaPuesto.toString());
		} catch (Exception e) {
			// TODO: handle exception
			listaPuesto = null;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error. Problemas al recuperar registros puesto", ""));
		}
	}

	public void limpiarEntidad() {
		entidad = new Operacion();
		entidadseleccionada = new Operacion();
		listaFiltroResumen = listaResumen;
		listaPreOperacion = new ArrayList<>();
	}

	public void cancelarEliminacionEntidad() {
		entidad = new Operacion();
		entidadseleccionada = new Operacion();
		booBtnEliminar = false;
	}

	public void obtenerPeriodoActual() {

		List<Periodo> listaperiodos = null;

		periodoActual = new Periodo();
		periodoAnterior = new Periodo();

		try {
			listaperiodos = periodoService.listar();
			System.out.println("Listas Periodods: " + listaperiodos.toString());
		} catch (Exception e) {
			// TODO: handle exception
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al recuperar periodo fiscal en curso ", ""));
			return;
		}

		if (listaperiodos == null || listaperiodos.isEmpty()) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "No existe periodo fiscal activo en curso ", ""));
			return;
		} else {

			if (listaperiodos.size() > 1) {
				periodoActual = listaperiodos.get(0);
				periodoAnterior = listaperiodos.get(1);
			} else {
				periodoActual = listaperiodos.get(0);
				periodoAnterior = null;
			}
		}
	}

	public void obtenerPeriodoActual2() {

		List<Periodo> listaperiodos = null;

		periodoActual = new Periodo();
		periodoAnterior = new Periodo();

		try {
			listaperiodos = periodoService.listar();
			System.out.println("Listas Periodods: " + listaperiodos.toString());
		} catch (Exception e) {
			// TODO: handle exception
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al recuperar periodo fiscal en curso ", ""));
			return;
		}

		if (listaperiodos == null || listaperiodos.isEmpty()) {
			periodoActual = null;
			periodoAnterior = null;
			return;
		} else {

			if (listaperiodos.size() > 1) {
				periodoActual = listaperiodos.get(0);
				periodoAnterior = listaperiodos.get(1);
			} else {
				periodoActual = listaperiodos.get(0);
				periodoAnterior = null;
			}
		}
	}

	public String obtenerInputPeriodo() {

		listaPreOperacion = new ArrayList<>();

		// Estatus Operacion 1 = Pendiente;
		Integer idEstatusOperacion = 1;
		// Estatus Proveedor 3 = CC SArita;
		Integer idProveedorAdministracion = 3;
		// Tipo Operacion 1 = Ingreso
		Integer idTipoOperacion = 1;

		Integer idCategoria = 3;
		String descricionCategoria = "COBRO ADMINISTRATIVO";

		List<OperacionAdministracionTransfer> listaOperacionesLocal = new ArrayList<>();

		for (int i = 0; i <= listaPuestoFiltro.size() - 1; i++) {

			Integer idPuesto = listaPuestoFiltro.get(i).getId();

			OperacionAdministracionTransfer operacionTransfer = new OperacionAdministracionTransfer();

			List<OperacionAdministracionTransfer> listalocal = new ArrayList<>();

			listalocal = listaResumen.stream().filter(x -> idPuesto.equals(x.getPuestoId())).collect(Collectors.toList());

			if (listalocal == null || listalocal.isEmpty()) {

				operacionTransfer.setCategoriaDes(descricionCategoria);
				operacionTransfer.setCategoriaId(idCategoria);
				operacionTransfer.setDias(periodoActual.getDias());
				operacionTransfer.setMontoTotal(conversiones.formatoMontos_a(periodoActual.getDias() * variableCostoAdministracion.getMonto()));
				operacionTransfer.setMontoUnitario(variableCostoAdministracion.getMonto());
				operacionTransfer.setPuestoDes(listaPuestoFiltro.get(i).getDescripcion());
				operacionTransfer.setPuestoId(listaPuestoFiltro.get(i).getId());
				operacionTransfer.setEstatusOperacionId(idEstatusOperacion);
				operacionTransfer.setIdProveedorAdministracion(idProveedorAdministracion);
				operacionTransfer.setTipoOperacionId(idTipoOperacion);
				operacionTransfer.setIdPeriodo(periodoActual.getId());

				listaOperacionesLocal.add(operacionTransfer);
			} else {
				operacionTransfer.setCategoriaDes(descricionCategoria);
				operacionTransfer.setCategoriaId(idCategoria);
				operacionTransfer.setDias(periodoActual.getDias() - listalocal.get(0).getDias());
				Integer diasPendientes = periodoActual.getDias() - listalocal.get(0).getDias();
				operacionTransfer.setMontoTotal(conversiones.formatoMontos_a(diasPendientes * variableCostoAdministracion.getMonto()));
				operacionTransfer.setMontoUnitario(variableCostoAdministracion.getMonto());
				operacionTransfer.setPuestoDes(listaPuestoFiltro.get(i).getDescripcion());
				operacionTransfer.setPuestoId(listaPuestoFiltro.get(i).getId());
				operacionTransfer.setEstatusOperacionId(idEstatusOperacion);
				operacionTransfer.setIdProveedorAdministracion(idProveedorAdministracion);
				operacionTransfer.setTipoOperacionId(idTipoOperacion);
				operacionTransfer.setIdPeriodo(periodoActual.getId());

				listaOperacionesLocal.add(operacionTransfer);
			}

		}

		listaPreOperacion = listaOperacionesLocal;

		System.out.println("listaPreOperacion : " + listaPreOperacion.toString());

		return "";

	}

	public String generarOperaciones() {
		
		String descripcionOperacion = periodoActual.getDescripcion() + " " + periodoActual.getAnioFiscal().getDescripcion();
		

		try {
			servicio.generarOperacionAdministracion(listaPreOperacion, descripcionOperacion,
					entidad.getFechaVencimiento(), usuarioSesionBean.getUsuario().getId());
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Creacion exitosa de deuda", ""));
		} catch (Exception e) {
			// TODO: handle exception
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error en creacion de deuda", ""));
			return "";
		}

		limpiarEntidad();
		listarEntidad();

		filtrarPuestosAdministracionSinCompletarOperacion();
		obtenerInputPeriodo();
		obtenerCantidadAdministracion();

		return "";
	}
	
	
public String generarOperaciones2() {
		
		String descripcionOperacion = periodoActual.getDescripcion() + " " + periodoActual.getAnioFiscal().getDescripcion();
		

		try {
			grabarMasivo(listaPreOperacion, descripcionOperacion,
					entidad.getFechaVencimiento(), usuarioSesionBean.getUsuario().getId());
			
		} catch (Exception e) {
			// TODO: handle exception
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error en creacion de deuda", ""));
			return "";
		}

		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Creacion exitosa de deuda", ""));
		
		limpiarEntidad();
		listarEntidad();

		filtrarPuestosAdministracionSinCompletarOperacion();
		obtenerInputPeriodo();
		obtenerCantidadAdministracion();

		return "";
		
	}

	public String filtrar() {

		if (listaEntidadAdministracion == null || listaEntidadAdministracion.isEmpty() || listaEntidadAdministracion.size() == 0) {
			filtro = null;
			return "";
		}
		listaEntidadAdministracionFiltro = null;
		listaEntidadAdministracionFiltro = new ArrayList<>();
		System.out.println("Texto a filtra: " + filtro);
		for (int i = 0; i <= listaEntidadAdministracion.size() - 1; i++) {
			if (listaEntidadAdministracion.get(i).getPuesto().getDescripcion().contains(filtro)) {
				System.out.println("listaEntidadAdministracion: " + listaEntidadAdministracion.get(i).toString());
				listaEntidadAdministracionFiltro.add(listaEntidadAdministracion.get(i));
			}
		}
		filtro = null;
		return "";
	}

	public void obtenerVariables() {

		List<Variable> listaVariables = new ArrayList<Variable>();

		listaVariables = variableService.listarActivo();

		variableCostoAdministracion = listaVariables.get(3);

		System.err.println("variableCostoAdministracion: " + variableCostoAdministracion.toString());

	}

	public void onRowSelect(SelectEvent event) {

		System.out.println("Entidad Seleccionada: " + entidadseleccionada.toString());

		if (entidadseleccionada == null) {
			entidadseleccionada = new Operacion();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "No se selecciono ningun registro", ""));
		} else {

			booBtnEliminar = true;

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Seleccion de registro exitosa", ""));
		}
	}

	public void onRowUnselect(UnselectEvent event) {
		entidadseleccionada = new Operacion();

		booBtnEliminar = false;

		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Se anulo seleccion de registro ", ""));
	}

	public void cargaInicial() {
		obtenerPeriodoActual2();

		if (periodoActual == null) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "No existe periodo fiscal activo en curso ", ""));
			claseAdministracion = "RedBack";
			mensajeAdministracion = "Registre un periodo fiscal para calcular situacion de operacion Gasto Administrativo";
			return;
		} else {
			obtenerVariables();
			listarPuestos();
			listarEntidad();

			filtrarPuestosAdministracionSinCompletarOperacion();
			obtenerInputPeriodo();
			obtenerCantidadAdministracion();
		}
	}
	
	
	public void grabarMasivo(List<OperacionAdministracionTransfer> listaLocalMas, String descripcionLocalMas, Date fechaVencimientoLocalMas, Integer idUsuarioLocalMas) {
		

		for (int i = 0; i <= listaLocalMas.size() - 1; i++) {
			
			System.out.println("ListaLocalMas: " + listaLocalMas.toString());

			Integer cantidadRegistros = 0;
			cantidadRegistros = listaLocalMas.get(i).getDias();

			for (int j = 1; j <= cantidadRegistros; j++) {

				Operacion operacionLocall = new Operacion();
				operacionLocall.setCategoriaOperacion(new CategoriaOperacion(listaLocalMas.get(i).getCategoriaId()));
				operacionLocall.setDescripcion(((j+"").length()==2?j+"":"0"+j) +" " +descripcionLocalMas);
				operacionLocall.setEstado(1);
				operacionLocall.setEstatusOperacion(new EstatusOperacion(listaLocalMas.get(i).getEstatusOperacionId()));
				operacionLocall.setFechaVencimiento(fechaVencimientoLocalMas);
				operacionLocall.setMonto(listaLocalMas.get(i).getMontoUnitario());
				operacionLocall.setPeriodo(new Periodo(listaLocalMas.get(i).getIdPeriodo()));
				operacionLocall.setProveedor(new Proveedor(listaLocalMas.get(i).getIdProveedorAdministracion()));
				operacionLocall.setPuesto(new Puesto(listaLocalMas.get(i).getPuestoId()));
				operacionLocall.setTipoOperacion(new TipoOperacion(listaLocalMas.get(i).getTipoOperacionId()));
				operacionLocall.setPersonaResponsableOperacion(null);
				operacionLocall.setIdUsuario(idUsuarioLocalMas);
				System.out.println("operacionLocall: " + operacionLocall.toString());
				servicio.crear(operacionLocall);

			}
		}
		
	}

}
