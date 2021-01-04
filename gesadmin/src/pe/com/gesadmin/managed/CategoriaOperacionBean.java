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

import pe.com.gesadmin.entity.AnioFiscal;
import pe.com.gesadmin.entity.CategoriaOperacion;
import pe.com.gesadmin.entity.TipoOperacion;
import pe.com.gesadmin.service.CategoriaOperacionService;
import pe.com.gesadmin.service.TipoOperacionService;
import pe.com.gesadmin.service.impl.CategoriaOperacionServiceImpl;
import pe.com.gesadmin.service.impl.TipoOperacionServiceImpl;


@ManagedBean
@ViewScoped
public class CategoriaOperacionBean {

	private List<CategoriaOperacion> lista = new ArrayList<>();
	private List<CategoriaOperacion> listafiltro;
	private CategoriaOperacion entidad = new CategoriaOperacion();
	private CategoriaOperacion entidadseleccionada = new CategoriaOperacion();
	
	private List<TipoOperacion> listaTipoOperacion = new ArrayList<>();

	private String filtro;

	@EJB
	private CategoriaOperacionService servicio = new CategoriaOperacionServiceImpl();
	
	@EJB
	private TipoOperacionService tipoOperacionService  = new TipoOperacionServiceImpl();

	public CategoriaOperacionBean() {
		// TODO Auto-generated constructor stub
		filtro = null;
		entidad = new CategoriaOperacion();
	}

	@PostConstruct
	public void init() {
		listarEntidad();
		listarTipoOperacion();
	}

	public List<CategoriaOperacion> getLista() {
		return lista;
	}

	public void setLista(List<CategoriaOperacion> lista) {
		this.lista = lista;
	}

	public List<CategoriaOperacion> getListafiltro() {
		return listafiltro;
	}

	public void setListafiltro(List<CategoriaOperacion> listafiltro) {
		this.listafiltro = listafiltro;
	}

	public CategoriaOperacion getEntidad() {
		return entidad;
	}

	public void setEntidad(CategoriaOperacion entidad) {
		this.entidad = entidad;
	}

	public CategoriaOperacion getEntidadseleccionada() {
		return entidadseleccionada;
	}

	public void setEntidadseleccionada(CategoriaOperacion entidadseleccionada) {
		this.entidadseleccionada = entidadseleccionada;
	}

	public void setServicio(CategoriaOperacionService servicio) {
		this.servicio = servicio;
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

	public List<TipoOperacion> getListaTipoOperacion() {
		return listaTipoOperacion;
	}

	public void setListaTipoOperacion(List<TipoOperacion> listaTipoOperacion) {
		this.listaTipoOperacion = listaTipoOperacion;
	}

	public void setTipoOperacionService(TipoOperacionService tipoOperacionService) {
		this.tipoOperacionService = tipoOperacionService;
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

		entidad = new CategoriaOperacion();

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
			entidad = new CategoriaOperacion();
			entidadseleccionada = new CategoriaOperacion();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "No se selecciono ningun registro", ""));
		} else {
			entidad = entidadseleccionada;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Seleccion de registro exitosa", ""));
		}
	}

	public void onRowUnselect(UnselectEvent event) {
		entidad = new CategoriaOperacion();
		entidadseleccionada = new CategoriaOperacion();
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Se anulo seleccion de registro ", ""));
	}

	public void limpiar() {
		entidad = new CategoriaOperacion();
		entidadseleccionada = new CategoriaOperacion();
		
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
	
	public void listarTipoOperacion() {
		try {
			listaTipoOperacion = tipoOperacionService.listar();
		} catch (Exception e) {
			// TODO: handle exception
			listaTipoOperacion = null;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error.", "Problemas al recuperar registros tipo de movimiento"));
			
		}
	}

}
