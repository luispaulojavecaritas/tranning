package pe.com.gesadmin.service.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import pe.com.gesadmin.dao.CargoDao;
import pe.com.gesadmin.dao.impl.CargoDaoImpl;
import pe.com.gesadmin.entity.Cargo;
import pe.com.gesadmin.service.CargoService;

@Stateless
public class CargoServiceImpl implements CargoService {
	
	@EJB
	CargoDao cargoDao = new CargoDaoImpl();

	@Override
	public List<Cargo> listar() {
		// TODO Auto-generated method stub
		return cargoDao.findAll();
	}
	
	@Override
	public List<Cargo> listarActivo() {
		// TODO Auto-generated method stub
		return cargoDao.findAllActive();
	}

	@Override
	public void crear(Cargo entidad) {
		// TODO Auto-generated method stub
		cargoDao.create(entidad);
	}

	@Override
	public void actualizar(Cargo entidad) {
		// TODO Auto-generated method stub
		cargoDao.update(entidad);
	}

	@Override
	public Cargo recuperar(Integer id) {
		// TODO Auto-generated method stub
		return cargoDao.findById(id);
	}

}
