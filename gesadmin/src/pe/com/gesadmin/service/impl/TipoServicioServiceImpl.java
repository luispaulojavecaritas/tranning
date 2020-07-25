package pe.com.gesadmin.service.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import pe.com.gesadmin.dao.TipoServicioDao;
import pe.com.gesadmin.dao.impl.TipoServicioDaoImpl;
import pe.com.gesadmin.entity.TipoServicio;
import pe.com.gesadmin.service.TipoServicioService;

@Stateless
public class TipoServicioServiceImpl implements TipoServicioService {
	
	@EJB
	TipoServicioDao tipoServicioDao = new TipoServicioDaoImpl();

	@Override
	public List<TipoServicio> listar() {
		// TODO Auto-generated method stub
		return tipoServicioDao.findAll();
	}
	
	@Override
	public List<TipoServicio> listarActivo() {
		// TODO Auto-generated method stub
		return tipoServicioDao.findAllActive();
	}

	@Override
	public void crear(TipoServicio entidad) {
		// TODO Auto-generated method stub
		tipoServicioDao.create(entidad);
	}

	@Override
	public void actualizar(TipoServicio entidad) {
		// TODO Auto-generated method stub
		tipoServicioDao.update(entidad);
	}

	@Override
	public TipoServicio recuperar(Integer id) {
		// TODO Auto-generated method stub
		return tipoServicioDao.findById(id);
	}

}
