package pe.com.gesadmin.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import pe.com.gesadmin.dao.PeriodoDao;
import pe.com.gesadmin.entity.Periodo;

@Stateless
public class PeriodoDaoImpl implements PeriodoDao{
	
	@PersistenceContext(unitName = "gesadminPU")
	EntityManager em;

	@Override
	public List<Periodo> findAll() {
		// TODO Auto-generated method stub
		String query = "select b FROM Periodo b";
        TypedQuery<Periodo> tq = em.createQuery(query, Periodo.class);
        List<Periodo> lista = tq.getResultList();
        return lista;
	}
	
	@Override
	public List<Periodo> findAllActive() {
		// TODO Auto-generated method stub
		String query = "select b FROM Periodo b where b.estado = 1";
        TypedQuery<Periodo> tq = em.createQuery(query, Periodo.class);
        List<Periodo> lista = tq.getResultList();
        return lista;
	}

	@Override
	public void create(Periodo entidad) {
		// TODO Auto-generated method stub
		em.persist(entidad);
	}

	@Override
	public void update(Periodo entidad) {
		// TODO Auto-generated method stub
		em.merge(entidad);
	}

	@Override
	public Periodo findById(Integer id) {
		// TODO Auto-generated method stub
		return em.find(Periodo.class, id);
	}

	@Override
	public void updateEstado() {
		// TODO Auto-generated method stub
		String query = "update periodo set estado = 0";
		em.createNativeQuery(query).executeUpdate();
		
	}

	@Override
	public void updateEstadoAfterCreateAnioFiscal() {
		// TODO Auto-generated method stub
		String query = "update periodo set estado = 0  where id_anio_fiscal = (select af.id from anio_fiscal af where af.estado = 0)";
		em.createNativeQuery(query).executeUpdate();		
	}

	@Override
	public List<Periodo> findByIdAnioFiscal(Integer idAnioFiscal) {
		// TODO Auto-generated method stub
		String query = "select b FROM Periodo b where b.anioFiscal.id = :idAnioFiscal";
        TypedQuery<Periodo> tq = em.createQuery(query, Periodo.class);
        tq.setParameter("idAnioFiscal", idAnioFiscal);
        List<Periodo> lista = tq.getResultList();
        return lista;
	}
	
	


}
