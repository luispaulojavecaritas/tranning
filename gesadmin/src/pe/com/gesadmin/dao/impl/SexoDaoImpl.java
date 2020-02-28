package pe.com.gesadmin.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import pe.com.gesadmin.dao.SexoDao;
import pe.com.gesadmin.entity.Sexo;

@Stateless
public class SexoDaoImpl implements SexoDao {
	
	@PersistenceContext(unitName = "gesadminPU")
	EntityManager em;

	@Override
	public List<Sexo> findAll() {
		// TODO Auto-generated method stub
		String query = "select b FROM Sexo b";
        TypedQuery<Sexo> tq = em.createQuery(query, Sexo.class);
        List<Sexo> lista = tq.getResultList();
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
		return em.find(Sexo.class, id);
	}


}
