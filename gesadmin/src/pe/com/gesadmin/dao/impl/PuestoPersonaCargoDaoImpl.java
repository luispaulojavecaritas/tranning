package pe.com.gesadmin.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import pe.com.gesadmin.dao.PuestoPersonaCargoDao;
import pe.com.gesadmin.entity.PuestoPersonaCargo;

@Stateless
public class PuestoPersonaCargoDaoImpl implements PuestoPersonaCargoDao {
	
	@PersistenceContext(unitName = "gesadminPU")
	EntityManager em;

	@Override
	public List<PuestoPersonaCargo> findAll() {
		// TODO Auto-generated method stub
		String query = "select b FROM PuestoPersonaCargo b";
        TypedQuery<PuestoPersonaCargo> tq = em.createQuery(query, PuestoPersonaCargo.class);
        List<PuestoPersonaCargo> lista = tq.getResultList();
        return lista;
	}
	
	@Override
	public List<PuestoPersonaCargo> findAllActive() {
		// TODO Auto-generated method stub
		String query = "select b FROM PuestoPersonaCargo b where b.estado = 1";
        TypedQuery<PuestoPersonaCargo> tq = em.createQuery(query, PuestoPersonaCargo.class);
        List<PuestoPersonaCargo> lista = tq.getResultList();
        return lista;
	}
	

	@Override
	public void create(PuestoPersonaCargo entidad) {
		// TODO Auto-generated method stub
		em.persist(entidad);
	}

	@Override
	public void update(PuestoPersonaCargo entidad) {
		// TODO Auto-generated method stub
		em.merge(entidad);
	}

	@Override
	public PuestoPersonaCargo findById(Integer id) {
		// TODO Auto-generated method stub
		 return em.find(PuestoPersonaCargo.class, id);
	}

	@Override
	public List<PuestoPersonaCargo> findbyPuestoId(Integer idPuesto) {
		// TODO Auto-generated method stub
		String query = "select b FROM PuestoPersonaCargo b where b.estado = 1 and b.puesto.id = :idPuesto";
        TypedQuery<PuestoPersonaCargo> tq = em.createQuery(query, PuestoPersonaCargo.class);
        tq.setParameter("idPuesto", idPuesto);
        List<PuestoPersonaCargo> lista = tq.getResultList();
        return lista;
	}

	
}