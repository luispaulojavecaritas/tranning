package pe.com.gesadmin.managed;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.TransferEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.DualListModel;

import pe.com.gesadmin.entity.Operacion;
import pe.com.gesadmin.entity.Periodo;
import pe.com.gesadmin.entity.Proveedor;
import pe.com.gesadmin.entity.Puesto;
import pe.com.gesadmin.entity.CategoriaOperacion;
import pe.com.gesadmin.entity.EstatusOperacion;
import pe.com.gesadmin.entity.TipoOperacion;
import pe.com.gesadmin.service.OperacionService;
import pe.com.gesadmin.service.PeriodoService;
import pe.com.gesadmin.service.ProveedorService;
import pe.com.gesadmin.service.PuestoService;
import pe.com.gesadmin.service.CategoriaOperacionService;
import pe.com.gesadmin.service.EstatusOperacionService;
import pe.com.gesadmin.service.TipoOperacionService;
import pe.com.gesadmin.service.impl.OperacionServiceImpl;
import pe.com.gesadmin.service.impl.PeriodoServiceImpl;
import pe.com.gesadmin.service.impl.ProveedorServiceImpl;
import pe.com.gesadmin.service.impl.PuestoServiceImpl;
import pe.com.gesadmin.service.impl.CategoriaOperacionServiceImpl;
import pe.com.gesadmin.service.impl.EstatusOperacionServiceImpl;
import pe.com.gesadmin.service.impl.TipoOperacionServiceImpl;

@ManagedBean
@ViewScoped
public class OperacionBean {

	private List<Operacion> lista = new ArrayList<>();
	private List<Operacion> listafiltro;
	private Operacion entidad = new Operacion();
	private Operacion entidadseleccionada = new Operacion();

	private List<TipoOperacion> listaTipoOrden = new ArrayList<>();

	private List<Puesto> listaPuesto = new ArrayList<Puesto>();
	private List<Puesto> listaPuestoSeleccionado = new ArrayList<Puesto>();
	private List<Puesto> listaDeudorFiltro = new ArrayList<Puesto>();

	private List<Proveedor> listaProveedor = new ArrayList<>();
	private List<Proveedor> listaAcreedorFiltro = new ArrayList<Proveedor>();

	private List<EstatusOperacion> listaEstatusOperacion = new ArrayList<>();
	private List<Periodo> listaPeriodo = new ArrayList<>();
	private List<CategoriaOperacion> listaCategoriaOperacion = new ArrayList<>();

	private String[] puestosSeleccionados;
	private List<String> puestosLista;

	private String filtro;

	private boolean booIngreso = false;
	private boolean booEgreso = false;
	private boolean booDetalle = false;

	@EJB
	private OperacionService servicio = new OperacionServiceImpl();
	@EJB
	private TipoOperacionService tipoOperacionService = new TipoOperacionServiceImpl();
	@EJB
	private PuestoService puestoService = new PuestoServiceImpl();
	@EJB
	private EstatusOperacionService situacionOperacionService = new EstatusOperacionServiceImpl();
	@EJB
	private PeriodoService periodoService = new PeriodoServiceImpl();
	@EJB
	private CategoriaOperacionService categoriaOperacionService = new CategoriaOperacionServiceImpl();
	@EJB
	private ProveedorService proveedorService = new ProveedorServiceImpl();

	public OperacionBean() {
		// TODO Auto-generated constructor stub
		filtro = null;
		entidad = new Operacion();
		listaCategoriaOperacion = new ArrayList<>();
		booIngreso = false;
		booEgreso = false;
		booDetalle = false;

		puestosSeleccionados = null;
		puestosLista = null;

		listaDeudorFiltro = null;
		listaAcreedorFiltro = null;
	}

	@PostConstruct
	public void init() {
		listarEntidad();
		listarPeriodo();
		listarPuesto();
		listarSituacionOrden();
		listarTipoOrden();
		listarProveedor();

	}

	public List<Puesto> getListaDeudorFiltro() {
		return listaDeudorFiltro;
	}

	public void setListaDeudorFiltro(List<Puesto> listaDeudorFiltro) {
		this.listaDeudorFiltro = listaDeudorFiltro;
	}

	public List<Proveedor> getListaAcreedorFiltro() {
		return listaAcreedorFiltro;
	}

	public void setListaAcreedorFiltro(List<Proveedor> listaAcreedorFiltro) {
		this.listaAcreedorFiltro = listaAcreedorFiltro;
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

	public List<TipoOperacion> getListaTipoOrden() {
		return listaTipoOrden;
	}

	public void setListaTipoOrden(List<TipoOperacion> listaTipoOrden) {
		this.listaTipoOrden = listaTipoOrden;
	}

	public List<Puesto> getListaPuesto() {
		return listaPuesto;
	}

	public void setListaPuesto(List<Puesto> listaPuesto) {
		this.listaPuesto = listaPuesto;
	}

	public List<EstatusOperacion> getlistaEstatusOperacion() {
		return listaEstatusOperacion;
	}

	public void setlistaEstatusOperacion(List<EstatusOperacion> listaEstatusOperacion) {
		this.listaEstatusOperacion = listaEstatusOperacion;
	}

	public List<Periodo> getListaPeriodo() {
		return listaPeriodo;
	}

	public void setListaPeriodo(List<Periodo> listaPeriodo) {
		this.listaPeriodo = listaPeriodo;
	}

	public void setPeriodoService(PeriodoService periodoService) {
		this.periodoService = periodoService;
	}

	public List<Puesto> getListaPuestoSeleccionado() {
		return listaPuestoSeleccionado;
	}

	public void setListaPuestoSeleccionado(List<Puesto> listaPuestoSeleccionado) {
		this.listaPuestoSeleccionado = listaPuestoSeleccionado;
	}

	public List<EstatusOperacion> getListaEstatusOperacion() {
		return listaEstatusOperacion;
	}

	public void setListaEstatusOperacion(List<EstatusOperacion> listaEstatusOperacion) {
		this.listaEstatusOperacion = listaEstatusOperacion;
	}

	public List<CategoriaOperacion> getListaCategoriaOperacion() {
		return listaCategoriaOperacion;
	}

	public void setListaCategoriaOperacion(List<CategoriaOperacion> listaCategoriaOperacion) {
		this.listaCategoriaOperacion = listaCategoriaOperacion;
	}

	public void setCategoriaOperacionService(CategoriaOperacionService categoriaOperacionService) {
		this.categoriaOperacionService = categoriaOperacionService;
	}

	public boolean isBooIngreso() {
		return booIngreso;
	}

	public void setBooIngreso(boolean booIngreso) {
		this.booIngreso = booIngreso;
	}

	public boolean isBooEgreso() {
		return booEgreso;
	}

	public void setBooEgreso(boolean booEgreso) {
		this.booEgreso = booEgreso;
	}

	public void setTipoOperacionService(TipoOperacionService tipoOperacionService) {
		this.tipoOperacionService = tipoOperacionService;
	}

	public void setPuestoService(PuestoService puestoService) {
		this.puestoService = puestoService;
	}

	public void setSituacionOperacionService(EstatusOperacionService situacionOperacionService) {
		this.situacionOperacionService = situacionOperacionService;
	}

	public List<Proveedor> getListaProveedor() {
		return listaProveedor;
	}

	public void setListaProveedor(List<Proveedor> listaProveedor) {
		this.listaProveedor = listaProveedor;
	}

	public void setProveedorService(ProveedorService proveedorService) {
		this.proveedorService = proveedorService;
	}

	public String[] getPuestosSeleccionados() {
		return puestosSeleccionados;
	}

	public void setPuestosSeleccionados(String[] puestosSeleccionados) {
		this.puestosSeleccionados = puestosSeleccionados;
	}

	public List<String> getPuestosLista() {
		return puestosLista;
	}

	public void setPuestosLista(List<String> puestosLista) {
		this.puestosLista = puestosLista;
	}

	public boolean isBooDetalle() {
		return booDetalle;
	}

	public void setBooDetalle(boolean booDetalle) {
		this.booDetalle = booDetalle;
	}

	public String guardar() {

		if (booIngreso) {
			entidad.setPersonaResponsableOperacion(null);
			generarOperacionLista();

		} else if (booEgreso) {
			entidad.setPersonaResponsableOperacion(null);
			generarOperacionUnico();
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "No se reconoce tipo de operacion a crear", ""));
		}

		limpiar();
		listarEntidad();

		return "";
	}

	public String generarOperacionUnico() {

		List<Operacion> listaOrdenesGenerar = new ArrayList<Operacion>();

		listaPuestoSeleccionado = new ArrayList<>();
		listaPuestoSeleccionado = convertirPuestoArrayToList(puestosSeleccionados);

		if (listaPuestoSeleccionado == null || listaPuestoSeleccionado.isEmpty()
				|| listaPuestoSeleccionado.size() == 0) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Seleccione Puestos para aplicar orden", ""));
			return "";
		}

		for (int i = 0; i <= listaPuestoSeleccionado.size() - 1; i++) {
			Operacion ordenGenerar = new Operacion();
			ordenGenerar.setTipoOperacion(new TipoOperacion(entidad.getTipoOperacion().getId()));
			ordenGenerar.setCategoriaOperacion(new CategoriaOperacion(entidad.getCategoriaOperacion().getId()));
			ordenGenerar.setPeriodo(new Periodo(entidad.getPeriodo().getId()));
			ordenGenerar.setDescripcion(entidad.getDescripcion());
			ordenGenerar.setEstatusOperacion(new EstatusOperacion(entidad.getEstatusOperacion().getId()));
			ordenGenerar.setMonto(entidad.getMonto());
			ordenGenerar.setFechaVencimiento(entidad.getFechaVencimiento());
			ordenGenerar.setEstado(entidad.getEstado());
			ordenGenerar.setPuesto(new Puesto(listaPuestoSeleccionado.get(i).getId()));
			ordenGenerar.setProveedor(new Proveedor(entidad.getProveedor().getId()));
			ordenGenerar.setPersonaResponsableOperacion(null);
			listaOrdenesGenerar.add(ordenGenerar);
		}

		try {
			servicio.crearlista(listaOrdenesGenerar);

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Excepcion en registrar lista de ordenes: " + e.toString());
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error en registrar lista de ordenes", ""));
			return "";
		}

		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Registros creados", ""));
		return "";

	}

	public String generarOperacionLista() {
		System.out.println("generarOperacionLista");

		List<Operacion> listaOrdenesGenerar = new ArrayList<Operacion>();

		listaPuestoSeleccionado = new ArrayList<>();
		listaPuestoSeleccionado = convertirPuestoArrayToList(puestosSeleccionados);
		System.out.println("Array: " + puestosSeleccionados.toString());

		if (listaPuestoSeleccionado == null || listaPuestoSeleccionado.isEmpty()
				|| listaPuestoSeleccionado.size() == 0) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Seleccione Puestos para aplicar orden", ""));
			return "";
		}

		for (int i = 0; i <= listaPuestoSeleccionado.size() - 1; i++) {
			Operacion ordenGenerar = new Operacion();
			ordenGenerar.setTipoOperacion(new TipoOperacion(entidad.getTipoOperacion().getId()));
			ordenGenerar.setCategoriaOperacion(new CategoriaOperacion(entidad.getCategoriaOperacion().getId()));
			ordenGenerar.setPeriodo(new Periodo(entidad.getPeriodo().getId()));
			ordenGenerar.setDescripcion(entidad.getDescripcion());
			ordenGenerar.setEstatusOperacion(new EstatusOperacion(entidad.getEstatusOperacion().getId()));
			ordenGenerar.setMonto(entidad.getMonto());
			ordenGenerar.setFechaVencimiento(entidad.getFechaVencimiento());
			ordenGenerar.setEstado(entidad.getEstado());
			ordenGenerar.setPuesto(new Puesto(listaPuestoSeleccionado.get(i).getId()));
			ordenGenerar.setProveedor(new Proveedor(entidad.getProveedor().getId()));
			ordenGenerar.setPersonaResponsableOperacion(null);
			listaOrdenesGenerar.add(ordenGenerar);
		}

		try {
			servicio.crearlista(listaOrdenesGenerar);

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Excepcion en registrar lista de ordenes: " + e.toString());
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error en registrar lista de ordenes", ""));
			return "";
		}

		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Registros creados", ""));
		return "";

	}

	public String eliminar() {

		if (entidad.getId() == null) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Seleccione registro a eliminar", ""));
			limpiar();
			listarEntidad();
			return "";
		}

		entidad.setEstado(0);

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

	public void listarEntidad() {
		lista = new ArrayList<>();
		try {
			lista = servicio.listarActivo();
		} catch (Exception e) {
			// TODO: handle exception
			lista = null;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error.", "Problemas al recuperar registros"));
		}

		listafiltro = lista;
	}

	public void onRowSelect(SelectEvent event) {
		if (entidadseleccionada == null) {
			entidad = new Operacion();
			entidadseleccionada = new Operacion();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "No se selecciono ningun registro", ""));
		} else {
			recuperar();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Seleccion de registro exitosa", ""));
		}
	}

	public void recuperar() {

		Integer idEntidad = entidadseleccionada.getId();
		limpiar();

		entidadseleccionada = new Operacion();

		try {
			entidadseleccionada = servicio.recuperar(idEntidad);
			booDetalle = true;
		} catch (Exception e) {
			// TODO: handle exception
			entidadseleccionada = null;
			booDetalle = false;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error.", "Problemas al recuperar registro"));
		}
	}

	public void onRowUnselect(UnselectEvent event) {
		entidad = new Operacion();
		entidadseleccionada = new Operacion();
		booDetalle = false;

		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Se anulo seleccion de registro ", ""));
	}

	public void limpiar() {
		entidad = new Operacion();
		entidadseleccionada = new Operacion();
		puestosSeleccionados = null;

		listafiltro = lista;

		listaPuesto = null;
		listaPuestoSeleccionado = null;
		puestosLista = null;

		listaDeudorFiltro = null;
		listaAcreedorFiltro = null;

		listaCategoriaOperacion = null;

		booDetalle = false;
		booEgreso = false;
		booIngreso = false;

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
					|| lista.get(i).getCategoriaOperacion().getDescripcion().contains(filtro)
					|| lista.get(i).getTipoOperacion().getDescripcion().contains(filtro) 
					|| lista.get(i).getProveedor().getRazonSocial().contains(filtro) 
					|| lista.get(i).getPuesto().getDescripcion().contains(filtro)) {
				System.out.println("lista: " + lista.get(i).toString());
				listafiltro.add(lista.get(i));
			}
		}
		filtro = null;
		return "";
	}

	public void listarTipoOrden() {
		listaTipoOrden = new ArrayList<>();
		try {
			listaTipoOrden = tipoOperacionService.listarActivo();
		} catch (Exception e) {
			// TODO: handle exception
			listaTipoOrden = null;
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error.",
					"Problemas al recuperar registros tipos de orden"));

		}
	}

	public void listarPuesto() {
		listaPuesto = new ArrayList<>();
		try {
			listaPuesto = puestoService.listarActivo();
		} catch (Exception e) {
			// TODO: handle exception
			listaPuesto = null;
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error.",
					"Problemas al recuperar registros puestos"));

		}
	}

	public void listarSituacionOrden() {
		listaEstatusOperacion = new ArrayList<>();
		try {
			listaEstatusOperacion = situacionOperacionService.listarActivo();
		} catch (Exception e) {
			// TODO: handle exception
			listaEstatusOperacion = null;
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error.",
					"Problemas al recuperar registros situaciones orden"));

		}
	}

	public void listarPeriodo() {
		listaPeriodo = new ArrayList<>();
		try {
			listaPeriodo = periodoService.listarActivo();
		} catch (Exception e) {
			// TODO: handle exception
			listaPeriodo = null;
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error.",
					"Problemas al recuperar registros periodo"));

		}
	}

	public void listarCategoriaOperacion() {
		listaCategoriaOperacion = new ArrayList<>();
		try {
			listaCategoriaOperacion = categoriaOperacionService.listarActivo();
		} catch (Exception e) {
			// TODO: handle exception
			listaCategoriaOperacion = null;
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error.",
					"Problemas al recuperar registros categoria operacion"));

		}
	}

	public void onTransfer(TransferEvent event) {
		StringBuilder builder = new StringBuilder();
		for (Object item : event.getItems()) {
			builder.append(((Puesto) item).getDescripcion()).append("<br />");
		}

		FacesMessage msg = new FacesMessage();
		msg.setSeverity(FacesMessage.SEVERITY_INFO);
		msg.setSummary("Items Transferred");
		msg.setDetail(builder.toString());

		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onSelect(SelectEvent event) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Seleccion de registro exitosa", ""));
	}

	public void onUnselect(UnselectEvent event) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Se anulo seleccion de registro", ""));
	}

	public void onReorder() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "List Reordered", null));
	}

	public List<String> convertirListaPuestosToArray() {

		List<String> listaDescripcion = new ArrayList<>();
		for (int i = 0; i <= listaDeudorFiltro.size() - 1; i++) {
			listaDescripcion.add(listaDeudorFiltro.get(i).getDescripcion());
		}
		return listaDescripcion;
	}

	public List<Puesto> convertirPuestoArrayToList(String[] listaTarget) {
		System.out.println("listaDeudorFiltro: " + listaDeudorFiltro.toString());
		List<Puesto> listaPuestooo = new ArrayList<>();
		List<String> arregloList = Arrays.asList(listaTarget);
		for (int i = 0; i <= arregloList.size() - 1; i++) {
			for (int j = 0; j <= listaDeudorFiltro.size() - 1; j++) {
				if (listaDeudorFiltro.get(j).getDescripcion().equalsIgnoreCase(arregloList.get(i))) {
					listaPuestooo.add(new Puesto(listaDeudorFiltro.get(j).getId()));
				}
			}
		}
		return listaPuestooo;
	}

	public void listarCategorias() {

		Integer codigoTipoOperacion = entidad.getTipoOperacion().getId();
		TipoOperacion tipoOperacionLocal = new TipoOperacion();

		try {
			listaCategoriaOperacion = categoriaOperacionService.listarPordTipoOperacion(codigoTipoOperacion);
			tipoOperacionLocal = tipoOperacionService.recuperar(codigoTipoOperacion);

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Excepcion al listas categorias de Operacion: " + e.toString());
			listaCategoriaOperacion = null;
		}

		if (listaCategoriaOperacion == null || listaCategoriaOperacion.isEmpty()
				|| listaCategoriaOperacion.size() == 0) {
			booIngreso = false;
			booIngreso = false;

		} else {
			if (tipoOperacionLocal.getDescripcion().equalsIgnoreCase("INGRESO")) {
				booIngreso = true;
				booEgreso = false;
			} else if (tipoOperacionLocal.getDescripcion().equalsIgnoreCase("EGRESO")) {
				booIngreso = false;
				booEgreso = true;
			} else {
				booIngreso = false;
				booEgreso = false;
			}
		}

		try {
			listaDeudorFiltro = null;
			listaDeudorFiltro = puestoService.listarFiltro(booIngreso);

			puestosLista = convertirListaPuestosToArray();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Excepcion al obtener listaDeudorFiltro: " + e.toString());
			listaDeudorFiltro = null;
		}

		try {
			listaAcreedorFiltro = null;
			listaAcreedorFiltro = proveedorService.ListarFiltro(booEgreso);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Excepcion al obtener listaAcreedorFiltro: " + e.toString());
			listaAcreedorFiltro = null;
		}

	}

	public void listarProveedor() {

		try {
			listaProveedor = proveedorService.listarActivo();
		} catch (Exception e) {
			// TODO: handle exception
			listaProveedor = null;
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error.",
					"Problemas al recuperar registros proveedor"));
		}
	}

}
