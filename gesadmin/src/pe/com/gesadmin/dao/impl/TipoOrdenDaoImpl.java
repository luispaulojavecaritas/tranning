package pe.com.gesadmin.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import pe.com.gesadmin.dao.TipoOrdenDao;
import pe.com.gesadmin.entity.TipoOrden;

@Stateless
public class TipoOrdenDaoImpl implements TipoOrdenDao {
	
	@PersistenceContext(unitName = "gesadminPU")
	EntityManager em;

	@Override
	public List<TipoOrden> findAll() {
		// TODO Auto-generated method stub
		String query = "select b FROM TipoOrden b";
        TypedQuery<TipoOrden> tq = em.createQuery(query, TipoOrden.class);
        List<TipoOrden> lista = tq.getResultList();
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
		return em.find(TipoOrden.class, id);
	}


}
