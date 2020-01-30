/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.cc_sarita.transfer;

/**
 *
 * @author USUARIO
 */
public class MovimientoTransfer {
    
    private String id;
    private String descripcion;
    private String monto;
    private String fechaPago;
    private String fechaVencimiento;
    private String tipoOrden;
    private String situacionOrden;
    private String puestoProveedor;
    private String personaOperacion;

    public MovimientoTransfer() {
    }

    public MovimientoTransfer(String id) {
        this.id = id;
    }

    public MovimientoTransfer(String id, String descripcion, String monto, String fechaPago, String fechaVencimiento, String tipoOrden, String situacionOrden, String puestoProveedor, String personaOperacion) {
        this.id = id;
        this.descripcion = descripcion;
        this.monto = monto;
        this.fechaPago = fechaPago;
        this.fechaVencimiento = fechaVencimiento;
        this.tipoOrden = tipoOrden;
        this.situacionOrden = situacionOrden;
        this.puestoProveedor = puestoProveedor;
        this.personaOperacion = personaOperacion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }

    public String getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(String fechaPago) {
        this.fechaPago = fechaPago;
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getTipoOrden() {
        return tipoOrden;
    }

    public void setTipoOrden(String tipoOrden) {
        this.tipoOrden = tipoOrden;
    }

    public String getSituacionOrden() {
        return situacionOrden;
    }

    public void setSituacionOrden(String situacionOrden) {
        this.situacionOrden = situacionOrden;
    }

    public String getPuestoProveedor() {
        return puestoProveedor;
    }

    public void setPuestoProveedor(String puestoProveedor) {
        this.puestoProveedor = puestoProveedor;
    }

    public String getPersonaOperacion() {
        return personaOperacion;
    }

    public void setPersonaOperacion(String personaOperacion) {
        this.personaOperacion = personaOperacion;
    }

    @Override
    public String toString() {
        return "MovimientoTransfer{" + "id=" + id + ", descripcion=" + descripcion + ", monto=" + monto + ", fechaPago=" + fechaPago + ", fechaVencimiento=" + fechaVencimiento + ", tipoOrden=" + tipoOrden + ", situacionOrden=" + situacionOrden + ", puestoProveedor=" + puestoProveedor + ", personaOperacion=" + personaOperacion + '}';
    }   
    
}
