package pe.com.gesadmin.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import pe.com.gesadmin.dao.SexoDao;
import pe.com.gesadmin.entity.Sexo;

public class SexoDaoImpl implements SexoDao {
	
	@PersistenceContext(unitName = "gesadminPU")
	EntityManager em;

	@Override
	public List<Sexo> findAll() {
		// TODO Auto-generated method stub
		String query = "select new pe.com.gesadmin.entity.Sexo(b.id, b.descripcion, b.estado) FROM Sexo b where b.estado = 1";
        TypedQuery tq = em.createQuery(query, Sexo.class);
        List<Sexo> lista = tq.getResultList();
        em.close();
        return lista;
	}

	@Override
	public void create(Sexo entidad) {
		// TODO Auto-generated method stub
		em.persist(entidad);
	}

	@Override
	public void update(Sexo entidad) {
		// TODO Auto-generated method stub
		em.merge(entidad);
	}

	@Override
	public Sexo findById(Integer id) {
		// TODO Auto-generated method stub
		String query = "select b from Sexo b where b.id = :id";
        TypedQuery<Sexo> typedQuery = em.createQuery(query, Sexo.class);
        typedQuery.setParameter("id", id);
        return typedQuery.getSingleResult();
	}


}
