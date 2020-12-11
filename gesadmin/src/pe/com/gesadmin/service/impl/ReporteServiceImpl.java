package pe.com.gesadmin.service.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import pe.com.gesadmin.dao.ReporteDao;
import pe.com.gesadmin.dao.impl.ReporteDaoImpl;
import pe.com.gesadmin.entity.transfer.ReporteCuatro;
import pe.com.gesadmin.entity.transfer.ReporteDos;
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
		return reporteDao.getReporteCuatroDia(dia);
	}

}
