package pe.com.gesadmin.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import pe.com.gesadmin.dao.PaisDao;
import pe.com.gesadmin.entity.Bloque;
import pe.com.gesadmin.entity.Pais;

@Stateless
public class PaisDaoImpl implements PaisDao{
	
	@PersistenceContext(unitName = "gesadminPU")
	EntityManager em;

	@Override
	public List<Pais> findAll() {
		// TODO Auto-generated method stub        
        String query = "select b FROM Pais b";
        TypedQuery<Pais> tq = em.createQuery(query, Pais.class);
        List<Pais> lista = tq.getResultList();
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
		return em.find(Pais.class, id);
	}


}
