package pe.com.gesadmin.service.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import pe.com.gesadmin.dao.PeriodoDao;
import pe.com.gesadmin.dao.impl.PeriodoDaoImpl;
import pe.com.gesadmin.entity.Periodo;
import pe.com.gesadmin.service.PeriodoService;

@Stateless
public class PeriodoServiceImpl implements PeriodoService {
	
	@EJB
	PeriodoDao periodoDao = new PeriodoDaoImpl();

	@Override
	public List<Periodo> listar() {
		// TODO Auto-generated method stub
		return periodoDao.findAll();
	}

	@Override
	public void crear(Periodo entidad) {
		// TODO Auto-generated method stub
		periodoDao.updateEstado();
		
		entidad.setEstado(1);
		periodoDao.create(entidad);
	}

	@Override
	public void actualizar(Periodo entidad) {
		// TODO Auto-generated method stub
		periodoDao.update(entidad);
	}

	@Override
	public Periodo recuperar(Integer id) {
		// TODO Auto-generated method stub
		return periodoDao.findById(id);
	}

}
