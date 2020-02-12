package pe.com.gesadmin.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pe.com.gesadmin.dao.PuestoPersonaDao;
import pe.com.gesadmin.entity.PuestoPersona;
import pe.com.gesadmin.transfer.PuestoPersonaTransfer;

public class PuestoPersonaDaoImpl implements PuestoPersonaDao {
	
	@PersistenceContext(unitName = "gesadminPU")
	EntityManager em;

	@Override
	public List<PuestoPersona> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PuestoPersonaTransfer> findTransferAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void create(PuestoPersona entidad) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(PuestoPersona entidad) {
		// TODO Auto-generated method stub

	}

	@Override
	public PuestoPersona findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
