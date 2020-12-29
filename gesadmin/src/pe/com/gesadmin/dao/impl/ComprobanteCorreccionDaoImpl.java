package pe.com.gesadmin.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import pe.com.gesadmin.dao.ComprobanteCorreccionDao;
import pe.com.gesadmin.entity.ComprobanteCorreccion;

@Stateless
public class ComprobanteCorreccionDaoImpl implements ComprobanteCorreccionDao {
	
	@PersistenceContext(unitName = "gesadminPU")
	EntityManager em;

	@Override
	public List<ComprobanteCorreccion> findAll() {
		// TODO Auto-generated method stub
		String query = "select b FROM ComprobanteCorreccion b order by b.id desc";
        TypedQuery<ComprobanteCorreccion> tq = em.createQuery(query, ComprobanteCorreccion.class);
        List<ComprobanteCorreccion> lista = tq.getResultList();
        return lista;
	}
	
	@Override
	public List<ComprobanteCorreccion> findAllActive() {
		// TODO Auto-generated method stub
		String query = "select b FROM ComprobanteCorreccion b where b.estado = 1";
        TypedQuery<ComprobanteCorreccion> tq = em.createQuery(query, ComprobanteCorreccion.class);
        List<ComprobanteCorreccion> lista = tq.getResultList();
        return lista;
	}

	@Override
	public void create(ComprobanteCorreccion entidad) {
		// TODO Auto-generated method stub
		em.persist(entidad);
	}

	@Override
	public void update(ComprobanteCorreccion entidad) {
		// TODO Auto-generated method stub
		em.merge(entidad);
	}

	@Override
	public ComprobanteCorreccion findById(Integer id) {
		// TODO Auto-generated method stub
		return em.find(ComprobanteCorreccion.class, id);
	}


}
