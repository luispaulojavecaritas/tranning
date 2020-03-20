package pe.com.gesadmin.managed;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import pe.com.gesadmin.entity.Bloque;
import pe.com.gesadmin.entity.Persona;
import pe.com.gesadmin.entity.Puesto;
import pe.com.gesadmin.entity.PuestoPersona;
import pe.com.gesadmin.entity.TipoEstatus;
import pe.com.gesadmin.service.BloqueService;
import pe.com.gesadmin.service.PersonaService;
import pe.com.gesadmin.service.PuestoPersonaService;
import pe.com.gesadmin.service.PuestoService;
import pe.com.gesadmin.service.TipoEstatusService;
import pe.com.gesadmin.service.impl.BloqueServiceImpl;
import pe.com.gesadmin.service.impl.PersonaServiceImpl;
import pe.com.gesadmin.service.impl.PuestoPersonaServiceImpl;
import pe.com.gesadmin.service.impl.PuestoServiceImpl;
import pe.com.gesadmin.service.impl.TipoEstatusServiceImpl;


@ManagedBean
@ViewScoped
public class PuestoPersonaEstatusBean {

	private List<PuestoPersona> lista = new ArrayList<>();
	private List<PuestoPersona> listafiltro;
	private PuestoPersona entidad = new PuestoPersona();
	private PuestoPersona entidadseleccionada = new PuestoPersona();

	private String filtro;

	@EJB
	private PuestoPersonaService servicio = new   PuestoPersonaServiceImpl();
	
	@EJB
	private PuestoService puestoService = new PuestoServiceImpl();
	
	@EJB
	private PersonaService personaService = new PersonaServiceImpl();
	
	@EJB
	private TipoEstatusService tipoEstatusService = new TipoEstatusServiceImpl();
	
	private List<Puesto> listaPuestos  = new ArrayList<>();
	private List<Persona> listaPersonas  = new ArrayList<>();
	private List<TipoEstatus> listaTipoEstatus  = new ArrayList<>();

	public PuestoPersonaEstatusBean() {
		// TODO Auto-generated constructor stub
		filtro = null;
		entidad = new PuestoPersona();
	}

	@PostConstruct
	public void init() {
		listarEntidad();
		listarPersonas();
		listarPuestos();
		listarTipoEstatus();
	}

	
	public List<PuestoPersona> getLista() {
		return lista;
	}

	public void setLista(List<PuestoPersona> lista) {
		this.lista = lista;
	}

	public List<PuestoPersona> getListafiltro() {
		return listafiltro;
	}

	public void setListafiltro(List<PuestoPersona> listafiltro) {
		this.listafiltro = listafiltro;
	}

	public PuestoPersona getEntidad() {
		return entidad;
	}

	public void setEntidad(PuestoPersona entidad) {
		this.entidad = entidad;
	}

	public PuestoPersona getEntidadseleccionada() {
		return entidadseleccionada;
	}

	public void setEntidadseleccionada(PuestoPersona entidadseleccionada) {
		this.entidadseleccionada = entidadseleccionada;
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

	public List<Puesto> getListaPuestos() {
		return listaPuestos;
	}

	public void setListaPuestos(List<Puesto> listaPuestos) {
		this.listaPuestos = listaPuestos;
	}

	public List<Persona> getListaPersonas() {
		return listaPersonas;
	}

	public void setListaPersonas(List<Persona> listaPersonas) {
		this.listaPersonas = listaPersonas;
	}

	public List<TipoEstatus> getListaTipoEstatus() {
		return listaTipoEstatus;
	}

	public void setListaTipoEstatus(List<TipoEstatus> listaTipoEstatus) {
		this.listaTipoEstatus = listaTipoEstatus;
	}

	public void setServicio(PuestoPersonaService servicio) {
		this.servicio = servicio;
	}

	public void setPuestoService(PuestoService puestoService) {
		this.puestoService = puestoService;
	}

	public void setPersonaService(PersonaService personaService) {
		this.personaService = personaService;
	}

	public void setTipoEstatusService(TipoEstatusService tipoEstatusService) {
		this.tipoEstatusService = tipoEstatusService;
	}

	public String guardar() {
		
		PuestoPersona puestoPersona = new PuestoPersona();
		puestoPersona = entidad;
		puestoPersona.setPersona(new Persona(entidad.getPersona().getId())); 
		puestoPersona.setPuesto(new Puesto(entidad.getPuesto().getId()));
		puestoPersona.setTipoEstatus(new TipoEstatus(entidad.getTipoEstatus().getId()));
		

		if (puestoPersona.getId() == null) {
			System.out.println("A guardar");
			try {
				servicio.crear(puestoPersona);
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
				servicio.actualizar(puestoPersona);
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

		entidad = new PuestoPersona();

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
	
	
	public void listarPersonas() {
		listaPersonas = new ArrayList<>();
		try {
			listaPersonas = personaService.listar();
		} catch (Exception e) {
			// TODO: handle exception
			listaPersonas = null;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error.", "Problemas al recuperar lista de Bloques"));
		}
		
	}
	
	public void listarPuestos() {
		listaPuestos = new ArrayList<>();
		try {
			listaPuestos = puestoService.listar();
		} catch (Exception e) {
			// TODO: handle exception
			listaPuestos = null;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error.", "Problemas al recuperar lista de Bloques"));
		}
		
	}
	
	public void listarTipoEstatus() {
		listaTipoEstatus = new ArrayList<>();
		try {
			listaTipoEstatus = tipoEstatusService.listar();
		} catch (Exception e) {
			// TODO: handle exception
			listaTipoEstatus = null;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error.", "Problemas al recuperar lista de Bloques"));
		}
		
	}

	public void onRowSelect(SelectEvent event) {
		if (entidadseleccionada == null) {
			entidad = new PuestoPersona();
			entidadseleccionada = new PuestoPersona();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "No se selecciono ningun registro", ""));
		} else {
			entidad = entidadseleccionada;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Seleccion de registro exitosa", ""));
		}
	}

	public void onRowUnselect(UnselectEvent event) {
		entidad = new PuestoPersona();
		entidadseleccionada = new PuestoPersona();
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Se anulo seleccion de registro ", ""));
	}

	public void limpiar() {
		entidad = new PuestoPersona();
		entidadseleccionada = new PuestoPersona();
		
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
