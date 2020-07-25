package pe.com.gesadmin.service.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import pe.com.gesadmin.dao.AmonestacionDao;
import pe.com.gesadmin.dao.impl.AmonestacionDaoImpl;
import pe.com.gesadmin.entity.Amonestacion;
import pe.com.gesadmin.service.AmonestacionService;

@Stateless
public class AmonestacionServiceImpl implements AmonestacionService {
	
	@EJB
	AmonestacionDao amonestacionDao = new AmonestacionDaoImpl();

	@Override
	public List<Amonestacion> listar() {
		// TODO Auto-generated method stub
		return amonestacionDao.findAll();
	}
	
	@Override
	public List<Amonestacion> listarActivo() {
		// TODO Auto-generated method stub
		return amonestacionDao.findAllActive();
	}

	@Override
	public void crear(Amonestacion entidad) {
		// TODO Auto-generated method stub
		amonestacionDao.create(entidad);
	}

	@Override
	public void actualizar(Amonestacion entidad) {
		// TODO Auto-generated method stub
		amonestacionDao.update(entidad);
	}

	@Override
	public Amonestacion recuperar(Integer id) {
		// TODO Auto-generated method stub
		return amonestacionDao.findById(id);
	}

	@Override
	public List<Amonestacion> listarPorPeriodoId(Integer idPeriodo) {
		// TODO Auto-generated method stub
		return amonestacionDao.findByPeriodoId(idPeriodo);
	}

	@Override
	public List<Amonestacion> listarPorAnioId(Integer idAnio) {
		// TODO Auto-generated method stub
		return amonestacionDao.findByAnioId(idAnio);
	}

}
