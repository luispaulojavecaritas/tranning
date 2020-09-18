package pe.com.gesadmin.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import pe.com.gesadmin.dao.CategoriaOperacionDao;
import pe.com.gesadmin.entity.CategoriaOperacion;

@Stateless
public class CategoriaOperacionDaoImpl implements CategoriaOperacionDao{

	@PersistenceContext(unitName = "gesadminPU")
	EntityManager em;

	@Override
	public List<CategoriaOperacion> findAll() {
		// TODO Auto-generated method stub
		String query = "select b FROM CategoriaOperacion b order by b.id desc";
        TypedQuery<CategoriaOperacion> tq = em.createQuery(query, CategoriaOperacion.class);
        List<CategoriaOperacion> lista = tq.getResultList();
        return lista;
	}
	
	@Override
	public List<CategoriaOperacion> findAllActive() {
		// TODO Auto-generated method stub
		String query = "select b FROM CategoriaOperacion b where b.estado = 1";
        TypedQuery<CategoriaOperacion> tq = em.createQuery(query, CategoriaOperacion.class);
        List<CategoriaOperacion> lista = tq.getResultList();
        return lista;
	}

	@Override
	public void create(CategoriaOperacion entidad) {
		// TODO Auto-generated method stub
		em.persist(entidad);
	}

	@Override
	public void update(CategoriaOperacion entidad) {
		// TODO Auto-generated method stub
		em.merge(entidad);
	}

	@Override
	public CategoriaOperacion findById(Integer id) {
		// TODO Auto-generated method stub
		return em.find(CategoriaOperacion.class, id);

	}

	@Override
	public List<CategoriaOperacion> findByIdTipoOperacion(Integer idTipoOperacion) {
		// TODO Auto-generated method stub
		String query = "select b FROM CategoriaOperacion b where b.estado = 1 and b.tipoOperacion.id = :idTipoOperacion";
        TypedQuery<CategoriaOperacion> tq = em.createQuery(query, CategoriaOperacion.class);
        tq.setParameter("idTipoOperacion", idTipoOperacion);
        List<CategoriaOperacion> lista = tq.getResultList();
        return lista;
	}

	@Override
	public List<CategoriaOperacion> findByIdTipoOperacionNoServiciosNoAdministrativos(Integer idTipoOperacion) {
		// TODO Auto-generated method stub
		String query = "select b FROM CategoriaOperacion b where b.estado = 1 and b.tipoOperacion.id = :idTipoOperacion and b.id not in (1,2,3)";
        TypedQuery<CategoriaOperacion> tq = em.createQuery(query, CategoriaOperacion.class);
        tq.setParameter("idTipoOperacion", idTipoOperacion);
        List<CategoriaOperacion> lista = tq.getResultList();
        return lista;
	}


}
