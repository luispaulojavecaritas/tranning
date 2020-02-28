package pe.com.gesadmin.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import pe.com.gesadmin.dao.IngresoDao;
import pe.com.gesadmin.entity.Ingreso;

public class IngresoDaoImpl implements IngresoDao{

	@PersistenceContext(unitName = "gesadminPU")
	EntityManager em;

	@Override
	public List<Ingreso> findAll() {
		// TODO Auto-generated method stub
		String query = "select new pe.com.gesadmin.entity.Ingreso(b.id, b.descripcion, b.estado) FROM Ingreso b where b.estado = 1";
        TypedQuery tq = em.createQuery(query, Ingreso.class);
        List<Ingreso> lista = tq.getResultList();
        em.close();
        return lista;
	}

	@Override
	public void create(Ingreso entidad) {
		// TODO Auto-generated method stub
		em.persist(entidad);
	}

	@Override
	public void update(Ingreso entidad) {
		// TODO Auto-generated method stub
		em.merge(entidad);
	}

	@Override
	public Ingreso findById(Integer id) {
		// TODO Auto-generated method stub
		String query = "select b from Ingreso b where b.id = :id";
        TypedQuery<Ingreso> typedQuery = em.createQuery(query, Ingreso.class);
        typedQuery.setParameter("id", id);
        return typedQuery.getSingleResult();
	}


}
