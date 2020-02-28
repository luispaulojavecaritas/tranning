/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.gesadmin.transfer;

/**
 *
 * @author USUARIO
 */
public class PuestoTransfer {
    
    private String id;
    private String descripcion;
    private String estado;
    private String registro;
    private String bloqueDescripcion;

    public PuestoTransfer() {
    }

    public PuestoTransfer(String id, String descripcion, String estado, String registro, String bloqueDescripcion) {
        this.id = id;
        this.descripcion = descripcion;
        this.estado = estado;
        this.registro = registro;
        this.bloqueDescripcion = bloqueDescripcion;
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

    public String getBloqueDescripcion() {
        return bloqueDescripcion;
    }

    public void setBloqueDescripcion(String bloqueDescripcion) {
        this.bloqueDescripcion = bloqueDescripcion;
    }

    @Override
    public String toString() {
        return "PuestoTransfer{" + "id=" + id + ", descripcion=" + descripcion + ", estado=" + estado + ", registro=" + registro + ", bloqueDescripcion=" + bloqueDescripcion + '}';
    }    
    
}
