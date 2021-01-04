package pe.com.gesadmin.managed;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import pe.com.gesadmin.entity.MedidaServicio;
import pe.com.gesadmin.entity.Operacion;
import pe.com.gesadmin.entity.Periodo;
import pe.com.gesadmin.entity.Puesto;
import pe.com.gesadmin.entity.TipoServicio;
import pe.com.gesadmin.entity.Variable;
import pe.com.gesadmin.entity.transfer.LecturasMedidasPreOperacion;
import pe.com.gesadmin.entity.transfer.OperacionAdministracionTransfer;
import pe.com.gesadmin.service.MedidaServicioService;
import pe.com.gesadmin.service.OperacionService;
import pe.com.gesadmin.service.PeriodoService;
import pe.com.gesadmin.service.PuestoService;
import pe.com.gesadmin.service.TipoServicioService;
import pe.com.gesadmin.service.VariableService;
import pe.com.gesadmin.service.impl.MedidaServicioServiceImpl;
import pe.com.gesadmin.service.impl.OperacionServiceImpl;
import pe.com.gesadmin.service.impl.PeriodoServiceImpl;
import pe.com.gesadmin.service.impl.PuestoServiceImpl;
import pe.com.gesadmin.service.impl.TipoServicioServiceImpl;
import pe.com.gesadmin.service.impl.VariableServiceImpl;
import pe.com.gesadmin.util.Conversiones;

@ManagedBean
@ViewScoped
public class OperacionConsumoServicioBean {

	@ManagedProperty("#{usuarioSesionBean}")
	private UsuarioSesionBean usuarioSesionBean;

	private List<Operacion> lista = new ArrayList<>();
	private List<Operacion> listafiltro;
	private Operacion entidad = new Operacion();
	private Operacion entidadseleccionada = new Operacion();

	private Periodo periodoActual = new Periodo();
	private Periodo periodoAnterior = new Periodo();
	private TipoServicio tipoServicio = new TipoServicio();
	private TipoServicio tipoServicioActual = new TipoServicio();

	private List<TipoServicio> listaTipoServicio = new ArrayList<>();
	
	private List<Puesto> listaPuestoAgua = new ArrayList<>();
	private List<Puesto> listaPuestoLuz = new ArrayList<>();
	
	private List<Puesto> listaPuestoFiltro = new ArrayList<>();

	List<LecturasMedidasPreOperacion> listaPreOperacion = new ArrayList<>();

	private Integer cantidadAgua;
	private Integer cantidadLuz;
	private String claseAgua;
	private String claseLuz;
	private String mensajeAgua;
	private String mensajeLuz;

	private List<Operacion> listaEntidadLuz = new ArrayList<>();
	private List<Operacion> listaEntidadAgua = new ArrayList<>();

	private boolean booBtnEliminar = false;
	private boolean booTipoServicio = false;
	private boolean booEntidadRegistro = false;

	private String lecturaAnterior = "No registra";

	private Variable variableCostoConsumoLuz = new Variable();
	private Variable variableCostoAlumbradoLuz = new Variable();
	private Variable variableCostoConsumoAgua = new Variable();

	private String filtro;

	@EJB
	private OperacionService servicio = new OperacionServiceImpl();
	@EJB
	private MedidaServicioService medidaServicioService = new MedidaServicioServiceImpl();
	@EJB
	private PeriodoService periodoService = new PeriodoServiceImpl();
	@EJB
	private TipoServicioService tipoServicioService = new TipoServicioServiceImpl();
	@EJB
	private PuestoService puestoService = new PuestoServiceImpl();
	@EJB
	private VariableService variableService = new VariableServiceImpl();

	private Conversiones conversiones = new Conversiones();

	public OperacionConsumoServicioBean() {
		// TODO Auto-generated constructor stub
		filtro = null;
		entidad = new Operacion();
		lista = new ArrayList<>();
		listaTipoServicio = new ArrayList<>();

		periodoActual = new Periodo();
		periodoAnterior = new Periodo();

		tipoServicio = new TipoServicio();
		tipoServicioActual = new TipoServicio();
	//	listaPuesto = new ArrayList<>();
		listaPuestoAgua = new ArrayList<>();
		listaPuestoLuz = new ArrayList<>();

		cantidadAgua = 0;
		cantidadLuz = 0;
		claseAgua = "RedBack";
		claseLuz = "RedBack";
		mensajeAgua = "";
		mensajeLuz = "";

		listaEntidadLuz = new ArrayList<>();
		listaEntidadAgua = new ArrayList<>();
		listaPuestoFiltro = new ArrayList<>();

		listaPreOperacion = new ArrayList<>();

		variableCostoConsumoLuz = new Variable();
		variableCostoAlumbradoLuz = new Variable();
		variableCostoConsumoAgua = new Variable();

		booBtnEliminar = false;
		booEntidadRegistro = false;
		booTipoServicio = false;

		lecturaAnterior = "No registra";
	}

	@PostConstruct
	public void init() {
		/*
		 * listarPuestos(); listarEntidad(); listarTipoServicio();
		 * obtenerPeriodoActual(); obtenerVariables();
		 */

		cargaInicial();
	}

	public List<Operacion> getLista() {
		return lista;
	}

	public void setLista(List<Operacion> lista) {
		this.lista = lista;
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

	public Periodo getPeriodoActual() {
		return periodoActual;
	}

	public void setPeriodoActual(Periodo periodoActual) {
		this.periodoActual = periodoActual;
	}

	public void setPeriodoService(PeriodoService periodoService) {
		this.periodoService = periodoService;
	}

	public List<TipoServicio> getListaTipoServicio() {
		return listaTipoServicio;
	}

	public void setListaTipoServicio(List<TipoServicio> listaTipoServicio) {
		this.listaTipoServicio = listaTipoServicio;
	}

	public void setTipoServicioService(TipoServicioService tipoServicioService) {
		this.tipoServicioService = tipoServicioService;
	}

	public TipoServicio getTipoServicio() {
		return tipoServicio;
	}

	public void setTipoServicio(TipoServicio tipoServicio) {
		this.tipoServicio = tipoServicio;
	}

	public List<Puesto> getListaPuestoAgua() {
		return listaPuestoAgua;
	}

	public void setListaPuestoAgua(List<Puesto> listaPuestoAgua) {
		this.listaPuestoAgua = listaPuestoAgua;
	}

	public List<Puesto> getListaPuestoLuz() {
		return listaPuestoLuz;
	}

	public void setListaPuestoLuz(List<Puesto> listaPuestoLuz) {
		this.listaPuestoLuz = listaPuestoLuz;
	}

	public void setPuestoService(PuestoService puestoService) {
		this.puestoService = puestoService;
	}

	public TipoServicio getTipoServicioActual() {
		return tipoServicioActual;
	}

	public void setTipoServicioActual(TipoServicio tipoServicioActual) {
		this.tipoServicioActual = tipoServicioActual;
	}

	public Integer getCantidadAgua() {
		return cantidadAgua;
	}

	public void setCantidadAgua(Integer cantidadAgua) {
		this.cantidadAgua = cantidadAgua;
	}

	public Integer getCantidadLuz() {
		return cantidadLuz;
	}

	public void setCantidadLuz(Integer cantidadLuz) {
		this.cantidadLuz = cantidadLuz;
	}

	public String getClaseAgua() {
		return claseAgua;
	}

	public void setClaseAgua(String claseAgua) {
		this.claseAgua = claseAgua;
	}

	public String getClaseLuz() {
		return claseLuz;
	}

	public void setClaseLuz(String claseLuz) {
		this.claseLuz = claseLuz;
	}

	public String getMensajeAgua() {
		return mensajeAgua;
	}

	public void setMensajeAgua(String mensajeAgua) {
		this.mensajeAgua = mensajeAgua;
	}

	public String getMensajeLuz() {
		return mensajeLuz;
	}

	public void setMensajeLuz(String mensajeLuz) {
		this.mensajeLuz = mensajeLuz;
	}

	public List<Operacion> getListaEntidadLuz() {
		return listaEntidadLuz;
	}

	public void setListaEntidadLuz(List<Operacion> listaEntidadLuz) {
		this.listaEntidadLuz = listaEntidadLuz;
	}

	public List<Operacion> getListaEntidadAgua() {
		return listaEntidadAgua;
	}

	public void setListaEntidadAgua(List<Operacion> listaEntidadAgua) {
		this.listaEntidadAgua = listaEntidadAgua;
	}

	public List<Puesto> getListaPuestoFiltro() {
		return listaPuestoFiltro;
	}

	public void setListaPuestoFiltro(List<Puesto> listaPuestoFiltro) {
		this.listaPuestoFiltro = listaPuestoFiltro;
	}

	public boolean isBooBtnEliminar() {
		return booBtnEliminar;
	}

	public void setBooBtnEliminar(boolean booBtnEliminar) {
		this.booBtnEliminar = booBtnEliminar;
	}

	public String getLecturaAnterior() {
		return lecturaAnterior;
	}

	public void setLecturaAnterior(String lecturaAnterior) {
		this.lecturaAnterior = lecturaAnterior;
	}

	public Periodo getPeriodoAnterior() {
		return periodoAnterior;
	}

	public void setPeriodoAnterior(Periodo periodoAnterior) {
		this.periodoAnterior = periodoAnterior;
	}

	public void setMedidaServicioService(MedidaServicioService medidaServicioService) {
		this.medidaServicioService = medidaServicioService;
	}

	public Variable getVariableCostoConsumoLuz() {
		return variableCostoConsumoLuz;
	}

	public void setVariableCostoConsumoLuz(Variable variableCostoConsumoLuz) {
		this.variableCostoConsumoLuz = variableCostoConsumoLuz;
	}

	public Variable getVariableCostoAlumbradoLuz() {
		return variableCostoAlumbradoLuz;
	}

	public void setVariableCostoAlumbradoLuz(Variable variableCostoAlumbradoLuz) {
		this.variableCostoAlumbradoLuz = variableCostoAlumbradoLuz;
	}

	public Variable getVariableCostoConsumoAgua() {
		return variableCostoConsumoAgua;
	}

	public void setVariableCostoConsumoAgua(Variable variableCostoConsumoAgua) {
		this.variableCostoConsumoAgua = variableCostoConsumoAgua;
	}

	public void setVariableService(VariableService variableService) {
		this.variableService = variableService;
	}

	public List<LecturasMedidasPreOperacion> getListaPreOperacion() {
		return listaPreOperacion;
	}

	public void setListaPreOperacion(List<LecturasMedidasPreOperacion> listaPreOperacion) {
		this.listaPreOperacion = listaPreOperacion;
	}

	public boolean isBooEntidadRegistro() {
		return booEntidadRegistro;
	}

	public void setBooEntidadRegistro(boolean booEntidadRegistro) {
		this.booEntidadRegistro = booEntidadRegistro;
	}

	public boolean isBooTipoServicio() {
		return booTipoServicio;
	}

	public void setBooTipoServicio(boolean booTipoServicio) {
		this.booTipoServicio = booTipoServicio;
	}

	public UsuarioSesionBean getUsuarioSesionBean() {
		return usuarioSesionBean;
	}

	public void setUsuarioSesionBean(UsuarioSesionBean usuarioSesionBean) {
		this.usuarioSesionBean = usuarioSesionBean;
	}

	public String guardar() {
		System.out.println("Periodo: " + periodoActual.toString());
		System.out.println("Tipo Servicio: " + tipoServicioActual.toString());
		System.out.println("Entidad: " + entidad.toString());
		entidad.setEstado(1);

		if (entidad.getId() == null) {
			System.out.println("A guardar");
			try {

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

				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro actualizado", ""));
			} catch (Exception e) {
				// TODO: handle exception
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al actualizar registro", ""));
			}
		}
		limpiarEntidad();
		listarEntidad();

		if (tipoServicioActual.getId() == 1) {
			filtrarPuestosLuz();
		} else if (tipoServicioActual.getId() == 2) {
			filtrarPuestosAgua();
		} else {
			listaPuestoFiltro = new ArrayList<>();
		}

		return "";
	}

	public String eliminar() {

		if (entidadseleccionada.getId() == null) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Seleccione registro a eliminar", ""));
			limpiarEntidad();
			listarEntidad();
			return "";
		}

		try {
			/*
			 * servicio.eliminar(entidadseleccionada.getId());
			 */

			if (entidadseleccionada.getEstatusOperacion().getId() == 1
					|| entidadseleccionada.getEstatusOperacion().getId() == 3) {
				Operacion operacionServicioLocal = new Operacion();
				operacionServicioLocal = servicio.recuperar(entidadseleccionada.getId());
				operacionServicioLocal.setEstado(0);
				operacionServicioLocal.setIdUsuario(usuarioSesionBean.getUsuario().getId());
				servicio.actualizar(operacionServicioLocal);

				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro eliminado", ""));

			} else {

				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
						"Solo puede eliminar movimientos con estatus pendiente o vencida", ""));
				return "";

			}

		} catch (Exception e) {
			// TODO: handle exception
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al eliminar registro", ""));
		}

		limpiarEntidad();
		listarEntidad();
		tipoServicio = new TipoServicio();
		tipoServicioActual = new TipoServicio();

		booBtnEliminar = false;
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
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error. Problemas al recuperar registro", ""));
		}
	}

	public void listarEntidad() {
		lista = new ArrayList<>();
		try {
			lista = servicio.listarPorPeriodoactualCategoriaLuzAgua();
		} catch (Exception e) {
			// TODO: handle exception
			lista = null;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error. Problemas al recuperar registros", ""));
		}

		listafiltro = lista;

		obtenerCantidadLuz();
		obtenerCantidadAgua();
		obtenerBoolean();
	}

	public void obtenerCantidadLuz() {
		Integer idTipoServicioLuz = 1;
		Integer estado = 1;

		listaEntidadLuz = new ArrayList<>();
		listaEntidadLuz = lista.stream().filter(
				x -> idTipoServicioLuz.equals(x.getCategoriaOperacion().getId()) && estado.equals(x.getEstado()))
				.collect(Collectors.toList());
		cantidadLuz = listaEntidadLuz.size();
		System.out.println("Cantidad Luz registros: " + cantidadLuz);

		if (cantidadLuz == listaPuestoLuz.size()) {
			System.out.println("Registro de lectura de consumo de Distribucion electrica");
			claseLuz = "GreenBack";
			mensajeLuz = "Todos los Puestos tienen registrado su operacion de Distribucion electrica";
		} else {
			System.out.println("Registro incompleta lectura de consumo de Distribucion electrica");
			int diferencia = listaPuestoLuz.size() - cantidadLuz;
			claseLuz = "RedBack";
			mensajeLuz = "(" + diferencia
					+ ") Puestos no tienen registrado su operacion de Distribucion electrica";
		}
	}

	public void obtenerCantidadAgua() {
		Integer idTipoServicioAgua = 2;
		Integer estado = 1;

		listaEntidadAgua = new ArrayList<>();
		listaEntidadAgua = lista.stream().filter(
				x -> idTipoServicioAgua.equals(x.getCategoriaOperacion().getId()) && estado.equals(x.getEstado()))
				.collect(Collectors.toList());
		cantidadAgua = listaEntidadAgua.size();

		if (cantidadAgua == listaPuestoAgua.size()) {
			System.out.println("Registro de lectura de consumo de agua completa");
			claseAgua = "GreenBack";
			mensajeAgua = "Todos los Puestos tienen registrado su operacion de Distribucion de Agua Potable";
		} else {
			System.out.println("Registro incompleta lectura de consumo de agua completa");
			int diferencia = listaPuestoAgua.size() - cantidadAgua;
			claseAgua = "RedBack";
			mensajeAgua = "(" + diferencia + ") Puestos no tienen registrado su operacion de Distribucion de Agua Potable";
		}
	}

	public void obtenerBoolean() {

		if (claseLuz.equalsIgnoreCase("GreenBack") && claseAgua.equalsIgnoreCase("GreenBack")) {
			booEntidadRegistro = false;
			booTipoServicio = false;
		} else {
			booEntidadRegistro = false;
			booTipoServicio = true;

		}

	}

	public List<MedidaServicio> filtrarMedidas(List<MedidaServicio> listaMedida,
			List<Operacion> listaOperacionesLocal) {

		List<MedidaServicio> listaLocalMedida = new ArrayList<>();

		for (int i = 0; i <= listaMedida.size() - 1; i++) {

			boolean consideracion = false;

			for (int j = 0; j <= listaOperacionesLocal.size() - 1; j++) {
				if (listaMedida.get(i).getPuesto().getId() == listaOperacionesLocal.get(j).getPuesto().getId()) {
					System.out.println("El valor MEDIDA / puesto: " + listaMedida.get(i).getPuesto().getId()
							+ " SI Coincide con OPERACION / puesto: "
							+ listaOperacionesLocal.get(j).getPuesto().getId());
					consideracion = false;
					break;
				} else {
					System.out.println("El valor MEDIDA / puesto: " + listaMedida.get(i).getPuesto().getId()
							+ " NO Coincide con OPERACION / puesto: "
							+ listaOperacionesLocal.get(j).getPuesto().getId());
					consideracion = true;
				}
			}

			if (consideracion) {
				System.out.println("SI Agregaremos la medida: " + listaMedida.get(i).toString());
				listaLocalMedida.add(listaMedida.get(i));
			} else {
				System.out.println("NO Agregaremos la medida: " + listaMedida.get(i).toString());
			}
		}

		return listaLocalMedida;

	}

	public void filtrarPuestosLuz() {

		System.out.println("Filtro puestos por lista de LUZ");

		listaPuestoFiltro = new ArrayList<>();

		for (int i = 0; i <= listaPuestoLuz.size() - 1; i++) {

			boolean consideracion = false;

			for (int j = 0; j <= listaEntidadLuz.size() - 1; j++) {
				if (listaPuestoLuz.get(i).getId() == listaEntidadLuz.get(j).getPuesto().getId()) {
					System.out.println("El valor " + listaPuestoLuz.get(i).getId() + " SI Coincide con: "
							+ listaEntidadLuz.get(j).getPuesto().getId());
					consideracion = false;
					break;
				} else {
					System.out.println("El valor " + listaPuestoLuz.get(i).getId() + " NO Coincide con: "
							+ listaEntidadLuz.get(j).getPuesto().getId());
					consideracion = true;
				}
			}

			if (consideracion) {
				System.out.println("SI Agregaremos el puesto: " + listaPuestoLuz.get(i).toString());
				listaPuestoFiltro.add(listaPuestoLuz.get(i));
			} else {
				System.out.println("NO Agregaremos el puesto: " + listaPuestoLuz.get(i).toString());
			}
		}

	}

	public List<Operacion> filtrarOperacion() {

		List<Operacion> listaOperacionActual = new ArrayList<>();

		if (lista == null) {
			System.out.println("Lista Operacion filtrada es NULL ya que Lista es NULL");
		} else {

			for (int i = 0; i <= lista.size() - 1; i++) {

				if (lista.get(i).getCategoriaOperacion().getId() == tipoServicio.getId()) {
					listaOperacionActual.add(lista.get(i));
				}
			}

			System.out.println("Lista Operacion Filtrada: " + listaOperacionActual.toString());
		}

		return listaOperacionActual;

	}

	public void filtrarPuestosAgua() {

		System.out.println("Filtro puestos por lista de AGUA");

		listaPuestoFiltro = new ArrayList<>();

		for (int i = 0; i <= listaPuestoAgua.size() - 1; i++) {

			boolean consideracion = false;

			for (int j = 0; j <= listaEntidadAgua.size() - 1; j++) {
				if (listaPuestoAgua.get(i).getId() == listaEntidadAgua.get(j).getPuesto().getId()) {
					System.out.println("El valor " + listaPuestoAgua.get(i).getId() + " SI Coincide con: "
							+ listaEntidadAgua.get(j).getPuesto().getId());
					consideracion = false;
					break;
				} else {
					System.out.println("El valor " + listaPuestoAgua.get(i).getId() + " NO Coincide con: "
							+ listaEntidadAgua.get(j).getPuesto().getId());
					consideracion = true;
				}
			}

			if (consideracion) {
				System.out.println("SI Agregaremos el puesto: " + listaPuestoAgua.get(i).toString());
				listaPuestoFiltro.add(listaPuestoAgua.get(i));
			} else {
				System.out.println("NO Agregaremos el puesto: " + listaPuestoAgua.get(i).toString());
			}
		}

	}

	public void listarTipoServicio() {
		listaTipoServicio = new ArrayList<>();
		try {
			listaTipoServicio = tipoServicioService.listarActivo();
		} catch (Exception e) {
			// TODO: handle exception
			listaTipoServicio = null;
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error. Problemas al recuperar registros tipo servicio",
					""));
		}
	}

	public void listarPuestos() {
		listaPuestoAgua = new ArrayList<>();
		listaPuestoLuz = new ArrayList<>();
		try {
			listaPuestoAgua = puestoService.listarActivoAgua();
			listaPuestoLuz = puestoService.listarActivoLuz();
		} catch (Exception e) {
			// TODO: handle exception
			listaPuestoAgua = null;
			listaPuestoLuz = null;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error. Problemas al recuperar registros puesto", ""));
		}
	}

	public void limpiarEntidad() {
		entidad = new Operacion();
		entidadseleccionada = new Operacion();
		listafiltro = lista;
		lecturaAnterior = "No registra";
		listaPreOperacion = new ArrayList<>();
	}

	public void cancelarEliminacionEntidad() {
		entidad = new Operacion();
		entidadseleccionada = new Operacion();
		booBtnEliminar = false;
	}

	public void obtenerPeriodoActual() {

		List<Periodo> listaperiodos = null;

		periodoActual = new Periodo();
		periodoAnterior = new Periodo();

		try {
			listaperiodos = periodoService.listar();
		} catch (Exception e) {
			// TODO: handle exception
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al recuperar periodo fiscal en curso ", ""));
			return;
		}

		if (listaperiodos == null || listaperiodos.isEmpty()) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "No existe periodo fiscal activo en curso ", ""));
			return;
		} else {

			if (listaperiodos.size() > 1) {
				periodoActual = listaperiodos.get(0);
				periodoAnterior = listaperiodos.get(1);
			} else {
				periodoActual = listaperiodos.get(0);
				periodoAnterior = null;
			}
		}
	}

	public void obtenerPeriodoActual2() {

		List<Periodo> listaperiodos = null;

		periodoActual = new Periodo();
		periodoAnterior = new Periodo();

		try {
			listaperiodos = periodoService.listar();
		} catch (Exception e) {
			// TODO: handle exception
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al recuperar periodo fiscal en curso ", ""));
			return;
		}

		if (listaperiodos == null || listaperiodos.isEmpty()) {
			periodoActual = null;
			periodoAnterior = null;
			return;
		} else {

			if (listaperiodos.size() > 1) {
				periodoActual = listaperiodos.get(0);
				periodoAnterior = listaperiodos.get(1);
			} else {
				periodoActual = listaperiodos.get(0);
				periodoAnterior = null;
			}
		}
	}

	public void obtenerLecturaAnterior() {
		lecturaAnterior = null;

		if (periodoAnterior == null) {
			lecturaAnterior = "No registra";
		} else {
			MedidaServicio medidaAnteriorrr = new MedidaServicio();
			medidaAnteriorrr = medidaServicioService.recuperarPuestoidPeriodoidTiposervicioid(periodoAnterior.getId(),
					entidad.getPuesto().getId(), entidad.getCategoriaOperacion().getId());
			lecturaAnterior = medidaAnteriorrr.getMedida() + "";
		}
	}

	public String obtenerInputPeriodo() {

		listaPreOperacion = new ArrayList<>();

		// Estatus Operacion 1 = Pendiente;
		Integer idEstatusOperacion = 1;
		// Estatus Operacion 1 = Pendiente;
		Integer idProveedorLuz = 1;
		Integer idProveedorAgua = 2;
		// Tipo Operacion 1 = Ingreso
		Integer idTipoOperacion = 1;

		List<Operacion> listaOperacionesLocal = new ArrayList<>();
		listaOperacionesLocal = filtrarOperacion();

		List<MedidaServicio> listaMedidaActual = new ArrayList<MedidaServicio>();

		try {
			System.out.println("Periodo id: " + periodoActual.getId() + " / Tipo Servicio id: " + tipoServicio.getId());
			listaMedidaActual = medidaServicioService.listarPorPeriodoIdTipoServicio(periodoActual.getId(),
					tipoServicio.getId());
			System.out.println("listaMedidaActual: " + listaMedidaActual.toString());

		} catch (Exception e) {
			// TODO: handle exception
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error en obtener lectura de servicios.", ""));
			return "";
		}

		List<MedidaServicio> listaMedidaFiltrada = new ArrayList<>();

		if (listaOperacionesLocal == null || listaOperacionesLocal.isEmpty()) {
			listaMedidaFiltrada = listaMedidaActual;
		} else {
			listaMedidaFiltrada = filtrarMedidas(listaMedidaActual, listaOperacionesLocal);
		}

		for (int i = 0; i <= listaMedidaFiltrada.size() - 1; i++) {

			LecturasMedidasPreOperacion pre = new LecturasMedidasPreOperacion();
			pre.setPeriodoId(periodoActual.getId());
			pre.setPeriodoDescripcion(periodoActual.getDescripcion());

			pre.setEstatusOperacion(idEstatusOperacion);
			pre.setTipoOperacion(idTipoOperacion);

			pre.setIdMedidaServicio(listaMedidaFiltrada.get(i).getId());
			pre.setCategoriaId(listaMedidaFiltrada.get(i).getTipoServicio().getId());
			pre.setCategoriaDescripcion(listaMedidaFiltrada.get(i).getTipoServicio().getDescripcion());

			pre.setPuestoId(listaMedidaFiltrada.get(i).getPuesto().getId());
			pre.setPuestoDescripcion(listaMedidaFiltrada.get(i).getPuesto().getDescripcion());

			pre.setLecturaActual(listaMedidaFiltrada.get(i).getMedida());
			pre.setLecturaAnterior(listaMedidaFiltrada.get(i).getMedidaAnterior());
			pre.setConsumo(listaMedidaFiltrada.get(i).getConsumo());

			if (tipoServicio.getId() == 1) {

				Double costoUnitario = variableCostoConsumoLuz.getMonto();
				Double costoSubtotal = conversiones
						.formatoMontos_a(costoUnitario * listaMedidaFiltrada.get(i).getConsumo());
				Double costoFijo = variableCostoAlumbradoLuz.getMonto();
				Double costoTotal = conversiones.formatoMontos_a(costoSubtotal + costoFijo);

				pre.setProveedorAcreedor(idProveedorLuz);

				pre.setCostoUnitarioConsumo(costoUnitario);
				pre.setCostoSubtotalConsumo(costoSubtotal);
				pre.setCostoFijo(costoFijo);
				pre.setCostoTotal(costoTotal);

			} else {
				Double costoUnitario = variableCostoConsumoAgua.getMonto();
				Double costoSubtotal = conversiones
						.formatoMontos_a(costoUnitario * listaMedidaFiltrada.get(i).getConsumo());
				Double costoFijo = 0.00;
				Double costoTotal = conversiones.formatoMontos_a(costoSubtotal + costoFijo);

				pre.setProveedorAcreedor(idProveedorAgua);

				pre.setCostoUnitarioConsumo(costoUnitario);
				pre.setCostoSubtotalConsumo(costoSubtotal);
				pre.setCostoFijo(costoFijo);
				pre.setCostoTotal(costoTotal);
			}

			listaPreOperacion.add(pre);
		}

		if (listaPreOperacion == null || listaPreOperacion.isEmpty()) {
			booEntidadRegistro = false;
		} else {
			booEntidadRegistro = true;
		}

		return "";

	}

	public String generarOperaciones() {

		if (entidad.getDescripcion() != null || entidad.getDescripcion() != "") {
			String descripcion_min = entidad.getDescripcion();
			entidad.setDescripcion(descripcion_min.toUpperCase());
		} else {
			entidad.setDescripcion(null);
		}

		try {
			servicio.generarOperacionConsumoServicios(listaPreOperacion, entidad.getDescripcion(),
					entidad.getFechaVencimiento(), usuarioSesionBean.getUsuario().getId());
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Creacion exitosa de programacion de deuda de servicio", ""));
		} catch (Exception e) {
			// TODO: handle exception
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error en creacion de programacion de deuda de servicio", ""));
			return "";
		}

		limpiarEntidad();
		listarEntidad();

		tipoServicio = new TipoServicio();
		tipoServicioActual = new TipoServicio();

		return "";
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
			if (lista.get(i).getPuesto().getDescripcion().contains(filtro)
					|| lista.get(i).getCategoriaOperacion().getDescripcion().contains(filtro)) {
				System.out.println("lista: " + lista.get(i).toString());
				listafiltro.add(lista.get(i));
			}
		}
		filtro = null;
		return "";
	}

	public void obtenerVariables() {

		List<Variable> listaVariables = new ArrayList<Variable>();

		listaVariables = variableService.listarActivo();

		variableCostoConsumoLuz = listaVariables.get(0);
		variableCostoAlumbradoLuz = listaVariables.get(1);
		variableCostoConsumoAgua = listaVariables.get(2);

	}

	public void onRowSelect(SelectEvent event) {

		System.out.println("Entidad Seleccionada: " + entidadseleccionada.toString());

		if (entidadseleccionada == null) {
			entidadseleccionada = new Operacion();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "No se selecciono ningun registro", ""));
		} else {

			booEntidadRegistro = false;
			booBtnEliminar = true;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Seleccion de registro exitosa", ""));
		}
	}

	public void onRowUnselect(UnselectEvent event) {
		entidadseleccionada = new Operacion();
		booBtnEliminar = false;

		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Se anulo seleccion de registro ", ""));
	}

	public void cargaInicial() {
		obtenerPeriodoActual2();

		if (periodoActual == null) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "No existe periodo fiscal activo en curso ", ""));
			claseLuz = "RedBack";
			mensajeLuz = "Registre un periodo fiscal para calcular situacion de programacion de deuda y lectura del servicio de Distribucion electrica";
			claseAgua = "RedBack";
			mensajeAgua = "Registre un periodo fiscal para calcular situacion de programacion de deuda y lectura del servicio de Agua y Alcantarillado";

			booTipoServicio = false;
			booEntidadRegistro = false;
			return;
		} else {
			listarPuestos();
			listarEntidad();
			listarTipoServicio();
			obtenerVariables();
		}

	}

}
