package pe.com.gesadmin.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import pe.com.gesadmin.dao.PaisDao;
import pe.com.gesadmin.entity.Pais;

public class PaisDaoImpl implements PaisDao{
	
	@PersistenceContext(unitName = "gesadminPU")
	EntityManager em;

	@Override
	public List<Pais> findAll() {
		// TODO Auto-generated method stub
		String query = "select new pe.com.gesadmin.entity.Pais(b.id, b.descripcion, b.estado) FROM Pais b where b.estado = 1";
        TypedQuery tq = em.createQuery(query, Pais.class);
        List<Pais> lista = tq.getResultList();
        em.close();
        return lista;
	}

	@Override
	public void create(Pais entidad) {
		// TODO Auto-generated method stub
		em.persist(entidad);
	}

	@Override
	public void update(Pais entidad) {
		// TODO Auto-generated method stub
		em.merge(entidad);
	}

	@Override
	public Pais findById(Integer id) {
		// TODO Auto-generated method stub
		String query = "select b from Pais b where b.id = :id";
        TypedQuery<Pais> typedQuery = em.createQuery(query, Pais.class);
        typedQuery.setParameter("id", id);
        return typedQuery.getSingleResult();
	}


}
