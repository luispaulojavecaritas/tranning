package pe.com.gesadmin.managed;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import pe.com.gesadmin.entity.EstatusOperacion;
import pe.com.gesadmin.entity.Operacion;
import pe.com.gesadmin.entity.Periodo;
import pe.com.gesadmin.service.OperacionService;
import pe.com.gesadmin.service.PeriodoService;
import pe.com.gesadmin.service.impl.OperacionServiceImpl;
import pe.com.gesadmin.service.impl.PeriodoServiceImpl;
import pe.com.gesadmin.util.UtilFechas;

@ManagedBean
@ViewScoped
public class AnulacionComprobanteBean {

	@ManagedProperty("#{usuarioSesionBean}")
	private UsuarioSesionBean usuarioSesionBean;
	
	private String tipoDoc;
	private String nroDoc;
	
	private Integer idEstatusPendiente = 1;
	private Integer idEstatusAnulado = 4;
	private Date dateActual = new Date();
	
	private Periodo periodoActual = new Periodo();
	
	private List<Periodo> listaPeriodo = new ArrayList<>();



	@EJB
	private OperacionService servicio = new OperacionServiceImpl();

	@EJB
	private PeriodoService periodoService = new PeriodoServiceImpl();
	
	public AnulacionComprobanteBean() {
		// TODO Auto-generated constructor stub
		
		periodoActual= new Periodo();
		listaPeriodo = new ArrayList<>();
	
	}

	@PostConstruct
	public void init() {
		obteberPeriodoActual();
	}
	

	public void setPeriodoService(PeriodoService periodoService) {
		this.periodoService = periodoService;
	}

	public Integer getIdEstatusPendiente() {
		return idEstatusPendiente;
	}

	public void setIdEstatusPendiente(Integer idEstatusPendiente) {
		this.idEstatusPendiente = idEstatusPendiente;
	}

	public Integer getIdEstatusAnulado() {
		return idEstatusAnulado;
	}

	public void setIdEstatusAnulado(Integer idEstatusAnulado) {
		this.idEstatusAnulado = idEstatusAnulado;
	}

	public Date getDateActual() {
		return dateActual;
	}

	public void setDateActual(Date dateActual) {
		this.dateActual = dateActual;
	}

	public UsuarioSesionBean getUsuarioSesionBean() {
		return usuarioSesionBean;
	}

	public void setUsuarioSesionBean(UsuarioSesionBean usuarioSesionBean) {
		this.usuarioSesionBean = usuarioSesionBean;
	}

	public String getTipoDoc() {
		return tipoDoc;
	}

	public void setTipoDoc(String tipoDoc) {
		this.tipoDoc = tipoDoc;
	}

	public String getNroDoc() {
		return nroDoc;
	}

	public void setNroDoc(String nroDoc) {
		this.nroDoc = nroDoc;
	}

	public Periodo getPeriodoActual() {
		return periodoActual;
	}

	public void setPeriodoActual(Periodo periodoActual) {
		this.periodoActual = periodoActual;
	}

	public List<Periodo> getListaPeriodo() {
		return listaPeriodo;
	}

	public void setListaPeriodo(List<Periodo> listaPeriodo) {
		this.listaPeriodo = listaPeriodo;
	}

	public OperacionService getServicio() {
		return servicio;
	}

	public void setServicio(OperacionService servicio) {
		this.servicio = servicio;
	}
	
	
	public void obteberPeriodoActual() {
		
		periodoActual= new Periodo();
		
		listaPeriodo = periodoService.listarActivo();
		
		if(listaPeriodo==null || listaPeriodo.isEmpty()) {
			periodoActual = null;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "No existe un periodo activo, aperture un periodo", ""));
		}else {
			periodoActual = listaPeriodo.get(0);
		}
		
	}
	
	
	public String anularNroDo() {
		
		if(periodoActual == null) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "No existe un periodo activo, aperture un periodo", ""));
			limpiar();
			return "";
		}
		
		Operacion operacionLocal = new Operacion();
		
		UtilFechas utilFechas = new UtilFechas();
		String fechaActual = utilFechas.converDateToString(dateActual);
		
		
		
		operacionLocal = servicio.recuperarPorNroRecTipoDocFechaActual(nroDoc, tipoDoc, fechaActual, periodoActual.getId(), 1, 1);
		
		operacionLocal.setEstatusOperacion(new EstatusOperacion(4));
		operacionLocal.setIdUsuarioEliminacion(usuarioSesionBean.getUsuario().getId());
		
		
		servicio.actualizar(operacionLocal);
		
		limpiar();
		
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Anulacion de Nro de comprobante exitoso", ""));
		
		return "";
		
		
		
		
	}
	
	
	public void limpiar() {
		
		nroDoc = null;
		tipoDoc = null;

	}
	


	
}
