package pe.com.gesadmin.service.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import pe.com.gesadmin.dao.EstatusOperacionDao;
import pe.com.gesadmin.dao.impl.EstatusOperacionDaoImpl;
import pe.com.gesadmin.entity.EstatusOperacion;
import pe.com.gesadmin.service.EstatusOperacionService;

@Stateless
public class EstatusOperacionServiceImpl implements EstatusOperacionService {
	
	@EJB
	EstatusOperacionDao estatusOperacionDao = new EstatusOperacionDaoImpl();

	@Override
	public List<EstatusOperacion> listar() {
		// TODO Auto-generated method stub
		return estatusOperacionDao.findAll();
	}
	
	@Override
	public List<EstatusOperacion> listarActivo() {
		// TODO Auto-generated method stub
		return estatusOperacionDao.findAllActive();
	}

	@Override
	public void crear(EstatusOperacion entidad) {
		// TODO Auto-generated method stub
		estatusOperacionDao.create(entidad);
	}

	@Override
	public void actualizar(EstatusOperacion entidad) {
		// TODO Auto-generated method stub
		estatusOperacionDao.update(entidad);
	}

	@Override
	public EstatusOperacion recuperar(Integer id) {
		// TODO Auto-generated method stub
		return estatusOperacionDao.findById(id);
	}

}
