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
public class ContactoProveedorTransfer {

    private String id;
    private String perTipoDoc;
    private String perNroDoc;
    private String perNombre;
    private String perPaterno;
    private String perMaterno;
    private String perPais;
    private String perSexo;
    private String perCelular;
    private String perFijo;
    private String perCorreo;
    private String proveRuc;
    private String proveRazonSocial;
    private String proveCelular;
    private String proveFijo;
    private String proveCorreo;

    public ContactoProveedorTransfer() {
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPerTipoDoc() {
		return perTipoDoc;
	}

	public void setPerTipoDoc(String perTipoDoc) {
		this.perTipoDoc = perTipoDoc;
	}

	public String getPerNroDoc() {
		return perNroDoc;
	}

	public void setPerNroDoc(String perNroDoc) {
		this.perNroDoc = perNroDoc;
	}

	public String getPerNombre() {
		return perNombre;
	}

	public void setPerNombre(String perNombre) {
		this.perNombre = perNombre;
	}

	public String getPerPaterno() {
		return perPaterno;
	}

	public void setPerPaterno(String perPaterno) {
		this.perPaterno = perPaterno;
	}

	public String getPerMaterno() {
		return perMaterno;
	}

	public void setPerMaterno(String perMaterno) {
		this.perMaterno = perMaterno;
	}

	public String getPerPais() {
		return perPais;
	}

	public void setPerPais(String perPais) {
		this.perPais = perPais;
	}

	public String getPerSexo() {
		return perSexo;
	}

	public void setPerSexo(String perSexo) {
		this.perSexo = perSexo;
	}

	public String getPerCelular() {
		return perCelular;
	}

	public void setPerCelular(String perCelular) {
		this.perCelular = perCelular;
	}

	public String getPerFijo() {
		return perFijo;
	}

	public void setPerFijo(String perFijo) {
		this.perFijo = perFijo;
	}

	public String getPerCorreo() {
		return perCorreo;
	}

	public void setPerCorreo(String perCorreo) {
		this.perCorreo = perCorreo;
	}

	public String getProveRuc() {
		return proveRuc;
	}

	public void setProveRuc(String proveRuc) {
		this.proveRuc = proveRuc;
	}

	public String getProveRazonSocial() {
		return proveRazonSocial;
	}

	public void setProveRazonSocial(String proveRazonSocial) {
		this.proveRazonSocial = proveRazonSocial;
	}

	public String getProveCelular() {
		return proveCelular;
	}

	public void setProveCelular(String proveCelular) {
		this.proveCelular = proveCelular;
	}

	public String getProveFijo() {
		return proveFijo;
	}

	public void setProveFijo(String proveFijo) {
		this.proveFijo = proveFijo;
	}

	public String getProveCorreo() {
		return proveCorreo;
	}

	public void setProveCorreo(String proveCorreo) {
		this.proveCorreo = proveCorreo;
	}

	@Override
	public String toString() {
		return "ContactoProveedorTransfer [id=" + id + ", perTipoDoc=" + perTipoDoc + ", perNroDoc=" + perNroDoc
				+ ", perNombre=" + perNombre + ", perPaterno=" + perPaterno + ", perMaterno=" + perMaterno
				+ ", perPais=" + perPais + ", perSexo=" + perSexo + ", perCelular=" + perCelular + ", perFijo="
				+ perFijo + ", perCorreo=" + perCorreo + ", proveRuc=" + proveRuc + ", proveRazonSocial="
				+ proveRazonSocial + ", proveCelular=" + proveCelular + ", proveFijo=" + proveFijo + ", proveCorreo="
				+ proveCorreo + "]";
	}
    
    
}
