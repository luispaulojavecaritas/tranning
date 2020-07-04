package pe.com.gesadmin.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import pe.com.gesadmin.dao.MedidaLuzDao;
import pe.com.gesadmin.entity.MedidaLuz;

@Stateless
public class MedidaLuzDaoImpl implements MedidaLuzDao{
	
	@PersistenceContext(unitName = "gesadminPU")
	EntityManager em;

	@Override
	public List<MedidaLuz> findAll() {
		// TODO Auto-generated method stub
		String query = "select b FROM MedidaLuz b";
        TypedQuery<MedidaLuz> tq = em.createQuery(query, MedidaLuz.class);
        List<MedidaLuz> lista = tq.getResultList();
        return lista;
	}
	
	@Override
	public List<MedidaLuz> findAllActive() {
		// TODO Auto-generated method stub
		String query = "select b FROM MedidaLuz b where b.estado = 1";
        TypedQuery<MedidaLuz> tq = em.createQuery(query, MedidaLuz.class);
        List<MedidaLuz> lista = tq.getResultList();
        return lista;
	}

	@Override
	public void create(MedidaLuz entidad) {
		// TODO Auto-generated method stub
		em.persist(entidad);
	}

	@Override
	public void update(MedidaLuz entidad) {
		// TODO Auto-generated method stub
		em.merge(entidad);
	}

	@Override
	public MedidaLuz findById(Integer id) {
		// TODO Auto-generated method stub
		return em.find(MedidaLuz.class, id);
	}

}
