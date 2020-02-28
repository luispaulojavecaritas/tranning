package pe.com.gesadmin.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import pe.com.gesadmin.dao.OrdenDao;
import pe.com.gesadmin.entity.Orden;
import pe.com.gesadmin.transfer.OrdenTransfer;

@Stateless
public class OrdenDaoImpl implements OrdenDao{

	@PersistenceContext(unitName = "gesadminPU")
	EntityManager em;
	
	@Override
	public List<Orden> findByPeriodoId(Integer idPeriodo) {
		// TODO Auto-generated method stub
		List<Orden> lista = new ArrayList<>();
		String query = "SELECT b FROM Orden b where b.estado = 1 and b.periodo.id = :idPeriodo";
		TypedQuery<Orden> tq = em.createQuery(query, Orden.class);
		tq.setParameter("idPeriodo", idPeriodo);
		lista = tq.getResultList();
		return lista;
	}
	
	@Override
	public List<OrdenTransfer> findTransferAll() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void create(Orden entidad) {
		// TODO Auto-generated method stub
		em.persist(entidad);
	}

	@Override
	public void update(Orden entidad) {
		// TODO Auto-generated method stub
		em.merge(entidad);
	}

	@Override
	public Orden findById(Integer id) {
		// TODO Auto-generated method stub
		return em.find(Orden.class, id);

	}

}
