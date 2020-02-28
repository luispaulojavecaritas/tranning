package pe.com.gesadmin.service.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import pe.com.gesadmin.dao.EgresoDao;
import pe.com.gesadmin.dao.impl.EgresoDaoImpl;
import pe.com.gesadmin.entity.Egreso;
import pe.com.gesadmin.service.EgresoService;

@Stateless
public class EgresoServiceImpl implements EgresoService {
	
	@EJB
	EgresoDao egresoDao = new EgresoDaoImpl();

	@Override
	public List<Egreso> listar() {
		// TODO Auto-generated method stub
		return egresoDao.findAll();
	}

	@Override
	public void crear(Egreso entidad) {
		// TODO Auto-generated method stub
		egresoDao.create(entidad);
	}

	@Override
	public void actualizar(Egreso entidad) {
		// TODO Auto-generated method stub
		egresoDao.update(entidad);
	}

	@Override
	public Egreso recuperar(Integer id) {
		// TODO Auto-generated method stub
		return egresoDao.findById(id);
	}

}
