package pe.com.gesadmin.managed;

import java.util.ArrayList;
import java.util.List;


import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import pe.com.gesadmin.entity.Operacion;
import pe.com.gesadmin.entity.transfer.ReciboTransfers;
import pe.com.gesadmin.service.OperacionService;
import pe.com.gesadmin.service.ReporteService;
import pe.com.gesadmin.service.impl.OperacionServiceImpl;
import pe.com.gesadmin.service.impl.ReporteServiceImpl;

@ManagedBean
@ViewScoped
public class ConsultaComprobanteCorreccionBean {

	private List<ReciboTransfers> lista = new ArrayList<>();
	private Operacion entidadOperacion = new Operacion();
	private ReciboTransfers entidadseleccionada = new ReciboTransfers();
	private String nroDoc;

	private boolean booTabla = false;
	private boolean booDetalle = false;

	@EJB
	private ReporteService servicio = new ReporteServiceImpl();
	@EJB
	private OperacionService operacionService = new OperacionServiceImpl();

	public ConsultaComprobanteCorreccionBean() {
		// TODO Auto-generated constructor stub
		entidadOperacion = new Operacion();
		entidadseleccionada = new ReciboTransfers();
		lista = new ArrayList<>();

		booDetalle = false;
		booTabla = false;
	}

	public List<ReciboTransfers> getLista() {
		return lista;
	}

	public void setLista(List<ReciboTransfers> lista) {
		this.lista = lista;
	}

	public ReciboTransfers getEntidadseleccionada() {
		return entidadseleccionada;
	}

	public void setEntidadseleccionada(ReciboTransfers entidadseleccionada) {
		this.entidadseleccionada = entidadseleccionada;
	}

	public boolean isBooDetalle() {
		return booDetalle;
	}

	public void setBooDetalle(boolean booDetalle) {
		this.booDetalle = booDetalle;
	}

	public boolean isBooTabla() {
		return booTabla;
	}

	public void setBooTabla(boolean booTabla) {
		this.booTabla = booTabla;
	}

	public String getNroDoc() {
		return nroDoc;
	}

	public void setNroDoc(String nroDoc) {
		this.nroDoc = nroDoc;
	}

	public void setServicio(ReporteService servicio) {
		this.servicio = servicio;
	}

	public void setOperacionService(OperacionService operacionService) {
		this.operacionService = operacionService;
	}

	public Operacion getEntidadOperacion() {
		return entidadOperacion;
	}

	public void setEntidadOperacion(Operacion entidadOperacion) {
		this.entidadOperacion = entidadOperacion;
	}

	public void consultar() {

		lista = new ArrayList<>();

		try {
			lista = servicio.obtenerReporteCompCorr(nroDoc);
			booTabla = true;
			booDetalle = false;
			System.out.println("Mostrar Tabla todo ok");
		} catch (Exception e) {
			// TODO: handle exception
			lista = null;
			booDetalle = false;
			booTabla = false;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Problemas al recuperar registros", ""));
		}
		
	}
	
	public void recuperar() {

		entidadOperacion = new Operacion();

		try {
			entidadOperacion = operacionService.recuperar(Integer.parseInt(entidadseleccionada.getOpeId()));
			booDetalle = true;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Seleccion de registro exitosa", ""));
		} catch (Exception e) {
			// TODO: handle exception
			entidadOperacion = null;
			booDetalle = false;
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error.", "Problemas al recuperar registro"));
		}
	}
	
	public void onRowSelect(SelectEvent event) {
		if (entidadseleccionada == null) {
			entidadOperacion = new Operacion();
			entidadseleccionada = new ReciboTransfers();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "No se selecciono ningun registro", ""));
		} else {
			recuperar();
			entidadseleccionada = new ReciboTransfers();
			
		}
	}

	public void onRowUnselect(UnselectEvent event) {
		entidadOperacion = new Operacion();
		entidadseleccionada = new ReciboTransfers();
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Se anulo seleccion de registro ", ""));
	}

	public void limpiar() {
		entidadOperacion = new Operacion();
		entidadseleccionada = new ReciboTransfers();
		nroDoc = null;
		lista = new ArrayList<>();
		booDetalle = false;
		booTabla = false;

	}

}
