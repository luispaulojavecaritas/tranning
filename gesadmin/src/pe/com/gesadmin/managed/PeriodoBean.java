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
import pe.com.gesadmin.entity.Operacion;
import pe.com.gesadmin.entity.Periodo;
import pe.com.gesadmin.entity.Puesto;
import pe.com.gesadmin.service.AnioFiscalService;
import pe.com.gesadmin.service.OperacionService;
import pe.com.gesadmin.service.PeriodoService;
import pe.com.gesadmin.service.PuestoService;
import pe.com.gesadmin.service.impl.AnioFiscalServiceImpl;
import pe.com.gesadmin.service.impl.OperacionServiceImpl;
import pe.com.gesadmin.service.impl.PeriodoServiceImpl;
import pe.com.gesadmin.service.impl.PuestoServiceImpl;
import pe.com.gesadmin.util.UtilFechas;

@ManagedBean
@ViewScoped
public class PeriodoBean {

	private List<Periodo> lista = new ArrayList<>();
	private List<Periodo> listafiltro;
	private Periodo entidad = new Periodo();
	private Periodo entidadseleccionada = new Periodo();

	private AnioFiscal anioFiscalActual = new AnioFiscal();

	private Periodo periodoActual = new Periodo();

	private List<AnioFiscal> listaAnioFiscal = new ArrayList<>();

	private String filtro;

	@EJB
	private PeriodoService servicio = new PeriodoServiceImpl();
	@EJB
	private PuestoService puestoService = new PuestoServiceImpl();
	@EJB
	private OperacionService operacionService = new OperacionServiceImpl();

	@EJB
	private AnioFiscalService anioFiscalService = new AnioFiscalServiceImpl();

	public PeriodoBean() {
		// TODO Auto-generated constructor stub
		filtro = null;
		entidad = new Periodo();

		anioFiscalActual = new AnioFiscal();
		periodoActual = new Periodo();
	}

	@PostConstruct
	public void init() {
		listarEntidad();
		listarAnioFiscal();
		obtenerAnioFiscal();
		obtenerPeriodoActual();
	}

	public List<Periodo> getLista() {
		return lista;
	}

	public void setLista(List<Periodo> lista) {
		this.lista = lista;
	}

	public List<Periodo> getListafiltro() {
		return listafiltro;
	}

	public void setListafiltro(List<Periodo> listafiltro) {
		this.listafiltro = listafiltro;
	}

	public Periodo getEntidad() {
		return entidad;
	}

	public void setEntidad(Periodo entidad) {
		this.entidad = entidad;
	}

	public Periodo getEntidadseleccionada() {
		return entidadseleccionada;
	}

	public void setEntidadseleccionada(Periodo entidadseleccionada) {
		this.entidadseleccionada = entidadseleccionada;
	}

	public void setServicio(PeriodoService servicio) {
		this.servicio = servicio;
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

	public List<AnioFiscal> getListaAnioFiscal() {
		return listaAnioFiscal;
	}

	public void setListaAnioFiscal(List<AnioFiscal> listaAnioFiscal) {
		this.listaAnioFiscal = listaAnioFiscal;
	}

	public void setAnioFiscalService(AnioFiscalService anioFiscalService) {
		this.anioFiscalService = anioFiscalService;
	}

	public void setPuestoService(PuestoService puestoService) {
		this.puestoService = puestoService;
	}

	public void setOperacionService(OperacionService operacionService) {
		this.operacionService = operacionService;
	}

	public AnioFiscal getAnioFiscalActual() {
		return anioFiscalActual;
	}

	public void setAnioFiscalActual(AnioFiscal anioFiscalActual) {
		this.anioFiscalActual = anioFiscalActual;
	}

	public Periodo getPeriodoActual() {
		return periodoActual;
	}

	public void setPeriodoActual(Periodo periodoActual) {
		this.periodoActual = periodoActual;
	}

	public String guardar() {

		if (anioFiscalActual == null) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
					"No hay año fiscal activo. Registre un año fiscal.", ""));
			return "";
		} else {
			System.out.println("Si hay anio fiscal activo");
		}

		entidad.setAnioFiscal(new AnioFiscal(anioFiscalActual.getId()));

		if (entidad.getId() == null) {

			System.out.println("A guardar");

			if (lista == null || lista.isEmpty()) {

				System.out.println("No se validara operaciones, es primer registro");

			} else {
				
				System.out.println("Si se validara operaciones");

				if (periodoActual == null) {
					
					System.out.println("Actualmente no hay un periodo actual, No se validara operaciones");

				} else {					
					
					System.out.println("Si se validara operaciones");

					boolean validacionServicios = validarOperacionesLuzAgua();
					boolean validacionAdministracion = validarOperacionesAdministrativo();

					if (validacionServicios) {
						System.out.println("Cumple validacion operacion servicios luz y agua");
					} else {
						FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
								"Antes de aperturar un nuevo Periodo, culmine la creacion registros de operaciones referente a la medida de consumo de servicios de luz y agua",
								""));
						return "";
					}

					if (validacionAdministracion) {
						System.out.println("Cumple validacion operacion de categoria administrativo");
					} else {
						FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
								"Antes de aperturar un nuevo Periodo, culmine la creacion registros de operaciones referente a gastos administrativos",
								""));
						return "";
					}
				}

			}

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

		obtenerAnioFiscal();
		obtenerPeriodoActual();

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

		entidad = new Periodo();

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
			entidad = new Periodo();
			entidadseleccionada = new Periodo();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "No se selecciono ningun registro", ""));
		} else {
			entidad = entidadseleccionada;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Seleccion de registro exitosa", ""));
		}
	}

	public void onRowUnselect(UnselectEvent event) {
		entidad = new Periodo();
		entidadseleccionada = new Periodo();
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Se anulo seleccion de registro ", ""));
	}

	public void limpiar() {
		entidad = new Periodo();
		entidadseleccionada = new Periodo();

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

	public void listarAnioFiscal() {

		try {
			listaAnioFiscal = anioFiscalService.listarActivo();
		} catch (Exception e) {
			// TODO: handle exception
			listaAnioFiscal = null;
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error.",
					"Problemas al recuperar registros año fiscal"));
		}
	}

	public boolean validarOperacionesLuzAgua() {

		List<Puesto> listaPuestos = new ArrayList<>();
		List<Operacion> listaOperaciones = new ArrayList<>();

		listaPuestos = puestoService.listarFiltro(true);
		listaOperaciones = operacionService.listarPorPeriodoactualCategoriaLuzAgua();

		Integer diferencia = (listaPuestos.size() * 2) - listaOperaciones.size();

		if (diferencia == 0) {
			return true;
		} else {
			return false;
		}

	}

	public boolean validarOperacionesAdministrativo() {

		List<Puesto> listaPuestos = new ArrayList<>();
		List<Operacion> listaOperaciones = new ArrayList<>();

		listaPuestos = puestoService.listarFiltro(true);
		listaOperaciones = operacionService.listarPorPeriodoactualCategoriaAdministracion();

		int cantidadDias = periodoActual.getDias();

		Integer diferencia = (listaPuestos.size() * cantidadDias) - listaOperaciones.size();

		if (diferencia == 0) {
			return true;
		} else {
			return false;
		}

	}

	public void obtenerAnioFiscal() {

		List<AnioFiscal> listaAnioFiscal = anioFiscalService.listarActivo();

		listaAnioFiscal.toString();

		if (listaAnioFiscal == null || listaAnioFiscal.isEmpty()) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
					"No hay año fiscal activo. Registre un año fiscal.", ""));
			anioFiscalActual = null;
		} else {
			anioFiscalActual = listaAnioFiscal.get(0);
		}
	}

	public void obtenerPeriodoActual() {

		List<Periodo> listaPeriodoActivo = servicio.listarActivo();

		listaPeriodoActivo.toString();

		if (listaPeriodoActivo == null || listaPeriodoActivo.isEmpty()) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
					"No hay periodo fiscal activo. Registre un periodo fiscal.", ""));
			periodoActual = null;
		} else {
			periodoActual = listaPeriodoActivo.get(0);
		}
	}

}
