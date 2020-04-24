package pe.com.gesadmin.service.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import pe.com.gesadmin.dao.BloqueDao;
import pe.com.gesadmin.dao.impl.BloqueDaoImpl;
import pe.com.gesadmin.entity.Bloque;
import pe.com.gesadmin.service.BloqueService;

@Stateless
public class BloqueServiceImpl implements BloqueService {
	
	@EJB
	BloqueDao bloqueDao = new BloqueDaoImpl();

	@Override
	public List<Bloque> listar() {
		// TODO Auto-generated method stub
		return bloqueDao.findAll();
	}
	
	@Override
	public List<Bloque> listarActivo() {
		// TODO Auto-generated method stub
		return bloqueDao.findAllActive();
	}

	@Override
	public void crear(Bloque entidad) {
		// TODO Auto-generated method stub
		bloqueDao.create(entidad);
	}

	@Override
	public void actualizar(Bloque entidad) {
		// TODO Auto-generated method stub
		bloqueDao.update(entidad);
	}

	@Override
	public Bloque recuperar(Integer id) {
		// TODO Auto-generated method stub
		return bloqueDao.findById(id);
	}

}
