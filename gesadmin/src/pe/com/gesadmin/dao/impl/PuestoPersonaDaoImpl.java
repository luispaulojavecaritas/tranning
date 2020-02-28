package pe.com.gesadmin.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import pe.com.gesadmin.dao.PuestoPersonaDao;
import pe.com.gesadmin.entity.PuestoPersona;
import pe.com.gesadmin.transfer.PuestoPersonaTransfer;

@Stateless
public class PuestoPersonaDaoImpl implements PuestoPersonaDao {
	
	@PersistenceContext(unitName = "gesadminPU")
	EntityManager em;

	@Override
	public List<PuestoPersona> findAll() {
		// TODO Auto-generated method stub
		String query = "select b FROM PuestoPersona b";
        TypedQuery<PuestoPersona> tq = em.createQuery(query, PuestoPersona.class);
        List<PuestoPersona> lista = tq.getResultList();
        return lista;
	}
	
	@Override
	public List<PuestoPersonaTransfer> findTransferAll() {
		// TODO Auto-generated method stub
		return null;
	}
	

	@Override
	public void create(PuestoPersona entidad) {
		// TODO Auto-generated method stub
		em.persist(entidad);
	}

	@Override
	public void update(PuestoPersona entidad) {
		// TODO Auto-generated method stub
		em.merge(entidad);
	}

	@Override
	public PuestoPersona findById(Integer id) {
		// TODO Auto-generated method stub
		 return em.find(PuestoPersona.class, id);
	}

	
}