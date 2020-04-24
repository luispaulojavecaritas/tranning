package pe.com.gesadmin.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import pe.com.gesadmin.dao.TipoDocumentoDao;
import pe.com.gesadmin.entity.TipoDocumento;

@Stateless
public class TipoDocumentoDaoImpl implements TipoDocumentoDao {
	
	@PersistenceContext(unitName = "gesadminPU")
	EntityManager em;

	@Override
	public List<TipoDocumento> findAll() {
		// TODO Auto-generated method stub
		String query = "select b FROM TipoDocumento b";
        TypedQuery<TipoDocumento> tq = em.createQuery(query, TipoDocumento.class);
        List<TipoDocumento> lista = tq.getResultList();
        return lista;
	}
	
	@Override
	public List<TipoDocumento> findAllActive() {
		// TODO Auto-generated method stub
		String query = "select b FROM TipoDocumento b where b.estado = 1";
        TypedQuery<TipoDocumento> tq = em.createQuery(query, TipoDocumento.class);
        List<TipoDocumento> lista = tq.getResultList();
        return lista;
	}

	@Override
	public void create(TipoDocumento entidad) {
		// TODO Auto-generated method stub
		em.persist(entidad);
	}

	@Override
	public void update(TipoDocumento entidad) {
		// TODO Auto-generated method stub
		em.merge(entidad);
	}

	@Override
	public TipoDocumento findById(Integer id) {
		// TODO Auto-generated method stub
		return em.find(TipoDocumento.class, id);
	}


}
