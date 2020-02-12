package pe.com.gesadmin.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import pe.com.gesadmin.dao.TipoDocumentoDao;
import pe.com.gesadmin.entity.TipoDocumento;

public class TipoDocumentoDaoImpl implements TipoDocumentoDao {
	
	@PersistenceContext(unitName = "gesadminPU")
	EntityManager em;

	@Override
	public List<TipoDocumento> findAll() {
		// TODO Auto-generated method stub
		String query = "select new pe.com.gesadmin.entity.TipoDocumento(b.id, b.descripcion, b.estado) FROM TipoDocumento b where b.estado = 1";
        TypedQuery tq = em.createQuery(query, TipoDocumento.class);
        List<TipoDocumento> lista = tq.getResultList();
        em.close();
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
		String query = "select b from TipoDocumento b where b.id = :id";
        TypedQuery<TipoDocumento> typedQuery = em.createQuery(query, TipoDocumento.class);
        typedQuery.setParameter("id", id);
        return typedQuery.getSingleResult();
	}


}
