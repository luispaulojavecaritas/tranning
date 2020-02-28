package pe.com.gesadmin.service.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import pe.com.gesadmin.dao.SexoDao;
import pe.com.gesadmin.dao.impl.SexoDaoImpl;
import pe.com.gesadmin.entity.Sexo;
import pe.com.gesadmin.service.SexoService;

@Stateless
public class SexoServiceImpl implements SexoService {
	
	@EJB
	SexoDao sexoDao = new SexoDaoImpl();

	@Override
	public List<Sexo> listar() {
		// TODO Auto-generated method stub
		return sexoDao.findAll();
	}

	@Override
	public void crear(Sexo entidad) {
		// TODO Auto-generated method stub
		sexoDao.create(entidad);
	}

	@Override
	public void actualizar(Sexo entidad) {
		// TODO Auto-generated method stub
		sexoDao.update(entidad);
	}

	@Override
	public Sexo recuperar(Integer id) {
		// TODO Auto-generated method stub
		return sexoDao.findById(id);
	}

}
