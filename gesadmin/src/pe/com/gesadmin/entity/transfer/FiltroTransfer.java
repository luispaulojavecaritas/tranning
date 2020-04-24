package pe.com.gesadmin.entity.transfer;

public class FiltroTransfer {
	
	private Integer id;
	private String descripcion;

	public FiltroTransfer() {
		// TODO Auto-generated constructor stub
	}

	public FiltroTransfer(Integer id, String descripcion) {
		this.id = id;
		this.descripcion = descripcion;
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

	@Override
	public String toString() {
		return "FiltroTransfer [id=" + id + ", Descripcion=" + descripcion + "]";
	}
	
	
	

}
