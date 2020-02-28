package pe.com.gesadmin.service.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import pe.com.gesadmin.dao.SituacionOrdenDao;
import pe.com.gesadmin.dao.impl.SituacionOrdenDaoImpl;
import pe.com.gesadmin.entity.SituacionOrden;
import pe.com.gesadmin.service.SituacionOrdenService;

@Stateless
public class SituacionOrdenServiceImpl implements SituacionOrdenService {
	
	@EJB
	SituacionOrdenDao situacionOrdenDao = new SituacionOrdenDaoImpl();

	@Override
	public List<SituacionOrden> listar() {
		// TODO Auto-generated method stub
		return situacionOrdenDao.findAll();
	}

	@Override
	public void crear(SituacionOrden entidad) {
		// TODO Auto-generated method stub
		situacionOrdenDao.create(entidad);
	}

	@Override
	public void actualizar(SituacionOrden entidad) {
		// TODO Auto-generated method stub
		situacionOrdenDao.update(entidad);
	}

	@Override
	public SituacionOrden recuperar(Integer id) {
		// TODO Auto-generated method stub
		return situacionOrdenDao.findById(id);
	}

}
