package pe.com.gesadmin.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import pe.com.gesadmin.dao.MedidaAguaDao;
import pe.com.gesadmin.entity.MedidaAgua;

@Stateless
public class MedidaAguaDaoImpl implements MedidaAguaDao{
	
	@PersistenceContext(unitName = "gesadminPU")
	EntityManager em;

	@Override
	public List<MedidaAgua> findAll() {
		// TODO Auto-generated method stub
		String query = "select b FROM MedidaAgua b";
        TypedQuery<MedidaAgua> tq = em.createQuery(query, MedidaAgua.class);
        List<MedidaAgua> lista = tq.getResultList();
        return lista;
	}
	
	@Override
	public List<MedidaAgua> findAllActive() {
		// TODO Auto-generated method stub
		String query = "select b FROM MedidaAgua b where b.estado = 1";
        TypedQuery<MedidaAgua> tq = em.createQuery(query, MedidaAgua.class);
        List<MedidaAgua> lista = tq.getResultList();
        return lista;
	}

	@Override
	public void create(MedidaAgua entidad) {
		// TODO Auto-generated method stub
		em.persist(entidad);
	}

	@Override
	public void update(MedidaAgua entidad) {
		// TODO Auto-generated method stub
		em.merge(entidad);
	}

	@Override
	public MedidaAgua findById(Integer id) {
		// TODO Auto-generated method stub
		return em.find(MedidaAgua.class, id);
	}

}
