package pe.com.gesadmin.managed;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import pe.com.gesadmin.entity.Proveedor;
import pe.com.gesadmin.service.ProveedorService;
import pe.com.gesadmin.service.impl.ProveedorServiceImpl;
import pe.com.gesadmin.util.Constante;

@ManagedBean
@ViewScoped
public class ProveedorBean {

	private List<Proveedor> lista = new ArrayList<>();
	private List<Proveedor> listafiltro;
	private Proveedor entidad = new Proveedor();
	private Proveedor entidadseleccionada = new Proveedor();

	private String filtro;

	@EJB
	private ProveedorService servicio = new ProveedorServiceImpl();

	public ProveedorBean() {
		// TODO Auto-generated constructor stub
		filtro = null;
		entidad = new Proveedor();
	}

	@PostConstruct
	public void init() {
		listarEntidad();
		entidad = new Proveedor();
	}

	public List<Proveedor> getLista() {
		return lista;
	}

	public void setLista(List<Proveedor> lista) {
		this.lista = lista;
	}

	public List<Proveedor> getListafiltro() {
		return listafiltro;
	}

	public void setListafiltro(List<Proveedor> listafiltro) {
		this.listafiltro = listafiltro;
	}

	public Proveedor getEntidad() {
		return entidad;
	}

	public void setEntidad(Proveedor entidad) {
		this.entidad = entidad;
	}

	public Proveedor getEntidadseleccionada() {
		return entidadseleccionada;
	}

	public void setEntidadseleccionada(Proveedor entidadseleccionada) {
		this.entidadseleccionada = entidadseleccionada;
	}

	public void setServicio(ProveedorService servicio) {
		this.servicio = servicio;
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

	public String guardar() {

		Proveedor proveedor = new Proveedor();
		proveedor = entidad;

		System.out.println("Datos de proveedor: " + proveedor.toString());

		if (proveedor.getTelefonoCelular() == null || proveedor.getTelefonoCelular().trim() == "" || proveedor.getTelefonoCelular().isEmpty()) {
			System.out.println("No se valida telefono celular");
			proveedor.setTelefonoCelular(null);
		} else {
			System.out.println("Se procede a validar teléfono celular");

			Matcher matcher = Constante.CELULAR_PATTERN.matcher(proveedor.getTelefonoCelular());

			if (!matcher.matches()) {
				System.out.println("No cumple formato");

				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Formato de teléfono celular inválido", ""));
				listarEntidad();
				return "";

			} else {
				System.out.println("Si cumple formato");
			}
		}
		
		if (proveedor.getTelefonoFijo() == null || proveedor.getTelefonoFijo().trim() == "" || proveedor.getTelefonoFijo().isEmpty()) {
			System.out.println("No se valida telefono fijo");
			proveedor.setTelefonoFijo(null);
		} else {
			System.out.println("Se procede a validar teléfono fijo");

			Matcher matcher = Constante.FIJO_PATTERN.matcher(proveedor.getTelefonoFijo());

			if (!matcher.matches()) {
				System.out.println("No cumple formato");

				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Formato de teléfono fijo inválido", ""));
				listarEntidad();
				return "";

			} else {
				System.out.println("Si cumple formato");
			}
		}
		
		if (proveedor.getCorreoElectronico() == null || proveedor.getCorreoElectronico().trim() == "" || proveedor.getCorreoElectronico().isEmpty()) {
			System.out.println("No se valida correo electronico");
			proveedor.setCorreoElectronico(null);
		} else {
			System.out.println("Se procede a validar correo electronico");

			Matcher matcher = Constante.EMAIL_PATTERN.matcher(proveedor.getCorreoElectronico());

			if (!matcher.matches()) {
				System.out.println("No cumple formato");

				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Formato de correo electrónico inválido", ""));
				listarEntidad();
				return "";

			} else {
				System.out.println("Si cumple formato");
			}
		}

		if (proveedor.getId() == null) {
			System.out.println("A guardar");
			try {
				servicio.crear(proveedor);
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
				servicio.actualizar(proveedor);
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

		entidad = new Proveedor();

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
			entidad = new Proveedor();
			entidadseleccionada = new Proveedor();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "No se selecciono ningun registro", ""));
		} else {
			entidad = entidadseleccionada;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Seleccion de registro exitosa", ""));
		}
	}

	public void onRowUnselect(UnselectEvent event) {
		entidad = new Proveedor();
		entidadseleccionada = new Proveedor();
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Se anulo seleccion de registro ", ""));
	}

	public void limpiar() {
		entidad = new Proveedor();
		entidadseleccionada = new Proveedor();

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
			if (lista.get(i).getRuc().toString().contains(filtro) || lista.get(i).getRazonSocial().contains(filtro)) {
				System.out.println("lista: " + lista.get(i).toString());
				listafiltro.add(lista.get(i));
			}
		}
		filtro = null;
		return "";
	}

}
