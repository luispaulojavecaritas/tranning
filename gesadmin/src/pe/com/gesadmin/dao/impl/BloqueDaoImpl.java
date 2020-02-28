package pe.com.gesadmin.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import pe.com.gesadmin.dao.BloqueDao;
import pe.com.gesadmin.entity.Bloque;

@Stateless
public class BloqueDaoImpl implements BloqueDao{
	
	@PersistenceContext(unitName = "gesadminPU")
	EntityManager em;

	@Override
	public List<Bloque> findAll() {
		// TODO Auto-generated method stub
		String query = "select b FROM Bloque b";
        TypedQuery<Bloque> tq = em.createQuery(query, Bloque.class);
        List<Bloque> lista = tq.getResultList();
        return lista;
	}

	@Override
	public void create(Bloque entidad) {
		// TODO Auto-generated method stub
		em.persist(entidad);
	}

	@Override
	public void update(Bloque entidad) {
		// TODO Auto-generated method stub
		em.merge(entidad);
	}

	@Override
	public Bloque findById(Integer id) {
		// TODO Auto-generated method stub
		return em.find(Bloque.class, id);
	}

}
