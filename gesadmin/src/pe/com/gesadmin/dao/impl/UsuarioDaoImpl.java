package pe.com.gesadmin.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import pe.com.gesadmin.dao.UsuarioDao;
import pe.com.gesadmin.entity.Usuario;
import pe.com.gesadmin.transfer.UsuarioTransfer;

public class UsuarioDaoImpl implements UsuarioDao {
	
	@PersistenceContext(unitName = "gesadminPU")
	EntityManager em;

	@Override
	public List<Usuario> findAll() {
		// TODO Auto-generated method stub
		String query = "select new pe.com.gesadmin.entity.Usuario(b.id, b.descripcion, b.estado) FROM Usuario b where b.estado = 1";
        TypedQuery tq = em.createQuery(query, Usuario.class);
        List<Usuario> lista = tq.getResultList();
        em.close();
        return lista;
	}

	@Override
	public void create(Usuario entidad) {
		// TODO Auto-generated method stub
		em.persist(entidad);
	}

	@Override
	public void update(Usuario entidad) {
		// TODO Auto-generated method stub
		em.merge(entidad);
	}

	@Override
	public Usuario findById(Integer id) {
		// TODO Auto-generated method stub
		String query = "select b from Usuario b where b.id = :id";
        TypedQuery<Usuario> typedQuery = em.createQuery(query, Usuario.class);
        typedQuery.setParameter("id", id);
        return typedQuery.getSingleResult();
	}

	@Override
	public List<UsuarioTransfer> findTransferAll() {
		// TODO Auto-generated method stub
		return null;
	}


}
