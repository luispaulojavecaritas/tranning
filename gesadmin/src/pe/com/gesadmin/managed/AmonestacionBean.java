package pe.com.gesadmin.managed;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import pe.com.gesadmin.entity.Periodo;
import pe.com.gesadmin.entity.Persona;
import pe.com.gesadmin.entity.Puesto;
import pe.com.gesadmin.entity.PuestoPersonaCargo;
import pe.com.gesadmin.entity.TipoAmonestacion;
import pe.com.gesadmin.entity.Amonestacion;
import pe.com.gesadmin.entity.MedidaServicio;
import pe.com.gesadmin.service.AmonestacionService;
import pe.com.gesadmin.service.PeriodoService;
import pe.com.gesadmin.service.PuestoPersonaCargoService;
import pe.com.gesadmin.service.PuestoService;
import pe.com.gesadmin.service.TipoAmonestacionService;
import pe.com.gesadmin.service.impl.AmonestacionServiceImpl;
import pe.com.gesadmin.service.impl.PeriodoServiceImpl;
import pe.com.gesadmin.service.impl.PuestoPersonaCargoServiceImpl;
import pe.com.gesadmin.service.impl.PuestoServiceImpl;
import pe.com.gesadmin.service.impl.TipoAmonestacionServiceImpl;

@ManagedBean
@ViewScoped
public class AmonestacionBean {
	
    @ManagedProperty("#{usuarioSesionBean}")
    private UsuarioSesionBean usuarioSesionBean;

	private List<Amonestacion> lista = new ArrayList<>();
	private List<Amonestacion> listafiltro;
	private Amonestacion entidad = new Amonestacion();
	private Amonestacion entidadseleccionada = new Amonestacion();

	private List<Puesto> listaPuesto = new ArrayList<>();
	private List<PuestoPersonaCargo> listaPuestoPersonaCargo = new ArrayList<>();
	private List<TipoAmonestacion> listaTipoAmonestacion = new ArrayList<>();

	private Integer idPersona = null;

	private boolean booBtnEliminar = false;
	private boolean booRegistro = false;
	private Periodo periodoActual = new Periodo();

	private String filtro;

	@EJB
	private AmonestacionService servicio = new AmonestacionServiceImpl();

	@EJB
	private PeriodoService periodoService = new PeriodoServiceImpl();
	@EJB
	private PuestoService puestoService = new PuestoServiceImpl();
	@EJB
	private PuestoPersonaCargoService puestoPersonaCargoService = new PuestoPersonaCargoServiceImpl();
	@EJB
	private TipoAmonestacionService tipoAmonestacionService = new TipoAmonestacionServiceImpl();

	public AmonestacionBean() {
		// TODO Auto-generated constructor stub
		filtro = null;
		entidad = new Amonestacion();
		booBtnEliminar = false;
		booRegistro = false;
		periodoActual = new Periodo();
	}

	@PostConstruct
	public void init() {
		cargaInicial();
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

	public List<Puesto> getListaPuesto() {
		return listaPuesto;
	}

	public void setListaPuesto(List<Puesto> listaPuesto) {
		this.listaPuesto = listaPuesto;
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

	public void setPeriodoService(PeriodoService periodoService) {
		this.periodoService = periodoService;
	}

	public void setPuestoService(PuestoService puestoService) {
		this.puestoService = puestoService;
	}

	public void setPuestoPersonaCargoService(PuestoPersonaCargoService puestoPersonaCargoService) {
		this.puestoPersonaCargoService = puestoPersonaCargoService;
	}

	public List<TipoAmonestacion> getListaTipoAmonestacion() {
		return listaTipoAmonestacion;
	}

	public void setListaTipoAmonestacion(List<TipoAmonestacion> listaTipoAmonestacion) {
		this.listaTipoAmonestacion = listaTipoAmonestacion;
	}

	public void setTipoAmonestacionService(TipoAmonestacionService tipoAmonestacionService) {
		this.tipoAmonestacionService = tipoAmonestacionService;
	}

	public boolean isBooBtnEliminar() {
		return booBtnEliminar;
	}

	public void setBooBtnEliminar(boolean booBtnEliminar) {
		this.booBtnEliminar = booBtnEliminar;
	}

	public boolean isBooRegistro() {
		return booRegistro;
	}

	public void setBooRegistro(boolean booRegistro) {
		this.booRegistro = booRegistro;
	}

	public Periodo getPeriodoActual() {
		return periodoActual;
	}

	public void setPeriodoActual(Periodo periodoActual) {
		this.periodoActual = periodoActual;
	}

	public UsuarioSesionBean getUsuarioSesionBean() {
		return usuarioSesionBean;
	}

	public void setUsuarioSesionBean(UsuarioSesionBean usuarioSesionBean) {
		this.usuarioSesionBean = usuarioSesionBean;
	}

	public String guardar() {

		Amonestacion amonestacion = new Amonestacion();
		amonestacion = entidad;
		amonestacion.setPeriodo(new Periodo(entidad.getPeriodo().getId()));
		amonestacion.setPuesto(new Puesto(entidad.getPuesto().getId()));
		amonestacion.setTipoAmonestacion(new TipoAmonestacion(entidad.getTipoAmonestacion().getId()));

		PuestoPersonaCargo puestoPersonaCargo = new PuestoPersonaCargo();
		puestoPersonaCargo = puestoPersonaCargoService.recuperar(entidad.getPuestoPersonaCargo().getId());

		amonestacion.setPersona(new Persona(puestoPersonaCargo.getPersona().getId()));
		amonestacion.setPuestoPersonaCargo(new PuestoPersonaCargo(puestoPersonaCargo.getPuesto().getId()));
		amonestacion.setPeriodo(new Periodo(periodoActual.getId()));		
		amonestacion.setIdUsuario(usuarioSesionBean.getUsuario().getId());
		amonestacion.setEstado(1);
		
		
		if(entidad.getDescripcion() != null || entidad.getDescripcion() != "") {
			String descripcion_min = entidad.getDescripcion();
			amonestacion.setDescripcion(descripcion_min.toUpperCase());
		}

		if (amonestacion.getId() == null) {
			System.out.println("A guardar");
			try {
				servicio.crear(amonestacion);
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro creado", ""));
			} catch (Exception e) {
				// TODO: handle exception
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al crear registro", ""));
			}
		} else {
			System.out.println("A actualizar");
			try {
				servicio.actualizar(amonestacion);
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro actualizado", ""));
			} catch (Exception e) {
				// TODO: handle exception
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al actualizar registro", ""));
			}
		}
		limpiar();
		listarEntidad();
		return "";
	}
	
	
	public String eliminar() {

		if (entidadseleccionada.getId() == null) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Seleccione registro a eliminar", ""));
			limpiar();
			listarEntidad();
			return "";
		}

		try {
			System.out.println("Entidad Seleccionada: " + entidadseleccionada.toString());
			Amonestacion amonestacion = new Amonestacion();
			amonestacion = servicio.recuperar(entidadseleccionada.getId());
			amonestacion.setEstado(0);
			amonestacion.setIdUsuario(usuarioSesionBean.getUsuario().getId());
			servicio.actualizar(amonestacion);

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro eliminado", ""));
		} catch (Exception e) {
			// TODO: handle exception
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al eliminar registro", ""));
		}

		booBtnEliminar = false;
		listarEntidad();
		limpiar();

		return "";
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
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error.", "Problemas al recuperar registro"));
		}

		listarPuestosPersonaCargo();
	}

	public void listarEntidad() {
		lista = new ArrayList<>();
		try {
			lista = servicio.listarPorPeriodoId(periodoActual.getId());
		} catch (Exception e) {
			// TODO: handle exception
			lista = null;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error.", "Problemas al recuperar registros"));
		}

		listafiltro = lista;
	}

	public void listarPuestos() {
		listaPuesto = new ArrayList<>();
		try {
			listaPuesto = puestoService.listarActivo();
		} catch (Exception e) {
			// TODO: handle exception
			listaPuesto = null;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error.", "Problemas al recuperar lista de Puestos"));
		}

		listaPuestoPersonaCargo = null;
	}

	public void listarPuestosPersonaCargo() {
		listaPuestoPersonaCargo = new ArrayList<>();
		try {
			listaPuestoPersonaCargo = puestoPersonaCargoService.listarPuestoId(entidad.getPuesto().getId());
		} catch (Exception e) {
			// TODO: handle exception
			listaPuestoPersonaCargo = null;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error.", "Problemas al recuperar lista de Puestos"));
		}
	}

	public void listarTipoAmonestacion() {
		listaTipoAmonestacion = new ArrayList<>();
		try {
			listaTipoAmonestacion = tipoAmonestacionService.listarActivo();
		} catch (Exception e) {
			// TODO: handle exception
			listaTipoAmonestacion = null;
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error.",
					"Problemas al recuperar lista de tipos de amonestación"));
		}
	}

	
	public void onRowSelect(SelectEvent event) {
		if (entidadseleccionada == null) {
			entidad = new Amonestacion();
			entidadseleccionada = new Amonestacion();
			booBtnEliminar = false;
			booRegistro = true;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "No se selecciono ningun registro", ""));
		} else {
			entidad = new Amonestacion();
			booBtnEliminar = true;
			booRegistro = false;
			System.out.println("Entidad Seleccionada: " + entidadseleccionada.toString());
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Seleccion de registro exitosa", ""));
		}
	}	

	public void onRowUnselect(UnselectEvent event) {
		entidad = new Amonestacion();
		entidadseleccionada = new Amonestacion();
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Se anulo seleccion de registro ", ""));
	}

	public void limpiar() {
		entidad = null;
		entidad = new Amonestacion();
		entidadseleccionada = null;
		entidadseleccionada = new Amonestacion();
		
		listaPuestoPersonaCargo = null;
		
		booBtnEliminar = false;
		booRegistro = true;

		listafiltro = lista;

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
			if (lista.get(i).getTipoAmonestacion().getDescripcion().contains(filtro)
					|| lista.get(i).getPuesto().getDescripcion().contains(filtro)
					|| lista.get(i).getPersona().getNombre().contains(filtro)
					|| lista.get(i).getPersona().getPaterno().contains(filtro)
					|| lista.get(i).getPersona().getMaterno().contains(filtro)) {
				System.out.println("lista: " + lista.get(i).toString());
				listafiltro.add(lista.get(i));
			}
		}
		filtro = null;
		return "";
	}

	public void obtenerPeriodoActual() {

		List<Periodo> listaperiodos = null;
		periodoActual = new Periodo();

		try {
			listaperiodos = periodoService.listar();
		} catch (Exception e) {
			// TODO: handle exception
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al recuperar periodo fiscal en curso ", ""));
			return;
		}

		if (listaperiodos == null || listaperiodos.isEmpty()) {
			periodoActual = null;
			booRegistro = false;
			booBtnEliminar = false;
			return;
		} else {
			periodoActual = listaperiodos.get(0);
			booRegistro = true;
			booBtnEliminar = false;
		}
	}
	
	
	public void cancelarEliminacionEntidad() {
		entidad = new Amonestacion();
		entidadseleccionada = new Amonestacion();
		booBtnEliminar = false;
		booRegistro = true;

	}
	
	public void cargaInicial() {
		
		obtenerPeriodoActual();
		
		if(periodoActual == null) {
			lista = null;
		}else {
			listarEntidad();
		}
		
		listarPuestos();
		listarTipoAmonestacion();
		
	}

}
