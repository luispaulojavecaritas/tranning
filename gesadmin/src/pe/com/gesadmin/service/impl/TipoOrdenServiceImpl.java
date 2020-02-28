package pe.com.gesadmin.service.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import pe.com.gesadmin.dao.TipoOrdenDao;
import pe.com.gesadmin.dao.impl.TipoOrdenDaoImpl;
import pe.com.gesadmin.entity.TipoOrden;
import pe.com.gesadmin.service.TipoOrdenService;

@Stateless
public class TipoOrdenServiceImpl implements TipoOrdenService {
	
	@EJB
	TipoOrdenDao tipoOrdenDao = new TipoOrdenDaoImpl(); 

	@Override
	public List<TipoOrden> listar() {
		// TODO Auto-generated method stub
		return tipoOrdenDao.findAll();
	}

	@Override
	public void crear(TipoOrden entidad) {
		// TODO Auto-generated method stub
		tipoOrdenDao.create(entidad);

	}

	@Override
	public void actualizar(TipoOrden entidad) {
		// TODO Auto-generated method stub
		tipoOrdenDao.update(entidad);
	}

	@Override
	public TipoOrden recuperar(Integer id) {
		// TODO Auto-generated method stub
		return tipoOrdenDao.findById(id);
	}

}
