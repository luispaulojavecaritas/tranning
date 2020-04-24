package pe.com.gesadmin.service.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import pe.com.gesadmin.dao.TipoOperacionDao;
import pe.com.gesadmin.dao.impl.TipoOperacionDaoImpl;
import pe.com.gesadmin.entity.TipoOperacion;
import pe.com.gesadmin.service.TipoOperacionService;

@Stateless
public class TipoOperacionServiceImpl implements TipoOperacionService {
	
	@EJB
	TipoOperacionDao tipoOperacionDao = new TipoOperacionDaoImpl(); 

	@Override
	public List<TipoOperacion> listar() {
		// TODO Auto-generated method stub
		return tipoOperacionDao.findAll();
	}
	
	@Override
	public List<TipoOperacion> listarActivo() {
		// TODO Auto-generated method stub
		return tipoOperacionDao.findAllActive();
	}

	@Override
	public void crear(TipoOperacion entidad) {
		// TODO Auto-generated method stub
		tipoOperacionDao.create(entidad);

	}

	@Override
	public void actualizar(TipoOperacion entidad) {
		// TODO Auto-generated method stub
		tipoOperacionDao.update(entidad);
	}

	@Override
	public TipoOperacion recuperar(Integer id) {
		// TODO Auto-generated method stub
		return tipoOperacionDao.findById(id);
	}

}
