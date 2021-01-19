package pe.com.gesadmin.service.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import pe.com.gesadmin.dao.PuestoPersonaCargoDao;
import pe.com.gesadmin.dao.impl.PuestoPersonaCargoDaoImpl;
import pe.com.gesadmin.entity.PuestoPersonaCargo;
import pe.com.gesadmin.service.PuestoPersonaCargoService;
import pe.com.gesadmin.transfer.PuestoPersonaTransfer;

@Stateless
public class PuestoPersonaCargoServiceImpl implements PuestoPersonaCargoService {
	
	@EJB
	PuestoPersonaCargoDao puestoPersonaCargoDao = new PuestoPersonaCargoDaoImpl();

	@Override
	public List<PuestoPersonaCargo> listar() {
		// TODO Auto-generated method stub
		return puestoPersonaCargoDao.findAll();
	}

	@Override
	public List<PuestoPersonaCargo> listarActivo() {
		// TODO Auto-generated method stub
		return puestoPersonaCargoDao.findAllActive();
	}

	@Override
	public void crear(PuestoPersonaCargo entidad) {
		// TODO Auto-generated method stub
		puestoPersonaCargoDao.create(entidad);
	}

	@Override
	public void actualizar(PuestoPersonaCargo entidad) {
		// TODO Auto-generated method stub
		puestoPersonaCargoDao.update(entidad);
	}

	@Override
	public PuestoPersonaCargo recuperar(Integer id) {
		// TODO Auto-generated method stub
		return puestoPersonaCargoDao.findById(id);
	}

	@Override
	public List<PuestoPersonaCargo> listarPuestoId(Integer idPuesto) {
		// TODO Auto-generated method stub
		return puestoPersonaCargoDao.findbyPuestoId(idPuesto);
	}

	@Override
	public List<PuestoPersonaCargo> listarPuestoIdPropietarioActivo(Integer idPuesto) {
		// TODO Auto-generated method stub
		return puestoPersonaCargoDao.findPuestoIdPropietarioActivo(idPuesto);
	}

}
