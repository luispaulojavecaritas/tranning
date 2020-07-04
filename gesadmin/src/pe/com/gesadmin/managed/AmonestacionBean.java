package pe.com.gesadmin.managed;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import pe.com.gesadmin.entity.Bloque;
import pe.com.gesadmin.entity.Periodo;
import pe.com.gesadmin.entity.Persona;
import pe.com.gesadmin.entity.Puesto;
import pe.com.gesadmin.entity.PuestoPersonaCargo;
import pe.com.gesadmin.entity.TipoAmonestacion;
import pe.com.gesadmin.entity.Amonestacion;
import pe.com.gesadmin.service.AmonestacionService;
import pe.com.gesadmin.service.BloqueService;
import pe.com.gesadmin.service.PeriodoService;
import pe.com.gesadmin.service.PuestoPersonaCargoService;
import pe.com.gesadmin.service.PuestoService;
import pe.com.gesadmin.service.TipoAmonestacionService;
import pe.com.gesadmin.service.AmonestacionService;
import pe.com.gesadmin.service.impl.AmonestacionServiceImpl;
import pe.com.gesadmin.service.impl.BloqueServiceImpl;
import pe.com.gesadmin.service.impl.PeriodoServiceImpl;
import pe.com.gesadmin.service.impl.PuestoPersonaCargoServiceImpl;
import pe.com.gesadmin.service.impl.PuestoServiceImpl;
import pe.com.gesadmin.service.impl.TipoAmonestacionServiceImpl;
import pe.com.gesadmin.service.impl.AmonestacionServiceImpl;


@ManagedBean
@ViewScoped
public class AmonestacionBean {

	private List<Amonestacion> lista = new ArrayList<>();
	private List<Amonestacion> listafiltro;
	private Amonestacion entidad = new Amonestacion();
	private Amonestacion entidadseleccionada = new Amonestacion();
	
	private List<Periodo> listaPeriodo = new ArrayList<>();
	private List<Puesto> listaPuesto = new ArrayList<>();
	private List<PuestoPersonaCargo> listaPuestoPersonaCargo = new ArrayList<>();
	private List<TipoAmonestacion> listaTipoAmonestacion = new ArrayList<>();
	
	private Integer idPersona = null;
	

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
	}

	@PostConstruct
	public void init() {
		listarEntidad();
		listarPeriodo();
		listarPuestos();
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

	public List<Periodo> getListaPeriodo() {
		return listaPeriodo;
	}

	public void setListaPeriodo(List<Periodo> listaPeriodo) {
		this.listaPeriodo = listaPeriodo;
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

	public String guardar() {
		
		Amonestacion amonestacion = new Amonestacion();
		amonestacion = entidad;
		amonestacion.setPeriodo(new Periodo(entidad.getPeriodo().getId()));
		amonestacion.setPuesto(new Puesto(entidad.getPuesto().getId()));
		amonestacion.setTipoAmonestacion(new TipoAmonestacion(entidad.getTipoAmonestacion().getId()));
		amonestacion.setPersona(new Persona(idPersona));
		amonestacion.setTipoAmonestacion(new TipoAmonestacion(entidad.getTipoAmonestacion().getId()));
		

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
			lista = servicio.listar();
		} catch (Exception e) {
			// TODO: handle exception
			lista = null;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error.", "Problemas al recuperar registros"));
		}
		
		listafiltro = lista;
	}
	
	public void listarPeriodo() {
		listaPeriodo = new ArrayList<>();
		try {
			listaPeriodo = periodoService.listar();
		} catch (Exception e) {
			// TODO: handle exception
			listaPeriodo = null;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error.", "Problemas al recuperar lista de Periodos"));
		}
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
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error.", "Problemas al recuperar lista de tipos de amonestación"));
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
			if (lista.get(i).getDescripcion().contains(filtro)) {
				System.out.println("lista: " + lista.get(i).toString());
				listafiltro.add(lista.get(i));
			}
		}
		filtro = null;
		return "";
	}

}
