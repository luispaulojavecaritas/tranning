package pe.com.gesadmin.managed;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import pe.com.gesadmin.entity.AnioFiscal;
import pe.com.gesadmin.entity.Puesto;
import pe.com.gesadmin.entity.transfer.ReporteDos;
import pe.com.gesadmin.service.AnioFiscalService;
import pe.com.gesadmin.service.PuestoService;
import pe.com.gesadmin.service.ReporteService;
import pe.com.gesadmin.service.impl.AnioFiscalServiceImpl;
import pe.com.gesadmin.service.impl.PuestoServiceImpl;
import pe.com.gesadmin.service.impl.ReporteServiceImpl;

@ManagedBean
@ViewScoped
public class ReporteDosBean {

	private List<ReporteDos> lista = new ArrayList<>();
	private List<ReporteDos> listafiltro;
	private ReporteDos entidad = new ReporteDos();
	private Integer idPuesto;

	private String filtro;

	private List<AnioFiscal> listaAnioFiscal = new ArrayList<>();
	private List<Puesto> listaPuesto = new ArrayList<>();

	@EJB
	private ReporteService servicio = new ReporteServiceImpl();
	@EJB
	private AnioFiscalService anioFiscalService = new AnioFiscalServiceImpl();
	@EJB
	private PuestoService puestoService = new PuestoServiceImpl();

	public ReporteDosBean() {
		// TODO Auto-generated constructor stub
		filtro = null;
		entidad = new ReporteDos();
		idPuesto = null;
	}

	@PostConstruct
	public void init() {
		listarAnioFiscales();
		listarPuestoes();
	}

	public List<ReporteDos> getLista() {
		return lista;
	}

	public void setLista(List<ReporteDos> lista) {
		this.lista = lista;
	}

	public List<ReporteDos> getListafiltro() {
		return listafiltro;
	}

	public void setListafiltro(List<ReporteDos> listafiltro) {
		this.listafiltro = listafiltro;
	}

	public ReporteDos getEntidad() {
		return entidad;
	}

	public void setEntidad(ReporteDos entidad) {
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

	public List<AnioFiscal> getListaAnioFiscal() {
		return listaAnioFiscal;
	}

	public void setListaAnioFiscal(List<AnioFiscal> listaAnioFiscal) {
		this.listaAnioFiscal = listaAnioFiscal;
	}

	public List<Puesto> getListaPuesto() {
		return listaPuesto;
	}

	public void setListaPuesto(List<Puesto> listaPuesto) {
		this.listaPuesto = listaPuesto;
	}

	public void setAnioFiscalService(AnioFiscalService anioFiscalService) {
		this.anioFiscalService = anioFiscalService;
	}

	public void setPuestoService(PuestoService puestoService) {
		this.puestoService = puestoService;
	}

	public void listarAnioFiscales() {
		listaAnioFiscal = new ArrayList<>();

		try {
			listaAnioFiscal = anioFiscalService.listar();
		} catch (Exception e) {
			// TODO: handle exception
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error.",
					"Problemas al recuperar lista de años fiscales"));
		}
	}

	public void listarPuestoes() {

		listaPuesto = new ArrayList<>();
		try {
			listaPuesto = puestoService.listarFiltro(true);
		} catch (Exception e) {
			// TODO: handle exception
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error.",
					"Problemas al recuperar lista de categorias de operaciones"));
		}
	}

	public void limpiar() {
		entidad = new ReporteDos();

		lista = null;
		listafiltro = null;
		idPuesto = null;
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
			if (lista.get(i).getDescripcionPuesto().contains(filtro)
					|| lista.get(i).getDescripcionCategoriaOperacion().contains(filtro)) {
				System.out.println("lista: " + lista.get(i).toString());
				listafiltro.add(lista.get(i));
			}
		}
		filtro = null;
		return "";
	}

	public String consultar() {

		lista = new ArrayList<>();
		listafiltro = new ArrayList<>();

		try {
			lista = servicio.obtenerReporteDosPuestoId(idPuesto);
			listafiltro = lista;
		} catch (Exception e) {
			// TODO: handle exception
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error.", "Problemas al recuperar registros"));
			limpiar();

			return "";
		}

		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito.", "Registros encontrados"));

		return "";

	}

}
