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
import pe.com.gesadmin.entity.MedidaServicio;
import pe.com.gesadmin.entity.Periodo;
import pe.com.gesadmin.entity.transfer.FiltroTransfer;
import pe.com.gesadmin.entity.transfer.PeriodoTransfer;
import pe.com.gesadmin.service.AnioFiscalService;
import pe.com.gesadmin.service.MedidaServicioService;
import pe.com.gesadmin.service.PeriodoService;
import pe.com.gesadmin.service.PuestoService;
import pe.com.gesadmin.service.impl.AnioFiscalServiceImpl;
import pe.com.gesadmin.service.impl.MedidaServicioServiceImpl;
import pe.com.gesadmin.service.impl.PeriodoServiceImpl;
import pe.com.gesadmin.service.impl.PuestoServiceImpl;

@ManagedBean
@ViewScoped
public class ConsultaConsumoServicioBean {

	private List<MedidaServicio> lista = new ArrayList<>();
	private List<MedidaServicio> listafiltro;
	private MedidaServicio entidad = new MedidaServicio();
	private MedidaServicio entidadseleccionada = new MedidaServicio();

	private List<AnioFiscal> listaAnioFiscal;
	private List<Periodo> listaPeriodo;

	private List<FiltroTransfer> listaPuestoTransfers = new ArrayList<>();
	private List<FiltroTransfer> listaTipoServicioTransfers = new ArrayList<>();

	private List<FiltroTransfer> listaPuestoTransfersFiltro = new ArrayList<>();
	private List<FiltroTransfer> listaTipoServicioTransfersFiltro = new ArrayList<>();
	
	
	private PeriodoTransfer periodoTransfer = new PeriodoTransfer();

	private Integer idPuesto;
	private Integer idTipoServicio;

	private boolean booDetalle = false;
	private boolean booFiltro = false;

	private String filtro;

	@EJB
	private MedidaServicioService servicio = new MedidaServicioServiceImpl();

	@EJB
	private AnioFiscalService anioFiscalService = new AnioFiscalServiceImpl();

	@EJB
	private PeriodoService periodoService = new PeriodoServiceImpl();

	@EJB
	private PuestoService puestoService = new PuestoServiceImpl();


	public ConsultaConsumoServicioBean() {
		// TODO Auto-generated constructor stub
		filtro = null;
		entidad = new MedidaServicio();
		
		periodoTransfer = new PeriodoTransfer();

		listaPeriodo = null;
		listaPuestoTransfers = null;
		listaTipoServicioTransfers = null;


		idPuesto = null;
		idTipoServicio = null;
		
		booDetalle = false;
		booFiltro = false;
	}

	@PostConstruct
	public void init() {
		listarAnioFiscal();
	}

	public List<MedidaServicio> getLista() {
		return lista;
	}

	public void setLista(List<MedidaServicio> lista) {
		this.lista = lista;
	}

	public List<MedidaServicio> getListafiltro() {
		return listafiltro;
	}

	public void setListafiltro(List<MedidaServicio> listafiltro) {
		this.listafiltro = listafiltro;
	}

	public MedidaServicio getEntidad() {
		return entidad;
	}

	public void setEntidad(MedidaServicio entidad) {
		this.entidad = entidad;
	}

	public MedidaServicio getEntidadseleccionada() {
		return entidadseleccionada;
	}

	public void setEntidadseleccionada(MedidaServicio entidadseleccionada) {
		this.entidadseleccionada = entidadseleccionada;
	}

	public void setServicio(MedidaServicioService servicio) {
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

	public List<FiltroTransfer> getListaPuestoTransfers() {
		return listaPuestoTransfers;
	}

	public void setListaPuestoTransfers(List<FiltroTransfer> listaPuestoTransfers) {
		this.listaPuestoTransfers = listaPuestoTransfers;
	}

	public List<FiltroTransfer> getListaTipoServicioTransfers() {
		return listaTipoServicioTransfers;
	}

	public void setListaTipoServicioTransfers(List<FiltroTransfer> listaTipoServicioTransfers) {
		this.listaTipoServicioTransfers = listaTipoServicioTransfers;
	}

	public List<FiltroTransfer> getListaPuestoTransfersFiltro() {
		return listaPuestoTransfersFiltro;
	}

	public void setListaPuestoTransfersFiltro(List<FiltroTransfer> listaPuestoTransfersFiltro) {
		this.listaPuestoTransfersFiltro = listaPuestoTransfersFiltro;
	}

	public List<FiltroTransfer> getListaTipoServicioTransfersFiltro() {
		return listaTipoServicioTransfersFiltro;
	}

	public void setListaTipoServicioTransfersFiltro(List<FiltroTransfer> listaTipoServicioTransfersFiltro) {
		this.listaTipoServicioTransfersFiltro = listaTipoServicioTransfersFiltro;
	}

	public Integer getIdTipoServicio() {
		return idTipoServicio;
	}

	public void setIdTipoServicio(Integer idTipoServicio) {
		this.idTipoServicio = idTipoServicio;
	}

	public PeriodoTransfer getPeriodoTransfer() {
		return periodoTransfer;
	}

	public void setPeriodoTransfer(PeriodoTransfer periodoTransfer) {
		this.periodoTransfer = periodoTransfer;
	}
	
	

	public void recuperar() {

		entidad = new MedidaServicio();

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
			System.out.println("Consultar por Registros");
			
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

		obtenerListaPuestos();
		obtenerListaTipoServicio();

		listaPuestoTransfersFiltro = listaPuestoTransfers;
		listaTipoServicioTransfersFiltro = listaTipoServicioTransfers;
		
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
			entidad = new MedidaServicio();
			entidadseleccionada = new MedidaServicio();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "No se selecciono ningun registro", ""));
		} else {
			entidad = entidadseleccionada;
			booDetalle = true;
			booFiltro = false;

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Seleccion de registro exitosa", ""));
		}
	}

	public void onRowUnselect(UnselectEvent event) {
		entidad = new MedidaServicio();
		entidadseleccionada = new MedidaServicio();
		
		booDetalle = false;
		booFiltro = true;

		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Se anulo seleccion de registro ", ""));
	}

	public void limpiar() {
				
		entidad = new MedidaServicio();
		entidadseleccionada = new MedidaServicio();

		periodoTransfer = new PeriodoTransfer();
		idPuesto = null;


		listafiltro = null;
		listaPeriodo = null;

		listaPuestoTransfers = null;
		listaTipoServicioTransfers = null;
		
		listaPuestoTransfersFiltro = null;
		listaTipoServicioTransfersFiltro = null;
		

		booDetalle = false;
		booFiltro = false;

		lista = null;

	}
	
	public void limpiarFiltro() {
		booDetalle = false;
		booFiltro = true;
		
		listafiltro = null;
		listafiltro = lista;
		
		listaPuestoTransfersFiltro = null;
		listaTipoServicioTransfersFiltro = null;
		
		listaPuestoTransfersFiltro = listaPuestoTransfers;
		listaTipoServicioTransfersFiltro = listaTipoServicioTransfers;
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
			if (lista.get(i).getPuesto().getDescripcion().contains(filtro) 
					|| lista.get(i).getTipoServicio().getDescripcion().contains(filtro)) {
				System.out.println("lista: " + lista.get(i).toString());
				listafiltro.add(lista.get(i));
			}
		}
		filtro = null;
		return "";
	}



	public void obtenerListaTipoServicio() {

		listaTipoServicioTransfers = new ArrayList<>();
		List<FiltroTransfer> listaloLocal = new ArrayList<>();

		for (int i = 0; i <= listafiltro.size() - 1; i++) {
			listaloLocal.add(new FiltroTransfer(listafiltro.get(i).getTipoServicio().getId(),
					listafiltro.get(i).getTipoServicio().getDescripcion()));
		}

		listaTipoServicioTransfers = listaloLocal.stream().filter(distinctByKey(p -> p.getId())).collect(Collectors.toList() );
	}


	public void obtenerListaPuestos() {

		listaPuestoTransfers = new ArrayList<>();
		List<FiltroTransfer> listaloLocal = new ArrayList<>();

		for (int i = 0; i <= listafiltro.size() - 1; i++) {
			listaloLocal.add(new FiltroTransfer(listafiltro.get(i).getPuesto().getId(),
					listafiltro.get(i).getPuesto().getDescripcion()));
		}

		listaPuestoTransfers = listaloLocal.stream().filter(distinctByKey(p -> p.getId())).collect(Collectors.toList() );

	}

	public void actualizarListaTipo() {

		listaTipoServicioTransfersFiltro = new ArrayList<>();
		List<FiltroTransfer> listaloLocal = new ArrayList<>();

		for (int i = 0; i <= listafiltro.size() - 1; i++) {
			listaloLocal.add(new FiltroTransfer(listafiltro.get(i).getTipoServicio().getId(),
					listafiltro.get(i).getTipoServicio().getDescripcion()));
		}

		listaTipoServicioTransfersFiltro = listaloLocal.stream().filter(distinctByKey(p -> p.getId())).collect(Collectors.toList() );
	}
	

	public void actualizarListaPuestos() {

		listaPuestoTransfersFiltro = new ArrayList<>();
		List<FiltroTransfer> listaloLocal = new ArrayList<>();

		for (int i = 0; i <= listafiltro.size() - 1; i++) {
			listaloLocal.add(new FiltroTransfer(listafiltro.get(i).getPuesto().getId(),
					listafiltro.get(i).getPuesto().getDescripcion()));
		}

		listaPuestoTransfersFiltro = listaloLocal.stream().filter(distinctByKey(p -> p.getId())).collect(Collectors.toList() );
	}
	
	
	
	public void actualizarCatalogoFiltros() {
		actualizarListaPuestos();
		actualizarListaTipo();
	}
	
	

	public void actualizarListaEntidadFiltroPuesto() {

		List<MedidaServicio> listalocal = new ArrayList<>();

		listalocal = listafiltro.stream().filter(x -> idPuesto.equals(x.getPuesto().getId()))
				.collect(Collectors.toList());

		listafiltro = null;
		listafiltro = listalocal;

		idPuesto = null;
		idTipoServicio = null;
		
		booDetalle = false;
		
		filtro = null;
		
		actualizarCatalogoFiltros();
	}

	public void actualizarListaEntidadFiltroTipo() {

		List<MedidaServicio> listalocal = new ArrayList<>();

		listalocal = listafiltro.stream().filter(x -> idTipoServicio.equals(x.getTipoServicio().getId()))
				.collect(Collectors.toList());

		listafiltro = null;
		listafiltro = listalocal;

		idPuesto = null;
		idTipoServicio = null;
		
		booDetalle = false;
		
		filtro = null;
		
		actualizarCatalogoFiltros();
	}
	
	
	public void cerrarDetalle() {
		booDetalle = false;
		booFiltro = true;
	}


	
	public static <T> Predicate<T> distinctByKey(
		    Function<? super T, ?> keyExtractor) {
		   
		    Map<Object, Boolean> seen = new ConcurrentHashMap<>(); 
		    return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null; 
		}

}
