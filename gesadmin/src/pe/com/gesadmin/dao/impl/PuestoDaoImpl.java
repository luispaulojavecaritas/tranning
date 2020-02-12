package pe.com.gesadmin.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pe.com.gesadmin.dao.PuestoDao;
import pe.com.gesadmin.entity.Puesto;
import pe.com.gesadmin.transfer.PuestoTransfer;

public class PuestoDaoImpl implements PuestoDao {
	
	@PersistenceContext(unitName = "gesadminPU")
	EntityManager em;

	@Override
	public List<Puesto> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PuestoTransfer> findTransferAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void create(Puesto entidad) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Puesto entidad) {
		// TODO Auto-generated method stub

	}

	@Override
	public Puesto findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
