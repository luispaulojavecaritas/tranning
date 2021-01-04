package pe.com.gesadmin.service.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import pe.com.gesadmin.dao.ReporteDao;
import pe.com.gesadmin.dao.impl.ReporteDaoImpl;
import pe.com.gesadmin.entity.transfer.ReciboTransfers;
import pe.com.gesadmin.entity.transfer.ReporteComprobanteCorreccion;
import pe.com.gesadmin.entity.transfer.ReporteCuatro;
import pe.com.gesadmin.entity.transfer.ReporteCuatroAnulado;
import pe.com.gesadmin.entity.transfer.ReporteDos;
import pe.com.gesadmin.entity.transfer.ReporteReciboEgreso;
import pe.com.gesadmin.entity.transfer.ReporteTres;
import pe.com.gesadmin.entity.transfer.ReporteUno;
import pe.com.gesadmin.service.ReporteService;

@Stateless
public class ReporteServiceImpl implements ReporteService{
	
	@EJB
	ReporteDao reporteDao = new ReporteDaoImpl();

	@Override
	public List<ReporteUno> obtenerReporteUnoCategoriaAll(Integer idAnioFiscal) {
		// TODO Auto-generated method stub
		return reporteDao.getReporteUnoCategoriaAll(idAnioFiscal);
	}

	@Override
	public List<ReporteUno> obtenerReporteUnoCategoriaOperacionId(Integer idAnioFiscal, Integer idCategoriaOperacion) {
		// TODO Auto-generated method stub
		return reporteDao.getReporteUnoCategoriaOperacionId(idAnioFiscal, idCategoriaOperacion);
	}

	@Override
	public List<ReporteDos> obtenerReporteDosPuestoId(Integer idPuesto) {
		// TODO Auto-generated method stub
		return reporteDao.getReporteDosPuestoId(idPuesto);
	}

	@Override
	public List<ReporteTres> obtenerReporteTresCategoriaAll(Integer idPuesto) {
		// TODO Auto-generated method stub
		return reporteDao.getReporteTresCategoriaAll(idPuesto);
	}

	@Override
	public List<ReporteTres> obtenerReporteTresCategoriaOperacionId(Integer idPuesto, Integer idCategoriaOperacion) {
		// TODO Auto-generated method stub
		return reporteDao.getReporteTresCategoriaOperacionId(idPuesto, idCategoriaOperacion);
	}

	@Override
	public List<ReporteCuatro> obtenerReporteCuatroDiaNoAnulados(String dia) {
		// TODO Auto-generated method stub
		return reporteDao.getReporteCuatroDiaNoAnulados(dia);
	}

	@Override
	public List<ReporteCuatroAnulado> obtenerReporteCuatroDiaAnulados(String dia) {
		// TODO Auto-generated method stub
		return reporteDao.getReporteCuatroDiaAnulados(dia);
	}

	@Override
	public List<ReporteComprobanteCorreccion> obtenerReporteComprobanteCorreccion(Integer idOperacion) {
		// TODO Auto-generated method stub
		return reporteDao.getReporteComprobanteCorreccion(idOperacion);
	}

	@Override
	public List<ReporteReciboEgreso> obtenerReporteReciboEgreso(Integer idOperacion) {
		// TODO Auto-generated method stub
		return reporteDao.getReporteReciboEgreso(idOperacion);
	}

	@Override
	public List<ReciboTransfers> obtenerReporteRecegre(String nroDoc) {
		// TODO Auto-generated method stub
		return reporteDao.getReporteRecegre(nroDoc);
	}

	@Override
	public List<ReciboTransfers> obtenerReporteCompCorr(String nroDoc) {
		// TODO Auto-generated method stub
		return reporteDao.getReporteCompCorr(nroDoc);
	}

	@Override
	public List<ReciboTransfers> obtenerReporteRec(String nroDoc) {
		// TODO Auto-generated method stub
		return reporteDao.getReporteRec(nroDoc);
	}

}
