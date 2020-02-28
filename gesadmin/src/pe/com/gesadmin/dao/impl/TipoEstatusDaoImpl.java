package pe.com.gesadmin.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import pe.com.gesadmin.dao.TipoEstatusDao;
import pe.com.gesadmin.entity.TipoEstatus;

public class TipoEstatusDaoImpl implements TipoEstatusDao {
	
	@PersistenceContext(unitName = "gesadminPU")
	EntityManager em;

	@Override
	public List<TipoEstatus> findAll() {
		// TODO Auto-generated method stub
		String query = "select new pe.com.gesadmin.entity.TipoEstatus(b.id, b.descripcion, b.estado) FROM TipoEstatus b where b.estado = 1";
        TypedQuery tq = em.createQuery(query, TipoEstatus.class);
        List<TipoEstatus> lista = tq.getResultList();
        em.close();
        return lista;
	}

	@Override
	public void create(TipoEstatus entidad) {
		// TODO Auto-generated method stub
		em.persist(entidad);
	}

	@Override
	public void update(TipoEstatus entidad) {
		// TODO Auto-generated method stub
		em.merge(entidad);
	}

	@Override
	public TipoEstatus findById(Integer id) {
		// TODO Auto-generated method stub
		String query = "select b from TipoEstatus b where b.id = :id";
        TypedQuery<TipoEstatus> typedQuery = em.createQuery(query, TipoEstatus.class);
        typedQuery.setParameter("id", id);
        return typedQuery.getSingleResult();
	}


}
