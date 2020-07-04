package pe.com.gesadmin.service.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import pe.com.gesadmin.dao.MedidaLuzDao;
import pe.com.gesadmin.dao.impl.MedidaLuzDaoImpl;
import pe.com.gesadmin.entity.MedidaLuz;
import pe.com.gesadmin.service.MedidaLuzService;

@Stateless
public class MedidaLuzServiceImpl implements MedidaLuzService {
	
	@EJB
	MedidaLuzDao medidaLuzDao = new MedidaLuzDaoImpl();

	@Override
	public List<MedidaLuz> listar() {
		// TODO Auto-generated method stub
		return medidaLuzDao.findAll();
	}
	
	@Override
	public List<MedidaLuz> listarActivo() {
		// TODO Auto-generated method stub
		return medidaLuzDao.findAllActive();
	}

	@Override
	public void crear(MedidaLuz entidad) {
		// TODO Auto-generated method stub
		medidaLuzDao.create(entidad);
	}

	@Override
	public void actualizar(MedidaLuz entidad) {
		// TODO Auto-generated method stub
		medidaLuzDao.update(entidad);
	}

	@Override
	public MedidaLuz recuperar(Integer id) {
		// TODO Auto-generated method stub
		return medidaLuzDao.findById(id);
	}

}
