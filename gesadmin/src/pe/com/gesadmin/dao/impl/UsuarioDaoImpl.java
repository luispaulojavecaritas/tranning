package pe.com.gesadmin.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import pe.com.gesadmin.dao.UsuarioDao;
import pe.com.gesadmin.entity.Usuario;
import pe.com.gesadmin.transfer.UsuarioTransfer;

@Stateless
public class UsuarioDaoImpl implements UsuarioDao {
	
	@PersistenceContext(unitName = "gesadminPU")
	EntityManager em;

	@Override
	public List<Usuario> findAll() {
		// TODO Auto-generated method stub
		String query = "select b FROM Usuario b";
        TypedQuery<Usuario> tq = em.createQuery(query, Usuario.class);
        List<Usuario> lista = tq.getResultList();
        return lista;
	}
	
	@Override
	public List<UsuarioTransfer> findTransferAll() {
		// TODO Auto-generated method stub
		return null;
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
		return em.find(Usuario.class, id);
	}

	@Override
    public List<Usuario> findByUsuario (String usuario){
		// TODO Auto-generated method stub
		String query = "select b FROM Usuario b where b.usuario = :usuario and b.estado = 1";
        TypedQuery<Usuario> tq = em.createQuery(query, Usuario.class);
        tq.setParameter("usuario",usuario);
        List<Usuario> lista = tq.getResultList();
        return lista;
	}


}
