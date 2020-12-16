package pe.com.gesadmin.managed;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import pe.com.gesadmin.entity.Puesto;
import pe.com.gesadmin.entity.CategoriaOperacion;
import pe.com.gesadmin.entity.transfer.ReporteTres;
import pe.com.gesadmin.service.PuestoService;
import pe.com.gesadmin.service.CategoriaOperacionService;
import pe.com.gesadmin.service.ReporteService;
import pe.com.gesadmin.service.impl.PuestoServiceImpl;
import pe.com.gesadmin.service.impl.CategoriaOperacionServiceImpl;
import pe.com.gesadmin.service.impl.ReporteServiceImpl;

@ManagedBean
@ViewScoped
public class ReporteTresBean {

	private List<ReporteTres> lista = new ArrayList<>();
	private List<ReporteTres> listafiltro;
	private ReporteTres entidad = new ReporteTres();
	private Integer idPuesto;
	private Integer idCategoriaOperacion;

	private String filtro;

	private List<Puesto> listaPuesto = new ArrayList<>();
	private List<CategoriaOperacion> listaCategoriaOperacion = new ArrayList<>();

	@EJB
	private ReporteService servicio = new ReporteServiceImpl();
	@EJB
	private PuestoService puestoService = new PuestoServiceImpl();
	@EJB
	private CategoriaOperacionService categoriaOperacionService = new CategoriaOperacionServiceImpl();

	public ReporteTresBean() {
		// TODO Auto-generated constructor stub
		filtro = null;
		entidad = new ReporteTres();
		idPuesto = null;
		idCategoriaOperacion = null;
	}

	@PostConstruct
	public void init() {
		listarPuestoes();
		listarCategoriaOperaciones();
	}

	public List<ReporteTres> getLista() {
		return lista;
	}

	public void setLista(List<ReporteTres> lista) {
		this.lista = lista;
	}

	public List<ReporteTres> getListafiltro() {
		return listafiltro;
	}

	public void setListafiltro(List<ReporteTres> listafiltro) {
		this.listafiltro = listafiltro;
	}

	public ReporteTres getEntidad() {
		return entidad;
	}

	public void setEntidad(ReporteTres entidad) {
		this.entidad = entidad;
	}

	public void setServicio(ReporteService servicio) {
		this.servicio = servicio;
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

	public Integer getIdPuesto() {
		return idPuesto;
	}

	public void setIdPuesto(Integer idPuesto) {
		this.idPuesto = idPuesto;
	}

	public Integer getIdCategoriaOperacion() {
		return idCategoriaOperacion;
	}

	public void setIdCategoriaOperacion(Integer idCategoriaOperacion) {
		this.idCategoriaOperacion = idCategoriaOperacion;
	}

	public List<Puesto> getListaPuesto() {
		return listaPuesto;
	}

	public void setListaPuesto(List<Puesto> listaPuesto) {
		this.listaPuesto = listaPuesto;
	}

	public List<CategoriaOperacion> getListaCategoriaOperacion() {
		return listaCategoriaOperacion;
	}

	public void setListaCategoriaOperacion(List<CategoriaOperacion> listaCategoriaOperacion) {
		this.listaCategoriaOperacion = listaCategoriaOperacion;
	}

	public void setPuestoService(PuestoService puestoService) {
		this.puestoService = puestoService;
	}

	public void setCategoriaOperacionService(CategoriaOperacionService categoriaOperacionService) {
		this.categoriaOperacionService = categoriaOperacionService;
	}

	public void listarPuestoes() {
		listaPuesto = new ArrayList<>();

		try {
			listaPuesto = puestoService.listarFiltro(true);
		} catch (Exception e) {
			// TODO: handle exception
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error.",
					"Problemas al recuperar lista de años fiscales"));
		}
	}

	public void listarCategoriaOperaciones() {

		listaCategoriaOperacion = new ArrayList<>();
		try {
			listaCategoriaOperacion = categoriaOperacionService.listarPordTipoOperacion(1);
		} catch (Exception e) {
			// TODO: handle exception
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error.",
					"Problemas al recuperar lista de categorias de operaciones"));
		}
	}

	public void limpiar() {
		entidad = new ReporteTres();

		lista = null;
		listafiltro = null;
		idPuesto = null;
		idCategoriaOperacion = null;
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
			if (lista.get(i).getDescripcionCategoriaOperacion().contains(filtro) || lista.get(i).getDescripcionAnioFiscal().contains(filtro)) {
				System.out.println("lista: " + lista.get(i).toString());
				listafiltro.add(lista.get(i));
			}
		}
		filtro = null;
		return "";
	}

	public String consultar() {

		lista = new ArrayList<>();

		if (idCategoriaOperacion == null) {

			try {
				lista = servicio.obtenerReporteTresCategoriaAll(idPuesto);
			} catch (Exception e) {
				// TODO: handle exception
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error.", "Problemas al recuperar registros"));
				limpiar();
				return "";
			}

		} else {
			try {
				lista = servicio.obtenerReporteTresCategoriaOperacionId(idPuesto, idCategoriaOperacion);
			} catch (Exception e) {
				// TODO: handle exception
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error.", "Problemas al recuperar registros"));
				limpiar();
				return "";
			}
		}

		listafiltro = new ArrayList<>();
		listafiltro = lista;

		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito.", "Registros encontrados"));

		return "";

	}

}
