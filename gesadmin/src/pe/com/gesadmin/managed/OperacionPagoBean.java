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
import pe.com.gesadmin.entity.PuestoPersonaCargo;
import pe.com.gesadmin.service.AnioFiscalService;
import pe.com.gesadmin.service.OperacionService;
import pe.com.gesadmin.service.PeriodoService;
import pe.com.gesadmin.service.PuestoPersonaCargoService;
import pe.com.gesadmin.service.PuestoService;
import pe.com.gesadmin.service.impl.AnioFiscalServiceImpl;
import pe.com.gesadmin.service.impl.OperacionServiceImpl;
import pe.com.gesadmin.service.impl.PeriodoServiceImpl;
import pe.com.gesadmin.service.impl.PuestoPersonaCargoServiceImpl;
import pe.com.gesadmin.service.impl.PuestoServiceImpl;

@ManagedBean
@ViewScoped
public class OperacionPagoBean {

	private List<Operacion> lista = new ArrayList<>();
	private List<Operacion> listafiltro;
	private Operacion entidad = new Operacion();
	private Operacion entidadseleccionada = new Operacion();

	private List<AnioFiscal> listaAnioFiscal;
	private List<Periodo> listaPeriodo;
	private List<Puesto> listaPuesto;
	private List<PuestoPersonaCargo> listaPuestoPersonaCargo;
	

	private Integer idAnioFiscal;
	private Integer idPeriodo;
	private Integer idPuesto;
	private Integer idPersona;
	
	private String tipoDocumento;
	private String nroDocumento;
	
	private Integer cantidadRegistros;
	
	private boolean booDetalle;
	private boolean booRegistro;

	private String filtro;

	@EJB
	private OperacionService servicio = new OperacionServiceImpl();

	@EJB
	private AnioFiscalService anioFiscalService = new AnioFiscalServiceImpl();

	@EJB
	private PeriodoService periodoService = new PeriodoServiceImpl();
	
	@EJB
	private PuestoService puestoService = new PuestoServiceImpl();
	
	@EJB
	private PuestoPersonaCargoService puestoPersonaCargoService  = new PuestoPersonaCargoServiceImpl();
	

	public OperacionPagoBean() {
		// TODO Auto-generated constructor stub
		filtro = null;
		entidad = new Operacion();

		listaPeriodo = null;
		listaPuesto = null;
		listaPuestoPersonaCargo = null;
		
		
		idAnioFiscal = null;
		idPeriodo = null;
		idPuesto = null;
		idPersona = null;
		
		nroDocumento = null;
		tipoDocumento = null;
		
		cantidadRegistros = 0;
	}

	@PostConstruct
	public void init() {
		listarEntidad();
		listarAnioFiscal();
		listarPuestos();
		
	}

	public List<Operacion> getLista() {
		return lista;
	}

	public void setLista(List<Operacion> lista) {
		this.lista = lista;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getNroDocumento() {
		return nroDocumento;
	}

	public void setNroDocumento(String nroDocumento) {
		this.nroDocumento = nroDocumento;
	}

	public List<Operacion> getListafiltro() {
		return listafiltro;
	}

	public void setListafiltro(List<Operacion> listafiltro) {
		this.listafiltro = listafiltro;
	}

	public Operacion getEntidad() {
		return entidad;
	}

	public void setEntidad(Operacion entidad) {
		this.entidad = entidad;
	}

	public Operacion getEntidadseleccionada() {
		return entidadseleccionada;
	}

	public void setEntidadseleccionada(Operacion entidadseleccionada) {
		this.entidadseleccionada = entidadseleccionada;
	}

	public void setServicio(OperacionService servicio) {
		this.servicio = servicio;
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

	public void setAnioFiscalService(AnioFiscalService anioFiscalService) {
		this.anioFiscalService = anioFiscalService;
	}

	public void setPeriodoService(PeriodoService periodoService) {
		this.periodoService = periodoService;
	}

	public Integer getIdAnioFiscal() {
		return idAnioFiscal;
	}

	public void setIdAnioFiscal(Integer idAnioFiscal) {
		this.idAnioFiscal = idAnioFiscal;
	}

	public Integer getIdPeriodo() {
		return idPeriodo;
	}

	public void setIdPeriodo(Integer idPeriodo) {
		this.idPeriodo = idPeriodo;
	}

	public List<AnioFiscal> getListaAnioFiscal() {
		return listaAnioFiscal;
	}

	public void setListaAnioFiscal(List<AnioFiscal> listaAnioFiscal) {
		this.listaAnioFiscal = listaAnioFiscal;
	}

	public List<Periodo> getListaPeriodo() {
		return listaPeriodo;
	}

	public void setListaPeriodo(List<Periodo> listaPeriodo) {
		this.listaPeriodo = listaPeriodo;
	}

	public boolean isBooDetalle() {
		return booDetalle;
	}

	public void setBooDetalle(boolean booDetalle) {
		this.booDetalle = booDetalle;
	}

	public boolean isBooRegistro() {
		return booRegistro;
	}

	public void setBooRegistro(boolean booRegistro) {
		this.booRegistro = booRegistro;
	}

	public void setPuestoService(PuestoService puestoService) {
		this.puestoService = puestoService;
	}

	public Integer getIdPuesto() {
		return idPuesto;
	}

	public void setIdPuesto(Integer idPuesto) {
		this.idPuesto = idPuesto;
	}

	public List<Puesto> getListaPuesto() {
		return listaPuesto;
	}

	public void setListaPuesto(List<Puesto> listaPuesto) {
		this.listaPuesto = listaPuesto;
	}

	public void setPuestoPersonaCargoService(PuestoPersonaCargoService puestoPersonaCargoService) {
		this.puestoPersonaCargoService = puestoPersonaCargoService;
	}

	public List<PuestoPersonaCargo> getListaPuestoPersonaCargo() {
		return listaPuestoPersonaCargo;
	}

	public void setListaPuestoPersonaCargo(List<PuestoPersonaCargo> listaPuestoPersonaCargo) {
		this.listaPuestoPersonaCargo = listaPuestoPersonaCargo;
	}

	public Integer getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(Integer idPersona) {
		this.idPersona = idPersona;
	}	
	
	public Integer getCantidadRegistros() {
		return cantidadRegistros;
	}

	public void setCantidadRegistros(Integer cantidadRegistros) {
		this.cantidadRegistros = cantidadRegistros;
	}

	public String registrar_Pago() {
		
		Integer idEstatusOperacion = 2;
		try {
			servicio.registrarPago(entidadseleccionada.getId(), idPersona, idEstatusOperacion, tipoDocumento, nroDocumento);
			booDetalle = false;
			entidad = new Operacion();
			entidadseleccionada = new Operacion();
			idPersona = null;
			nroDocumento = null;
			tipoDocumento = null;
		} catch (Exception e) {
			// TODO: handle exception
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error al realizar registro de pago", ""));
			return "";
		}
		
		listarEntidad();
		
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO,"Registro de pago exitoso", ""));
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

		entidad = new Operacion();

		try {
			entidad = servicio.recuperar(entidadseleccionada.getId());
		} catch (Exception e) {
			// TODO: handle exception
			entidad = null;
			entidadseleccionada = null;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Problemas al recuperar registro", ""));
		}
	}

	public void listarAnioFiscal() {
		listaAnioFiscal = new ArrayList<>();
		try {
			listaAnioFiscal = anioFiscalService.listar();
		} catch (Exception e) {
			// TODO: handle exception
			listaAnioFiscal = null;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Problemas al recuperar registros año fiscal", ""));
		}
	}

	public void listarEntidad() {
		lista = new ArrayList<>();
		Integer estatusOperacionPendiente = 1;
		try {
			lista = servicio.listarPorPeriodoIdPuestoIdEstatusOperacionId(idPeriodo, idPuesto, estatusOperacionPendiente);
		} catch (Exception e) {
			// TODO: handle exception
			lista = null;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Problemas al recuperar registros", ""));
		}

		listafiltro = lista;
		
		obtenerCantidadRegistrosEntidad();
	}

	public void listarPeriodo() {

		if (idAnioFiscal == null) {
			
			listaPeriodo = null;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Seleccione periodo de anio fiscal", ""));
		} else {

			listaPeriodo = new ArrayList<>();
			try {
				listaPeriodo = periodoService.listarPorIdAnioFiscal(idAnioFiscal);
			} catch (Exception e) {
				// TODO: handle exception
				listaPeriodo = null;
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Problemas al recuperar registros periodo", ""));
			}
		}
	}
	

	public void onRowSelect(SelectEvent event) {
		
		if (entidadseleccionada == null) {
			entidad = new Operacion();
			entidadseleccionada = new Operacion();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "No se selecciono ningun registro", ""));
		} else {
			entidad = entidadseleccionada;
			booDetalle = true;
			
			
			try {
				listaPuestoPersonaCargo = new ArrayList<>();
				listaPuestoPersonaCargo = puestoPersonaCargoService.listarPuestoId(idPuesto);
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("Excepcion al listar listaPuestoPersonaCargo: " + e.toString());
				listaPuestoPersonaCargo = null;
			}
			
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Seleccion de registro exitosa", ""));
		}
	}

	public void onRowUnselect(UnselectEvent event) {
		entidad = new Operacion();
		entidadseleccionada = new Operacion();
		
		idAnioFiscal = null;
		idPeriodo = null;
		idPersona = null;
		nroDocumento = null;
		tipoDocumento = null;
		idPuesto = null;
		
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Se anulo seleccion de registro ", ""));
	}

	public void limpiar() {
		entidad = new Operacion();
		entidadseleccionada = new Operacion();
		
		idAnioFiscal = null;
		idPeriodo = null;
		idPuesto = null;
		idPersona = null;
		nroDocumento = null;
		tipoDocumento = null;
		
		cantidadRegistros = 0;

		listafiltro = null;
		listaPuestoPersonaCargo = null;
		listaPeriodo = null;
		
		booDetalle = false;
		
		lista = null;

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
			if (lista.get(i).getDescripcion().contains(filtro) || lista.get(i).getCategoriaOperacion().getDescripcion().contains(filtro)) {
				System.out.println("lista: " + lista.get(i).toString());
				listafiltro.add(lista.get(i));
			}
		}
		filtro = null;
		
		obtenerCantidadRegistrosEntidad();
		
		return "";
	}
	
	public void listarPuestos() {
		
		listaPuesto = new ArrayList<>();
		
		try {
			listaPuesto = puestoService.listarActivo();
		} catch (Exception e) {
			// TODO: handle exception
			listaPuesto = null;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Problemas al recuperar registros puestos (deudores)", ""));
		}
	}
	
	
	public void obtenerCantidadRegistrosEntidad() {
		
		if(listafiltro == null || listafiltro.isEmpty()) {
			cantidadRegistros = 0;
		}else {
			cantidadRegistros = listafiltro.size();
		}
	}
	

}
