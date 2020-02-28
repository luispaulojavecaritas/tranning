package pe.com.gesadmin.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import pe.com.gesadmin.dao.SituacionOrdenDao;
import pe.com.gesadmin.entity.SituacionOrden;

@Stateless
public class SituacionOrdenDaoImpl implements SituacionOrdenDao {
	
	@PersistenceContext(unitName = "gesadminPU")
	EntityManager em;

	@Override
	public List<SituacionOrden> findAll() {
		// TODO Auto-generated method stub
		String query = "select b FROM SituacionOrden b";
        TypedQuery<SituacionOrden> tq = em.createQuery(query, SituacionOrden.class);
        List<SituacionOrden> lista = tq.getResultList();
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
		return em.find(SituacionOrden.class, id);
	}


}
