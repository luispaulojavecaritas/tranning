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

import pe.com.gesadmin.entity.TipoServicio;
import pe.com.gesadmin.service.TipoServicioService;
import pe.com.gesadmin.service.impl.TipoServicioServiceImpl;

@ManagedBean
@ViewScoped
public class TipoServicioBean {

	private List<TipoServicio> lista = new ArrayList<>();
	private List<TipoServicio> listafiltro;
	private TipoServicio entidad = new TipoServicio();
	private TipoServicio entidadseleccionada = new TipoServicio();

	private String filtro;

	@EJB
	private TipoServicioService servicio = new TipoServicioServiceImpl();

	public TipoServicioBean() {
		// TODO Auto-generated constructor stub
		filtro = null;
		entidad = new TipoServicio();
	}

	@PostConstruct
	public void init() {
		listarEntidad();
	}

	public List<TipoServicio> getLista() {
		return lista;
	}

	public void setLista(List<TipoServicio> lista) {
		this.lista = lista;
	}

	public List<TipoServicio> getListafiltro() {
		return listafiltro;
	}

	public void setListafiltro(List<TipoServicio> listafiltro) {
		this.listafiltro = listafiltro;
	}

	public TipoServicio getEntidad() {
		return entidad;
	}

	public void setEntidad(TipoServicio entidad) {
		this.entidad = entidad;
	}

	public TipoServicio getEntidadseleccionada() {
		return entidadseleccionada;
	}

	public void setEntidadseleccionada(TipoServicio entidadseleccionada) {
		this.entidadseleccionada = entidadseleccionada;
	}

	public void setServicio(TipoServicioService servicio) {
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

			// TODO: handle exception
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "No se permite crear nuevos registros, solo actualizar", ""));

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

		entidad = new TipoServicio();

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
			entidad = new TipoServicio();
			entidadseleccionada = new TipoServicio();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "No se selecciono ningun registro", ""));
		} else {
			entidad = entidadseleccionada;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Seleccion de registro exitosa", ""));
		}
	}

	public void onRowUnselect(UnselectEvent event) {
		entidad = new TipoServicio();
		entidadseleccionada = new TipoServicio();
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Se anulo seleccion de registro ", ""));
	}

	public void limpiar() {
		entidad = new TipoServicio();
		entidadseleccionada = new TipoServicio();

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
