package pe.com.gesadmin.managed;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import pe.com.gesadmin.entity.Operacion;
import pe.com.gesadmin.entity.Periodo;
import pe.com.gesadmin.entity.Puesto;
import pe.com.gesadmin.entity.PuestoPersonaCargo;
import pe.com.gesadmin.service.OperacionService;
import pe.com.gesadmin.service.PeriodoService;
import pe.com.gesadmin.service.PuestoPersonaCargoService;
import pe.com.gesadmin.service.PuestoService;
import pe.com.gesadmin.service.impl.OperacionServiceImpl;
import pe.com.gesadmin.service.impl.PeriodoServiceImpl;
import pe.com.gesadmin.service.impl.PuestoPersonaCargoServiceImpl;
import pe.com.gesadmin.service.impl.PuestoServiceImpl;

@ManagedBean
@ViewScoped
public class OperacionEfectuarAdministracionBean {

	@ManagedProperty("#{usuarioSesionBean}")
	private UsuarioSesionBean usuarioSesionBean;
	public Integer idPuesto;
	public Integer idPeriodo;
	public String socio;
	public boolean booregistro = false;

	public String nroDoc;
	public String tipoDoc;
	public Date fechaPago;
	public Integer idPersonaResponsable;
	public String observaciones;
	private Date fechaMaxima;

	public List<Puesto> listaPuesto = new ArrayList<>();
	public List<PuestoPersonaCargo> listaPuestoPersonaCargos = new ArrayList<>();
	public List<Periodo> listaPerido = new ArrayList<>();
	public List<Operacion> listaAdminsitracion = new ArrayList<>();
	public List<String> operacionesCadenas = new ArrayList<>();
	private String[] operacionesSeleccionados;
	private List<Operacion> administracionesSeleccionadas;
	
	

	@EJB
	private PuestoService puestoService = new PuestoServiceImpl();
	@EJB
	private PeriodoService periodoService = new PeriodoServiceImpl();
	@EJB
	private OperacionService operacionService = new OperacionServiceImpl();
	@EJB
	private PuestoPersonaCargoService puestoPersonaCargoService = new PuestoPersonaCargoServiceImpl();

	public OperacionEfectuarAdministracionBean() {
		// TODO Auto-generated constructor stub
		idPuesto = null;
		idPeriodo = null;
		socio = "";

		nroDoc = null;
		tipoDoc = null;
		fechaPago = null;
		idPersonaResponsable = null;
		observaciones = null;

		listaAdminsitracion = new ArrayList<>();
		administracionesSeleccionadas = new ArrayList<>();

		booregistro = false;
		operacionesSeleccionados = null;
		operacionesCadenas = null;
		
		fechaMaxima  = new Date();
	}

	@PostConstruct
	public void init() {
		listarPuestos();
		listarPeriodos();
	}

	public Integer getIdPuesto() {
		return idPuesto;
	}

	public String[] getOperacionesSeleccionados() {
		return operacionesSeleccionados;
	}

	public void setOperacionesSeleccionados(String[] operacionesSeleccionados) {
		this.operacionesSeleccionados = operacionesSeleccionados;
	}

	public List<String> getOperacionesCadenas() {
		return operacionesCadenas;
	}

	public void setOperacionesCadenas(List<String> operacionesCadenas) {
		this.operacionesCadenas = operacionesCadenas;
	}

	public List<Operacion> getAdministracionesSeleccionadas() {
		return administracionesSeleccionadas;
	}

	public Date getFechaMaxima() {
		return fechaMaxima;
	}

	public void setFechaMaxima(Date fechaMaxima) {
		this.fechaMaxima = fechaMaxima;
	}

	public void setAdministracionesSeleccionadas(List<Operacion> administracionesSeleccionadas) {
		this.administracionesSeleccionadas = administracionesSeleccionadas;
	}

	public void setIdPuesto(Integer idPuesto) {
		this.idPuesto = idPuesto;
	}

	public Integer getIdPeriodo() {
		return idPeriodo;
	}

	public void setIdPeriodo(Integer idPeriodo) {
		this.idPeriodo = idPeriodo;
	}

	public String getSocio() {
		return socio;
	}

	public UsuarioSesionBean getUsuarioSesionBean() {
		return usuarioSesionBean;
	}

	public void setUsuarioSesionBean(UsuarioSesionBean usuarioSesionBean) {
		this.usuarioSesionBean = usuarioSesionBean;
	}

	public boolean isBooregistro() {
		return booregistro;
	}

	public void setBooregistro(boolean booregistro) {
		this.booregistro = booregistro;
	}

	public void setSocio(String socio) {
		this.socio = socio;
	}

	public String getNroDoc() {
		return nroDoc;
	}

	public void setNroDoc(String nroDoc) {
		this.nroDoc = nroDoc;
	}

	public String getTipoDoc() {
		return tipoDoc;
	}

	public void setTipoDoc(String tipoDoc) {
		this.tipoDoc = tipoDoc;
	}

	public Date getFechaPago() {
		return fechaPago;
	}

	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}

	public Integer getIdPersonaResponsable() {
		return idPersonaResponsable;
	}

	public void setIdPersonaResponsable(Integer idPersonaResponsable) {
		this.idPersonaResponsable = idPersonaResponsable;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public List<Puesto> getListaPuesto() {
		return listaPuesto;
	}

	public void setListaPuesto(List<Puesto> listaPuesto) {
		this.listaPuesto = listaPuesto;
	}

	public List<PuestoPersonaCargo> getListaPuestoPersonaCargos() {
		return listaPuestoPersonaCargos;
	}

	public void setListaPuestoPersonaCargos(List<PuestoPersonaCargo> listaPuestoPersonaCargos) {
		this.listaPuestoPersonaCargos = listaPuestoPersonaCargos;
	}

	public List<Periodo> getListaPerido() {
		return listaPerido;
	}

	public void setListaPerido(List<Periodo> listaPerido) {
		this.listaPerido = listaPerido;
	}

	public List<Operacion> getListaAdminsitracion() {
		return listaAdminsitracion;
	}

	public void setListaAdminsitracion(List<Operacion> listaAdminsitracion) {
		this.listaAdminsitracion = listaAdminsitracion;
	}

	public void setPuestoService(PuestoService puestoService) {
		this.puestoService = puestoService;
	}

	public void setPeriodoService(PeriodoService periodoService) {
		this.periodoService = periodoService;
	}

	public void setOperacionService(OperacionService operacionService) {
		this.operacionService = operacionService;
	}

	public void setPuestoPersonaCargoService(PuestoPersonaCargoService puestoPersonaCargoService) {
		this.puestoPersonaCargoService = puestoPersonaCargoService;
	}

	public void listarPuestos() {
		listaPuesto = new ArrayList<>();
		listaPuesto = puestoService.listarActivo();
	}

	public void listarPeriodos() {

		listaPerido = new ArrayList<>();
		listaPerido = periodoService.listar();
	}

	public String consultarDeudaAdministrativa() {

		listaAdminsitracion = new ArrayList<>();
		try {
			listaAdminsitracion = operacionService.listarAdministracionesPuestoIdPeridooId(idPuesto, idPeriodo);
			convertirListaAdministrativaToArray();
		} catch (Exception e) {
			// TODO: handle exception
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Error al consultar registros de deudas programadas", ""));
			limpiar();
			return "";
		}
		
		booregistro = true;

		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Seleccione registros de periodos del mismo periodo que desea cancelar", ""));

		return "";

	}

	public void limpiar() {
		listaAdminsitracion = new ArrayList<>();
		idPeriodo = null;
		idPuesto = null;
		socio = "";

		idPersonaResponsable = null;
		fechaPago = null;
		nroDoc = null;
		tipoDoc = null;
		observaciones = null;

		booregistro = false;
		operacionesCadenas = null;
		administracionesSeleccionadas = new ArrayList<>();

	}

	public void listarResponsablesPersonasPuesto() {
		listaPuestoPersonaCargos = new ArrayList<>();

		try {
			listaPuestoPersonaCargos = puestoPersonaCargoService.listarPuestoId(idPuesto);
			obtenerPropietario();

		} catch (Exception e) {
			// TODO: handle exception
			listaPuestoPersonaCargos = null;
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Error al recuperar personas - cargos asociadas al puesto seleccionado", ""));
			return;
		}

		if (listaPuestoPersonaCargos == null || listaPuestoPersonaCargos.isEmpty()) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Asocie a una persona a un puesto e indique su cargo, actualmente no existe estos registros", ""));
			return;
		}
	}

	public boolean validarPeriodo() {

		for (int i = 0; i < administracionesSeleccionadas.size()-1; i++) {
			if (administracionesSeleccionadas.get(i).getPeriodo().getId() == administracionesSeleccionadas.get(i + 1).getPeriodo().getId()) {
				System.out.println("Pertenece al mismo periodo");
			} else {
				System.out.println("NO Pertenece al mismo periodo");
				return false;
			}
		}

		return true;
	}

	public String cancelarDeudasSeleccionadas() {
		
		administracionesSeleccionadas = new ArrayList<>();
		administracionesSeleccionadas = convertirPuestoArrayToList(operacionesSeleccionados);

		if (administracionesSeleccionadas == null || administracionesSeleccionadas.size()==0) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
					"No a seleccionado ninguna deuda para cancelar, seleccione minimo un registro", ""));
			return "";
		} else {
			if (administracionesSeleccionadas.size() > 1) {
				System.out.println("Si es necesario validar correspondencia a un solo periodo");

				boolean localValidacion = validarPeriodo();

				if (localValidacion) {
					System.out.println("Aprobo la validacion, procedemos");
				} else {
					System.out.println("Desaprobo la validacion, abortamos");
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Solo se permite registros de un mismo periodo", ""));

					return "";
				}

			} else {
				System.out.println("No es necesario validar correspondencia a un solo periodo");
			}
		}

		Integer idEstatusOperacion = 2;

		for (int i = 0; i <= administracionesSeleccionadas.size() - 1; i++) {

			try {
				operacionService.registrarPagoDos(administracionesSeleccionadas.get(i).getId(), idPersonaResponsable,
						idEstatusOperacion, tipoDoc, nroDoc, usuarioSesionBean.getUsuario(), null, null, null,
						fechaPago, observaciones);

			} catch (Exception e) {
				// TODO: handle exception
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al registrar cancelacion de deuda", ""));
			}
		}

		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Cancelaciones realizadas de manera satisfactoria", ""));
		limpiar();
		return "";

	}

	public void obtenerPropietario() {

		if (listaPuestoPersonaCargos == null || listaPuestoPersonaCargos.isEmpty()) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
					"No hay socio o persona asociada en este puesto.",
					""));

			socio = "No registrado";
		} else {
			socio = listaPuestoPersonaCargos.get(0).getPersona().getNombre() + " "
					+ listaPuestoPersonaCargos.get(0).getPersona().getPaterno() + " "
					+ listaPuestoPersonaCargos.get(0).getPersona().getMaterno();
		}
	}
	
	
	public List<Operacion> convertirPuestoArrayToList(String[] listaTarget) {
		
		if(listaTarget==null) {
			return null;
		}
		System.out.println("Deudas Administrativas seleccionadas: " + listaTarget.toString());
		List<Operacion> listaOperaciones = new ArrayList<>();
		List<String> arregloList = Arrays.asList(listaTarget);
		for (int i = 0; i <= arregloList.size() - 1; i++) {
			for (int j = 0; j <= listaAdminsitracion.size() - 1; j++) {
				if (listaAdminsitracion.get(j).getDescripcion().equalsIgnoreCase(arregloList.get(i))) {
					Operacion opelOc = new Operacion();
					opelOc = listaAdminsitracion.get(j);
					listaOperaciones.add(opelOc);
				}
			}
		}
		return listaOperaciones;
	}
	
	public void  convertirListaAdministrativaToArray() {

		operacionesCadenas = null;
		List<String> listaDescripcion = new ArrayList<>();
		for (int i = 0; i <= listaAdminsitracion.size() - 1; i++) {
			listaDescripcion.add(listaAdminsitracion.get(i).getDescripcion());
		}
		operacionesCadenas = listaDescripcion;
	}

}
