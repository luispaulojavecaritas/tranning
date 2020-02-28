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
public class PuestoPersonaTransfer {
    
    private String id;
    private String descripcion;
    private String estado;
    private String registro;
    private String puestoDescripcion;
    private String personaDescripcion;
    private String tipoEstatusDescripcion;

    public PuestoPersonaTransfer() {
    }

    public PuestoPersonaTransfer(String id, String descripcion, String estado, String registro, String puestoDescripcion, String personaDescripcion, String tipoEstatusDescripcion) {
        this.id = id;
        this.descripcion = descripcion;
        this.estado = estado;
        this.registro = registro;
        this.puestoDescripcion = puestoDescripcion;
        this.personaDescripcion = personaDescripcion;
        this.tipoEstatusDescripcion = tipoEstatusDescripcion;
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

    public String getPuestoDescripcion() {
        return puestoDescripcion;
    }

    public void setPuestoDescripcion(String puestoDescripcion) {
        this.puestoDescripcion = puestoDescripcion;
    }

    public String getPersonaDescripcion() {
        return personaDescripcion;
    }

    public void setPersonaDescripcion(String personaDescripcion) {
        this.personaDescripcion = personaDescripcion;
    }

    public String getTipoEstatusDescripcion() {
        return tipoEstatusDescripcion;
    }

    public void setTipoEstatusDescripcion(String tipoEstatusDescripcion) {
        this.tipoEstatusDescripcion = tipoEstatusDescripcion;
    }

    @Override
    public String toString() {
        return "PuestoPersonaTransfer{" + "id=" + id + ", descripcion=" + descripcion + ", estado=" + estado + ", registro=" + registro + ", puestoDescripcion=" + puestoDescripcion + ", personaDescripcion=" + personaDescripcion + ", tipoEstatusDescripcion=" + tipoEstatusDescripcion + '}';
    }

}
