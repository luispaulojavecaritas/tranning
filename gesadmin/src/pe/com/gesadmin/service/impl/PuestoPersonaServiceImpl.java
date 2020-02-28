package pe.com.gesadmin.service.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import pe.com.gesadmin.dao.PuestoPersonaDao;
import pe.com.gesadmin.dao.impl.PuestoPersonaDaoImpl;
import pe.com.gesadmin.entity.PuestoPersona;
import pe.com.gesadmin.service.PuestoPersonaService;
import pe.com.gesadmin.transfer.PuestoPersonaTransfer;

@Stateless
public class PuestoPersonaServiceImpl implements PuestoPersonaService {
	
	@EJB
	PuestoPersonaDao puestoPersonaDao = new PuestoPersonaDaoImpl();

	@Override
	public List<PuestoPersona> listar() {
		// TODO Auto-generated method stub
		return puestoPersonaDao.findAll();
	}

	@Override
	public List<PuestoPersonaTransfer> listarTransfer() {
		// TODO Auto-generated method stub
		return puestoPersonaDao.findTransferAll();
	}

	@Override
	public void crear(PuestoPersona entidad) {
		// TODO Auto-generated method stub
		puestoPersonaDao.create(entidad);
	}

	@Override
	public void actualizar(PuestoPersona entidad) {
		// TODO Auto-generated method stub
		puestoPersonaDao.update(entidad);
	}

	@Override
	public PuestoPersona recuperar(Integer id) {
		// TODO Auto-generated method stub
		return puestoPersonaDao.findById(id);
	}

}
