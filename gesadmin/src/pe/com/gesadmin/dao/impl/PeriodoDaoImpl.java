package pe.com.gesadmin.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import pe.com.gesadmin.dao.PeriodoDao;
import pe.com.gesadmin.entity.Periodo;

@Stateless
public class PeriodoDaoImpl implements PeriodoDao{
	
	@PersistenceContext(unitName = "gesadminPU")
	EntityManager em;

	@Override
	public List<Periodo> findAll() {
		// TODO Auto-generated method stub
		String query = "select b FROM Periodo b";
        TypedQuery<Periodo> tq = em.createQuery(query, Periodo.class);
        List<Periodo> lista = tq.getResultList();
        return lista;
	}

	@Override
	public void create(Periodo entidad) {
		// TODO Auto-generated method stub
		em.persist(entidad);
	}

	@Override
	public void update(Periodo entidad) {
		// TODO Auto-generated method stub
		em.merge(entidad);
	}

	@Override
	public Periodo findById(Integer id) {
		// TODO Auto-generated method stub
		return em.find(Periodo.class, id);
	}


}
