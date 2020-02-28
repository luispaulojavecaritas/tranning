package pe.com.gesadmin.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import pe.com.gesadmin.dao.TipoEstatusDao;
import pe.com.gesadmin.entity.TipoEstatus;

@Stateless
public class TipoEstatusDaoImpl implements TipoEstatusDao {
	
	@PersistenceContext(unitName = "gesadminPU")
	EntityManager em;

	@Override
	public List<TipoEstatus> findAll() {
		// TODO Auto-generated method stub
		String query = "select b FROM TipoEstatus b";
        TypedQuery<TipoEstatus> tq = em.createQuery(query, TipoEstatus.class);
        List<TipoEstatus> lista = tq.getResultList();
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
		return em.find(TipoEstatus.class, id);
	}


}
