package pe.com.gesadmin.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import pe.com.gesadmin.dao.MedidaServicioDao;
import pe.com.gesadmin.entity.MedidaServicio;

@Stateless
public class MedidaServicioDaoImpl implements MedidaServicioDao{
	
	@PersistenceContext(unitName = "gesadminPU")
	EntityManager em;

	@Override
	public List<MedidaServicio> findAll() {
		// TODO Auto-generated method stub
		String query = "select b FROM MedidaServicio b";
        TypedQuery<MedidaServicio> tq = em.createQuery(query, MedidaServicio.class);
        List<MedidaServicio> lista = tq.getResultList();
        return lista;
	}
	
	@Override
	public List<MedidaServicio> findAllActive() {
		// TODO Auto-generated method stub
		String query = "select b FROM MedidaServicio b where b.estado = 1 order by b.periodo.anioFiscal.id, b.periodo.id, b.id desc";
        TypedQuery<MedidaServicio> tq = em.createQuery(query, MedidaServicio.class);
        List<MedidaServicio> lista = tq.getResultList();
        return lista;
	}

	@Override
	public void create(MedidaServicio entidad) {
		// TODO Auto-generated method stub
		em.persist(entidad);
	}

	@Override
	public void update(MedidaServicio entidad) {
		// TODO Auto-generated method stub
		em.merge(entidad);
	}

	@Override
	public MedidaServicio findById(Integer id) {
		// TODO Auto-generated method stub
		return em.find(MedidaServicio.class, id);
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		MedidaServicio medidaServicio = new MedidaServicio();
		medidaServicio = em.getReference(MedidaServicio.class, id);
		em.remove(medidaServicio);
	}

	@Override
	public List<MedidaServicio> findByPeriodoId(Integer idPeriodo) {
		// TODO Auto-generated method stub
		String query = "select b FROM MedidaServicio b where b.estado = 1 and b.periodo.id = :idPeriodo order by b.periodo.anioFiscal.id, b.periodo.id, b.id desc";
        TypedQuery<MedidaServicio> tq = em.createQuery(query, MedidaServicio.class);
        tq.setParameter("idPeriodo", idPeriodo);
        List<MedidaServicio> lista = tq.getResultList();
        return lista;
	}
	
	@Override
	public List<MedidaServicio> findByPeriodoIdAll(Integer idPeriodo) {
		// TODO Auto-generated method stub
		String query = "select b FROM MedidaServicio b where b.periodo.id = :idPeriodo order by b.periodo.anioFiscal.id, b.periodo.id, b.id desc";
        TypedQuery<MedidaServicio> tq = em.createQuery(query, MedidaServicio.class);
        tq.setParameter("idPeriodo", idPeriodo);
        List<MedidaServicio> lista = tq.getResultList();
        return lista;
	}
	
	@Override
	public List<MedidaServicio> findByPeriodoActive() {
		// TODO Auto-generated method stub
		String query = "select b FROM MedidaServicio b where b.estado = 1 and b.periodo.estado = 1 order by b.id desc";
        TypedQuery<MedidaServicio> tq = em.createQuery(query, MedidaServicio.class);
        List<MedidaServicio> lista = tq.getResultList();
        return lista;
	}

	@Override
	public List<MedidaServicio> findByAnioId(Integer idAnioFiscal) {
		// TODO Auto-generated method stub
		String query = "select b FROM MedidaServicio b where b.estado = 1 and b.periodo.anioFiscal.id = :idAnioFiscal order by b.periodo.anioFiscal.id, b.periodo.id, b.id desc";
        TypedQuery<MedidaServicio> tq = em.createQuery(query, MedidaServicio.class);
        tq.setParameter("idAnioFiscal", idAnioFiscal);
        List<MedidaServicio> lista = tq.getResultList();
        return lista;
	}
	
	@Override
	public List<MedidaServicio> findByAnioIdAll(Integer idAnioFiscal) {
		// TODO Auto-generated method stub
		String query = "select b FROM MedidaServicio b where b.periodo.anioFiscal.id = :idAnioFiscal order by b.periodo.anioFiscal.id, b.periodo.id, b.id desc";
        TypedQuery<MedidaServicio> tq = em.createQuery(query, MedidaServicio.class);
        tq.setParameter("idAnioFiscal", idAnioFiscal);
        List<MedidaServicio> lista = tq.getResultList();
        return lista;
	}

	@Override
	public MedidaServicio findByPuestoidPeriodoidTiposervicioid(Integer idPeriodo, Integer idPuesto, Integer idTipoServicio) {
		// TODO Auto-generated method stub
		System.out.println("Periodo id: " + idPeriodo + " Puesto id: " + idPuesto + " Tipo Servicio id: " + idTipoServicio);
		
		String query = "select b FROM MedidaServicio b where b.estado = 1 and b.periodo.id = :idPeriodo and b.puesto.id = :idPuesto and b.tipoServicio.id = :idTipoServicio";
        TypedQuery<MedidaServicio> tq = em.createQuery(query, MedidaServicio.class);
        tq.setParameter("idPeriodo", idPeriodo);
        tq.setParameter("idPuesto", idPuesto);
        tq.setParameter("idTipoServicio", idTipoServicio);
        List<MedidaServicio> lista = tq.getResultList();
        
        if(lista == null || lista.isEmpty() || lista.size() == 0) {
        	return null;
        } else {
        	return lista.get(0);
        } 
        
	}

	@Override
	public List<MedidaServicio> findByPeriodoidTiposervicioid(Integer idPeriodo, Integer idTipoServicio) {
		// TODO Auto-generated method stub
		String query = "select b FROM MedidaServicio b where b.estado = 1 and b.periodo.id = :idPeriodo and b.tipoServicio.id = :idTipoServicio";
        TypedQuery<MedidaServicio> tq = em.createQuery(query, MedidaServicio.class);
        tq.setParameter("idPeriodo", idPeriodo);
        tq.setParameter("idTipoServicio", idTipoServicio);
        List<MedidaServicio> lista = tq.getResultList();
        return lista;
	}


}
