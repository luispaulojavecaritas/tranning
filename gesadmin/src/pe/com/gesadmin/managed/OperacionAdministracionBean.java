package pe.com.gesadmin.managed;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import pe.com.gesadmin.entity.Amonestacion;
import pe.com.gesadmin.entity.MedidaServicio;
import pe.com.gesadmin.entity.Operacion;
import pe.com.gesadmin.entity.Periodo;
import pe.com.gesadmin.entity.Puesto;
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

@ManagedBean
@ViewScoped
public class OperacionAdministracionBean {

	private List<OperacionAdministracionTransfer> lista = new ArrayList<>();
	private List<OperacionAdministracionTransfer> listafiltro;
	private Operacion entidad = new Operacion();

	private OperacionAdministracionTransfer entidadseleccionadaTransfer = new OperacionAdministracionTransfer();
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

	private boolean booBtnEliminar = false;
	
	private boolean booRegistroEntidad = false;

	private Variable variableCostoAdministracion = new Variable();

	private String filtro;

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
		lista = new ArrayList<>();

		periodoActual = new Periodo();
		periodoAnterior = new Periodo();

		tipoServicio = new TipoServicio();
		tipoServicioActual = new TipoServicio();
		listaPuesto = new ArrayList<>();

		entidadseleccionadaTransfer = new OperacionAdministracionTransfer();

		cantidadAdministracion = 0;
		claseAdministracion = "RedBack";
		mensajeAdministracion = "";

		listaEntidadAdministracion = new ArrayList<>();
		listaPuestoFiltro = new ArrayList<>();

		listaPreOperacion = new ArrayList<>();
		variableCostoAdministracion = new Variable();

		booBtnEliminar = false;
		booRegistroEntidad = false;

		listafiltro = new ArrayList<>();

	}

	@PostConstruct
	public void init() {
		obtenerVariables();
		listarPuestos();
		obtenerPeriodoActual();
		listarEntidad();

		filtrarPuestosAdministracionSinCompletarOperacion();
		obtenerInputPeriodo();
		obtenerCantidadAdministracion();
	}

	public List<OperacionAdministracionTransfer> getLista() {
		return lista;
	}

	public void setLista(List<OperacionAdministracionTransfer> lista) {
		this.lista = lista;
	}

	public List<OperacionAdministracionTransfer> getListafiltro() {
		return listafiltro;
	}

	public void setListafiltro(List<OperacionAdministracionTransfer> listafiltro) {
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

	public OperacionAdministracionTransfer getEntidadseleccionadaTransfer() {
		return entidadseleccionadaTransfer;
	}

	public void setEntidadseleccionadaTransfer(OperacionAdministracionTransfer entidadseleccionadaTransfer) {
		this.entidadseleccionadaTransfer = entidadseleccionadaTransfer;
	}

	public boolean isBooRegistroEntidad() {
		return booRegistroEntidad;
	}

	public void setBooRegistroEntidad(boolean booRegistroEntidad) {
		this.booRegistroEntidad = booRegistroEntidad;
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

		if (entidadseleccionadaTransfer.getPuestoId() == null) {
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
			servicio.eliminarPorPeriodoidPuestoidCategoriaid(periodoActual.getId(),
					entidadseleccionadaTransfer.getPuestoId(), entidadseleccionadaTransfer.getCategoriaId());
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Registros eliminados", ""));
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
		lista = new ArrayList<>();
		listaEntidadAdministracion = new ArrayList<>();
		try {
			lista = servicio.listarPorPeriodoactualCategoriaAdministracionTransfer();
			System.out.println("Lista Transfer recuperada: " + lista.toString());
			listaEntidadAdministracion = servicio.listarPorPeriodoactualCategoriaAdministracion();
			System.out.println("Lista no Ransfers recuperada: " + listaEntidadAdministracion.toString());
		} catch (Exception e) {
			// TODO: handle exception
			lista = null;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error.", "Problemas al recuperar registros"));
		}

		listafiltro = lista;

	}

	public void obtenerCantidadAdministracion() {

		cantidadAdministracion = listaPuesto.size() * periodoActual.getDias();
		System.out.println("cantidadAdministracion = " + cantidadAdministracion);

		if (listafiltro == null || listafiltro.isEmpty()) {
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

		System.out.println("Filtro puestos por lista de Administracion");

		listaPuestoFiltro = new ArrayList<>();

		if (lista == null || lista.isEmpty()) {

			listaPuestoFiltro = listaPuesto;

		} else {

			for (int i = 0; i <= listaPuesto.size() - 1; i++) {

				boolean consideracion = false;

				for (int j = 0; j <= lista.size() - 1; j++) {
					if (listaPuesto.get(i).getId() == lista.get(j).getPuestoId()) {
						System.out.println("El valor " + listaPuesto.get(i).getId() + " SI Coincide con: "
								+ lista.get(j).getPuestoId());
						if (lista.get(j).getDias() == periodoActual.getDias()) {
							consideracion = false;
							break;
						} else {
							consideracion = true;
							break;
						}
					} else {
						System.out.println("El valor " + listaPuesto.get(i).getId() + " NO Coincide con: "
								+ lista.get(j).getPuestoId());
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
			listaPuesto = puestoService.listarFiltro(true);
			System.out.println("Lista Puesto: " + listaPuesto.toString());
		} catch (Exception e) {
			// TODO: handle exception
			listaPuesto = null;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error.", "Problemas al recuperar registros puesto"));
		}
	}

	public void limpiarEntidad() {
		entidad = new Operacion();
		entidadseleccionada = new Operacion();
		listafiltro = lista;
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
			Integer cantidadRegistrosCrear = 0;

			OperacionAdministracionTransfer operacionTransfer = new OperacionAdministracionTransfer();

			List<OperacionAdministracionTransfer> listalocal = new ArrayList<>();

			listalocal = lista.stream().filter(x -> idPuesto.equals(x.getPuestoId())).collect(Collectors.toList());

			if (listalocal == null || listalocal.isEmpty()) {

				operacionTransfer.setCategoriaDes(descricionCategoria);
				operacionTransfer.setCategoriaId(idCategoria);
				operacionTransfer.setDias(periodoActual.getDias());
				operacionTransfer.setMontoTotal(periodoActual.getDias() * variableCostoAdministracion.getMonto());
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
				operacionTransfer.setMontoTotal((periodoActual.getDias() - listalocal.get(0).getDias())
						* variableCostoAdministracion.getMonto());
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

		try {
			servicio.generarOperacionAdministracion(listaPreOperacion, entidad.getDescripcion(),
					entidad.getFechaVencimiento());
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Creacion exitosa de operaciones", ""));
		} catch (Exception e) {
			// TODO: handle exception
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error en creacion de operaciones", ""));
			return "";
		}

		limpiarEntidad();
		listarEntidad();

		filtrarPuestosAdministracionSinCompletarOperacion();
		obtenerInputPeriodo();
		obtenerCantidadAdministracion();

		return "";
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
			if (lista.get(i).getPuestoDes().contains(filtro) || lista.get(i).getCategoriaDes().contains(filtro)) {
				System.out.println("lista: " + lista.get(i).toString());
				listafiltro.add(lista.get(i));
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
		
		System.out.println("Entidad Seleccionada: " + entidadseleccionadaTransfer.toString());

		if (entidadseleccionadaTransfer == null) {
			entidadseleccionadaTransfer = new OperacionAdministracionTransfer();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "No se selecciono ningun registro", ""));
		} else {

			booBtnEliminar = true;

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Seleccion de registro exitosa", ""));
		}
	}

	public void onRowUnselect(UnselectEvent event) {
		entidadseleccionadaTransfer = new OperacionAdministracionTransfer();

		booBtnEliminar = false;

		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Se anulo seleccion de registro ", ""));
	}

}
