package pe.com.gesadmin.service.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import pe.com.gesadmin.dao.MedidaAguaDao;
import pe.com.gesadmin.dao.impl.MedidaAguaDaoImpl;
import pe.com.gesadmin.entity.MedidaAgua;
import pe.com.gesadmin.service.MedidaAguaService;

@Stateless
public class MedidaAguaServiceImpl implements MedidaAguaService {
	
	@EJB
	MedidaAguaDao medidaAguaDao = new MedidaAguaDaoImpl();

	@Override
	public List<MedidaAgua> listar() {
		// TODO Auto-generated method stub
		return medidaAguaDao.findAll();
	}
	
	@Override
	public List<MedidaAgua> listarActivo() {
		// TODO Auto-generated method stub
		return medidaAguaDao.findAllActive();
	}

	@Override
	public void crear(MedidaAgua entidad) {
		// TODO Auto-generated method stub
		medidaAguaDao.create(entidad);
	}

	@Override
	public void actualizar(MedidaAgua entidad) {
		// TODO Auto-generated method stub
		medidaAguaDao.update(entidad);
	}

	@Override
	public MedidaAgua recuperar(Integer id) {
		// TODO Auto-generated method stub
		return medidaAguaDao.findById(id);
	}

}
