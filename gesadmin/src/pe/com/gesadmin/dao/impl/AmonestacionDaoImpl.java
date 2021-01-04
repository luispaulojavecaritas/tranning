package pe.com.gesadmin.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import pe.com.gesadmin.dao.AmonestacionDao;
import pe.com.gesadmin.entity.Amonestacion;

@Stateless
public class AmonestacionDaoImpl implements AmonestacionDao{
	
	@PersistenceContext(unitName = "gesadminPU")
	EntityManager em;

	@Override
	public List<Amonestacion> findAll() {
		// TODO Auto-generated method stub
		String query = "select b FROM Amonestacion b";
        TypedQuery<Amonestacion> tq = em.createQuery(query, Amonestacion.class);
        List<Amonestacion> lista = tq.getResultList();
        return lista;
	}
	
	@Override
	public List<Amonestacion> findAllActive() {
		// TODO Auto-generated method stub
		String query = "select b FROM Amonestacion b where b.estado = 1";
        TypedQuery<Amonestacion> tq = em.createQuery(query, Amonestacion.class);
        List<Amonestacion> lista = tq.getResultList();
        return lista;
	}

	@Override
	public void create(Amonestacion entidad) {
		// TODO Auto-generated method stub
		em.persist(entidad);
	}

	@Override
	public void update(Amonestacion entidad) {
		// TODO Auto-generated method stub
		em.merge(entidad);
	}

	@Override
	public Amonestacion findById(Integer id) {
		// TODO Auto-generated method stub
		return em.find(Amonestacion.class, id);
	}

	@Override
	public List<Amonestacion> findByPeriodoId(Integer idPeriodo) {
		// TODO Auto-generated method stub
		String query = "select b FROM Amonestacion b where b.periodo.id = :idPeriodo";
        TypedQuery<Amonestacion> tq = em.createQuery(query, Amonestacion.class);
        tq.setParameter("idPeriodo", idPeriodo);
        List<Amonestacion> lista = tq.getResultList();
        return lista;
	}

	@Override
	public List<Amonestacion> findByAnioId(Integer idAnio) {
		// TODO Auto-generated method stub
		String query = "select b FROM Amonestacion b where b.periodo.anioFiscal.id = :idAnio";
        TypedQuery<Amonestacion> tq = em.createQuery(query, Amonestacion.class);
        tq.setParameter("idAnio", idAnio);
        List<Amonestacion> lista = tq.getResultList();
        return lista;
	}

}
