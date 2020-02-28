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
public class ContactoProveedorTransfer {

    private String id;
    private String proveeRuc;
    private String proveeRazonSocial;
    private String contacTipoDoc;
    private String contacNroDoc;
    private String contacNombre;
    private String contacFijo;
    private String contacCelular;
    private String contacCorreo;

    public ContactoProveedorTransfer() {
    }

    public ContactoProveedorTransfer(String id) {
        this.id = id;
    }

    public ContactoProveedorTransfer(String id, String proveeRuc, String proveeRazonSocial, String contacTipoDoc, String contacNroDoc, String contacNombre, String contacFijo, String contacCelular, String contacCorreo) {
        this.id = id;
        this.proveeRuc = proveeRuc;
        this.proveeRazonSocial = proveeRazonSocial;
        this.contacTipoDoc = contacTipoDoc;
        this.contacNroDoc = contacNroDoc;
        this.contacNombre = contacNombre;
        this.contacFijo = contacFijo;
        this.contacCelular = contacCelular;
        this.contacCorreo = contacCorreo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProveeRuc() {
        return proveeRuc;
    }

    public void setProveeRuc(String proveeRuc) {
        this.proveeRuc = proveeRuc;
    }

    public String getProveeRazonSocial() {
        return proveeRazonSocial;
    }

    public void setProveeRazonSocial(String proveeRazonSocial) {
        this.proveeRazonSocial = proveeRazonSocial;
    }

    public String getContacTipoDoc() {
        return contacTipoDoc;
    }

    public void setContacTipoDoc(String contacTipoDoc) {
        this.contacTipoDoc = contacTipoDoc;
    }

    public String getContacNroDoc() {
        return contacNroDoc;
    }

    public void setContacNroDoc(String contacNroDoc) {
        this.contacNroDoc = contacNroDoc;
    }

    public String getContacNombre() {
        return contacNombre;
    }

    public void setContacNombre(String contacNombre) {
        this.contacNombre = contacNombre;
    }

    public String getContacFijo() {
        return contacFijo;
    }

    public void setContacFijo(String contacFijo) {
        this.contacFijo = contacFijo;
    }

    public String getContacCelular() {
        return contacCelular;
    }

    public void setContacCelular(String contacCelular) {
        this.contacCelular = contacCelular;
    }

    public String getContacCorreo() {
        return contacCorreo;
    }

    public void setContacCorreo(String contacCorreo) {
        this.contacCorreo = contacCorreo;
    }

    @Override
    public String toString() {
        return "ContactoProveedorTransfer{" + "id=" + id + ", proveeRuc=" + proveeRuc + ", proveeRazonSocial=" + proveeRazonSocial + ", contacTipoDoc=" + contacTipoDoc + ", contacNroDoc=" + contacNroDoc + ", contacNombre=" + contacNombre + ", contacFijo=" + contacFijo + ", contacCelular=" + contacCelular + ", contacCorreo=" + contacCorreo + '}';
    }
    
    
    
    

}
