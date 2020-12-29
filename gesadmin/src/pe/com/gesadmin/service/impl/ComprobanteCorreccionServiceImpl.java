package pe.com.gesadmin.service.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import pe.com.gesadmin.dao.ComprobanteCorreccionDao;
import pe.com.gesadmin.dao.impl.ComprobanteCorreccionDaoImpl;
import pe.com.gesadmin.entity.ComprobanteCorreccion;
import pe.com.gesadmin.service.ComprobanteCorreccionService;

@Stateless
public class ComprobanteCorreccionServiceImpl implements ComprobanteCorreccionService {
	
	@EJB
	ComprobanteCorreccionDao ComprobanteCorreccionDao = new ComprobanteCorreccionDaoImpl();

	@Override
	public List<ComprobanteCorreccion> listar() {
		// TODO Auto-generated method stub
		return ComprobanteCorreccionDao.findAll();
	}
	
	@Override
	public List<ComprobanteCorreccion> listarActivo() {
		// TODO Auto-generated method stub
		return ComprobanteCorreccionDao.findAllActive();
	}

	@Override
	public void crear(ComprobanteCorreccion entidad) {
		// TODO Auto-generated method stub
		ComprobanteCorreccionDao.create(entidad);
	}

	@Override
	public void actualizar(ComprobanteCorreccion entidad) {
		// TODO Auto-generated method stub
		ComprobanteCorreccionDao.update(entidad);
	}

	@Override
	public ComprobanteCorreccion recuperar(Integer id) {
		// TODO Auto-generated method stub
		return ComprobanteCorreccionDao.findById(id);
	}

}
