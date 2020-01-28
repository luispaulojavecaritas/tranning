/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.cc_sarita.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author USUARIO
 */
@Entity
@Table(name = "orden")
public class Orden implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Integer id;
    @Column(name = "Descripcion")
    private String descripcion;
    @Column(name = "Monto")
    private double monto;
    @Column(name = "Fecha_Vencimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaVencimiento;
    @Column(name = "Fecha_Pago")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaPago;
    @Column(name = "Estado")
    private int estado;
    @Column(name = "Registro", updatable = false, insertable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date registro;
    @JoinColumn(name = "Id_Persona_Responsable_Operacion", referencedColumnName = "Id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Persona idPersonaResponsableOperacion;
    @JoinColumn(name = "Id_Proveedor", referencedColumnName = "Id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Proveedor idProveedor;
    @JoinColumn(name = "Id_Puesto", referencedColumnName = "Id")
    @ManyToOne(fetch = FetchType.EAGER)
    private Puesto idPuesto;
    @JoinColumn(name = "Id_Situacion_Orden", referencedColumnName = "Id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private SituacionOrden idSituacionOrden;
    @JoinColumn(name = "Id_Tipo_Orden", referencedColumnName = "Id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private SituacionOrden idTipoOrden;

    public Orden() {
    }

    public Orden(Integer id) {
        this.id = id;
    }

    public Orden(Integer id, String descripcion, double monto, int estado) {
        this.id = id;
        this.descripcion = descripcion;
        this.monto = monto;
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Date getRegistro() {
        return registro;
    }

    public void setRegistro(Date registro) {
        this.registro = registro;
    }

    public Persona getIdPersonaResponsableOperacion() {
        return idPersonaResponsableOperacion;
    }

    public void setIdPersonaResponsableOperacion(Persona idPersonaResponsableOperacion) {
        this.idPersonaResponsableOperacion = idPersonaResponsableOperacion;
    }

    public Proveedor getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(Proveedor idProveedor) {
        this.idProveedor = idProveedor;
    }

    public Puesto getIdPuesto() {
        return idPuesto;
    }

    public void setIdPuesto(Puesto idPuesto) {
        this.idPuesto = idPuesto;
    }

    public SituacionOrden getIdSituacionOrden() {
        return idSituacionOrden;
    }

    public void setIdSituacionOrden(SituacionOrden idSituacionOrden) {
        this.idSituacionOrden = idSituacionOrden;
    }

    public SituacionOrden getIdTipoOrden() {
        return idTipoOrden;
    }

    public void setIdTipoOrden(SituacionOrden idTipoOrden) {
        this.idTipoOrden = idTipoOrden;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Orden other = (Orden) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Orden{" + "id=" + id + ", descripcion=" + descripcion + ", monto=" + monto + ", fechaVencimiento=" + fechaVencimiento + ", fechaPago=" + fechaPago + ", estado=" + estado + ", registro=" + registro + ", idPersonaResponsableOperacion=" + idPersonaResponsableOperacion + ", idProveedor=" + idProveedor + ", idPuesto=" + idPuesto + ", idSituacionOrden=" + idSituacionOrden + ", idTipoOrden=" + idTipoOrden + '}';
    }
    
    
}
