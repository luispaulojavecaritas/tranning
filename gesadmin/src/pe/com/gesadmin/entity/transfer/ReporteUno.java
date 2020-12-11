package pe.com.gesadmin.entity.transfer;

public class ReporteUno {

	private Integer idPuesto;
	private String descripcionPuesto;
	private Integer idBloque;
	private String descripcionBloque;
	private String persona;
	private Double anterior;
	private Double enero;
	private Double febrero;
	private Double marzo;
	private Double abril;
	private Double mayo;
	private Double junio;
	private Double julio;
	private Double agosto;
	private Double setiembre;
	private Double octubre;
	private Double noviembre;
	private Double diciembre;
	private Double total;

	public ReporteUno() {
	}

	public Integer getIdPuesto() {
		return idPuesto;
	}

	public void setIdPuesto(Integer idPuesto) {
		this.idPuesto = idPuesto;
	}

	public String getDescripcionPuesto() {
		return descripcionPuesto;
	}

	public void setDescripcionPuesto(String descripcionPuesto) {
		this.descripcionPuesto = descripcionPuesto;
	}

	public Integer getIdBloque() {
		return idBloque;
	}

	public void setIdBloque(Integer idBloque) {
		this.idBloque = idBloque;
	}

	public String getDescripcionBloque() {
		return descripcionBloque;
	}

	public void setDescripcionBloque(String descripcionBloque) {
		this.descripcionBloque = descripcionBloque;
	}

	public String getPersona() {
		return persona;
	}

	public void setPersona(String persona) {
		this.persona = persona;
	}

	public Double getAnterior() {
		return anterior;
	}

	public void setAnterior(Double anterior) {
		this.anterior = anterior;
	}

	public Double getEnero() {
		return enero;
	}

	public void setEnero(Double enero) {
		this.enero = enero;
	}

	public Double getFebrero() {
		return febrero;
	}

	public void setFebrero(Double febrero) {
		this.febrero = febrero;
	}

	public Double getMarzo() {
		return marzo;
	}

	public void setMarzo(Double marzo) {
		this.marzo = marzo;
	}

	public Double getAbril() {
		return abril;
	}

	public void setAbril(Double abril) {
		this.abril = abril;
	}

	public Double getMayo() {
		return mayo;
	}

	public void setMayo(Double mayo) {
		this.mayo = mayo;
	}

	public Double getJunio() {
		return junio;
	}

	public void setJunio(Double junio) {
		this.junio = junio;
	}

	public Double getJulio() {
		return julio;
	}

	public void setJulio(Double julio) {
		this.julio = julio;
	}

	public Double getAgosto() {
		return agosto;
	}

	public void setAgosto(Double agosto) {
		this.agosto = agosto;
	}

	public Double getSetiembre() {
		return setiembre;
	}

	public void setSetiembre(Double setiembre) {
		this.setiembre = setiembre;
	}

	public Double getOctubre() {
		return octubre;
	}

	public void setOctubre(Double octubre) {
		this.octubre = octubre;
	}

	public Double getNoviembre() {
		return noviembre;
	}

	public void setNoviembre(Double noviembre) {
		this.noviembre = noviembre;
	}

	public Double getDiciembre() {
		return diciembre;
	}

	public void setDiciembre(Double diciembre) {
		this.diciembre = diciembre;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "ReporteUno [idPuesto=" + idPuesto + ", descripcionPuesto=" + descripcionPuesto + ", idBloque="
				+ idBloque + ", descripcionBloque=" + descripcionBloque + ", persona=" + persona + ", anterior="
				+ anterior + ", enero=" + enero + ", febrero=" + febrero + ", marzo=" + marzo + ", abril=" + abril
				+ ", mayo=" + mayo + ", junio=" + junio + ", julio=" + julio + ", agosto=" + agosto + ", setiembre="
				+ setiembre + ", octubre=" + octubre + ", noviembre=" + noviembre + ", diciembre=" + diciembre
				+ ", total=" + total + "]";
	}

}
