package pe.com.gesadmin.entity.transfer;

public class PeriodoTransfer {
	
	private Integer idAnio;
	private Integer IdPeriodo;
	
	public PeriodoTransfer() {
		// TODO Auto-generated constructor stub
	}

	public Integer getIdAnio() {
		return idAnio;
	}

	public void setIdAnio(Integer idAnio) {
		this.idAnio = idAnio;
	}

	public Integer getIdPeriodo() {
		return IdPeriodo;
	}

	public void setIdPeriodo(Integer idPeriodo) {
		IdPeriodo = idPeriodo;
	}

	@Override
	public String toString() {
		return "PeriodoTransfer [idAnio=" + idAnio + ", IdPeriodo=" + IdPeriodo + "]";
	}
	
	
}
