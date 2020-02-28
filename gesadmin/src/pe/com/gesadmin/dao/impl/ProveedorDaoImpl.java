package pe.com.gesadmin.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import pe.com.gesadmin.dao.ProveedorDao;
import pe.com.gesadmin.entity.Proveedor;

@Stateless
public class ProveedorDaoImpl implements ProveedorDao{
	
	@PersistenceContext(unitName = "gesadminPU")
	EntityManager em;

	@Override
	public List<Proveedor> findAll() {
		// TODO Auto-generated method stub
		String query = "select b FROM Proveedor b";
        TypedQuery<Proveedor> tq = em.createQuery(query, Proveedor.class);
        List<Proveedor> lista = tq.getResultList();
        return lista;
	}

	@Override
	public void create(Proveedor entidad) {
		// TODO Auto-generated method stub
		em.persist(entidad);
	}

	@Override
	public void update(Proveedor entidad) {
		// TODO Auto-generated method stub
		em.merge(entidad);
	}

	@Override
	public Proveedor findById(Integer id) {
		// TODO Auto-generated method stub
		 return em.find(Proveedor.class, id);
	}

	@Override
	public List<Proveedor> findByRuc(String ruc) {
		// TODO Auto-generated method stub
		String query = "select b FROM Proveedor b where b.estado = 1 and b.ruc = :ruc";
        TypedQuery<Proveedor> tq = em.createQuery(query, Proveedor.class);
        tq.setParameter("ruc", ruc);
        return tq.getResultList();
	}


}
