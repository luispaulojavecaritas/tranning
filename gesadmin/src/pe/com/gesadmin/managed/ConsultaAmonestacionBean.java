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
import pe.com.gesadmin.entity.Amonestacion;
import pe.com.gesadmin.entity.Periodo;
import pe.com.gesadmin.entity.transfer.FiltroTransfer;
import pe.com.gesadmin.entity.transfer.PeriodoTransfer;
import pe.com.gesadmin.service.AnioFiscalService;
import pe.com.gesadmin.service.AmonestacionService;
import pe.com.gesadmin.service.PeriodoService;
import pe.com.gesadmin.service.PuestoPersonaCargoService;
import pe.com.gesadmin.service.PuestoService;
import pe.com.gesadmin.service.impl.AnioFiscalServiceImpl;
import pe.com.gesadmin.service.impl.AmonestacionServiceImpl;
import pe.com.gesadmin.service.impl.PeriodoServiceImpl;
import pe.com.gesadmin.service.impl.PuestoPersonaCargoServiceImpl;
import pe.com.gesadmin.service.impl.PuestoServiceImpl;

@ManagedBean
@ViewScoped
public class ConsultaAmonestacionBean {

	private List<Amonestacion> lista = new ArrayList<>();
	private List<Amonestacion> listafiltro;
	private Amonestacion entidad = new Amonestacion();
	private Amonestacion entidadseleccionada = new Amonestacion();

	private List<AnioFiscal> listaAnioFiscal;
	private List<Periodo> listaPeriodo;

	private List<FiltroTransfer> listaPuestoTransfers = new ArrayList<>();
	private List<FiltroTransfer> listaTipoAmonestacionTransfers = new ArrayList<>();
	private List<FiltroTransfer> listaPersonaTransfers = new ArrayList<>();

	private List<FiltroTransfer> listaPuestoTransfersFiltro = new ArrayList<>();
	private List<FiltroTransfer> listaTipoAmonestacionTransfersFiltro = new ArrayList<>();
	private List<FiltroTransfer> listaPersonaTransfersFiltro = new ArrayList<>();
	
	
	private PeriodoTransfer periodoTransfer = new PeriodoTransfer();

	private Integer idPuesto;
	private Integer idTipoAmonestacion;
	private Integer idPersona;

	private boolean booDetalle = false;
	private boolean booFiltro = false;

	private String filtro;

	@EJB
	private AmonestacionService servicio = new AmonestacionServiceImpl();

	@EJB
	private AnioFiscalService anioFiscalService = new AnioFiscalServiceImpl();

	@EJB
	private PeriodoService periodoService = new PeriodoServiceImpl();

	@EJB
	private PuestoService puestoService = new PuestoServiceImpl();

	@EJB
	private PuestoPersonaCargoService puestoPersonaCargoService = new PuestoPersonaCargoServiceImpl();

	public ConsultaAmonestacionBean() {
		// TODO Auto-generated constructor stub
		filtro = null;
		entidad = new Amonestacion();
		
		periodoTransfer = new PeriodoTransfer();

		listaPeriodo = null;
		listaPuestoTransfers = null;
		listaTipoAmonestacionTransfers = null;
		listaPersonaTransfers = null;


		idPuesto = null;
		idTipoAmonestacion = null;
		idPersona = null;	
		
		booDetalle = false;
		booFiltro = false;
	}

	@PostConstruct
	public void init() {
		listarAnioFiscal();
	}

	public List<Amonestacion> getLista() {
		return lista;
	}

	public void setLista(List<Amonestacion> lista) {
		this.lista = lista;
	}

	public List<Amonestacion> getListafiltro() {
		return listafiltro;
	}

	public void setListafiltro(List<Amonestacion> listafiltro) {
		this.listafiltro = listafiltro;
	}

	public Amonestacion getEntidad() {
		return entidad;
	}

	public void setEntidad(Amonestacion entidad) {
		this.entidad = entidad;
	}

	public Amonestacion getEntidadseleccionada() {
		return entidadseleccionada;
	}

	public void setEntidadseleccionada(Amonestacion entidadseleccionada) {
		this.entidadseleccionada = entidadseleccionada;
	}

	public void setServicio(AmonestacionService servicio) {
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

	public void setPuestoPersonaCargoService(PuestoPersonaCargoService puestoPersonaCargoService) {
		this.puestoPersonaCargoService = puestoPersonaCargoService;
	}

	public List<FiltroTransfer> getListaPuestoTransfers() {
		return listaPuestoTransfers;
	}

	public void setListaPuestoTransfers(List<FiltroTransfer> listaPuestoTransfers) {
		this.listaPuestoTransfers = listaPuestoTransfers;
	}

	public List<FiltroTransfer> getListaTipoAmonestacionTransfers() {
		return listaTipoAmonestacionTransfers;
	}

	public void setListaTipoAmonestacionTransfers(List<FiltroTransfer> listaTipoAmonestacionTransfers) {
		this.listaTipoAmonestacionTransfers = listaTipoAmonestacionTransfers;
	}

	public List<FiltroTransfer> getListaPuestoTransfersFiltro() {
		return listaPuestoTransfersFiltro;
	}

	public void setListaPuestoTransfersFiltro(List<FiltroTransfer> listaPuestoTransfersFiltro) {
		this.listaPuestoTransfersFiltro = listaPuestoTransfersFiltro;
	}

	public List<FiltroTransfer> getListaTipoAmonestacionTransfersFiltro() {
		return listaTipoAmonestacionTransfersFiltro;
	}

	public void setListaTipoAmonestacionTransfersFiltro(List<FiltroTransfer> listaTipoAmonestacionTransfersFiltro) {
		this.listaTipoAmonestacionTransfersFiltro = listaTipoAmonestacionTransfersFiltro;
	}

	public List<FiltroTransfer> getListaPersonaTransfers() {
		return listaPersonaTransfers;
	}

	public void setListaPersonaTransfers(List<FiltroTransfer> listaPersonaTransfers) {
		this.listaPersonaTransfers = listaPersonaTransfers;
	}

	public List<FiltroTransfer> getListaPersonaTransfersFiltro() {
		return listaPersonaTransfersFiltro;
	}

	public void setListaPersonaTransfersFiltro(List<FiltroTransfer> listaPersonaTransfersFiltro) {
		this.listaPersonaTransfersFiltro = listaPersonaTransfersFiltro;
	}

	public Integer getIdTipoAmonestacion() {
		return idTipoAmonestacion;
	}

	public void setIdTipoAmonestacion(Integer idTipoAmonestacion) {
		this.idTipoAmonestacion = idTipoAmonestacion;
	}

	public Integer getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(Integer idPersona) {
		this.idPersona = idPersona;
	}

	public PeriodoTransfer getPeriodoTransfer() {
		return periodoTransfer;
	}

	public void setPeriodoTransfer(PeriodoTransfer periodoTransfer) {
		this.periodoTransfer = periodoTransfer;
	}
	
	

	public void recuperar() {

		entidad = new Amonestacion();

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

		obtenerListaPersona();
		obtenerListaPuestos();
		obtenerListaTipo();

		listaPuestoTransfersFiltro = listaPuestoTransfers;
		listaTipoAmonestacionTransfersFiltro = listaTipoAmonestacionTransfers;
		listaPersonaTransfersFiltro = listaPersonaTransfers;
		
		booFiltro = true;

	}

	public void listarPeriodo() {

		if (periodoTransfer.getIdAnio() == null) {

			listaPeriodo = null;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Seleccione periodo de anio fiscal", ""));
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
			entidad = new Amonestacion();
			entidadseleccionada = new Amonestacion();
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
		entidad = new Amonestacion();
		entidadseleccionada = new Amonestacion();
		
		booDetalle = false;
		booFiltro = true;

		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Se anulo seleccion de registro ", ""));
	}

	public void limpiar() {
				
		entidad = new Amonestacion();
		entidadseleccionada = new Amonestacion();

		periodoTransfer = new PeriodoTransfer();
		idPuesto = null;


		listafiltro = null;
		listaPeriodo = null;

		listaPuestoTransfers = null;
		listaTipoAmonestacionTransfers = null;
		
		listaPuestoTransfersFiltro = null;
		listaTipoAmonestacionTransfersFiltro = null;
		

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
		listaTipoAmonestacionTransfersFiltro = null;
		listaPersonaTransfersFiltro = null;
		
		listaPuestoTransfersFiltro = listaPuestoTransfers;
		listaTipoAmonestacionTransfersFiltro = listaTipoAmonestacionTransfers;
		listaPersonaTransfersFiltro = listaPersonaTransfers;
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
					|| lista.get(i).getPuesto().getDescripcion().contains(filtro)) {
				System.out.println("lista: " + lista.get(i).toString());
				listafiltro.add(lista.get(i));
			}
		}
		filtro = null;
		return "";
	}



	public void obtenerListaTipo() {

		listaTipoAmonestacionTransfers = new ArrayList<>();
		List<FiltroTransfer> listaloLocal = new ArrayList<>();

		for (int i = 0; i <= listafiltro.size() - 1; i++) {
			listaloLocal.add(new FiltroTransfer(listafiltro.get(i).getTipoAmonestacion().getId(),
					listafiltro.get(i).getTipoAmonestacion().getDescripcion()));
		}

		listaTipoAmonestacionTransfers = listaloLocal.stream().filter(distinctByKey(p -> p.getId())).collect(Collectors.toList() );
	}
	
	public void obtenerListaPersona() {

		listaPersonaTransfers = new ArrayList<>();
		List<FiltroTransfer> listaloLocal = new ArrayList<>();

		for (int i = 0; i <= listafiltro.size() - 1; i++) {
			listaloLocal.add(new FiltroTransfer(listafiltro.get(i).getPersona().getId(),
					listafiltro.get(i).getPersona().getNombre() + " " + listafiltro.get(i).getPersona().getPaterno() + " " + listafiltro.get(i).getPersona().getMaterno()));
		}

		listaPersonaTransfers = listaloLocal.stream().filter(distinctByKey(p -> p.getId())).collect(Collectors.toList() );
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

		listaTipoAmonestacionTransfersFiltro = new ArrayList<>();
		List<FiltroTransfer> listaloLocal = new ArrayList<>();

		for (int i = 0; i <= listafiltro.size() - 1; i++) {
			listaloLocal.add(new FiltroTransfer(listafiltro.get(i).getTipoAmonestacion().getId(),
					listafiltro.get(i).getTipoAmonestacion().getDescripcion()));
		}

		listaTipoAmonestacionTransfersFiltro = listaloLocal.stream().filter(distinctByKey(p -> p.getId())).collect(Collectors.toList() );
	}
	
	public void actualizarListaPersona() {

		listaPersonaTransfersFiltro = new ArrayList<>();
		List<FiltroTransfer> listaloLocal = new ArrayList<>();

		for (int i = 0; i <= listafiltro.size() - 1; i++) {
			listaloLocal.add(new FiltroTransfer(listafiltro.get(i).getPersona().getId(),
					listafiltro.get(i).getPersona().getNombre() + " "+ listafiltro.get(i).getPersona().getPaterno() + " " + listafiltro.get(i).getPersona().getMaterno()));
		}

		listaPersonaTransfersFiltro = listaloLocal.stream().filter(distinctByKey(p -> p.getId())).collect(Collectors.toList() );
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
		actualizarListaPersona();
	}
	
	

	public void actualizarListaEntidadFiltroPuesto() {

		List<Amonestacion> listalocal = new ArrayList<>();

		listalocal = listafiltro.stream().filter(x -> idPuesto.equals(x.getPuesto().getId()))
				.collect(Collectors.toList());

		listafiltro = null;
		listafiltro = listalocal;

		idPuesto = null;
		idPersona = null;
		idTipoAmonestacion = null;
		
		booDetalle = false;
		
		filtro = null;
		
		actualizarCatalogoFiltros();
	}

	public void actualizarListaEntidadFiltroTipo() {

		List<Amonestacion> listalocal = new ArrayList<>();

		listalocal = listafiltro.stream().filter(x -> idTipoAmonestacion.equals(x.getTipoAmonestacion().getId()))
				.collect(Collectors.toList());

		listafiltro = null;
		listafiltro = listalocal;

		idPuesto = null;
		idPersona = null;
		idTipoAmonestacion = null;
		
		booDetalle = false;
		
		filtro = null;
		
		actualizarCatalogoFiltros();
	}
	
	public void cerrarDetalle() {
		booDetalle = false;
		booFiltro = true;
	}

	public void actualizarListaEntidadFiltroPersona() {

		List<Amonestacion> listalocal = new ArrayList<>();

		listalocal = listafiltro.stream().filter(x -> idPersona.equals(x.getPersona().getId()))
				.collect(Collectors.toList());

		listafiltro = null;
		listafiltro = listalocal;

		idPuesto = null;
		idPersona = null;
		idTipoAmonestacion = null;
		
		booDetalle = false;
		
		filtro = null;
		
		actualizarCatalogoFiltros();
	}
	
	
	public static <T> Predicate<T> distinctByKey(
		    Function<? super T, ?> keyExtractor) {
		   
		    Map<Object, Boolean> seen = new ConcurrentHashMap<>(); 
		    return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null; 
		}

}
