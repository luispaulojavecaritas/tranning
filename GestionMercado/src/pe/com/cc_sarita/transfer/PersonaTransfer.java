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
public class PersonaTransfer {
    
    private Integer id;
    private String nombre;
    private String paterno;
    private String materno;
    private String nroDocumento;
    private String direccion;
    private String telefonoFijo;
    private String telefonoCelular;
    private String correoElectronico;
    private String registro;
    private String paisDescripcion;
    private String sexoDescripcion;
    private String tipoDocumentoDescripcion;

    public PersonaTransfer() {
    }

    public PersonaTransfer(Integer id) {
        this.id = id;
    }

    public PersonaTransfer(Integer id, String nombre, String paterno, String materno, String nroDocumento, String direccion, String telefonoFijo, String telefonoCelular, String correoElectronico, String registro, String paisDescripcion, String sexoDescripcion, String tipoDocumentoDescripcion) {
        this.id = id;
        this.nombre = nombre;
        this.paterno = paterno;
        this.materno = materno;
        this.nroDocumento = nroDocumento;
        this.direccion = direccion;
        this.telefonoFijo = telefonoFijo;
        this.telefonoCelular = telefonoCelular;
        this.correoElectronico = correoElectronico;
        this.registro = registro;
        this.paisDescripcion = paisDescripcion;
        this.sexoDescripcion = sexoDescripcion;
        this.tipoDocumentoDescripcion = tipoDocumentoDescripcion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPaterno() {
        return paterno;
    }

    public void setPaterno(String paterno) {
        this.paterno = paterno;
    }

    public String getMaterno() {
        return materno;
    }

    public void setMaterno(String materno) {
        this.materno = materno;
    }

    public String getNroDocumento() {
        return nroDocumento;
    }

    public void setNroDocumento(String nroDocumento) {
        this.nroDocumento = nroDocumento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefonoFijo() {
        return telefonoFijo;
    }

    public void setTelefonoFijo(String telefonoFijo) {
        this.telefonoFijo = telefonoFijo;
    }

    public String getTelefonoCelular() {
        return telefonoCelular;
    }

    public void setTelefonoCelular(String telefonoCelular) {
        this.telefonoCelular = telefonoCelular;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getRegistro() {
        return registro;
    }

    public void setRegistro(String registro) {
        this.registro = registro;
    }

    public String getPaisDescripcion() {
        return paisDescripcion;
    }

    public void setPaisDescripcion(String paisDescripcion) {
        this.paisDescripcion = paisDescripcion;
    }

    public String getSexoDescripcion() {
        return sexoDescripcion;
    }

    public void setSexoDescripcion(String sexoDescripcion) {
        this.sexoDescripcion = sexoDescripcion;
    }

    public String getTipoDocumentoDescripcion() {
        return tipoDocumentoDescripcion;
    }

    public void setTipoDocumentoDescripcion(String tipoDocumentoDescripcion) {
        this.tipoDocumentoDescripcion = tipoDocumentoDescripcion;
    }

    @Override
    public String toString() {
        return "PersonaTransfer{" + "id=" + id + ", nombre=" + nombre + ", paterno=" + paterno + ", materno=" + materno + ", nroDocumento=" + nroDocumento + ", direccion=" + direccion + ", telefonoFijo=" + telefonoFijo + ", telefonoCelular=" + telefonoCelular + ", correoElectronico=" + correoElectronico + ", registro=" + registro + ", paisDescripcion=" + paisDescripcion + ", sexoDescripcion=" + sexoDescripcion + ", tipoDocumentoDescripcion=" + tipoDocumentoDescripcion + '}';
    }
    
}
