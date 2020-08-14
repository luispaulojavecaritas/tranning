package pe.com.gesadmin.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import pe.com.gesadmin.dao.AnioFiscalDao;
import pe.com.gesadmin.entity.AnioFiscal;

@Stateless
public class AnioFiscalDaoImpl implements AnioFiscalDao{
	
	@PersistenceContext(unitName = "gesadminPU")
	EntityManager em;

	@Override
	public List<AnioFiscal> findAll() {
		// TODO Auto-generated method stub
		String query = "select b FROM AnioFiscal b order by b.id desc";
        TypedQuery<AnioFiscal> tq = em.createQuery(query, AnioFiscal.class);
        List<AnioFiscal> lista = tq.getResultList();
        return lista;
	}
	
	@Override
	public List<AnioFiscal> findAllActive() {
		// TODO Auto-generated method stub
		String query = "select b FROM AnioFiscal b where b.estado = 1";
        TypedQuery<AnioFiscal> tq = em.createQuery(query, AnioFiscal.class);
        List<AnioFiscal> lista = tq.getResultList();
        return lista;
	}

	@Override
	public void create(AnioFiscal entidad) {
		// TODO Auto-generated method stub
		em.persist(entidad);
	}

	@Override
	public void update(AnioFiscal entidad) {
		// TODO Auto-generated method stub
		em.merge(entidad);
	}

	@Override
	public AnioFiscal findById(Integer id) {
		// TODO Auto-generated method stub
		return em.find(AnioFiscal.class, id);
	}

	@Override
	public List<AnioFiscal> findByDescripcion(String anioFiscal) {
		// TODO Auto-generated method stub
		String query = "select b FROM AnioFiscal b where b.descripcion = :anioFiscal";
        TypedQuery<AnioFiscal> tq = em.createQuery(query, AnioFiscal.class);
        tq.setParameter(anioFiscal, "anioFiscal");
        List<AnioFiscal> lista = tq.getResultList();
        return lista;
	}

	@Override
	public void updateEstado() {
		// TODO Auto-generated method stub
		String query = "update anio_fiscal set estado = 0";
		em.createNativeQuery(query).executeUpdate();
	}

}
