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
public class UsuarioTransfer {
    
    private String id;
    private String usuario;
    private String clave;
    private String estado;
    private String registro;
    private String tipoNroDocPersona;
    private String nombrePersona;

    public UsuarioTransfer() {
    }

    public UsuarioTransfer(String id, String usuario, String clave, String estado, String registro, String tipoNroDocPersona, String nombrePersona) {
        this.id = id;
        this.usuario = usuario;
        this.clave = clave;
        this.estado = estado;
        this.registro = registro;
        this.tipoNroDocPersona = tipoNroDocPersona;
        this.nombrePersona = nombrePersona;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
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

    public String getTipoNroDocPersona() {
        return tipoNroDocPersona;
    }

    public void setTipoNroDocPersona(String tipoNroDocPersona) {
        this.tipoNroDocPersona = tipoNroDocPersona;
    }

    public String getNombrePersona() {
        return nombrePersona;
    }

    public void setNombrePersona(String nombrePersona) {
        this.nombrePersona = nombrePersona;
    }

    @Override
    public String toString() {
        return "UsuarioTransfer{" + "id=" + id + ", usuario=" + usuario + ", clave=" + clave + ", estado=" + estado + ", registro=" + registro + ", tipoNroDocPersona=" + tipoNroDocPersona + ", nombrePersona=" + nombrePersona + '}';
    }
    
    
    
}
