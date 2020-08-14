package pe.com.gesadmin.entity.transfer;

import java.util.Date;

public class LecturasMedidasPreOperacion {
	
	private Integer idMedidaServicio;
	
	private Integer estatusOperacion;
	private Integer proveedorAcreedor;
	private Integer tipoOperacion;

	private Integer periodoId;
	private String periodoDescripcion;
	
	private Integer categoriaId;
	private String categoriaDescripcion;
	
	private Integer puestoId;
	private String puestoDescripcion;
	
	private Double lecturaActual;
	private Double LecturaAnterior;
	private Double consumo;
	
	private Double costoUnitarioConsumo;
	private Double costoSubtotalConsumo;
	
	private Double costoFijo;
	
	private Double costoTotal;

	public LecturasMedidasPreOperacion() {
		// TODO Auto-generated constructor stub
	}

	public Integer getIdMedidaServicio() {
		return idMedidaServicio;
	}

	public void setIdMedidaServicio(Integer idMedidaServicio) {
		this.idMedidaServicio = idMedidaServicio;
	}

	public Integer getPeriodoId() {
		return periodoId;
	}

	public void setPeriodoId(Integer periodoId) {
		this.periodoId = periodoId;
	}

	public String getPeriodoDescripcion() {
		return periodoDescripcion;
	}

	public void setPeriodoDescripcion(String periodoDescripcion) {
		this.periodoDescripcion = periodoDescripcion;
	}

	public Integer getCategoriaId() {
		return categoriaId;
	}

	public void setCategoriaId(Integer categoriaId) {
		this.categoriaId = categoriaId;
	}

	public String getCategoriaDescripcion() {
		return categoriaDescripcion;
	}

	public void setCategoriaDescripcion(String categoriaDescripcion) {
		this.categoriaDescripcion = categoriaDescripcion;
	}

	public Integer getPuestoId() {
		return puestoId;
	}

	public void setPuestoId(Integer puestoId) {
		this.puestoId = puestoId;
	}

	public String getPuestoDescripcion() {
		return puestoDescripcion;
	}

	public void setPuestoDescripcion(String puestoDescripcion) {
		this.puestoDescripcion = puestoDescripcion;
	}

	public Double getLecturaActual() {
		return lecturaActual;
	}

	public void setLecturaActual(Double lecturaActual) {
		this.lecturaActual = lecturaActual;
	}

	public Double getLecturaAnterior() {
		return LecturaAnterior;
	}

	public void setLecturaAnterior(Double lecturaAnterior) {
		LecturaAnterior = lecturaAnterior;
	}

	public Double getConsumo() {
		return consumo;
	}

	public void setConsumo(Double consumo) {
		this.consumo = consumo;
	}

	public Double getCostoUnitarioConsumo() {
		return costoUnitarioConsumo;
	}

	public void setCostoUnitarioConsumo(Double costoUnitarioConsumo) {
		this.costoUnitarioConsumo = costoUnitarioConsumo;
	}

	public Double getCostoSubtotalConsumo() {
		return costoSubtotalConsumo;
	}

	public void setCostoSubtotalConsumo(Double costoSubtotalConsumo) {
		this.costoSubtotalConsumo = costoSubtotalConsumo;
	}

	public Double getCostoFijo() {
		return costoFijo;
	}

	public void setCostoFijo(Double costoFijo) {
		this.costoFijo = costoFijo;
	}

	public Double getCostoTotal() {
		return costoTotal;
	}

	public void setCostoTotal(Double costoTotal) {
		this.costoTotal = costoTotal;
	}

	public Integer getEstatusOperacion() {
		return estatusOperacion;
	}

	public void setEstatusOperacion(Integer estatusOperacion) {
		this.estatusOperacion = estatusOperacion;
	}

	public Integer getProveedorAcreedor() {
		return proveedorAcreedor;
	}

	public void setProveedorAcreedor(Integer proveedorAcreedor) {
		this.proveedorAcreedor = proveedorAcreedor;
	}

	public Integer getTipoOperacion() {
		return tipoOperacion;
	}

	public void setTipoOperacion(Integer tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
	}


	@Override
	public String toString() {
		return "LecturasMedidasPreOperacion [idMedidaServicio=" + idMedidaServicio + ", estatusOperacion="
				+ estatusOperacion + ", proveedorAcreedor=" + proveedorAcreedor + ", tipoOperacion=" + tipoOperacion
				+ ", periodoId=" + periodoId
				+ ", periodoDescripcion=" + periodoDescripcion + ", categoriaId=" + categoriaId
				+ ", categoriaDescripcion=" + categoriaDescripcion + ", puestoId=" + puestoId + ", puestoDescripcion="
				+ puestoDescripcion + ", lecturaActual=" + lecturaActual + ", LecturaAnterior=" + LecturaAnterior
				+ ", consumo=" + consumo + ", costoUnitarioConsumo=" + costoUnitarioConsumo + ", costoSubtotalConsumo="
				+ costoSubtotalConsumo + ", costoFijo=" + costoFijo + ", costoTotal=" + costoTotal + "]";
	}
	
	
	

}
