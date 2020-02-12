package pe.com.gesadmin.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import pe.com.gesadmin.dao.TipoOrdenDao;
import pe.com.gesadmin.entity.TipoOrden;

public class TipoOrdenDaoImpl implements TipoOrdenDao {
	
	@PersistenceContext(unitName = "gesadminPU")
	EntityManager em;

	@Override
	public List<TipoOrden> findAll() {
		// TODO Auto-generated method stub
		String query = "select new pe.com.gesadmin.entity.TipoOrden(b.id, b.descripcion, b.estado) FROM TipoOrden b where b.estado = 1";
        TypedQuery tq = em.createQuery(query, TipoOrden.class);
        List<TipoOrden> lista = tq.getResultList();
        em.close();
        return lista;
	}

	@Override
	public void create(TipoOrden entidad) {
		// TODO Auto-generated method stub
		em.persist(entidad);
	}

	@Override
	public void update(TipoOrden entidad) {
		// TODO Auto-generated method stub
		em.merge(entidad);
	}

	@Override
	public TipoOrden findById(Integer id) {
		// TODO Auto-generated method stub
		String query = "select b from TipoOrden b where b.id = :id";
        TypedQuery<TipoOrden> typedQuery = em.createQuery(query, TipoOrden.class);
        typedQuery.setParameter("id", id);
        return typedQuery.getSingleResult();
	}


}
