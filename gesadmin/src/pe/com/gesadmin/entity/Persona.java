package pe.com.gesadmin.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the persona database table.
 * 
 */
@Entity
@NamedQuery(name="Persona.findAll", query="SELECT p FROM Persona p")
public class Persona implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(name="correo_electronico")
	private String correoElectronico;

	private String direccion;

	private String materno;

	private String nombre;

	@Column(name="nro_documento")
	private Integer nroDocumento;

	private String paterno;

	@Column(insertable=false, updatable=false)
	private Timestamp registro;

	@Column(name="telefono_celular")
	private String telefonoCelular;

	@Column(name="telefono_fijo")
	private String telefonoFijo;

	//uni-directional many-to-one association to Pais
	@ManyToOne
	@JoinColumn(name="id_pais", referencedColumnName = "id")
	private Pais pais;

	//uni-directional many-to-one association to Sexo
	@ManyToOne
	@JoinColumn(name="id_sexo", referencedColumnName = "id")
	private Sexo sexo;

	//uni-directional many-to-one association to TipoDocumento
	@ManyToOne
	@JoinColumn(name="id_tipo_documento", referencedColumnName = "id")
	private TipoDocumento tipoDocumento;

	public Persona() {
		pais = new Pais();
		sexo = new Sexo();
		tipoDocumento = new TipoDocumento();
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCorreoElectronico() {
		return this.correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getMaterno() {
		return this.materno;
	}

	public void setMaterno(String materno) {
		this.materno = materno;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getNroDocumento() {
		return this.nroDocumento;
	}

	public void setNroDocumento(Integer nroDocumento) {
		this.nroDocumento = nroDocumento;
	}

	public String getPaterno() {
		return this.paterno;
	}

	public void setPaterno(String paterno) {
		this.paterno = paterno;
	}

	public Timestamp getRegistro() {
		return this.registro;
	}

	public void setRegistro(Timestamp registro) {
		this.registro = registro;
	}

	public String getTelefonoCelular() {
		return this.telefonoCelular;
	}

	public void setTelefonoCelular(String telefonoCelular) {
		this.telefonoCelular = telefonoCelular;
	}

	public String getTelefonoFijo() {
		return this.telefonoFijo;
	}

	public void setTelefonoFijo(String telefonoFijo) {
		this.telefonoFijo = telefonoFijo;
	}

	public Pais getPais() {
		return this.pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	public Sexo getSexo() {
		return this.sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public TipoDocumento getTipoDocumento() {
		return this.tipoDocumento;
	}

	public void setTipoDocumento(TipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
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
		Persona other = (Persona) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Persona [id=" + id + ", correoElectronico=" + correoElectronico + ", direccion=" + direccion
				+ ", materno=" + materno + ", nombre=" + nombre + ", nroDocumento=" + nroDocumento + ", paterno="
				+ paterno + ", registro=" + registro + ", telefonoCelular=" + telefonoCelular + ", telefonoFijo="
				+ telefonoFijo + ", pais=" + pais + ", sexo=" + sexo + ", tipoDocumento=" + tipoDocumento + "]";
	}
	
	

}