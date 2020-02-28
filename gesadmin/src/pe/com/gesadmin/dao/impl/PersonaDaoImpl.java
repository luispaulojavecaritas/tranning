package pe.com.gesadmin.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pe.com.gesadmin.dao.PersonaDao;
import pe.com.gesadmin.entity.Persona;
import pe.com.gesadmin.transfer.PersonaTransfer;

public class PersonaDaoImpl implements PersonaDao{
	
	@PersistenceContext(unitName = "gesadminPU")
	EntityManager em;

	@Override
	public List<Persona> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PersonaTransfer> findTransferAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void create(Persona entidad) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Persona entidad) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Persona findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
