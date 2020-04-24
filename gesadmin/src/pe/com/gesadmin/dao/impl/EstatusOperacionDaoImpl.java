package pe.com.gesadmin.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import pe.com.gesadmin.dao.EstatusOperacionDao;
import pe.com.gesadmin.entity.EstatusOperacion;

@Stateless
public class EstatusOperacionDaoImpl implements EstatusOperacionDao {
	
	@PersistenceContext(unitName = "gesadminPU")
	EntityManager em;

	@Override
	public List<EstatusOperacion> findAll() {
		// TODO Auto-generated method stub
		String query = "select b FROM EstatusOperacion b";
        TypedQuery<EstatusOperacion> tq = em.createQuery(query, EstatusOperacion.class);
        List<EstatusOperacion> lista = tq.getResultList();
        return lista;
	}
	
	@Override
	public List<EstatusOperacion> findAllActive() {
		// TODO Auto-generated method stub
		String query = "select b FROM EstatusOperacion b where b.estado = 1";
        TypedQuery<EstatusOperacion> tq = em.createQuery(query, EstatusOperacion.class);
        List<EstatusOperacion> lista = tq.getResultList();
        return lista;
	}

	@Override
	public void create(EstatusOperacion entidad) {
		// TODO Auto-generated method stub
		em.persist(entidad);
	}

	@Override
	public void update(EstatusOperacion entidad) {
		// TODO Auto-generated method stub
		em.merge(entidad);
	}

	@Override
	public EstatusOperacion findById(Integer id) {
		// TODO Auto-generated method stub
		return em.find(EstatusOperacion.class, id);
	}


}
