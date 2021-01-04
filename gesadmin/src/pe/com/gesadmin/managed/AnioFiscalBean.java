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
public class AnioFiscalBean {

	private List<AnioFiscal> lista = new ArrayList<>();
	private List<AnioFiscal> listafiltro;
	private AnioFiscal entidad = new AnioFiscal();
	private AnioFiscal entidadseleccionada = new AnioFiscal();
	
	private AnioFiscal anioFiscalActual = new AnioFiscal();
	private Periodo periodoActual = new Periodo();
	

	private String filtro;

	@EJB
	private AnioFiscalService servicio = new AnioFiscalServiceImpl();
	@EJB
	private PuestoService puestoService = new PuestoServiceImpl();
	@EJB
	private OperacionService operacionService = new OperacionServiceImpl();	
	@EJB
	private PeriodoService periodoService = new PeriodoServiceImpl();	


	public AnioFiscalBean() {
		// TODO Auto-generated constructor stub
		filtro = null;
		entidad = new AnioFiscal();
		
		anioFiscalActual = new AnioFiscal();
		periodoActual = new Periodo();
	}

	@PostConstruct
	public void init() {
		listarEntidad();
		obtenerAnioFiscalActual();
	}

	public List<AnioFiscal> getLista() {
		return lista;
	}

	public void setLista(List<AnioFiscal> lista) {
		this.lista = lista;
	}

	public List<AnioFiscal> getListafiltro() {
		return listafiltro;
	}

	public void setListafiltro(List<AnioFiscal> listafiltro) {
		this.listafiltro = listafiltro;
	}

	public AnioFiscal getEntidad() {
		return entidad;
	}

	public void setEntidad(AnioFiscal entidad) {
		this.entidad = entidad;
	}

	public AnioFiscal getEntidadseleccionada() {
		return entidadseleccionada;
	}

	public void setEntidadseleccionada(AnioFiscal entidadseleccionada) {
		this.entidadseleccionada = entidadseleccionada;
	}

	public void setServicio(AnioFiscalService servicio) {
		this.servicio = servicio;
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
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

	public void setPeriodoService(PeriodoService periodoService) {
		this.periodoService = periodoService;
	}

	public Periodo getPeriodoActual() {
		return periodoActual;
	}

	public void setPeriodoActual(Periodo periodoActual) {
		this.periodoActual = periodoActual;
	}

	public String guardar() {

		if (entidad.getId() == null) {
			
			System.out.println("A guardar");

			if (lista == null || lista.isEmpty()) {
				
				System.out.println("No se validara operaciones, es primer registro");

			} else {
				
				System.out.println("Si se validara operaciones");
				
				if (periodoActual == null) {
					System.out.println("No hay un Periodo fiscal activo");
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
							"No hay periodo fiscal activo. Registre un periodo fiscal.", ""));
					return "";
				}

				boolean validacionServicios = validarOperacionesLuzAgua();
				boolean validacionAdministracion = validarOperacionesAdministrativo();

				if (validacionServicios) {
					System.out.println("Cumple validacion operaciones de categoria  servicios luz y agua");
				} else {
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Complete registros de programacion de deuda de concepto servicios de luz y agua", ""));
					return "";
				}

				if (validacionAdministracion) {
					System.out.println("Cumple validacion programacion de deuda de concepto administrativo");
				} else {
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Complete registros de programacion de deuda de concepto administrativo", ""));
					return "";
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
		
		limpiar();
		listarEntidad();
		obtenerAnioFiscalActual();
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

		entidad = new AnioFiscal();

		try {
			entidad = servicio.recuperar(entidadseleccionada.getId());
		} catch (Exception e) {
			// TODO: handle exception
			entidad = null;
			entidadseleccionada = null;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al recuperar registro", ""));
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
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al recuperar registros", ""));
		}
		
		listafiltro = lista;
	}

	public void onRowSelect(SelectEvent event) {
		if (entidadseleccionada == null) {
			entidad = new AnioFiscal();
			entidadseleccionada = new AnioFiscal();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "No se selecciono ningun registro", ""));
		} else {
			entidad = entidadseleccionada;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Seleccion de registro exitosa", ""));
		}
	}

	public void onRowUnselect(UnselectEvent event) {
		entidad = new AnioFiscal();
		entidadseleccionada = new AnioFiscal();
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Se anulo seleccion de registro ", ""));
	}

	public void limpiar() {
		entidad = new AnioFiscal();
		entidadseleccionada = new AnioFiscal();
		
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
	
	public boolean validarOperacionesLuzAgua() {

		List<Puesto> listaPuestosAgua = new ArrayList<>();
		List<Puesto> listaPuestosLuz = new ArrayList<>();
		List<Operacion> listaOperaciones = new ArrayList<>();
		
		listaPuestosAgua = puestoService.listarActivoAgua();
		listaPuestosLuz = puestoService.listarActivoLuz();
		
		Integer cantidadAgua = (listaPuestosAgua==null ||listaPuestosAgua.isEmpty())?0:listaPuestosAgua.size();
		Integer cantidadLuz = (listaPuestosLuz==null ||listaPuestosLuz.isEmpty())?0:listaPuestosLuz.size();
		
		listaOperaciones = operacionService.listarPorPeriodoactualCategoriaLuzAgua();

		Integer diferencia = (cantidadAgua+cantidadLuz) - listaOperaciones.size();
		System.out.println("vALORES DE oPERACIONES LUZ Y AGUA: AGUA "+ cantidadAgua + " LUZ "+ cantidadLuz +" DIFERENCIA " + diferencia);

		if (diferencia == 0) {
			return true;
		} else {
			return false;
		}

	}

	public boolean validarOperacionesAdministrativo() {

		List<Puesto> listaPuestos = new ArrayList<>();
		List<Operacion> listaOperaciones = new ArrayList<>();

		listaPuestos = puestoService.listarFiltroNoAdminNiPropiedad();
		listaOperaciones = operacionService.listarPorPeriodoactualCategoriaAdministracion();

		UtilFechas utilFechas = new UtilFechas();
		int cantidadDias = periodoActual.getDias();

		Integer diferencia = (listaPuestos.size() * cantidadDias) - listaOperaciones.size();

		if (diferencia == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	
	public void obtenerAnioFiscalActual() {
		List<AnioFiscal> listaAnioFiscal = servicio.listarActivo();
		
		listaAnioFiscal.toString();

		if (listaAnioFiscal == null || listaAnioFiscal.isEmpty()) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
					"No hay año fiscal activo. Registre un año fiscal.", ""));
			anioFiscalActual = null;
		} else {
			anioFiscalActual = listaAnioFiscal.get(0);
		}
		
		obtenerPeriodoFiscalActual();
	}
	
	public void obtenerPeriodoFiscalActual() {
		List<Periodo> listaPeriodoFiscal = periodoService.listarActivo();
		
		listaPeriodoFiscal.toString();

		if (listaPeriodoFiscal == null || listaPeriodoFiscal.isEmpty()) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
					"No hay periodo fiscal activo. Registre un periodo fiscal.", ""));
			periodoActual = null;
		} else {
			periodoActual = listaPeriodoFiscal.get(0);
		}
	}


}
