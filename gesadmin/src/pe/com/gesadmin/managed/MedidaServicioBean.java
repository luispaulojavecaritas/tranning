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
import pe.com.gesadmin.service.MedidaServicioService;
import pe.com.gesadmin.service.OperacionService;
import pe.com.gesadmin.service.PeriodoService;
import pe.com.gesadmin.service.PuestoService;
import pe.com.gesadmin.service.TipoServicioService;
import pe.com.gesadmin.service.impl.MedidaServicioServiceImpl;
import pe.com.gesadmin.service.impl.OperacionServiceImpl;
import pe.com.gesadmin.service.impl.PeriodoServiceImpl;
import pe.com.gesadmin.service.impl.PuestoServiceImpl;
import pe.com.gesadmin.service.impl.TipoServicioServiceImpl;
import pe.com.gesadmin.util.Conversiones;

@ManagedBean
@ViewScoped
public class MedidaServicioBean {

	@ManagedProperty("#{usuarioSesionBean}")
	private UsuarioSesionBean usuarioSesionBean;

	private List<MedidaServicio> lista = new ArrayList<>();
	private List<MedidaServicio> listafiltro;
	private MedidaServicio entidad = new MedidaServicio();
	private MedidaServicio entidadAnterior = new MedidaServicio();

	private MedidaServicio entidadseleccionada = new MedidaServicio();

	private Periodo periodoActual = new Periodo();
	private Periodo periodoAnterior = new Periodo();
	private TipoServicio tipoServicio = new TipoServicio();
	private TipoServicio tipoServicioActual = new TipoServicio();

	private List<TipoServicio> listaTipoServicio = new ArrayList<>();
	private List<Puesto> listaPuesto = new ArrayList<>();
	private List<Puesto> listaPuestoFiltro = new ArrayList<>();

	private Integer cantidadAgua;
	private Integer cantidadLuz;
	private String claseAgua;
	private String claseLuz;
	private String mensajeAgua;
	private String mensajeLuz;

	private List<MedidaServicio> listaEntidadLuz = new ArrayList<>();
	private List<MedidaServicio> listaEntidadAgua = new ArrayList<>();

	private boolean booBtnEliminar = false;
	private boolean booLecturaAnterior = false;

	private boolean booRegistrosPre = false;
	private boolean booRegistro = false;

	private String lecturaAnterior = "No registra";

	private String filtro;

	private Conversiones conversiones = new Conversiones();

	@EJB
	private MedidaServicioService servicio = new MedidaServicioServiceImpl();
	@EJB
	private PeriodoService periodoService = new PeriodoServiceImpl();
	@EJB
	private TipoServicioService tipoServicioService = new TipoServicioServiceImpl();
	@EJB
	private PuestoService puestoService = new PuestoServiceImpl();
	@EJB
	private OperacionService operacionService = new OperacionServiceImpl();

	public MedidaServicioBean() {
		// TODO Auto-generated constructor stub
		filtro = null;
		entidad = new MedidaServicio();
		entidadAnterior = new MedidaServicio();

		lista = new ArrayList<>();
		listaTipoServicio = new ArrayList<>();

		periodoActual = new Periodo();
		periodoAnterior = new Periodo();

		tipoServicio = new TipoServicio();
		tipoServicioActual = new TipoServicio();
		listaPuesto = new ArrayList<>();

		cantidadAgua = 0;
		cantidadLuz = 0;
		claseAgua = "RedBack";
		claseLuz = "RedBack";
		mensajeAgua = "";
		mensajeLuz = "";

		listaEntidadLuz = new ArrayList<>();
		listaEntidadAgua = new ArrayList<>();
		listaPuestoFiltro = new ArrayList<>();

		booBtnEliminar = false;
		booLecturaAnterior = false;
		booRegistrosPre = false;
		booRegistro = false;

		lecturaAnterior = "No registra";
	}

	@PostConstruct
	public void init() {
		cargaInicial();
	}

	public List<MedidaServicio> getLista() {
		return lista;
	}

	public void setLista(List<MedidaServicio> lista) {
		this.lista = lista;
	}

	public List<MedidaServicio> getListafiltro() {
		return listafiltro;
	}

	public void setListafiltro(List<MedidaServicio> listafiltro) {
		this.listafiltro = listafiltro;
	}

	public MedidaServicio getEntidad() {
		return entidad;
	}

	public void setEntidad(MedidaServicio entidad) {
		this.entidad = entidad;
	}

	public MedidaServicio getEntidadseleccionada() {
		return entidadseleccionada;
	}

	public void setEntidadseleccionada(MedidaServicio entidadseleccionada) {
		this.entidadseleccionada = entidadseleccionada;
	}

	public void setServicio(MedidaServicioService servicio) {
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

	public List<Puesto> getListaPuesto() {
		return listaPuesto;
	}

	public void setListaPuesto(List<Puesto> listaPuesto) {
		this.listaPuesto = listaPuesto;
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

	public List<MedidaServicio> getListaEntidadLuz() {
		return listaEntidadLuz;
	}

	public void setListaEntidadLuz(List<MedidaServicio> listaEntidadLuz) {
		this.listaEntidadLuz = listaEntidadLuz;
	}

	public List<MedidaServicio> getListaEntidadAgua() {
		return listaEntidadAgua;
	}

	public void setListaEntidadAgua(List<MedidaServicio> listaEntidadAgua) {
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

	public boolean isBooLecturaAnterior() {
		return booLecturaAnterior;
	}

	public void setBooLecturaAnterior(boolean booLecturaAnterior) {
		this.booLecturaAnterior = booLecturaAnterior;
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

	public MedidaServicio getEntidadAnterior() {
		return entidadAnterior;
	}

	public void setEntidadAnterior(MedidaServicio entidadAnterior) {
		this.entidadAnterior = entidadAnterior;
	}

	public void setOperacionService(OperacionService operacionService) {
		this.operacionService = operacionService;
	}

	public boolean isBooRegistrosPre() {
		return booRegistrosPre;
	}

	public void setBooRegistrosPre(boolean booRegistrosPre) {
		this.booRegistrosPre = booRegistrosPre;
	}

	public boolean isBooRegistro() {
		return booRegistro;
	}

	public void setBooRegistro(boolean booRegistro) {
		this.booRegistro = booRegistro;
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
		entidad.setIdUsuario(usuarioSesionBean.getUsuario().getId());
		entidad.setRegistro(new Date());
		entidad.setEstado(1);

		Double consumo = 0.00;
		/*
		 * if (booLecturaAnterior) {
		 * System.out.println("SI Validaremos lectura anterior vs actual"); if
		 * (entidad.getMedida() >= entidadAnterior.getMedida()) { System.out.println(
		 * "SI Paso validacion de comparacion de medida actual tiene que ser superior o igual a la medida anterior"
		 * ); consumo = obtener_consumo(); } else { System.out.println(
		 * "NO Paso validacion de comparacion de medida actual tiene que ser superior o igual a la medida anterior"
		 * ); FacesContext.getCurrentInstance().addMessage(null, new
		 * FacesMessage(FacesMessage.SEVERITY_WARN,
		 * "La lectura del presente periodo tiene que se mayor o igual a la lectura del anterior periodo"
		 * , "")); return ""; } } else {
		 * System.out.println("NO Validaremos lectura anterior vs actual"); consumo =
		 * entidad.getMedida(); entidadAnterior = new MedidaServicio();
		 * entidadAnterior.setMedida(0.00); }
		 */

		System.out.println("Validaremos lectura anterior vs actual");
		if (entidad.getMedida() >= entidadAnterior.getMedida()) {
			System.out.println(
					"SI Paso validacion de comparacion de medida actual tiene que ser superior o igual a la medida anterior");
			consumo = obtener_consumo();
		} else {
			System.out.println(
					"NO Paso validacion de comparacion de medida actual tiene que ser superior o igual a la medida anterior");
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
					"La lectura del presente periodo tiene que se mayor o igual a la lectura del anterior periodo",
					""));
			return "";
		}

		if (entidad.getId() == null) {
			System.out.println("A guardar");
			try {

				MedidaServicio entidadLocal = new MedidaServicio();
				entidadLocal.setPuesto(new Puesto(entidad.getPuesto().getId()));
				entidadLocal.setMedida(entidad.getMedida());
				entidadLocal.setMedidaAnterior(entidadAnterior.getMedida());
				entidadLocal.setEstado(entidad.getEstado());
				entidadLocal.setTipoServicio(new TipoServicio(tipoServicioActual.getId()));
				entidadLocal.setPeriodo(new Periodo(periodoActual.getId()));
				entidadLocal.setIdUsuario(entidad.getIdUsuario());
				entidadLocal.setRegistro(new Date());
				entidadLocal.setConsumo(consumo);

				MedidaServicio medidaVerificador = servicio.recuperarPuestoidPeriodoidTiposervicioid(
						entidadLocal.getPeriodo().getId(), entidadLocal.getPuesto().getId(),
						entidadLocal.getTipoServicio().getId());

				if (medidaVerificador == null) {
					servicio.crear(entidadLocal);
					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro creado", ""));
				} else {
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Actualmente existe un registro creado en el presente periodo", ""));
				}

			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("Excepcion: " + e.getCause().toString());
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al crear registro", ""));
			}

		} else {
			System.out.println("A actualizar");
			try {

				MedidaServicio entidadLocal = new MedidaServicio();
				entidadLocal.setPuesto(new Puesto(entidad.getPuesto().getId()));
				entidadLocal.setMedida(entidad.getMedida());
				entidadLocal.setMedidaAnterior(entidadAnterior.getMedida());
				entidadLocal.setEstado(entidad.getEstado());
				entidadLocal.setTipoServicio(new TipoServicio(tipoServicioActual.getId()));
				entidadLocal.setPeriodo(new Periodo(periodoActual.getId()));
				entidadLocal.setIdUsuario(entidad.getIdUsuario());
				entidadLocal.setRegistro(new Date());
				entidadLocal.setConsumo(consumo);

				servicio.actualizar(entidad);
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
			listaPuestoFiltro = listaPuesto;
		}

		if (claseLuz.equalsIgnoreCase("GreenBack") && claseAgua.equalsIgnoreCase("GreenBack")) {
			booRegistrosPre = false;
			booRegistro = false;
		}

		return "";
	}

	public String eliminar() {

		if (entidadseleccionada.getId() == null) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Seleccione registro a eliminar", ""));
			limpiarEntidad();
			listarEntidad();
			limpiarTipoServicio();
			return "";
		}

		List<Operacion> listaOperacionAsociada = new ArrayList<>();
		listaOperacionAsociada = operacionService.listarPorPeriodoActivePuestoIdCategoriaIdOperacionActivo(
				entidadseleccionada.getPuesto().getId(), entidadseleccionada.getTipoServicio().getId());

		if (listaOperacionAsociada == null || listaOperacionAsociada.isEmpty()) {
			System.out.println("NO existe operaciones asociadas a esta medicion");
			MedidaServicio medidaServicio = new MedidaServicio();
			medidaServicio = servicio.recuperar(entidadseleccionada.getId());
			medidaServicio.setEstado(0);
			medidaServicio.setIdUsuario(usuarioSesionBean.getUsuario().getId());
			medidaServicio.setRegistro(new Date());
			servicio.actualizar(medidaServicio);

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro eliminado", ""));

		} else {

			Operacion operacionLocalMedida = new Operacion();
			operacionLocalMedida = listaOperacionAsociada.get(0);
			
			System.out.println("Operacion entidad = " + operacionLocalMedida.toString());

			if (operacionLocalMedida.getEstatusOperacion().getId() == 1
					|| operacionLocalMedida.getEstatusOperacion().getId() == 3) {
				// estado pendiente o vencida
				try {

					MedidaServicio medidaServicio = new MedidaServicio();
					medidaServicio = servicio.recuperar(entidadseleccionada.getId());
					medidaServicio.setEstado(0);
					medidaServicio.setIdUsuario(usuarioSesionBean.getUsuario().getId());
					medidaServicio.setRegistro(new Date());
					servicio.actualizar(medidaServicio);

					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro eliminado", ""));
				} catch (Exception e) {
					// TODO: handle exception
					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al eliminar registro", ""));
				}
				
				System.out.println("Evaluando eliminar operacion relacionada a medida de servicio (pendiente o vencida)");
				operacionService.actualizarPorPeriodoidPuestoidCategoriaid(entidadseleccionada.getPeriodo().getId(),
						entidadseleccionada.getPuesto().getId(), entidadseleccionada.getTipoServicio().getId(),
						usuarioSesionBean.getUsuario().getId());
				
			} else if (operacionLocalMedida.getEstatusOperacion().getId() == 4) {
				// Estado cancelada
				
				try {

					MedidaServicio medidaServicio = new MedidaServicio();
					medidaServicio = servicio.recuperar(entidadseleccionada.getId());
					medidaServicio.setEstado(0);
					medidaServicio.setIdUsuario(usuarioSesionBean.getUsuario().getId());
					medidaServicio.setRegistro(new Date());
					servicio.actualizar(medidaServicio);

					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro eliminado", ""));
				} catch (Exception e) {
					// TODO: handle exception
					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al eliminar registro", ""));
				}
				
				System.out.println("Evaluando eliminar operacion relacionada a medida de servicio (cancelada)");
			} else {
				// Estado efectuada
				System.out.println("Evaluando eliminar operacion relacionada a medida de servicio (efectuada)");				
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
						"Solo puede eliminar mediciones que tengan operaciones con estatus pendiente o vencida", ""));
				return "";
			}
		}

		booBtnEliminar = false;

		listarEntidad();
		limpiarEntidad();
		limpiarTipoServicio();

		return "";
	}

	public void recuperar() {

		entidad = new MedidaServicio();

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
			lista = servicio.listarPorPeriodoActivo();
		} catch (Exception e) {
			// TODO: handle exception
			lista = null;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al recuperar registros", ""));
		}

		listafiltro = lista;

		obtenerCantidadLuz();
		obtenerCantidadAgua();

		obtenerBooRegistro();
	}

	public void obtenerCantidadLuz() {
		Integer idTipoServicioLuz = 1;
		Integer estado = 1;

		listaEntidadLuz = new ArrayList<>();
		listaEntidadLuz = lista.stream()
				.filter(x -> idTipoServicioLuz.equals(x.getTipoServicio().getId()) && estado.equals(x.getEstado()))
				.collect(Collectors.toList());
		cantidadLuz = listaEntidadLuz.size();
		System.out.println("Cantidad Luz registros: " + cantidadLuz);

		if (cantidadLuz == listaPuesto.size()) {
			System.out.println("Registro de lectura de consumo de Distribucion electrica");
			claseLuz = "GreenBack";
			mensajeLuz = "Todos los Puestos tienen registrado su lectura del servicio de Distribucion electrica";
		} else {
			System.out.println("Registro incompleta lectura de consumo de Distribucion electrica");
			int diferencia = listaPuesto.size() - cantidadLuz;
			claseLuz = "RedBack";
			mensajeLuz = "(" + diferencia
					+ ") Puestos no tienen registrado su lectura del servicio de Distribucion electrica";
		}
	}

	public void obtenerCantidadAgua() {
		Integer idTipoServicioAgua = 2;
		Integer estado = 1;

		listaEntidadAgua = new ArrayList<>();
		listaEntidadAgua = lista.stream()
				.filter(x -> idTipoServicioAgua.equals(x.getTipoServicio().getId()) && estado.equals(x.getEstado()))
				.collect(Collectors.toList());
		cantidadAgua = listaEntidadAgua.size();

		if (cantidadAgua == listaPuesto.size()) {
			System.out.println("Registro de lectura de consumo de agua completa");
			claseAgua = "GreenBack";
			mensajeAgua = "Todos los Puestos tienen registrado su lectura del servicio de Agua Potable";
		} else {
			System.out.println("Registro incompleta lectura de consumo de agua completa");
			int diferencia = listaPuesto.size() - cantidadAgua;
			claseAgua = "RedBack";
			mensajeAgua = "(" + diferencia + ") Puestos no tienen registrado su lectura del servicio de Agua Potable";
		}
	}

	public void filtrarPuestosLuz() {

		System.out.println("Filtro puestos por lista de LUZ");

		listaPuestoFiltro = new ArrayList<>();

		for (int i = 0; i <= listaPuesto.size() - 1; i++) {

			boolean consideracion = false;

			for (int j = 0; j <= listaEntidadLuz.size() - 1; j++) {
				if (listaPuesto.get(i).getId() == listaEntidadLuz.get(j).getPuesto().getId()) {
					System.out.println("El valor " + listaPuesto.get(i).getId() + " SI Coincide con: "
							+ listaEntidadLuz.get(j).getPuesto().getId());
					consideracion = false;
					break;
				} else {
					System.out.println("El valor " + listaPuesto.get(i).getId() + " NO Coincide con: "
							+ listaEntidadLuz.get(j).getPuesto().getId());
					consideracion = true;
				}
			}

			if (consideracion) {
				System.out.println("SI Agregaremos el puesto: " + listaPuesto.get(i).toString());
				listaPuestoFiltro.add(listaPuesto.get(i));
			} else {
				System.out.println("NO Agregaremos el puesto: " + listaPuesto.get(i).toString());
			}
		}

	}

	public void filtrarPuestosAgua() {

		System.out.println("Filtro puestos por lista de AGUA");

		listaPuestoFiltro = new ArrayList<>();

		for (int i = 0; i <= listaPuesto.size() - 1; i++) {

			boolean consideracion = false;

			for (int j = 0; j <= listaEntidadAgua.size() - 1; j++) {
				if (listaPuesto.get(i).getId() == listaEntidadAgua.get(j).getPuesto().getId()) {
					System.out.println("El valor " + listaPuesto.get(i).getId() + " SI Coincide con: "
							+ listaEntidadAgua.get(j).getPuesto().getId());
					consideracion = false;
					break;
				} else {
					System.out.println("El valor " + listaPuesto.get(i).getId() + " NO Coincide con: "
							+ listaEntidadAgua.get(j).getPuesto().getId());
					consideracion = true;
				}
			}

			if (consideracion) {
				System.out.println("SI Agregaremos el puesto: " + listaPuesto.get(i).toString());
				listaPuestoFiltro.add(listaPuesto.get(i));
			} else {
				System.out.println("NO Agregaremos el puesto: " + listaPuesto.get(i).toString());
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
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al recuperar registros tipo servicio", ""));
		}
	}

	public void listarPuestos() {
		listaPuesto = new ArrayList<>();
		try {
			listaPuesto = puestoService.listarFiltro(true);
		} catch (Exception e) {
			// TODO: handle exception
			listaPuesto = null;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al recuperar registros puesto", ""));
		}
	}

	public void onRowSelect(SelectEvent event) {
		if (entidadseleccionada == null) {
			entidad = new MedidaServicio();
			entidadseleccionada = new MedidaServicio();
			booBtnEliminar = false;
			booRegistrosPre = false;
			booRegistro = true;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "No se selecciono ningun registro", ""));
		} else {
			entidad = new MedidaServicio();
			booBtnEliminar = true;
			booRegistrosPre = false;
			booRegistro = false;
			System.out.println("Entidad Seleccionada: " + entidadseleccionada.toString());
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Seleccion de registro exitosa", ""));
		}
	}

	public void onRowUnselect(UnselectEvent event) {
		entidad = new MedidaServicio();
		entidadseleccionada = new MedidaServicio();
		booBtnEliminar = false;
		booRegistrosPre = false;
		booRegistro = true;

		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Se anulo seleccion de registro ", ""));
	}

	public void limpiarEntidad() {
		entidad = new MedidaServicio();
		entidadAnterior = new MedidaServicio();
		entidadseleccionada = new MedidaServicio();
		listafiltro = lista;
		lecturaAnterior = "No registra";
	}

	public void limpiarTipoServicio() {
		limpiarEntidad();
		tipoServicio = new TipoServicio();
		tipoServicioActual = new TipoServicio();
		listaPuestoFiltro = new ArrayList<>();
		booRegistrosPre = false;
	}

	public void cancelarEliminacionEntidad() {
		entidad = new MedidaServicio();
		entidadseleccionada = new MedidaServicio();
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
				booLecturaAnterior = true;
			} else {
				periodoActual = listaperiodos.get(0);
				periodoAnterior = null;
				booLecturaAnterior = false;
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
				booLecturaAnterior = true;
			} else {
				periodoActual = listaperiodos.get(0);
				periodoAnterior = null;
				booLecturaAnterior = false;
			}
		}
	}

	public Double obtener_consumo() {

		System.out.println("Entidad anterior: " + entidadAnterior.toString());

		Double medidaActual = entidad.getMedida();
		Double medidaAnterior = null;

		medidaAnterior = entidadAnterior.getMedida();

		Double consumoActual = conversiones.formatoMontos_a(medidaActual - medidaAnterior);

		return consumoActual;

	}

	public void obtenerLecturaAnterior() {
		lecturaAnterior = null;
		entidadAnterior = new MedidaServicio();

		if (periodoAnterior == null) {
			lecturaAnterior = "No registra";
			booLecturaAnterior = true;
		} else {
			entidadAnterior = servicio.recuperarPuestoidPeriodoidTiposervicioid(periodoAnterior.getId(),
					entidad.getPuesto().getId(), tipoServicio.getId());
			lecturaAnterior = entidadAnterior.getMedida() + "";
			booLecturaAnterior = false;
		}
	}

	public String obtenerTipoServicioActual() {

		tipoServicioActual = new TipoServicio();
		tipoServicioActual = new TipoServicio(tipoServicio.getId());
		lecturaAnterior = "No registra";

		System.out.println("Servicio Actual obtenido: " + tipoServicioActual.toString());

		if (tipoServicioActual.getId() == 1) {
			if (listaEntidadLuz == null || listaEntidadLuz.isEmpty()) {
				listaPuestoFiltro = listaPuesto;
			} else {
				filtrarPuestosLuz();
			}
		} else if (tipoServicioActual.getId() == 2) {
			if (listaEntidadAgua == null || listaEntidadAgua.isEmpty()) {
				listaPuestoFiltro = listaPuesto;
			} else {
				filtrarPuestosAgua();
			}
		} else {
			listaPuestoFiltro = null;
		}

		if (listaPuestoFiltro == null || listaPuestoFiltro.isEmpty()) {
			booRegistrosPre = false;
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
					"No se encontro puestos pendientes de registrar lectura de servicios", ""));
		} else {
			booRegistrosPre = true;
		}

		return "";
	}

	public String obtenerTipoServicioActual2() {

		tipoServicioActual = new TipoServicio();
		tipoServicioActual = new TipoServicio(tipoServicio.getId());
		lecturaAnterior = "No registra";

		System.out.println("Servicio Actual obtenido: " + tipoServicioActual.toString());

		if (tipoServicioActual.getId() == 1) {
			if (listaEntidadLuz == null || listaEntidadLuz.isEmpty()) {
				listaPuestoFiltro = listaPuesto;
			} else {
				filtrarPuestosLuz();
			}
		} else if (tipoServicioActual.getId() == 2) {
			if (listaEntidadAgua == null || listaEntidadAgua.isEmpty()) {
				listaPuestoFiltro = listaPuesto;
			} else {
				filtrarPuestosAgua();
			}
		} else {
			listaPuestoFiltro = null;
		}

		if (listaPuestoFiltro == null || listaPuestoFiltro.isEmpty()) {
			booRegistrosPre = false;
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
					"No se encontro puestos pendientes de registrar lectura de servicios", ""));
		} else {
			booRegistrosPre = true;
		}

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
					|| lista.get(i).getTipoServicio().getDescripcion().contains(filtro)) {
				System.out.println("lista: " + lista.get(i).toString());
				listafiltro.add(lista.get(i));
			}
		}
		filtro = null;
		return "";
	}

	public void obtenerBooRegistro() {

		if (claseAgua.equalsIgnoreCase("GreenBack") && claseLuz.equalsIgnoreCase("GreenBack")) {
			booRegistro = false;
		} else {
			booRegistro = true;
		}
	}

	public void cargaInicial() {

		obtenerPeriodoActual2();

		if (periodoActual == null) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "No existe periodo fiscal activo en curso ", ""));
			claseLuz = "RedBack";
			mensajeLuz = "Registre un periodo fiscal para calcular situacion de lectura del servicio de Distribucion electrica";
			claseAgua = "RedBack";
			mensajeAgua = "Registre un periodo fiscal para calcular situacion de lectura del servicio de Agua y Alcantarillado";
			return;
		} else {
			listarPuestos();
			listarEntidad();
			listarTipoServicio();
			booLecturaAnterior = false;
		}

	}

}
