package pe.com.gesadmin.service.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import pe.com.gesadmin.dao.IngresoDao;
import pe.com.gesadmin.dao.impl.IngresoDaoImpl;
import pe.com.gesadmin.entity.Ingreso;
import pe.com.gesadmin.service.IngresoService;

@Stateless
public class IngresoServiceImpl implements IngresoService {
	
	@EJB
	IngresoDao ingresoDao = new IngresoDaoImpl();

	@Override
	public List<Ingreso> listar() {
		// TODO Auto-generated method stub
		return ingresoDao.findAll();
	}

	@Override
	public void crear(Ingreso entidad) {
		// TODO Auto-generated method stub
		ingresoDao.create(entidad);
	}

	@Override
	public void actualizar(Ingreso entidad) {
		// TODO Auto-generated method stub
		ingresoDao.update(entidad);
	}

	@Override
	public Ingreso recuperar(Integer id) {
		// TODO Auto-generated method stub
		return ingresoDao.findById(id);
	}

}
