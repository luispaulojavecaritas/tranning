package pe.com.gesadmin.service;

import java.util.List;

import javax.ejb.Local;

import pe.com.gesadmin.entity.transfer.ReciboTransfers;
import pe.com.gesadmin.entity.transfer.ReporteComprobanteCorreccion;
import pe.com.gesadmin.entity.transfer.ReporteCuatro;
import pe.com.gesadmin.entity.transfer.ReporteCuatroAnulado;
import pe.com.gesadmin.entity.transfer.ReporteDos;
import pe.com.gesadmin.entity.transfer.ReporteReciboEgreso;
import pe.com.gesadmin.entity.transfer.ReporteTres;
import pe.com.gesadmin.entity.transfer.ReporteUno;

@Local
public interface ReporteService {
	
	public List<ReporteUno> obtenerReporteUnoCategoriaAll(Integer idAnioFiscal);

	public List<ReporteUno> obtenerReporteUnoCategoriaOperacionId(Integer idAnioFiscal, Integer idCategoriaOperacion);

	public List<ReporteDos> obtenerReporteDosPuestoId(Integer idPuesto);

	public List<ReporteTres> obtenerReporteTresCategoriaAll(Integer idPuesto);

	public List<ReporteTres> obtenerReporteTresCategoriaOperacionId(Integer idPuesto, Integer idCategoriaOperacion);

	public List<ReporteCuatro> obtenerReporteCuatroDiaNoAnulados(String dia);
	
	public List<ReporteCuatroAnulado> obtenerReporteCuatroDiaAnulados(String dia);
	
	public List<ReporteComprobanteCorreccion> obtenerReporteComprobanteCorreccion(Integer idOperacion);
	
	public List<ReporteReciboEgreso> obtenerReporteReciboEgreso(Integer idOperacion);
	
	public List<ReciboTransfers> obtenerReporteRecegre(String nroDoc);
	
	public List<ReciboTransfers> obtenerReporteCompCorr(String nroDoc);
	
	public List<ReciboTransfers> obtenerReporteRec(String nroDoc);

}
