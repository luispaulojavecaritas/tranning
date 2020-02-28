package pe.com.gesadmin.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pe.com.gesadmin.dao.OrdenDao;
import pe.com.gesadmin.entity.Orden;
import pe.com.gesadmin.transfer.OrdenTransfer;

public class OrdenDaoImpl implements OrdenDao{

	@PersistenceContext(unitName = "gesadminPU")
	EntityManager em;
	
	@Override
	public List<Orden> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrdenTransfer> findTransferAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void create(Orden entidad) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Orden entidad) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Orden findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
