package pe.com.gesadmin.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import pe.com.gesadmin.dao.EgresoDao;
import pe.com.gesadmin.entity.Egreso;

public class EgresoDaoImpl implements EgresoDao{

	@PersistenceContext(unitName = "gesadminPU")
	EntityManager em;

	@Override
	public List<Egreso> findAll() {
		// TODO Auto-generated method stub
		String query = "select new pe.com.gesadmin.entity.Egreso(b.id, b.descripcion, b.estado) FROM Egreso b where b.estado = 1";
        TypedQuery tq = em.createQuery(query, Egreso.class);
        List<Egreso> lista = tq.getResultList();
        em.close();
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
		String query = "select b from Egreso b where b.id = :id";
        TypedQuery<Egreso> typedQuery = em.createQuery(query, Egreso.class);
        typedQuery.setParameter("id", id);
        return typedQuery.getSingleResult();
	}


}
