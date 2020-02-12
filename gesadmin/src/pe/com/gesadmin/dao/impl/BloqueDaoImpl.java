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
		String query = "select new pe.com.gesadmin.entity.Bloque(b.id, b.descripcion, b.estado) FROM Bloque b where b.estado = 1";
        TypedQuery tq = em.createQuery(query, Bloque.class);
        List<Bloque> lista = tq.getResultList();
        em.close();
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
		String query = "select b from Bloque b where b.id = :id";
        TypedQuery<Bloque> typedQuery = em.createQuery(query, Bloque.class);
        typedQuery.setParameter("id", id);
        return typedQuery.getSingleResult();
	}

}
