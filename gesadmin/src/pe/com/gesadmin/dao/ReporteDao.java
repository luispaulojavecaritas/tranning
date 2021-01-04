package pe.com.gesadmin.dao;

import java.util.List;

import pe.com.gesadmin.entity.transfer.ReciboTransfers;
import pe.com.gesadmin.entity.transfer.ReporteComprobanteCorreccion;
import pe.com.gesadmin.entity.transfer.ReporteCuatro;
import pe.com.gesadmin.entity.transfer.ReporteCuatroAnulado;
import pe.com.gesadmin.entity.transfer.ReporteDos;
import pe.com.gesadmin.entity.transfer.ReporteReciboEgreso;
import pe.com.gesadmin.entity.transfer.ReporteTres;
import pe.com.gesadmin.entity.transfer.ReporteUno;

public interface ReporteDao {

	public List<ReporteUno> getReporteUnoCategoriaAll(Integer idAnioFiscal);

	public List<ReporteUno> getReporteUnoCategoriaOperacionId(Integer idAnioFiscal, Integer idCategoriaOperacion);

	public List<ReporteDos> getReporteDosPuestoId(Integer idPuesto);

	public List<ReporteTres> getReporteTresCategoriaAll(Integer idPuesto);

	public List<ReporteTres> getReporteTresCategoriaOperacionId(Integer idPuesto, Integer idCategoriaOperacion);

	public List<ReporteCuatro> getReporteCuatroDiaNoAnulados(String dia);
	
	public List<ReporteCuatroAnulado> getReporteCuatroDiaAnulados(String dia);
	
	public List<ReporteComprobanteCorreccion> getReporteComprobanteCorreccion(Integer id);
	
	public List<ReporteReciboEgreso> getReporteReciboEgreso(Integer id);
	
	public List<ReciboTransfers> getReporteRecegre(String nroDoc);
	
	public List<ReciboTransfers> getReporteCompCorr(String nroDoc);
	
	public List<ReciboTransfers> getReporteRec(String nroDoc);
	
}
