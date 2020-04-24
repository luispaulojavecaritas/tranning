package pe.com.gesadmin.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import pe.com.gesadmin.dao.CargoDao;
import pe.com.gesadmin.entity.Cargo;

@Stateless
public class CargoDaoImpl implements CargoDao {
	
	@PersistenceContext(unitName = "gesadminPU")
	EntityManager em;

	@Override
	public List<Cargo> findAll() {
		// TODO Auto-generated method stub
		String query = "select b FROM Cargo b";
        TypedQuery<Cargo> tq = em.createQuery(query, Cargo.class);
        List<Cargo> lista = tq.getResultList();
        return lista;
	}
	
	@Override
	public List<Cargo> findAllActive() {
		// TODO Auto-generated method stub
		String query = "select b FROM Cargo b where b.estado = 1";
        TypedQuery<Cargo> tq = em.createQuery(query, Cargo.class);
        List<Cargo> lista = tq.getResultList();
        return lista;
	}

	@Override
	public void create(Cargo entidad) {
		// TODO Auto-generated method stub
		em.persist(entidad);
	}

	@Override
	public void update(Cargo entidad) {
		// TODO Auto-generated method stub
		em.merge(entidad);
	}

	@Override
	public Cargo findById(Integer id) {
		// TODO Auto-generated method stub
		return em.find(Cargo.class, id);
	}


}
