package pe.com.gesadmin.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import pe.com.gesadmin.dao.PuestoDao;
import pe.com.gesadmin.entity.Puesto;
import pe.com.gesadmin.transfer.PuestoTransfer;

@Stateless
public class PuestoDaoImpl implements PuestoDao {
	
	@PersistenceContext(unitName = "gesadminPU")
	EntityManager em;

	@Override
	public List<Puesto> findAll() {
		// TODO Auto-generated method stub
		String query = "select b FROM Puesto b";
        TypedQuery<Puesto> tq = em.createQuery(query, Puesto.class);
        List<Puesto> lista = tq.getResultList();
        return lista;
	}
	
	@Override
	public List<PuestoTransfer> findTransferAll() {
		// TODO Auto-generated method stub
		return null;
	}
	

	@Override
	public void create(Puesto entidad) {
		// TODO Auto-generated method stub
		em.persist(entidad);
	}

	@Override
	public void update(Puesto entidad) {
		// TODO Auto-generated method stub
		em.merge(entidad);
	}

	@Override
	public Puesto findById(Integer id) {
		// TODO Auto-generated method stub
		 return em.find(Puesto.class, id);
	}

	
}