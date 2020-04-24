package pe.com.gesadmin.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import pe.com.gesadmin.dao.PersonaDao;
import pe.com.gesadmin.entity.Persona;
import pe.com.gesadmin.transfer.PersonaTransfer;

@Stateless
public class PersonaDaoImpl implements PersonaDao{
	
	@PersistenceContext(unitName = "gesadminPU")
	EntityManager em;

	@Override
	public List<Persona> findAll() {
		// TODO Auto-generated method stub
		List<Persona> lista = new ArrayList<>();
		String query = "SELECT b FROM Persona b";
		TypedQuery<Persona> tq = em.createQuery(query, Persona.class);
		lista = tq.getResultList();
		return lista;
	}
	
	@Override
	public List<Persona> findAllActive() {
		// TODO Auto-generated method stub
		List<Persona> lista = new ArrayList<>();
		String query = "SELECT b FROM Persona b where b.estado = 1";
		TypedQuery<Persona> tq = em.createQuery(query, Persona.class);
		lista = tq.getResultList();
		return lista;
	}
	
	@Override
	public List<Persona> findByNroDoc(String nroDoc) {
		// TODO Auto-generated method stub
		List<Persona> lista = new ArrayList<>();
		String query = "SELECT b FROM Persona b where b.estado = 1 and b.nroDocumento = :nroDoc";
		TypedQuery<Persona> tq = em.createQuery(query, Persona.class);
		tq.setParameter("nroDoc", nroDoc);
		lista = tq.getResultList();
		return lista;
	}

	@Override
	public List<PersonaTransfer> findTransferAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void create(Persona entidad) {
		// TODO Auto-generated method stub
		em.persist(entidad);
	}

	@Override
	public void update(Persona entidad) {
		// TODO Auto-generated method stub
		em.merge(entidad);
	}

	@Override
	public Persona findById(Integer id) {
		// TODO Auto-generated method stub
		return em.find(Persona.class, id);
	}


}
