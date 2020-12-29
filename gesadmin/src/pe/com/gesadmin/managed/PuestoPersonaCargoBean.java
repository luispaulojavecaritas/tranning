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
import pe.com.gesadmin.entity.PuestoPersonaCargo;
import pe.com.gesadmin.entity.Cargo;
import pe.com.gesadmin.service.BloqueService;
import pe.com.gesadmin.service.PersonaService;
import pe.com.gesadmin.service.PuestoPersonaCargoService;
import pe.com.gesadmin.service.PuestoService;
import pe.com.gesadmin.service.CargoService;
import pe.com.gesadmin.service.impl.BloqueServiceImpl;
import pe.com.gesadmin.service.impl.PersonaServiceImpl;
import pe.com.gesadmin.service.impl.PuestoPersonaCargoServiceImpl;
import pe.com.gesadmin.service.impl.PuestoServiceImpl;
import pe.com.gesadmin.service.impl.CargoServiceImpl;

@ManagedBean
@ViewScoped
public class PuestoPersonaCargoBean {

	private List<PuestoPersonaCargo> lista = new ArrayList<>();
	private List<PuestoPersonaCargo> listafiltro;
	private PuestoPersonaCargo entidad = new PuestoPersonaCargo();
	private PuestoPersonaCargo entidadseleccionada = new PuestoPersonaCargo();

	private String filtro;

	@EJB
	private PuestoPersonaCargoService servicio = new PuestoPersonaCargoServiceImpl();

	@EJB
	private PuestoService puestoService = new PuestoServiceImpl();

	@EJB
	private PersonaService personaService = new PersonaServiceImpl();

	@EJB
	private CargoService cargoService = new CargoServiceImpl();

	private List<Puesto> listaPuestos = new ArrayList<>();
	private List<Persona> listaPersonas = new ArrayList<>();
	private List<Cargo> listaCargos = new ArrayList<>();

	public PuestoPersonaCargoBean() {
		// TODO Auto-generated constructor stub
		filtro = null;
		entidad = new PuestoPersonaCargo();
	}

	@PostConstruct
	public void init() {
		listarEntidad();
		listarPersonas();
		listarPuestos();
		listarCargos();
	}



	public List<PuestoPersonaCargo> getLista() {
		return lista;
	}

	public void setLista(List<PuestoPersonaCargo> lista) {
		this.lista = lista;
	}

	public List<PuestoPersonaCargo> getListafiltro() {
		return listafiltro;
	}

	public void setListafiltro(List<PuestoPersonaCargo> listafiltro) {
		this.listafiltro = listafiltro;
	}

	public PuestoPersonaCargo getEntidad() {
		return entidad;
	}

	public void setEntidad(PuestoPersonaCargo entidad) {
		this.entidad = entidad;
	}

	public PuestoPersonaCargo getEntidadseleccionada() {
		return entidadseleccionada;
	}

	public void setEntidadseleccionada(PuestoPersonaCargo entidadseleccionada) {
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

	public List<Cargo> getListaCargos() {
		return listaCargos;
	}

	public void setListaCargos(List<Cargo> listaCargos) {
		this.listaCargos = listaCargos;
	}

	public void setServicio(PuestoPersonaCargoService servicio) {
		this.servicio = servicio;
	}

	public void setPuestoService(PuestoService puestoService) {
		this.puestoService = puestoService;
	}

	public void setPersonaService(PersonaService personaService) {
		this.personaService = personaService;
	}

	public void setCargoService(CargoService cargoService) {
		this.cargoService = cargoService;
	}

	public String guardar() {

		PuestoPersonaCargo puestoPersona = new PuestoPersonaCargo();
		puestoPersona = entidad;
		puestoPersona.setPersona(new Persona(entidad.getPersona().getId()));
		puestoPersona.setPuesto(new Puesto(entidad.getPuesto().getId()));
		puestoPersona.setCargo(new Cargo(entidad.getCargo().getId()));

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

		entidad = new PuestoPersonaCargo();

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

	public void listarCargos() {
		listaCargos = new ArrayList<>();
		try {
			listaCargos = cargoService.listar();
		} catch (Exception e) {
			// TODO: handle exception
			listaCargos = null;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error.", "Problemas al recuperar lista de Bloques"));
		}

	}

	public void onRowSelect(SelectEvent event) {
		if (entidadseleccionada == null) {
			entidad = new PuestoPersonaCargo();
			entidadseleccionada = new PuestoPersonaCargo();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "No se selecciono ningun registro", ""));
		} else {
			entidad = entidadseleccionada;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Seleccion de registro exitosa", ""));
		}
	}

	public void onRowUnselect(UnselectEvent event) {
		entidad = new PuestoPersonaCargo();
		entidadseleccionada = new PuestoPersonaCargo();
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Se anulo seleccion de registro ", ""));
	}

	public void limpiar() {
		entidad = new PuestoPersonaCargo();
		entidadseleccionada = new PuestoPersonaCargo();

		listafiltro = lista;

	}

	public String filtrar() {
		
		if(filtro.isEmpty() || filtro == null || filtro == "") {
			listafiltro = null;
			listafiltro = lista;
			filtro = null;
			return "";
		}

		if (lista == null || lista.isEmpty() || lista.size() == 0) {
			filtro = null;
			return "";
		}
		
		
		listafiltro = null;
		listafiltro = new ArrayList<>();
		System.out.println("Texto a filtra: " + filtro);
		for (int i = 0; i <= lista.size() - 1; i++) {
			if (lista.get(i).getPuesto().getDescripcion().equalsIgnoreCase(filtro)
					|| lista.get(i).getPuesto().getBloque().getDescripcion().equalsIgnoreCase(filtro)
					|| lista.get(i).getPersona().getNombre().equalsIgnoreCase(filtro)
					|| lista.get(i).getPersona().getPaterno().equalsIgnoreCase(filtro)
					|| lista.get(i).getPersona().getMaterno().equalsIgnoreCase(filtro)) {
				System.out.println("lista: " + lista.get(i).toString());
				listafiltro.add(lista.get(i));
			}
		}
		filtro = null;
		return "";
	}

}
