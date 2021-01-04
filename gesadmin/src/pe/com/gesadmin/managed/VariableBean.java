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

import pe.com.gesadmin.entity.Variable;
import pe.com.gesadmin.service.VariableService;
import pe.com.gesadmin.service.impl.VariableServiceImpl;


@ManagedBean
@ViewScoped
public class VariableBean {

	private List<Variable> lista = new ArrayList<>();
	private List<Variable> listafiltro;
	private Variable entidad = new Variable();
	private Variable entidadseleccionada = new Variable();

	private String filtro;

	@EJB
	private VariableService servicio = new VariableServiceImpl();

	public VariableBean() {
		// TODO Auto-generated constructor stub
		filtro = null;
		entidad = new Variable();
	}

	@PostConstruct
	public void init() {
		listarEntidad();
	}

	public List<Variable> getLista() {
		return lista;
	}

	public void setLista(List<Variable> lista) {
		this.lista = lista;
	}

	public List<Variable> getListafiltro() {
		return listafiltro;
	}

	public void setListafiltro(List<Variable> listafiltro) {
		this.listafiltro = listafiltro;
	}

	public Variable getEntidad() {
		return entidad;
	}

	public void setEntidad(Variable entidad) {
		this.entidad = entidad;
	}

	public Variable getEntidadseleccionada() {
		return entidadseleccionada;
	}

	public void setEntidadseleccionada(Variable entidadseleccionada) {
		this.entidadseleccionada = entidadseleccionada;
	}

	public void setServicio(VariableService servicio) {
		this.servicio = servicio;
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

	public String guardar() {

		if (entidad.getId() == null) {
			System.out.println("A guardar");
			try { 
				servicio.crear(entidad);
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro creado", ""));
				
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("Excepcion: " + e.getCause().toString());
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al crear registro", ""));
			}

		} else {
			System.out.println("A actualizar");
			try {
				servicio.actualizar(entidad);
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

		entidad = new Variable();

		try {
			entidad = servicio.recuperar(entidadseleccionada.getId());
		} catch (Exception e) {
			// TODO: handle exception
			entidad = null;
			entidadseleccionada = null;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error. Problemas al recuperar registro", ""));
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
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error. Problemas al recuperar registros", ""));
		}
		
		listafiltro = lista;
	}

	public void onRowSelect(SelectEvent event) {
		if (entidadseleccionada == null) {
			entidad = new Variable();
			entidadseleccionada = new Variable();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "No se selecciono ningun registro", ""));
		} else {
			entidad = entidadseleccionada;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Seleccion de registro exitosa", ""));
		}
	}

	public void onRowUnselect(UnselectEvent event) {
		entidad = new Variable();
		entidadseleccionada = new Variable();
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Se anulo seleccion de registro ", ""));
	}

	public void limpiar() {
		entidad = new Variable();
		entidadseleccionada = new Variable();
		
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
