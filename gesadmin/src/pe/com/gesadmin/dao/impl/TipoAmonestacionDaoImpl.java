package pe.com.gesadmin.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import pe.com.gesadmin.dao.TipoAmonestacionDao;
import pe.com.gesadmin.entity.TipoAmonestacion;

@Stateless
public class TipoAmonestacionDaoImpl implements TipoAmonestacionDao{
	
	@PersistenceContext(unitName = "gesadminPU")
	EntityManager em;

	@Override
	public List<TipoAmonestacion> findAll() {
		// TODO Auto-generated method stub
		String query = "select b FROM TipoAmonestacion b";
        TypedQuery<TipoAmonestacion> tq = em.createQuery(query, TipoAmonestacion.class);
        List<TipoAmonestacion> lista = tq.getResultList();
        return lista;
	}
	
	@Override
	public List<TipoAmonestacion> findAllActive() {
		// TODO Auto-generated method stub
		String query = "select b FROM TipoAmonestacion b where b.estado = 1";
        TypedQuery<TipoAmonestacion> tq = em.createQuery(query, TipoAmonestacion.class);
        List<TipoAmonestacion> lista = tq.getResultList();
        return lista;
	}

	@Override
	public void create(TipoAmonestacion entidad) {
		// TODO Auto-generated method stub
		em.persist(entidad);
	}

	@Override
	public void update(TipoAmonestacion entidad) {
		// TODO Auto-generated method stub
		em.merge(entidad);
	}

	@Override
	public TipoAmonestacion findById(Integer id) {
		// TODO Auto-generated method stub
		return em.find(TipoAmonestacion.class, id);
	}
	
	@Override
	public List<TipoAmonestacion> findByDescripcion(String descripcion) {
		// TODO Auto-generated method stub
		String query = "select b FROM TipoAmonestacion b where b.descripcion = :descripcion";
        TypedQuery<TipoAmonestacion> tq = em.createQuery(query, TipoAmonestacion.class);
        tq.setParameter(descripcion, "descripcion");
        List<TipoAmonestacion> lista = tq.getResultList();
        return lista;
	}

}
