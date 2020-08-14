package pe.com.gesadmin.managed;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import pe.com.gesadmin.entity.AnioFiscal;
import pe.com.gesadmin.entity.Operacion;
import pe.com.gesadmin.entity.Periodo;
import pe.com.gesadmin.entity.transfer.FiltroTransfer;
import pe.com.gesadmin.entity.transfer.PeriodoTransfer;
import pe.com.gesadmin.service.AnioFiscalService;
import pe.com.gesadmin.service.OperacionService;
import pe.com.gesadmin.service.PeriodoService;
import pe.com.gesadmin.service.PuestoPersonaCargoService;
import pe.com.gesadmin.service.PuestoService;
import pe.com.gesadmin.service.impl.AnioFiscalServiceImpl;
import pe.com.gesadmin.service.impl.OperacionServiceImpl;
import pe.com.gesadmin.service.impl.PeriodoServiceImpl;
import pe.com.gesadmin.service.impl.PuestoPersonaCargoServiceImpl;
import pe.com.gesadmin.service.impl.PuestoServiceImpl;

@ManagedBean
@ViewScoped
public class ConsultaOperacionBean {

	private List<Operacion> lista = new ArrayList<>();
	private List<Operacion> listafiltro;
	private Operacion entidad = new Operacion();
	private Operacion entidadseleccionada = new Operacion();

	private List<AnioFiscal> listaAnioFiscal;
	private List<Periodo> listaPeriodo;

	private List<FiltroTransfer> listaEstatusOperacionTransfers = new ArrayList<>();
	private List<FiltroTransfer> listaPuestoTransfers = new ArrayList<>();
	private List<FiltroTransfer> listaTipoOperacionTransfers = new ArrayList<>();
	private List<FiltroTransfer> listaCategoriaTransfers = new ArrayList<>();

	private List<FiltroTransfer> listaEstatusOperacionTransfersFiltro = new ArrayList<>();
	private List<FiltroTransfer> listaPuestoTransfersFiltro = new ArrayList<>();
	private List<FiltroTransfer> listaTipoOperacionTransfersFiltro = new ArrayList<>();
	private List<FiltroTransfer> listaCategoriaTransfersFiltro = new ArrayList<>();

	private PeriodoTransfer periodoTransfer = new PeriodoTransfer();

	private Integer idAnioFiscal;
	private Integer idPeriodo;

	private Integer idEstatus;
	private Integer idPuesto;
	private Integer idTipo;
	private Integer idCategoria;

	private boolean booDetalle = false;
	private boolean booFiltro = false;

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

	public ConsultaOperacionBean() {
		// TODO Auto-generated constructor stub
		filtro = null;
		entidad = new Operacion();

		listaPeriodo = null;
		listaEstatusOperacionTransfers = null;
		listaPuestoTransfers = null;
		listaTipoOperacionTransfers = null;
		listaCategoriaTransfers = null;

		periodoTransfer = new PeriodoTransfer();

		idAnioFiscal = null;
		idPeriodo = null;
		idPuesto = null;

		booDetalle = false;
		booFiltro = false;
	}

	@PostConstruct
	public void init() {
		listarAnioFiscal();
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

	public boolean isBooFiltro() {
		return booFiltro;
	}

	public void setBooFiltro(boolean booFiltro) {
		this.booFiltro = booFiltro;
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

	public void setPuestoPersonaCargoService(PuestoPersonaCargoService puestoPersonaCargoService) {
		this.puestoPersonaCargoService = puestoPersonaCargoService;
	}

	public List<FiltroTransfer> getListaEstatusOperacionTransfers() {
		return listaEstatusOperacionTransfers;
	}

	public void setListaEstatusOperacionTransfers(List<FiltroTransfer> listaEstatusOperacionTransfers) {
		this.listaEstatusOperacionTransfers = listaEstatusOperacionTransfers;
	}

	public List<FiltroTransfer> getListaPuestoTransfers() {
		return listaPuestoTransfers;
	}

	public void setListaPuestoTransfers(List<FiltroTransfer> listaPuestoTransfers) {
		this.listaPuestoTransfers = listaPuestoTransfers;
	}

	public List<FiltroTransfer> getListaTipoOperacionTransfers() {
		return listaTipoOperacionTransfers;
	}

	public void setListaTipoOperacionTransfers(List<FiltroTransfer> listaTipoOperacionTransfers) {
		this.listaTipoOperacionTransfers = listaTipoOperacionTransfers;
	}

	public List<FiltroTransfer> getListaCategoriaTransfers() {
		return listaCategoriaTransfers;
	}

	public void setListaCategoriaTransfers(List<FiltroTransfer> listaCategoriaTransfers) {
		this.listaCategoriaTransfers = listaCategoriaTransfers;
	}

	public List<FiltroTransfer> getListaEstatusOperacionTransfersFiltro() {
		return listaEstatusOperacionTransfersFiltro;
	}

	public void setListaEstatusOperacionTransfersFiltro(List<FiltroTransfer> listaEstatusOperacionTransfersFiltro) {
		this.listaEstatusOperacionTransfersFiltro = listaEstatusOperacionTransfersFiltro;
	}

	public List<FiltroTransfer> getListaPuestoTransfersFiltro() {
		return listaPuestoTransfersFiltro;
	}

	public void setListaPuestoTransfersFiltro(List<FiltroTransfer> listaPuestoTransfersFiltro) {
		this.listaPuestoTransfersFiltro = listaPuestoTransfersFiltro;
	}

	public List<FiltroTransfer> getListaTipoOperacionTransfersFiltro() {
		return listaTipoOperacionTransfersFiltro;
	}

	public void setListaTipoOperacionTransfersFiltro(List<FiltroTransfer> listaTipoOperacionTransfersFiltro) {
		this.listaTipoOperacionTransfersFiltro = listaTipoOperacionTransfersFiltro;
	}

	public List<FiltroTransfer> getListaCategoriaTransfersFiltro() {
		return listaCategoriaTransfersFiltro;
	}

	public void setListaCategoriaTransfersFiltro(List<FiltroTransfer> listaCategoriaTransfersFiltro) {
		this.listaCategoriaTransfersFiltro = listaCategoriaTransfersFiltro;
	}

	public Integer getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}

	public Integer getIdEstatus() {
		return idEstatus;
	}

	public void setIdEstatus(Integer idEstatus) {
		this.idEstatus = idEstatus;
	}

	public Integer getIdTipo() {
		return idTipo;
	}

	public void setIdTipo(Integer idTipo) {
		this.idTipo = idTipo;
	}

	public PeriodoTransfer getPeriodoTransfer() {
		return periodoTransfer;
	}

	public void setPeriodoTransfer(PeriodoTransfer periodoTransfer) {
		this.periodoTransfer = periodoTransfer;
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
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Problemas al recuperar registros aÃ±o fiscal", ""));
		}
	}

	public void listarEntidad() {
		
		boolean booanio  = (periodoTransfer.getIdAnio() != null) ? true : false;
		boolean booperiodo  = (periodoTransfer.getIdPeriodo() != null) ? true : false;
		
		lista = new ArrayList<>();
		
		if(booperiodo) {
			System.out.println("Consultar por Id Periodo");
			
			try {
				lista = servicio.listarPorPeriodoId(periodoTransfer.getIdPeriodo());
			} catch (Exception e) {
				// TODO: handle exception
				lista = null;
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Problemas al recuperar registros", ""));
			}
			
		}else if(booanio == true && booperiodo == false) {
			System.out.println("Consultar por Id Anio");
			
			try {
				lista = servicio.listarPorAnioId(periodoTransfer.getIdAnio());
			} catch (Exception e) {
				// TODO: handle exception
				lista = null;
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Problemas al recuperar registros", ""));
			}
			
			
		}else if(booperiodo == false && booanio == false) {
			System.out.println("Consultar por Registros Activos");
			
			try {
				lista = servicio.listarActivo();
			} catch (Exception e) {
				// TODO: handle exception
				lista = null;
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Problemas al recuperar registros", ""));
			}
		}else {
			System.out.println("Consultar no reconocida");
			lista = null;
		}
		

		listafiltro = lista;

		obtenerListaEstatus();
		obtenerListaPuestos();
		obtenerListaTipo();
		obtenerListaCategoria();

		listaEstatusOperacionTransfersFiltro = listaEstatusOperacionTransfers;
		listaPuestoTransfersFiltro = listaPuestoTransfers;
		listaTipoOperacionTransfersFiltro = listaTipoOperacionTransfers;
		listaCategoriaTransfersFiltro = listaCategoriaTransfers;

		booFiltro = true;

	}
	
	
	
	
	

	public void listarPeriodo() {

		if (periodoTransfer.getIdAnio() == null) {

			listaPeriodo = null;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Seleccione periodo de año fiscal", ""));
		} else {

			listaPeriodo = new ArrayList<>();
			try {
				listaPeriodo = periodoService.listarPorIdAnioFiscal(periodoTransfer.getIdAnio());
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

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Seleccion de registro exitosa", ""));
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

		idAnioFiscal = null;
		idPeriodo = null;
		idPuesto = null;
		idCategoria = null;
		idEstatus = null;
		idTipo = null;

		listafiltro = null;
		listaPeriodo = null;

		listaEstatusOperacionTransfers = null;
		listaPuestoTransfers = null;
		listaTipoOperacionTransfers = null;
		listaCategoriaTransfers = null;

		listaEstatusOperacionTransfersFiltro = null;
		listaPuestoTransfersFiltro = null;
		listaTipoOperacionTransfersFiltro = null;
		listaCategoriaTransfersFiltro = null;

		periodoTransfer = new PeriodoTransfer();

		booDetalle = false;
		booFiltro = false;

		lista = null;

	}

	public void limpiarFiltro() {
		booDetalle = false;
		booFiltro = true;

		listafiltro = null;
		listafiltro = lista;

		listaEstatusOperacionTransfersFiltro = null;
		listaPuestoTransfersFiltro = null;
		listaTipoOperacionTransfersFiltro = null;
		listaCategoriaTransfersFiltro = null;

		listaEstatusOperacionTransfersFiltro = listaEstatusOperacionTransfers;
		listaPuestoTransfersFiltro = listaPuestoTransfers;
		listaTipoOperacionTransfersFiltro = listaTipoOperacionTransfers;
		listaCategoriaTransfersFiltro = listaCategoriaTransfers;
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

	public void obtenerListaEstatus() {

		listaEstatusOperacionTransfers = new ArrayList<>();
		List<FiltroTransfer> listaloLocal = new ArrayList<>();

		for (int i = 0; i <= listafiltro.size() - 1; i++) {
			listaloLocal.add(new FiltroTransfer(listafiltro.get(i).getEstatusOperacion().getId(),
					listafiltro.get(i).getEstatusOperacion().getDescripcion()));
		}

		listaEstatusOperacionTransfers = listaloLocal.stream().filter(distinctByKey(p -> p.getId()))
				.collect(Collectors.toList());
	}

	public void obtenerListaTipo() {

		listaTipoOperacionTransfers = new ArrayList<>();
		List<FiltroTransfer> listaloLocal = new ArrayList<>();

		for (int i = 0; i <= listafiltro.size() - 1; i++) {
			listaloLocal.add(new FiltroTransfer(listafiltro.get(i).getTipoOperacion().getId(),
					listafiltro.get(i).getTipoOperacion().getDescripcion()));
		}

		listaTipoOperacionTransfers = listaloLocal.stream().filter(distinctByKey(p -> p.getId()))
				.collect(Collectors.toList());
	}

	public void obtenerListaCategoria() {

		listaCategoriaTransfers = new ArrayList<>();
		List<FiltroTransfer> listaloLocal = new ArrayList<>();

		for (int i = 0; i <= listafiltro.size() - 1; i++) {
			listaloLocal.add(new FiltroTransfer(listafiltro.get(i).getCategoriaOperacion().getId(),
					listafiltro.get(i).getCategoriaOperacion().getDescripcion()));
		}

		listaCategoriaTransfers = listaloLocal.stream().filter(distinctByKey(p -> p.getId()))
				.collect(Collectors.toList());
	}

	public void obtenerListaPuestos() {

		listaPuestoTransfers = new ArrayList<>();
		List<FiltroTransfer> listaloLocal = new ArrayList<>();

		for (int i = 0; i <= listafiltro.size() - 1; i++) {
			listaloLocal.add(new FiltroTransfer(listafiltro.get(i).getPuesto().getId(),
					listafiltro.get(i).getPuesto().getDescripcion()));
		}

		listaPuestoTransfers = listaloLocal.stream().filter(distinctByKey(p -> p.getId())).collect(Collectors.toList());

	}

	public void actualizarListaEstatus() {

		listaEstatusOperacionTransfersFiltro = new ArrayList<>();
		List<FiltroTransfer> listaloLocal = new ArrayList<>();

		for (int i = 0; i <= listafiltro.size() - 1; i++) {
			listaloLocal.add(new FiltroTransfer(listafiltro.get(i).getEstatusOperacion().getId(),
					listafiltro.get(i).getEstatusOperacion().getDescripcion()));
		}

		listaEstatusOperacionTransfersFiltro = listaloLocal.stream().filter(distinctByKey(p -> p.getId()))
				.collect(Collectors.toList());
	}

	public void actualizarListaTipo() {

		listaTipoOperacionTransfersFiltro = new ArrayList<>();
		List<FiltroTransfer> listaloLocal = new ArrayList<>();

		for (int i = 0; i <= listafiltro.size() - 1; i++) {
			listaloLocal.add(new FiltroTransfer(listafiltro.get(i).getTipoOperacion().getId(),
					listafiltro.get(i).getTipoOperacion().getDescripcion()));
		}

		listaTipoOperacionTransfersFiltro = listaloLocal.stream().filter(distinctByKey(p -> p.getId()))
				.collect(Collectors.toList());
	}

	public void actualizarListaCategoria() {

		listaCategoriaTransfersFiltro = new ArrayList<>();
		List<FiltroTransfer> listaloLocal = new ArrayList<>();

		for (int i = 0; i <= listafiltro.size() - 1; i++) {
			listaloLocal.add(new FiltroTransfer(listafiltro.get(i).getCategoriaOperacion().getId(),
					listafiltro.get(i).getCategoriaOperacion().getDescripcion()));
		}

		listaCategoriaTransfersFiltro = listaloLocal.stream().filter(distinctByKey(p -> p.getId()))
				.collect(Collectors.toList());
	}

	public void actualizarListaPuestos() {

		listaPuestoTransfersFiltro = new ArrayList<>();
		List<FiltroTransfer> listaloLocal = new ArrayList<>();

		for (int i = 0; i <= listafiltro.size() - 1; i++) {
			listaloLocal.add(new FiltroTransfer(listafiltro.get(i).getPuesto().getId(),
					listafiltro.get(i).getPuesto().getDescripcion()));
		}

		listaPuestoTransfersFiltro = listaloLocal.stream().filter(distinctByKey(p -> p.getId()))
				.collect(Collectors.toList());
	}

	public void actualizarCatalogoFiltros() {
		actualizarListaEstatus();
		actualizarListaPuestos();
		actualizarListaTipo();
		actualizarListaCategoria();
	}

	public void actualizarListaEntidadFiltroPuesto() {

		List<Operacion> listalocal = new ArrayList<>();

		listalocal = listafiltro.stream().filter(x -> idPuesto.equals(x.getPuesto().getId()))
				.collect(Collectors.toList());

		listafiltro = null;
		listafiltro = listalocal;

		idPuesto = null;
		idEstatus = null;
		idTipo = null;
		idCategoria = null;

		booDetalle = false;

		filtro = null;

		actualizarCatalogoFiltros();
	}

	public void actualizarListaEntidadFiltroTipo() {

		List<Operacion> listalocal = new ArrayList<>();

		listalocal = listafiltro.stream().filter(x -> idTipo.equals(x.getTipoOperacion().getId()))
				.collect(Collectors.toList());

		listafiltro = null;
		listafiltro = listalocal;

		idPuesto = null;
		idEstatus = null;
		idTipo = null;
		idCategoria = null;

		booDetalle = false;

		filtro = null;

		actualizarCatalogoFiltros();
	}

	public void actualizarListaEntidadFiltroEstatus() {

		List<Operacion> listalocal = new ArrayList<>();

		listalocal = listafiltro.stream().filter(x -> idEstatus.equals(x.getEstatusOperacion().getId()))
				.collect(Collectors.toList());

		listafiltro = null;
		listafiltro = listalocal;

		idPuesto = null;
		idEstatus = null;
		idTipo = null;
		idCategoria = null;

		booDetalle = false;

		filtro = null;

		actualizarCatalogoFiltros();
	}

	public void actualizarListaEntidadFiltroCategoria() {

		List<Operacion> listalocal = new ArrayList<>();

		listalocal = listafiltro.stream().filter(x -> idCategoria.equals(x.getCategoriaOperacion().getId()))
				.collect(Collectors.toList());

		listafiltro = null;
		listafiltro = listalocal;

		idPuesto = null;
		idEstatus = null;
		idTipo = null;
		idCategoria = null;

		booDetalle = false;

		filtro = null;

		actualizarCatalogoFiltros();
	}

	public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {

		Map<Object, Boolean> seen = new ConcurrentHashMap<>();
		return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
	}

}
