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
public class OrdenTransfer {

    private String id;
    private String descripcion;
    private String monto;
    private String fechaVencimiento;
    private String fechaPago;
    private String estado;
    private String registro;
    private String personaId;
    private String personaTipoDoc;
    private String personaNroDoc;
    private String personaNombre;
    private String personaCargo;
    private String proveedorId;
    private String proveedorDescripcion;
    private String puestoId;
    private String puestoDescripcion;
    private String situacionOrdenDescripcion;
    private String tipoOrdenDescripcion;

    public OrdenTransfer() {
    }

    public OrdenTransfer(String id) {
        this.id = id;
    }

    public OrdenTransfer(String id, String descripcion, String monto, String fechaVencimiento, String fechaPago, String estado, String registro, String personaId, String personaTipoDoc, String personaNroDoc, String personaNombre, String personaCargo, String proveedorId, String proveedorDescripcion, String puestoId, String puestoDescripcion, String situacionOrdenDescripcion, String tipoOrdenDescripcion) {
        this.id = id;
        this.descripcion = descripcion;
        this.monto = monto;
        this.fechaVencimiento = fechaVencimiento;
        this.fechaPago = fechaPago;
        this.estado = estado;
        this.registro = registro;
        this.personaId = personaId;
        this.personaTipoDoc = personaTipoDoc;
        this.personaNroDoc = personaNroDoc;
        this.personaNombre = personaNombre;
        this.personaCargo = personaCargo;
        this.proveedorId = proveedorId;
        this.proveedorDescripcion = proveedorDescripcion;
        this.puestoId = puestoId;
        this.puestoDescripcion = puestoDescripcion;
        this.situacionOrdenDescripcion = situacionOrdenDescripcion;
        this.tipoOrdenDescripcion = tipoOrdenDescripcion;
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

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(String fechaPago) {
        this.fechaPago = fechaPago;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getRegistro() {
        return registro;
    }

    public void setRegistro(String registro) {
        this.registro = registro;
    }

    public String getPersonaId() {
        return personaId;
    }

    public void setPersonaId(String personaId) {
        this.personaId = personaId;
    }

    public String getPersonaTipoDoc() {
        return personaTipoDoc;
    }

    public void setPersonaTipoDoc(String personaTipoDoc) {
        this.personaTipoDoc = personaTipoDoc;
    }

    public String getPersonaNroDoc() {
        return personaNroDoc;
    }

    public void setPersonaNroDoc(String personaNroDoc) {
        this.personaNroDoc = personaNroDoc;
    }

    public String getPersonaNombre() {
        return personaNombre;
    }

    public void setPersonaNombre(String personaNombre) {
        this.personaNombre = personaNombre;
    }

    public String getPersonaCargo() {
        return personaCargo;
    }

    public void setPersonaCargo(String personaCargo) {
        this.personaCargo = personaCargo;
    }

    public String getProveedorId() {
        return proveedorId;
    }

    public void setProveedorId(String proveedorId) {
        this.proveedorId = proveedorId;
    }

    public String getProveedorDescripcion() {
        return proveedorDescripcion;
    }

    public void setProveedorDescripcion(String proveedorDescripcion) {
        this.proveedorDescripcion = proveedorDescripcion;
    }

    public String getPuestoId() {
        return puestoId;
    }

    public void setPuestoId(String puestoId) {
        this.puestoId = puestoId;
    }

    public String getPuestoDescripcion() {
        return puestoDescripcion;
    }

    public void setPuestoDescripcion(String puestoDescripcion) {
        this.puestoDescripcion = puestoDescripcion;
    }

    public String getSituacionOrdenDescripcion() {
        return situacionOrdenDescripcion;
    }

    public void setSituacionOrdenDescripcion(String situacionOrdenDescripcion) {
        this.situacionOrdenDescripcion = situacionOrdenDescripcion;
    }

    public String getTipoOrdenDescripcion() {
        return tipoOrdenDescripcion;
    }

    public void setTipoOrdenDescripcion(String tipoOrdenDescripcion) {
        this.tipoOrdenDescripcion = tipoOrdenDescripcion;
    }

    @Override
    public String toString() {
        return "OrdenTransfer{" + "id=" + id + ", descripcion=" + descripcion + ", monto=" + monto + ", fechaVencimiento=" + fechaVencimiento + ", fechaPago=" + fechaPago + ", estado=" + estado + ", registro=" + registro + ", personaId=" + personaId + ", personaTipoDoc=" + personaTipoDoc + ", personaNroDoc=" + personaNroDoc + ", personaNombre=" + personaNombre + ", personaCargo=" + personaCargo + ", proveedorId=" + proveedorId + ", proveedorDescripcion=" + proveedorDescripcion + ", puestoId=" + puestoId + ", puestoDescripcion=" + puestoDescripcion + ", situacionOrdenDescripcion=" + situacionOrdenDescripcion + ", tipoOrdenDescripcion=" + tipoOrdenDescripcion + '}';
    }
    

}
