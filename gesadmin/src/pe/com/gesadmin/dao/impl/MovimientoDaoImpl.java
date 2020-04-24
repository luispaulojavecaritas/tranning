package pe.com.gesadmin.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import pe.com.gesadmin.dao.MovimientoDao;
import pe.com.gesadmin.entity.Movimiento;

@Stateless
public class MovimientoDaoImpl implements MovimientoDao{
	
	@PersistenceContext(unitName = "gesadminPU")
	EntityManager em;

	@Override
	public List<Movimiento> findByPeriodo(String periodo) {
		// TODO Auto-generated method stub
		List<Movimiento> lista = new ArrayList<>();
		String query = "SELECT b FROM Movimiento b where b.estado = 1 and b.periodo = :periodo";
		TypedQuery<Movimiento> tq = em.createQuery(query, Movimiento.class);
		tq.setParameter("periodo", periodo);
		lista = tq.getResultList();
		return lista;
	}
	
	@Override
	public List<Movimiento> findAll() {
		// TODO Auto-generated method stub
		List<Movimiento> lista = new ArrayList<>();
		String query = "SELECT b FROM Movimiento b";
		TypedQuery<Movimiento> tq = em.createQuery(query, Movimiento.class);
		lista = tq.getResultList();
		return lista;
	}
	
	@Override
	public List<Movimiento> findAllActive() {
		// TODO Auto-generated method stub
		List<Movimiento> lista = new ArrayList<>();
		String query = "SELECT b FROM Movimiento b where b.estado = 1";
		TypedQuery<Movimiento> tq = em.createQuery(query, Movimiento.class);
		lista = tq.getResultList();
		return lista;
	}

	@Override
	public void create(Movimiento entidad) {
		// TODO Auto-generated method stub
		em.persist(entidad);
	}

	@Override
	public void update(Movimiento entidad) {
		// TODO Auto-generated method stub
		em.merge(entidad);
	}

	@Override
	public Movimiento findById(Integer id) {
		// TODO Auto-generated method stub
		return em.find(Movimiento.class, id);

	}

}
