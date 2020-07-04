package pe.com.gesadmin.service.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import pe.com.gesadmin.dao.TipoAmonestacionDao;
import pe.com.gesadmin.dao.impl.TipoAmonestacionDaoImpl;
import pe.com.gesadmin.entity.TipoAmonestacion;
import pe.com.gesadmin.service.TipoAmonestacionService;

@Stateless
public class TipoAmonestacionServiceImpl implements TipoAmonestacionService {
	
	@EJB
	TipoAmonestacionDao tipoAmonestacionDao = new TipoAmonestacionDaoImpl();

	@Override
	public List<TipoAmonestacion> listar() {
		// TODO Auto-generated method stub
		return tipoAmonestacionDao.findAll();
	}
	
	@Override
	public List<TipoAmonestacion> listarActivo() {
		// TODO Auto-generated method stub
		return tipoAmonestacionDao.findAllActive();
	}

	@Override
	public void crear(TipoAmonestacion entidad) {
		// TODO Auto-generated method stub
		tipoAmonestacionDao.create(entidad);
	}

	@Override
	public void actualizar(TipoAmonestacion entidad) {
		// TODO Auto-generated method stub
		tipoAmonestacionDao.update(entidad);
	}

	@Override
	public TipoAmonestacion recuperar(Integer id) {
		// TODO Auto-generated method stub
		return tipoAmonestacionDao.findById(id);
	}

}
