package pe.com.gesadmin.service.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import pe.com.gesadmin.dao.PaisDao;
import pe.com.gesadmin.dao.impl.PaisDaoImpl;
import pe.com.gesadmin.entity.Pais;
import pe.com.gesadmin.service.PaisService;

@Stateless
public class PaisServiceImpl implements PaisService {
	
	@EJB
	PaisDao paisDao = new PaisDaoImpl();

	@Override
	public List<Pais> listar() {
		// TODO Auto-generated method stub
		return paisDao.findAll();
	}
	
	@Override
	public List<Pais> listarActivo() {
		// TODO Auto-generated method stub
		return paisDao.findAllActive();
	}

	@Override
	public void crear(Pais entidad) {
		// TODO Auto-generated method stub
		paisDao.create(entidad);
	}

	@Override
	public void actualizar(Pais entidad) {
		// TODO Auto-generated method stub
		paisDao.update(entidad);
	}

	@Override
	public Pais recuperar(Integer id) {
		// TODO Auto-generated method stub
		return paisDao.findById(id);
	}

}
