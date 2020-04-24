package pe.com.gesadmin.service.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import pe.com.gesadmin.dao.PersonaDao;
import pe.com.gesadmin.dao.impl.PersonaDaoImpl;
import pe.com.gesadmin.entity.Persona;
import pe.com.gesadmin.service.PersonaService;
import pe.com.gesadmin.transfer.PersonaTransfer;

@Stateless
public class PersonaServiceImpl implements PersonaService {
	
	@EJB
	PersonaDao personaDao = new PersonaDaoImpl();

	@Override
	public List<Persona> listar() {
		// TODO Auto-generated method stub
		return personaDao.findAll();
	}
	
	@Override
	public List<Persona> listarActivo() {
		// TODO Auto-generated method stub
		return personaDao.findAllActive();
	}

	@Override
	public List<Persona> listarPorNroDoc(String nroDoc) {
		// TODO Auto-generated method stub
		return personaDao.findByNroDoc(nroDoc);
	}

	@Override
	public List<PersonaTransfer> listarTransfer() {
		// TODO Auto-generated method stub
		return personaDao.findTransferAll();
	}

	@Override
	public void crear(Persona entidad) {
		// TODO Auto-generated method stub
		personaDao.create(entidad);
	}

	@Override
	public void actualizar(Persona entidad) {
		// TODO Auto-generated method stub
		personaDao.update(entidad);
	}

	@Override
	public Persona recuperar(Integer id) {
		// TODO Auto-generated method stub
		return personaDao.findById(id);
	}

}
