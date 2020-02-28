package pe.com.gesadmin.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import pe.com.gesadmin.dao.IngresoDao;
import pe.com.gesadmin.entity.Ingreso;

@Stateless
public class IngresoDaoImpl implements IngresoDao{

	@PersistenceContext(unitName = "gesadminPU")
	EntityManager em;

	@Override
	public List<Ingreso> findAll() {
		// TODO Auto-generated method stub
		String query = "select b FROM Ingreso b";
        TypedQuery<Ingreso> tq = em.createQuery(query, Ingreso.class);
        List<Ingreso> lista = tq.getResultList();
        return lista;
	}

	@Override
	public void create(Ingreso entidad) {
		// TODO Auto-generated method stub
		em.persist(entidad);
	}

	@Override
	public void update(Ingreso entidad) {
		// TODO Auto-generated method stub
		em.merge(entidad);
	}

	@Override
	public Ingreso findById(Integer id) {
		// TODO Auto-generated method stub
		return em.find(Ingreso.class, id);

	}


}
