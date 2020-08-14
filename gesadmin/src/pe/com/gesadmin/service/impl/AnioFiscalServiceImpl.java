package pe.com.gesadmin.service.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import pe.com.gesadmin.dao.AnioFiscalDao;
import pe.com.gesadmin.dao.PeriodoDao;
import pe.com.gesadmin.dao.impl.AnioFiscalDaoImpl;
import pe.com.gesadmin.dao.impl.PeriodoDaoImpl;
import pe.com.gesadmin.entity.AnioFiscal;
import pe.com.gesadmin.service.AnioFiscalService;

@Stateless
public class AnioFiscalServiceImpl implements AnioFiscalService {
	
	@EJB
	AnioFiscalDao anioFiscalDao = new AnioFiscalDaoImpl();
	
	@EJB
	PeriodoDao periodoDao = new PeriodoDaoImpl();

	@Override
	public List<AnioFiscal> listar() {
		// TODO Auto-generated method stub
		return anioFiscalDao.findAll();
	}
	
	@Override
	public List<AnioFiscal> listarActivo() {
		// TODO Auto-generated method stub
		return anioFiscalDao.findAllActive();
	}

	@Override
	public void crear(AnioFiscal entidad) {
		// TODO Auto-generated method stub
		anioFiscalDao.updateEstado();
		
		entidad.setEstado(1);
		
		anioFiscalDao.create(entidad);
		
		periodoDao.updateEstado();
	}

	@Override
	public void actualizar(AnioFiscal entidad) {
		// TODO Auto-generated method stub
		anioFiscalDao.update(entidad);
	}

	@Override
	public AnioFiscal recuperar(Integer id) {
		// TODO Auto-generated method stub
		return anioFiscalDao.findById(id);
	}

}
