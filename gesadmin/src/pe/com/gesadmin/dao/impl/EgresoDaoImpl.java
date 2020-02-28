package pe.com.gesadmin.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import pe.com.gesadmin.dao.EgresoDao;
import pe.com.gesadmin.entity.Egreso;

@Stateless
public class EgresoDaoImpl implements EgresoDao{

	@PersistenceContext(unitName = "gesadminPU")
	EntityManager em;

	@Override
	public List<Egreso> findAll() {
		// TODO Auto-generated method stub
		String query = "select b FROM Egreso b";
        TypedQuery<Egreso> tq = em.createQuery(query, Egreso.class);
        List<Egreso> lista = tq.getResultList();
        return lista;
	}

	@Override
	public void create(Egreso entidad) {
		// TODO Auto-generated method stub
		em.persist(entidad);
	}

	@Override
	public void update(Egreso entidad) {
		// TODO Auto-generated method stub
		em.merge(entidad);
	}

	@Override
	public Egreso findById(Integer id) {
		// TODO Auto-generated method stub
		return em.find(Egreso.class, id);

	}


}
