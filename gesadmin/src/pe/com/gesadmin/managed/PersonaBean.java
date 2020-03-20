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

import pe.com.gesadmin.entity.Pais;
import pe.com.gesadmin.entity.Persona;
import pe.com.gesadmin.entity.Sexo;
import pe.com.gesadmin.entity.TipoDocumento;
import pe.com.gesadmin.service.PaisService;
import pe.com.gesadmin.service.PersonaService;
import pe.com.gesadmin.service.SexoService;
import pe.com.gesadmin.service.TipoDocumentoService;
import pe.com.gesadmin.service.impl.PaisServiceImpl;
import pe.com.gesadmin.service.impl.PersonaServiceImpl;
import pe.com.gesadmin.service.impl.SexoServiceImpl;
import pe.com.gesadmin.service.impl.TipoDocumentoServiceImpl;

@ManagedBean
@ViewScoped
public class PersonaBean {

	private List<Persona> lista = new ArrayList<>();
	private List<Persona> listafiltro;
	private Persona entidad = new Persona();
	private Persona entidadseleccionada = new Persona();

	private String filtro;

	@EJB
	private PersonaService servicio = new PersonaServiceImpl();

	private List<TipoDocumento> listaTipoDocumento = new ArrayList<>();
	private List<Pais> listaPais = new ArrayList<>();
	private List<Sexo> listaSexo = new ArrayList<>();

	@EJB
	private TipoDocumentoService tipoDocumentoService = new TipoDocumentoServiceImpl();
	@EJB
	private PaisService paisService = new PaisServiceImpl();
	@EJB
	private SexoService sexoService = new SexoServiceImpl();

	public PersonaBean() {
		// TODO Auto-generated constructor stub
		filtro = null;
		entidad = new Persona();
		
	}

	@PostConstruct
	public void init() {
		listarEntidad();
		listarPais();
		listarSexo();
		listarTipoDocumento();
	}

	public List<Persona> getLista() {
		return lista;
	}

	public void setLista(List<Persona> lista) {
		this.lista = lista;
	}

	public List<Persona> getListafiltro() {
		return listafiltro;
	}

	public void setListafiltro(List<Persona> listafiltro) {
		this.listafiltro = listafiltro;
	}

	public Persona getEntidad() {
		return entidad;
	}

	public void setEntidad(Persona entidad) {
		this.entidad = entidad;
	}

	public Persona getEntidadseleccionada() {
		return entidadseleccionada;
	}

	public void setEntidadseleccionada(Persona entidadseleccionada) {
		this.entidadseleccionada = entidadseleccionada;
	}

	public void setServicio(PersonaService servicio) {
		this.servicio = servicio;
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

	public List<TipoDocumento> getListaTipoDocumento() {
		return listaTipoDocumento;
	}

	public void setListaTipoDocumento(List<TipoDocumento> listaTipoDocumento) {
		this.listaTipoDocumento = listaTipoDocumento;
	}

	public List<Pais> getListaPais() {
		return listaPais;
	}

	public void setListaPais(List<Pais> listaPais) {
		this.listaPais = listaPais;
	}

	public List<Sexo> getListaSexo() {
		return listaSexo;
	}

	public void setListaSexo(List<Sexo> listaSexo) {
		this.listaSexo = listaSexo;
	}

	public void setTipoDocumentoService(TipoDocumentoService tipoDocumentoService) {
		this.tipoDocumentoService = tipoDocumentoService;
	}

	public void setPaisService(PaisService paisService) {
		this.paisService = paisService;
	}

	public void setSexoService(SexoService sexoService) {
		this.sexoService = sexoService;
	}

	public String guardar() {

		Persona persona = new Persona();
		persona = entidad;
		persona.setPais(new Pais(entidad.getPais().getId()));
		persona.setSexo(new Sexo(entidad.getSexo().getId()));
		persona.setTipoDocumento(new TipoDocumento(entidad.getTipoDocumento().getId()));

		if (persona.getId() == null) {
			System.out.println("A guardar");
			try {
				servicio.crear(persona);
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
				servicio.actualizar(persona);
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

		entidad = new Persona();

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
			entidad = new Persona();
			entidadseleccionada = new Persona();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "No se selecciono ningun registro", ""));
		} else {
			entidad = entidadseleccionada;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Seleccion de registro exitosa", ""));
		}
	}

	public void onRowUnselect(UnselectEvent event) {
		entidad = new Persona();
		entidadseleccionada = new Persona();
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Se anulo seleccion de registro ", ""));
	}

	public void limpiar() {
		entidad = new Persona();
		entidadseleccionada = new Persona();

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
			if (lista.get(i).getNombre().contains(filtro) || lista.get(i).getPaterno().contains(filtro)
					|| lista.get(i).getMaterno().contains(filtro)
					|| lista.get(i).getNroDocumento().toString().contains(filtro)) {
				System.out.println("lista: " + lista.get(i).toString());
				listafiltro.add(lista.get(i));
			}
		}
		filtro = null;
		return "";
	}

	public void listarPais() {

		listaPais = new ArrayList<>();
		try {
			listaPais = paisService.listar();
		} catch (Exception e) {
			// TODO: handle exception
			listaPais = null;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error.", "Problemas al recuperar lista de pais"));
		}

	}

	public void listarSexo() {

		listaSexo = new ArrayList<>();
		try {
			listaSexo = sexoService.listar();
		} catch (Exception e) {
			// TODO: handle exception
			listaSexo = null;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error.", "Problemas al recuperar lista de sexo"));
		}
	}

	public void listarTipoDocumento() {

		listaTipoDocumento = new ArrayList<>();
		try {
			listaTipoDocumento = tipoDocumentoService.listar();
		} catch (Exception e) {
			// TODO: handle exception
			listaTipoDocumento = null;
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error.",
					"Problemas al recuperar lista de tipo de documento"));
		}

	}

}
