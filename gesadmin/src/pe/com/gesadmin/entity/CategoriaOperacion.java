package pe.com.gesadmin.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the egreso database table.
 * 
 */
@Entity
@Table(name = "categoria_operacion")
@NamedQuery(name="CategoriaOperacion.findAll", query="SELECT e FROM CategoriaOperacion e")
public class CategoriaOperacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private String descripcion;

	private Integer estado;

	@Column(insertable=false, updatable=false)
	private Timestamp registro;
	
	@ManyToOne
	@JoinColumn(name="id_tipo_operacion", referencedColumnName = "id")
	private TipoOperacion tipoOperacion;

	public CategoriaOperacion() {
		tipoOperacion = new TipoOperacion();
	}

	public CategoriaOperacion(Integer id) {
		this.id = id;
	}
	
	public CategoriaOperacion(Integer id, String descripcion) {
		this.id = id;
		this.descripcion = descripcion;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getEstado() {
		return this.estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public Timestamp getRegistro() {
		return this.registro;
	}

	public void setRegistro(Timestamp registro) {
		this.registro = registro;
	}
	
	

	public TipoOperacion getTipoOperacion() {
		return tipoOperacion;
	}

	public void setTipoOperacion(TipoOperacion tipoOperacion) {
		this.tipoOperacion = tipoOperacion;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CategoriaOperacion other = (CategoriaOperacion) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CategoriaOperacion [id=" + id + ", descripcion=" + descripcion + ", estado=" + estado + ", registro="
				+ registro + ", tipoOperacion=" + tipoOperacion + "]";
	}
	
	

}