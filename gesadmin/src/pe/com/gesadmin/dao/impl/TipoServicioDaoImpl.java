package pe.com.gesadmin.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import pe.com.gesadmin.dao.TipoServicioDao;
import pe.com.gesadmin.entity.TipoServicio;

@Stateless
public class TipoServicioDaoImpl implements TipoServicioDao{
	
	@PersistenceContext(unitName = "gesadminPU")
	EntityManager em;

	@Override
	public List<TipoServicio> findAll() {
		// TODO Auto-generated method stub        
        String query = "select b FROM TipoServicio b";
        TypedQuery<TipoServicio> tq = em.createQuery(query, TipoServicio.class);
        List<TipoServicio> lista = tq.getResultList();
        return lista;
	}
	
	@Override
	public List<TipoServicio> findAllActive() {
		// TODO Auto-generated method stub        
        String query = "select b FROM TipoServicio b where b.estado = 1";
        TypedQuery<TipoServicio> tq = em.createQuery(query, TipoServicio.class);
        List<TipoServicio> lista = tq.getResultList();
        return lista;
	}

	@Override
	public void create(TipoServicio entidad) {
		// TODO Auto-generated method stub
		em.persist(entidad);
	}

	@Override
	public void update(TipoServicio entidad) {
		// TODO Auto-generated method stub
		em.merge(entidad);
	}

	@Override
	public TipoServicio findById(Integer id) {
		// TODO Auto-generated method stub
		return em.find(TipoServicio.class, id);
	}


}
