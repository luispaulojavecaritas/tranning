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

import pe.com.gesadmin.entity.ContactoProveedor;
import pe.com.gesadmin.entity.Persona;
import pe.com.gesadmin.entity.Proveedor;
import pe.com.gesadmin.service.ContactoProveedorService;
import pe.com.gesadmin.service.PersonaService;
import pe.com.gesadmin.service.ProveedorService;
import pe.com.gesadmin.service.impl.ContactoProveedorServiceImpl;
import pe.com.gesadmin.service.impl.PersonaServiceImpl;
import pe.com.gesadmin.service.impl.ProveedorServiceImpl;


@ManagedBean
@ViewScoped
public class ContactoProveedorBean {

	private List<ContactoProveedor> lista = new ArrayList<>();
	private List<ContactoProveedor> listafiltro;
	private ContactoProveedor entidad = new ContactoProveedor();
	private ContactoProveedor entidadseleccionada = new ContactoProveedor();

	private String filtro;

	@EJB
	private ContactoProveedorService servicio = new ContactoProveedorServiceImpl();
	
	private List<Proveedor> listaProveedor = new ArrayList<>();
	private List<Persona> listaPersona = new ArrayList<>();
	
	@EJB
	private PersonaService personaService = new  PersonaServiceImpl();
	
	@EJB
	private ProveedorService proveedorService = new  ProveedorServiceImpl();
	

	public ContactoProveedorBean() {
		// TODO Auto-generated constructor stub
		filtro = null;
		entidad = new ContactoProveedor();
	}

	@PostConstruct
	public void init() {
		listarEntidad();
		listarPersonas();
		listarProveedores();
	}

	public List<ContactoProveedor> getLista() {
		return lista;
	}

	public void setLista(List<ContactoProveedor> lista) {
		this.lista = lista;
	}

	public List<ContactoProveedor> getListafiltro() {
		return listafiltro;
	}

	public void setListafiltro(List<ContactoProveedor> listafiltro) {
		this.listafiltro = listafiltro;
	}

	public ContactoProveedor getEntidad() {
		return entidad;
	}

	public void setEntidad(ContactoProveedor entidad) {
		this.entidad = entidad;
	}

	public ContactoProveedor getEntidadseleccionada() {
		return entidadseleccionada;
	}

	public void setEntidadseleccionada(ContactoProveedor entidadseleccionada) {
		this.entidadseleccionada = entidadseleccionada;
	}

	public void setServicio(ContactoProveedorService servicio) {
		this.servicio = servicio;
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

	public List<Proveedor> getListaProveedor() {
		return listaProveedor;
	}

	public void setListaProveedor(List<Proveedor> listaProveedor) {
		this.listaProveedor = listaProveedor;
	}

	public List<Persona> getListaPersona() {
		return listaPersona;
	}

	public void setListaPersona(List<Persona> listaPersona) {
		this.listaPersona = listaPersona;
	}

	public void setPersonaService(PersonaService personaService) {
		this.personaService = personaService;
	}

	public void setProveedorService(ProveedorService proveedorService) {
		this.proveedorService = proveedorService;
	}

	public String guardar() {
		
		ContactoProveedor contactoProveedor = new ContactoProveedor();
		contactoProveedor = entidad;
		contactoProveedor.setPersona(new Persona(entidad.getPersona().getId()));
		contactoProveedor.setProveedor(new Proveedor(entidad.getProveedor().getId()));

		if (contactoProveedor.getId() == null) {
			System.out.println("A guardar");
			try {
				servicio.crear(contactoProveedor);
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
				servicio.actualizar(contactoProveedor);
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

		entidad = new ContactoProveedor();

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

	public void onRowSelect(SelectEvent event) {
		if (entidadseleccionada == null) {
			entidad = new ContactoProveedor();
			entidadseleccionada = new ContactoProveedor();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "No se selecciono ningun registro", ""));
		} else {
			entidad = entidadseleccionada;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Seleccion de registro exitosa", ""));
		}
	}

	public void onRowUnselect(UnselectEvent event) {
		entidad = new ContactoProveedor();
		entidadseleccionada = new ContactoProveedor();
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Se anulo seleccion de registro ", ""));
	}

	public void limpiar() {
		entidad = new ContactoProveedor();
		entidadseleccionada = new ContactoProveedor();
		
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
			if (lista.get(i).getProveedor().getRazonSocial().contains(filtro) || 
					lista.get(i).getProveedor().getRuc().toString().contains(filtro) || 
					lista.get(i).getPersona().getNroDocumento().toString().contains(filtro) || 
					lista.get(i).getPersona().getNombre().contains(filtro) || 
					lista.get(i).getPersona().getPaterno().contains(filtro) || 
					lista.get(i).getPersona().getMaterno().contains(filtro)) {
				System.out.println("lista: " + lista.get(i).toString());
				listafiltro.add(lista.get(i));
			}
		}
		filtro = null;
		return "";
	}
	
	
	
	public void listarPersonas() {

		listaPersona = new ArrayList<>();
		try {
			listaPersona = personaService.listar();
		} catch (Exception e) {
			// TODO: handle exception
			listaPersona = null;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error.", "Problemas al recuperar lista de personas"));
		}
	}
	
	
	public void listarProveedores() {

		listaProveedor = new ArrayList<>();
		try {
			listaProveedor = proveedorService.listar();
		} catch (Exception e) {
			// TODO: handle exception
			listaProveedor = null;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error.", "Problemas al recuperar lista de personas"));
		}
	}

}
