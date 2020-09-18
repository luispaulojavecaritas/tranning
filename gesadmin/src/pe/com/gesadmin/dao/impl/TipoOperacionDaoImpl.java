package pe.com.gesadmin.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import pe.com.gesadmin.dao.TipoOperacionDao;
import pe.com.gesadmin.entity.TipoOperacion;

@Stateless
public class TipoOperacionDaoImpl implements TipoOperacionDao {
	
	@PersistenceContext(unitName = "gesadminPU")
	EntityManager em;

	@Override
	public List<TipoOperacion> findAll() {
		// TODO Auto-generated method stub
		String query = "select b FROM TipoOperacion b order by b.id desc";
        TypedQuery<TipoOperacion> tq = em.createQuery(query, TipoOperacion.class);
        List<TipoOperacion> lista = tq.getResultList();
        return lista;
	}
	
	@Override
	public List<TipoOperacion> findAllActive() {
		// TODO Auto-generated method stub
		String query = "select b FROM TipoOperacion b where b.estado = 1";
        TypedQuery<TipoOperacion> tq = em.createQuery(query, TipoOperacion.class);
        List<TipoOperacion> lista = tq.getResultList();
        return lista;
	}

	@Override
	public void create(TipoOperacion entidad) {
		// TODO Auto-generated method stub
		em.persist(entidad);
	}

	@Override
	public void update(TipoOperacion entidad) {
		// TODO Auto-generated method stub
		em.merge(entidad);
	}

	@Override
	public TipoOperacion findById(Integer id) {
		// TODO Auto-generated method stub
		return em.find(TipoOperacion.class, id);
	}


}
