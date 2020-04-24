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
import pe.com.gesadmin.entity.Puesto;
import pe.com.gesadmin.service.BloqueService;
import pe.com.gesadmin.service.PuestoService;
import pe.com.gesadmin.service.impl.BloqueServiceImpl;
import pe.com.gesadmin.service.impl.PuestoServiceImpl;


@ManagedBean
@ViewScoped
public class PuestoBean {

	private List<Puesto> lista = new ArrayList<>();
	private List<Puesto> listafiltro;
	private Puesto entidad = new Puesto();
	private Puesto entidadseleccionada = new Puesto();

	private String filtro;

	@EJB
	private PuestoService servicio = new PuestoServiceImpl();
	
	@EJB
	private BloqueService bloqueService = new BloqueServiceImpl();
	
	private List<Bloque> listaBloque  = new ArrayList<>();

	public PuestoBean() {
		// TODO Auto-generated constructor stub
		filtro = null;
		entidad = new Puesto();
	}

	@PostConstruct
	public void init() {
		listarEntidad();
		listarBloque();
	}

	public List<Puesto> getLista() {
		return lista;
	}

	public void setLista(List<Puesto> lista) {
		this.lista = lista;
	}

	public List<Puesto> getListafiltro() {
		return listafiltro;
	}

	public void setListafiltro(List<Puesto> listafiltro) {
		this.listafiltro = listafiltro;
	}

	public Puesto getEntidad() {
		return entidad;
	}

	public void setEntidad(Puesto entidad) {
		this.entidad = entidad;
	}

	public Puesto getEntidadseleccionada() {
		return entidadseleccionada;
	}

	public void setEntidadseleccionada(Puesto entidadseleccionada) {
		this.entidadseleccionada = entidadseleccionada;
	}

	public void setServicio(PuestoService servicio) {
		this.servicio = servicio;
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}
	

	public void setBloqueService(BloqueService bloqueService) {
		this.bloqueService = bloqueService;
	}

	public List<Bloque> getListaBloque() {
		return listaBloque;
	}

	public void setListaBloque(List<Bloque> listaBloque) {
		this.listaBloque = listaBloque;
	}

	public String guardar() {
		
		Puesto puesto = new Puesto();
		puesto = entidad;
		puesto.setBloque(new Bloque(entidad.getBloque().getId()));
		
		boolean verificarcion = verificarCorrespondencia(entidad.getDescripcion(), entidad.getBloque().getId());
		
		if(verificarcion) {
			System.out.println("SI existe correspondencia");
		}else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Descripcion no coincide con bloque", ""));
			listarEntidad();
			return "";
		}

		if (puesto.getId() == null) {
			System.out.println("A guardar");
			try {
				servicio.crear(puesto);
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
				servicio.actualizar(puesto);
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

		entidad = new Puesto();

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
	
	
	public void listarBloque() {
		listaBloque = new ArrayList<>();
		try {
			listaBloque = bloqueService.listar();
		} catch (Exception e) {
			// TODO: handle exception
			listaBloque = null;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error.", "Problemas al recuperar lista de Bloques"));
		}
		
	}

	public void onRowSelect(SelectEvent event) {
		if (entidadseleccionada == null) {
			entidad = new Puesto();
			entidadseleccionada = new Puesto();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "No se selecciono ningun registro", ""));
		} else {
			entidad = entidadseleccionada;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Seleccion de registro exitosa", ""));
		}
	}

	public void onRowUnselect(UnselectEvent event) {
		entidad = new Puesto();
		entidadseleccionada = new Puesto();
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Se anulo seleccion de registro ", ""));
	}

	public void limpiar() {
		entidad = null;
		entidad = new Puesto();
		entidadseleccionada = null;
		entidadseleccionada = new Puesto();
				
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
	
	public boolean verificarCorrespondencia(String descripcionPuesto, Integer bloqueId) {
		
		Bloque bloque = bloqueService.recuperar(bloqueId);
		
		if (descripcionPuesto.contains(bloque.getDescripcion())) {
			System.out.println("Si coincide");
			return true;
		}else {
			System.out.println("No coincide");
			return false;
		}
		
	}

}
