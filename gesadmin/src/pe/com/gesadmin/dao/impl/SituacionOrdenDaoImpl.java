package pe.com.gesadmin.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import pe.com.gesadmin.dao.SituacionOrdenDao;
import pe.com.gesadmin.entity.SituacionOrden;

public class SituacionOrdenDaoImpl implements SituacionOrdenDao {
	
	@PersistenceContext(unitName = "gesadminPU")
	EntityManager em;

	@Override
	public List<SituacionOrden> findAll() {
		// TODO Auto-generated method stub
		String query = "select new pe.com.gesadmin.entity.SituacionOrden(b.id, b.descripcion, b.estado) FROM SituacionOrden b where b.estado = 1";
        TypedQuery tq = em.createQuery(query, SituacionOrden.class);
        List<SituacionOrden> lista = tq.getResultList();
        em.close();
        return lista;
	}

	@Override
	public void create(SituacionOrden entidad) {
		// TODO Auto-generated method stub
		em.persist(entidad);
	}

	@Override
	public void update(SituacionOrden entidad) {
		// TODO Auto-generated method stub
		em.merge(entidad);
	}

	@Override
	public SituacionOrden findById(Integer id) {
		// TODO Auto-generated method stub
		String query = "select b from SituacionOrden b where b.id = :id";
        TypedQuery<SituacionOrden> typedQuery = em.createQuery(query, SituacionOrden.class);
        typedQuery.setParameter("id", id);
        return typedQuery.getSingleResult();
	}


}
