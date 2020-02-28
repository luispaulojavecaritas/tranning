package pe.com.gesadmin.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import pe.com.gesadmin.dao.ProveedorDao;
import pe.com.gesadmin.entity.Proveedor;

public class ProveedorDaoImpl implements ProveedorDao{
	
	@PersistenceContext(unitName = "gesadminPU")
	EntityManager em;

	@Override
	public List<Proveedor> findAll() {
		// TODO Auto-generated method stub
		String query = "select new pe.com.gesadmin.entity.Proveedor(b.id, b.descripcion, b.estado) FROM Proveedor b where b.estado = 1";
        TypedQuery tq = em.createQuery(query, Proveedor.class);
        List<Proveedor> lista = tq.getResultList();
        em.close();
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
		String query = "select b from Proveedor b where b.id = :id";
        TypedQuery<Proveedor> typedQuery = em.createQuery(query, Proveedor.class);
        typedQuery.setParameter("id", id);
        return typedQuery.getSingleResult();
	}


}
